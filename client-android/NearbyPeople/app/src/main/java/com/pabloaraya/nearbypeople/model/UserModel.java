package com.pabloaraya.nearbypeople.model;

/**
 * Created by pablo on 9/22/14.
 */
public class UserModel {

    private String user_id;
    private String facebook_id;
    private String first_name;
    private String last_name;
    private String full_name;
    private String nickname;
    private String email;
    private String gcm_id;
    private String gender;

    public UserModel() {
        // TODO Auto-generated constructor stub
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getFullName() {
        return full_name;
    }

    public void setFullName(String full_name) {
        this.full_name = full_name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGcmId() {
        return gcm_id;
    }

    public void setGcmId(String gcm_id) {
        this.gcm_id = gcm_id;
    }

    public String getFacebookId() {
        return facebook_id;
    }

    public void setFacebookId(String facebook_id) {
        this.facebook_id = facebook_id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

