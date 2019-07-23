package com.zggk.newiroad.disease;


import com.zggk.newiroad.Bean.DiseaseListbean;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.R;

import com.zggk.newiroad.db.dbhelper.CuringDao;
import com.zggk.newiroad.db.dbhelper.CuringDaoImpl;
import com.zggk.newiroad.db.dbhelper.dbInfo.DiseaseBaseInfo;
import com.zggk.newiroad.disease.lookdiseasedb.LookdiseasedbActivity;
import com.zggk.newiroad.disease.newdisease.NewdiseaseActivity;
import com.zggk.newiroad.mvp.MVPBaseActivity;
import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zggk.newiroad.thread.LoadDataDialog;
import com.zggk.newiroad.utils.Dialog.CommBtnListener;
import com.zggk.newiroad.utils.Dialog.CommNotificationDialog;
import com.zggk.newiroad.utils.PermissionUtils;
import com.zggk.newiroad.utils.PopSelectListener;
import com.zggk.newiroad.utils.Utils;
import com.zggk.newiroad.utils.WheelDateSelectPopupWindow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class DiseaseActivity extends MVPBaseActivity<DiseaseContract.View, DiseasePresenter> implements DiseaseContract.View {
    /**********************
     * 控件声明
     ************************/
    private LinearLayout backLayout;
    private TextView vLastMonthTxt;//上个月
    private TextView vNextMonthTxt;//下个月
    private LinearLayout vSelectMonthLayout;//当前选择的筛选布局
    private TextView vSelectMonthStartTxt;//当前选择的筛选开始时间
    private TextView vSelectMonthEndTxt;//当前选择的筛选结束时间
    private ListView listView;//列表listview
    private TextView vUpdateNowTxt;//立即上传按钮
    private TextView newDiseaseTxt;//添加新病害按钮
    private RelativeLayout mainLayout;//父布局
    private View vZheZhaoView;//有popupwindow显示时遮罩view
    private SmartRefreshLayout refreshLayout;//下拉刷新控件
    private View vFilterZheZhao;//筛选时显示的遮罩层
    /**********************
     * 变量声明
     ************************/
    private WheelDateSelectPopupWindow popupWindow;
    private DiseaseAdapter adapter;
    private ArrayList<DiseaseBaseInfo> listDataLocal = new ArrayList<>();
    private ArrayList<DiseaseListbean.BHLISTBean> listDataNetWork = new ArrayList<>();
    private PermissionUtils permissionUtils;//权限管理类
    private List<String> permissionsOfSDCardAndCamera;//读写sd卡权限 摄像头权限 位置权限
    private SimpleDateFormat simpleDateFormatResult = new SimpleDateFormat("yyyy/MM/dd");
    private CuringDao curingDao;
    private Gson gson = new Gson();
    private String gydwmc = "";//管养单位名称
    private String gydwid = "";//管养单位id
    private String lxid = "";//路线id
    private String bhid = "";//病害id
    private String stime = "";//开始时间
    private String etime = "";//截止时间
    private String dataid = "0";//最后一条数据条目id
    private String action = "0";//翻页动作 0:下拉  1:上滑
    private String pagesize = "10";//显示条目数
    private LoadDataDialog loadDataDialog;//加载等待对话框

    private CommNotificationDialog commitWarmDialog;//立即上传提示对话框
    private Date dateStart;//当前选择的开始日期
    private Date dateEnd;//当前选择的结束日期
    private String xcrqdate;//巡查日期  从日志的查看今天病害跳转过来

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.act_dis);
        initView();
        setListener();
        loadData();
    }

    /**
     * 实例化控件
     */
    private void initView() {
        curingDao = new CuringDaoImpl(this);
        gydwid = MyApplication.spUtils.getString("dqgydwid");
        gydwmc = MyApplication.spUtils.getString("dqgydwmc");
        xcrqdate = getIntent().getStringExtra("xcrqdate");
        TextView text = (TextView) findViewById(R.id.title);
        text.setText("采集病害");
        backLayout = (LinearLayout) findViewById(R.id.go_back);
        vLastMonthTxt = (TextView) findViewById(R.id.last_month_txt);
        vSelectMonthLayout = (LinearLayout) findViewById(R.id.activity_disease_list_filter_time_layout);
        vSelectMonthStartTxt = (TextView) findViewById(R.id.activity_disease_list_filter_time_start_txt);
        vSelectMonthEndTxt = (TextView) findViewById(R.id.activity_disease_list_filter_time_end_txt);
        vNextMonthTxt = (TextView) findViewById(R.id.next_month_txt);
        vUpdateNowTxt = (TextView) findViewById(R.id.activity_disease_list_ljsc_txt);
        newDiseaseTxt = (TextView) findViewById(R.id.activity_disease_list_bhcj_txt);
        mainLayout = (RelativeLayout) findViewById(R.id.activity_disease_list_parent_layout);
        vZheZhaoView = findViewById(R.id.activity_disease_list_zhe_zhao_layout);
        vFilterZheZhao = findViewById(R.id.activity_disease_list_zhe_zhao_top_filter_layout);
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        listView = (ListView) findViewById(R.id.disease_list_view);
        initPopWindow();
        Calendar calendar = Calendar.getInstance();
        if (!Utils.isNull(xcrqdate)) {
            Log.i("巡查日期",xcrqdate);
            String xcDateStr=xcrqdate.split(" ")[0].replace("-","/");
            try {
                Date xcDate=simpleDateFormatResult.parse(xcDateStr);
                dateStart = xcDate;
                dateEnd = xcDate;
                if(popupWindow!=null){
                    SimpleDateFormat format=new SimpleDateFormat("yyyy年MM月dd日");
                    String initDateStr=format.format(xcDate);
                    popupWindow.setInitStartAndEndDate(initDateStr,initDateStr);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            vSelectMonthStartTxt.setText(xcDateStr);
            vSelectMonthEndTxt.setText(xcDateStr);
        } else {
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            dateStart = calendar.getTime();
            String dateStartStr = simpleDateFormatResult.format(dateStart);
            vSelectMonthStartTxt.setText(dateStartStr);
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            dateEnd = calendar.getTime();
            String dateEndStr = simpleDateFormatResult.format(dateEnd);
            vSelectMonthEndTxt.setText(dateEndStr);
        }

        adapter = new DiseaseAdapter(this, listDataLocal, listDataNetWork);
        listView.setAdapter(adapter);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                loadRefreshData();
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                loadMoreData();
            }
        });
    }

    /**
     * 第一次进入加载数据的方法
     */
    private void loadData() {
        stime = vSelectMonthStartTxt.getText().toString().replace("/", "-");
        etime = vSelectMonthEndTxt.getText().toString().replace("/", "-");
        String str = "正在加载";
        showLoadingDialogMethod(str);
        queryDiseaseData();
        callDataNetWork();
    }

    /**
     * 调用接口
     */
    private void callDataNetWork() {
        mPresenter.testinfo(gydwid, stime, etime, dataid,action, pagesize);
    }

    /**
     * 显示对话框
     */
    private void showLoadingDialogMethod(String str) {
        if (loadDataDialog == null) {
            loadDataDialog = new LoadDataDialog(this);
        }
        loadDataDialog.setTitleStr(str);
        loadDataDialog.show();
    }

    /**
     * 设置按钮监听
     */
    private void setListener() {
        //返回按钮
        backLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
//                Intent intent =new Intent(DiseaseListActivity.this, MainActivity.class);
//                startActivity(intent);
            }
        });
        //时间选择
        vSelectMonthLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.show(mainLayout);
            }
        });

        //上一个月
        vLastMonthTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (refreshLayout.isRefreshing() || refreshLayout.isLoading()) {
                    MyApplication.app.customToast("等待数据加载完成再进行操作");
                } else {
                    String startDateStr = vSelectMonthStartTxt.getText().toString();
                    Calendar calendar = Calendar.getInstance();
                    try {
                        calendar.setTime(simpleDateFormatResult.parse(startDateStr));
                        calendar.add(Calendar.MONTH, -1);
                        calendar.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
                        dateStart = calendar.getTime();
                        startDateStr = simpleDateFormatResult.format(dateStart);
                        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                        dateEnd = calendar.getTime();
                        String endDateStr = simpleDateFormatResult.format(dateEnd);
                        vSelectMonthStartTxt.setText(startDateStr);
                        vSelectMonthEndTxt.setText(endDateStr);
                        refreshDataMethod();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //下一个月
        vNextMonthTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (refreshLayout.isRefreshing() || refreshLayout.isLoading()) {
                    MyApplication.app.customToast("等待数据加载完成再进行操作");
                } else {
                    String endDateStr = vSelectMonthEndTxt.getText().toString();
                    Calendar calendar = Calendar.getInstance();
                    try {
                        calendar.setTime(simpleDateFormatResult.parse(endDateStr));
                        calendar.add(Calendar.MONTH, 1);
                        calendar.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
                        dateStart = calendar.getTime();
                        String startDateStr = simpleDateFormatResult.format(dateStart);
                        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                        dateEnd = calendar.getTime();
                        endDateStr = simpleDateFormatResult.format(dateEnd);
                        vSelectMonthStartTxt.setText(startDateStr);
                        vSelectMonthEndTxt.setText(endDateStr);
                        refreshDataMethod();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //立即上传
        vUpdateNowTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Utils.isNetworkAvailable(DiseaseActivity.this)) {
                    MyApplication.app.customToast("当前没有数据");
                } else {
                    if (listDataLocal != null && listDataLocal.size() > 0) {
//                        comitListDataMethod();
                        showCommitWarmDialog();

                    } else {
                        MyApplication.app.customToast("暂时没有待上传数据");
                    }
                }
            }
        });

        //新增病害采集按钮
        newDiseaseTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkPermissionsOfSDCardAndCamera()) {
                    goToDiseaseNewActivity();
                }
            }
        });
    }

    /**
     * 进入新增病害页面
     */
    private void goToDiseaseNewActivity() {
        Intent intent = new Intent(DiseaseActivity.this, NewdiseaseActivity.class);
        startActivity(intent);
    }

    /**
     * 实例化时间选择器
     */
    private void initPopWindow() {
        popupWindow = new WheelDateSelectPopupWindow(this, 1, new PopSelectListener() {
            @Override
            public void selectResult(Object... result) {
                String startData = result[0].toString();
                String endData = result[1].toString();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                try {
                    dateStart = simpleDateFormat.parse(startData);
                    dateEnd = simpleDateFormat.parse(endData);
                    startData = simpleDateFormatResult.format(dateStart);
                    endData = simpleDateFormatResult.format(dateEnd);
                    vSelectMonthStartTxt.setText(startData);
                    vSelectMonthEndTxt.setText(endData);
                    refreshDataMethod();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 查询数据库中的病害信息
     */
    private void queryDiseaseData() {
        listDataLocal.clear();
        List<DiseaseBaseInfo> listBase;
        long stimeLong = dateStart.getTime();
        long etimeLong = dateEnd.getTime();
        listBase = curingDao.queryAllBingHaiBase(gydwid);
        if (listBase != null) {
            listDataLocal.addAll(listBase);
        }
    }
    /**
     * 下拉刷新数据
     */
    private void loadRefreshData() {
        stime = vSelectMonthStartTxt.getText().toString().replace("/", "-");
        etime = vSelectMonthEndTxt.getText().toString().replace("/", "-");
        refreshLayout.setLoadmoreFinished(false);
        action = "0";
        dataid = "0";
        queryDiseaseData();
        callDataNetWork();
    }

    /**
     * 上拉加载更多数据
     */
    private void loadMoreData() {
        action = "1";
        if (listDataNetWork != null && listDataNetWork.size() > 0) {
            dataid = listDataNetWork.get(listDataNetWork.size() - 1).getBHID();
        }
        queryDiseaseData();
        callDataNetWork();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // “读写SD卡”的权限结果处理
        if (permissionUtils != null && permissionsOfSDCardAndCamera != null && permissionUtils.dealRequestPermissionsResult(
                permissionsOfSDCardAndCamera, requestCode, permissions, grantResults)) {
            permissionsOfSDCardAndCamera = null;
            goToDiseaseNewActivity();
            return;
        }
    }

    /**
     * 对“读写SD卡”权限的检测
     *
     * @return true：需要请求权限；false：无需请求权限
     */
    public boolean checkPermissionsOfSDCardAndCamera() {
        if (permissionUtils == null) {
            permissionUtils = PermissionUtils
                    .newInstance(this);
        }
        if (permissionsOfSDCardAndCamera == null) {
            permissionsOfSDCardAndCamera = new ArrayList<String>();
            permissionsOfSDCardAndCamera
                    .add(Manifest.permission.CAMERA);
            permissionsOfSDCardAndCamera
                    .add(Manifest.permission.READ_EXTERNAL_STORAGE);
            permissionsOfSDCardAndCamera.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            permissionsOfSDCardAndCamera.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            permissionsOfSDCardAndCamera.add(Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS);
            permissionsOfSDCardAndCamera.add(Manifest.permission.CHANGE_WIFI_STATE);
        }
        return permissionUtils.requestPermissions(permissionsOfSDCardAndCamera);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (NewdiseaseActivity.isSaveOrUpdateData|| LookdiseasedbActivity.isSaveOrUpdateData) {
            NewdiseaseActivity.isSaveOrUpdateData = false;
            refreshDataMethod();
        }
    }

    /**
     * 刷新数据
     */
    public void refreshDataMethod() {
        refreshLayout.setVisibility(View.VISIBLE);
        refreshLayout.autoRefresh();
    }

    /**
     * 显示遮罩view
     */
    public void showZheZhaoView() {
        if (!vZheZhaoView.isShown()) {
            vZheZhaoView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 隐藏遮罩view
     */
    public void hideZheZhaoView() {
        if (vZheZhaoView.isShown()) {
            vZheZhaoView.setVisibility(View.GONE);
        }
    }

    /**
     * 显示筛选遮罩view
     */
    public void showFilterZheZhaoView() {
        if (!vFilterZheZhao.isShown()) {
            vFilterZheZhao.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 隐藏筛选遮罩view
     */
    public void hideFilterZheZhaoView() {
        if (vFilterZheZhao.isShown()) {
            vFilterZheZhao.setVisibility(View.GONE);
        }
    }



    /**
     * 刷新或加载更多数据关闭方法
     */
    private void closeRefreshOrLoadData() {
        if ("0".equals(action)) {
            refreshLayout.finishRefresh();
        } else {
            refreshLayout.finishLoadmore();
        }
    }

    /**
     * 上传待上传的数据
     */
    private void comitListDataMethod() {
        String str = "正在手机待上传数据";
        showLoadingDialogMethod(str);
//        Map<Integer, List<DiseaseInfo>> diseaseInfoMap = new HashMap<>();
//        for (int i = 0; i < listDataLocal.size(); i++) {
//            DiseaseBaseInfo baseInfo = listDataLocal.get(i);
//            if (baseInfo != null) {
//                List<DiseaseInfo> listInfo = curingDao.queryBingHaiByBhbaseId(baseInfo.getBHJBID());
//                if (listInfo != null && listInfo.size() > 0) {
//                    diseaseInfoMap.put(i, listInfo);
//                }
//            }
//        }
        if (loadDataDialog != null && loadDataDialog.isShowing()) {
            loadDataDialog.cancel();
        }
        UploadDiseaseDataDialog uploadDiseaseDataDialog = new UploadDiseaseDataDialog(this, listDataLocal);
        uploadDiseaseDataDialog.show();
    }

    /**
     * 显示立即上传提示对话框
     */
    private void showCommitWarmDialog() {
        String title = "当前有%s条病害数据需要上传，请问是否立即上传？";
        String titleStr = String.format(title, String.valueOf(listDataLocal.size()));
        String cancelStr = "取消";
        String okStr = "立即上传";
        if (commitWarmDialog == null) {
            commitWarmDialog = new CommNotificationDialog(this, titleStr, okStr, cancelStr, new CommBtnListener() {
                @Override
                public void CommOkBtnClick() {
                    comitListDataMethod();
                }

                @Override
                public void CommCancelBtnClick() {

                }
            });
        }
        commitWarmDialog.setWarmTitle(titleStr);
        commitWarmDialog.show();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        curingDao.release();
    }

    @Override
    public void getData(DiseaseListbean videoVos2) {
            if (videoVos2 != null) {
                if (videoVos2.getBHLIST() != null) {
                    if ("0".equals(action)) {
                        listDataNetWork.clear();
                    }
                    listDataNetWork.addAll(videoVos2.getBHLIST());
                    if (videoVos2.getBHLIST().size() == 0 && listDataLocal.size() == 0) {
                        refreshLayout.setVisibility(View.GONE);
                    }
                }
                String isalso = videoVos2.getISALSO();
                if (!Utils.isNull(isalso)) {
                    if ("0".equals(isalso)) {
                        refreshLayout.setLoadmoreFinished(true);
                    }else{
                        refreshLayout.setLoadmoreFinished(false);
                    }
                }
            } else {
                if ("0".equals(action)) {
                    listDataNetWork.clear();
                    if(listDataLocal.size()==0){
                        MyApplication.app.customToast("当前没有网络");
                    }
                }
            }
            if (loadDataDialog != null && loadDataDialog.isShowing()) {
                loadDataDialog.cancel();
            }
            adapter.notifyDataSetChanged();
            closeRefreshOrLoadData();

    }

    @Override
    public void onRequestError(String msg) {

    }

    @Override
    public void onRequestEnd() {

    }
}
