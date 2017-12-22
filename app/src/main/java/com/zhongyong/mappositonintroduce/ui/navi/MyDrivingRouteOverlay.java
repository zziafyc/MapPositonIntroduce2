package com.zhongyong.mappositonintroduce.ui.navi;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.zhongyong.mappositonintroduce.R;
import com.zhongyong.mappositonintroduce.utils.DrivingRouteOverlay;

/**
 * Created by fyc on 2017/12/22.
 */

public class MyDrivingRouteOverlay extends DrivingRouteOverlay {
    /**
     * 构造函数
     *
     * @param baiduMap 该DrivingRouteOvelray引用的 BaiduMap
     */
    public MyDrivingRouteOverlay(BaiduMap baiduMap) {
        super(baiduMap);
    }

    @Override
    public BitmapDescriptor getStartMarker() {
        return BitmapDescriptorFactory.fromResource(R.drawable.icon_openmap_focuse_mark);
    }

    @Override
    public BitmapDescriptor getTerminalMarker() {
        return BitmapDescriptorFactory.fromResource(R.drawable.icon_openmap_mark);
    }
}
