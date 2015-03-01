package com.pabloaraya.nearbypeople.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidsocialnetworks.lib.SocialNetwork;
import com.androidsocialnetworks.lib.listener.OnLoginCompleteListener;
import com.pabloaraya.nearbypeople.R;

/**
 * Created by pablo on 2/23/15.
 */
public class IndexFragment extends ConfirmLogin implements OnLoginCompleteListener {

    public static IndexFragment newInstance() {
        return new IndexFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login,container,false);
    }

    @Override
    protected void onFacebookAction() {
        mSocialNetworkManager.getFacebookSocialNetwork().requestLogin();
        showProgress("Sign in");
    }

    @Override
    public void onLoginSuccess(int i) {
// let's reset buttons, we need to disable buttons
        onSocialNetworkManagerInitialized();

        hideProgress();

        handleSuccess("","");
    }

    @Override
    public void onError(int socialNetworkID, String requestID, String errorMessage, Object data) {
        hideProgress();
        handleError(errorMessage);
    }

    @Override
    public void onSocialNetworkManagerInitialized() {
        super.onSocialNetworkManagerInitialized();

        for (SocialNetwork socialNetwork : mSocialNetworkManager.getInitializedSocialNetworks()) {
            socialNetwork.setOnLoginCompleteListener(this);
        }
    }
}
