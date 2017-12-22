package com.zhongyong.mappositonintroduce.base;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.navisdk.adapter.BNRoutePlanNode;
import com.zhongyong.mappositonintroduce.ui.location.service.LocationService;

/**
 * Created by fyc on 2017/12/20.
 */

public class MyApp extends Application {
    public LocationService mLocationService;
    public static BNRoutePlanNode MyLocationNode;

    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(getApplicationContext());
        /***
         * 初始化定位sdk，建议在Application中创建
         */
        mLocationService = new LocationService(getApplicationContext());

    }
}
