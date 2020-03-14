package com.example.newdemo.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.newdemo.R;
import com.example.newdemo.home.bean.Bean.ResultBeanData;
import com.example.newdemo.utils.Constants;

import java.util.List;

public class SeckillRecyclerViewAdapter extends RecyclerView.Adapter <SeckillRecyclerViewAdapter.ViewHolder>{


    private final List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> list;
    private final Context mContext;

    public SeckillRecyclerViewAdapter(Context mContext, List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> list) {

        this.list=list;
        this.mContext=mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=View.inflate(mContext, R.layout.item_seckill,null);
        return new ViewHolder(itemView);
    }

    @Override



    public void onBindViewHolder(ViewHolder holder, int position) {

        //根据配置得到对应的数据
        ResultBeanData.ResultBean.SeckillInfoBean.ListBean listBean = list.get(position);

        //得到数据
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+listBean.getFigure()).into(holder.iv_figure);
        holder.tv_cover_price.setText(listBean.getCover_price());
        holder.tv_origin_price.setText(listBean.getOrigin_price());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends  RecyclerView.ViewHolder{

        private ImageView iv_figure;
        private TextView tv_cover_price;
        private TextView tv_origin_price;


        public ViewHolder(View itemView) {
            super(itemView);
            iv_figure=(ImageView) itemView.findViewById(R.id.iv_figure);
            tv_cover_price= (TextView) itemView.findViewById(R.id.tv_cover_price);
            tv_origin_price= (TextView) itemView.findViewById(R.id.tv_origin_price);


            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                   // Toast.makeText(mContext,"秒杀"+getLayoutPosition(),Toast.LENGTH_SHORT).show();
                    if(onSeckillRecyclerView!=null){
                        onSeckillRecyclerView.onItemClick(getLayoutPosition());

                    }
                }
            });
        }
    }

    public interface OnSeckillRecyclerView{

        //当某条被点击时回调
        public void onItemClick(int position);

    }

    private OnSeckillRecyclerView onSeckillRecyclerView;

    //设置item的监听
    public void setOnSeckillRecyclerView(OnSeckillRecyclerView onSeckillRecyclerView){
        this.onSeckillRecyclerView=onSeckillRecyclerView;
    }
}
