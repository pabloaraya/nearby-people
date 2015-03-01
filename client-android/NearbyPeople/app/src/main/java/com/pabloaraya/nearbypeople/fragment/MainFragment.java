package com.pabloaraya.nearbypeople.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.pabloaraya.nearbypeople.R;
import com.pabloaraya.nearbypeople.adapter.MainRootAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class MainFragment extends Fragment implements ViewPager.OnPageChangeListener{

    private Toolbar toolbar;
    private PagerSlidingTabStrip tabs;
    private ViewPager mViewPager;

    private MainRootAdapter chatListAdapter;

    /* Socket */
    public static Socket socket;

    /* Socket Constant */
    final private static String SOCKET_URL  = "http://54.86.110.206/";
    final private static String EVENT_MESSAGE = "message";

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

        try {
            socket = IO.socket(SOCKET_URL);
            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {

                }
            });
            socket.on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {

                }
            });
            socket.on(EVENT_MESSAGE, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    try {
                        JSONObject messageJson = new JSONObject(args[0].toString());
                        /*final MessageModel messageModel = new MessageModel(
                                messageJson.getString(VAR_MESSAGE),
                                messageJson.getString(VAR_NAME));

                        Thread thread = new Thread() {
                            @Override
                            public void run() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        messageAdapter.addMessage(messageModel);
                                    }
                                });
                            }
                        };
                        thread.start();*/
                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
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

        mViewPager = (ViewPager) view.findViewById(R.id.pagerChat);
        mViewPager.setAdapter(chatListAdapter);

        tabs = (PagerSlidingTabStrip)view.findViewById(R.id.tabs);
        tabs.setIndicatorHeight(5);
        tabs.setIndicatorColorResource(android.R.color.white);
        tabs.setBackgroundResource(R.color.app_color);
        tabs.setTextColorResource(android.R.color.white);

        //tabs.setShouldExpand(true);
        tabs.setOnPageChangeListener(this);
        tabs.setViewPager(mViewPager);

        return view;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
