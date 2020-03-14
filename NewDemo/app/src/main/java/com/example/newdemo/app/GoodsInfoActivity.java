package com.example.newdemo.app;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.newdemo.R;
import com.example.newdemo.home.bean.Bean.GoodsBean;
import com.example.newdemo.shoppingcart.utils.CartStorage;
import com.example.newdemo.utils.Constants;

public class GoodsInfoActivity extends Activity implements View.OnClickListener {

    private ImageButton ibGoodInfoBack;
    private ImageButton ibGoodInfoMore;
    private ImageView ivGoodInfoImage;
    private TextView tvGoodInfoName;
    private TextView tvGoodInfoDesc;
    private TextView tvGoodInfoPrice;
    private TextView tvGoodInfoStyle;
    private WebView wbGoodInfoMore;
    private LinearLayout llGoodsRoot;
    private TextView tvGoodInfoCallcenter;
    private TextView tvGoodInfoCollection;
    private TextView tvGoodInfoCart;
    private Button btnGoodInfoAddcart;
    private TextView tv_more_share;
    private TextView tv_more_search;
    private TextView tv_more_home;
    private Button btn_more;
    private GoodsBean goodsBean;



    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2019-09-28 14:29:33 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        tv_more_share=(TextView) findViewById(R.id.tv_more_share);
        tv_more_search=(TextView) findViewById(R.id.tv_more_search);
        tv_more_home=(TextView) findViewById(R.id.tv_more_home);
        btn_more= (Button) findViewById(R.id.btn_more);

        ibGoodInfoBack = (ImageButton)findViewById( R.id.ib_good_info_back );
        ibGoodInfoMore = (ImageButton)findViewById( R.id.ib_good_info_more );
        ivGoodInfoImage = (ImageView)findViewById( R.id.iv_good_info_image );
        tvGoodInfoName = (TextView)findViewById( R.id.tv_good_info_name );
        tvGoodInfoDesc = (TextView)findViewById( R.id.tv_good_info_desc );
        tvGoodInfoPrice = (TextView)findViewById( R.id.tv_good_info_price );
        tvGoodInfoStyle = (TextView)findViewById( R.id.tv_good_info_style );
        wbGoodInfoMore = (WebView)findViewById( R.id.wb_good_info_more );
        llGoodsRoot = (LinearLayout)findViewById( R.id.ll_goods_root );


        tvGoodInfoCallcenter = (TextView)findViewById( R.id.tv_good_info_callcenter );
        tvGoodInfoCollection = (TextView)findViewById( R.id.tv_good_info_collection );
        tvGoodInfoCart = (TextView)findViewById( R.id.tv_good_info_cart );

        btnGoodInfoAddcart = (Button)findViewById( R.id.btn_good_info_addcart );

        ibGoodInfoBack.setOnClickListener( this );
        ibGoodInfoMore.setOnClickListener( this );
        btnGoodInfoAddcart.setOnClickListener( this );

        tvGoodInfoCallcenter.setOnClickListener( this );
        tvGoodInfoCollection.setOnClickListener( this );
        tvGoodInfoCart.setOnClickListener(this);

        //设置点击事件
        tv_more_share.setOnClickListener( this );
        tv_more_search.setOnClickListener( this );
        tv_more_home.setOnClickListener( this );

    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2019-09-28 14:29:33 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == ibGoodInfoBack ) {
            finish();
            // Handle clicks for ibGoodInfoBack
        } else if ( v == ibGoodInfoMore ) {
            // Handle clicks for ibGoodInfoMore
            Toast.makeText(this,"更多",Toast.LENGTH_SHORT).show();
        } else if ( v == btnGoodInfoAddcart ) {
            // Handle clicks for btnGoodInfoAddcart
            CartStorage.getInstance().addData(goodsBean);
            Toast.makeText(this,"加入收藏成功",Toast.LENGTH_SHORT).show();

        }else if ( v == tvGoodInfoCallcenter ) {
            // Handle clicks for btnGoodInfoAddcart
            Toast.makeText(this,"客户中心",Toast.LENGTH_SHORT).show();

        }else if ( v == tvGoodInfoCollection ) {
            // Handle clicks for btnGoodInfoAddcart
            Toast.makeText(this,"添加收藏",Toast.LENGTH_SHORT).show();

        }else if ( v == tvGoodInfoCart ) {
            // Handle clicks for btnGoodInfoAddcart
            Toast.makeText(this,"加入收藏成功",Toast.LENGTH_SHORT).show();

        }else if ( v == tv_more_share ) {
            // Handle clicks for btnGoodInfoAddcart
            Toast.makeText(this,"分享",Toast.LENGTH_SHORT).show();

        }else if ( v == tv_more_search ) {
            // Handle clicks for btnGoodInfoAddcart
            Toast.makeText(this,"搜索",Toast.LENGTH_SHORT).show();

        }else if ( v == tv_more_home ) {
            // Handle clicks for btnGoodInfoAddcart
            Toast.makeText(this,"主页面",Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        findViews();

        //接收数据
        goodsBean = (GoodsBean) getIntent().getSerializableExtra("goodsBean");
        if(goodsBean!=null){
            //Toast.makeText(this,"goodsBean=="+goodsBean.toString(),Toast.LENGTH_SHORT).show();;
            setDataForView(goodsBean);
        }
    }

    //设置数据
    private void setDataForView(GoodsBean goodsBean) {
        //设置图片
        Glide.with(this).load(Constants.BASE_URL_IMAGE+goodsBean.getFigure()).into(ivGoodInfoImage);
        //设置名称
        tvGoodInfoName.setText(goodsBean.getName());

        //设置价格
        tvGoodInfoPrice.setText("$"+goodsBean.getCover_price());

        //webView
        setWebViewData(goodsBean.getProduct_id());
    }

    private void setWebViewData(String product_id) {
        if(product_id!=null){
            wbGoodInfoMore.loadUrl("https://www.baidu.com");

            //设置z支持javascript
            WebSettings webSettings=wbGoodInfoMore.getSettings();
            webSettings.setUseWideViewPort(true);//支c持双击放大页面
            webSettings.setJavaScriptEnabled(true);//支持js
           // webSettings.setDomStorageEnabled(true);

            //解决网页无法打开的问题

            //优先使用缓存
            webSettings.setCacheMode(webSettings.LOAD_CACHE_ELSE_NETWORK);


            wbGoodInfoMore.setWebViewClient(new WebViewClient(){

                //可修改
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        view.loadUrl(request.getUrl().toString());
                    }
                    return true;
                }
            });
        }


    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
