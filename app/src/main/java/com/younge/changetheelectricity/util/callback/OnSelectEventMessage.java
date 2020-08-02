package com.younge.changetheelectricity.util.callback;

public class OnSelectEventMessage {

    private int position;

    public OnSelectEventMessage(int position){
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
