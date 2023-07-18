package com.example.casestudy_movieproject.model.enums;

public enum EStatus {
    COMPLETE(1),
    SHOWING(2),
    COMING_SOON(3),
    CANCEL(4);

    private int index;

    EStatus(int index) {
        this.index = index;
    }
}
