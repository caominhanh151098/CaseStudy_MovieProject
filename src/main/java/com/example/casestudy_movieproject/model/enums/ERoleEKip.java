package com.example.casestudy_movieproject.model.enums;

public enum ERoleEKip {
    ACTOR(1),
    DIRECTOR(2);
    private int index;

    ERoleEKip(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
