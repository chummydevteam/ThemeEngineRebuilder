package com.chummy.jezebel.theme.rebuilder;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.io.DataOutputStream;
import java.io.IOException;

import eu.chainfire.libsuperuser.Shell;

public class MainActivity extends Activity {
    private NotificationManager myNotificationManager;
    private int notificationIdOne = 111;
    private RemoteViews contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Shell.SU.available()) {
            rebuildThemeCache();
        } else {
            createNotification("Theme Rebuilder is unable to start.",
                    "This app requires root functionality enabled on your device.",
                    android.R.drawable.ic_dialog_alert);
            finish();
            System.exit(0);
        }
    }

    public void RunAsRoot(String[] cmds) {
        try {
            Process p = Runtime.getRuntime().exec("su");
            DataOutputStream os = new DataOutputStream(p.getOutputStream());
            for (String tmpCmd : cmds) {
                os.writeBytes(tmpCmd + "\n");
            }
            os.writeBytes("exit\n");
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
            Toast toast = Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG);
            toast.show();
        }
    }


    private void rebuildThemeCache() {

        new MaterialDialog.Builder(this)
                .title("Rebuild Theme Engine Cache")
                .content(getResources().getString(R.string.rebuild_disclaimer))
                .backgroundColorRes(R.color.dialog_background)
                .positiveColorRes(R.color.accent)
                .neutralColorRes(R.color.accent)
                .negativeColorRes(R.color.accent)
                .positiveText("PROCEED")
                .negativeText("CANCEL")
                .cancelable(false)
                .callback(new MaterialDialog.ButtonCallback() {
                    public void onPositive(MaterialDialog dialog) {
                        rebuildCommands();
                        createNotification("All Theme Caches Cleared!",
                                "Click here to reboot your device immediately.",
                                android.R.drawable.stat_notify_sync);
                        finish();
                        System.exit(0);
                    }

                    public void onNegative(MaterialDialog dialog) {
                        finish();
                        System.exit(0);
                    }


                })
                .show();
    }

    protected void rebuildCommands() {
        String[] commands_mount3 = {"mount -o remount,rw /system"};
        String[] commands_backup = {"rm -r /data/resource-cache"};
        String[] commands_close3 = {"mount -o remount,ro /system"};

        RunAsRoot(commands_mount3);
        RunAsRoot(commands_backup);
        RunAsRoot(commands_close3);
    }

    protected void createNotification(String title, String content, int resID) {

        // Invoking the default notification service
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);

        contentView = new RemoteViews(getApplicationContext().getPackageName(), R.layout.notification);
        contentView.setTextViewText(R.id.notiftitle, title);
        contentView.setTextViewText(R.id.notiftext, content);
        contentView.setImageViewResource(R.id.notifimage, resID);

        mBuilder.setContentTitle(title);
        mBuilder.setContentText(content);
        mBuilder.setSmallIcon(resID);
        mBuilder.setContent(contentView);

        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, Notification.class);
        resultIntent.putExtra("notificationId", notificationIdOne);

        //This ensures that navigating backward from the Activity leads out of the app to Home page
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        // Adds the back stack for the Intent
        stackBuilder.addParentStack(Notification.class);

        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_ONE_SHOT //can only be used once
                );
        // start the activity when the user clicks the notification text
        mBuilder.setContentIntent(resultPendingIntent);

        myNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // pass the Notification object to the system
        myNotificationManager.notify(notificationIdOne, mBuilder.build());
    }

}
