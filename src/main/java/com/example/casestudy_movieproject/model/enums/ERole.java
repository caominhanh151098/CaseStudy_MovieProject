package com.example.casestudy_movieproject.model.enums;

public enum ERole {
    ROLE_ADMIN(1),
    ROLE_USER(2);

    private int index;

    ERole(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
