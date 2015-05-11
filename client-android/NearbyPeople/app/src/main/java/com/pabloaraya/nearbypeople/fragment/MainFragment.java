package com.pabloaraya.nearbypeople.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.pabloaraya.nearbypeople.MainActivity;
import com.pabloaraya.nearbypeople.R;
import com.pabloaraya.nearbypeople.adapter.MainRootAdapter;
import com.pabloaraya.nearbypeople.model.MessageChatModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class MainFragment extends Fragment implements MaterialTabListener {

    private Toolbar toolbar;
    private PagerSlidingTabStrip tabs;
    private ViewPager mViewPager;

    MaterialTabHost tabHost;

    private MainRootAdapter chatListAdapter;

    /* Socket */
    public static Socket socket;

    /* Socket Constant */
    //final public static String SOCKET_URL  = "http://54.86.110.206/";
    final public static String SOCKET_URL  = "http://192.168.56.1/";

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        chatListAdapter = new MainRootAdapter(getActivity(), getChildFragmentManager());
        // TODO: Only we need one more time, to add

        try {
            socket = IO.socket(SOCKET_URL);
            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {

                    // TODO: There is the magic!
                }
            });
            socket.on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    // TODO: Here the game is over
                }
            });

            socket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        /* Set actionbar */
        toolbar = (Toolbar)view.findViewById(R.id.toolbar);
        if (toolbar != null) {
            ((ActionBarActivity)getActivity()).setSupportActionBar(toolbar);
            ((ActionBarActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }

        tabHost = (MaterialTabHost)view.findViewById(R.id.materialTabHost);

        mViewPager = (ViewPager) view.findViewById(R.id.pagerChat);
        mViewPager.setAdapter(chatListAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // when user do a swipe the selected tab change
                tabHost.setSelectedNavigationItem(position);
            }
        });

        // insert all tabs from pagerAdapter data
        for (int i = 0; i < chatListAdapter.getCount(); i++) {
            tabHost.addTab(
                    tabHost.newTab()
                            .setIcon(getResources().getDrawable(chatListAdapter.getIcon(i)))
                            .setTabListener(this)
            );
        }

        /*tabs = (PagerSlidingTabStrip)view.findViewById(R.id.tabs);
        tabs.setIndicatorHeight(5);
        tabs.setIndicatorColorResource(android.R.color.white);
        tabs.setBackgroundResource(R.color.app_color);
        tabs.setTextColorResource(android.R.color.white);

        //tabs.setShouldExpand(true);
        tabs.setOnPageChangeListener(this);
        tabs.setViewPager(mViewPager);*/

        return view;
    }

    @Override
    public void onTabSelected(MaterialTab materialTab) {
        mViewPager.setCurrentItem(materialTab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }
}
