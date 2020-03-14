package com.example.newdemo.shoppingcart.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.newdemo.R;

public class AddSubView extends LinearLayout implements View.OnClickListener {
    private final Context context;
    private ImageView iv_sub;
    private ImageView iv_add;
    private TextView tv_value;
    private int value;
    private int maxvalue=5;
    private int minvalue=1;
    public int getValue() {
        String valueStr=tv_value.getText().toString().trim();
        if(!TextUtils.isEmpty(valueStr)){
            value = Integer.parseInt(valueStr);
        }
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        tv_value.setText(value+"");
    }

    public int getMaxvalue() {
        return maxvalue;
    }

    public void setMaxvalue(int maxvalue) {
        this.maxvalue = maxvalue;
    }

    public int getMinvalue() {
        return minvalue;
    }

    public void setMinvalue(int minvalue) {
        this.minvalue = minvalue;
    }


    public AddSubView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        //把布局文件实例化，并加载到addsubview类中
        View.inflate(context,R.layout.add_sub_view,this);
        iv_add=(ImageView) findViewById(R.id.iv_add);
        iv_sub=(ImageView) findViewById(R.id.iv_sub);
        tv_value=(TextView) findViewById(R.id.tv_value);

        int value = getValue();
        setValue(value);

        //设置点击事件
        iv_sub.setOnClickListener(this);
        iv_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_sub:
                subNumber();
                break;
            case R.id.iv_add:
                addNumber();
                break;


        }
    }

    private void addNumber() {
        if(value<maxvalue){
            value++;
        }
        setValue(value);
        if(onNumberChangeListener!=null){
            onNumberChangeListener.onNumberChange(value);
        }
    }

    private void subNumber() {
        if(value>minvalue){
            value--;
        }
        setValue(value);

        if(onNumberChangeListener!=null){
            onNumberChangeListener.onNumberChange(value);
        }
    }
    public interface OnNumberChangeListener{
        public void onNumberChange(int value);
    }

    private OnNumberChangeListener onNumberChangeListener;



    public void setOnNumberChangeListener(OnNumberChangeListener onNumberChangeListener) {
        this.onNumberChangeListener = onNumberChangeListener;
    }
}
