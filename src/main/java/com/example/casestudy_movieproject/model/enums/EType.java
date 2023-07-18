package com.example.casestudy_movieproject.model.enums;

public enum EType {
    MOVIE(1),
    SERIES(2);

    private int index;

    EType(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
