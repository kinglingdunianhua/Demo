package com.example.newdemo.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newdemo.R;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.newdemo.utils.Constants.*;

public class LoginActivity extends Activity implements View.OnClickListener {
    private ImageButton ibLoginBack;
    private EditText etLoginPhone;
    private EditText etLoginPwd;
    private Button btnLogin;
    private TextView tvLoginRegister;
    private TextView tvLoginForgetPwd;
    private ImageButton ibWeibo;
    private ImageButton ibQq;
    private ImageButton ibWechat;
    private Response response;
    private ImageButton ibUserIconAvator;
    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2019-10-12 14:24:22 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        ibLoginBack = (ImageButton)findViewById( R.id.ib_login_back );
        etLoginPhone = (EditText)findViewById( R.id.et_login_phone );
        etLoginPwd = (EditText)findViewById( R.id.et_login_pwd );
        btnLogin = (Button)findViewById( R.id.btn_login );
        tvLoginRegister = (TextView)findViewById( R.id.tv_login_register );
        tvLoginForgetPwd = (TextView)findViewById( R.id.tv_login_forget_pwd );
        ibWeibo = (ImageButton)findViewById( R.id.ib_weibo );
        ibQq = (ImageButton)findViewById( R.id.ib_qq );
        ibWechat = (ImageButton)findViewById( R.id.ib_wechat );





        ibLoginBack.setOnClickListener( this );
        btnLogin.setOnClickListener( this );
        ibWeibo.setOnClickListener( this );
        ibQq.setOnClickListener( this );
        ibWechat.setOnClickListener( this );
        tvLoginRegister.setOnClickListener(this);
        tvLoginForgetPwd.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if ( v == ibLoginBack ) {
            finish();
            // Handle clicks for ibLoginBack
        } else if ( v == btnLogin ) {
            // Handle clicks for btnLogin
            Toast.makeText(this,"登陆被点击了",Toast.LENGTH_SHORT).show();
            Toast.makeText(this,etLoginPhone.getText().toString()+etLoginPwd.getText().toString(),Toast.LENGTH_SHORT).show();
            OkHttpClient client = new OkHttpClient();
            RequestBody body= new FormBody.Builder()
                    .add(etLoginPhone.getText().toString(),etLoginPwd.getText().toString())
                    .build();
            Request request=new Request.Builder()
                    .url(Base_URL)
                    .post(body)
                    .build();
            /*
            try{
                response=client.newCall(request).execute();
                String result = response.body().toString();
            }catch (Exception e){
                e.printStackTrace();
            }
            */
            //View view = View.inflate(this, R.layout.fragment_user, null);

            //ibUserIconAvator = (ImageButton)view.findViewById( R.id.ib_user_icon_avator );
            //ibUserIconAvator.setImageDrawable(getResources().getDrawable(R.drawable.signpicture));

            finish();

        } else if ( v == ibWeibo ) {
            // Handle clicks for ibWeibo
            Toast.makeText(this,"微信登录加载",Toast.LENGTH_SHORT).show();
        } else if ( v == ibQq ) {
            // Handle clicks for ibQq
            Toast.makeText(this,"QQ登录加载",Toast.LENGTH_SHORT).show();
        } else if ( v == ibWechat ) {
            // Handle clicks for ibWechat
            Toast.makeText(this,"微博登陆加载",Toast.LENGTH_SHORT).show();
        }else if ( v == tvLoginRegister ) {
            // Handle clicks for ibWechat
            Toast.makeText(this,"注册账号",Toast.LENGTH_SHORT).show();
        }else if ( v == tvLoginForgetPwd ) {
            // Handle clicks for ibWechat
            Toast.makeText(this,"忘记密码",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate( Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        findViews();

    }


}
