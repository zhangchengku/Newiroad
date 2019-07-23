package com.zggk.newiroad.repair.lookrepairrely;


import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.zggk.newiroad.Bean.BhDetailInfo;
import com.zggk.newiroad.Bean.CZFADATABean;
import com.zggk.newiroad.Bean.ItemBeanInfo;
import com.zggk.newiroad.Bean.Lookdiseasebean;
import com.zggk.newiroad.Bean.TPListInfo;
import com.zggk.newiroad.R;
import com.zggk.newiroad.db.dbhelper.CuringDaoImpl;
import com.zggk.newiroad.disease.AddLogPictureGridAdapter;
import com.zggk.newiroad.mvp.MVPBaseActivity;
import com.zggk.newiroad.utils.ListViewForScrollView;
import com.zggk.newiroad.utils.NoScroolGridView;
import com.zggk.newiroad.utils.Utils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class LookRepairRelyActivity extends MVPBaseActivity<LookRepairRelyContract.View, LookRepairRelyPresenter> implements LookRepairRelyContract.View {
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
    TextView diseaseNewPileNumberOneEdit;
    @Bind(R.id.disease_new_pile_number_jia)
    TextView diseaseNewPileNumberJia;
    @Bind(R.id.disease_new_pile_number_two_edit)
    TextView diseaseNewPileNumberTwoEdit;
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
    @Bind(R.id.activity_new_disease_zhe_zhao_layout)
    View activityNewDiseaseZheZhaoLayout;
    @Bind(R.id.disease_new_parent_layout)
    RelativeLayout diseaseNewParentLayout;
    private CuringDaoImpl curingDao;
    private String cjbhid;
    private BhDetailInfo detailInfo;
    private ArrayList<TPListInfo> picInfo = new ArrayList<>();
    private ArrayList<CZFADATABean> szfaInfo = new ArrayList<>();
    private int directionSelInt;
    private ReapirRelyAdapter gcxmadapter;
    private ArrayList<ItemBeanInfo> gcxmResult = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        Utils.setStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.look_repair_rely);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        ButterKnife.bind(this);
        curingDao = new CuringDaoImpl(this);
        cjbhid = getIntent().getStringExtra("cjbhid");
        mPresenter.testinfo(cjbhid);
        initview();


    }
    private void initview() {
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title.setText("派发详情");
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

    private void setDataInView() {

        diseaseNewUnitNameTxt.setText(detailInfo.getDCR());
        diseaseNewRoadLineTxt.setText(detailInfo.getLXMC());
        diseaseNewTimeTxt.setText(detailInfo.getDCSJ().replace("T", " "));
        if (detailInfo.getDCLX() != null) {
            diseaseNewToExamineTypeTxt.setText(curingDao.queryInvestigationById(detailInfo.getDCLX()).getDCMC());
        }
        if (detailInfo.getQDZH() != null) {
            String zhStr = detailInfo.getQDZH();
            if (zhStr.contains(".")) {
                String zhfStr = zhStr.substring(0, zhStr.indexOf("."));
                String zhaStr = zhStr.substring(zhStr.indexOf(".") + 1, zhStr.length());
                diseaseNewPileNumberOneEdit.setText(zhfStr);
                diseaseNewPileNumberTwoEdit.setText(zhaStr);
            } else {
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
            for (int i = 0; i < szfaInfo.size(); i++) {
                ItemBeanInfo itembean = new ItemBeanInfo();
                itembean.setXmmc(szfaInfo.get(i).getGCXM());
                itembean.setGcxmid(szfaInfo.get(i).getGCXMID());
                itembean.setJldw(szfaInfo.get(i).getDW());
                itembean.setJsgs(szfaInfo.get(i).getJSGS());
                gcxmResult.add(itembean);
            }
            gcxmadapter = new ReapirRelyAdapter(this, gcxmResult);
            addDdiseaseListView.setAdapter(gcxmadapter);
        }
        ArrayList<String> listImgUrl = new ArrayList<>();
        if (picInfo != null && picInfo.size() > 0) {
            for (int k = 0; k < picInfo.size(); k++) {
                TPListInfo tpinfo = picInfo.get(k);
                if (tpinfo != null) {
                    String imgUrl = tpinfo.getFILEPATH();
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
}
