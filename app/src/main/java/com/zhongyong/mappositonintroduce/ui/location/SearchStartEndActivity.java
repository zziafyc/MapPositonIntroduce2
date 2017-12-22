package com.zhongyong.mappositonintroduce.ui.location;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.navisdk.adapter.BNRoutePlanNode;
import com.zhongyong.mappositonintroduce.R;
import com.zhongyong.mappositonintroduce.base.BaseActivity;
import com.zhongyong.mappositonintroduce.base.MyApp;
import com.zhongyong.mappositonintroduce.ui.navi.BNDemoGuideActivity;
import com.zhongyong.mappositonintroduce.ui.navi.MyDrivingRouteOverlay;

import butterknife.Bind;

/**
 * Created by fyc on 2017/12/21.
 */

public class SearchStartEndActivity extends BaseActivity {
    @Bind(R.id.startLocation)
    TextView startLocationTv;
    @Bind(R.id.endLocation)
    TextView endLocationTv;
    @Bind(R.id.historyLv)
    ListView historyLv;
    @Bind(R.id.routePlanMap)
    TextureMapView routePlanMap;
    @Bind(R.id.startNavi)
    LinearLayout startNaviLl;
    @Bind(R.id.changeIv)
    ImageView changeIv;
    @Bind(R.id.backLL)
    LinearLayout backLl;
    private BaiduMap mBaiduMap;
    private BNRoutePlanNode startNode;
    private BNRoutePlanNode endNode;
    private PlanNode startPlanNode;
    private PlanNode endPlanNode;
    private RoutePlanSearch mRoutePlanSearch;
    private static final int REQUEST_START_LOCATION = 1;
    private static final int REQUEST_END_LOCATION = 2;
    private static final int RESULT_CODE_LOCATION = 8;

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_search_start_end;
    }

    @Override
    public void initViewsAndEvents() {
        //相关初始化
        initViews();
        initListener();

    }

    private void initViews() {
        //初始化地图相关
        mBaiduMap = routePlanMap.getMap();
        mRoutePlanSearch = RoutePlanSearch.newInstance();
        mRoutePlanSearch.setOnGetRoutePlanResultListener(listener);
        //初始化startNode
        if (MyApp.MyLocationNode != null) {
            startNode = MyApp.MyLocationNode;
        }
        //从主界面跳转到locationActivity，然后跳转到此的情况
        Intent intent = getIntent();
        if (intent != null) {
            PoiInfo poiInfo = intent.getParcelableExtra("poiInfo");
            if (poiInfo != null) {
                String locationName = poiInfo.name;
                if (!TextUtils.isEmpty(locationName)) {
                    endLocationTv.setText(locationName);
                }
                endNode = new BNRoutePlanNode(poiInfo.location.longitude,
                        poiInfo.location.latitude, poiInfo.name, null, BNRoutePlanNode.CoordinateType.BD09LL);
                initRoutePlan();
                return;
            }

        }
        //导航按钮初始化
        if (startNode != null && endNode != null) {
            startNaviLl.setVisibility(View.VISIBLE);
        } else {
            startNaviLl.setVisibility(View.GONE);
        }
    }

    private void initRoutePlan() {
        if (startNode != null && endNode != null) {
            routePlanMap.setVisibility(View.VISIBLE);
            historyLv.setVisibility(View.GONE);
            startNaviLl.setVisibility(View.VISIBLE);
            if (startNode.getName().equals(endNode.getName())) {
                showToast("对不起，始发地和目的地不能相同！");
                return;
            }
            startPlanNode = PlanNode.withLocation(new LatLng(startNode.getLatitude(), startNode.getLongitude()));
            endPlanNode = PlanNode.withLocation(new LatLng(endNode.getLatitude(), endNode.getLongitude()));
            mRoutePlanSearch.drivingSearch(new DrivingRoutePlanOption().from(startPlanNode).to(endPlanNode));
        }
    }

    OnGetRoutePlanResultListener listener = new OnGetRoutePlanResultListener() {
        public void onGetWalkingRouteResult(WalkingRouteResult result) {
            // 获取步行线路规划结果

        }

        public void onGetTransitRouteResult(TransitRouteResult result) {
            // 获取公交换乘路径规划结果
        }

        @Override
        public void onGetMassTransitRouteResult(MassTransitRouteResult result) {

        }

        public void onGetDrivingRouteResult(DrivingRouteResult result) {
            // 获取驾车线路规划结果
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                Toast.makeText(SearchStartEndActivity.this, "抱歉，未找到结果",
                        Toast.LENGTH_SHORT).show();
            }
            if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
                // 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
                // result.getSuggestAddrInfo()
                return;
            }
            if (result.error == SearchResult.ERRORNO.NO_ERROR) {
                mBaiduMap.clear();
                MyDrivingRouteOverlay overlay = new MyDrivingRouteOverlay(mBaiduMap);
                mBaiduMap.setOnMarkerClickListener(overlay);
                overlay.setData(result.getRouteLines().get(0));
                overlay.addToMap();
                overlay.zoomToSpan();

            }
        }

        @Override
        public void onGetIndoorRouteResult(IndoorRouteResult result) {

        }

        @Override
        public void onGetBikingRouteResult(BikingRouteResult result) {

        }
    };

    private void initListener() {
        backLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        startLocationTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchStartEndActivity.this, LocationActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("selectLocation", startLocationTv.getText().toString());
                intent.putExtras(bundle);
                startActivityForResult(intent, REQUEST_START_LOCATION);

            }
        });
        endLocationTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchStartEndActivity.this, LocationActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("selectLocation", endLocationTv.getText().toString());
                intent.putExtras(bundle);
                startActivityForResult(intent, REQUEST_END_LOCATION);

            }
        });
        startNaviLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dealResult();
            }
        });
        changeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            return;
        }
        if (requestCode == REQUEST_START_LOCATION && resultCode == RESULT_CODE_LOCATION) {
            PoiInfo poiInfo = data.getParcelableExtra("poiInfo");
            if (poiInfo != null) {
                String locationName = poiInfo.name;
                if (!TextUtils.isEmpty(locationName)) {
                    startLocationTv.setText(locationName);
                }
                startNode = new BNRoutePlanNode(poiInfo.location.longitude,
                        poiInfo.location.latitude, poiInfo.name, null, BNRoutePlanNode.CoordinateType.BD09LL);
            }
        } else if (requestCode == REQUEST_END_LOCATION && resultCode == RESULT_CODE_LOCATION) {
            PoiInfo poiInfo = data.getParcelableExtra("poiInfo");
            if (poiInfo != null) {
                String locationName = poiInfo.name;
                if (!TextUtils.isEmpty(locationName)) {
                    endLocationTv.setText(locationName);
                }
                endNode = new BNRoutePlanNode(poiInfo.location.longitude,
                        poiInfo.location.latitude, poiInfo.name, null, BNRoutePlanNode.CoordinateType.BD09LL);
            }
        }
        initRoutePlan();
    }

    private void dealResult() {
        if (startNode != null && endNode != null) {
            if (startNode.getName().equals(endNode.getName())) {
                showToast("对不起，始发地和目的地不能相同！");
                return;
            }
            Intent intent = new Intent(SearchStartEndActivity.this, BNDemoGuideActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("startNode", startNode);
            bundle.putSerializable("endNode", endNode);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
