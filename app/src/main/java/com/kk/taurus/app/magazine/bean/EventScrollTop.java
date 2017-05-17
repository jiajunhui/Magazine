package com.kk.taurus.app.magazine.bean;

import java.io.Serializable;

/**
 * Created by Taurus on 2017/5/17.
 */

public class EventScrollTop implements Serializable{
    public static final int TYPE_WX = 1;
    public static final int TYPE_JOKE = 2;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
