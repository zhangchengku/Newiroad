package com.zggk.newiroad.examinedeal;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zggk.newiroad.Bean.ToExamineDataInfo;
import com.zggk.newiroad.Bean.ToExamineInfo;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.R;
import com.zggk.newiroad.mvp.MVPBaseFragment;
import com.zggk.newiroad.repair.RepairActivity;
import com.zggk.newiroad.repair.lookrepair.LookRepairActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ExamineDealFragment extends MVPBaseFragment<ExamineDealContract.View, ExamineDealPresenter> implements ExamineDealContract.View {

    @Bind(R.id.com_no_data_lly)
    LinearLayout comNoDataLly;
    @Bind(R.id.com_no_data_content_layout)
    LinearLayout comNoDataContentLayout;
    private View parentView;

    private TextView toExamineQuanXuanTxt;
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
    private String listtype = "0";//0:待办 1:已办
    private String dataid = "";
    private String action = "0";//0:下拉  1:上滑
    private String pagesize = "10";
    private String sortfield = "";
    private String sortorder = "";

    private String sgdwid = "";//施工单位Id
    private String sgfzr = "";
    private String htid = "";
    private String GZZLBH = "待办";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = getActivity();
        parentView = inflater.inflate(R.layout.examine_fragment, container, false);
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
        toExamineQuanXuanTxt = (TextView) parentView.findViewById(R.id.to_examine_quan_xuan_txt);


        adapter = new ToExamineListAdapter(activity, listToExamineInfo, true, "待办");
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


        toExamineQuanXuanTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.setAllSelect();
            }
        });

    }

    /**
     * 刷新数据
     */
    public void refreshDataMethod() {
        if (comNoDataLly != null && toExamineRefreshLayout != null) {
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

    @Override
    public void onResume() {
        super.onResume();
        if (LookRepairActivity.isSaveOrUpdateData) {
            LookRepairActivity.isSaveOrUpdateData = false;
            toExamineRefreshLayout.autoRefresh();
        }
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
        mPresenter.addDisease(startTime, lxid, bhlx, gydwid, listtype, action, dataid);
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

    /**
     * 获取选择的待派发的数据
     *
     * @return
     */
    public ArrayList<ToExamineInfo> getSelectInfoData(String option) {
        ArrayList<ToExamineInfo> listResult = new ArrayList<>();
        ArrayList<Boolean> listSelTag = adapter.getListSelectTag();
        boolean isStateSame = true;
        if (listSelTag != null && listSelTag.size() > 0) {
            if (listSelTag.size() == listToExamineInfo.size()) {
                for (int i = 0; i < listSelTag.size(); i++) {
                    ToExamineInfo info = listToExamineInfo.get(i);
                    String bhzt = info.getBHZT();
                    if (listSelTag.get(i)) {
                        listResult.add(info);
                        if ("paifa".equals(option)) {
                            if (MyApplication.spUtils.getString("QX").equals("2")) {//市
                                if ("19".equals(bhzt)) {
                                    isStateSame = false;
                                }
                            } else if (MyApplication.spUtils.getString("QX").equals("3")) {//县
                                if (!"2".equals(bhzt) && !"20".equals(bhzt)) {
                                    isStateSame = false;
                                }
                            }
                        } else if ("shenhe".equals(option)) {
                            if (!"17".equals(bhzt)) {
                                isStateSame = false;
                            }
                        } else if ("shangbao".equals(option)) {
                            if (!"2".equals(bhzt) && !"1".equals(bhzt) && !"19".equals(bhzt)) {
                                isStateSame = false;
                            }
                        }
                    }
                }
            } else {
                Log.i("测试", "数据大小不一致");
            }
        }

        if (listResult.size() > 0) {
            if (isStateSame) {
                return listResult;
            } else {
                if ("paifa".equals(option)) {
                    MyApplication.app.customToast("只有新添加和新病害可以派发，请重新选择");
                } else if ("shenhe".equals(option)) {
                    MyApplication.app.customToast("只有已上报的数据可以审核，请重新选择");
                } else if ("shangbao".equals(option)) {
                    MyApplication.app.customToast("只有新病害和审核不通过可以上报，请重新选择");
                }
                return null;
            }
        } else {
            MyApplication.app.customToast("请选择病害");
            return null;
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


