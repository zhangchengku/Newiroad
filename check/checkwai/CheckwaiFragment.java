package com.zggk.newiroad.check.checkwai;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
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
import com.zggk.newiroad.check.UploadcheckDataDialog;
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

public class CheckwaiFragment extends MVPBaseFragment<CheckwaiContract.View, CheckwaiPresenter> implements CheckwaiContract.View {

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
    @Bind(R.id.plys)
    TextView plys;
    @Bind(R.id.shang)
    LinearLayout shang;
    @Bind(R.id.qx)
    TextView qx;
    @Bind(R.id.qrys)
    TextView qrys;
    @Bind(R.id.xia)
    LinearLayout xia;
    @Bind(R.id.rrr)
    RelativeLayout rrr;
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
    private SgRInfo videoVo;
    private ArrayList<String> listLxData = new ArrayList<>();
    private ArrayList<String> listbhData = new ArrayList<>();
    private ArrayList<SgRInfo.LXDATABean> listLxInfoData = new ArrayList<>();
    private ArrayList<SgRInfo.BHLXDATABean> listbhInfoData = new ArrayList<>();
    private CheckPopu lxFilterPop;
    private CheckPopu bhFilterPop;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_checkwai, container, false);
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
        SgRInfo.LXDATABean lxlistBean = new SgRInfo.LXDATABean();
        lxlistBean.setLXMC("所有路线");
        lxlistBean.setLXID("");
        listLxInfoData.add(lxlistBean);
        if(videoVo!=null&&videoVo.getLXDATA()!=null&&videoVo.getLXDATA().size()>0){
            for (int i = 0; i < videoVo.getLXDATA().size(); i++) {
                if (!Utils.isNull(videoVo.getLXDATA().get(i).getLXMC()) && !Utils.isNull(videoVo.getLXDATA().get(i).getLXID())) {
                    listLxData.add(videoVo.getLXDATA().get(i).getLXMC());
                    listLxInfoData.add(videoVo.getLXDATA().get(i));
                }
            }
        }
        if (lxFilterPop == null) {
            lxFilterPop = new CheckPopu(getActivity(), listLxData, new DiseaseNewSelectObjectListener() {
                @Override
                public void selectPosition(int position) {
                    if (listLxData.get(position).equals("所有路线")) {
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
        SgRInfo.BHLXDATABean bhlistBean = new SgRInfo.BHLXDATABean();
        bhlistBean.setBHLXID("");
        bhlistBean.setBHLX("所有病害");
        listbhInfoData.add(bhlistBean);
        if(videoVo!=null&&videoVo.getBHLXDATA()!=null&&videoVo.getBHLXDATA().size()>0){
            for (int i = 0; i < videoVo.getBHLXDATA().size(); i++) {
                if (!Utils.isNull(videoVo.getBHLXDATA().get(i).getBHLXID()) && !Utils.isNull(videoVo.getBHLXDATA().get(i).getBHLX())) {
                    listbhData.add(videoVo.getBHLXDATA().get(i).getBHLX());
                    listbhInfoData.add(videoVo.getBHLXDATA().get(i));

                }
            }
        }
        if (bhFilterPop == null) {
            bhFilterPop = new CheckPopu(getActivity(), listbhData, new DiseaseNewSelectObjectListener() {
                @Override
                public void selectPosition(int position) {
                    if (listbhData.get(position).equals("所有病害")) {
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
        quanx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                        for (int i = 0; i < checkwaidate.size(); i++) {
                            checkwaidate.get(i).isCheck = true;
                        }
                        checkadapter.notifyDataSetChanged();
                } else {
                        for (int i = 0; i < checkwaidate.size(); i++) {
                            checkwaidate.get(i).isCheck = false;
                        }
                        checkadapter.notifyDataSetChanged();
                    }
            }
        });
        plys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (refreshLayout.isRefreshing() == true || refreshLayout.isLoading() == true) {
                } else {
                    if (checkadapter != null) {
                        quanx.setVisibility(View.VISIBLE);
                        shang.setVisibility(View.GONE);
                        xia.setVisibility(View.VISIBLE);
                        checkadapter.flage = true;
                        checkadapter.notifyDataSetChanged();
                    }
                }

            }
        });
        waihandleRv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (refreshLayout.isRefreshing() || refreshLayout.isLoading()) {
                    Log.e("onItemClick: ", "没加载");
                } else {
                    Intent intent = new Intent(getActivity(), CheckxqActivity.class);
                    intent.putExtra("BHID", String.valueOf(checkwaidate.get(position).getBHID()));
                    startActivityForResult(intent, 1);
                }
            }
        });
        qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quanx.setVisibility(View.GONE);
                shang.setVisibility(View.VISIBLE);
                xia.setVisibility(View.GONE);
                checkadapter.flage = false;
                checkadapter.notifyDataSetChanged();
            }
        });
        qrys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updates.clear();

                    for (int i = 0; i < checkwaidate.size(); i++) {
                        if (checkwaidate.get(i).isCheck) {
                            updates.add(checkwaidate.get(i));
                        }
                    }
                    showCommitWarmDialog();
            }
        });
    }

    private void showCommitWarmDialog() {
        if (updates.size() != 0) {
            String title = "当前有%s条验收数据需要上传，请问是否立即上传？";
            String titleStr = String.format(title, String.valueOf(updates.size()));
            String cancelStr = "取消";
            String okStr = "确定";
            if (commitWarmDialog == null) {
                commitWarmDialog = new CommNotificationDialog(getActivity(), titleStr, okStr, cancelStr, new CommBtnListener() {
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
        } else {

            MyApplication.app.customToast("您没有要上传的验收记录");
        }
    }
    private void comitListDataMethod() {
        UploadcheckDataDialog uploadcheckDataDialog = new UploadcheckDataDialog(this, updates);
        uploadcheckDataDialog.show();
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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2) {
            if (requestCode == 1) {
                refreshLayout.autoRefresh();
            }
        }
    }
    public void refreshDataMethod() {
        quanx.setVisibility(View.GONE);
        shang.setVisibility(View.VISIBLE);
        xia.setVisibility(View.GONE);
        checkadapter.flage = false;
        checkadapter.notifyDataSetChanged();
        refreshLayout.autoRefresh();
    }
}
