package bliss.about.fragments;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melnykov.fab.FloatingActionButton;
import com.melnykov.fab.ObservableScrollView;

import bliss.about.R;
import bliss.about.activities.MainActivity;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.section_home, container, false);

        ActionBar toolbar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (toolbar != null) {
            toolbar.setTitle(R.string.app_name);
        }

        ObservableScrollView content = (ObservableScrollView) root.findViewById(R.id.HomeContent);

        FloatingActionButton fab = (FloatingActionButton) root.findViewById(R.id.apply_btn);
        fab.setColorNormal(getResources().getColor(R.color.accent));
        fab.setColorPressed(getResources().getColor(R.color.accent_pressed));
        fab.setColorRipple(getResources().getColor(R.color.semitransparent_white));
        fab.show(true);
        fab.attachToScrollView(content);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).result.setSelectionByIdentifier(3);
                ((MainActivity) getActivity()).switchFragment(3, getResources().getString(R.string.section_three), "Apply");
            }
        });

        return root;
    }

    private boolean AppIsInstalled(String packageName) {
        final PackageManager pm = getActivity().getPackageManager();
        boolean installed;
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }
        return installed;
    }

}
