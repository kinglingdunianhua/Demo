package com.example.newdemo.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.newdemo.R;
import com.example.newdemo.app.GoodsInfoActivity;
import com.example.newdemo.home.bean.Bean.GoodsBean;
import com.example.newdemo.home.bean.Bean.ResultBeanData;
import com.example.newdemo.utils.Constants;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.listener.OnLoadImageListener;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeFragmentAdapter extends RecyclerView.Adapter {

    /** * 五种类型 */
    /** * 横幅广告 */
    public static final int BANNER = 0;
    /** * 频道 */
    public static final int CHANNEL = 1;
    /** * 活动 */
    public static final int ACT = 2;
    /** * 秒杀 */
    public static final int SECKILL = 3;
    /** * 推荐 */
    public static final int RECOMMEND = 4;
    /** * 热卖 */
    public static final int HOT = 5;
    private static final String GOODS_BEAN ="goodsBean" ;
    private final Context mContext;
    private final ResultBeanData.ResultBean resultBean;

    //初始化布局
    private final LayoutInflater mlayoutInflater;
    //当前类型
    private  int currentType=0;
    public HomeFragmentAdapter(Context mContext, ResultBeanData.ResultBean resultBean) {
        this.mContext=mContext;
        this.resultBean=resultBean;
        mlayoutInflater=LayoutInflater.from(mContext);

    }
    //相当于getview，创建viewHolder，实例化banner_viewpager
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==BANNER){
            return new BannerViewHolder(mContext,mlayoutInflater.inflate(R.layout.banner_viewpager,null));
        }else if (viewType==CHANNEL){
            return new ChannelViewHolder(mContext,mlayoutInflater.inflate(R.layout.channel_item,null));
        }else if(viewType==ACT){
            return new ActViewHolder(mContext,mlayoutInflater.inflate(R.layout.act_item,null));
        }else if(viewType==SECKILL){
            return new SeckillViewHolder(mContext,mlayoutInflater.inflate(R.layout.seckill_item,null));
        }else if (viewType == RECOMMEND) {
            return new RecommendViewHolder(mContext,mlayoutInflater.inflate(R.layout.recommend_item, null));
        }else if (viewType == HOT) {
            return new HotViewHolder(mContext,mlayoutInflater.inflate(R.layout.hot_item, null));
        }

        return null;
    }


    //相当于getview方法中的绑定数据模板
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(getItemViewType(position)==BANNER){

            BannerViewHolder bannerViewHolder=(BannerViewHolder)holder;
            bannerViewHolder.setData(resultBean.getBanner_info());
        }else if(getItemViewType(position)==CHANNEL){

            ChannelViewHolder channelViewHolder=(ChannelViewHolder) holder;
            channelViewHolder.setData(resultBean.getChannel_info());
        }else if(getItemViewType(position)==ACT){

            ActViewHolder actViewHolder=(ActViewHolder) holder;
            actViewHolder.setData(resultBean.getAct_info());
        }else if(getItemViewType(position)==SECKILL){

            SeckillViewHolder seckillViewHolder=(SeckillViewHolder)holder;
            seckillViewHolder.setData(resultBean.getSeckill_info());

        } else if (getItemViewType(position) == RECOMMEND) {
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
            recommendViewHolder.setData(resultBean.getRecommend_info());
        }else if (getItemViewType(position) == HOT) {
            HotViewHolder hotViewHolder = (HotViewHolder) holder;
            hotViewHolder.setData(resultBean.getHot_info());
        }
    }


    class HotViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_more_hot;
        private GridView gv_hot;
        private final Context mContext;
        private HotGridViewAdapter adapter;

        public HotViewHolder(final Context mContext, View itemView ) {
            super(itemView);

            tv_more_hot = (TextView) itemView.findViewById(R.id.tv_more_hot);
            gv_hot = (GridView) itemView.findViewById(R.id.gv_hot);
            this.mContext = mContext;


        }

        public void setData(final List<ResultBeanData.ResultBean.HotInfoBean> hot_info) {
            adapter=new HotGridViewAdapter(mContext,hot_info);
            gv_hot.setAdapter(adapter);
            gv_hot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Toast.makeText(mContext,"position"+position,Toast.LENGTH_SHORT).show();

                    //热卖商品信息类
                    ResultBeanData.ResultBean.HotInfoBean hotInfoBean=hot_info.get(position);

                    //商品信息类
                    GoodsBean goodsBean=new GoodsBean();
                    goodsBean.setCover_price(hotInfoBean.getCover_price());
                    goodsBean.setFigure(hotInfoBean.getFigure());
                    goodsBean.setName(hotInfoBean.getName());
                    goodsBean.setProduct_id(hotInfoBean.getProduct_id());
                    startGoodsInfoActivity(goodsBean);
                }
            });


        }
    }
    class RecommendViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_more_recommend;
        private GridView gv_recommend;
        private Context mContext;
        private RecommendGridViewAdapter adapter;

        public RecommendViewHolder(final Context mContext, View itemView) {
            super(itemView);
            tv_more_recommend = (TextView) itemView.findViewById(R.id.tv_more_recommend);
            gv_recommend = (GridView) itemView.findViewById(R.id.gv_recommend);
            this.mContext = mContext;


        }

        public void setData(final List<ResultBeanData.ResultBean.RecommendInfoBean> recommend_info) {

            //设置数据和适配器

            adapter = new RecommendGridViewAdapter(mContext, recommend_info);
            gv_recommend.setAdapter(adapter);

            gv_recommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    Toast.makeText(mContext,"position"+position,Toast.LENGTH_SHORT).show();
                    ResultBeanData.ResultBean.RecommendInfoBean recommendInfoBean=recommend_info.get(position);
                    GoodsBean goodsBean=new GoodsBean();
                    goodsBean.setCover_price(recommendInfoBean.getCover_price());
                    goodsBean.setFigure(recommendInfoBean.getFigure());
                    goodsBean.setName(recommendInfoBean.getName());
                    goodsBean.setProduct_id(recommendInfoBean.getProduct_id());
                    startGoodsInfoActivity(goodsBean);
                }
            });



        }
    }
    class BannerViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        private View itemView;
        private Banner banner;

        public BannerViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext=mContext;
            this.banner=(Banner)itemView.findViewById(R.id.banner);

        }

        public void setData(List<ResultBeanData.ResultBean.BannerInfoBean> banner_info) {
            //设置Banner的数据
            List<String> imagesUrl = new ArrayList<>();

            //得到图片集合
            for(int i=0;i<banner_info.size();i++){
                String imageUrl=banner_info.get(i).getImage();
                imagesUrl.add(imageUrl);
            }
            banner.setImages(imagesUrl, new OnLoadImageListener() {
                @Override
                public void OnLoadImage(ImageView view, Object url) {

                    //联网请求图片
                    Glide.with(mContext).load(Constants.BASE_URL_IMAGE+url).into(view);
                }
            });
            banner.setOnBannerClickListener(new OnBannerClickListener(){


                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext,"position"+position,Toast.LENGTH_SHORT).show();


                  //  startGoodsInfoActivity(goodsBean);
                }
            });


        }
    }

    //启动详细页面
    private void startGoodsInfoActivity(GoodsBean goodsBean) {

        Intent intent = new Intent(mContext, GoodsInfoActivity.class);
        intent.putExtra(GOODS_BEAN,goodsBean);
        mContext.startActivity(intent);
    }

    class SeckillViewHolder extends RecyclerView.ViewHolder{

        private final Context mContext;
        private TextView tv_time_secKill;
        private TextView tv_more_seckill;
        private RecyclerView re_seckill;
        private SeckillRecyclerViewAdapter adapter;

        //相差多少毫秒
        private long dt=0;

        private Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                dt=dt-1000;
                SimpleDateFormat formatter=new SimpleDateFormat("HH:mm:ss");
                String time=formatter.format(new Date(dt));
                tv_time_secKill.setText(time);

                handler.removeMessages(0);
                handler.sendEmptyMessageDelayed(0,1000);

                if(dt<=0){
                    handler.removeCallbacksAndMessages(null);
                }
            }
        };

        public SeckillViewHolder(Context mContext, View iitemView) {
            super(iitemView);

            tv_time_secKill=(TextView) itemView.findViewById(R.id.tv_time_seckill);
            tv_more_seckill=(TextView) itemView.findViewById(R.id.tv_more_seckill);
            re_seckill= (RecyclerView) itemView.findViewById(R.id.rv_seckill);

            this.mContext=mContext;
        }

        public void setData(final ResultBeanData.ResultBean.SeckillInfoBean seckill_info) {

           //获得数据，设置数据
            //设置数据
            adapter=new SeckillRecyclerViewAdapter(mContext,seckill_info.getList());

            re_seckill.setAdapter(adapter);

            //设置布局管理器
            re_seckill.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));

            //设置item的点击事件,真正的点击事件在适配器中
            adapter.setOnSeckillRecyclerView(new SeckillRecyclerViewAdapter.OnSeckillRecyclerView() {
                @Override
                public void onItemClick(int position) {
                    Toast.makeText(mContext,"秒杀位置"+position,Toast.LENGTH_SHORT).show();

                    ResultBeanData.ResultBean.SeckillInfoBean.ListBean listBean=seckill_info.getList().get(position);
                    GoodsBean goodsBean=new GoodsBean();
                    goodsBean.setCover_price(listBean.getCover_price());
                    goodsBean.setFigure(listBean.getFigure());
                    goodsBean.setName(listBean.getName());
                    goodsBean.setProduct_id(listBean.getProduct_id());
                    startGoodsInfoActivity(goodsBean);
                }
            });

            //秒杀倒计时
            dt= Integer.valueOf( seckill_info.getEnd_time())-Integer.valueOf( seckill_info.getStart_time());

            SimpleDateFormat formatter=new SimpleDateFormat("HH:mm:ss");
            String time=formatter.format(new Date(dt));
            tv_time_secKill.setText(time);

            handler.sendEmptyMessageDelayed(0,1000);
        }
    }
    class ChannelViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        private GridView gv_Channel;
        private ChannelAdapter adapter;


        public ChannelViewHolder(final Context mContext, View itemView) {
            super(itemView);
            this.mContext=mContext;
            gv_Channel= (GridView) itemView.findViewById(R.id.gv_channel);


            //设置item点击事件
            gv_Channel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView adapterView, View view, int position, long id) {
                    Toast.makeText(mContext,"position"+position,Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void setData(List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {

            //得到数据了，设置GridView适配器

            adapter=new ChannelAdapter(mContext,channel_info);
            gv_Channel.setAdapter(adapter);
        }
    }
    class ActViewHolder extends RecyclerView.ViewHolder{

        private  Context mContext;
        private ViewPager act_viewpager;

        public ActViewHolder(Context mContext, View itemView){
            super(itemView);
            this.mContext=mContext;

            act_viewpager=(ViewPager) itemView.findViewById(R.id.act_viewpager);


        }

        public void setData(final List<ResultBeanData.ResultBean.ActInfoBean> act_info) {

            act_viewpager.setPageMargin(60);
            act_viewpager.setOffscreenPageLimit(3);//>=3

            //setPageTransformer 决定动画效果
            act_viewpager.setPageTransformer(true, new
                    ScaleInTransformer());
            act_viewpager.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return act_info.size();
                }

                @Override
                public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                    return view==object;
                }

                @NonNull
                @Override
                public Object instantiateItem(@NonNull ViewGroup container, final int position) {
                    ImageView imageView=new ImageView(mContext);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    //添加到容器

                    Glide.with(mContext).load(Constants.BASE_URL_IMAGE+act_info.get(position).getIcon_url()).into(imageView);

                    container.addView(imageView);

                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(mContext,"position"+position,Toast.LENGTH_SHORT).show();
                        }
                    });

                    return imageView;
                }

                @Override
                public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                    container.removeView((View)object);
                }
            });
        }
    }
    //得到类型
    @Override
    public int getItemViewType(int position) {
        switch (position){
            case BANNER:
                currentType=BANNER;
                break;
            case CHANNEL:
                currentType=CHANNEL;
                break;
            case ACT:
                currentType=ACT;
                break;
            case SECKILL:
                currentType=SECKILL;
                break;
            case RECOMMEND:
                currentType=RECOMMEND;
                break;
            case HOT:
                currentType=HOT;
                break;
        }
        return currentType;

    }

    //总共有多少个item
    @Override
    public int getItemCount() {

        //开发过程中要从1-->6
        return 6;
    }
}
