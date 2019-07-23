package com.zggk.newiroad.lookdisease;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zggk.newiroad.Bean.BhDetailInfo;
import com.zggk.newiroad.Bean.CZFADATABean;
import com.zggk.newiroad.Bean.ItemBeanInfo;
import com.zggk.newiroad.Bean.Lookdiseasebean;
import com.zggk.newiroad.Bean.TPListInfo;
import com.zggk.newiroad.R;
import com.zggk.newiroad.disease.AddLogPictureGridAdapter;
import com.zggk.newiroad.disease.ListAdapter;
import com.zggk.newiroad.db.dbhelper.CuringDaoImpl;
import com.zggk.newiroad.db.dbhelper.dbInfo.TypeOfInvestigation;
import com.zggk.newiroad.log.add.AddActivity;
import com.zggk.newiroad.mvp.MVPBaseActivity;
import com.zggk.newiroad.utils.ListViewForScrollView;
import com.zggk.newiroad.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LookdiseaseActivity extends MVPBaseActivity<LookdiseaseContract.View, LookdiseasePresenter> implements LookdiseaseContract.View {

    /****
     * 控件声明
     *****/
    private ScrollView scrollView;
    private LinearLayout backLayout;//返回按钮
    private TextView vUnitNameTxt;//采集单位
    private TextView vRoadLineTxt;//采集路线
    private TextView vTimeTxt;//采集时间
    private TextView vExamineTypeTxt;//调查类型
    private TextView vPileNumberTxt;//桩号
    private TextView vDriverDirectionTxt;//行驶方向
    private ListViewForScrollView diseaseListView;//病害信息列表


    private BhDetailInfo detailInfo;//病害明细集合
    private ArrayList<TPListInfo> pictureList=new ArrayList<>();//详情图片集合
    /*****
     * 变量声明
     *****/
//    private DiseaseDetailAdapter detailAdapter;

    private Gson gson = new Gson();
    private String cjbhid;//传入进来的采集病害id
    private final int GET_DISEASE_DETAIL_TAG=1;
    private String comloading;
    private ArrayList<TPListInfo> picInfo=new ArrayList<>();
    private ArrayList<BhDetailInfo> listInfo = new ArrayList<>();
    private ArrayList<CZFADATABean> szfaInfo= new ArrayList<>();
    private ArrayList<CZFADATABean> szfaInfoList= new ArrayList<>();
    private CuringDaoImpl curingDao;
    private LinearLayout layouts;
    TextView bhmcTxt;//病害名称
    ListView ddiseaselist;
    LinearLayout cjtpLayout;//图片采集列表适配器
    GridView vPictureGridView;//采集的图片显示列表
    TextView bhlxTxt;//工程量计算公式结果
    TextView sgfsTxt;//工程量计算公式结果
    TextView bhlx;
    TextView sgfs;
    private ArrayList<ItemBeanInfo> gcxmlist = new ArrayList<>();
    private lookdiseaseAdapter disadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setStatusBarColor(this,R.color.theme_color);
        setContentView(R.layout.act_lookdisease);
        curingDao = new CuringDaoImpl(this);
        initView();
        setListener();
        comloading="正在加载。。。";
        cjbhid = getIntent().getStringExtra("cjbhid");
        loadDetailData();
    }

    private void initView() {
        scrollView = (ScrollView) findViewById(R.id.activity_disease_detail_scrollview);
        backLayout = (LinearLayout) findViewById(R.id.go_back);
        TextView text = (TextView) findViewById(R.id.title);
        text.setText("巡查病害");
        vUnitNameTxt = (TextView) findViewById(R.id.disease_detail_unit_name_txt);
        vRoadLineTxt = (TextView) findViewById(R.id.disease_detail_road_line_txt);
        vTimeTxt = (TextView) findViewById(R.id.disease_detail_time_txt);
        vExamineTypeTxt = (TextView) findViewById(R.id.disease_detail_to_examine_type_txt);
        vPileNumberTxt = (TextView) findViewById(R.id.disease_detail_pile_number_txt);
        vDriverDirectionTxt = (TextView) findViewById(R.id.disease_detail_driver_direction_txt);
        layouts = (LinearLayout) findViewById(R.id.layout);
        View convertView = LayoutInflater.from(LookdiseaseActivity.this).inflate(R.layout.disease_custom_item, null, false);
        bhmcTxt = (TextView) convertView.findViewById(R.id.disease_custom_item_bhmc_txt);
        cjtpLayout = (LinearLayout) convertView.findViewById(R.id.disease_custom_item_cjtp_layout);
        vPictureGridView = (GridView) convertView.findViewById(R.id.cai_ji_picture_show_grid);
        bhlxTxt = (TextView) convertView.findViewById(R.id.bhlx);
        sgfsTxt = (TextView) convertView.findViewById(R.id.sgfs);
        bhlx=(TextView)convertView.findViewById(R.id.bhlx);
        sgfs=(TextView)convertView.findViewById(R.id.sgfs);
        ddiseaselist = (ListView) convertView.findViewById(R.id.add_ddisease_list_view);
        layouts.addView(convertView);
//        diseaseListView = (ListViewForScrollView) findViewById(R.id.disease_detail_list_view);
//
//        detailAdapter = new DiseaseDetailAdapter(this, listInfo,pictureList,szfaInfoList);
//        diseaseListView.setAdapter(detailAdapter);
    }

    private void setListener() {
        backLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    /**
     * 调用巡查病害详情接口信息
     */
    private void loadDetailData() {
        mPresenter.testinfo(cjbhid);
    }



    /**
     * 设置数据
     */
    private void setDataInView() {
        vUnitNameTxt.setText(detailInfo.getDCR());
        vRoadLineTxt.setText(detailInfo.getLXMC());
        vTimeTxt.setText(detailInfo.getDCSJ().replace("T"," "));
        if (detailInfo.getDCLX()!=null){
            vExamineTypeTxt.setText(curingDao.queryInvestigationById(detailInfo.getDCLX()).getDCMC());
        }
        if (detailInfo.getQDZH()!=null){
            String zhStr=detailInfo.getQDZH();
            if(zhStr.contains(".")){
                String zhfStr=zhStr.substring(0,zhStr.indexOf("."));
                String zhaStr=zhStr.substring(zhStr.indexOf(".")+1,zhStr.length());
                if(zhaStr.length()==1){
                    zhaStr+="00";
                }else if(zhaStr.length()==2){
                    zhaStr+="0";
                }
                vPileNumberTxt.setText("K"+zhfStr+"+"+zhaStr);
            }else{
                vPileNumberTxt.setText("K"+zhStr);
            }
        }

        vDriverDirectionTxt.setText(detailInfo.getWZFX());

        listInfo.add(detailInfo);
        if (picInfo != null&&picInfo.size()>0) {
            pictureList.clear();
            pictureList.addAll(picInfo);
        }else{//设置一个默认图
            TPListInfo info=new TPListInfo();
            info.setFILEPATH("");
            pictureList.add(info);
        }
        szfaInfoList.addAll(szfaInfo);
        bhlx.setText(detailInfo.getSHBW());
        bhmcTxt.setText(detailInfo.getBHMC());
        sgfs.setText(detailInfo.getCZFAMC());
        if (szfaInfo != null && szfaInfo.size() > 0) {
            for (int i = 0;i<szfaInfo.size();i++){
                ItemBeanInfo itembean = new ItemBeanInfo();
                itembean.setXmmc(szfaInfo.get(i).getGCXM());
                itembean.setJldw(szfaInfo.get(i).getDW());
                itembean.setJsgs(replaceNull(szfaInfo.get(i).getJSGS()));
                itembean.setSl(szfaInfo.get(i).getSL());
                gcxmlist.add(itembean);
            }
            disadapter = new lookdiseaseAdapter(this,gcxmlist);
            ddiseaselist.setAdapter(disadapter);
        }
        ArrayList<String> listImgUrl = new ArrayList<>();
        if (picInfo != null && picInfo.size() > 0) {
            for(int k=0;k<picInfo.size();k++){
                TPListInfo tpinfo=picInfo.get(k);
                if(tpinfo!=null){
                    String imgUrl=tpinfo.getFILEPATH();
                    listImgUrl.add(imgUrl);
                }
            }
            AddLogPictureGridAdapter imgAdapter = new AddLogPictureGridAdapter(this, listImgUrl);
           vPictureGridView.setAdapter(imgAdapter);
        }
//        detailAdapter.notifyDataSetChanged();
        //滑动到顶部
//        scrollView.post(new Runnable() {
//            @Override
//            public void run() {
//                scrollView.fullScroll(ScrollView.FOCUS_UP);
//            }
//        });
    }
    public static String replaceNull(String str) {
        if (str == null||str.equals("null")) {
            return "";
        }
        return str;
    }
    @Override
    public void getData(Lookdiseasebean detailDataInfo) {
        if (detailDataInfo != null) {
            if (detailDataInfo.getBHDATA() != null) {
                detailInfo = detailDataInfo.getBHDATA().get(0);
                picInfo = detailDataInfo.getTPDZ();
                szfaInfo = detailDataInfo.getCZFADATA();
                if (detailInfo != null) {
                    setDataInView();
                }
            }
        }
    }

    @Override
    public void onRequestError(String msg) {

    }

    @Override
    public void onRequestEnd() {

    }
}

