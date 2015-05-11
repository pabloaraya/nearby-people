package com.pabloaraya.nearbypeople.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;

import com.pabloaraya.nearbypeople.R;
import com.pabloaraya.nearbypeople.fragment.NearbyPeopleFragment;
import com.pabloaraya.nearbypeople.fragment.PrivateRoomFragment;
import com.pabloaraya.nearbypeople.fragment.ProfileFragment;

/**
 * Created by pablo on 2/28/15.
 */
public class MainRootAdapter extends FragmentPagerAdapter {

    Context context;

    private int[] imageResId = {
            R.drawable.ic_action_people,
            //R.drawable.ic_action_chat,
            R.drawable.ic_action_profile
    };

    public MainRootAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a DummySectionFragment (defined as a static inner class
        // below) with the page number as its lone argument.

        switch(position) {
            case 0:
                return NearbyPeopleFragment.newInstance();
            //case 1:
            //    return PrivateRoomFragment.newInstance();
            case 1:
                return ProfileFragment.newInstance();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Nearby People";
            //case 1:
            //    return "Private Messages";
            case 1:
                return "Profile";
        }
        return null;
    }

    public int getIcon(int i){
        return imageResId[i];
    }
}

