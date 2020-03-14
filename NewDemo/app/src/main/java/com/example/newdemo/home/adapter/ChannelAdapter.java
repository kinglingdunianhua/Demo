package com.example.newdemo.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.newdemo.R;
import com.example.newdemo.home.bean.Bean.ResultBeanData;
import com.example.newdemo.utils.Constants;

import java.util.List;

public class ChannelAdapter extends BaseAdapter {


    private List<ResultBeanData.ResultBean.ChannelInfoBean> datas;
    private final Context mContext;


    public ChannelAdapter(Context mContext, List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {
        this.mContext=mContext;
        this.datas=channel_info;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(convertview==null){
            convertview= View.inflate(mContext,R.layout.item_channel,null);
            viewHolder=new ViewHolder();
            viewHolder.iv_icon=(ImageView) convertview.findViewById(R.id.iv_channel);
            viewHolder.tv_title=(TextView) convertview.findViewById(R.id.tv_channel);
            convertview.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder)convertview.getTag();
        }


        //根据位置得到数据
        ResultBeanData.ResultBean.ChannelInfoBean channelInfoBean=datas.get(position);
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+channelInfoBean.getImage()).into(viewHolder.iv_icon);
        viewHolder.tv_title.setText(channelInfoBean.getChannel_name());
        return convertview;
    }
    static class ViewHolder{
        ImageView iv_icon;
        TextView tv_title;
    }
}
