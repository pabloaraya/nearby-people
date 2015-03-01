package com.pabloaraya.nearbypeople.model;

/**
 * Created by pablo on 12/6/14.
 */
public class MessageChatModel {

    private String user_id;
    private String facebook_id;
    private String user_name;
    private String user_avatar;
    private String user_message;
    private double user_latitude;
    private double user_longitude;
    private boolean is_mine;

    public MessageChatModel(){

    }

    public String getUserId() {
        return user_id;
    }

    public void setUserId(String user_id) {
        this.user_id = user_id;
    }

    public String getUserName() {
        return user_name;
    }

    public void setUserName(String user_name) {
        this.user_name = user_name;
    }

    public String getUserAvatar() {
        return user_avatar;
    }

    public void setUserAvatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }

    public String getUserMessage() {
        return user_message;
    }

    public void setUserMessage(String user_message) {
        this.user_message = user_message;
    }

    public boolean isMine(){
        return is_mine;
    }

    public void setMine(boolean is_mine){
        this.is_mine = is_mine;
    }

    public double getUserLatitude() {
        return user_latitude;
    }

    public void setUserLatitude(double user_latitude) {
        this.user_latitude = user_latitude;
    }

    public double getUserLongitude() {
        return user_longitude;
    }

    public void setUserLongitude(double user_longitude) {
        this.user_longitude = user_longitude;
    }

}
