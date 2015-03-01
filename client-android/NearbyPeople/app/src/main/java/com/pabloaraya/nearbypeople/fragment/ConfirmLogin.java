package com.pabloaraya.nearbypeople.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.View;

import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.model.GraphUser;
import com.pabloaraya.nearbypeople.MainActivity;

/**
 * Created by pablo on 2/23/15.
 */
public abstract class ConfirmLogin extends BaseFragment implements View.OnClickListener {

    @Override
    public void onSocialNetworkManagerInitialized() {
        Log.e("WHERE", "INITIALIZED");
        Log.e("isConnected", String.valueOf(mSocialNetworkManager.getFacebookSocialNetwork().isConnected()));
        if (mSocialNetworkManager.getFacebookSocialNetwork().isConnected()) {
            final Session session = Session.getActiveSession();

            Request.executeMeRequestAsync(session, new Request.GraphUserCallback(){

                @Override
                public void onCompleted(GraphUser graphUser, Response response) {
                    MainActivity.sharedEditorPref.putString(
                            MainActivity.VAR_FACEBOOK_ID, graphUser.getId());
                    MainActivity.sharedEditorPref.putString(
                            MainActivity.VAR_FIRST_NAME, graphUser.getFirstName());
                    MainActivity.sharedEditorPref.putString(
                            MainActivity.VAR_LAST_NAME, graphUser.getLastName());
                    MainActivity.sharedEditorPref.putString(
                            MainActivity.VAR_PROFILE_AVATAR,
                            "http://graph.facebook.com/"+graphUser.getId()+"/picture?type=large");
                    MainActivity.sharedEditorPref.commit();

                    Log.e("FIRST NAME", MainActivity.sharedPref.getString(MainActivity.VAR_FIRST_NAME, ""));
                    /*new Request(
                            session,
                            "/me/friendlists",
                            null,
                            HttpMethod.GET,
                            new Request.Callback() {
                                @Override
                                public void onCompleted(Response response) {
                                    Log.e("FRIENDS", response.toString());
                                }
                            }
                    ).executeAsync();*/
                }
            });
        }
    }
}