package com.example.newdemo.app;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.example.newdemo.R;
import com.example.newdemo.base.BaseFragment;
import com.example.newdemo.community.CommunityFragment;
import com.example.newdemo.home.Fragment.HomeFragment;
import com.example.newdemo.person_page.PersonPage;
import com.example.newdemo.shoppingcart.fragment.ShoppingCartFragment;
import com.example.newdemo.user.UserFragment;
import com.xuexiang.xui.XUI;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {


    @Bind(R.id.frameLayout)
    FrameLayout frameLayout;

    @Bind(R.id.rg_main)
    RadioGroup rgMain;
    private ArrayList<BaseFragment> fragments;
    private BaseFragment tempFragemnt;
    public Boolean IsTrue=false;
    protected void onCreate(Bundle savedInstanceState) {
        XUI.initTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initFragment();
        initListener();



    }

    private void initFragment() {

        fragments=new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new PersonPage());
        fragments.add(new CommunityFragment());
        fragments.add(new ShoppingCartFragment());
        fragments.add(new UserFragment());
    }

    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            private int position;

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        position = 0;
                        break;
                    case R.id.rb_type:

                        position = 1;
                        break;
                    case R.id.rb_community:
                        position = 2;

                        break;
                    case R.id.rb_cart:
                        position = 3;
                        break;
                    case R.id.rb_user:
                        position = 4;
                        break;
                }
                BaseFragment baseFragment = getFragment(position);
                switchFragment(tempFragemnt, baseFragment);
            }
        });
        rgMain.check(R.id.rb_home);
    }

    private BaseFragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

    private void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {
        if (tempFragemnt != nextFragment) {
            tempFragemnt = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                if (!nextFragment.isAdded()) {
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.frameLayout, nextFragment).commit();
                } else {
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }

        }
    }
}


