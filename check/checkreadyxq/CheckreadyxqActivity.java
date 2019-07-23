package com.zggk.newiroad.check.checkreadyxq;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zggk.newiroad.Bean.CheckxqBean;
import com.zggk.newiroad.Bean.ItemSgjdInfo;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.R;
import com.zggk.newiroad.check.YsDialog;
import com.zggk.newiroad.check.checkxq.Checkxqadapter;
import com.zggk.newiroad.check.cjjdAdapter;
import com.zggk.newiroad.check.pfjdAdapter;
import com.zggk.newiroad.db.dbhelper.CuringDaoImpl;
import com.zggk.newiroad.disease.SendTopicPictureGridAdapter;
import com.zggk.newiroad.handle.LoadDataDialog;
import com.zggk.newiroad.handle.inwaihandle.ShowImgActivity;
import com.zggk.newiroad.mvp.MVPBaseActivity;
import com.zggk.newiroad.utils.ListViewForScrollView;
import com.zggk.newiroad.utils.NoScroolGridView;
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

public class CheckreadyxqActivity extends MVPBaseActivity<CheckreadyxqContract.View, CheckreadyxqPresenter> implements CheckreadyxqContract.View {

    @Bind(R.id.go_back)
    LinearLayout goBack;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.right)
    TextView right;
    @Bind(R.id.disease_detail_road_line_txt)
    TextView diseaseDetailRoadLineTxt;
    @Bind(R.id.disease_detail_time_txt)
    TextView diseaseDetailTimeTxt;
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
    @Bind(R.id.sg_te_lay)
    LinearLayout sgTeLay;
    @Bind(R.id.sgjd_list)
    ListViewForScrollView sgjdList;
    @Bind(R.id.pfjd_te)
    TextView pfjdTe;
    @Bind(R.id.fa_te_lay)
    LinearLayout faTeLay;
    @Bind(R.id.pfjd_list)
    ListViewForScrollView pfjdList;
    @Bind(R.id.cjjd_te)
    TextView cjjdTe;
    @Bind(R.id.ys_te_lay)
    LinearLayout ysTeLay;
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
    @Bind(R.id.czwt_et)
    TextView czwtEt;
    @Bind(R.id.czwt_bu)
    TextView czwtBu;
    @Bind(R.id.activity_disease_detail_scrollview)
    ScrollView activityDiseaseDetailScrollview;
    @Bind(R.id.ysjd_te)
    TextView ysjdTe;
    @Bind(R.id.ysjd_te_lay)
    LinearLayout ysjdTeLay;
    @Bind(R.id.ysjd_list)
    ListViewForScrollView ysjdList;
    private CuringDaoImpl curingDao;
    private List<CheckxqBean.LXUPDATEISTBean> LXDATE = new ArrayList<>();
    private ArrayList<ItemSgjdInfo> sggcxmResult = new ArrayList<>();
    private ArrayList<ItemSgjdInfo> ysgcxmResult = new ArrayList<>();
    private ArrayList<ItemSgjdInfo> pfgcxmResult = new ArrayList<>();
    private ArrayList<ItemSgjdInfo> cjgcxmResult = new ArrayList<>();

    private pfjdAdapter pfgcxmadapter;
    private cjjdAdapter cjgcxmadapter;
    private List<String> cjTP = new ArrayList<>();
    private YsDialog warmDialog;
    private Gson gson = new Gson();
    private LoadDataDialog loadDataDialog;
    private CommonAdapter<ItemSgjdInfo> sggcxmadapter;
    private CommonAdapter<ItemSgjdInfo> ysgcxmadapter;
    private String SGQTP,SGZTP,SGHTP;;
    private ArrayList<String> sGQTP = new ArrayList<>();
    private ArrayList<String> sGZTP = new ArrayList<>();
    private ArrayList<String> sGHTP = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.act_check_rexq);
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
                    Intent intent = new Intent(CheckreadyxqActivity.this, ShowImgActivity.class);
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
                    Intent intent = new Intent(CheckreadyxqActivity.this, ShowImgActivity.class);
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
                    Intent intent = new Intent(CheckreadyxqActivity.this, ShowImgActivity.class);
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
        czwtEt.setText(replaceNull(videoVos.getBHDATA().get(0).getYSBZ()));
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

        if (videoVos.getYSCZFA() != null && videoVos.getYSCZFA().size() > 0) {
            ysjdTeLay.setVisibility(View.VISIBLE);
            ysjdTe.setText(Html.fromHtml("<font color=\"#666666\">" + "由" + "</font>" + replaceNull(videoVos.getBHDATA().get(0).getSGDW()) + "<font color=\"#666666\">" + "在" + "</font>施工阶段<font color=\"#666666\">" + "录入" + "</font>"));
            for (int i = 0; i < videoVos.getYSCZFA().size(); i++) {
                ItemSgjdInfo itembean = new ItemSgjdInfo();
                itembean.setGCXM(videoVos.getYSCZFA().get(i).getGCXM());
                itembean.setGCXMID(videoVos.getYSCZFA().get(i).getGCXMID());
                itembean.setJSGS(videoVos.getYSCZFA().get(i).getJSGS());
                itembean.setSL(videoVos.getYSCZFA().get(i).getSL());
                itembean.setDW(videoVos.getYSCZFA().get(i).getDW());
                itembean.setHD(videoVos.getYSCZFA().get(i).getHD());
                ysgcxmResult.add(itembean);
            }
            ysgcxmadapter = new CommonAdapter<ItemSgjdInfo>(this,
                    R.layout.item_readyhandleitem, ysgcxmResult) {
                @Override
                protected void convert(ViewHolder holder, ItemSgjdInfo tubiaoVo, int position) {
                    holder.setText(R.id.nr1, tubiaoVo.getGCXM());
                    holder.setText(R.id.ed_hd, tubiaoVo.getHD());
                    holder.setText(R.id.ed, tubiaoVo.getJSGS());
                    holder.setText(R.id.dw, "=" + tubiaoVo.getSL() + "  " + tubiaoVo.getDW());
                }
            };
            ysjdList.setAdapter(ysgcxmadapter);
        }


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
            sggcxmadapter = new CommonAdapter<ItemSgjdInfo>(this,
                    R.layout.item_readyhandleitem, sggcxmResult) {
                @Override
                protected void convert(ViewHolder holder, ItemSgjdInfo tubiaoVo, int position) {
                    holder.setText(R.id.nr1, tubiaoVo.getGCXM());
                    holder.setText(R.id.ed_hd, tubiaoVo.getHD());
                    holder.setText(R.id.ed, tubiaoVo.getJSGS());
                    holder.setText(R.id.dw, "=" + tubiaoVo.getSL() + "  " + tubiaoVo.getDW());
                }
            };
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
            Glide.with(CheckreadyxqActivity.this)
                    .asBitmap()
                    .apply(MyApplication.app.options)
                    .load(videoVos.getSGQTP())
                    .into(shigongqian);
        }
        if (!videoVos.getSGZTP().equals("") || videoVos.getSGZTP() != null) {
            SGZTP = videoVos.getSGZTP();
            Glide.with(CheckreadyxqActivity.this)
                    .asBitmap()
                    .apply(MyApplication.app.options)
                    .load(videoVos.getSGZTP())
                    .into(shigongzhong);
        }
        if (!videoVos.getSGHTP().equals("") || videoVos.getSGHTP() != null) {
            SGHTP = videoVos.getSGHTP();
            Glide.with(CheckreadyxqActivity.this)
                    .asBitmap()
                    .apply(MyApplication.app.options)
                    .load(videoVos.getSGHTP())
                    .into(shigonghou);
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

