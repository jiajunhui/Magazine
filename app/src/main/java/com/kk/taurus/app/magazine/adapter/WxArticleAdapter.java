package com.kk.taurus.app.magazine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kk.taurus.app.magazine.R;
import com.kk.taurus.app.magazine.bean.WxArticleRsp;
import com.kk.taurus.app.magazine.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Taurus on 2017/2/7.
 */

public class WxArticleAdapter extends RecyclerView.Adapter<WxArticleAdapter.WxArticleItemHolder>{

    private Context mContext;
    private List<WxArticleRsp.WxArticle> mList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public WxArticleAdapter(Context context, List<WxArticleRsp.WxArticle> list){
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public WxArticleItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new WxArticleItemHolder(View.inflate(mContext,R.layout.item_wx_article,null));
    }

    @Override
    public void onBindViewHolder(final WxArticleItemHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null){
                    onItemClickListener.onItemClick(holder,position);
                }
            }
        });
        WxArticleRsp.WxArticle wxArticle = mList.get(position);
        Glide.with(mContext)
                .load(wxArticle.firstImg)
                .placeholder(R.mipmap.ic_default_img)
                .crossFade()
                .into(holder.ivImg);
        holder.tvTitle.setText(wxArticle.title);
        holder.tvSource.setText(wxArticle.source);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class WxArticleItemHolder extends RecyclerView.ViewHolder{

        ImageView ivImg;
        TextView tvTitle;
        TextView tvSource;

        public WxArticleItemHolder(View itemView) {
            super(itemView);
            ivImg = (ImageView) itemView.findViewById(R.id.iv_img);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvSource = (TextView) itemView.findViewById(R.id.tv_source);
        }
    }

}
