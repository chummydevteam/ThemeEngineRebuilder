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

import bliss.about.R;

public class MaintainerFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.section_maintainers, container, false);

        ActionBar toolbar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (toolbar != null)
            toolbar.setTitle(R.string.section_maintainers);

        CardView aclegg = (CardView) root.findViewById(R.id.aclegg_card);
        aclegg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent devweb = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.aclegg2011)));
                startActivity(devweb);
            }
        });

        CardView bito = (CardView) root.findViewById(R.id.bito_card);
        bito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent devweb = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.bito)));
                startActivity(devweb);
            }
        });

        CardView blueyes = (CardView) root.findViewById(R.id.blueyes_card);
        blueyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent devweb = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.blueyes)));
                startActivity(devweb);
            }
        });

        CardView darkeyes = (CardView) root.findViewById(R.id.darkeyes_card);
        darkeyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent devweb = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.dark_eyes_)));
                startActivity(devweb);
            }
        });

        CardView electrikjesus = (CardView) root.findViewById(R.id.electrikjesus_card);
        electrikjesus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent devweb = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.electrikjesus)));
                startActivity(devweb);
            }
        });

        CardView fizbanrapper = (CardView) root.findViewById(R.id.fizbanrapper_card);
        fizbanrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent devweb = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.fizbanrapper)));
                startActivity(devweb);
            }
        });

        CardView genesixx = (CardView) root.findViewById(R.id.genesixx_card);
        genesixx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent devweb = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.genesixxbf3)));
                startActivity(devweb);
            }
        });

        CardView keturr74 = (CardView) root.findViewById(R.id.keturr74_card);
        keturr74.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent devweb = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.KeTuRr74)));
                startActivity(devweb);
            }
        });

        CardView pjgraber03 = (CardView) root.findViewById(R.id.pjgraber_card);
        pjgraber03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent devweb = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.pjgraber03)));
                startActivity(devweb);
            }
        });

        CardView pstrang = (CardView) root.findViewById(R.id.pstrang_card);
        pstrang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent devweb = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.pstrang)));
                startActivity(devweb);
            }
        });


        return root;
    }

}
