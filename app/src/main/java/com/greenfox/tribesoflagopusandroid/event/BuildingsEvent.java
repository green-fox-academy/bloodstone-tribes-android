package com.greenfox.tribesoflagopusandroid.event;

public class BuildingsEvent {

    String message;

    public BuildingsEvent() {
    }

    public BuildingsEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
