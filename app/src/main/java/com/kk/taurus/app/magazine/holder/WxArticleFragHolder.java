package com.kk.taurus.app.magazine.holder;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.kk.taurus.app.magazine.R;
import com.kk.taurus.app.magazine.adapter.WxArticleAdapter;
import com.kk.taurus.app.magazine.bean.WebPageData;
import com.kk.taurus.app.magazine.bean.WxArticleRsp;
import com.kk.taurus.app.magazine.listener.OnItemClickListener;
import com.kk.taurus.app.magazine.widget.divider.HorizontalDividerItemDecoration;
import com.kk.taurus.baseframe.base.ContentHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Taurus on 2017/2/4.
 */

public class WxArticleFragHolder extends ContentHolder<WxArticleRsp.WxArticleList> implements OnItemClickListener, XRecyclerView.LoadingListener {

    private XRecyclerView mRecycler;
    private WxArticleAdapter mAdapter;
    private List<WxArticleRsp.WxArticle> mList = new ArrayList<>();
    private OnWxArticleListener onWxArticleListener;
    private boolean reload;

    public WxArticleFragHolder(Context context,OnWxArticleListener onWxArticleListener) {
        super(context);
        this.onWxArticleListener = onWxArticleListener;
    }

    @Override
    public void onCreate() {
        setContentView(R.layout.fragment_wx_article);
        mRecycler = getViewById(R.id.recycler);
    }

    @Override
    public void onHolderCreated(Bundle savedInstanceState) {
        super.onHolderCreated(savedInstanceState);
        mRecycler.setLoadingListener(this);
        mRecycler.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        HorizontalDividerItemDecoration.Builder builder = new HorizontalDividerItemDecoration.Builder(mContext);
        builder.color(Color.parseColor("#bbbbbb"));
        builder.size(1);
        mRecycler.addItemDecoration(builder.build());
        mAdapter = new WxArticleAdapter(mContext,mList);
        mAdapter.setOnItemClickListener(this);
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void refreshView() {
        super.refreshView();
        if(reload){
            mRecycler.refreshComplete();
        }else{
            mRecycler.loadMoreComplete();
        }
        if(reload){
            mList.clear();
        }
        mList.addAll(mData.list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder holder, int position) {
        WxArticleRsp.WxArticle wxArticle = mList.get(position);
        WebPageData webPageData = new WebPageData();
        webPageData.setUrl(wxArticle.url);
        webPageData.setTitle(wxArticle.title);
        webPageData.setSource(wxArticle.source);
        if(onWxArticleListener!=null){
            onWxArticleListener.onIntentWebView(webPageData);
        }
    }

    @Override
    public void onRefresh() {
        reload = true;
        if(onWxArticleListener!=null){
            onWxArticleListener.onRefresh(mList);
        }
    }

    @Override
    public void onLoadMore() {
        reload = false;
        if(onWxArticleListener!=null){
            onWxArticleListener.onLoadMore(mList);
        }
    }

    public interface OnWxArticleListener{
        void onIntentWebView(WebPageData webPageData);
        void onRefresh(List<WxArticleRsp.WxArticle> list);
        void onLoadMore(List<WxArticleRsp.WxArticle> list);
    }
}
