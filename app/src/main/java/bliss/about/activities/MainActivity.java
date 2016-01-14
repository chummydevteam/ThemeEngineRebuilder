package bliss.about.activities;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import bliss.about.adapters.ChangelogAdapter;
import bliss.about.utilities.Preferences;
import bliss.about.utilities.Util;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.pkmmte.requestmanager.PkRequestManager;
import com.pkmmte.requestmanager.RequestSettings;

import bliss.about.R;


public class MainActivity extends AppCompatActivity {

    private static final boolean WITH_LICENSE_CHECKER = false;
    private static final String MARKET_URL = "https://play.google.com/store/apps/details?id=";
    public String thaApp, thaHome, thaPreviews, thaApply, thaWalls, thaRequest, thaCredits;
    public String current_system_name_lower, current_system_name_upper;
    public Drawer result = null;
    public String version;
    private int currentItem = -1;
    private boolean firstrun, enable_features, a;
    private Preferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Uncomment this for custom themeing
        // setTheme(R.style.CustomTheme);

        // Grab a reference to the manager and store it in a variable. This helps make code shorter.
        PkRequestManager mRequestManager = PkRequestManager.getInstance(this);
        mRequestManager.setDebugging(false);
        // Set your custom settings. Email address is required! Everything else is set to default.
        mRequestManager.setSettings(new RequestSettings.Builder()
                .addEmailAddress(getResources().getString(R.string.email_id))
                .emailSubject(getResources().getString(R.string.email_request_subject))
                .emailPrecontent(getResources().getString(R.string.request_precontent))
                .saveLocation(Environment.getExternalStorageDirectory().getAbsolutePath() + getResources().getString(R.string.request_save_location))
                .build());
        mRequestManager.loadAppsIfEmptyAsync();

        mPrefs = new Preferences(MainActivity.this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(true)
                .withProfileImagesClickable(false)
                .withProfileImagesVisible(true)
                .addProfiles(
                        new ProfileDrawerItem().withName("Team Bliss").withIcon(getResources().getDrawable(R.drawable.bliss))
                )
                .withHeaderBackground(R.drawable.header)
                .withSelectionFirstLine(getResources().getString(R.string.app_long_name))
                .withSelectionSecondLine(Build.DEVICE)
                .withSavedInstance(savedInstanceState)
                .build();

        enable_features = mPrefs.isFeaturesEnabled();
        firstrun = mPrefs.isFirstRun();

        thaApp = getResources().getString(R.string.app_name);
        thaHome = getResources().getString(R.string.section_one);
        thaPreviews = getResources().getString(R.string.section_two);
        thaApply = getResources().getString(R.string.section_three);
        thaWalls = getResources().getString(R.string.section_four);
        thaRequest = getResources().getString(R.string.section_five);

        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withDrawerWidthDp(350)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Main Screen").withIcon(GoogleMaterial.Icon.gmd_home).withIdentifier(1),
                        new PrimaryDrawerItem().withName("About the App").withIcon(GoogleMaterial.Icon.gmd_info).withIdentifier(2),
                        new SectionDrawerItem().withName("Social Media"),
                        new PrimaryDrawerItem().withName("Google+").withDescription("Direct support with the devs.").withCheckable(false).withIcon(GoogleMaterial.Icon.gmd_palette).withIdentifier(3),
                        new PrimaryDrawerItem().withName("Pushbullet").withDescription("Ready for bleeding edge updates?!").withCheckable(false).withIcon(GoogleMaterial.Icon.gmd_palette).withIdentifier(4),
                        new PrimaryDrawerItem().withName("GitHub").withDescription("Come join our open source development!").withCheckable(false).withIcon(GoogleMaterial.Icon.gmd_palette).withIdentifier(5),
                        new PrimaryDrawerItem().withName("Official Site").withDescription("Come check our site out!").withCheckable(false).withIcon(GoogleMaterial.Icon.gmd_palette).withIdentifier(6),
                        new SectionDrawerItem().withName("BlissRom Specifics"),
                        new PrimaryDrawerItem().withName("Current Device Thread").withDescription("Launch the XDA page for this device.").withCheckable(false).withIcon(GoogleMaterial.Icon.gmd_palette).withIdentifier(7),
                        new PrimaryDrawerItem().withName("All Supported Devices").withDescription("Browse all supported devices.").withCheckable(false).withIcon(GoogleMaterial.Icon.gmd_palette).withIdentifier(8),
                        new SectionDrawerItem().withName("About the Team"),
                        new PrimaryDrawerItem().withName("Developers").withDescription("List of all developers of Team Bliss.").withIcon(GoogleMaterial.Icon.gmd_palette).withIdentifier(9),
                        new PrimaryDrawerItem().withName("Maintainers").withDescription("List of maintainers for Team Bliss.").withIcon(GoogleMaterial.Icon.gmd_palette).withIdentifier(10)

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {

                        if (drawerItem != null) {
                            a = true;
                            switch (drawerItem.getIdentifier()) {
                                case 1:
                                    switchFragment(1, thaApp, "Home");
                                    break;
                                case 2:
                                    switchFragment(2, thaCredits, "Credits");
                                    break;
                                case 3:
                                    // Google+ Link
                                    Intent googlePlusIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/u/1/communities/118265887490106132524"));
                                    startActivity(googlePlusIntent);
                                    break;
                                case 5:
                                    // Pushbullet Link
                                    Intent pushbulletIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.pushbullet.com/channel?tag=blissroms"));
                                    startActivity(pushbulletIntent);
                                    break;
                                case 6:
                                    // GitHub Link
                                    Intent githubIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/BlissRoms"));
                                    startActivity(githubIntent);
                                    break;
                                case 7:
                                    // Device checker
                                    current_system_name_lower = Build.DEVICE;
                                    current_system_name_upper = current_system_name_lower.toUpperCase();
                                    switch (current_system_name_upper) {
                                        // ASUS Devices

                                        case "Z008":
                                            Intent Z008 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://forum.xda-developers.com/zenfone2/development/official-bliss-v6-0-zenfone-2-team-bliss-t3285686"));
                                            startActivity(Z008);
                                            break;
                                        case "Z00A":
                                            Intent Z00A = new Intent(Intent.ACTION_VIEW, Uri.parse("http://forum.xda-developers.com/zenfone2/development/official-bliss-v6-0-zenfone-2-team-bliss-t3285686"));
                                            startActivity(Z00A);
                                            break;
                                        case "M8":
                                            Intent M8 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://forum.xda-developers.com/htc-one-m8/development/rom-bliss-stalk-team-bliss-t2913348"));
                                            startActivity(M8);
                                            break;

                                        // Google Devices

                                        case "HAMMERHEAD":
                                            Intent HAMMERHEAD = new Intent(Intent.ACTION_VIEW, Uri.parse("http://forum.xda-developers.com/google-nexus-5/development/rom-t2995181"));
                                            startActivity(HAMMERHEAD);
                                            break;
                                        case "SHAMU":
                                            Intent SHAMU = new Intent(Intent.ACTION_VIEW, Uri.parse("http://forum.xda-developers.com/nexus-6/development/rom-blisspop-team-bliss-t3077737"));
                                            startActivity(SHAMU);
                                            break;
                                        case "GROUPER":
                                            Intent GROUPER = new Intent(Intent.ACTION_VIEW, Uri.parse("http://forum.xda-developers.com/nexus-7/development/rom-blisspop-teambliss-t3137240"));
                                            startActivity(GROUPER);
                                            break;
                                        case "TILAPIA":
                                            Intent TILAPIA = new Intent(Intent.ACTION_VIEW, Uri.parse("http://forum.xda-developers.com/nexus-7/development/rom-blisspop-teambliss-t3137240"));
                                            startActivity(TILAPIA);
                                            break;
                                        case "FLO":
                                            Intent FLO = new Intent(Intent.ACTION_VIEW, Uri.parse("http://forum.xda-developers.com/nexus-7-2013/development/rom-blisspop-teambliss-v1-8-t3011252"));
                                            startActivity(FLO);
                                            break;
                                        case "DEB":
                                            Intent DEB = new Intent(Intent.ACTION_VIEW, Uri.parse("http://forum.xda-developers.com/nexus-7-2013/development/rom-blisspop-teambliss-v1-8-t3011252"));
                                            startActivity(DEB);
                                            break;

                                        // Nicholas' Debugging Devices

                                        case "SCORPION": // debugging
                                            Intent scorp = new Intent(Intent.ACTION_VIEW, Uri.parse("http://forum.xda-developers.com/nexus-7-2013/development/rom-blisspop-teambliss-v1-8-t3011252"));
                                            startActivity(scorp);
                                            break;

                                        // LG G2 Variants

                                        case "D800":
                                            Intent D800 = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.lg_g2)));
                                            startActivity(D800);
                                            break;
                                        case "D801":
                                            Intent D801 = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.lg_g2)));
                                            startActivity(D801);
                                            break;
                                        case "D802":
                                            Intent D802 = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.lg_g2)));
                                            startActivity(D802);
                                            break;
                                        case "D803":
                                            Intent D803 = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.lg_g2)));
                                            startActivity(D803);
                                            break;
                                        case "F320":
                                            Intent F320 = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.lg_g2)));
                                            startActivity(F320);
                                            break;
                                        case "LS980":
                                            Intent LS980 = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.lg_g2)));
                                            startActivity(LS980);
                                            break;
                                        case "VS980":
                                            Intent VS980 = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.lg_g2)));
                                            startActivity(VS980);
                                            break;

                                        // LG G3 Variants

                                        case "D850":
                                            Intent D850 = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.lg_g3)));
                                            startActivity(D850);
                                            break;
                                        case "D851":
                                            Intent D851 = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.lg_g3)));
                                            startActivity(D851);
                                            break;
                                        case "D852":
                                            Intent D852 = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.lg_g3)));
                                            startActivity(D852);
                                            break;
                                        case "D855":
                                            Intent D855 = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.lg_g3)));
                                            startActivity(D855);
                                            break;
                                        case "LS990":
                                            Intent LS990 = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.lg_g3)));
                                            startActivity(LS990);
                                            break;
                                        case "VS985":
                                            Intent VS985 = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.lg_g3)));
                                            startActivity(VS985);
                                            break;

                                        // LG G4 Variants

                                        case "H811":
                                            Intent H811 = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.lg_g4)));
                                            startActivity(H811);
                                            break;
                                        case "H815":
                                            Intent H815 = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.lg_g4)));
                                            startActivity(H815);
                                            break;

                                        // Motorola Devices

                                        case "CLARK":
                                            showNotSupportedDialog();
                                            break;

                                        // NVIDIA Devices

                                        case "SHIELDTAB":
                                            Intent SHIELDTAB = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.shieldtab)));
                                            startActivity(SHIELDTAB);
                                            break;

                                        // Samsung Devices

                                        case "JFLTE":
                                            Intent JFLTE = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.s4)));
                                            startActivity(JFLTE);
                                            break;
                                        case "JFLTEATT":
                                            Intent JFLTEATT = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.s4)));
                                            startActivity(JFLTEATT);
                                            break;
                                        case "JFLTETMO":
                                            Intent JFLTETMO = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.s4)));
                                            startActivity(JFLTETMO);
                                            break;
                                        case "JFLTEXX":
                                            Intent JFLTEXX = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.s4)));
                                            startActivity(JFLTEXX);
                                            break;
                                        case "JFLTESPR":
                                            Intent JFLTESPR = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.s4)));
                                            startActivity(JFLTESPR);
                                            break;
                                        case "I9500":
                                            Intent I9500 = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.s4)));
                                            startActivity(I9500);
                                            break;
                                        case "KLTE":
                                            showNotSupportedDialog();
                                            break;
                                        case "N7100":
                                            Intent N7100 = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.note2)));
                                            startActivity(N7100);
                                            break;
                                        case "N7105":
                                            Intent N7105 = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.note2)));
                                            startActivity(N7105);
                                            break;
                                        case "TRLTEXX":
                                            Intent TRLTEXX = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.note4)));
                                            startActivity(TRLTEXX);
                                            break;
                                        case "TRLTETD":
                                            Intent TRLTETD = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.note4)));
                                            startActivity(TRLTETD);
                                            break;
                                        case "TRLTETMO":
                                            Intent TRLTETMO = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.note4)));
                                            startActivity(TRLTETMO);
                                            break;
                                        case "TRLTECAN":
                                            Intent TRLTECAN = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.note4)));
                                            startActivity(TRLTECAN);
                                            break;
                                        default:
                                            showNotSupportedDialog();
                                            break;
                                    }
                                    break;
                            }

                        } else {
                            a = false;
                        }
                        return a;
                    }

                })
                .withSavedInstance(savedInstanceState)
                .build();

        result.getListView().setVerticalScrollBarEnabled(false);

        if (savedInstanceState == null) {
            currentItem = -1;
            result.setSelectionByIdentifier(1);
        }
    }

    public void switchFragment(int itemId, String title, String fragment) {
        if (currentItem == itemId) {
            // Don't allow re-selection of the currently active item
            return;
        }
        currentItem = itemId;
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(title);

        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(R.id.main, Fragment.instantiate(MainActivity.this,
                        "bliss.about.fragments." + fragment + "Fragment"))
                .commit();

        if (result.isDrawerOpen()) {
            result.closeDrawer();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState = result.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else if (result != null && currentItem != 1) {
            result.setSelection(0);
        } else if (result != null) {
            super.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.share:
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody =
                        getResources().getString(R.string.share_one) +
                                getResources().getString(R.string.iconpack_designer) +
                                getResources().getString(R.string.share_two) +
                                MARKET_URL + getPackageName();
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, (getResources().getString(R.string.share_title))));
                break;

            case R.id.sendemail:
                StringBuilder emailBuilder = new StringBuilder();

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + getResources().getString(R.string.email_id)));
                intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.email_subject));

                emailBuilder.append("\n \n \nOS Version: ").append(System.getProperty("os.version")).append("(").append(Build.VERSION.INCREMENTAL).append(")");
                emailBuilder.append("\nOS API Level: ").append(Build.VERSION.SDK_INT);
                emailBuilder.append("\nDevice: ").append(Build.DEVICE);
                emailBuilder.append("\nManufacturer: ").append(Build.MANUFACTURER);
                emailBuilder.append("\nModel (and Product): ").append(Build.MODEL).append(" (").append(Build.PRODUCT).append(")");
                PackageInfo appInfo = null;
                try {
                    appInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                assert appInfo != null;
                emailBuilder.append("\nApp Version Name: ").append(appInfo.versionName);
                emailBuilder.append("\nApp Version Code: ").append(appInfo.versionCode);

                intent.putExtra(Intent.EXTRA_TEXT, emailBuilder.toString());
                startActivity(Intent.createChooser(intent, (getResources().getString(R.string.send_title))));
                break;

            case R.id.changelog:
                showChangelog();
                break;
        }
        return true;
    }

    private void showNotSupportedDialog() {
        new MaterialDialog.Builder(this)
                .title("Sorry, this device does not have an XDA thread yet.")
                .content("If you believe you are receiving this error, please contact your device maintainer.")
                .positiveText("ALRIGHT")
                .show();
    }

    private void showChangelog() {
        new MaterialDialog.Builder(this)
                .title(R.string.changelog_dialog_title)
                .adapter(new ChangelogAdapter(this, R.array.fullchangelog), null)
                .positiveText(R.string.nice)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        mPrefs.setNotFirstrun();
                    }
                }).show();
    }

    private void showChangelogDialog() {
        String launchinfo = getSharedPreferences("PrefsFile", MODE_PRIVATE).getString("version", "0");
        if (launchinfo != null && !launchinfo.equals(Util.getAppVersion(this)))
            showChangelog();
        storeSharedPrefs();
    }

    @SuppressLint("CommitPrefEdits")
    private void storeSharedPrefs() {
        SharedPreferences sharedPreferences = getSharedPreferences("PrefsFile", MODE_PRIVATE);
        sharedPreferences.edit().putString("version", Util.getAppVersion(this)).commit();
    }

}
