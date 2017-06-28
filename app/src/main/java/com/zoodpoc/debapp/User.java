package com.zoodpoc.debapp;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by pblead26 on 28-Jun-17.
 */



    @IgnoreExtraProperties
    public class User {

        public String sender_name;
        //public String is_media;

        // Default constructor required for calls to
        // DataSnapshot.getValue(User.class)
        public User() {
        }

        public User(String sender_name) {
            this.sender_name = sender_name;

        }
    }

