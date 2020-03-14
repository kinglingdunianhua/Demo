package com.example.newdemo.shoppingcart.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.newdemo.R;
import com.example.newdemo.base.BaseFragment;
import com.example.newdemo.home.bean.Bean.GoodsBean;
import com.example.newdemo.shoppingcart.adapter.ShoppingCartAdapter;
import com.example.newdemo.shoppingcart.utils.CartStorage;

import java.util.List;

public class ShoppingCartFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = ShoppingCartFragment.class.getSimpleName();


    private TextView tvShopcartEdit;
    private RecyclerView recyclerview;
    private LinearLayout llCheckAll;
    private CheckBox checkboxAll;
    private TextView tvShopcartTotal;
    private Button btnCheckOut;
    private CheckBox cbAll;
    private Button btnDelete;
    private Button btnCollection;
    private ImageView ivEmpty;
    private TextView tvEmptyCartTobuy;
    private LinearLayout ll_empty_shopcart;
    private ShoppingCartAdapter adapter;


    @Override
    public View initView() {
        Log.e(TAG,"购物车视图被初始化了");
        View view=View.inflate(mContext, R.layout.fragment_shoppingcart,null);
        tvShopcartEdit = (TextView)view.findViewById( R.id.tv_shopcart_edit );
        recyclerview = (RecyclerView)view.findViewById( R.id.recyclerview );
        llCheckAll = (LinearLayout)view.findViewById( R.id.ll_check_all );
        checkboxAll = (CheckBox)view.findViewById( R.id.checkbox_all );
        tvShopcartTotal = (TextView)view.findViewById( R.id.tv_shopcart_total );
        btnCheckOut = (Button)view.findViewById( R.id.btn_check_out );
        cbAll = (CheckBox)view.findViewById( R.id.cb_all );
        btnDelete = (Button)view.findViewById( R.id.btn_delete );
        btnCollection = (Button)view.findViewById( R.id.btn_collection );
        ivEmpty = (ImageView)view.findViewById( R.id.iv_empty );
        tvEmptyCartTobuy = (TextView)view.findViewById( R.id.tv_empty_cart_tobuy );
        ll_empty_shopcart= (LinearLayout) view.findViewById(R.id.ll_empty_shopcart);




        btnCheckOut.setOnClickListener(this);
        btnDelete.setOnClickListener( this );
        btnCollection.setOnClickListener( this );


        return view;
    }

    @Override
    public void onClick(View v) {
        if ( v == btnCheckOut ) {
            // Handle clicks for btnCheckOut
        } else if ( v == btnDelete ) {
            // Handle clicks for btnDelete
        } else if ( v == btnCollection ) {
            // Handle clicks for btnCollection
        }
    }


    @Override
    public void initData() {
        super.initData();
        Log.e(TAG,"购物车数据被初始化了");

        showData();


    }

    //展现数据
    private void showData() {
        List<GoodsBean> goodsBeanList= CartStorage.getInstance().getAllData();

        if(goodsBeanList!=null&&goodsBeanList.size()>0){
            //干掉没有数据的布局
            ll_empty_shopcart.setVisibility(View.GONE);
            //设置适配器
            adapter=new ShoppingCartAdapter(mContext,goodsBeanList);
            recyclerview.setAdapter(adapter);

            //设置布局管理器
            recyclerview.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));

        }else{
            //显示空的布局
            ll_empty_shopcart.setVisibility(View.VISIBLE);
        }

    }
}

