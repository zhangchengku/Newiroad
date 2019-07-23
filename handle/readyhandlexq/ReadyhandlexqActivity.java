package com.zggk.newiroad.handle.readyhandlexq;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zggk.newiroad.Bean.ItemBeanInfo;
import com.zggk.newiroad.Bean.ItemSgjdInfo;
import com.zggk.newiroad.Bean.ReadyhandxqleInfo;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.R;
import com.zggk.newiroad.disease.ListAdapter;
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

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ReadyhandlexqActivity extends MVPBaseActivity<ReadyhandlexqContract.View, ReadyhandlexqPresenter> implements ReadyhandlexqContract.View {


    @Bind(R.id.go_back)
    LinearLayout goBack;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.right)
    TextView right;
    @Bind(R.id.disease_detail_unit_name_txt)
    TextView diseaseDetailUnitNameTxt;
    @Bind(R.id.disease_detail_road_line_txt)
    TextView diseaseDetailRoadLineTxt;
    @Bind(R.id.disease_detail_time_txt)
    TextView diseaseDetailTimeTxt;
    @Bind(R.id.disease_detail_to_examine_type_txt)
    TextView diseaseDetailToExamineTypeTxt;
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
    @Bind(R.id.disease_detail_driver_direction_txt)
    TextView diseaseDetailDriverDirectionTxt;
    @Bind(R.id.disease_custom_item_bhlx_txt)
    TextView diseaseCustomItemBhlxTxt;
    @Bind(R.id.disease_custom_item_bhmc_txt)
    TextView diseaseCustomItemBhmcTxt;
    @Bind(R.id.disease_custom_item_bh_txt)
    TextView diseaseCustomItemBhTxt;
    @Bind(R.id.add_ddisease_list_view)
    ListViewForScrollView addDdiseaseListView;
    @Bind(R.id.cai_ji_picture_add_grid)
    NoScroolGridView caiJiPictureAddGrid;
    @Bind(R.id.czwt_et)
    TextView czwtEt;
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
    @Bind(R.id.activity_disease_detail_scrollview)
    ScrollView activityDiseaseDetailScrollview;
    private String BHID;
    private ReadyhandxqleInfo.BHDATABean indate;
    private ListAdapter adapter;
    private LoadDataDialog loadDataDialog;
    private CommonAdapter<ItemSgjdInfo> recyadapter;
    private  ArrayList<String> sGQTP = new ArrayList<>();
    private  ArrayList<String> sGZTP = new ArrayList<>();
    private  ArrayList<String> sGHTP = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.act_readyhandle_xq);
        ButterKnife.bind(this);
        initdate();
        String str = "正在加载...";
        showLoadingDialogMethod(str);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initdate() {
        BHID = getIntent().getStringExtra("BHID");
        mPresenter.getxqdate(BHID);
    }

    @Override
    public void onRequestError(String msg) {

    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void getData( ReadyhandxqleInfo videoVos2) {

        title.setText("施工明细");
        if (videoVos2.getBHDATA() != null && videoVos2.getBHDATA().size() > 0) {
            indate = videoVos2.getBHDATA().get(0);
            czwtEt.setText(replaceNull(indate.getBZ()));
            diseaseDetailUnitNameTxt.setText(replaceNull(indate.getDCR()));
            diseaseDetailRoadLineTxt.setText(replaceNull(indate.getLXMC()));
            diseaseDetailTimeTxt.setText(replaceNull(indate.getDCSJ().replace("T"," ")));
            diseaseDetailToExamineTypeTxt.setText(replaceNull(indate.getDCLX()));
            if (indate.getQDZH() != null) {
                String zhfStr = indate.getQDZH().substring(0, indate.getQDZH().indexOf("."));
                String zhaStr = indate.getQDZH().substring(indate.getQDZH().indexOf(".") + 1, indate.getQDZH().length());
                diseaseNewPileNumberOneEdit.setText(zhfStr);
                diseaseNewPileNumberTwoEdit.setText(zhaStr);
            }
            diseaseDetailDriverDirectionTxt.setText(replaceNull(indate.getWZFX()));
            diseaseCustomItemBhlxTxt.setText(replaceNull(indate.getSHBW()));
            diseaseCustomItemBhmcTxt.setText(replaceNull(indate.getBHLX()));
            diseaseCustomItemBhTxt.setText(replaceNull(indate.getCZFAMC()));
        }
        ArrayList<String> listImgUrl = new ArrayList<>();
        if (videoVos2.getCJTP() != null && videoVos2.getCJTP().size() > 0) {
            for (int i = 0; i < videoVos2.getCJTP().size(); i++) {
                listImgUrl.add(videoVos2.getCJTP().get(i).getFILEPATH());
            }
            SendTopicPictureGridAdapter addPictureAdapter = new SendTopicPictureGridAdapter(this, listImgUrl);
            caiJiPictureAddGrid.setAdapter(addPictureAdapter);
        }
        ArrayList<ItemSgjdInfo> diseaselist = new ArrayList<>();
        if (videoVos2.getCZFADATA() != null && videoVos2.getCZFADATA().size() > 0) {
            for (int i = 0; i < videoVos2.getCZFADATA().size(); i++) {
                ItemSgjdInfo ItemBeanInfo = new ItemSgjdInfo();
                ItemBeanInfo.setGCXM(videoVos2.getCZFADATA().get(i).getGCXM());
                ItemBeanInfo.setJSGS(videoVos2.getCZFADATA().get(i).getJSGS());
                ItemBeanInfo.setDW(videoVos2.getCZFADATA().get(i).getDW());
                ItemBeanInfo.setSL(videoVos2.getCZFADATA().get(i).getSL());
                ItemBeanInfo.setHD(videoVos2.getCZFADATA().get(i).getHD());
                diseaselist.add(ItemBeanInfo);
            }
        }
        if (videoVos2.getSGQTP() != null) {
            Glide.with(ReadyhandlexqActivity.this)
                    .asBitmap()
                    .apply(MyApplication.app.options)
                    .load(videoVos2.getSGQTP())
                    .into(shigongqian);
            sGQTP.clear();
            sGQTP.add(videoVos2.getSGQTP());
            shigongqian.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ReadyhandlexqActivity.this, ShowImgActivity.class);
                    intent.putExtra("img2", sGQTP);
                    intent.putExtra("position", 0);
                    startActivity(intent);
                }
            });
        }
        if (videoVos2.getSGZTP() != null) {
            Glide.with(ReadyhandlexqActivity.this)
                    .asBitmap()
                    .apply(MyApplication.app.options)
                    .load(videoVos2.getSGZTP())
                    .into(shigongzhong);
            sGZTP.clear();
            sGZTP.add(videoVos2.getSGZTP());
            shigongzhong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ReadyhandlexqActivity.this, ShowImgActivity.class);
                    intent.putExtra("img2", sGZTP);
                    intent.putExtra("position", 0);
                    startActivity(intent);
                }
            });
        }
        if (videoVos2.getSGHTP() != null) {
            Glide.with(ReadyhandlexqActivity.this)
                    .asBitmap()
                    .apply(MyApplication.app.options)
                    .load(videoVos2.getSGHTP())
                    .into(shigonghou);
            sGHTP.clear();
            sGHTP.add(videoVos2.getSGHTP());
            shigonghou.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ReadyhandlexqActivity.this, ShowImgActivity.class);
                    intent.putExtra("img2", sGHTP);
                    intent.putExtra("position", 0);
                    startActivity(intent);
                }
            });
        }
        recyadapter = new CommonAdapter<ItemSgjdInfo>(this,
                R.layout.item_readyhandleitem, diseaselist) {
            @Override
            protected void convert(ViewHolder holder, ItemSgjdInfo tubiaoVo, int position) {
                holder.setText(R.id.nr1, tubiaoVo.getGCXM());
                holder.setText(R.id.ed_hd, tubiaoVo.getHD());
                holder.setText(R.id.ed, tubiaoVo.getJSGS());
                holder.setText(R.id.dw, "="+tubiaoVo.getSL()+"  "+tubiaoVo.getDW());
            }
        };
        addDdiseaseListView.setAdapter(recyadapter);
        if (loadDataDialog != null && loadDataDialog.isShowing()) {
            loadDataDialog.cancel();
        }
    }

    public static String replaceNull(String str) {
        if (str == null || str.equals("null")) {
            return "";
        }
        return str;
    }

    private void showLoadingDialogMethod(String str) {
        if (loadDataDialog == null) {
            loadDataDialog = new LoadDataDialog(this);
        }
        loadDataDialog.setTitleStr(str);
        loadDataDialog.show();
    }
}
