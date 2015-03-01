package com.pabloaraya.nearbypeople.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pabloaraya.nearbypeople.R;

/**
 * Created by pablo on 2/28/15.
 */
public class MessageAdapterHolder extends RecyclerView.ViewHolder{

    protected TextView textViewMessage;
    protected TextView textViewName;

    public MessageAdapterHolder(View view) {
        super(view);
        this.textViewMessage = (TextView)view.findViewById(R.id.textViewMessage);
        this.textViewName   = (TextView)view.findViewById(R.id.textViewName);
    }
}
