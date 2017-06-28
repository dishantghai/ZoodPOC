package com.zoodpoc.debapp;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by pblead26 on 28-Jun-17.
 */



    @IgnoreExtraProperties
    public class User {

    public String payload;

    public User(String payload, String sender_name) {
        this.payload = payload;
        this.sender_name = sender_name;
    }

    public String sender_name;
    public Boolean is_for;
    public Boolean is_media;
    public String media_type;
    public String media_url;
    public String msg_id;
    public String send_time;
    public String sender_uid;

    public User(Boolean is_for, Boolean is_media, String media_type, String media_url, String msg_id, String payload, String sender_name, String send_time, String sender_uid) {

        this.is_for = is_for;
        this.is_media = is_media;
        this.media_type = media_type;
        this.media_url = media_url;
        this.msg_id = msg_id;
        this.payload = payload;
        this.sender_name = sender_name;
        this.send_time = send_time;
        this.sender_uid = sender_uid;
    }

    public Boolean getIs_for() {
        return is_for;
    }

    public void setIs_for(Boolean is_for) {
        this.is_for = is_for;
    }

    public Boolean getIs_media() {
        return is_media;
    }

    public void setIs_media(Boolean is_media) {
        this.is_media = is_media;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public String getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public String getTimestamp() {
        return send_time;
    }

    public void setTimestamp(String send_time) {
        this.send_time = send_time;
    }

    public String getSender_uid() {
        return sender_uid;
    }

    public void setSender_uid(String sender_uid) {
        this.sender_uid = sender_uid;
    }



    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }


    public String getSender_name() {
        return sender_name;
    }

    public void setSender_name(String sender_name) {
        this.sender_name = sender_name;
    }

        //public String is_media;

        // Default constructor required for calls to
        // DataSnapshot.getValue(User.class)
        public User() {
        }

        public User(String sender_name) {
            this.sender_name = sender_name;

        }


    }

