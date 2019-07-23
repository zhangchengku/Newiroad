package com.zggk.newiroad.season.seasonrealy;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zggk.newiroad.Bean.Seasonhandlebean;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.R;
import com.zggk.newiroad.handle.LoadDataDialog;
import com.zggk.newiroad.mvp.MVPBaseActivity;
import com.zggk.newiroad.utils.Utils;
import butterknife.Bind;
import butterknife.ButterKnife;
/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SeasonRealyActivity extends MVPBaseActivity<SeasonRealyContract.View, SeasonRealyPresenter> implements SeasonRealyContract.View {


    @Bind(R.id.go_back)
    LinearLayout goBack;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.right)
    TextView right;
    @Bind(R.id.activity_new_disease_zhe_zhao_layout)
    View activityNewDiseaseZheZhaoLayout;
    @Bind(R.id.luxian_warm_txt)
    TextView luxianWarmTxt;
    @Bind(R.id.disease_new_road_line_txt_label)
    TextView diseaseNewRoadLineTxtLabel;
    @Bind(R.id.disease_new_road_line_txt)
    TextView diseaseNewRoadLineTxt;
    @Bind(R.id.disease_new_road_line_layout)
    RelativeLayout diseaseNewRoadLineLayout;
    @Bind(R.id.qdzh_x)
    TextView qdzhX;
    @Bind(R.id.qdzh_te)
    TextView qdzhTe;
    @Bind(R.id.qdzh_dw)
    TextView qdzhDw;
    @Bind(R.id.qdzh_text1)
    TextView qdzhText1;
    @Bind(R.id.qdzh_lj)
    TextView qdzhLj;
    @Bind(R.id.qdzh_text2)
    TextView qdzhText2;
    @Bind(R.id.zdzh_x)
    TextView zdzhX;
    @Bind(R.id.zdzh_te)
    TextView zdzhTe;
    @Bind(R.id.zdzh_dw)
    TextView zdzhDw;
    @Bind(R.id.zdzh_text1)
    TextView zdzhText1;
    @Bind(R.id.zdzh_lj)
    TextView zdzhLj;
    @Bind(R.id.zdzh_text2)
    TextView zdzhText2;
    @Bind(R.id.disease_new_driver_direction_shang_txt)
    TextView diseaseNewDriverDirectionShangTxt;
    @Bind(R.id.disease_new_driver_direction_xia_txt)
    TextView diseaseNewDriverDirectionXiaTxt;
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
    @Bind(R.id.czwt_et)
    TextView czwtEt;
    @Bind(R.id.sgsl_t)
    TextView sgslT;
    @Bind(R.id.sgsl_tee)
    TextView sgslTee;
    @Bind(R.id.sgsl_dw)
    TextView sgslDw;
    @Bind(R.id.sgsl)
    LinearLayout sgsl;
    @Bind(R.id.czwt_et2)
    TextView czwtEt2;
    @Bind(R.id.shigongqian)
    ImageView shigongqian;
    @Bind(R.id.shigongqiande)
    ImageView shigongqiande;
    @Bind(R.id.shigongzhong)
    ImageView shigongzhong;
    @Bind(R.id.shigongzhongde)
    ImageView shigongzhongde;
    @Bind(R.id.shigonghou)
    ImageView shigonghou;
    @Bind(R.id.shigonghoude)
    ImageView shigonghoude;
    @Bind(R.id.activity_disease_new_scrollview)
    ScrollView activityDiseaseNewScrollview;
    @Bind(R.id.disease_new_parent_layout)
    RelativeLayout diseaseNewParentLayout;
    private int directionSelInt = 0;// 1上行-右 2下行-左 3双向-全幅
    private String SGQ,SGZ,SGH;
    private LoadDataDialog loadDataDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.act_season_realy);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        ButterKnife.bind(this);
        title.setText("验收详情");
        String str = "正在加载...";
        showLoadingDialogMethod(str);
        initdate();
        initsener();
    }
    private void showLoadingDialogMethod(String str) {
        if (loadDataDialog == null) {
            loadDataDialog = new LoadDataDialog(this);
        }
        loadDataDialog.setTitleStr(str);
        loadDataDialog.show();
    }
    private void initsener() {
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initdate() {
        mPresenter.getdate(getIntent().getStringExtra("ID"));
    }

    @Override
    public void getData(Seasonhandlebean videoVos2) {
        diseaseNewRoadLineTxt.setText(replaceNull(videoVos2.getJJXYH().get(0).getLXMC()));
        String zhStr = replaceNull(videoVos2.getJJXYH().get(0).getQDZH());
        if(zhStr.contains(".")){
            String zhone = zhStr.substring(0, zhStr.indexOf("."));
            String zhtwo = zhStr.substring(zhStr.indexOf(".") + 1, zhStr.length());
            qdzhText1.setText(zhone);
            qdzhText2.setText(zhtwo);
        }else{
            qdzhText1.setText(zhStr);
        }
        String ZzhStr = replaceNull(videoVos2.getJJXYH().get(0).getZDZH());
        if(ZzhStr.contains(".")){
            String zhone = ZzhStr.substring(0, ZzhStr.indexOf("."));
            String zhtwo = ZzhStr.substring(ZzhStr.indexOf(".") + 1, ZzhStr.length());
            zdzhText1.setText(zhone);
            zdzhText2.setText(zhtwo);
        }else{
            zdzhText1.setText(ZzhStr);
        }
        if (videoVos2.getJJXYH().get(0).getWZFX().equals("上行")){
            setShangXingSelect();
        }else {
            setXiaXingSelect();
        }
        diseaseCustomEditItemBhlxTxtBtn.setText(replaceNull(videoVos2.getJJXYH().get(0).getSGDWMC()));
        diseaseCustomEditItemBhmcTxtBtn.setText(replaceNull(videoVos2.getJJXYH().get(0).getSGLXMC()));
        czwtEt.setText(replaceNull(videoVos2.getJJXYH().get(0).getRWYQ()));
        sgslTee.setText(replaceNull(videoVos2.getJJXYH().get(0).getSGLXSL()));
        sgslDw.setText(replaceNull(videoVos2.getJJXYH().get(0).getSGLXDW()));
        czwtEt2.setText(replaceNull(videoVos2.getJJXYH().get(0).getSGSM()));
        if (videoVos2.getPIC().size()>0&&videoVos2.getPIC()!=null){
            SGQ=videoVos2.getPIC().get(0).getFILEPATH();
            SGZ=videoVos2.getPIC().get(1).getFILEPATH();
            SGH=videoVos2.getPIC().get(2).getFILEPATH();
            if (SGQ != null) {
                Glide.with(SeasonRealyActivity.this)
                        .asBitmap()
                        .apply(MyApplication.app.options)
                        .load(SGQ)
                        .into(shigongqian);
            }
            if (SGZ != null) {
                Glide.with(SeasonRealyActivity.this)
                        .asBitmap()
                        .apply(MyApplication.app.options)
                        .load(SGZ)
                        .into(shigongzhong);
            }
            if (SGH != null) {
                Glide.with(SeasonRealyActivity.this)
                        .asBitmap()
                        .apply(MyApplication.app.options)
                        .load(SGH)
                        .into(shigonghou);
            }
        }
        if (loadDataDialog != null && loadDataDialog.isShowing()) {
            loadDataDialog.cancel();
        }
    }
    public static String replaceNull(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }
    private void setShangXingSelect() {
        if (directionSelInt != 1) {
            directionSelInt = 1;
            diseaseNewDriverDirectionShangTxt.setTextColor(getResources().getColor(R.color.white));
            diseaseNewDriverDirectionShangTxt.setBackgroundDrawable(getResources().getDrawable(R.drawable.month_btn_shap));
            diseaseNewDriverDirectionXiaTxt.setTextColor(getResources().getColor(R.color.select_month_text_bg));
            diseaseNewDriverDirectionXiaTxt.setBackgroundDrawable(getResources().getDrawable(R.drawable.direction_select_shap));
        }
    }
    private void setXiaXingSelect() {
        if (directionSelInt != 2) {
            directionSelInt = 2;
            diseaseNewDriverDirectionShangTxt.setTextColor(getResources().getColor(R.color.select_month_text_bg));
            diseaseNewDriverDirectionShangTxt.setBackgroundDrawable(getResources().getDrawable(R.drawable.direction_select_shap));
            diseaseNewDriverDirectionXiaTxt.setTextColor(getResources().getColor(R.color.white));
            diseaseNewDriverDirectionXiaTxt.setBackgroundDrawable(getResources().getDrawable(R.drawable.month_btn_shap));
        }
    }
    @Override
    public void onRequestError(String msg) {

    }

    @Override
    public void onRequestEnd() {

    }
}
