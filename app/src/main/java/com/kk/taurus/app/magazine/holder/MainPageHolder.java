package com.kk.taurus.app.magazine.holder;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.kk.taurus.app.magazine.R;
import com.kk.taurus.baseframe.base.ContentHolder;
import com.kk.taurus.baseframe.base.HolderData;

/**
 * Created by Taurus on 2017/2/4.
 */

public class MainPageHolder extends ContentHolder<HolderData> {

    private RadioGroup mRadioGroup;
    private OnMainPageListener onMainPageListener;

    public MainPageHolder(Context context,OnMainPageListener onMainPageListener) {
        super(context);
        this.onMainPageListener = onMainPageListener;
    }

    @Override
    public void onCreate() {
        setContentView(R.layout.activity_main);
        mRadioGroup = getViewById(R.id.radioGroup);
    }

    public View getContainer(){
        return mRootView.findViewById(R.id.frag_container);
    }

    @Override
    public void onHolderCreated(Bundle savedInstanceState) {
        super.onHolderCreated(savedInstanceState);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_wx_article:
                        if(onMainPageListener!=null){
                            onMainPageListener.onSwitchWxArticle();
                        }
                        break;

                    case R.id.rb_joke:
                        if(onMainPageListener!=null){
                            onMainPageListener.onSwitchJoke();
                        }
                        break;
                }
            }
        });
    }

    public interface OnMainPageListener{
        void onSwitchWxArticle();
        void onSwitchJoke();
    }

}
