package com.zggk.newiroad.log;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zggk.newiroad.Bean.LogListbean;
import com.zggk.newiroad.Bean.LoglistSbean;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.R;
import com.zggk.newiroad.db.dbhelper.CuringDaoImpl;
import com.zggk.newiroad.db.dbhelper.dbInfo.LogRecordInfo;
import com.zggk.newiroad.log.add.AddActivity;
import com.zggk.newiroad.log.lookblog.LookblogActivity;
import com.zggk.newiroad.log.looklog.LooklogActivity;
import com.zggk.newiroad.mvp.MVPBaseActivity;
import com.zggk.newiroad.utils.Dialog.CommBtnListener;
import com.zggk.newiroad.utils.Dialog.CommNotificationDialog;
import com.zggk.newiroad.utils.PopSelectListener;
import com.zggk.newiroad.utils.Utils;
import com.zggk.newiroad.utils.WheelDateSelectPopupWindow;
import com.zggk.newiroad.utils.adapter.CommonAdapter;
import com.zggk.newiroad.utils.adapter.ViewHolder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class LogActivity extends MVPBaseActivity<LogContract.View, LogPresenter> implements LogContract.View {
    @Bind(R.id.go_back)
    LinearLayout goBack;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.right)
    TextView right;
    @Bind(R.id.last_month_txt)
    TextView lastMonthTxt;
    @Bind(R.id.activity_disease_list_filter_time_start_txt)
    TextView activityDiseaseListFilterTimeStartTxt;
    @Bind(R.id.activity_disease_list_filter_time_end_txt)
    TextView activityDiseaseListFilterTimeEndTxt;
    @Bind(R.id.activity_disease_list_filter_time_layout)
    LinearLayout activityDiseaseListFilterTimeLayout;
    @Bind(R.id.next_month_txt)
    TextView nextMonthTxt;
    @Bind(R.id.month_filter_layout)
    RelativeLayout monthFilterLayout;
    @Bind(R.id.vvv)
    View vvv;
    @Bind(R.id.type)
    TextView type;
    @Bind(R.id.log_bg)
    LinearLayout logBg;
    @Bind(R.id.logrecycle)
    ListView logrecycle;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @Bind(R.id.shangchuan)
    TextView shangchuan;
    @Bind(R.id.add_log)
    TextView addLog;
    @Bind(R.id.ddd)
    LinearLayout ddd;
    @Bind(R.id.my)
    RelativeLayout my;
    private Gson gson = new Gson();
    private String STIME;
    private String ETIME;
    private String DATAID;
    private String ACTION;
    private String PAGESIZE;
    private CommonAdapter<LoglistSbean> recyadapter;
    private List<LoglistSbean> XCRZDATA = new ArrayList<>();
    private List<LoglistSbean> datess = new ArrayList<>();
    private int page = 1;
    private CuringDaoImpl curingDao;
    private List<LogRecordInfo> bendidates;
    private List<LoglistSbean> dateslist3 = new ArrayList<>();
    private SimpleDateFormat simpleDateFormatResult = new SimpleDateFormat("yyyy/MM/dd");
    private WheelDateSelectPopupWindow popupWindow;
    private CommNotificationDialog commitWarmDialog;
    private String gydwid = MyApplication.app.spUtils.getString("dqgydwid", "");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        Utils.setStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.act_log);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        curingDao = new CuringDaoImpl(this);
        title.setText("巡查日志");
        STIME = Utils.thisMonth();
        ETIME = Utils.thisMonthEnd();
        activityDiseaseListFilterTimeStartTxt.setText(STIME);
        activityDiseaseListFilterTimeEndTxt.setText(ETIME);
        setadapter();
        setPullRefresher();
        refreshLayout.autoRefresh();
        right.setVisibility(View.VISIBLE);
        right.setText(curingDao.queryGydwById(gydwid).getGYDWMC());
        initener();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RESULT_OK，判断另外一个activity已经结束数据输入功能，Standard activity result:
        // operation succeeded. 默认值是-1
        if (resultCode == 2) {
            if (requestCode == 1) {
                refreshLayout.autoRefresh();
            } else if (requestCode == 2) {
                right.setText(data.getStringExtra("DW"));
                gydwid = data.getStringExtra("ID");
                refreshLayout.autoRefresh();
            }
        }
    }

    private void initdate() {
        dateslist3.clear();
        datess.clear();
        XCRZDATA.clear();
        recyadapter.notifyDataSetChanged();
        DATAID = "0";
        ACTION = "0";
        PAGESIZE = "20";
        bendidates = curingDao.queryAllRzjl();
        for (int i = bendidates.size(); i > 0; i--) {
            LoglistSbean dates = new LoglistSbean();
            dates.setGYDW(bendidates.get(i - 1).getGYDWMC());
            dates.setXCSJ(bendidates.get(i - 1).getJLSJ() );
            dates.setRZID(bendidates.get(i - 1).getRZID() + "");
            dates.setType(0);
            dateslist3.add(dates);
        }
        if (Utils.isNetworkAvailable(this) == false) {//没网
            XCRZDATA.addAll(dateslist3);
            logrecycle.setAdapter(recyadapter);
        } else {//有网
            page = 1;
            mPresenter.testinfo(gydwid, STIME, ETIME, DATAID, ACTION, PAGESIZE);
            logrecycle.setAdapter(recyadapter);
        }
    }

    private void setPullRefresher() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                initdate();
                refreshLayout.finishRefresh(1000);
                refreshlayout.finishRefresh(2000);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page = 2;
                ACTION = "1";
                DATAID = XCRZDATA.get(XCRZDATA.size() - 1).getRZID();
                mPresenter.testinfo(gydwid, STIME, ETIME, DATAID, ACTION, PAGESIZE);
                refreshLayout.finishLoadmore(1000);
                refreshlayout.finishLoadmore(2000);
            }
        });
    }

    @Override
    public void getData(LogListbean videoVos2) {
        if (page == 2) {
            for (int i = 0; i < videoVos2.getRZDATA().size(); i++) {
                LoglistSbean datess2 = new LoglistSbean();
                datess2.setGYDW(videoVos2.getRZDATA().get(i).getGYDW());
                datess2.setXCSJ(videoVos2.getRZDATA().get(i).getXCSJ());
                datess2.setRZID(videoVos2.getRZDATA().get(i).getRZID());
                datess2.setType(1);
                XCRZDATA.add(datess2);
                ;
            }

            logrecycle.setAdapter(recyadapter);
            String isalso = videoVos2.getISALSO();
            if (!Utils.isNull(isalso)) {
                if ("0".equals(isalso)) {
                    refreshLayout.setEnableLoadmore(false);
                    refreshLayout.setEnableAutoLoadmore(false);
                } else if ("1".equals(isalso)) {
                    refreshLayout.setEnableLoadmore(true);
                    refreshLayout.setEnableAutoLoadmore(true);
                }
            }
        } else {
            refreshLayout.setEnableLoadmore(true);
            refreshLayout.setEnableAutoLoadmore(true);
            XCRZDATA.clear();
            for (int i = 0; i < videoVos2.getRZDATA().size(); i++) {
                LoglistSbean dates = new LoglistSbean();
                dates.setGYDW(videoVos2.getRZDATA().get(i).getGYDW());
                dates.setXCSJ(videoVos2.getRZDATA().get(i).getXCSJ());
                dates.setRZID(videoVos2.getRZDATA().get(i).getRZID());
                dates.setType(1);
                datess.add(dates);
            }
            XCRZDATA.addAll(dateslist3);
            XCRZDATA.addAll(datess);
            logrecycle.setAdapter(recyadapter);
        }
        closeRefreshOrLoadData();

    }

    private void closeRefreshOrLoadData() {
        if ("0".equals(ACTION)) {
            refreshLayout.finishRefresh();
        } else {
            refreshLayout.finishLoadmore();
        }
    }


    private void setadapter() {
        recyadapter = new CommonAdapter<LoglistSbean>(this,
                R.layout.item_log, XCRZDATA) {
            @Override
            protected void convert(ViewHolder holder, LoglistSbean tubiaoVo, int position) {
                holder.setText(R.id.time, tubiaoVo.getXCSJ());
                holder.setText(R.id.gygs, tubiaoVo.getGYDW());
                if (tubiaoVo.getType() == 0) {
                    holder.setText(R.id.type, "未上传");
                    holder.setTextColor(R.id.type, Color.parseColor("#39a9ff"));
                } else if (tubiaoVo.getType() == 1) {
                    holder.setText(R.id.type, "已上传");
                    holder.setTextColor(R.id.type, Color.parseColor("#2cb303"));
                }
            }
        };
    }

    @Override
    public void onRequestError(String msg) {
    }

    @Override
    public void onRequestEnd() {
    }

    private void initener() {
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
//        right.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(LogActivity.this, OrganizationActivity.class);
//                startActivityForResult(intent,2);
//            }
//        });
        addLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(LogActivity.this, AddActivity.class), 1);
            }
        });
        shangchuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isNetworkAvailable(LogActivity.this) == false) {//没网
                    MyApplication.app.customToast("您没有要上传的数据");
                } else {//有网
                    showCommitWarmDialog();
                }
            }
        });
        logrecycle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (refreshLayout.isRefreshing() || refreshLayout.isLoading()) {

                } else {
                    if (XCRZDATA.get(position).getType() == 0) {//本地
                        Intent intent = new Intent(LogActivity.this, LooklogActivity.class);
                        intent.putExtra("RZID", String.valueOf(XCRZDATA.get(position).getRZID()));
                        startActivityForResult(intent, 1);
                    } else if (XCRZDATA.get(position).getType() == 1) {//网络
                        Intent intent = new Intent(LogActivity.this, LookblogActivity.class);
                        intent.putExtra("RZID", String.valueOf(XCRZDATA.get(position).getRZID()));
                        startActivity(intent);
                    }
                }
            }
        });
        nextMonthTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String endDateStr = activityDiseaseListFilterTimeEndTxt.getText().toString().replace("-", "/");
                Calendar calendar = Calendar.getInstance();
                try {
                    calendar.setTime(simpleDateFormatResult.parse(endDateStr));
                    calendar.add(Calendar.MONTH, 1);
                    calendar.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
                    String startDateStr = simpleDateFormatResult.format(calendar.getTime());
                    calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                    endDateStr = simpleDateFormatResult.format(calendar.getTime());
                    activityDiseaseListFilterTimeStartTxt.setText(startDateStr);
                    activityDiseaseListFilterTimeEndTxt.setText(endDateStr);
                    STIME = startDateStr.replace("/", "-");
                    ETIME = endDateStr.replace("/", "-");
                    datess.clear();
                    dateslist3.clear();
                    XCRZDATA.clear();
                    initdate();
                    refreshLayout.autoRefresh();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        popupWindow = new WheelDateSelectPopupWindow(LogActivity.this, 1, new PopSelectListener() {
            @Override
            public void selectResult(Object... result) {
                String startData = result[0].toString();
                String endData = result[1].toString();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                try {
                    Date dateStart = simpleDateFormat.parse(startData);
                    Date dateEnd = simpleDateFormat.parse(endData);
                    startData = simpleDateFormatResult.format(dateStart);
                    endData = simpleDateFormatResult.format(dateEnd);
                    activityDiseaseListFilterTimeStartTxt.setText(startData);
                    activityDiseaseListFilterTimeEndTxt.setText(endData);
                    STIME = startData.replace("/", "-");
                    ETIME = endData.replace("/", "-");
                    dateslist3.clear();
                    datess.clear();
                    XCRZDATA.clear();
                    initdate();
                    refreshLayout.autoRefresh();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        activityDiseaseListFilterTimeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.show(my);
            }
        });
        lastMonthTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String startDateStr = activityDiseaseListFilterTimeStartTxt.getText().toString().replace("-", "/");
                Calendar calendar = Calendar.getInstance();
                try {
                    calendar.setTime(simpleDateFormatResult.parse(startDateStr));
                    calendar.add(Calendar.MONTH, -1);
                    calendar.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
                    startDateStr = simpleDateFormatResult.format(calendar.getTime());
                    calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                    String endDateStr = simpleDateFormatResult.format(calendar.getTime());
                    activityDiseaseListFilterTimeStartTxt.setText(startDateStr);
                    activityDiseaseListFilterTimeEndTxt.setText(endDateStr);
                    STIME = startDateStr.replace("/", "-");
                    ETIME = endDateStr.replace("/", "-");
                    dateslist3.clear();
                    datess.clear();
                    XCRZDATA.clear();
                    initdate();
                    refreshLayout.autoRefresh();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 显示立即上传提示对话框
     */
    private void showCommitWarmDialog() {
        if (bendidates.size() != 0) {
            String title = getString(R.string.log_update_warm_title);
            String titleStr = String.format(title, String.valueOf(bendidates.size()));
            String cancelStr = "取消";
            String okStr = "确认";
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

    }

    private void comitListDataMethod() {

        UploadlogDataDialog uploadWaihandleDataDialog = new UploadlogDataDialog(this, bendidates);
        uploadWaihandleDataDialog.show();

    }

    /**
     * 刷新数据
     */
    public void refreshDataMethod() {
        MyApplication.app.customToast("提交日志成功");
        dateslist3.clear();
        refreshLayout.autoRefresh();
    }
}
