package com.pabloaraya.nearbypeople.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

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
    final private static String VAR_MESSAGE = "message";
    final private static String VAR_NAME = "name";
    final private static String VAR_EMPTY = "unknown";


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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_nearby_people, container, false);

        /* Initialize recycler view */
        recyclerView = (RecyclerView)view.findViewById(R.id.listViewChat);
        //recyclerView.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(getActivity());
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
                        messageJson.put(VAR_MESSAGE, editTextMessage.getText().toString());
                        //messageJson.put(VAR_NAME, userPreference.getString(VAR_NAME, VAR_EMPTY));

                        //socket.emit(EVENT_MESSAGE, messageJson);

                        MessageChatModel messageModel = new MessageChatModel();
                        messageModel.setUserId("1");
                        messageModel.setUserName("Pablo");
                        messageModel.setUserMessage(editTextMessage.getText().toString());

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
