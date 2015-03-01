package com.pabloaraya.nearbypeople.model;

/**
 * Created by pablo on 12/12/14.
 */
public class PrivateRoomModel {

    private String user_id;
    private String facebook_id;
    private String user_name;
    private String user_avatar;
    private String user_last_message;
    private boolean is_read;

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

    public String getUserLastMessage() {
        return user_last_message;
    }

    public void setUserLastMessage(String user_last_message) {
        this.user_last_message = user_last_message;
    }

    public void setRead(boolean is_read){
        this.is_read = is_read;
    }
    public boolean isRead(){
        return is_read;
    }
}
