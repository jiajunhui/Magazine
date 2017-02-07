package com.kk.taurus.app.magazine;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.kk.taurus.app.magazine.fragment.JokeFragment;
import com.kk.taurus.app.magazine.fragment.WxArticleFragment;
import com.kk.taurus.app.magazine.holder.MainPageHolder;
import com.kk.taurus.baseframe.base.HolderData;
import com.kk.taurus.baseframe.ui.activity.TopBarActivity;

public class MainActivity extends TopBarActivity<HolderData,MainPageHolder> implements MainPageHolder.OnMainPageListener {

    private WxArticleFragment wxArticleFragment;
    private JokeFragment jokeFragment;

    @Override
    public MainPageHolder getContentViewHolder(Bundle savedInstanceState) {
        return new MainPageHolder(this,this);
    }

    @Override
    public void loadState() {
        super.loadState();
        onSwitchWxArticle();
    }

    @Override
    public void initData() {
        super.initData();
        setStatusBarColor(getResources().getColor(com.kk.taurus.baseframe.R.color.top_bar_background));
        getTopBarNavigationIcon().setVisibility(View.GONE);
        setTopBarTitle(getString(R.string.app_name));
    }

    @Override
    public void onSwitchWxArticle() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if(jokeFragment!=null){
            ft.hide(jokeFragment);
        }
        if(wxArticleFragment==null){
            wxArticleFragment = new WxArticleFragment();
            ft.add(mContentHolder.getContainer().getId(),wxArticleFragment);
        }else{
            ft.show(wxArticleFragment);
        }
        ft.commitAllowingStateLoss();
    }

    @Override
    public void onSwitchJoke() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if(wxArticleFragment!=null){
            ft.hide(wxArticleFragment);
        }
        if(jokeFragment==null){
            jokeFragment = new JokeFragment();
            ft.add(mContentHolder.getContainer().getId(),jokeFragment);
        }else{
            ft.show(jokeFragment);
        }
        ft.commitAllowingStateLoss();
    }
}
