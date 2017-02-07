package com.kk.taurus.app.magazine.bean;

import com.kk.taurus.baseframe.base.HolderData;

import java.io.Serializable;

/**
 * Created by Taurus on 2017/2/7.
 */

public class WebPageData implements HolderData ,Serializable {
    private String url;
    private String title;
    private String source;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
