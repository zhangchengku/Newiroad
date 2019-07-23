package com.zggk.newiroad.season.addtask;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.zggk.newiroad.Bean.SUCCESBEAN;
import com.zggk.newiroad.Bean.addtaskjson;
import com.zggk.newiroad.Bean.seasonbean;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.R;
import com.zggk.newiroad.handle.LoadDataDialog;
import com.zggk.newiroad.kdxf.DictationResult;
import com.zggk.newiroad.log.add.AddActivity;
import com.zggk.newiroad.mvp.MVPBaseActivity;
import com.zggk.newiroad.utils.DiseaseNewSelectObjectListener;
import com.zggk.newiroad.utils.Utils;
import com.zggk.newiroad.utils.season.seasonLXpopu;

import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class AddTaskActivity extends MVPBaseActivity<AddTaskContract.View, AddTaskPresenter> implements AddTaskContract.View {
    @Bind(R.id.go_back)
    LinearLayout goBack;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.right)
    TextView right;
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
    @Bind(R.id.qdzh_x)
    TextView qdzhX;
    @Bind(R.id.qdzh_te)
    TextView qdzhTe;
    @Bind(R.id.qdzh_dw)
    TextView qdzhDw;
    @Bind(R.id.qdzh_text1)
    EditText qdzhText1;
    @Bind(R.id.qdzh_lj)
    TextView qdzhLj;
    @Bind(R.id.qdzh_text2)
    EditText qdzhText2;
    @Bind(R.id.zdzh_x)
    TextView zdzhX;
    @Bind(R.id.zdzh_te)
    TextView zdzhTe;
    @Bind(R.id.zdzh_dw)
    TextView zdzhDw;
    @Bind(R.id.zdzh_text1)
    EditText zdzhText1;
    @Bind(R.id.zdzh_lj)
    TextView zdzhLj;
    @Bind(R.id.zdzh_text2)
    EditText zdzhText2;
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
    EditText czwtEt;
    @Bind(R.id.czwt_bu)
    TextView czwtBu;
    @Bind(R.id.activity_disease_new_scrollview)
    ScrollView activityDiseaseNewScrollview;
    @Bind(R.id.plys)
    TextView plys;
    @Bind(R.id.shang)
    LinearLayout shang;
    @Bind(R.id.activity_new_disease_zhe_zhao_layout)
    View activityNewDiseaseZheZhaoLayout;
    @Bind(R.id.disease_new_parent_layout)
    RelativeLayout diseaseNewParentLayout;
    private LoadDataDialog loadDataDialog;
    private ArrayList<String>  LxResult = new ArrayList<>();
    private ArrayList<String>  DwResult = new ArrayList<>();
    private ArrayList<String>  SglxResult = new ArrayList<>();
    private List<seasonbean.YYLXDATABean> LxResultint;
    private List<seasonbean.GYDWBean> DwResultint;
    private List<seasonbean.YYSGLXBean> SglxResultint;
    private seasonbean.YYLXDATABean Lxbe;
    private seasonbean.GYDWBean Dwbe;
    private seasonbean.YYSGLXBean Sglxbe;
    private seasonLXpopu LxPop;
    private seasonLXpopu DwPop;
    private seasonLXpopu SglxPop;
    private String dictationResultStr;
    private static String APPID = "5bf211f5";
    private List<String> xf = new ArrayList<>();
    private List<String> zong = new ArrayList<>();
    private int directionSelInt = 1;// 1上行-右 2下行-左 3双向-全幅
    private Gson gson = new Gson();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.act_add_task);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        ButterKnife.bind(this);
        title.setText("新任务");
        String str = "正在加载...";
        showLoadingDialogMethod(str);
        initpopw();
        initdate();
        initsener();
    }

    private void initsener() {
        plys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plys.setClickable(false);
                showLoadingDialogMethod("上传中...");
                update();
            }
        });
        diseaseNewRoadLineLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LxPop.show(diseaseNewParentLayout);
            }
        });
        bhlx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DwPop.show(diseaseNewParentLayout);
            }
        });
        bhmc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SglxPop.show(diseaseNewParentLayout);
            }
        });
        diseaseNewDriverDirectionShangTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setShangXingSelect();
            }
        });
        diseaseNewDriverDirectionXiaTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setXiaXingSelect();
            }
        });
        czwtBu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getkdxf(czwtEt);
            }
        });
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void update() {
        addtaskjson addtaskjson =       new addtaskjson();
        addtaskjson.setCREATOR(MyApplication.spUtils.getString("dlr"));
        addtaskjson.setGYDW(MyApplication.spUtils.getString("dqgydwid"));
        addtaskjson.setGYDWMC(MyApplication.spUtils.getString("dqgydwmc"));
        addtaskjson.setLXCODE(Lxbe.getLXID());
        String xsfxStr = "";
        if (directionSelInt == 1) {
            xsfxStr = diseaseNewDriverDirectionShangTxt.getText().toString();
        } else if (directionSelInt == 2) {
            xsfxStr = diseaseNewDriverDirectionXiaTxt.getText().toString();
        }
        addtaskjson.setWZFX(xsfxStr);
        addtaskjson.setLXMC(Lxbe.getLXMC());
        String QzhStr="0.0";
        String zhOneStr=qdzhText1.getText().toString();
        String zhTwoStr=qdzhText2.getText().toString();
        if(Utils.isNull(zhTwoStr)){
            QzhStr = zhOneStr;
        }else{
            QzhStr = zhOneStr + "." + zhTwoStr;
        }
        addtaskjson.setQDZH(QzhStr);
        String ZzhStr="0.0";
        String ZzhOneStr=zdzhText1.getText().toString();
        String ZzhTwoStr=zdzhText2.getText().toString();
        if(Utils.isNull(zhTwoStr)){
            ZzhStr = ZzhOneStr;
        }else{
            ZzhStr = ZzhOneStr + "." + ZzhTwoStr;
        }
        addtaskjson.setZDZH(ZzhStr);
        addtaskjson.setSGLX(Sglxbe.getSGLX());
        addtaskjson.setSGLXMC(Sglxbe.getSGLXMC());
        addtaskjson.setSGLXDW(Sglxbe.getJLDW());
        addtaskjson.setSGDW(Dwbe.getGYDWID());
        addtaskjson.setSGDWMC(Dwbe.getGYDWMC());
        addtaskjson.setRWYQ(czwtEt.getText().toString());
        String tijiaodates = gson.toJson(addtaskjson);
        mPresenter.addTask(tijiaodates);
    }

    private void initpopw() {
        LxPop = new seasonLXpopu(this, "请选择路线", LxResult, new DiseaseNewSelectObjectListener() {
            @Override
            public void selectPosition(int position) {
                diseaseNewRoadLineTxt.setText(LxResult.get(position));
                Lxbe = LxResultint.get(position);
            }
        });
        DwPop = new seasonLXpopu(this, "请选择施工单位", DwResult, new DiseaseNewSelectObjectListener() {
            @Override
            public void selectPosition(int position) {
                diseaseCustomEditItemBhlxTxtBtn.setText(DwResult.get(position));
                Dwbe = DwResultint.get(position);
            }
        });
        SglxPop = new seasonLXpopu(this, "请选择施工类型", SglxResult, new DiseaseNewSelectObjectListener() {
            @Override
            public void selectPosition(int position) {
                diseaseCustomEditItemBhmcTxtBtn.setText(SglxResult.get(position));
                Sglxbe = SglxResultint.get(position);
            }
        });
    }

    private void initdate() {
        String DATE=getIntent().getStringExtra("DATE");
        seasonbean seasonbean=new Gson().fromJson(DATE,seasonbean.class);
        getlxdate(seasonbean.getYYLXDATA());
        getdwdate(seasonbean.getGYDW());
        getsglxdate(seasonbean.getYYSGLX());
        if (loadDataDialog != null && loadDataDialog.isShowing()) {
            loadDataDialog.cancel();
        }
    }

    private void getsglxdate(List<seasonbean.YYSGLXBean> sglx) {
        SglxResultint = sglx;
        if (SglxResultint != null&&SglxResultint.size()>0) {
            SglxResult.clear();
            for (int i = 0; i < SglxResultint.size(); i++) {
                SglxResult.add(SglxResultint.get(i).getSGLXMC());
            }
            SglxPop.notifityData();
        }
    }
    private void getdwdate(List<seasonbean.GYDWBean> gydw) {
        DwResultint = gydw;
        if (DwResultint != null&&DwResultint.size()>0) {
            DwResult.clear();
            for (int i = 0; i < DwResultint.size(); i++) {
                DwResult.add(DwResultint.get(i).getGYDWMC());
            }
            DwPop.notifityData();
        }
    }

    private void getlxdate(List<seasonbean.YYLXDATABean> lx) {
        LxResultint = lx;
        if (LxResultint != null&&LxResultint.size()>0) {
            LxResult.clear();
            for (int i = 0; i < LxResultint.size(); i++) {
                LxResult.add(LxResultint.get(i).getLXMC()+LxResultint.get(i).getQDZH() + "-" +LxResultint.get(i).getZDZH());
                if (i == 0) {
                    Lxbe = LxResultint.get(i);
                    diseaseNewRoadLineTxt.setText(LxResultint.get(i).getLXMC()+LxResultint.get(i).getQDZH() + "-" +LxResultint.get(i).getZDZH());
                }
            }
            LxPop.notifityData();
        }
    }










    private void showLoadingDialogMethod(String str) {
        if (loadDataDialog == null) {
            loadDataDialog = new LoadDataDialog(this);
        }
        loadDataDialog.setTitleStr(str);
        loadDataDialog.show();
    }
    public void showZheZhaoView() {
        if (!activityNewDiseaseZheZhaoLayout.isShown()) {
            activityNewDiseaseZheZhaoLayout.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 隐藏遮罩view
     */
    public void hideZheZhaoView() {
        if (activityNewDiseaseZheZhaoLayout.isShown()) {
            activityNewDiseaseZheZhaoLayout.setVisibility(View.GONE);
        }
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
    private void getkdxf(final EditText kdaxfdate) {
        dictationResultStr = "[";
        // 语音配置对象初始化
        SpeechUtility.createUtility(AddTaskActivity.this, SpeechConstant.APPID + "=" + APPID);
        // 1.创建SpeechRecognizer对象，第2个参数：本地听写时传InitListener
        SpeechRecognizer mIat = SpeechRecognizer.createRecognizer(AddTaskActivity.this, null);
        // 交互动画
        final RecognizerDialog iatDialog = new RecognizerDialog(AddTaskActivity.this, null);
        // 2.设置听写参数，详见《科大讯飞MSC API手册(Android)》SpeechConstant类
        mIat.setParameter(SpeechConstant.DOMAIN, "iat");
        mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mIat.setParameter(SpeechConstant.ACCENT, "mandarin");
        // 3.开始听写
        iatDialog.setListener(new RecognizerDialogListener() {
            @Override
            public void onResult(RecognizerResult results, boolean isLast) {

                if (!isLast) {
                    dictationResultStr += results.getResultString() + ",";
                } else {
                    dictationResultStr += results.getResultString() + "]";
                }
                if (isLast) {
                    Gson gson = new Gson();
                    List<DictationResult> dictationResultList = gson
                            .fromJson(dictationResultStr,
                                    new TypeToken<List<DictationResult>>() {
                                    }.getType());
                    String finalResult = "";
                    for (int i = 0; i < dictationResultList.size() - 1; i++) {
                        finalResult += dictationResultList.get(i)
                                .toString();
                    }
                    xf.clear();
                    zong.clear();
                    zong.add(kdaxfdate.getText().toString());
                    zong.add(finalResult);
                    kdaxfdate.setText(addet(zong));
                    kdaxfdate.requestFocus();
                    kdaxfdate.setSelection(addet(zong).length());
                }
            }

            @Override
            public void onError(SpeechError error) {
                error.getPlainDescription(true);
            }

        });
        iatDialog.show();

    }
    public static String addet(List<String> list) {
        StringBuilder sb = new StringBuilder();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (i < list.size() - 1) {
                    sb.append(list.get(i));
                } else {
                    sb.append(list.get(i));
                }
            }
        }
        return sb.toString();
    }

    @Override
    public void onRequestError(String msg) {

    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void getData(SUCCESBEAN videoVos2) {
        if (loadDataDialog != null && loadDataDialog.isShowing()) {
            loadDataDialog.cancel();
        }
        if (videoVos2.getSTATE().equals("1")){
            MyApplication.app.customToast("上传成功");
            Intent intent = new Intent();
            setResult(2, intent);
            finish();
        }
        plys.setClickable(true);
    }
}
