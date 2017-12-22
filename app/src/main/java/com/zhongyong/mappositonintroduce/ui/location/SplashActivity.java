package com.zhongyong.mappositonintroduce.ui.location;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.zhongyong.mappositonintroduce.R;
import com.zhongyong.mappositonintroduce.base.BaseActivity;

/**
 * Created by fyc on 2017/12/20.
 */

public class SplashActivity extends BaseActivity {
    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initViewsAndEvents() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    Log.e(TAG, "thread sleep failed");
                } finally {
                    turnToMain();
                }
            }
        }.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    private void turnToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.keep);
    }

}
