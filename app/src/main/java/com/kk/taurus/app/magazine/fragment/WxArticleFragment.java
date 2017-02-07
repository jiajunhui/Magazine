package com.kk.taurus.app.magazine.fragment;

import android.content.Intent;
import android.os.Bundle;

import com.kk.taurus.app.magazine.activity.WebViewActivity;
import com.kk.taurus.app.magazine.bean.WebPageData;
import com.kk.taurus.app.magazine.bean.WxArticleRsp;
import com.kk.taurus.app.magazine.engine.DataEngine;
import com.kk.taurus.app.magazine.holder.WxArticleFragHolder;
import com.kk.taurus.baseframe.bean.PageState;
import com.kk.taurus.baseframe.ui.fragment.StateFragment;
import com.kk.taurus.http_helper.callback.BeanCallBack;

import java.util.List;

/**
 * Created by Taurus on 2017/2/4.
 */

public class WxArticleFragment extends StateFragment<WxArticleRsp.WxArticleList,WxArticleFragHolder> implements WxArticleFragHolder.OnWxArticleListener {

    private final int DEFAULT_PS = 20;
    private int pno = 1;
    private int ps = DEFAULT_PS;

    @Override
    public WxArticleFragHolder getContentViewHolder(Bundle savedInstanceState) {
        return new WxArticleFragHolder(mContext,this);
    }

    @Override
    public void loadState() {
        setPageState(PageState.loading());
        loadData();
    }

    private void loadData() {
        DataEngine.getWxArticle(pno, ps, new BeanCallBack<WxArticleRsp>() {
            @Override
            public void onResponseBean(WxArticleRsp result) {
                if(result!=null && result.getError_code()==0){
                    setData(result.result);
                    setPageState(PageState.success());
                }
            }
        });
    }

    @Override
    public void onIntentWebView(WebPageData webPageData) {
        Intent intent = new Intent(mContext, WebViewActivity.class);
        intent.putExtra(WebViewActivity.KEY_WEB_PAGE_DATA,webPageData);
        startActivity(intent);
    }

    @Override
    public void onRefresh(List<WxArticleRsp.WxArticle> list) {
        pno = 1;
        loadData();
    }

    @Override
    public void onLoadMore(List<WxArticleRsp.WxArticle> list) {
        pno++;
        loadData();
    }
}
