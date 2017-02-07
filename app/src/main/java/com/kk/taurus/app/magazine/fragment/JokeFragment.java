package com.kk.taurus.app.magazine.fragment;

import android.os.Bundle;

import com.kk.taurus.app.magazine.bean.JokeRsp;
import com.kk.taurus.app.magazine.engine.DataEngine;
import com.kk.taurus.app.magazine.holder.JokeFragHolder;
import com.kk.taurus.baseframe.bean.PageState;
import com.kk.taurus.baseframe.ui.fragment.StateFragment;
import com.kk.taurus.http_helper.callback.BeanCallBack;

import java.util.List;

/**
 * Created by Taurus on 2017/2/7.
 */

public class JokeFragment extends StateFragment<JokeRsp.JokeList,JokeFragHolder> implements JokeFragHolder.OnJokeListener {

    private final int DEFAULT_PAGE_SIZE = 20;

    private int page = 1;
    private int pageSize = DEFAULT_PAGE_SIZE;

    @Override
    public JokeFragHolder getContentViewHolder(Bundle savedInstanceState) {
        return new JokeFragHolder(mContext,this);
    }

    @Override
    public void loadState() {
        setPageState(PageState.loading());
        loadData();
    }

    private void loadData() {
        DataEngine.getJokes(page,pageSize, new BeanCallBack<JokeRsp>() {
            @Override
            public void onResponseBean(JokeRsp result) {
                if(result!=null && result.getError_code()==0){
                    setData(result.result);
                    setPageState(PageState.success());
                }
            }
        });
    }

    @Override
    public void onRefresh(List<JokeRsp.Joke> list) {
        page = 1;
        loadData();
    }

    @Override
    public void onLoadMore(List<JokeRsp.Joke> list) {
        page++;
        loadData();
    }
}
