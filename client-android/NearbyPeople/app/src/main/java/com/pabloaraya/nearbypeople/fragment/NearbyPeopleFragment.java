package com.pabloaraya.nearbypeople.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;

import com.github.nkzawa.emitter.Emitter;
import com.pabloaraya.nearbypeople.MainActivity;
import com.pabloaraya.nearbypeople.R;
import com.pabloaraya.nearbypeople.adapter.MessageAdapter;
import com.pabloaraya.nearbypeople.model.MessageChatModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NearbyPeopleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NearbyPeopleFragment extends Fragment {

    private LinearLayoutManager linearLayoutManager;;
    private RecyclerView recyclerView;
    private MessageAdapter messageAdapter;

    /* Variables Constant */
    final private static String VAR_USER_ID = "user_id";
    final private static String VAR_NAME = "name";
    final private static String VAR_AVATAR = "avatar";
    final private static String VAR_MESSAGE = "message";
    final private static String VAR_IS_MINE = "mine";

    final private static String VAR_EMPTY = "unknown";


    final public static String EVENT_MESSAGE = "message";


    // TODO: Rename and change types and number of parameters
    public static NearbyPeopleFragment newInstance() {
        NearbyPeopleFragment fragment = new NearbyPeopleFragment();
        return fragment;
    }

    public NearbyPeopleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainFragment.socket.on(EVENT_MESSAGE, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                try {
                    JSONObject messageJson = new JSONObject(args[0].toString());
                    final MessageChatModel messageModel = new MessageChatModel();
                    messageModel.setUserId(messageJson.getString(VAR_USER_ID));
                    messageModel.setUserAvatar(messageJson.getString(VAR_AVATAR));
                    messageModel.setUserName(messageJson.getString(VAR_NAME));
                    messageModel.setUserMessage(messageJson.getString(VAR_MESSAGE));
                    messageModel.setMine(false);

                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    messageAdapter.addMessage(messageModel);
                                }
                            });
                        }
                    };
                    thread.start();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_nearby_people, container, false);

        /* Initialize recycler view */
        recyclerView = (RecyclerView)view.findViewById(R.id.listViewChat);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        /* Set adapter */
        messageAdapter = new MessageAdapter(getActivity());
        recyclerView.setAdapter(messageAdapter);

        final EditText editTextMessage = (EditText)view.findViewById(R.id.editTextMessage);
        ImageButton btnSend = (ImageButton)view.findViewById(R.id.btnSendMessage);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editTextMessage.getText().toString().isEmpty()){
                    JSONObject messageJson = new JSONObject();
                    try {
                        messageJson.put(VAR_USER_ID, "1");
                        messageJson.put(VAR_NAME, "Pablo");
                        messageJson.put(VAR_AVATAR,"https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xpf1/v/t1.0-1/p50x50/10959765_10205056274063138_8313510093762556398_n.jpg?oh=22926ba5d234c01aa18d8bc0637814b7&oe=55492466&__gda__=1431093888_bafbd3624f08d28d9e0ffc4c3d41af0e");
                        messageJson.put(VAR_MESSAGE, editTextMessage.getText().toString());
                        messageJson.put(VAR_IS_MINE, true);


                        MainFragment.socket.emit(EVENT_MESSAGE, messageJson);

                        MessageChatModel messageModel = new MessageChatModel();
                        messageModel.setUserId("1");
                        messageModel.setUserAvatar("https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xpf1/v/t1.0-1/p50x50/10959765_10205056274063138_8313510093762556398_n.jpg?oh=22926ba5d234c01aa18d8bc0637814b7&oe=55492466&__gda__=1431093888_bafbd3624f08d28d9e0ffc4c3d41af0e");
                        messageModel.setUserName("Pablo");
                        messageModel.setUserMessage(editTextMessage.getText().toString());
                        messageModel.setMine(true);

                        messageAdapter.addMessage(messageModel);

                        recyclerView.smoothScrollToPosition(messageAdapter.getItemCount() - 1);

                        editTextMessage.setText("");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        return view;
    }


}
