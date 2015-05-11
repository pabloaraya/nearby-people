package com.pabloaraya.nearbypeople.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pabloaraya.nearbypeople.R;
import com.pabloaraya.nearbypeople.model.MessageChatModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pablo on 2/28/15.
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapterHolder>{

    private Context context;
    private List<MessageChatModel> messages;

    private final static int VIEW_LAYOUT_MINE_MESSAGE = 0;
    private final static int VIEW_LAYOUT_MESSAGE = 1;

    /* Typeface */
    private Typeface robotoLight;

    public MessageAdapter(Context context) {
        this.context    = context;
        this.messages   = new ArrayList<>();
        //robotoLight     = Typeface.createFromAsset(context.getAssets(), "Roboto-Light.ttf");
    }

    @Override
    public MessageAdapterHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view;
        if(i == VIEW_LAYOUT_MINE_MESSAGE){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_message_mine, null);
        } else{
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_message, null);
        }
        MessageAdapterHolder mh = new MessageAdapterHolder(view);
        return mh;
    }

    @Override
    public void onBindViewHolder(MessageAdapterHolder messageHolder, int i) {

        /* Message model */
        MessageChatModel message = messages.get(i);

        /* Set elements */
        messageHolder.textViewMessage.setText(message.getUserMessage());

        if(!message.isMine()) {
            messageHolder.textViewName.setText(message.getUserName());
            Picasso.with(context).load(message.getUserAvatar()).into(messageHolder.circleImageAvatar);
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void addMessage(MessageChatModel message){
        if(getItemCount() > 0) {
            /* Get last message */
            MessageChatModel lastMessage = messages.get(getItemCount() - 1);

            /* verify equals */
            if (message.getUserId().equals(lastMessage.getUserId())) {

                /* Concatenate the two messages */
                String concatenate = lastMessage.getUserMessage()
                        .concat("\n")
                        .concat(message.getUserMessage());

                messages.get(getItemCount() - 1).setUserMessage(concatenate);
            } else {
                messages.add(message);
            }
        }else{
            messages.add(message);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if(messages.get(position).isMine()){
            return VIEW_LAYOUT_MINE_MESSAGE;
        }else{
            return VIEW_LAYOUT_MESSAGE;
        }
    }
}
