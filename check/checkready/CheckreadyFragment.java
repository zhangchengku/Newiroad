package com.zggk.newiroad.check.checkready;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zggk.newiroad.Bean.SgRInfo;
import com.zggk.newiroad.Bean.checkwaibean;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.R;
import com.zggk.newiroad.check.CheckActivity;
import com.zggk.newiroad.check.CheckPopu;
import com.zggk.newiroad.check.Checkadapter;
import com.zggk.newiroad.check.checkreadyxq.CheckreadyxqActivity;
import com.zggk.newiroad.check.checkxq.CheckxqActivity;
import com.zggk.newiroad.handle.LoadDataDialog;
import com.zggk.newiroad.mvp.MVPBaseFragment;
import com.zggk.newiroad.utils.Dialog.CommBtnListener;
import com.zggk.newiroad.utils.Dialog.CommNotificationDialog;
import com.zggk.newiroad.utils.DiseaseNewSelectObjectListener;
import com.zggk.newiroad.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class CheckreadyFragment extends MVPBaseFragment<CheckreadyContract.View, CheckreadyPresenter> implements CheckreadyContract.View {

    @Bind(R.id.quanx)
    CheckBox quanx;
    @Bind(R.id.lx)
    TextView lx;
    @Bind(R.id.lxr)
    RelativeLayout lxr;
    @Bind(R.id.type)
    TextView type;
    @Bind(R.id.bhr)
    RelativeLayout bhr;
    @Bind(R.id.rr)
    LinearLayout rr;
    @Bind(R.id.waihandle_rv)
    ListView waihandleRv;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private LoadDataDialog loadDataDialog;
    private int page;
    private String DATATAID;
    private String ACTION;
    private String PAGESIZE = "20";
    private String STIME = Utils.thisMonth();
    private String ETIME = Utils.thisMonthEnd();
    private String LXID = "";
    private String BHID = "";
    private String GYDWID = MyApplication.app.spUtils.getString("dqgydwid", "");
    private List<checkwaibean.BHLISTBean> checkwaidate = new ArrayList<>();
    private List<checkwaibean.BHLISTBean> updates = new ArrayList<>();
    private Checkadapter checkadapter;
    private CommNotificationDialog commitWarmDialog;
    private ArrayList<String> listLxData = new ArrayList<>();
    private ArrayList<String> listbhData = new ArrayList<>();
    private ArrayList<SgRInfo.YBLXDATABean> listLxInfoData = new ArrayList<>();
    private ArrayList<SgRInfo.YBBHLXDATABean> listbhInfoData = new ArrayList<>();
    private CheckPopu lxFilterPop;
    private CheckPopu bhFilterPop;
    private SgRInfo videoVo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_checkready, container, false);
        ButterKnife.bind(this, view);
        initener();
        initpopu();
        setPullRefresher();
        initdate();
        return view;
    }
    private void initpopu() {
        listLxData.clear();
        listLxInfoData.clear();
        listLxData.add("所有路线");
        SgRInfo.YBLXDATABean lxlistBean = new SgRInfo.YBLXDATABean();
        lxlistBean.setLXMC("所有路线");
        lxlistBean.setLXID("");
        listLxInfoData.add(lxlistBean);
        if (videoVo.getYBLXDATA().size()>0){
            for (int i = 0; i < videoVo.getYBLXDATA().size(); i++) {
                if (!Utils.isNull(videoVo.getYBLXDATA().get(i).getLXMC()) && !Utils.isNull(videoVo.getYBLXDATA().get(i).getLXID())) {
                    listLxData.add(videoVo.getYBLXDATA().get(i).getLXMC());
                    listLxInfoData.add(videoVo.getYBLXDATA().get(i));
                }
            }
        }
        if (lxFilterPop == null) {
            lxFilterPop = new CheckPopu(getActivity(), listLxData, new DiseaseNewSelectObjectListener() {
                @Override
                public void selectPosition(int position) {
                    if (listLxData.equals("所有路线")) {
                        checkwaidate.clear();
                        LXID = "";
                        refreshLayout.autoRefresh();
                    } else {
                        checkwaidate.clear();
                        LXID = listLxInfoData.get(position).getLXID();
                        refreshLayout.autoRefresh();
                    }
                    lx.setText(listLxData.get(position));

                }
            });
        } else {
            lxFilterPop.notifityData();
        }
        listbhData.clear();
        listbhInfoData.clear();
        listbhData.add("所有病害");
        SgRInfo.YBBHLXDATABean bhlistBean = new SgRInfo.YBBHLXDATABean();
        bhlistBean.setBHLX("所有病害");
        bhlistBean.setBHLXID("");
        listbhInfoData.add(bhlistBean);
        if (videoVo.getYBBHLXDATA().size()>0){
            for (int i = 0; i < videoVo.getYBBHLXDATA().size(); i++) {
                if (!Utils.isNull(videoVo.getYBBHLXDATA().get(i).getBHLXID()) && !Utils.isNull(videoVo.getYBBHLXDATA().get(i).getBHLX())) {
                    listbhData.add(videoVo.getYBBHLXDATA().get(i).getBHLX());
                    listbhInfoData.add(videoVo.getYBBHLXDATA().get(i));
                }
            }
        }
        if (bhFilterPop == null) {
            bhFilterPop = new CheckPopu(getActivity(), listbhData, new DiseaseNewSelectObjectListener() {
                @Override
                public void selectPosition(int position) {
                    if (listbhData.equals("所有病害")) {
                        checkwaidate.clear();
                        BHID = "";
                        refreshLayout.autoRefresh();
                    } else {
                        checkwaidate.clear();
                        BHID = listbhInfoData.get(position).getBHLXID();
                        refreshLayout.autoRefresh();
                    }
                    type.setText(listbhData.get(position));

                }
            });
        } else {
            bhFilterPop.notifityData();
        }
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        videoVo = ((CheckActivity) activity).getSxDate();
    }
    private void initener() {
        lxr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lxFilterPop.show(rr);
            }
        });
        bhr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bhFilterPop.show(rr);
            }
        });
        waihandleRv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getActivity(), CheckreadyxqActivity.class);
                intent.putExtra("BHID", String.valueOf(checkwaidate.get(position).getBHID()));
                startActivityForResult(intent, 1);
            }
        });

    }


    private void initdate() {
        String str = "正在加载";
        showLoadingDialogMethod(str);
        if (refreshLayout.isLoadmoreFinished()) {
            refreshLayout.setLoadmoreFinished(false);
        }
        page = 1;
        checkwaidate.clear();
        DATATAID = "0";
        ACTION = "0";
        mPresenter.getlist(LXID, GYDWID, BHID, STIME, ETIME, DATATAID, ACTION, PAGESIZE);
    }


    private void setPullRefresher() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                if (refreshlayout.isLoadmoreFinished()) {
                    refreshlayout.setLoadmoreFinished(false);
                }
                page = 1;
                checkwaidate.clear();
                DATATAID = "0";
                ACTION = "0";
                mPresenter.getlist(LXID, GYDWID, BHID, STIME, ETIME, DATATAID, ACTION, PAGESIZE);

            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page = 2;
                ACTION = "1";
                DATATAID = checkwaidate.get(checkwaidate.size() - 1).getBHID();
                mPresenter.getlist(LXID, GYDWID, BHID, STIME, ETIME, DATATAID, ACTION, PAGESIZE);
            }
        });
    }


    @Override
    public void getData(checkwaibean videoVos2) {
        if (page == 2) {
            checkwaidate.addAll(videoVos2.getBHLIST());
            checkadapter.notifyDataSetChanged();
            refreshLayout.finishLoadmore();
            if (videoVos2.getISALSO().equals("0")) {
                refreshLayout.setLoadmoreFinished(true);
            }
        } else {
            checkwaidate = videoVos2.getBHLIST();
            checkadapter = new Checkadapter(getActivity(), checkwaidate);
            waihandleRv.setAdapter(checkadapter);
            refreshLayout.finishRefresh();
        }
        if (loadDataDialog != null && loadDataDialog.isShowing()) {
            loadDataDialog.cancel();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 显示对话框
     */
    private void showLoadingDialogMethod(String str) {
        if (loadDataDialog == null) {
            loadDataDialog = new LoadDataDialog(getActivity());
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
}

