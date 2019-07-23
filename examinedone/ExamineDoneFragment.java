package com.zggk.newiroad.examinedone;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zggk.newiroad.Bean.ToExamineDataInfo;
import com.zggk.newiroad.Bean.ToExamineInfo;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.R;
import com.zggk.newiroad.examinedeal.ToExamineListAdapter;
import com.zggk.newiroad.mvp.MVPBaseFragment;
import com.zggk.newiroad.repair.RepairActivity;
import com.zggk.newiroad.utils.Utils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ExamineDoneFragment extends MVPBaseFragment<ExamineDoneContract.View, ExamineDonePresenter> implements ExamineDoneContract.View {

    @Bind(R.id.com_no_data_lly)
    LinearLayout comNoDataLly;
    private View parentView;
    @Bind(R.id.com_no_data_content_layout)
    LinearLayout comNoDataContentLayout;
    private ListView toExamineListView;
    private SmartRefreshLayout toExamineRefreshLayout;


    private Activity activity;
    private Gson gson = new Gson();
    private final int TAG_GET_LIST = 1;//获取派发列表数据的网络标识
    private ToExamineListAdapter adapter;//列表适配器
    private ToExamineDataInfo examineDataInfo;
    private ArrayList<ToExamineInfo> listToExamineInfo = new ArrayList<>();
    private String startTime = "";

    private String lxid = "";
    private String qdid = "";
    private String gydwid = "";
    private String bhlx = "";
    private String listtype = "1";//0:待办 1:已办
    private String dataid = "";
    private String action = "0";//0:下拉  1:上滑
    private String pagesize = "10";
    private String sortfield = "";
    private String sortorder = "";

    private String sgdwid = "";//施工单位Id
    private String sgfzr = "";
    private String htid = "";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        activity = getActivity();

        parentView = inflater.inflate(R.layout.examine__done_fragment, container, false);
        initView();
        refreshDataMethod();
        ButterKnife.bind(this, parentView);
        comNoDataContentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshDataMethod();
            }
        });
        return parentView;
    }

    private void initView() {
        toExamineListView = (ListView) parentView.findViewById(R.id.to_examine_list_view);
        toExamineRefreshLayout = (SmartRefreshLayout) parentView.findViewById(R.id.to_examine_refreshLayout);


        adapter = new ToExamineListAdapter(activity, listToExamineInfo, false, "已办");
        toExamineListView.setAdapter(adapter);

        toExamineRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                loadRefreshData();
            }
        });
        toExamineRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                loadMoreData();
            }
        });


    }

    /**
     * 刷新数据
     */
    public void refreshDataMethod() {
        if (comNoDataLly!=null&&toExamineRefreshLayout!=null) {
            comNoDataLly.setVisibility(View.GONE);
            toExamineRefreshLayout.setVisibility(View.VISIBLE);
        }
        if (listToExamineInfo != null && listToExamineInfo.size() > 0) {
            listToExamineInfo.clear();
            adapter.notifyDataSetChanged();
        }
        toExamineRefreshLayout.autoRefresh();
    }

    /**
     * 下拉刷新数据
     */
    private void loadRefreshData() {
        toExamineRefreshLayout.setLoadmoreFinished(false);
        action = "0";
        dataid = "0";
        callDataNetWork();
    }

    /**
     * 上拉加载更多数据
     */
    private void loadMoreData() {
        action = "1";
        if (listToExamineInfo != null && listToExamineInfo.size() > 0) {
            dataid = listToExamineInfo.get(listToExamineInfo.size() - 1).getBHID();
        }
        callDataNetWork();
    }

    private void getParamsMethod() {
        if (activity instanceof RepairActivity) {
            RepairActivity RepairActivity = (RepairActivity) activity;
            startTime = RepairActivity.getStartTime();
            lxid = RepairActivity.getLxid();
            qdid = RepairActivity.getQdid();
            gydwid = RepairActivity.getGydwid();
            bhlx = RepairActivity.getBhlx();

            sgdwid = RepairActivity.getSgdwid();
            sgfzr = RepairActivity.getSgfzr();
            htid = RepairActivity.getHtid();
        }
    }

    /**
     * 调用审核派发列表接口
     */
    private void callDataNetWork() {
        getParamsMethod();
        mPresenter.addDisease(startTime, lxid, bhlx, gydwid, listtype);
    }


    /**
     * 关闭刷新或加载更多
     */
    private void closeRefreshOrLoadMoreMethod() {
        if ("0".equals(action)) {
            toExamineRefreshLayout.finishRefresh();
        } else {
            toExamineRefreshLayout.finishLoadmore();
        }
    }

    @Override
    public void getData(ToExamineDataInfo videoVos2) {
        examineDataInfo = videoVos2;
        if (examineDataInfo != null && "1".equals(examineDataInfo.getState())) {
            if (examineDataInfo.getBHLIST() != null) {
                if (examineDataInfo.getBHLIST().size() > 0) {
                    ArrayList<Boolean> listSelTag = adapter.getListSelectTag();
                    if ("0".equals(action)) {
                        listToExamineInfo.clear();
                        listSelTag.clear();
                    }
                    listToExamineInfo.addAll(examineDataInfo.getBHLIST());
                    for (int i = 0; i < listToExamineInfo.size(); i++) {
                        if (i >= listSelTag.size()) {
                            listSelTag.add(false);
                        }
                    }
                    adapter.setListSelectTag(listSelTag);
                    adapter.notifyDataSetChanged();
                } else {
                    if ("0".equals(action)) {
                        comNoDataLly.setVisibility(View.VISIBLE);
                        toExamineRefreshLayout.setVisibility(View.GONE);
                    }
                }
            }
            String isalso = examineDataInfo.getISALSO();
            if (!Utils.isNull(isalso)) {
                if ("0".equals(isalso)) {
                    toExamineRefreshLayout.setLoadmoreFinished(true);
                } else if ("1".equals(isalso)) {
                    toExamineRefreshLayout.setLoadmoreFinished(false);
                }
            }
        } else {
            if (examineDataInfo == null) {
                MyApplication.app.customToast("您当前没有网络");
            }
        }
        closeRefreshOrLoadMoreMethod();
    }


    @Override
    public void onRequestError(String msg) {

    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
