package com.kk.taurus.app.magazine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kk.taurus.app.magazine.R;
import com.kk.taurus.app.magazine.bean.JokeRsp;
import com.kk.taurus.app.magazine.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Taurus on 2017/2/7.
 */

public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.JokeItemHolder>{

    private Context mContext;
    private List<JokeRsp.Joke> mList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public JokeAdapter(Context context, List<JokeRsp.Joke> list){
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public JokeItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new JokeItemHolder(View.inflate(mContext,R.layout.item_joke,null));
    }

    @Override
    public void onBindViewHolder(JokeItemHolder holder, int position) {
        JokeRsp.Joke joke = mList.get(position);
        holder.tvContent.setText(Html.fromHtml(joke.content));
        holder.tvTime.setText(joke.updatetime);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class JokeItemHolder extends RecyclerView.ViewHolder{

        TextView tvContent;
        TextView tvTime;

        public JokeItemHolder(View itemView) {
            super(itemView);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
        }
    }

}
