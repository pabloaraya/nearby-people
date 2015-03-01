package com.pabloaraya.nearbypeople;

import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import com.pabloaraya.nearbypeople.fragment.IndexFragment;
import com.pabloaraya.nearbypeople.fragment.MainFragment;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MainActivity extends ActionBarActivity {

    /* Shared Preferences */
    public static SharedPreferences sharedPref;
    public static SharedPreferences.Editor sharedEditorPref;

    /* Var constant */
    public final static String VAR_FACEBOOK_ID = "facebook_id";
    public final static String VAR_FIRST_NAME = "first_name";
    public final static String VAR_LAST_NAME = "last_name";
    public final static String VAR_PROFILE_AVATAR = "profile_avatar";

    /* Aux constant */
    public final static String VAR_EMPTY = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Instance Shared Preference */
        sharedPref = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
        sharedEditorPref = sharedPref.edit();

        try{
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.pabloaraya.nearbypeople", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));

            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

        if (savedInstanceState == null) {
            if(!sharedPref.contains(VAR_FACEBOOK_ID)) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.root_container, MainFragment.newInstance())
                        .commit();

            }else{
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.root_container, IndexFragment.newInstance())
                        .commit();
            }
        }

        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
            return;
        }
        super.onBackPressed();
    }
}
