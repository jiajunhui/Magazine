package com.kk.taurus.app.magazine.bean;

import com.kk.taurus.app.magazine.base.BaseRsp;
import com.kk.taurus.baseframe.base.HolderData;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Taurus on 2017/2/4.
 */

public class WxArticleRsp extends BaseRsp<WxArticleRsp.WxArticleList> {

    public static class WxArticleList implements HolderData{
        public int totalPage;
        public int ps;
        public int pno;
        public List<WxArticle> list;
    }

    public static class WxArticle implements Serializable{
        public String id;
        public String title;
        public String source;
        public String firstImg;
        public String mark;
        public String url;
    }

}
