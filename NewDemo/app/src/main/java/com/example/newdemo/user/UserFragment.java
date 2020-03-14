package com.example.newdemo.user;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newdemo.R;
import com.example.newdemo.app.LoginActivity;
import com.example.newdemo.base.BaseFragment;
import com.xuexiang.xui.widget.button.RippleView;

//import static com.example.newdemo.utils.Constants.IsSign;

public class UserFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = UserFragment.class.getSimpleName();

    private ScrollView scrollview;
    private ImageButton ibUserIconAvator;
    private TextView tvUsername;
    private TextView tvUserCollect;
    private TextView tvUserInvitation;
    private TextView tvUserCallcenter;
    private TextView tvUserFeedback;
    private TextView tvUsercenter;
    private RippleView rippleView;



    private void findViews(View view) {
        scrollview = (ScrollView)view.findViewById( R.id.scrollview );
        ibUserIconAvator = (ImageButton)view.findViewById( R.id.ib_user_icon_avator );
        tvUsername = (TextView)view.findViewById( R.id.tv_username );
        tvUserCollect = (TextView)view.findViewById( R.id.tv_user_collect );
        tvUserInvitation = (TextView)view.findViewById( R.id.tv_user_invitation );
        tvUserCallcenter = (TextView)view.findViewById( R.id.tv_user_callcenter );
        tvUserFeedback = (TextView)view.findViewById( R.id.tv_user_feedback );
        tvUsercenter = (TextView)view.findViewById( R.id.tv_usercenter );
        rippleView=(RippleView)view.findViewById(R.id.logout);

        ibUserIconAvator.setOnClickListener( this );
        tvUserCollect.setOnClickListener(this);
        tvUserInvitation.setOnClickListener(this);
        tvUserCallcenter.setOnClickListener(this);
        tvUserFeedback.setOnClickListener(this);
        rippleView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if ( v == ibUserIconAvator ) {

            // Handle clicks for ibUserIconAvator
            Intent intent=new Intent(mContext, LoginActivity.class);
            getContext().startActivity(intent);


            ibUserIconAvator.setImageDrawable(getResources().getDrawable(R.drawable.signpicture));
            tvUsername.setText("已登录");

        }else if(v==rippleView){
            Toast.makeText(mContext,"登出",Toast.LENGTH_SHORT).show();
        }else if(v==tvUserFeedback){
            Toast.makeText(mContext,"服务反馈",Toast.LENGTH_SHORT).show();
        }else if(v==tvUserCallcenter){
            Toast.makeText(mContext,"客服中心",Toast.LENGTH_SHORT).show();
        }else if(v==tvUserInvitation){
            Toast.makeText(mContext,"邀请分享",Toast.LENGTH_SHORT).show();
        }else if(v==tvUserCollect){
            Toast.makeText(mContext,"我的收藏",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public View initView() {
        Log.e(TAG,"使用者视图被初始化了");
        View view = View.inflate(mContext, R.layout.fragment_user,null);

        findViews(view);
        return view;
    }
    @Override
    public void initData() {
        super.initData();
        Log.e(TAG,"使用者数据被初始化了");

    }
}

