package com.kk.taurus.app.magazine.bean;

import com.kk.taurus.baseframe.base.HolderData;
import com.kk.taurus.http_helper.bean.AbsResponse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Taurus on 2017/2/4.
 */

public class WxArticleRsp extends AbsResponse<WxArticleRsp.WxArticleData> {

    public static class WxArticleData implements Serializable{
        public int error_code;
        public String reason;
        public WxArticleList result;
    }

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
