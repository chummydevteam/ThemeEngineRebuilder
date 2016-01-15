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
import android.widget.TextView;

import bliss.about.R;

public class CreditsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.section_credits, container, false);

        ActionBar toolbar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (toolbar != null)
            toolbar.setTitle(R.string.section_about);

        CardView banner = (CardView) root.findViewById(R.id.about_banner);
        banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent devweb = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.about_bliss_repo)));
                startActivity(devweb);
            }
        });

        TextView web = (TextView) root.findViewById(R.id.web_button);
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent devweb = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.dev_link)));
                startActivity(devweb);
            }
        });

        TextView googleplus = (TextView) root.findViewById(R.id.gplus_button);
        googleplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent devgplus = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.dev_gplus_link)));
                startActivity(devgplus);
            }
        });


        return root;
    }

}
