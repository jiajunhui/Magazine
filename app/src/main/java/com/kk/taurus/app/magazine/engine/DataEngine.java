package com.kk.taurus.app.magazine.engine;

import com.kk.taurus.app.magazine.bean.JokeReq;
import com.kk.taurus.app.magazine.bean.WxArticleReq;
import com.kk.taurus.app.magazine.config.Constant;
import com.kk.taurus.http_helper.XHTTP;
import com.kk.taurus.http_helper.bean.XRequest;
import com.kk.taurus.http_helper.callback.ReqCallBack;

import okhttp3.Call;

/**
 * Created by Taurus on 2017/2/4.
 */

public class DataEngine {

    public static Call getWxArticle(int pno, int ps, ReqCallBack reqCallBack){
        WxArticleReq wxArticleReq = new WxArticleReq();
        XRequest xRequest = new XRequest();
        xRequest.setUrl(Constant.url_wx);
        xRequest.addParams(wxArticleReq.key_pno,String.valueOf(pno))
                .addParams(wxArticleReq.key_ps,String.valueOf(ps))
                .addParams(wxArticleReq.key_dtype,"json")
                .addParams(wxArticleReq.key_app,Constant.key_wx_article);
        return XHTTP.newPost(xRequest,reqCallBack);
    }

    public static Call getJokes(int page, int pageSize, ReqCallBack reqCallBack){
        JokeReq jokeReq = new JokeReq();
        XRequest xRequest = new XRequest();
        xRequest.setUrl(Constant.url_joke);
        xRequest.addParams(JokeReq.key_page,String.valueOf(page))
                .addParams(JokeReq.key_page_size,String.valueOf(pageSize))
                .addParams(JokeReq.key_sort,JokeReq.SORT_TIME_PRE)
                .addParams(JokeReq.key_time,String.valueOf(System.currentTimeMillis()).substring(0,10))
                .addParams(jokeReq.key_app,Constant.key_joke);
        return XHTTP.newGet(xRequest,reqCallBack);
    }
}
