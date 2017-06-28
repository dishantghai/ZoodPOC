package com.zoodpoc.debapp;

/**
 * Created by pblead26 on 28-Jun-17.
 */

public class ChatMessage {
    public boolean rightSide;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String message;

    public String getSender_name() {
        return sender_name;
    }

    public void setSender_name(String sender_name) {
        this.sender_name = sender_name;
    }

    public String sender_name;


    public ChatMessage(boolean rightSide, String message) {
        super();
        this.rightSide = rightSide;
        this.message = message;
    }

    public ChatMessage(boolean rightSide, String message, String sender_name) {
        super();
        this.rightSide = rightSide;
        this.message = message;
        this.sender_name = sender_name;
    }

    public ChatMessage(String message) {
        super();
        this.message = message;
    }
}