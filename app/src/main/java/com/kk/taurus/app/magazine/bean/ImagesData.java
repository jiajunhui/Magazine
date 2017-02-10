package com.kk.taurus.app.magazine.bean;

import com.kk.taurus.baseframe.base.HolderData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Taurus on 2017/2/10.
 */

public class ImagesData implements HolderData , Serializable{

    private List<String> images = new ArrayList<>();
    private String currUrl;

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getCurrUrl() {
        return currUrl;
    }

    public void setCurrUrl(String currUrl) {
        this.currUrl = currUrl;
    }
}
