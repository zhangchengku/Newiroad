package com.zggk.newiroad.handle.waihandle;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zggk.newiroad.Bean.waiHandleItembean;
import com.zggk.newiroad.Bean.waihandlelistbean;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.R;
import com.zggk.newiroad.db.dbhelper.CuringDaoImpl;
import com.zggk.newiroad.db.dbhelper.dbInfo.ConstructionInfo;
import com.zggk.newiroad.db.dbhelper.dbInfo.ConstructionUploadInfo;
import com.zggk.newiroad.db.dbhelper.dbInfo.FilterBHInfo;
import com.zggk.newiroad.db.dbhelper.dbInfo.FilterLxInfo;
import com.zggk.newiroad.handle.LoadDataDialog;
import com.zggk.newiroad.handle.bdwaihandle.BDwaihandleActivity;
import com.zggk.newiroad.handle.handleListpopwindow;
import com.zggk.newiroad.handle.inwaihandle.INwaihandleActivity;
import com.zggk.newiroad.mvp.MVPBaseFragment;
import com.zggk.newiroad.utils.Dialog.CommBtnListener;
import com.zggk.newiroad.utils.Dialog.CommNotificationDialog;
import com.zggk.newiroad.utils.DiseaseNewSelectObjectListener;
import com.zggk.newiroad.utils.Utils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class WaihandleFragment extends MVPBaseFragment<WaihandleContract.View, WaihandlePresenter> implements WaihandleContract.View {
    @Bind(R.id.lx)
    TextView lx;
    @Bind(R.id.type)
    TextView type;
    @Bind(R.id.rr)
    LinearLayout rr;
    @Bind(R.id.rrr)
    LinearLayout rrr;
    @Bind(R.id.waihandle_rv)
    RecyclerView waihandleRv;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @Bind(R.id.look_log)
    TextView lookLog;
    @Bind(R.id.disease_list_no_data_layout)
    View diseaselist_no_data_layout;
    @Bind(R.id.com_no_data_content_layout)
    LinearLayout comno_data_content_layout;
    @Bind(R.id.lxr)
    RelativeLayout lxr;
    @Bind(R.id.bhr)
    RelativeLayout bhr;
    private CuringDaoImpl curingDao;
    private List<waihandlelistbean> BDlist = new ArrayList<>();
    private List<waihandlelistbean> LIstdate = new ArrayList<>();
    private List<waihandlelistbean> INlist = new ArrayList<>();
    private List<String> CJTPLIST = new ArrayList<>();
    private CommonAdapter<waihandlelistbean> waihandlervadapter;
    private String GYDWID = MyApplication.app.spUtils.getString("dqgydwid", "");
    private String DATATAID = "0";
    private String ACTION = "0";
    private String PAGESIZE = "50";
    private List<ConstructionUploadInfo> querySgwxUpload;

    private handleListpopwindow lxFilterPop;
    private handleListpopwindow bhFilterPop;
    private ArrayList<String> listLxData = new ArrayList<>();
    private ArrayList<String> listbhData = new ArrayList<>();
    private ArrayList<FilterLxInfo> listLxInfoData = new ArrayList<>();
    private ArrayList<FilterBHInfo> listbhInfoData = new ArrayList<>();
    private List<ConstructionUploadInfo> BDsql = new ArrayList<>();
    private List<ConstructionInfo> Insql = new ArrayList<>();
    private String lxid = "";
    private String lxmc = "";
    private String bhid = "";
    private CommNotificationDialog commitWarmDialog;
    private LoadDataDialog loadDataDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_waihandle, container, false);
        ButterKnife.bind(this, view);
        curingDao = new CuringDaoImpl(getContext());
        String str = "正在加载...";
        showLoadingDialogMethod(str);
        setPullRefresher();
        initdate();
        initener();
        initpopw();
        return view;
    }

    private void setPullRefresher() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                LIstdate.clear();
                BDlist.clear();
                INlist.clear();
                initdate();
            }
        });
    }

    private void initdate() {
        waihandleRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        BLbd();
        if (Utils.isNetworkAvailable(getContext()) == false) {//没网
            INbd();
            LIstdate.addAll(BDlist);
            LIstdate.addAll(INlist);
            if (LIstdate.size() == 0) {
                refreshLayout.finishRefresh();
                refreshLayout.setVisibility(View.GONE);
                diseaselist_no_data_layout.setVisibility(View.VISIBLE);
            } else {
                setadapter();
                refreshLayout.finishRefresh();
            }
            if (loadDataDialog != null && loadDataDialog.isShowing()) {
                loadDataDialog.cancel();
            }
        } else {//有网
            mPresenter.getlist("", GYDWID, "", DATATAID, ACTION, PAGESIZE);
        }
    }

    private void setadapter() {
        waihandlervadapter = new CommonAdapter<waihandlelistbean>(getActivity(),
                R.layout.item_waihandle, LIstdate) {
            @Override
            protected void convert(ViewHolder holder, waihandlelistbean tubiaoVo, int position) {
                if (tubiaoVo.getType() == 0) {
                    holder.setText(R.id.disease_adapter_item_state_txt, "待上传");
                    holder.setBackgroundColor(R.id.disease_adapter_item_state_txt, getActivity().getResources().getColor(R.color.disease_flag_text_bg_dsc));
                } else {
                    holder.setText(R.id.disease_adapter_item_state_txt, "待施工");
                    holder.setBackgroundColor(R.id.disease_adapter_item_state_txt, getActivity().getResources().getColor(R.color.disease_flag_text_bg_dsc));
                }
                if (tubiaoVo.getTPDZ() != null) {
                    Glide.with(getContext())
                            .asBitmap()
                            .load(tubiaoVo.getTPDZ())
                            .into((ImageView) holder.getView(R.id.disease_adapter_item_cover_img));
                }

                holder.setText(R.id.disease_adapter_item_road_name_txt, tubiaoVo.getLXBM() + " " + tubiaoVo.getQDZH());
//                holder.setText(R.id.disease_adapter_item_unit_name_txt, tubiaoVo.getGYDWMC());
                holder.setText(R.id.disease_adapter_item_disease_name_txt, tubiaoVo.getBHMC());
                holder.setText(R.id.disease_adapter_item_time_txt, tubiaoVo.getYQWCSJ().replace("T", " "));
            }
        };
        waihandleRv.setAdapter(waihandlervadapter);
        waihandlervadapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if (refreshLayout.isRefreshing() || refreshLayout.isLoading()) {
                    Log.e("onItemClick: ", "没加载");

                } else {
                    if (LIstdate.get(position).getType() == 0) {
                        Intent intent = new Intent(getActivity(), BDwaihandleActivity.class);
                        intent.putExtra("BHID", String.valueOf(LIstdate.get(position).getBHID()));
                        startActivityForResult(intent, 1);
                    } else if (LIstdate.get(position).getType() == 1) {
                        Intent intent = new Intent(getActivity(), INwaihandleActivity.class);
                        intent.putExtra("BHID", String.valueOf(LIstdate.get(position).getBHID()));
                        startActivityForResult(intent, 1);
                    }
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

    }

    @Override
    public void getData(waiHandleItembean videoVos2) {
        if (videoVos2.getSTATE().equals("1")) {
            if (lookLog.isEnabled() == false) {
                lookLog.setEnabled(true);
            }
            setdate(videoVos2);
            LIstdate.addAll(BDlist);
            LIstdate.addAll(INlist);
            if (LIstdate.size() == 0) {
                refreshLayout.finishRefresh();
                refreshLayout.setVisibility(View.GONE);
                diseaselist_no_data_layout.setVisibility(View.VISIBLE);
            } else {
                setadapter();
                refreshLayout.finishRefresh();
            }
        }
        if (loadDataDialog != null && loadDataDialog.isShowing()) {
            loadDataDialog.cancel();
        }
    }

    @Override
    public void onRequestError(String msg) {
        if (msg.equals("0")) {
            MyApplication.app.customToast("您当前网络信号不好");
            lookLog.setEnabled(false);
            LIstdate.clear();
            BDlist.clear();
            INlist.clear();
            BLbd();
            INbd();
            LIstdate.addAll(BDlist);
            LIstdate.addAll(INlist);
            if (LIstdate.size() == 0) {
                refreshLayout.finishRefresh();
                refreshLayout.setVisibility(View.GONE);
                diseaselist_no_data_layout.setVisibility(View.VISIBLE);
            } else {
                setadapter();
                refreshLayout.finishRefresh();
            }
            if (loadDataDialog != null && loadDataDialog.isShowing()) {
                loadDataDialog.cancel();
            }
        }
    }

    @Override
    public void onRequestEnd() {

    }

    /**
     * 刷新数据
     */
    public void refreshDataMethod() {
        BDlist.clear();
        INlist.clear();
        LIstdate.clear();
        refreshLayout.autoRefresh();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2) {
            if (requestCode == 1) {
                BDlist.clear();
                INlist.clear();
                LIstdate.clear();
                refreshLayout.autoRefresh();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void BLbd() {
        BDsql = curingDao.querySgxxUploadBylxidAndBhid(lxmc, bhid);
        if (BDsql != null && BDsql.size() > 0) {
            for (int i = 0; i < BDsql.size(); i++) {
                waihandlelistbean waihandlelistbean = new waihandlelistbean();
                waihandlelistbean.setTPDZ(BDsql.get(i).getTPDZ());
                waihandlelistbean.setLXBM(BDsql.get(i).getLXBM());
                waihandlelistbean.setQDZH(BDsql.get(i).getQDZH());
                waihandlelistbean.setBHID(BDsql.get(i).getBHID());
                waihandlelistbean.setYQWCSJ(BDsql.get(i).getDCSJ());
                waihandlelistbean.setBHMC(BDsql.get(i).getBHMC());
                waihandlelistbean.setType(0);
                BDlist.add(waihandlelistbean);
            }
        }
    }

    private void INbd() {
        Insql = curingDao.querySgxxBylxidAndBhid(lxmc, bhid);
        if (Insql != null && Insql.size() > 0) {
            for (int i = 0; i < Insql.size(); i++) {
                waihandlelistbean waihandlelistbean = new waihandlelistbean();
                waihandlelistbean.setTPDZ(Insql.get(i).getTPDZ());
                waihandlelistbean.setLXBM(Insql.get(i).getLXBM());
                waihandlelistbean.setQDZH(Insql.get(i).getQDZH());
                waihandlelistbean.setBHID(Insql.get(i).getBHID());
                waihandlelistbean.setYQWCSJ(Insql.get(i).getDCSJ());
                waihandlelistbean.setBHMC(Insql.get(i).getBHMC());
                waihandlelistbean.setType(1);
                INlist.add(waihandlelistbean);
            }
        }
    }

    private void setdate(waiHandleItembean videoVos2) {
        if (videoVos2.getBHLIST().size() != 0) {
            for (int i = 0; i < videoVos2.getBHLIST().size(); i++) {
                if (curingDao.isExitByBhid(videoVos2.getBHLIST().get(i).getBHID()) == false && curingDao.isExitByBhidInUpload(videoVos2.getBHLIST().get(i).getBHID()) == false) {
                    ConstructionInfo constructionInfo = new ConstructionInfo();
                    constructionInfo.setDCR(replaceNull(videoVos2.getBHLIST().get(i).getDCR()));//
                    constructionInfo.setLXBM(replaceNull(videoVos2.getBHLIST().get(i).getLXBM()));//
                    constructionInfo.setDCSJ(replaceNull(videoVos2.getBHLIST().get(i).getDCSJ()));//
                    constructionInfo.setDCLX(replaceNull(videoVos2.getBHLIST().get(i).getDCLX()));//
                    constructionInfo.setQDZH(replaceNull(videoVos2.getBHLIST().get(i).getQDZH()));//
                    constructionInfo.setWZFX(replaceNull(videoVos2.getBHLIST().get(i).getWZFX()));//
                    constructionInfo.setTPDZ(replaceNull(videoVos2.getBHLIST().get(i).getTPDZ()));//
                    constructionInfo.setBHLX(replaceNull(videoVos2.getBHLIST().get(i).getBHLX()));//
                    constructionInfo.setBHLXID(replaceNull(videoVos2.getBHLIST().get(i).getBHLXID()));
                    constructionInfo.setBHMC(replaceNull(videoVos2.getBHLIST().get(i).getBHMC()));//
                    constructionInfo.setBHID(replaceNull(videoVos2.getBHLIST().get(i).getBHID()));//
                    String GCXM = "";
                    String GCXMID = "";
                    String JSGS = "";
                    String SL = "";
                    String DW = "";
                    String HD = "";
                    List<waiHandleItembean.CZFABean> listImgUrl = videoVos2.getBHLIST().get(i).getCZFA();
                    if (listImgUrl != null && listImgUrl.size() > 0) {
                        for (int k = 0; k < listImgUrl.size(); k++) {
                            if (k == 0) {
                                GCXM += replaceNull(listImgUrl.get(k).getGCXM());
                                GCXMID += replaceNull(listImgUrl.get(k).getGCXMID());
                                JSGS += replaceGSNull(listImgUrl.get(k).getJSGS());
                                SL += replaceNull(listImgUrl.get(k).getSL());
                                DW += replaceNull(listImgUrl.get(k).getDW());
                                if (listImgUrl.get(k).getHD()==null){
                                    HD += "0";
                                }
                            } else {
                                GCXM += "," + replaceNull(listImgUrl.get(k).getGCXM());
                                GCXMID += "," + replaceNull(listImgUrl.get(k).getGCXMID());
                                JSGS += "," + replaceGSNull(listImgUrl.get(k).getJSGS());
                                SL += "," + replaceNull(listImgUrl.get(k).getSL());
                                DW += "," + replaceNull(listImgUrl.get(k).getDW());
                                if (listImgUrl.get(k).getHD()==null){
                                    HD += "," + "0";
                                }
                            }
                        }
                    }
                    constructionInfo.setCZFAMC(replaceNull(videoVos2.getBHLIST().get(i).getCZFAMC()));
                    constructionInfo.setGCXM(GCXM);
                    constructionInfo.setGCXMID(GCXMID);
                    constructionInfo.setJSGS(JSGS);
                    constructionInfo.setSL(SL);
                    constructionInfo.setDW(DW);
                    constructionInfo.setHD(HD);
                    constructionInfo.setSGMX(replaceNull(videoVos2.getBHLIST().get(i).getSGMX()));//
                    constructionInfo.setBHMC(replaceNull(videoVos2.getBHLIST().get(i).getBHMC()));//
                    constructionInfo.setBHMC(replaceNull(videoVos2.getBHLIST().get(i).getBHMC()));//
                    int j = curingDao.addSgwx(constructionInfo);
                    if (j != 0) {
                        Log.e("添加数据库", "addsqlit: 成功");
                    } else {
                        Log.e("添加数据库", "addsqlit: 失败");
                    }
                }
            }
            INbd();
        }
    }


    public static String replaceNull(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }
    public static String replaceGSNull(String str) {
        if (str == null) {
            return "0";
        }
        return str;
    }


    public static String listToString(List<String> list) {
        StringBuilder sb = new StringBuilder();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (i < list.size() - 1) {
                    sb.append(list.get(i) + ",");
                } else {
                    sb.append(list.get(i));
                }
            }
        }
        return sb.toString();
    }

    private void initener() {
        lxr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (curingDao.queryAllFilterLXInfo().size() != 0) {
                    lxFilterPop.show(rr);
                }
                lxFilterPop.show(rrr);

            }
        });
        bhr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (curingDao.queryAllFilterBHInfo().size() != 0) {
                    bhFilterPop.show(rr);
                }
                bhFilterPop.show(rrr);

            }
        });
        lookLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCommitWarmDialog();
            }
        });
        comno_data_content_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (diseaselist_no_data_layout.isShown()) {
                    diseaselist_no_data_layout.setVisibility(View.GONE);
                    refreshLayout.setVisibility(View.VISIBLE);
                }
                refreshLayout.autoRefresh();
            }
        });
    }


    private void initpopw() {
        listLxData.clear();
        listLxInfoData.clear();
        listLxData.add("所有路线");
        FilterLxInfo filterLxInfo = new FilterLxInfo();
        filterLxInfo.setLXID("");
        filterLxInfo.setLXMC("所有路线");
        listLxInfoData.add(filterLxInfo);
        if (curingDao.queryAllFilterLXInfo()!=null){
            if (curingDao.queryAllFilterLXInfo().size()>0)     {
                for (int i = 0; i < curingDao.queryAllFilterLXInfo().size(); i++) {
                    listLxData.add(curingDao.queryAllFilterLXInfo().get(i).getLXMC());
                    listLxInfoData.add(curingDao.queryAllFilterLXInfo().get(i));
                }
            }

        }

        if (lxFilterPop == null) {
            lxFilterPop = new handleListpopwindow(getActivity(), listLxData, new DiseaseNewSelectObjectListener() {
                @Override
                public void selectPosition(int position) {
                    if (listLxData.get(position).equals("所有路线")) {
                        lxmc = "";
                        lxid = "";
                        if (diseaselist_no_data_layout.isShown()) {
                            diseaselist_no_data_layout.setVisibility(View.GONE);
                            refreshLayout.setVisibility(View.VISIBLE);
                        }
                        refreshLayout.autoRefresh();
                    } else {
                        lxmc = listLxData.get(position);
                        lxid = listLxInfoData.get(position).getLXID();
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
        FilterBHInfo filterBHInfo = new FilterBHInfo();
        filterBHInfo.setBHMCID("");
        filterBHInfo.setBHMC("所有病害");
        listbhInfoData.add(filterBHInfo);
        if (curingDao.queryAllFilterBHInfo()!=null)  {
            if (curingDao.queryAllFilterBHInfo().size()>0)     {
                for (int i = 0; i < curingDao.queryAllFilterBHInfo().size(); i++) {
                    listbhData.add(curingDao.queryAllFilterBHInfo().get(i).getBHMC());
                    listbhInfoData.add(curingDao.queryAllFilterBHInfo().get(i));
                }
            }
        }

        if (bhFilterPop == null) {
            bhFilterPop = new handleListpopwindow(getActivity(), listbhData, new DiseaseNewSelectObjectListener() {
                @Override
                public void selectPosition(int position) {
                    if (listbhData.get(position).equals("所有病害")) {
                        bhid = "";
                        if (diseaselist_no_data_layout.isShown()) {
                            diseaselist_no_data_layout.setVisibility(View.GONE);
                            refreshLayout.setVisibility(View.VISIBLE);
                        }
                        refreshLayout.autoRefresh();
                    } else {
                        bhid = listbhInfoData.get(position).getBHMCID();
                        refreshLayout.autoRefresh();
                    }
                    type.setText(listbhData.get(position));
                }
            });
        } else {
            bhFilterPop.notifityData();
        }

    }

    /**
     * 显示立即上传提示对话框
     */
    private void showCommitWarmDialog() {
//        querySgwxUpload = curingDao.querySgwxUpload();
        querySgwxUpload = curingDao.querySgxxUploadBylxidAndBhid(lxmc, bhid);//根据当前的筛选进行查询
        if (querySgwxUpload.size() != 0) {
            String title = "当前有%s条施工维修数据需要上传，请问是否立即上传？";
            String titleStr = String.format(title, String.valueOf(querySgwxUpload.size()));
            String cancelStr = "取消";
            String okStr = "立即上传";
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
                commitWarmDialog.setWarmTitle(titleStr);
                commitWarmDialog.show();
            }
        } else {
            MyApplication.app.customToast("您没有要上传的施工记录");
        }

    }

    private void comitListDataMethod() {
        UploadWaihandleDataDialog uploadWaihandleDataDialog = new UploadWaihandleDataDialog(this, querySgwxUpload);
        uploadWaihandleDataDialog.show();

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



}