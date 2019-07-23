package com.zggk.newiroad.repair.lookrepair;


import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.google.gson.Gson;
import com.zggk.newiroad.Bean.Adddiseasejson;
import com.zggk.newiroad.Bean.Basebean;
import com.zggk.newiroad.Bean.BhDetailInfo;
import com.zggk.newiroad.Bean.CZFADATABean;
import com.zggk.newiroad.Bean.Dingbean;
import com.zggk.newiroad.Bean.ItemBeanInfo;
import com.zggk.newiroad.Bean.Lookdiseasebean;
import com.zggk.newiroad.Bean.TPListInfo;
import com.zggk.newiroad.Bean.ToExamineInfo;
import com.zggk.newiroad.Bean.czfaxx;
import com.zggk.newiroad.Bean.repairPFjson;
import com.zggk.newiroad.Bean.repairRKbean;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.R;
import com.zggk.newiroad.db.dbhelper.CuringDaoImpl;
import com.zggk.newiroad.disease.AddLogPictureGridAdapter;
import com.zggk.newiroad.disease.ListAdapter;
import com.zggk.newiroad.handle.LoadDataDialog;
import com.zggk.newiroad.lookdisease.LookdiseaseActivity;
import com.zggk.newiroad.mvp.MVPBaseActivity;

import com.zggk.newiroad.repair.RepairActivity;
import com.zggk.newiroad.repair.ToExamineWarmDialog;
import com.zggk.newiroad.utils.Dialog.CommBtnListener;
import com.zggk.newiroad.utils.Dialog.CommNotificationDialog;
import com.zggk.newiroad.utils.DiseaeNewSelectObjectPopupWindow;
import com.zggk.newiroad.utils.DiseaseNewSelectObjectListener;
import com.zggk.newiroad.utils.ListViewForScrollView;
import com.zggk.newiroad.utils.NoScroolGridView;
import com.zggk.newiroad.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class LookRepairActivity extends MVPBaseActivity<LookRepairContract.View, LookRepairPresenter> implements LookRepairContract.View {


    @Bind(R.id.go_back)
    LinearLayout goBack;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.right)
    TextView right;
    @Bind(R.id.disease_new_unit_name_txt)
    TextView diseaseNewUnitNameTxt;
    @Bind(R.id.luxian_warm_txt)
    TextView luxianWarmTxt;
    @Bind(R.id.disease_new_road_line_txt_label)
    TextView diseaseNewRoadLineTxtLabel;
    @Bind(R.id.disease_new_road_line_txt)
    TextView diseaseNewRoadLineTxt;
    @Bind(R.id.disease_new_road_line_arrow_img)
    ImageView diseaseNewRoadLineArrowImg;
    @Bind(R.id.disease_new_road_line_layout)
    RelativeLayout diseaseNewRoadLineLayout;
    @Bind(R.id.time_warm_txt)
    TextView timeWarmTxt;
    @Bind(R.id.disease_new_time_txt_label)
    TextView diseaseNewTimeTxtLabel;
    @Bind(R.id.disease_new_time_txt)
    TextView diseaseNewTimeTxt;
    @Bind(R.id.disease_new_time_layout)
    RelativeLayout diseaseNewTimeLayout;
    @Bind(R.id.dclx_warm_txt)
    TextView dclxWarmTxt;
    @Bind(R.id.disease_new_to_examine_type_txt_label)
    TextView diseaseNewToExamineTypeTxtLabel;
    @Bind(R.id.disease_new_to_examine_type_txt)
    TextView diseaseNewToExamineTypeTxt;
    @Bind(R.id.disease_new_to_examine_type_layout)
    RelativeLayout diseaseNewToExamineTypeLayout;
    @Bind(R.id.zh_warm_txt)
    TextView zhWarmTxt;
    @Bind(R.id.disease_new_pile_number_label)
    TextView diseaseNewPileNumberLabel;
    @Bind(R.id.disease_new_pile_number_k)
    TextView diseaseNewPileNumberK;
    @Bind(R.id.disease_new_pile_number_one_edit)
    EditText diseaseNewPileNumberOneEdit;
    @Bind(R.id.disease_new_pile_number_jia)
    TextView diseaseNewPileNumberJia;
    @Bind(R.id.disease_new_pile_number_two_edit)
    EditText diseaseNewPileNumberTwoEdit;
    @Bind(R.id.disease_new_pile_number_location_txt)
    TextView diseaseNewPileNumberLocationTxt;
    @Bind(R.id.disease_new_driver_direction_shang_txt)
    TextView diseaseNewDriverDirectionShangTxt;
    @Bind(R.id.disease_new_driver_direction_xia_txt)
    TextView diseaseNewDriverDirectionXiaTxt;
    @Bind(R.id.disease_new_driver_direction_quan_txt)
    TextView diseaseNewDriverDirectionQuanTxt;
    @Bind(R.id.disease_custom_edit_item_bhlx_label_txt)
    TextView diseaseCustomEditItemBhlxLabelTxt;
    @Bind(R.id.disease_custom_edit_item_bhlx_txt_btn)
    TextView diseaseCustomEditItemBhlxTxtBtn;
    @Bind(R.id.bhlx)
    LinearLayout bhlx;
    @Bind(R.id.disease_custom_edit_item_bhmc_label_txt)
    TextView diseaseCustomEditItemBhmcLabelTxt;
    @Bind(R.id.disease_custom_edit_item_bhmc_txt_btn)
    TextView diseaseCustomEditItemBhmcTxtBtn;
    @Bind(R.id.bhmc)
    LinearLayout bhmc;
    @Bind(R.id.disease_custom_edit_item_sgfs_label_txt)
    TextView diseaseCustomEditItemSgfsLabelTxt;
    @Bind(R.id.disease_custom_edit_item_sgfs_txt_btn)
    TextView diseaseCustomEditItemSgfsTxtBtn;
    @Bind(R.id.sgfs)
    LinearLayout sgfs;
    @Bind(R.id.add_ddisease_list_view)
    ListViewForScrollView addDdiseaseListView;
    @Bind(R.id.cai_ji_picture_add_grid)
    NoScroolGridView caiJiPictureAddGrid;
    @Bind(R.id.disease_custom_edit_item_cjtp_layout)
    LinearLayout diseaseCustomEditItemCjtpLayout;
    @Bind(R.id.disease_new_bh_content_layout)
    LinearLayout diseaseNewBhContentLayout;
    @Bind(R.id.activity_disease_new_scrollview)
    ScrollView activityDiseaseNewScrollview;
    @Bind(R.id.disease_new_bhcj_upload_now_txt)
    TextView diseaseNewBhcjUploadNowTxt;
    @Bind(R.id.activity_disease_list_bottom_btn_layout)
    LinearLayout activityDiseaseListBottomBtnLayout;
    @Bind(R.id.activity_new_disease_zhe_zhao_layout)
    View activityNewDiseaseZheZhaoLayout;
    @Bind(R.id.disease_new_parent_layout)
    RelativeLayout diseaseNewParentLayout;
    private String cjbhid;
    private BhDetailInfo detailInfo;//病害明细集合
    private ArrayList<TPListInfo> pictureList=new ArrayList<>();//详情图片集合
    private ArrayList<TPListInfo> picInfo = new ArrayList<>();
    private ArrayList<CZFADATABean> szfaInfo = new ArrayList<>();
    private int directionSelInt;
    private ArrayList<ItemBeanInfo> gcxmResult = new ArrayList<>();
    private ToExamineWarmDialog warmDialog;
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private String gydwid = MyApplication.app.spUtils.getString("dqgydwid");
    private CuringDaoImpl curingDao;
    private String DH = MyApplication.GZZLBH;
    private ListAdapter gcxmadapter;
    private Gson gson = new Gson();
    public static boolean isSaveOrUpdateData;//是否修改了数据
    private ArrayList<String> listPFResult= new ArrayList<>();
    private DiseaeNewSelectObjectPopupWindow pfPop;
    private CommNotificationDialog SBDialog;
    private ArrayList<ItemBeanInfo> contents;
    private LoadDataDialog loadDataDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        Utils.setStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.look_repair);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        ButterKnife.bind(this);
        Log.e("单号", DH);
        curingDao = new CuringDaoImpl(this);
        cjbhid = getIntent().getStringExtra("cjbhid");

        mPresenter.testinfo(cjbhid);
        initview();
        diseaseNewBhcjUploadNowTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkAvailable(LookRepairActivity.this)) {
                    if (pfPop != null ) {
                        pfPop.show(diseaseNewParentLayout);
                    }
                } else {
                    MyApplication.app.customToast("您当前没有网络");
                }
            }
        });
        //定位
        diseaseNewPileNumberLocationTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(locationClient==null){
                    initLocation();
                }
                startLocation();
            }
        });
    }

    private void showPaiFaTiShiDialog() {
        if (warmDialog == null) {
            warmDialog = new ToExamineWarmDialog(this, new CommBtnListener() {
                @Override
                public void CommOkBtnClick() {
                    paifaCommit();
                    warmDialog.close();
                }

                @Override
                public void CommCancelBtnClick() {

                }
            });
        }
        warmDialog.show();
    }
    private void showLoadingDialogMethod(String str) {
        if (loadDataDialog == null) {
            loadDataDialog = new LoadDataDialog(this);
        }
        loadDataDialog.setTitleStr(str);
        loadDataDialog.show();
    }
    private void paifaCommit() {
        showLoadingDialogMethod("上传中");
        String creator = MyApplication.spUtils.getString("dlr");
        String gydwid = MyApplication.spUtils.getString("dqgydwid");
        String zhStr="0.0";
        String zhOneStr=diseaseNewPileNumberOneEdit.getText().toString();
        String zhTwoStr=diseaseNewPileNumberTwoEdit.getText().toString();
        if(Utils.isNull(zhTwoStr)){
            zhStr = zhOneStr;
        }else{
            zhStr = zhOneStr + "." + zhTwoStr;
        }
        ArrayList<repairPFjson.PFDXXBean> PFDXXlist = new ArrayList<>();
        repairPFjson.PFDXXBean PFDXX=new repairPFjson.PFDXXBean();
        PFDXX.setCREATOR(creator);
        PFDXX.setGZZLBH(DH);
        PFDXX.setJLDW(gydwid);
        PFDXX.setPFR(creator);
        PFDXX.setRWXDSJ(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));//
        PFDXX.setSGDW(MyApplication.GYDWID);
        PFDXX.setYQWCSJ(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        PFDXXlist.add(PFDXX);
        ArrayList<repairPFjson.XCBHBean> XCBHlist = new ArrayList<>();
        repairPFjson.XCBHBean XCBH=new repairPFjson.XCBHBean();
        XCBH.setGUID_OBJ(cjbhid);
        XCBH.setJLDW(gydwid);
        XCBH.setSGDW(MyApplication.GYDWID);
        XCBH.setPFR(MyApplication.spUtils.getString("dlr"));
        XCBH.setCREATOR(MyApplication.spUtils.getString("dlr"));
        XCBH.setQDZHPF(zhStr);
        XCBH.setCZFAMC(detailInfo.getCZFAMC());
        ArrayList<repairPFjson.CZFABean> cZFAlist= new ArrayList<>();
        if (gcxmadapter.get() != null && gcxmadapter.get().size() > 0) {
            for (int k = 0; k < gcxmadapter.get().size(); k++) {
                repairPFjson.CZFABean   cZFABean  =    new repairPFjson.CZFABean();
                cZFABean.setJSGS(replaceNull(gcxmadapter.get().get(k).getJsgs()));
                cZFABean.setGCXMID(gcxmadapter.get().get(k).getGcxmid());
                cZFAlist.add(cZFABean);
            }
            XCBH.setCZFA(cZFAlist);
        }
        XCBHlist.add(XCBH);
        repairPFjson repairPFjson =  new repairPFjson();
        repairPFjson.setXCBH(XCBHlist);
        repairPFjson.setPFDXX(PFDXXlist);
        String JSON = gson.toJson(repairPFjson);
        mPresenter.paifaDisease(JSON);
    }
    public static String replaceNull(String str) {
        if (str == null || str.equals("null")) {
            return "";
        }
        return str;
    }
    private void initview() {
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title.setText("派发详情");
        if (Utils.isNetworkAvailable(this)) {
            diseaseNewPileNumberLocationTxt.setVisibility(View.VISIBLE);
        } else {
            diseaseNewPileNumberLocationTxt.setVisibility(View.GONE);
        }
        listPFResult.clear();
        if (MyApplication.spUtils.getString("QX").equals("1")){
            listPFResult.add("上报");
            listPFResult.add("审核");
            listPFResult.add("派发");
        }else if (MyApplication.spUtils.getString("QX").equals("2")) {
            listPFResult.add("审核");
            listPFResult.add("派发");
        }else if (MyApplication.spUtils.getString("QX").equals("3")) {
            listPFResult.add("上报");
            listPFResult.add("派发");
        }
        pfPop = new DiseaeNewSelectObjectPopupWindow(this, "请选择处置方式", listPFResult, new DiseaseNewSelectObjectListener() {
            @Override
            public void selectPosition(int position) {
                if (listPFResult.get(position).equals("派发")){
                    if (MyApplication.spUtils.getString("QX").equals("2")) {//市
                        if (getIntent().getStringExtra("BHZT").equals("19")){
                            MyApplication.app.customToast("只有新添加和新病害可以派发，请重新选择");
                            return;
                        }
                    } else if (MyApplication.spUtils.getString("QX").equals("3")) {//县
                        if (!"2".equals(getIntent().getStringExtra("BHZT")) && !"20".equals(getIntent().getStringExtra("BHZT"))) {
                            MyApplication.app.customToast("只有新添加和新病害可以派发，请重新选择");
                           return;
                        }
                    }
                    showPaiFaTiShiDialog();
                }else if (listPFResult.get(position).equals("上报")){
                    if (!"2".equals(getIntent().getStringExtra("BHZT")) && !"1".equals(getIntent().getStringExtra("BHZT")) && !"19".equals(getIntent().getStringExtra("BHZT"))) {
                        MyApplication.app.customToast("只有新病害和审核不通过可以上报，请重新选择");
                        return;
                    }
                    showSBDialog();
                }else if (listPFResult.get(position).equals("审核")){
                    if (!getIntent().getStringExtra("BHZT").equals("17")) {
                        MyApplication.app.customToast("只有已上报的数据可以审核，请重新选择");
                       return;
                    }
                    showSHDialog();
                }
            }
        });
    }
    /**
     * 显示上报提示框
     */
    private void showSBDialog() {
        if (SBDialog == null) {
            String title = "是否立即上报？";
            String okStr = "确认";
            String cancelStr = "取消";
            SBDialog = new CommNotificationDialog(getContext(), title, okStr, cancelStr, new CommBtnListener() {
                @Override
                public void CommOkBtnClick() {
                    showLoadingDialogMethod("上传中");
                    String zhStr="0.0";
                    String zhOneStr=diseaseNewPileNumberOneEdit.getText().toString();
                    String zhTwoStr=diseaseNewPileNumberTwoEdit.getText().toString();
                    if(Utils.isNull(zhTwoStr)){
                        zhStr = zhOneStr;
                    }else{
                        zhStr = zhOneStr + "." + zhTwoStr;
                    }
                    czfaxx czfaxx = new czfaxx();
                    ArrayList<czfaxx.CZFABean> cZFAlist= new ArrayList<>();
                    if (gcxmadapter.get() != null && gcxmadapter.get().size() > 0) {
                        for (int k = 0; k < gcxmadapter.get().size(); k++) {
                            czfaxx.CZFABean   cZFABean  =    new czfaxx.CZFABean();
                            cZFABean.setJSGS(replaceNull(gcxmadapter.get().get(k).getJsgs()));
                            cZFABean.setGCXMID(gcxmadapter.get().get(k).getGcxmid());
                            cZFAlist.add(cZFABean);
                        }
                        czfaxx.setCZFA(cZFAlist);
                    }
                    String JSON = gson.toJson(czfaxx);
                    mPresenter.UpdateBhState("'"+cjbhid+"'","17",zhStr,JSON);
                }

                @Override
                public void CommCancelBtnClick() {

                }
            });
        }
        SBDialog.show();
    }
    /**
     * 显示审核提示框
     */
    private void showSHDialog() {
        LayoutInflater factory = LayoutInflater.from(getContext());//提示框
        final View views = factory.inflate(R.layout.repairshdialog, null);//这里必须是final的
        TextView cancle = (TextView) views.findViewById(R.id.off_commit);
        TextView commit = (TextView) views.findViewById(R.id.commit);
        TextView NOcommit = (TextView) views.findViewById(R.id.NOcommit);
        final EditText et = (EditText) views.findViewById(R.id.et);
        cancle.setText("取消");
        commit.setText("通过");
        NOcommit.setText("不通过");
        final AlertDialog dialog = new AlertDialog.Builder(getContext()).create();
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoadingDialogMethod("上传中");
                String zhStr="0.0";
                String zhOneStr=diseaseNewPileNumberOneEdit.getText().toString();
                String zhTwoStr=diseaseNewPileNumberTwoEdit.getText().toString();
                if(Utils.isNull(zhTwoStr)){
                    zhStr = zhOneStr;
                }else{
                    zhStr = zhOneStr + "." + zhTwoStr;
                }
                czfaxx czfaxx = new czfaxx();
                ArrayList<czfaxx.CZFABean> cZFAlist= new ArrayList<>();
                if (gcxmadapter.get() != null && gcxmadapter.get().size() > 0) {
                    for (int k = 0; k < gcxmadapter.get().size(); k++) {
                        czfaxx.CZFABean   cZFABean  =    new czfaxx.CZFABean();
                        cZFABean.setJSGS(replaceNull(gcxmadapter.get().get(k).getJsgs()));
                        cZFABean.setGCXMID(gcxmadapter.get().get(k).getGcxmid());
                        cZFAlist.add(cZFABean);
                    }
                    czfaxx.setCZFA(cZFAlist);
                }
                String JSON = gson.toJson(czfaxx);
                mPresenter.RovalBhShyj("'"+cjbhid+"'","18",et.getText().toString(),zhStr,JSON);
                dialog.dismiss();
            }
        });
        NOcommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoadingDialogMethod("上传中");
                String zhStr="0.0";
                String zhOneStr=diseaseNewPileNumberOneEdit.getText().toString();
                String zhTwoStr=diseaseNewPileNumberTwoEdit.getText().toString();
                if(Utils.isNull(zhTwoStr)){
                    zhStr = zhOneStr;
                }else{
                    zhStr = zhOneStr + "." + zhTwoStr;
                }
                czfaxx czfaxx = new czfaxx();
                ArrayList<czfaxx.CZFABean> cZFAlist= new ArrayList<>();
                if (gcxmadapter.get() != null && gcxmadapter.get().size() > 0) {
                    for (int k = 0; k < gcxmadapter.get().size(); k++) {
                        czfaxx.CZFABean   cZFABean  =    new czfaxx.CZFABean();
                        cZFABean.setJSGS(replaceNull(gcxmadapter.get().get(k).getJsgs()));
                        cZFABean.setGCXMID(gcxmadapter.get().get(k).getGcxmid());
                        cZFAlist.add(cZFABean);
                    }
                    czfaxx.setCZFA(cZFAlist);
                }
                String JSON = gson.toJson(czfaxx);
                mPresenter.RovalBhShyj("'"+cjbhid+"'","19",et.getText().toString(),zhStr,JSON);
                dialog.dismiss();
            }
        });
        dialog.setView(views);
        dialog.show();
    }
    @Override
    public void getData(Lookdiseasebean detailDataInfo) {
        if (detailDataInfo.getBHDATA() != null) {
            detailInfo = detailDataInfo.getBHDATA().get(0);
            picInfo = detailDataInfo.getTPDZ();
            szfaInfo = detailDataInfo.getCZFADATA();
            if (detailInfo != null) {
                setDataInView();
            }
        }
    }

    @Override
    public void getData2(Dingbean videoVos2) {
        if (videoVos2.getSTATE().equals("1")){
            if (videoVos2.getGPS().size()>0){
                String zhStr=videoVos2.getGPS().get(0).getQDZH();
                if(!Utils.isNull(zhStr)){
                    if(zhStr.contains(".")){
                        String zhOneStr=zhStr.substring(0,zhStr.lastIndexOf("."));
                        String zhTwoStr=zhStr.substring(zhStr.lastIndexOf(".")+1,zhStr.length());
                        diseaseNewPileNumberOneEdit.setText(zhOneStr);
                        diseaseNewPileNumberTwoEdit.setText(zhTwoStr);
                    }else{
                        diseaseNewPileNumberOneEdit.setText(videoVos2.getGPS().get(0).getQDZH());
                        diseaseNewPileNumberTwoEdit.setText("0");
                    }
                    String str=diseaseNewPileNumberOneEdit.getText().toString();
                    diseaseNewPileNumberOneEdit.setSelection(str.length());
                }
            }   else {
                MyApplication.app.customToast("您当前位置暂时无法定位");
            }
        }
    }
    @Override
    public void getData3(repairRKbean videoVos2) {
        if (loadDataDialog != null && loadDataDialog.isShowing()) {
            loadDataDialog.cancel();
        }
        if (videoVos2.getState().equals("1")){
            MyApplication.app.customToast("派发成功");
            MyApplication.spUtils.remove("GZZLBH");
            isSaveOrUpdateData = true;
            finish();
        }else {
            MyApplication.app.customToast("派发失败");
        }
    }

    @Override
    public void getData4(Basebean videoVos2) {
        if (loadDataDialog != null && loadDataDialog.isShowing()) {
            loadDataDialog.cancel();
        }
        if (videoVos2.getSTATE().equals("1")){
            MyApplication.app.customToast("上报成功");
            isSaveOrUpdateData = true;
            finish();
        }else {
            MyApplication.app.customToast("上报失败");
        }
    }

    @Override
    public void getData5(Basebean videoVos2) {
        if (loadDataDialog != null && loadDataDialog.isShowing()) {
            loadDataDialog.cancel();
        }
        if (videoVos2.getSTATE().equals("1")){
            MyApplication.app.customToast("审批成功");
            isSaveOrUpdateData = true;
            finish();
        }else {
            MyApplication.app.customToast("审批失败");
        }
    }

    private void setDataInView() {

        diseaseNewUnitNameTxt.setText(detailInfo.getDCR());
        diseaseNewRoadLineTxt.setText(detailInfo.getLXMC());
        diseaseNewTimeTxt.setText(detailInfo.getDCSJ().replace("T"," "));
        if (detailInfo.getDCLX()!=null){
            diseaseNewToExamineTypeTxt.setText( curingDao.queryInvestigationById(detailInfo.getDCLX()).getDCMC());
        }
        if (detailInfo.getQDZH()!=null){
            String zhStr=detailInfo.getQDZH();
            if(zhStr.contains(".")){
                String zhfStr=zhStr.substring(0,zhStr.indexOf("."));
                String zhaStr=zhStr.substring(zhStr.indexOf(".")+1,zhStr.length());
                diseaseNewPileNumberOneEdit.setText(zhfStr);
                diseaseNewPileNumberTwoEdit.setText(zhaStr);
            }else{
                diseaseNewPileNumberOneEdit.setText(zhStr);
            }
        }
        String xsfxStr = detailInfo.getWZFX();
        if (xsfxStr.equals(diseaseNewDriverDirectionShangTxt.getText())) {
            setShangXingSelect();
        } else if (xsfxStr.equals(diseaseNewDriverDirectionXiaTxt.getText())) {
            setXiaXingSelect();
        } else {
            setQuanFuSelect();
        }
        diseaseCustomEditItemBhlxTxtBtn.setText(detailInfo.getSHBW());
        bhmc.setVisibility(View.VISIBLE);
        diseaseCustomEditItemBhmcTxtBtn.setText(detailInfo.getBHMC());
        sgfs.setVisibility(View.VISIBLE);
        diseaseCustomEditItemSgfsTxtBtn.setText(detailInfo.getCZFAMC());
        if (szfaInfo != null && szfaInfo.size() > 0) {
            for (int i = 0;i<szfaInfo.size();i++){
                ItemBeanInfo itembean = new ItemBeanInfo();
                itembean.setXmmc(szfaInfo.get(i).getGCXM());
                itembean.setGcxmid(szfaInfo.get(i).getGCXMID());
                itembean.setJldw(szfaInfo.get(i).getDW());
                itembean.setJsgs(szfaInfo.get(i).getJSGS());
                gcxmResult.add(itembean);
            }
            gcxmadapter   = new ListAdapter(this,gcxmResult);
            addDdiseaseListView.setAdapter(gcxmadapter);
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
            caiJiPictureAddGrid.setAdapter(imgAdapter);
        }
    }
    private void setShangXingSelect() {
        if (directionSelInt != 1) {
            directionSelInt = 1;
            diseaseNewDriverDirectionShangTxt.setTextColor(getResources().getColor(R.color.white));
            diseaseNewDriverDirectionShangTxt.setBackgroundDrawable(getResources().getDrawable(R.drawable.month_btn_shap));
            diseaseNewDriverDirectionXiaTxt.setTextColor(getResources().getColor(R.color.select_month_text_bg));
            diseaseNewDriverDirectionXiaTxt.setBackgroundDrawable(getResources().getDrawable(R.drawable.direction_select_shap));
            diseaseNewDriverDirectionQuanTxt.setTextColor(getResources().getColor(R.color.select_month_text_bg));
            diseaseNewDriverDirectionQuanTxt.setBackgroundDrawable(getResources().getDrawable(R.drawable.direction_select_shap));
        }
    }
    private void setXiaXingSelect() {
        if (directionSelInt != 2) {
            directionSelInt = 2;
            diseaseNewDriverDirectionShangTxt.setTextColor(getResources().getColor(R.color.select_month_text_bg));
            diseaseNewDriverDirectionShangTxt.setBackgroundDrawable(getResources().getDrawable(R.drawable.direction_select_shap));
            diseaseNewDriverDirectionXiaTxt.setTextColor(getResources().getColor(R.color.white));
            diseaseNewDriverDirectionXiaTxt.setBackgroundDrawable(getResources().getDrawable(R.drawable.month_btn_shap));
            diseaseNewDriverDirectionQuanTxt.setTextColor(getResources().getColor(R.color.select_month_text_bg));
            diseaseNewDriverDirectionQuanTxt.setBackgroundDrawable(getResources().getDrawable(R.drawable.direction_select_shap));
        }
    }

    private void setQuanFuSelect() {
        if (directionSelInt != 3) {
            directionSelInt = 3;
            diseaseNewDriverDirectionShangTxt.setTextColor(getResources().getColor(R.color.select_month_text_bg));
            diseaseNewDriverDirectionShangTxt.setBackgroundDrawable(getResources().getDrawable(R.drawable.direction_select_shap));
            diseaseNewDriverDirectionXiaTxt.setTextColor(getResources().getColor(R.color.select_month_text_bg));
            diseaseNewDriverDirectionXiaTxt.setBackgroundDrawable(getResources().getDrawable(R.drawable.direction_select_shap));
            diseaseNewDriverDirectionQuanTxt.setTextColor(getResources().getColor(R.color.white));
            diseaseNewDriverDirectionQuanTxt.setBackgroundDrawable(getResources().getDrawable(R.drawable.month_btn_shap));
        }
    }
    @Override
    public void onRequestError(String msg) {

    }

    @Override
    public void onRequestEnd() {

    }
    /********定位功能开始**********/
    /**
     * 初始化定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void initLocation() {
        //初始化client
        locationClient = new AMapLocationClient(this.getApplicationContext());
        locationOption = getDefaultOption();
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
    }

    /**
     * 默认的定位参数
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(true);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        mOption.setGeoLanguage(AMapLocationClientOption.GeoLanguage.DEFAULT);//可选，设置逆地理信息的语言，默认值为默认语言（根据所在地区选择语言）
        return mOption;
    }

    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {//定位成功
                Double longitude = aMapLocation.getLongitude();//经度
                Double latitude = aMapLocation.getLatitude();//纬度
                Log.i("定位结果","经度"+String.valueOf(longitude)+" 纬度"+String.valueOf(latitude));
                mPresenter.getZH(detailInfo.getLXCODE(),String.valueOf(longitude),String.valueOf(latitude),gydwid);
            } else {//定位失败
                Log.i("定位失败","定位失败");
            }
            stopLocation();
        }
    };

    /**
     * 开始定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void startLocation() {
        // 设置定位参数
//        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }

    /**
     * 停止定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void stopLocation() {
        // 停止定位
        locationClient.stopLocation();
    }

    /**
     * 销毁定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void destroyLocation() {
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyLocation();
        curingDao.release();
    }
}
