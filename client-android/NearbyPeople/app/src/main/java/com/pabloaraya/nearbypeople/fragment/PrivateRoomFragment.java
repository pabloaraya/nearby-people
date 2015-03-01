package com.pabloaraya.nearbypeople.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pabloaraya.nearbypeople.R;

public class PrivateRoomFragment extends Fragment {

    public static PrivateRoomFragment newInstance() {
        PrivateRoomFragment fragment = new PrivateRoomFragment();
        return fragment;
    }

    public PrivateRoomFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_private_room, container, false);
    }

}
