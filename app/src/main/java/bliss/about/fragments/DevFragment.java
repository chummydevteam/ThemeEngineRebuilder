package bliss.about.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import bliss.about.R;

public class DevFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.section_developers, container, false);

        ActionBar toolbar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (toolbar != null)
            toolbar.setTitle(R.string.section_developers);

        // Patrick
        TextView pat_xda = (TextView) root.findViewById(R.id.web_button);
        pat_xda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent devweb = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.patrick_xda)));
                startActivity(devweb);
            }
        });

        TextView pat_gplus = (TextView) root.findViewById(R.id.gplus_button);
        pat_gplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent devgplus = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.patrick_gplus)));
                startActivity(devgplus);
            }
        });

        // Jean
        TextView jean_xda = (TextView) root.findViewById(R.id.web_button2);
        jean_xda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent devweb = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.jean_xda)));
                startActivity(devweb);
            }
        });

        TextView jean_gplus = (TextView) root.findViewById(R.id.gplus_button2);
        jean_gplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent devgplus = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.jean_gplus)));
                startActivity(devgplus);
            }
        });

        // Jon
        TextView jon_xda = (TextView) root.findViewById(R.id.web_button3);
        jon_xda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent devweb = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.jon_xda)));
                startActivity(devweb);
            }
        });

        TextView jon_gplus = (TextView) root.findViewById(R.id.gplus_button3);
        jon_gplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent devgplus = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.jon_gplus)));
                startActivity(devgplus);
            }
        });

        // Nicholas
        TextView nicholas_xda = (TextView) root.findViewById(R.id.web_button4);
        nicholas_xda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent devweb = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.nicholas_xda)));
                startActivity(devweb);
            }
        });

        TextView nicholas_gplus = (TextView) root.findViewById(R.id.gplus_button4);
        nicholas_gplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent devgplus = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.nicholas_gplus)));
                startActivity(devgplus);
            }
        });


        return root;
    }

}
