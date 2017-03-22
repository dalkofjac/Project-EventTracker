package com.dk.eventtracker.helpers;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import com.dk.eventtracker.R;

/**
 * Created by Dalibor on 22.3.2017..
 */

public class FragmentStarter {
    public static void StartNewFragment (Fragment fragment, Activity mActivity, int priority) {
        if (priority == 1) {
            FragmentManager fragmentManager = mActivity.getFragmentManager();
            if (fragmentManager.getBackStackEntryCount() <= 1) {
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.addToBackStack("2");
                ft.replace(R.id.fragment_container, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            } else {
                fragmentManager.popBackStack("1", 0);
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack("2")
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }
        }
        else{
            FragmentTransaction fm = mActivity.getFragmentManager().beginTransaction();
            fm.replace(R.id.fragment_container, fragment);
            fm.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fm.addToBackStack("1");
            fm.commit();
        }
    }
}
