package com.kk.taurus.app.magazine.holder;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.kk.taurus.app.magazine.R;
import com.kk.taurus.app.magazine.adapter.JokeAdapter;
import com.kk.taurus.app.magazine.bean.JokeRsp;
import com.kk.taurus.app.magazine.widget.divider.HorizontalDividerItemDecoration;
import com.kk.taurus.baseframe.base.ContentHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Taurus on 2017/2/7.
 */

public class JokeFragHolder extends ContentHolder<JokeRsp.JokeList> implements XRecyclerView.LoadingListener {

    private XRecyclerView mRecycler;
    private JokeAdapter mAdapter;
    private List<JokeRsp.Joke> mList = new ArrayList<>();
    private OnJokeListener onJokeListener;
    private boolean reload;

    public JokeFragHolder(Context context,OnJokeListener onJokeListener) {
        super(context);
        this.onJokeListener = onJokeListener;
    }

    @Override
    public void onCreate() {
        setContentView(R.layout.fragment_joke);
        mRecycler = getViewById(R.id.recycler);
    }

    @Override
    public void onHolderCreated(Bundle savedInstanceState) {
        super.onHolderCreated(savedInstanceState);
        mRecycler.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        HorizontalDividerItemDecoration.Builder builder = new HorizontalDividerItemDecoration.Builder(mContext);
        builder.color(Color.parseColor("#bbbbbb"));
        builder.size(1);
        mRecycler.addItemDecoration(builder.build());
        mRecycler.setLoadingListener(this);
        mAdapter = new JokeAdapter(mContext,mList);
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
        mList.addAll(mData.data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        reload = true;
        if(onJokeListener!=null){
            onJokeListener.onRefresh(mList);
        }
    }

    @Override
    public void onLoadMore() {
        reload = false;
        if(onJokeListener!=null){
            onJokeListener.onLoadMore(mList);
        }
    }

    public interface OnJokeListener{
        void onRefresh(List<JokeRsp.Joke> list);
        void onLoadMore(List<JokeRsp.Joke> list);
    }
}
