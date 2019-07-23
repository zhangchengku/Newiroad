package com.zggk.newiroad.season;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
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
import com.zggk.newiroad.Bean.Seasonlistbean;
import com.zggk.newiroad.Bean.seasonbean;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.R;
import com.zggk.newiroad.handle.LoadDataDialog;
import com.zggk.newiroad.mvp.MVPBaseActivity;
import com.zggk.newiroad.season.addtask.AddTaskActivity;
import com.zggk.newiroad.season.seasoncheck.SeasonCheckActivity;
import com.zggk.newiroad.season.seasonhandle.SeasonhandleActivity;
import com.zggk.newiroad.season.seasonrealy.SeasonRealyActivity;
import com.zggk.newiroad.utils.DiseaseNewSelectObjectListener;
import com.zggk.newiroad.utils.Utils;
import com.zggk.newiroad.utils.adapter.CommonAdapter;
import com.zggk.newiroad.utils.adapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SeasonActivity extends MVPBaseActivity<SeasonContract.View, SeasonPresenter> implements SeasonContract.View {


    @Bind(R.id.go_back)
    LinearLayout goBack;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.right)
    TextView right;
    @Bind(R.id.type)
    TextView type;
    @Bind(R.id.bhr)
    RelativeLayout bhr;
    @Bind(R.id.sg)
    TextView sg;
    @Bind(R.id.sgr)
    RelativeLayout sgr;
    @Bind(R.id.lx)
    TextView lx;
    @Bind(R.id.lxr)
    RelativeLayout lxr;
    @Bind(R.id.rr)
    LinearLayout rr;
    @Bind(R.id.rrr)
    LinearLayout rrr;
    @Bind(R.id.activity_disease_list_zhe_zhao_top_filter_layout)
    View activityDiseaseListZheZhaoTopFilterLayout;
    @Bind(R.id.waihandle_rv)
    ListView waihandleRv;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @Bind(R.id.plys)
    TextView plys;
    @Bind(R.id.shang)
    LinearLayout shang;
    private LoadDataDialog loadDataDialog;
    private ArrayList<String> LxData = new ArrayList<>();
    private ArrayList<String> DwData = new ArrayList<>();
    private ArrayList<String> SglxData = new ArrayList<>();
    private String Lxid = "";
    private SeasonpPopwindow LxPop;
    private String Dwid = MyApplication.spUtils.getString("dqgydwid");
    private String sglx = "";
    private String dataid = "0";
    private String action = "0";
    private SeasonpPopwindow DwPop;
    private List<seasonbean.YYLXDATABean> lxbean = new ArrayList<>();
    private List<seasonbean.GYDWBean> dwbean = new ArrayList<>();
    private List<seasonbean.YYSGLXBean> sglxbean = new ArrayList<>();
    private SeasonpPopwindow SglxPop;
    private ArrayList<Seasonlistbean.LISTDATABean> ListDate = new ArrayList<>();
    private CommonAdapter<Seasonlistbean.LISTDATABean> adapter;
    private seasonbean DATEJson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.act_season);
        ButterKnife.bind(this);
        title.setText("季节性养护");
        String str = "正在加载...";
        showLoadingDialogMethod(str);
        initdate();
        initsener();
        initpopw();
        setPullRefresher();
    }

    private void setPullRefresher() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                if (refreshlayout.isLoadmoreFinished()) {
                    refreshlayout.setLoadmoreFinished(false);
                }
                dataid = "0";
                action = "0";
                mPresenter.getlist(Lxid, Dwid, sglx, dataid, action);

            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                action = "1";
                dataid = ListDate.get(ListDate.size() - 1).getGUID_OBJ();
                mPresenter.getlist(Lxid, Dwid, sglx, dataid, action);
            }
        });
    }

    private void initpopw() {
        if (DwPop == null) {
            DwPop = new SeasonpPopwindow(this, DwData, new DiseaseNewSelectObjectListener() {
                @Override
                public void selectPosition(int position) {
                    if (DwData.get(position).equals("所有路线")) {
                        Dwid = "";
                        refreshLayout.autoRefresh();
                    } else {
                        Dwid = dwbean.get(position).getGYDWID();
                        refreshLayout.autoRefresh();
                    }
                    type.setText(DwData.get(position));
                }
            });
        } else {
            DwPop.notifityData();
        }
        if (LxPop == null) {
            LxPop = new SeasonpPopwindow(this, LxData, new DiseaseNewSelectObjectListener() {
                @Override
                public void selectPosition(int position) {
                    if (lxbean.get(position).equals("所有路线")) {
                        Lxid = "";
                        refreshLayout.autoRefresh();
                    } else {
                        Lxid = lxbean.get(position).getLXID();
                        refreshLayout.autoRefresh();
                    }
                    lx.setText(LxData.get(position));
                }
            });
        } else {
            LxPop.notifityData();
        }


        if (SglxPop == null) {
            SglxPop = new SeasonpPopwindow(this, SglxData, new DiseaseNewSelectObjectListener() {
                @Override
                public void selectPosition(int position) {
                    if (sglxbean.get(position).equals("所有路线")) {
                        sglx = "";
                        refreshLayout.autoRefresh();
                    } else {
                        sglx = sglxbean.get(position).getSGLX();
                        refreshLayout.autoRefresh();
                    }
                    lx.setText(SglxData.get(position));
                }
            });
        } else {
            SglxPop.notifityData();
        }
    }
    private void initsener() {
        shang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(SeasonActivity.this,AddTaskActivity.class);
                intent.putExtra("DATE",new Gson().toJson(DATEJson));
                startActivityForResult(intent, 1);
            }
        });
        waihandleRv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (refreshLayout.isRefreshing() || refreshLayout.isLoading()) {
                    Log.e("onItemClick: ", "没加载");
                } else {
                    if (ListDate.get(position).getSTATE().equals("1")){
                        Intent intent  = new Intent(SeasonActivity.this,SeasonhandleActivity.class);
                        intent.putExtra("ID",ListDate.get(position).getGUID_OBJ());
                        startActivityForResult(intent, 1);
                    } if (ListDate.get(position).getSTATE().equals("2")){
                        Intent intent  = new Intent(SeasonActivity.this,SeasonCheckActivity.class);
                        intent.putExtra("ID",ListDate.get(position).getGUID_OBJ());
                        startActivityForResult(intent, 1);
                    } if (ListDate.get(position).getSTATE().equals("3")){
                        Intent intent  = new Intent(SeasonActivity.this,SeasonRealyActivity.class);
                        intent.putExtra("ID",ListDate.get(position).getGUID_OBJ());
                        startActivityForResult(intent, 1);
                    }

                }
            }
        });
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lxr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LxPop.show(rrr);

            }
        });
        bhr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DwPop.show(rrr);

            }
        });
        sgr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SglxPop.show(rrr);
            }
        });
    }

    private void initdate() {
        mPresenter.getbhlxdate();
    }

    private void showLoadingDialogMethod(String str) {
        if (loadDataDialog == null) {
            loadDataDialog = new LoadDataDialog(this);
        }
        loadDataDialog.setTitleStr(str);
        loadDataDialog.show();
    }

    @Override
    public void onRequestError(String msg) {

    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void getData(final seasonbean seasonbean) {
        DATEJson = seasonbean;
        lxbean = seasonbean.getYYLXDATA();
        dwbean = seasonbean.getGYDW();
        sglxbean = seasonbean.getYYSGLX();
        LxData.clear();
        LxData.add("所有路线");
        if (seasonbean.getYYLXDATA().size()>0)        {
            for (int i = 0; i < seasonbean.getYYLXDATA().size(); i++) {
                LxData.add(seasonbean.getYYLXDATA().get(i).getLXMC());
            }
        }

        DwData.clear();
        DwData.add("所有单位");
        if (seasonbean.getGYDW().size()>0)      {
            for (int i = 0; i < seasonbean.getGYDW().size(); i++) {
                DwData.add(seasonbean.getGYDW().get(i).getGYDWMC());
            }
        }

        SglxData.clear();
        SglxData.add("所有类型");
        if (seasonbean.getYYSGLX().size()>0)        {
            for (int i = 0; i < seasonbean.getYYSGLX().size(); i++) {
                SglxData.add(seasonbean.getYYSGLX().get(i).getSGLXMC());
            }
        }

        mPresenter.getlist(Lxid, Dwid, sglx, dataid, action);
    }

    @Override
    public void getData2(Seasonlistbean videoVos2) {
        if (action.equals("1")) {
            ListDate.addAll(videoVos2.getLISTDATA());
            adapter.notifyDataSetChanged();
            refreshLayout.finishLoadmore();
            if (videoVos2.getISALSO().equals("0")) {
                refreshLayout.setLoadmoreFinished(true);
            }
        } else {
            ListDate.clear();
            ListDate.addAll(videoVos2.getLISTDATA());
            setadapter();
            refreshLayout.finishRefresh();
        }
        if (loadDataDialog != null && loadDataDialog.isShowing()) {
            loadDataDialog.cancel();
        }
    }

    private void setadapter() {
        adapter = new CommonAdapter<Seasonlistbean.LISTDATABean>(this,
                R.layout.item_season, ListDate) {
            @Override
            protected void convert(ViewHolder holder, Seasonlistbean.LISTDATABean tubiaoVo, int position) {
                if (tubiaoVo.getSTATE().equals("1")) {
                    holder.setText(R.id.zt, "待施工");
                    holder.setTextColor(R.id.zt, getResources().getColor(R.color.pf_xin_tian_jia_text_color));
                } else if (tubiaoVo.getSTATE().equals("2")) {
                    holder.setText(R.id.zt, "待验收");
                    holder.setTextColor(R.id.zt, getResources().getColor(R.color.pf_xin_bing_hai_text_color));
                } else if (tubiaoVo.getSTATE().equals("3")) {
                    holder.setText(R.id.zt, "已验收");
                    holder.setTextColor(R.id.zt, getResources().getColor(R.color.disease_flag_text_bg_ypf_yys_ywx));
                }
                holder.setText(R.id.lx, tubiaoVo.getLXMC());
                holder.setText(R.id.sglx, tubiaoVo.getSGLXMC());
                holder.setText(R.id.dw, tubiaoVo.getGYDWMC());
                if (position % 2 == 0) {
                    holder.setBackgroundColor(R.id.lay, getResources().getColor(R.color.transparent));
                } else {
                    holder.setBackgroundColor(R.id.lay, getResources().getColor(R.color.direction_btn_bg_color));
                }
            }
        };
        waihandleRv.setAdapter(adapter);
    }

    public void showFilterZheZhaoView() {
        if (!activityDiseaseListZheZhaoTopFilterLayout.isShown()) {
            activityDiseaseListZheZhaoTopFilterLayout.setVisibility(View.VISIBLE);
        }
    }

    public void hideFilterZheZhaoView() {
        if (activityDiseaseListZheZhaoTopFilterLayout.isShown()) {
            activityDiseaseListZheZhaoTopFilterLayout.setVisibility(View.GONE);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2) {
            if (requestCode == 1) {
                ListDate.clear();
                refreshLayout.autoRefresh();
            }
        }
    }
}
