package bliss.about.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;

import bliss.about.R;


public class SupportedDevicesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.section_supported_devices, container, false);

        ActionBar toolbar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (toolbar != null)
            toolbar.setTitle(R.string.section_supported_devices);

        CardView zenfone2 = (CardView) root.findViewById(R.id.asus_zenfone2);
        zenfone2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent devweb = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.zenfone2)));
                startActivity(devweb);
            }
        });

        CardView m8 = (CardView) root.findViewById(R.id.htc_one_m8);
        m8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent devweb = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.m8)));
                startActivity(devweb);
            }
        });

        CardView nexus4 = (CardView) root.findViewById(R.id.nexus_4);
        nexus4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotSupportedDialog();
            }
        });

        CardView nexus5 = (CardView) root.findViewById(R.id.nexus_5);
        nexus5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent devweb = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.hammerhead)));
                startActivity(devweb);
            }
        });

        CardView nexus6 = (CardView) root.findViewById(R.id.nexus_6);
        nexus6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent devweb = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.shamu)));
                startActivity(devweb);
            }
        });

        CardView nexus_7_2012 = (CardView) root.findViewById(R.id.nexus_7_2012);
        nexus_7_2012.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent devweb = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.nexus7_2012)));
                startActivity(devweb);
            }
        });

        CardView nexus_7_2013 = (CardView) root.findViewById(R.id.nexus_7_2013);
        nexus_7_2013.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent devweb = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.nexus7_2013)));
                startActivity(devweb);
            }
        });

        CardView lg_g2 = (CardView) root.findViewById(R.id.lg_g2);
        lg_g2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent devweb = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.lg_g2)));
                startActivity(devweb);
            }
        });

        CardView lg_g3 = (CardView) root.findViewById(R.id.lg_g3);
        lg_g3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent devweb = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.lg_g3)));
                startActivity(devweb);
            }
        });

        CardView lg_g4 = (CardView) root.findViewById(R.id.lg_g4);
        lg_g4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent devweb = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.lg_g4)));
                startActivity(devweb);
            }
        });


        CardView motoxpure = (CardView) root.findViewById(R.id.motoxpure);
        motoxpure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotSupportedDialog();
            }
        });


        CardView nvidiashieldtab = (CardView) root.findViewById(R.id.nvidiashieldtab);
        nvidiashieldtab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent devweb = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.shieldtab)));
                startActivity(devweb);
            }
        });


        CardView s4 = (CardView) root.findViewById(R.id.galaxys4);
        s4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent devweb = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.s4)));
                startActivity(devweb);
            }
        });

        CardView n2 = (CardView) root.findViewById(R.id.galaxyn2);
        n2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent devweb = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.note2)));
                startActivity(devweb);
            }
        });

        CardView n4 = (CardView) root.findViewById(R.id.galaxyn4);
        n4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent devweb = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.note4)));
                startActivity(devweb);
            }
        });

        CardView apply = (CardView) root.findViewById(R.id.apply);
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent devweb = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.apply_for_maintainer)));
                startActivity(devweb);
            }
        });

        return root;
    }

    private void showNotSupportedDialog() {
        new MaterialDialog.Builder(getActivity())
                .title("Sorry, this device does not have an XDA thread yet.")
                .content("If you believe you are receiving this error, please contact your device maintainer.")
                .positiveText("ALRIGHT")
                .show();
    }

}
