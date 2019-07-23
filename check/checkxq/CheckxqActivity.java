package com.zggk.newiroad.check.checkxq;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zggk.newiroad.Bean.CheckxqBean;
import com.zggk.newiroad.Bean.ItemSgjdInfo;
import com.zggk.newiroad.Bean.ysjsonbean;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.R;
import com.zggk.newiroad.check.YsDialog;
import com.zggk.newiroad.check.cjjdAdapter;
import com.zggk.newiroad.check.pfjdAdapter;
import com.zggk.newiroad.check.sgjdAdapter;
import com.zggk.newiroad.db.dbhelper.CuringDaoImpl;
import com.zggk.newiroad.disease.SendTopicPictureGridAdapter;
import com.zggk.newiroad.handle.LoadDataDialog;
import com.zggk.newiroad.handle.inwaihandle.ShowImgActivity;
import com.zggk.newiroad.mvp.MVPBaseActivity;

import com.zggk.newiroad.repair.ToExamineWarmDialog;
import com.zggk.newiroad.utils.Dialog.CommBtnListener;
import com.zggk.newiroad.utils.ListViewForScrollView;
import com.zggk.newiroad.utils.NoScroolGridView;
import com.zggk.newiroad.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class CheckxqActivity extends MVPBaseActivity<CheckxqContract.View, CheckxqPresenter> implements CheckxqContract.View {
    @Bind(R.id.go_back)
    LinearLayout goBack;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.right)
    TextView right;
    @Bind(R.id.disease_detail_road_line_txt)
    TextView diseaseDetailRoadLineTxt;
    @Bind(R.id.rv_lx)
    ListViewForScrollView rvLx;
    @Bind(R.id.disease_detail_driver_direction_txt)
    TextView diseaseDetailDriverDirectionTxt;
    @Bind(R.id.disease_detail_contract_txt)
    TextView diseaseDetailContractTxt;
    @Bind(R.id.disease_detail_del_list_txt)
    TextView diseaseDetailDelListTxt;
    @Bind(R.id.sgjd_te)
    TextView sgjdTe;
    @Bind(R.id.sgjd_list)
    ListViewForScrollView sgjdList;
    @Bind(R.id.pfjd_te)
    TextView pfjdTe;
    @Bind(R.id.pfjd_list)
    ListViewForScrollView pfjdList;
    @Bind(R.id.cjjd_te)
    TextView cjjdTe;
    @Bind(R.id.cjjd_list)
    ListViewForScrollView cjjdList;
    @Bind(R.id.cai_ji_picture_add_grid)
    NoScroolGridView caiJiPictureAddGrid;
    @Bind(R.id.shigongqian)
    ImageView shigongqian;
    @Bind(R.id.shigongzhong)
    ImageView shigongzhong;
    @Bind(R.id.shigonghou)
    ImageView shigonghou;
    @Bind(R.id.activity_disease_detail_scrollview)
    ScrollView activityDiseaseDetailScrollview;
    @Bind(R.id.save)
    TextView save;
    @Bind(R.id.add_log)
    TextView addLog;
    @Bind(R.id.yan_shou_bottom_btn_layout)
    LinearLayout yanShouBottomBtnLayout;
    @Bind(R.id.disease_detail_time_txt)
    TextView diseaseDetailTimeTxt;
    @Bind(R.id.sg_te_lay)
    LinearLayout sgTeLay;
    @Bind(R.id.fa_te_lay)
    LinearLayout faTeLay;
    @Bind(R.id.ys_te_lay)
    LinearLayout ysTeLay;
    @Bind(R.id.czwt_et)
    EditText czwtEt;

    private CuringDaoImpl curingDao;
    private List<CheckxqBean.LXUPDATEISTBean> LXDATE = new ArrayList<>();
    private ArrayList<ItemSgjdInfo> sggcxmResult = new ArrayList<>();
    private ArrayList<ItemSgjdInfo> pfgcxmResult = new ArrayList<>();
    private ArrayList<ItemSgjdInfo> cjgcxmResult = new ArrayList<>();
    private sgjdAdapter sggcxmadapter;
    private pfjdAdapter pfgcxmadapter;
    private cjjdAdapter cjgcxmadapter;
    private List<String> cjTP = new ArrayList<>();
    private YsDialog warmDialog;
    private Gson gson = new Gson();
    private LoadDataDialog loadDataDialog;
    private String SGQTP,SGZTP,SGHTP;;
    private ArrayList<String> sGQTP = new ArrayList<>();
    private ArrayList<String> sGZTP = new ArrayList<>();
    private ArrayList<String> sGHTP = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.act_check_xq);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        ButterKnife.bind(this);
        initdate();
        linstener();
        String str = "正在加载...";
        showLoadingDialogMethod(str);
    }

    private void linstener() {
        shigongqian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!SGQTP.equals("")) {
                    sGQTP.clear();
                    sGQTP.add(SGQTP);
                    Intent intent = new Intent(CheckxqActivity.this, ShowImgActivity.class);
                    intent.putExtra("img2", sGQTP);
                    intent.putExtra("position", 0);
                    startActivity(intent);
                }

            }
        });
        shigongzhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!SGZTP.equals("")) {
                    sGZTP.clear();
                    sGZTP.add(SGZTP);
                    Intent intent = new Intent(CheckxqActivity.this, ShowImgActivity.class);
                    intent.putExtra("img2", sGZTP);
                    intent.putExtra("position", 0);
                    startActivity(intent);
                }
            }
        });
        shigonghou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!SGHTP.equals("")) {
                    sGHTP.clear();
                    sGHTP.add(SGHTP);
                    Intent intent = new Intent(CheckxqActivity.this, ShowImgActivity.class);
                    intent.putExtra("img2", sGHTP);
                    intent.putExtra("position", 0);
                    startActivity(intent);
                }
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (czwtEt.getText().toString().equals("")){
                    MyApplication.app.customToast("请输入验收意见");
                }else {
                    save.setClickable(false);
                    showPaiFaTiShiDialog(1);
                }
            }
        });
        addLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLog.setClickable(false);
                showPaiFaTiShiDialog(2);
            }
        });
    }
    private void showPaiFaTiShiDialog(final int type) {
        if (warmDialog == null) {
            warmDialog = new YsDialog(this, new CommBtnListener() {
                @Override
                public void CommOkBtnClick() {
                    String str = "上传中";
                    showLoadingDialogMethod(str);
                    ysCommit(type);
                    warmDialog.close();
                }

                @Override
                public void CommCancelBtnClick() {
                    if (type==1){
                        save.setClickable(true);
                    }else {
                        addLog.setClickable(true);
                    }

                }
            });
        }
        warmDialog.show();
    }

    private void ysCommit(int type) {
        ysjsonbean ysjsonbean =    new ysjsonbean();
        ysjsonbean.setBHGUID(getIntent().getStringExtra("BHID"));
        ysjsonbean.setYSDW(MyApplication.spUtils.getString("dqgydwid"));
        ysjsonbean.setYSR(MyApplication.spUtils.getString("dlr"));
        ysjsonbean.setBZ(czwtEt.getText().toString());
        ArrayList<ysjsonbean.CZFABean> listCzr = new ArrayList<>();
        for (int i =0;i<sggcxmadapter.get().size();i++){
            ysjsonbean.CZFABean   czfa =         new  ysjsonbean.CZFABean();
            czfa.setGCXMID(replaceNull(sggcxmadapter.get().get(i).getGCXMID()));
            czfa.setHD(replaceNull(sggcxmadapter.get().get(i).getHD()));
            czfa.setJSGS(replaceNull(sggcxmadapter.get().get(i).getJSGS()));
            listCzr.add(czfa);
        }
        ysjsonbean.setCZFA(listCzr);
        String tijiaodates = gson.toJson(ysjsonbean);
        Log.e("测试",tijiaodates);
        if (type==1){
           String types = "1";
            mPresenter.add(tijiaodates,types);
        }else {
            String types = "0";
            mPresenter.add(tijiaodates,types);
        }
    }

    private void initdate() {
        curingDao = new CuringDaoImpl(this);
        title.setText("验收详情");
        mPresenter.getxqdate(getIntent().getStringExtra("BHID"));
    }

    @Override
    public void onRequestError(String msg) {

    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void getData(CheckxqBean videoVos) {
        if (loadDataDialog != null && loadDataDialog.isShowing()) {
            loadDataDialog.cancel();
        }
        diseaseDetailRoadLineTxt.setText(replaceNull(videoVos.getBHDATA().get(0).getLXMC()));
        String zhStr = videoVos.getBHDATA().get(0).getQDZH();
        if (zhStr.contains(".")) {
            String zhfStr = zhStr.substring(0, zhStr.indexOf("."));
            String zhaStr = zhStr.substring(zhStr.indexOf(".") + 1, zhStr.length());
            if (zhaStr.length() == 1) {
                zhaStr += "00";
            } else if (zhaStr.length() == 2) {
                zhaStr += "0";
            }
            diseaseDetailTimeTxt.setText("K" + zhfStr + "+" + zhaStr);
        } else {
            diseaseDetailTimeTxt.setText("K" + zhStr);
        }
        diseaseDetailContractTxt.setText(replaceNull(videoVos.getBHDATA().get(0).getBHLX()));
        diseaseDetailDelListTxt.setText(replaceNull(videoVos.getBHDATA().get(0).getCZFAMC()));
        LXDATE.clear();
        if (videoVos.getBHDATA().get(0).getQDZHWX() != null) {
            CheckxqBean.LXUPDATEISTBean wxLXbean = new CheckxqBean.LXUPDATEISTBean();
            wxLXbean.setDW(videoVos.getBHDATA().get(0).getSGDW());
            wxLXbean.setSL(videoVos.getBHDATA().get(0).getQDZHWX());
            wxLXbean.setType("1");
            LXDATE.add(wxLXbean);
        }
        if (videoVos.getBHDATA().get(0).getQDZHPF() != null) {
            CheckxqBean.LXUPDATEISTBean wxLXbean = new CheckxqBean.LXUPDATEISTBean();
            wxLXbean.setDW(videoVos.getBHDATA().get(0).getJLDW());
            wxLXbean.setSL(videoVos.getBHDATA().get(0).getQDZHPF());
            wxLXbean.setType("2");
            LXDATE.add(wxLXbean);
        }
        if (videoVos.getBHDATA().get(0).getQDZH() != null) {
            CheckxqBean.LXUPDATEISTBean wxLXbean = new CheckxqBean.LXUPDATEISTBean();
            wxLXbean.setDW(videoVos.getBHDATA().get(0).getGYDW());
            wxLXbean.setSL(videoVos.getBHDATA().get(0).getQDZH());
            wxLXbean.setType("3");
            LXDATE.add(wxLXbean);
        }
        rvLx.setAdapter(new Checkxqadapter(this, LXDATE));
        if (videoVos.getSGCZFA() != null && videoVos.getSGCZFA().size() > 0) {
            sgTeLay.setVisibility(View.VISIBLE);
            sgjdTe.setText(Html.fromHtml("<font color=\"#666666\">" + "由" + "</font>" + replaceNull(videoVos.getBHDATA().get(0).getSGDW()) + "<font color=\"#666666\">" + "在" + "</font>施工阶段<font color=\"#666666\">" + "录入" + "</font>"));
            for (int i = 0; i < videoVos.getSGCZFA().size(); i++) {
                ItemSgjdInfo itembean = new ItemSgjdInfo();
                itembean.setGCXM(videoVos.getSGCZFA().get(i).getGCXM());
                itembean.setGCXMID(videoVos.getSGCZFA().get(i).getGCXMID());
                itembean.setJSGS(videoVos.getSGCZFA().get(i).getJSGS());
                itembean.setSL(videoVos.getSGCZFA().get(i).getSL());
                itembean.setDW(videoVos.getSGCZFA().get(i).getDW());
                itembean.setHD(videoVos.getSGCZFA().get(i).getHD());
                sggcxmResult.add(itembean);
            }
            sggcxmadapter = new sgjdAdapter(this, sggcxmResult);
            sgjdList.setAdapter(sggcxmadapter);
        }
        if (videoVos.getPFCZFA() != null && videoVos.getPFCZFA().size() > 0) {
            faTeLay.setVisibility(View.VISIBLE);
            pfjdTe.setText(Html.fromHtml("<font color=\"#666666\">" + "由" + "</font>" + replaceNull(videoVos.getBHDATA().get(0).getJLDW()) + "<font color=\"#666666\">" + "在" + "</font>派发阶段<font color=\"#666666\">" + "录入" + "</font>"));
            for (int i = 0; i < videoVos.getPFCZFA().size(); i++) {
                ItemSgjdInfo itembean = new ItemSgjdInfo();
                itembean.setGCXM(videoVos.getPFCZFA().get(i).getGCXM());
                itembean.setGCXMID(videoVos.getPFCZFA().get(i).getGCXMID());
                itembean.setJSGS(videoVos.getPFCZFA().get(i).getJSGS());
                itembean.setSL(videoVos.getPFCZFA().get(i).getSL());
                itembean.setDW(videoVos.getPFCZFA().get(i).getDW());
                pfgcxmResult.add(itembean);
            }
            pfgcxmadapter = new pfjdAdapter(this, pfgcxmResult);
            pfjdList.setAdapter(pfgcxmadapter);
        }
        if (videoVos.getCJCZFA() != null && videoVos.getCJCZFA().size() > 0) {
            ysTeLay.setVisibility(View.VISIBLE);
            cjjdTe.setText(Html.fromHtml("<font color=\"#666666\">" + "由" + "</font>" + replaceNull(videoVos.getBHDATA().get(0).getGYDW()) + "<font color=\"#666666\">" + "在" + "</font>采集阶段<font color=\"#666666\">" + "录入" + "</font>"));
            for (int i = 0; i < videoVos.getCJCZFA().size(); i++) {
                ItemSgjdInfo itembean = new ItemSgjdInfo();
                itembean.setGCXM(videoVos.getCJCZFA().get(i).getGCXM());
                itembean.setGCXMID(videoVos.getCJCZFA().get(i).getGCXMID());
                itembean.setJSGS(videoVos.getCJCZFA().get(i).getJSGS());
                itembean.setSL(videoVos.getCJCZFA().get(i).getSL());
                itembean.setDW(videoVos.getCJCZFA().get(i).getDW());
                cjgcxmResult.add(itembean);
            }
            cjgcxmadapter = new cjjdAdapter(this, cjgcxmResult);
            cjjdList.setAdapter(cjgcxmadapter);
        }
        cjTP.clear();
        for (int j = 0; j < videoVos.getCJTP().size(); j++) {
            cjTP.add(videoVos.getCJTP().get(j).getFILEPATH());

        }
        ArrayList<String> listImgUrl = new ArrayList<>();
        if (cjTP.size() == 0) {
            listImgUrl.add(String.valueOf(R.drawable.morentu));
        } else {
            listImgUrl.addAll(cjTP);
        }
        SendTopicPictureGridAdapter addPictureAdapter = new SendTopicPictureGridAdapter(this, listImgUrl);
        caiJiPictureAddGrid.setAdapter(addPictureAdapter);
        if (!videoVos.getSGQTP().equals("") || videoVos.getSGQTP() != null) {
            SGQTP = videoVos.getSGQTP();
            Glide.with(CheckxqActivity.this)
                    .asBitmap()
                    .apply(MyApplication.app.options)
                    .load(videoVos.getSGQTP())
                    .into(shigongqian);
        }
        if (!videoVos.getSGZTP().equals("") || videoVos.getSGZTP() != null) {
            SGZTP = videoVos.getSGZTP();
            Glide.with(CheckxqActivity.this)
                    .asBitmap()
                    .apply(MyApplication.app.options)
                    .load(videoVos.getSGZTP())
                    .into(shigongzhong);

        }
        if (!videoVos.getSGHTP().equals("") || videoVos.getSGHTP() != null) {
            SGHTP = videoVos.getSGHTP();
            Glide.with(CheckxqActivity.this)
                    .asBitmap()
                    .apply(MyApplication.app.options)
                    .load(videoVos.getSGHTP())
                    .into(shigonghou);
        }
    }

    @Override
    public void getData2(CheckxqBean videoVos) {
        if (loadDataDialog != null && loadDataDialog.isShowing()) {
            loadDataDialog.cancel();
        }
        save.setClickable(true);
        addLog.setClickable(true);
        if(videoVos.getSTATE().equals("1")){
            MyApplication.app.customToast("提交成功");
            Intent intent = new Intent();
            setResult(2, intent);
            finish();
        }
    }

    public static String replaceNull(String str) {
        if (str == null || str.equals("null")) {
            return "";
        } else {
            return str;
        }

    }
    private void showLoadingDialogMethod(String str) {
        if (loadDataDialog == null) {
            loadDataDialog = new LoadDataDialog(this);
        }
        loadDataDialog.setTitleStr(str);
        loadDataDialog.show();
    }
}
