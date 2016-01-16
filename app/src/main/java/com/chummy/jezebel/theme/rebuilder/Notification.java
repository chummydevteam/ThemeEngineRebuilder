package com.chummy.jezebel.theme.rebuilder;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import eu.chainfire.libsuperuser.Shell;

public class Notification extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Shell.SU.available()) {
            (new StartUp()).setContext(getApplicationContext()).execute("reboot");
        } else {
            finish();
            System.exit(0);
        }
    }

    private class StartUp extends AsyncTask<String, Void, Void> {


        boolean suAvailable = false;
        private Context context = null;

        public StartUp setContext(Context context) {
            this.context = context;
            return this;
        }

        @Override
        protected Void doInBackground(String... params) {
            suAvailable = Shell.SU.available();
            if (suAvailable) {
                switch (params[0]) {
                    case "reboot":
                        Shell.SU.run("reboot");
                        break;
                    case "recov":
                        Shell.SU.run("reboot recovery");
                        break;
                    case "shutdown":
                        Shell.SU.run("reboot -p");
                        break;
                    case "sysui":
                        Shell.SU.run("pkill com.android.systemui");
                        break;
                }
            }

            return null;
        }

    }

}