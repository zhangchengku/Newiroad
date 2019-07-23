package com.zggk.newiroad.handle.readyhandle;


import com.zggk.newiroad.Bean.ReadyListbean;
import com.zggk.newiroad.Bean.SgRInfo;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.R;
import com.zggk.newiroad.handle.HandleActivity;
import com.zggk.newiroad.handle.LoadDataDialog;
import com.zggk.newiroad.handle.handleListpopwindow;
import com.zggk.newiroad.handle.readyhandlexq.ReadyhandlexqActivity;
import com.zggk.newiroad.mvp.MVPBaseFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
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
 *  邮箱 784787081@qq.com
 */

public class ReadyhandleFragment extends MVPBaseFragment<ReadyhandleContract.View, ReadyhandlePresenter> implements ReadyhandleContract.View {
    @Bind(R.id.lx)
    TextView lx;
    @Bind(R.id.type)
    TextView type;
    @Bind(R.id.rr)
    LinearLayout rr;
    @Bind(R.id.alreadyhandle_rv)
    ListView alreadyhandleRv;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @Bind(R.id.vFilterLayout)
    LinearLayout vFilterLayout;
    @Bind(R.id.disease_list_no_data_layout)
    View diseaselist_no_data_layout;
    @Bind(R.id.com_no_data_content_layout)
    LinearLayout comno_data_content_layout;
    private String GYDWID = MyApplication.app.spUtils.getString("dqgydwid", "");
    private String DATATAID;
    private String ACTION;
    private String PAGESIZE = "10";
    private CommonAdapter<ReadyListbean.BHLISTBean> waihandlervadapter;
    private List<ReadyListbean.BHLISTBean> LIstdate = new ArrayList<>();
    private ArrayList<String> listLxData = new ArrayList<>();
    private ArrayList<String> listbhData = new ArrayList<>();
    private ArrayList<SgRInfo.YBLXDATABean> listLxInfoData = new ArrayList<>();
    private ArrayList<SgRInfo.YBBHLXDATABean> listbhInfoData = new ArrayList<>();
    private handleListpopwindow lxFilterPop;
    private handleListpopwindow bhFilterPop;
    private String LXID="";
    private String BHMCID="";
    private int page;
    private RelativeLayout lxrr;
    private RelativeLayout bhrr;
    private LoadDataDialog loadDataDialog;
    private SgRInfo videoVos;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_alreadyhandle, container, false);
        ButterKnife.bind(this, view);
        lxrr = (RelativeLayout) view.findViewById(R.id.lxr);
        bhrr = (RelativeLayout) view.findViewById(R.id.bhr);
        String str = "正在加载...";
        showLoadingDialogMethod(str);

        initpopu();
        initdate();
        initener();
        setPullRefresher();

        return view;
    }

    private void initdate() {
        page = 1;
        LIstdate.clear();
        DATATAID = "0";
        ACTION = "0";
        mPresenter.getlist(LXID, GYDWID, BHMCID, DATATAID, ACTION, PAGESIZE);
    }

    private void setPullRefresher() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                if (refreshlayout.isLoadmoreFinished()) {
                    refreshlayout.setLoadmoreFinished(false);
                }
                page = 1;
                LIstdate.clear();
                if(waihandlervadapter!=null){
                    waihandlervadapter.notifyDataSetChanged();
                }
                DATATAID = "0";
                ACTION = "0";
                mPresenter.getlist(LXID, GYDWID, BHMCID, DATATAID, ACTION, PAGESIZE);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page = 2;
                ACTION = "1";
                DATATAID = LIstdate.get(LIstdate.size() - 1).getBHID();
                mPresenter.getlist(LXID, GYDWID, BHMCID, DATATAID, ACTION, PAGESIZE);
            }
        });
    }

    private void initpopu() {
        listLxData.clear();
        listLxData.add("所有路线");
        listbhData.clear();
        listbhData.add("所有病害");
        SgRInfo.YBLXDATABean lxlistBean=new SgRInfo.YBLXDATABean();
        lxlistBean.setLXID("");
        lxlistBean.setLXMC("所有路线");
        listLxInfoData.add(lxlistBean);
        SgRInfo.YBBHLXDATABean bhlistBean=new SgRInfo.YBBHLXDATABean();
        bhlistBean.setBHLXID("");
        bhlistBean.setBHLX("所有病害");
        listbhInfoData.add(bhlistBean);
        if (videoVos.getYBLXDATA().size()>0)       {
            for (int i = 0; i < videoVos.getYBLXDATA().size(); i++) {
                SgRInfo.YBLXDATABean lxlistBeanTemp=videoVos.getYBLXDATA().get(i);
                if(!Utils.isNull(lxlistBeanTemp.getLXID())&&!Utils.isNull(lxlistBeanTemp.getLXMC())){
                    listLxData.add(lxlistBeanTemp.getLXMC());
                    listLxInfoData.add(lxlistBeanTemp);
                }
            }
        }
       if (videoVos.getYBBHLXDATA().size()>0){
           for (int i = 0; i < videoVos.getYBBHLXDATA().size(); i++) {
               SgRInfo.YBBHLXDATABean bhlistBeanTemp=videoVos.getYBBHLXDATA().get(i);
               if(!Utils.isNull(bhlistBeanTemp.getBHLXID())&&!Utils.isNull(bhlistBeanTemp.getBHLX())){
                   listbhData.add(bhlistBeanTemp.getBHLX());
                   listbhInfoData.add(bhlistBeanTemp);
               }
           }
       }


        if (lxFilterPop == null) {
            lxFilterPop = new handleListpopwindow(getActivity(), listLxData, new DiseaseNewSelectObjectListener() {
                @Override
                public void selectPosition(int position) {
                    if (listLxData.get(position).equals("所有路线")) {
                        LXID = "";
                        if (diseaselist_no_data_layout.isShown()) {
                            diseaselist_no_data_layout.setVisibility(View.GONE);
                            refreshLayout.setVisibility(View.VISIBLE);
                        }
                        refreshLayout.autoRefresh();
                    } else {
                        LXID = listLxInfoData.get(position).getLXID();
                        refreshLayout.autoRefresh();
                    }
                    lx.setText(listLxData.get(position));

                }
            });
        }else {
            lxFilterPop.notifityData();
        }
        if (bhFilterPop == null) {
            bhFilterPop = new handleListpopwindow(getActivity(), listbhData, new DiseaseNewSelectObjectListener() {
                @Override
                public void selectPosition(int position) {
                    if (listbhData.get(position).equals("所有病害")) {
                        BHMCID = "";
                        if (diseaselist_no_data_layout.isShown()) {
                            diseaselist_no_data_layout.setVisibility(View.GONE);
                            refreshLayout.setVisibility(View.VISIBLE);
                        }
                        refreshLayout.autoRefresh();
                    } else {
                        BHMCID = listbhInfoData.get(position).getBHLXID();
                        refreshLayout.autoRefresh();
                    }
                    type.setText(listbhData.get(position));

                }
            });
        }else {
            bhFilterPop.notifityData();
        }
    }


    @Override
    public void getData(ReadyListbean videoVos2) {
        if (page == 2) {
            LIstdate.addAll(videoVos2.getBHLIST());
            waihandlervadapter.setmDatas(LIstdate);
            refreshLayout.finishLoadmore();
            if (videoVos2.getISALSO().equals("0")) {
                refreshLayout.setLoadmoreFinished(true);
            }
        } else {
            LIstdate = videoVos2.getBHLIST();
            if (LIstdate.size() == 0) {
                refreshLayout.setVisibility(View.GONE);
                diseaselist_no_data_layout.setVisibility(View.VISIBLE);
            } else {
                setadapter();
            }
            refreshLayout.finishRefresh();
        }
        if (loadDataDialog != null && loadDataDialog.isShowing()) {
            loadDataDialog.cancel();
        }
    }
    private void setadapter() {
        waihandlervadapter = new CommonAdapter<ReadyListbean.BHLISTBean>(getActivity(),
                R.layout.item_waihandle, LIstdate) {
            @Override
            protected void convert(ViewHolder holder, ReadyListbean.BHLISTBean tubiaoVo, int position) {
                    holder.setText(R.id.disease_adapter_item_state_txt, "施工完成");
                    holder.setBackgroundColor(R.id.disease_adapter_item_state_txt, getActivity().getResources().getColor(R.color.disease_flag_text_bg_sgz));
                Glide.with(getContext())
                        .asBitmap()
                        .load(tubiaoVo.getTPDZ())
                        .into((ImageView) holder.getView(R.id.disease_adapter_item_cover_img));
                holder.setText(R.id.disease_adapter_item_road_name_txt, tubiaoVo.getLXBM()+" "+tubiaoVo.getQDZH());
//                holder.setText(R.id.disease_adapter_item_unit_name_txt, tubiaoVo.getSGDW());
                holder.setText(R.id.disease_adapter_item_disease_name_txt, tubiaoVo.getBHLX());
              holder.setText(R.id.disease_adapter_item_time_txt, tubiaoVo.getYQWCSJ().replace("T"," "));
            }
        };
        alreadyhandleRv.setAdapter(waihandlervadapter);
        alreadyhandleRv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (refreshLayout.isRefreshing() || refreshLayout.isLoading()) {
                } else {
                    Intent intent = new Intent(getActivity(), ReadyhandlexqActivity.class);
                    intent.putExtra("BHID", String.valueOf(LIstdate.get(position).getBHID()));
                    startActivity(intent);
                }

            }
        });
    }

    @Override
    public void onRequestError(String msg) {
        if (msg.equals("0")) {
            MyApplication.app.customToast("您当前网络信号不好");
            refreshLayout.finishRefresh();
            if (loadDataDialog != null && loadDataDialog.isShowing()) {
                loadDataDialog.cancel();
            }
        }
    }
    @Override
    public void onRequestEnd() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void initener() {

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
        lxrr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isNetworkAvailable(getContext()) == false) {//没网
                    MyApplication.app.customToast("您当前没有网");
                } else {//有网
                    lxFilterPop.show(rr);
                }
            }
        });
        bhrr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isNetworkAvailable(getContext()) == false) {//没网
                    MyApplication.app.customToast("您当前没有网");
                } else {//有网
                    bhFilterPop.show(rr);
                }

            }
        });
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        videoVos = ((HandleActivity) activity).getSxDate();

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
