package com.example.newdemo.person_page;

import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.newdemo.R;
import com.example.newdemo.base.BaseFragment;
import com.example.newdemo.utils.Constants;
import com.xuexiang.xui.widget.button.RippleView;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PersonPage extends BaseFragment implements View.OnClickListener {
    private static final String TAG = PersonPage.class.getSimpleName();
    private DrawerLayout drawerLayout;
    private LinearLayout linearLayout;

    private NiceSpinner sex;
    private NiceSpinner person_height;
    private NiceSpinner person_weight;
    private NiceSpinner season;
    private NiceSpinner clothesSituation;
    private NiceSpinner colthesStyle;
    private RippleView match_start;
//    private TextView textView;

    @Override
    public View initView() {
        Log.e(TAG,"类型视图被初始化了");

        View view=View.inflate(mContext, R.layout.fragment_person,null);
        findview(view);
        return view;
    }

    private void findview(View view) {
        drawerLayout=(DrawerLayout) view.findViewById(R.id.drawer);
        linearLayout=(LinearLayout) view.findViewById(R.id.person_left);

        sex=(NiceSpinner)view.findViewById(R.id.sex);
        person_height=(NiceSpinner)view.findViewById(R.id.person_height);
        person_weight=(NiceSpinner)view.findViewById(R.id.person_weight);
        season = (NiceSpinner)view.findViewById( R.id.season );
        clothesSituation = (NiceSpinner)view.findViewById( R.id.clothes_situation );
        colthesStyle = (NiceSpinner)view.findViewById( R.id.colthes_style );
        match_start=(RippleView)view.findViewById(R.id.match_start);

        match_start.setOnClickListener(this);
    }

    @Override
    public void initData() {
        super.initData();
        DrawableLisenter();

        initPersonal();

        Log.e(TAG,"类型数据被初始化了");

    }

    private void initPersonal() {

        //基本信息
        List<String> setSex=new LinkedList<>(Arrays.asList(getResources().getStringArray(Constants.Gender)));
        sex.attachDataSource(setSex);
        sex.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                String item= (String) parent.getItemAtPosition(position);
            }
        });
        List<String> setHeight=new LinkedList<>(Arrays.asList(getResources().getStringArray(Constants.Height)));
        person_height.attachDataSource(setHeight);
        person_height.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                String item= (String) parent.getItemAtPosition(position);
            }
        });
        List<String> setWeight=new LinkedList<>(Arrays.asList(getResources().getStringArray(Constants.Weight)));
        person_weight.attachDataSource(setWeight);
        person_weight.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                String item= (String) parent.getItemAtPosition(position);
            }
        });


        //个性标签
        List<String> setSituation=new LinkedList<>(Arrays.asList(getResources().getStringArray(Constants.Situation)));
        clothesSituation.attachDataSource(setSituation);
        clothesSituation.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                String item= (String) parent.getItemAtPosition(position);
            }
        });
        List<String> setSeason=new LinkedList<>(Arrays.asList(getResources().getStringArray(Constants.Season)));
        season.attachDataSource(setSeason);
        season.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                String item= (String) parent.getItemAtPosition(position);
            }
        });
        List<String> setStyle=new LinkedList<>(Arrays.asList(getResources().getStringArray(Constants.Style)));
        colthesStyle.attachDataSource(setStyle);
        colthesStyle.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                String item= (String) parent.getItemAtPosition(position);
            }
        });
    }




    private void DrawableLisenter() {
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.END);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v==match_start){
            String personal_information=sex.getText().toString()+","+person_height.getText().toString()+","+person_weight.getText().toString()+","+clothesSituation.getText().toString()+","+season.getText().toString()+","+colthesStyle.getText().toString();
            Toast.makeText(mContext,personal_information,Toast.LENGTH_SHORT).show();

        }
    }
}

