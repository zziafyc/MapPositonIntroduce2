package com.zhongyong.mappositonintroduce.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * Created by fyc on 2017/11/16.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private Toast toast = null;
    protected static String TAG = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        ButterKnife.bind(this);
        TAG = this.getClass().getSimpleName();
        initViewsAndEvents();

    }

    public abstract int getLayoutResourceId();

    public abstract void initViewsAndEvents();

    public void showToast(String msg) {
        if (null != msg) {
            if (toast == null) {
                toast = Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
            } else {
                toast.setText(msg);
            }
            toast.show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
