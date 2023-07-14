package com.example.casestudy_movieproject.model.enums;

public enum EQuality {
    HD(1),
    HD_VIET_SUB(2),
    CAM(3);

    private int index;

    EQuality(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
