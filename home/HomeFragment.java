package com.zggk.newiroad.home;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;
import com.zggk.newiroad.Bean.CdflInfo;
import com.zggk.newiroad.Bean.SgRInfo;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.R;
import com.zggk.newiroad.check.CheckActivity;
import com.zggk.newiroad.db.dbhelper.CuringDaoImpl;
import com.zggk.newiroad.db.dbhelper.dbInfo.FilterBHInfo;
import com.zggk.newiroad.db.dbhelper.dbInfo.FilterLxInfo;
import com.zggk.newiroad.handle.HandleActivity;
import com.zggk.newiroad.handle.LoadDataDialog;

import com.zggk.newiroad.disease.DiseaseActivity;
import com.zggk.newiroad.home.distribute.DistributeActivity;

import com.zggk.newiroad.main.MainActivity;
import com.zggk.newiroad.repair.RepairActivity;
import com.zggk.newiroad.log.LogActivity;
import com.zggk.newiroad.mvp.MVPBaseFragment;

import com.zggk.newiroad.season.SeasonActivity;
import com.zggk.newiroad.utils.PermissionUtils;
import com.zggk.newiroad.utils.Utils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;


import java.util.ArrayList;
import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class HomeFragment extends MVPBaseFragment<HomeContract.View, HomePresenter> implements HomeContract.View {
    private View view;

    private RecyclerView mRecyclerView;

    private CommonAdapter<CdflInfo.ZCDBean> mAdapter;
    private RecyclerView.ItemDecoration itemDecoration;
    private LinearLayoutManager mManagerColor;
    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;
    private ArrayList<Integer> list_path= new ArrayList<>();
    private Banner banner;
    private int[] guide_images = {
            R.drawable.gcys_img,
            R.drawable.xcbi_img,
            R.drawable.shpf_img,
            R.drawable.sgwx_img,
            R.drawable.xcrz_img,
            R.drawable.xcrz_img,
    };
    private TextView gs;
    private int types;
    private PermissionUtils permissionUtils;
    private ArrayList<String> permissionsOfSDCardAndCamera;
    private LoadDataDialog loadDataDialog;
    private CuringDaoImpl curingDao;
    private List<CdflInfo> videoVos2;//登录接口返回的显示哪些模块权限的数据
    private ArrayList<CdflInfo.ZCDBean> date;
    private String xcrzTitle, xcbhTitle, shpfTitle, sgwxTitle, gcysTitle, jjxyhTitle;//模块名称
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fra_home, null);
        curingDao = new CuringDaoImpl(getActivity());
        initData();
        gs = (TextView)view. findViewById(R.id.gs);
        gs.setText(MyApplication.spUtils.getString("dqgydwmc"));
        mRecyclerView = (RecyclerView)view. findViewById(R.id.list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mAdapter= new CommonAdapter<CdflInfo.ZCDBean>(getActivity(),R.layout.item,date){

            @Override
            protected void convert(ViewHolder holder, CdflInfo.ZCDBean s, int position) {
                if (s.getCDMC().equals("工程验收")) {
                    holder.setBackgroundRes(R.id.im, guide_images[0]);
                    holder.setText(R.id.te, s.getCDMC());
                } else if (s.getCDMC().equals("巡查病害")) {
                    holder.setBackgroundRes(R.id.im, guide_images[1]);
                    holder.setText(R.id.te, s.getCDMC());
                } else if (s.getCDMC().equals("审核派发")) {
                    holder.setBackgroundRes(R.id.im, guide_images[2]);
                    holder.setText(R.id.te, s.getCDMC());
                } else if (s.getCDMC().equals("施工维修")) {
                    holder.setBackgroundRes(R.id.im, guide_images[3]);
                    holder.setText(R.id.te, s.getCDMC());
                } else if (s.getCDMC().equals("巡查日志")) {
                    holder.setBackgroundRes(R.id.im, guide_images[4]);
                    holder.setText(R.id.te, s.getCDMC());
                }else if (s.getCDMC().equals("季节性养护")) {
                    holder.setBackgroundRes(R.id.im, guide_images[5]);
                    holder.setText(R.id.te, s.getCDMC());
                }
            }
        };
        mRecyclerView.setAdapter(mAdapter);
        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(mAdapter);
        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.header, null);
        mHeaderAndFooterWrapper.addHeaderView(headView);
        banner = (Banner) headView.findViewById(R.id.banner);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new MyLoader());
        //设置图片集合
        banner.setImages(list_path);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(10000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        mRecyclerView.setAdapter(mHeaderAndFooterWrapper);
        mHeaderAndFooterWrapper.notifyDataSetChanged();
        linstener();
        return view;
    }

    private void linstener() {
        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
               if (date.get(position-1).getCDMC().equals("巡查日志")){
                   types=1;
                   if (!checkPermissionsOfSDCardAndCamera()) {
                       Intent intent = new Intent(getActivity(), LogActivity.class);
                       startActivity(intent);
                   }
               }else if (date.get(position-1).getCDMC().equals("巡查病害")){
                   types=2;
                   if (!checkPermissionsOfSDCardAndCamera()) {
                       Intent intent = new Intent(getActivity(), DiseaseActivity.class);
                       startActivity(intent);
                   }

               }else if (date.get(position-1).getCDMC().equals("审核派发")){
                   types=3;
                   if (!checkPermissionsOfSDCardAndCamera()) {
                       Intent intent = new Intent(getActivity(), RepairActivity.class);
                       startActivity(intent);
                   }

               }else if (date.get(position-1).getCDMC().equals("施工维修")){
                   types=4;
                   if (!checkPermissionsOfSDCardAndCamera()) {
                       if (Utils.isNetworkAvailable(getContext()) == false) {//没网
                           Intent intent = new Intent(getActivity(), HandleActivity.class);
                           startActivity(intent);
                       } else {//有网
                           String str = "正在加载...";
                           showLoadingDialogMethod(str);
                           getsxtj();
                       }

                   }

               } else if (date.get(position-1).getCDMC().equals("工程验收")){
                   types=5;
                   if (!checkPermissionsOfSDCardAndCamera()) {
                       Intent intent = new Intent(getActivity(), CheckActivity.class);
                       startActivity(intent);
                   }

               }else if (date.get(position-1).getCDMC().equals("季节性养护")){
                   types=5;
                   if (!checkPermissionsOfSDCardAndCamera()) {
                       Intent intent = new Intent(getActivity(), SeasonActivity.class);
                       startActivity(intent);
                   }

               }

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    private void getsxtj() {
        OkHttpUtils.get()
                .tag(this)
                .addParams("lxid","" )
                .addParams("bhid","")
                .addParams("gydwid",MyApplication.app.spUtils.getString("dqgydwid", "") )
                .url(MyApplication.BASEURL+"QueryYhwx")
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(okhttp3.Call call, Exception e, int id) {
                        if (!"".equals(id+"")&&id==0){
                            if (loadDataDialog != null && loadDataDialog.isShowing()) {
                                loadDataDialog.cancel();
                            }
                            MyApplication.app.customToast("您当前网络信号不好");
                        }
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        SgRInfo videoVos = JSON.parseObject(response, SgRInfo.class);
                        if (videoVos.getSTATE().equals("1")){
                            if (loadDataDialog != null && loadDataDialog.isShowing()) {
                                loadDataDialog.cancel();
                            }
                            if (videoVos.getLXDATA() != null && videoVos.getLXDATA().size() > 0) {
                                ArrayList<FilterLxInfo> lxlistInfo = new ArrayList<>();
                                for (int i = 0; i < videoVos.getLXDATA().size(); i++) {
                                    if (!Utils.isNull(videoVos.getLXDATA().get(i).getLXID())
                                            && !Utils.isNull(videoVos.getLXDATA().get(i).getLXMC())) {
                                        FilterLxInfo LXlist = new FilterLxInfo();
                                        LXlist.setLXID(videoVos.getLXDATA().get(i).getLXID());
                                        LXlist.setLXMC(videoVos.getLXDATA().get(i).getLXMC());
                                        lxlistInfo.add(LXlist);
                                    }
                                }
                                curingDao.deleteAllLXInfo();
                                curingDao.addFilterLXInfo(lxlistInfo);
                            }
                            if (videoVos.getBHLXDATA() != null && videoVos.getBHLXDATA().size() > 0) {
                                ArrayList<FilterBHInfo> bhlistInfo = new ArrayList<>();
                                for (int i = 0; i < videoVos.getBHLXDATA().size(); i++) {
                                    if (!Utils.isNull(videoVos.getBHLXDATA().get(i).getBHLXID())
                                            && !Utils.isNull(videoVos.getBHLXDATA().get(i).getBHLX())) {
                                        FilterBHInfo BHlist = new FilterBHInfo();
                                        BHlist.setBHMC(videoVos.getBHLXDATA().get(i).getBHLX());
                                        BHlist.setBHMCID(videoVos.getBHLXDATA().get(i).getBHLXID());
                                        bhlistInfo.add(BHlist);
                                    }
                                }
                                curingDao.deleteAllBHInfo();
                                curingDao.addFilterBHInfo(bhlistInfo);
                            }
                            Intent intent = new Intent(getActivity(), HandleActivity.class);
                            intent.putExtra("videoVos",new Gson().toJson(videoVos));
                            startActivity(intent);
                        }
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // “读写SD卡”的权限结果处理
        if (permissionUtils != null && permissionsOfSDCardAndCamera != null && permissionUtils.dealRequestPermissionsResult(
                permissionsOfSDCardAndCamera, requestCode, permissions, grantResults)) {
            permissionsOfSDCardAndCamera = null;
            if (types==1){
                Intent intent = new Intent(getActivity(), LogActivity.class);
                startActivity(intent);
            }else if (types==2){
                Intent intent = new Intent(getActivity(), DiseaseActivity.class);
                startActivity(intent);
            }else if (types==3){
                Intent intent = new Intent(getActivity(), RepairActivity.class);
                startActivity(intent);
            }else if (types==4){
                Intent intent = new Intent(getActivity(), HandleActivity.class);
                startActivity(intent);
            }else if (types==5){
                Intent intent = new Intent(getActivity(), CheckActivity.class);
                startActivity(intent);
            }
            return;
        }
    }
    public boolean checkPermissionsOfSDCardAndCamera() {
        if (permissionUtils == null) {
            permissionUtils = PermissionUtils
                    .newInstance(getActivity());
        }
        if (permissionsOfSDCardAndCamera == null) {
            permissionsOfSDCardAndCamera = new ArrayList<String>();
            permissionsOfSDCardAndCamera.add(Manifest.permission.CAMERA);
            permissionsOfSDCardAndCamera.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            permissionsOfSDCardAndCamera.add(Manifest.permission.RECORD_AUDIO);
            permissionsOfSDCardAndCamera.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            permissionsOfSDCardAndCamera.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            permissionsOfSDCardAndCamera.add(Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS);
            permissionsOfSDCardAndCamera.add(Manifest.permission.CHANGE_WIFI_STATE);
        }
        return permissionUtils.requestPermissions(permissionsOfSDCardAndCamera);
    }

    protected void initData() {
        list_path = new ArrayList<>();
        list_path.add(R.drawable.timg);
        xcrzTitle = "巡查日志";
        xcbhTitle = "巡查病害";
        shpfTitle = "审核派发";
        sgwxTitle = "施工维修";
        gcysTitle = "工程验收";
        jjxyhTitle = "季节性养护";
        videoVos2 = JSON.parseArray(MyApplication.app.spUtils.getString("acfl"), CdflInfo.class);
        date = new ArrayList<CdflInfo.ZCDBean>();
        for (int i = 0; i < videoVos2.get(0).getZCD().size(); i++) {
            CdflInfo.ZCDBean zcdBean = videoVos2.get(0).getZCD().get(i);
            String title = zcdBean.getCDMC();
            if (xcrzTitle.equals(title)
                    || xcbhTitle.equals(title)
                    || shpfTitle.equals(title)
                    || sgwxTitle.equals(title)
                    || gcysTitle.equals(title)
                    || jjxyhTitle.equals(title)) {
                date.add(zcdBean);

            }
        }
    }
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load( path).into(imageView);
        }
    }
    private void showLoadingDialogMethod(String str) {
        if (loadDataDialog == null) {
            loadDataDialog = new LoadDataDialog(getActivity());
        }
        loadDataDialog.setTitleStr(str);
        loadDataDialog.show();
    }
}