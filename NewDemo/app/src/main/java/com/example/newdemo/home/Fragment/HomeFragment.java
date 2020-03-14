package com.example.newdemo.home.Fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.newdemo.R;
import com.example.newdemo.base.BaseFragment;
import com.example.newdemo.home.adapter.HomeFragmentAdapter;
import com.example.newdemo.home.bean.Bean.ResultBeanData;
import com.example.newdemo.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class HomeFragment extends BaseFragment {
    private static final String TAG = HomeFragment.class.getSimpleName();

    private RecyclerView rvHome;
    private ImageView ib_top;
    private TextView tv_search_home;
    private TextView tv_message_home;
    private HomeFragmentAdapter adapter;
    private ResultBeanData.ResultBean resultBean;

    @Override
    public View initView() {
        Log.e(TAG, "主页视图被初始化了");
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        rvHome = (RecyclerView) view.findViewById(R.id.rv_home);
        ib_top = (ImageView) view.findViewById(R.id.ib_top);
        tv_search_home = (TextView) view.findViewById(R.id.tv_search_home);
        tv_message_home = (TextView) view.findViewById(R.id.tv_message_home);

//设置点击事件
        initListener();
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "主页数据被初始化了");


        //前端向后端发送接口的地方。。。。需要传输过来一个homeFragment的json文件


        String url = Constants.HOME_URL;
        OkHttpUtils
                .get()
                .url(url)

                //网站密码

                // .addParams("username", "hyman")
                //  .addParams("password", "123")

                .build()
                .execute(new StringCallback()
                {

                    //当请求失败的时候回调
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG,"首页请求失败"+e.getMessage());


                    }

                    //当联网成功的时候回调，请求成功的数据
                    @Override
                    public void onResponse(String response, int id) {

                        Log.e(TAG,"首页请求成功"+response);



                       processData(response);
                    }


                });
    }

    private void processData(String json) {
        ResultBeanData resultBeanData = JSON.parseObject(json, ResultBeanData.class);
        resultBean=resultBeanData.getResult();

        if(resultBean!=null){
            //有数据
            //设置适配器
            adapter=new HomeFragmentAdapter(mContext,resultBean);
            rvHome.setAdapter(adapter);

            GridLayoutManager manager=new GridLayoutManager(mContext,1);
            //设置跨度大小的监听
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if(position<=3){

                        ib_top.setVisibility(View.GONE);
                    }else{
                        //显示隐藏按钮
                        ib_top.setVisibility(View.VISIBLE);
                    }
                    return 1;
                }
            });
            //设置布局管理者
            rvHome.setLayoutManager(manager);
        }else{

            //没有数据
        }


        Log.e(TAG,"解析成功=="+resultBean.getHot_info().get(0).getName());

    }
    private void initListener() {

        ib_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rvHome.scrollToPosition(0);
            }
        });


        //搜索
        tv_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
            }
        });


        tv_message_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "进入消息中心", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
