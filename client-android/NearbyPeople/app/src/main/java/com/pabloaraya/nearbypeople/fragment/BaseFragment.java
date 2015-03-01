package com.pabloaraya.nearbypeople.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.androidsocialnetworks.lib.SocialNetwork;
import com.androidsocialnetworks.lib.SocialNetworkManager;
import com.facebook.widget.LoginButton;
import com.pabloaraya.nearbypeople.R;

import java.util.Arrays;

/**
 * Created by pablo on 2/23/15.
 */
public abstract class BaseFragment extends Fragment implements SocialNetworkManager.OnInitializationCompleteListener, View.OnClickListener{
    private static final String TAG = BaseFragment.class.getSimpleName();

    public static final String SOCIAL_NETWORK_TAG = "ConfirmLogin.SOCIAL_NETWORK_TAG";
    private static final String PROGRESS_DIALOG_TAG = "ConfirmLogin.PROGRESS_DIALOG_TAG";
    protected SocialNetworkManager mSocialNetworkManager;
    protected boolean mSocialNetworkManagerInitialized = false;

    protected LoginButton mFacebookButton;

    protected abstract void onFacebookAction();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        mSocialNetworkManager = (SocialNetworkManager) getFragmentManager().findFragmentByTag(SOCIAL_NETWORK_TAG);

        if (mSocialNetworkManager == null) {
            mSocialNetworkManager = SocialNetworkManager.Builder.from(getActivity())
                    .facebook()
                    .build();
            getFragmentManager().beginTransaction().add(mSocialNetworkManager, SOCIAL_NETWORK_TAG).commit();

            mSocialNetworkManager.setOnInitializationCompleteListener(this);
        } else {
            // we need to setup buttons correctly, mSocialNetworkManager isn't null, so
            // we are sure that it was initialized
            mSocialNetworkManagerInitialized = true;
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mFacebookButton = (LoginButton) view.findViewById(R.id.facebook_button);
        mFacebookButton.setOnClickListener(this);
        mFacebookButton.setReadPermissions(Arrays.asList("public_profile", "email", "user_friends"));

        if (mSocialNetworkManagerInitialized) {
            onSocialNetworkManagerInitialized();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        onRequestCancel();
    }

    protected void showProgress(String text) {
        ProgressDialogFragment progressDialogFragment = ProgressDialogFragment.newInstance(text);
        progressDialogFragment.setTargetFragment(this, 0);
        progressDialogFragment.show(getFragmentManager(), PROGRESS_DIALOG_TAG);
    }

    protected void hideProgress() {
        Fragment fragment = getFragmentManager().findFragmentByTag(PROGRESS_DIALOG_TAG);

        if (fragment != null) {
            getFragmentManager().beginTransaction().remove(fragment).commit();
        }
    }

    protected void handleError(String text) {
        AlertDialogFragment.newInstance("Error", text).show(getFragmentManager(), null);
    }

    protected void handleSuccess(String title, String message) {
        AlertDialogFragment.newInstance(title, message).show(getFragmentManager(), null);
        Log.e("STATUS", "handleSuccess");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.facebook_button:
                onFacebookAction();
                break;
            default:
                throw new IllegalArgumentException("Can't find click handler for: " + v);
        }
    }

    @Override
    public void onSocialNetworkManagerInitialized() {

    }

    protected boolean checkIsLoginned(int socialNetworkID) {
        if (mSocialNetworkManager.getSocialNetwork(socialNetworkID).isConnected()) {
            return true;
        }

        AlertDialogFragment
                .newInstance("Request Login", "This action request login, please go to login demo first.")
                .show(getFragmentManager(), null);

        return false;
    }

    public void onRequestCancel() {
        Log.d(TAG, "BaseDemoFragment.onRequestCancel");

        for (SocialNetwork socialNetwork : mSocialNetworkManager.getInitializedSocialNetworks()) {
            socialNetwork.cancelAll();
        }
    }
}
