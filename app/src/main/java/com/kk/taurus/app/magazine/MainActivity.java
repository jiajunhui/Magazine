package com.kk.taurus.app.magazine;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.kk.taurus.app.magazine.bean.EventScrollTop;
import com.kk.taurus.app.magazine.fragment.JokeFragment;
import com.kk.taurus.app.magazine.fragment.WxArticleFragment;
import com.kk.taurus.app.magazine.holder.MainPageHolder;
import com.kk.taurus.baseframe.base.HolderData;
import com.kk.taurus.baseframe.ui.activity.ToolBarActivity;

import org.greenrobot.eventbus.EventBus;

public class MainActivity extends ToolBarActivity<HolderData,MainPageHolder> implements MainPageHolder.OnMainPageListener {

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
        setNavigationIcon(null);
        setCenterTitle(getString(R.string.app_name));
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

    @Override
    protected void onToolBarDoubleClick() {
        super.onToolBarDoubleClick();
        int type = 0;
        if(wxArticleFragment!=null && !wxArticleFragment.isHidden()){
            type = EventScrollTop.TYPE_WX;
        }
        if(jokeFragment!=null && !jokeFragment.isHidden()){
            type = EventScrollTop.TYPE_JOKE;
        }
        EventScrollTop event = new EventScrollTop();
        event.setType(type);
        EventBus.getDefault().post(event);
    }
}
