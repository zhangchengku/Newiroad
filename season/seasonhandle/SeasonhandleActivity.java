package com.zggk.newiroad.season.seasonhandle;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.bumptech.glide.Glide;
import com.example.logic.ImgFileListActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.tooklkit.Tooklkit;
import com.zggk.newiroad.Bean.SUCCESBEAN;
import com.zggk.newiroad.Bean.Seasonhandlebean;

import com.zggk.newiroad.Bean.Seasonhandlejson;
import com.zggk.newiroad.Bean.waiHandleItembean;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.R;
import com.zggk.newiroad.disease.MinePopupWindow;
import com.zggk.newiroad.handle.LoadDataDialog;
import com.zggk.newiroad.handle.inwaihandle.ShowImgActivity;
import com.zggk.newiroad.kdxf.DictationResult;
import com.zggk.newiroad.mvp.MVPBaseActivity;
import com.zggk.newiroad.utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SeasonhandleActivity extends MVPBaseActivity<SeasonhandleContract.View, SeasonhandlePresenter> implements SeasonhandleContract.View {
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
    @Bind(R.id.sgsl_et)
    EditText sgslEt;
    @Bind(R.id.sgsl_dw)
    TextView sgslDw;
    @Bind(R.id.sgsl)
    LinearLayout sgsl;
    @Bind(R.id.czwt_et2)
    EditText czwtEt2;
    @Bind(R.id.czwt_bu)
    TextView czwtBu;
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
    @Bind(R.id.plys)
    TextView plys;
    @Bind(R.id.shang)
    LinearLayout shang;
    @Bind(R.id.activity_new_disease_zhe_zhao_layout)
    View activityNewDiseaseZheZhaoLayout;
    @Bind(R.id.disease_new_parent_layout)
    RelativeLayout diseaseNewParentLayout;
    private int directionSelInt = 0;// 1上行-右 2下行-左 3双向-全幅
    private String dictationResultStr;
    private static String APPID = "5bf211f5";
    private List<String> xf = new ArrayList<>();
    private List<String> zong = new ArrayList<>();
    private boolean shigongqianyesbig = false;
    private boolean shigongzhongyesbig = false;
    private boolean shigonghoubig = false;
    private int type;
    private MinePopupWindow minePopupWindow;
    private  ArrayList<String> sGQTP = new ArrayList<>();
    private  ArrayList<String> sGZTP = new ArrayList<>();
    private  ArrayList<String> sGHTP = new ArrayList<>();
    private String SGQTP = "";
    private String SGZTP = "";
    private String SGHTP = "";
    private int childViewPosition;
    private String cameraPath;
    private Gson gson = new Gson();
    private LoadDataDialog loadDataDialog;
    private String SGQ,SGZ,SGH;
    public void setChildViewPosition(int childViewPosition) {
        this.childViewPosition = childViewPosition;
    }
    public void setCameraPath(String cameraPath) {
        this.cameraPath = cameraPath;
    }
    private final int CHOOSE_PICTURE_CODE = 1;
    private final int CAMERA_CODE = 2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.act_season_handle);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        ButterKnife.bind(this);
        title.setText("养护维修");
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
    private int isSaveOk(){
        int result=5;
        if(Utils.isNull(SGQTP)){
            result=1;
        }
        if(Utils.isNull(SGHTP)){
            result=2;
        }
        if(Utils.isNull(SGZTP)){
            result=3;
        }
        return result;
    }
    private void initsener() {
        shang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkAvailable(SeasonhandleActivity.this)) {
                    int result = isSaveOk();
                    if (result == 1) {
                        MyApplication.app.customToast("请上传施工前图片");
                    } else if (result == 2) {
                        MyApplication.app.customToast("请上传施工后图片");
                    } else if (result == 3) {
                        MyApplication.app.customToast("请上传施工中图片");
                    }else {
                        shang.setClickable(false);
                        showLoadingDialogMethod("上传中...");
                        update();
                    }
                }
            }
        });
        czwtBu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getkdxf(czwtEt2);
            }
        });
        shigongqian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shigongqianyesbig == false) {
                    type = 1;
                    if (minePopupWindow == null) {
                        minePopupWindow = new MinePopupWindow(SeasonhandleActivity.this, itemOnClick);
                    }
                    minePopupWindow.showAtLocation(activityDiseaseNewScrollview, Gravity.BOTTOM, 0, 0);
                } else {
                    sGQTP.clear();
                    sGQTP.add(SGQTP);
                    sGQTP.add(SGQTP);
                    Intent intent = new Intent(SeasonhandleActivity.this, ShowImgActivity.class);
                    intent.putExtra("img2", sGQTP);
                    intent.putExtra("position", 0);
                    startActivity(intent);
                }
            }
        });
        shigongzhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shigongzhongyesbig == false) {
                    type = 3;
                    if (minePopupWindow == null) {
                        minePopupWindow = new MinePopupWindow(SeasonhandleActivity.this, itemOnClick);
                    }
                    minePopupWindow.showAtLocation(activityDiseaseNewScrollview, Gravity.BOTTOM, 0, 0);
                } else {
                    sGZTP.clear();
                    sGZTP.add(SGZTP);
                    Intent intent = new Intent(SeasonhandleActivity.this, ShowImgActivity.class);
                    intent.putExtra("img2", sGZTP);
                    intent.putExtra("position", 0);
                    startActivity(intent);
                }
            }
        });
        shigonghou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shigonghoubig ==false){
                    type = 2;
                    if (minePopupWindow == null) {
                        minePopupWindow = new MinePopupWindow(SeasonhandleActivity.this, itemOnClick);
                    }
                    minePopupWindow.showAtLocation(activityDiseaseNewScrollview, Gravity.BOTTOM, 0, 0);
                }else {
                    sGHTP.clear();
                    sGHTP.add(SGHTP);
                    Intent intent = new Intent(SeasonhandleActivity.this, ShowImgActivity.class);
                    intent.putExtra("img2", sGHTP);
                    intent.putExtra("position", 0);
                    startActivity(intent);
                }
            }
        });
        shigongqiande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(SeasonhandleActivity.this)
                        .asBitmap()
                        .apply(MyApplication.app.options)
                        .load(R.drawable.morentu)
                        .into(shigongqian);
                shigongqiande.setVisibility(View.GONE);
                shigongqianyesbig =false;
                SGQTP = "";
            }
        });
        shigonghoude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(SeasonhandleActivity.this)
                        .asBitmap()
                        .apply(MyApplication.app.options)
                        .load(R.drawable.morentu)
                        .into(shigonghou);
                shigonghoude.setVisibility(View.GONE);
                shigonghoubig =false;
                SGHTP = "";
            }
        });
        shigongzhongde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(SeasonhandleActivity.this)
                        .asBitmap()
                        .apply(MyApplication.app.options)
                        .load(R.drawable.morentu)
                        .into(shigongzhong);
                shigongzhongde.setVisibility(View.GONE);
                shigongzhongyesbig =false;
                SGZTP = "";
            }
        });
    }

    private void update() {
        Seasonhandlejson addtaskjson =       new Seasonhandlejson();
        addtaskjson.setGUID_OBJ(getIntent().getStringExtra("ID"));
        addtaskjson.setSGSM(replaceNull(czwtEt2.getText().toString()));
        ArrayList<Seasonhandlejson.PICBean> listPic = new ArrayList<>();
        if (!Utils.isNull(SGQTP)) {
            Seasonhandlejson.PICBean picInfo = new Seasonhandlejson.PICBean();
            String nameStr = SGQTP.substring(SGQTP.lastIndexOf("/") + 1, SGQTP.length());
            String typeStr = SGQTP.substring(SGQTP.lastIndexOf(".") + 1, SGQTP.length());
            picInfo.setPicFileName(nameStr);
            picInfo.setPicFileType(typeStr);
            String strBlob = Utils.bmpToBase64String(SGQTP);
            Log.i("图片提交的二进制流", "=====" + strBlob);
            picInfo.setPicDataBlob(strBlob);
            picInfo.setWJLX("0");
            listPic.add(picInfo);
        }
        if (!Utils.isNull(SGZTP)) {
            Seasonhandlejson.PICBean picInfo = new Seasonhandlejson.PICBean();
            String nameStr = SGZTP.substring(SGZTP.lastIndexOf("/") + 1, SGZTP.length());
            String typeStr = SGZTP.substring(SGZTP.lastIndexOf(".") + 1, SGZTP.length());
            picInfo.setPicFileName(nameStr);
            picInfo.setPicFileType(typeStr);
            String strBlob = Utils.bmpToBase64String(SGZTP);
            Log.i("图片提交的二进制流", "=====" + strBlob);
            picInfo.setPicDataBlob(strBlob);
            picInfo.setWJLX("1");
            listPic.add(picInfo);
        }
        if (!Utils.isNull(SGHTP)) {
            Seasonhandlejson.PICBean picInfo = new Seasonhandlejson.PICBean();
            String nameStr = SGHTP.substring(SGHTP.lastIndexOf("/") + 1, SGHTP.length());
            String typeStr = SGHTP.substring(SGHTP.lastIndexOf(".") + 1, SGHTP.length());
            picInfo.setPicFileName(nameStr);
            picInfo.setPicFileType(typeStr);
            String strBlob = Utils.bmpToBase64String(SGHTP);
            Log.i("图片提交的二进制流", "=====" + strBlob);
            picInfo.setPicDataBlob(strBlob);
            picInfo.setWJLX("2");
            listPic.add(picInfo);
        }
        addtaskjson.setPIC(listPic);
        addtaskjson.setSGLXDW(replaceNull(sgslDw.getText().toString()));
        addtaskjson.setSGLXSL(replaceNull(sgslEt.getText().toString()));
        addtaskjson.setSTATE("2");
        String tijiaodates = gson.toJson(addtaskjson);
        mPresenter.addTask(tijiaodates);
    }

    private void initdate() {
        mPresenter.getdate(getIntent().getStringExtra("ID"));
    }

    @Override
    public void getData(Seasonhandlebean videoVos2) {
if (videoVos2.getJJXYH().size()>0){
    if (videoVos2.getJJXYH().get(0).getSGLXSL()!=null){
        sgslEt.setText(videoVos2.getJJXYH().get(0).getSGLXSL());
    }
    if (videoVos2.getJJXYH().get(0).getSGSM()!=null){
        czwtEt2.setText(videoVos2.getJJXYH().get(0).getSGSM());
    }

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
    sgslDw.setText(replaceNull(videoVos2.getJJXYH().get(0).getSGLXDW()));
    if (loadDataDialog != null && loadDataDialog.isShowing()) {
        loadDataDialog.cancel();
    }

    if (videoVos2.getPIC().size()>0&&videoVos2.getPIC()!=null){
        if (videoVos2.getPIC().size()==1){
            SGQ=videoVos2.getPIC().get(0).getFILEPATH();
        }else if (videoVos2.getPIC().size()==2){
            SGZ=videoVos2.getPIC().get(1).getFILEPATH();
        }else {
            SGH=videoVos2.getPIC().get(2).getFILEPATH();
        }
        if (SGQ != null) {
            Glide.with(SeasonhandleActivity.this)
                    .asBitmap()
                    .apply(MyApplication.app.options)
                    .load(SGQ)
                    .into(shigongqian);
        }
        if (SGZ != null) {
            Glide.with(SeasonhandleActivity.this)
                    .asBitmap()
                    .apply(MyApplication.app.options)
                    .load(SGZ)
                    .into(shigongzhong);
        }
        if (SGH != null) {
            Glide.with(SeasonhandleActivity.this)
                    .asBitmap()
                    .apply(MyApplication.app.options)
                    .load(SGH)
                    .into(shigonghou);
        }
    }


}




    }

    @Override
    public void getData2(SUCCESBEAN videoVos2) {
        if (loadDataDialog != null && loadDataDialog.isShowing()) {
            loadDataDialog.cancel();
        }
        if (videoVos2.getSTATE().equals("1")){
            MyApplication.app.customToast("上传成功");
            Intent intent = new Intent();
            setResult(2, intent);
            finish();
        }
        shang.setClickable(true);
    }

    @Override
    public void onRequestError(String msg) {

    }

    @Override
    public void onRequestEnd() {

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
    private void getkdxf(final EditText kdaxfdate) {
        dictationResultStr = "[";
        // 语音配置对象初始化
        SpeechUtility.createUtility(SeasonhandleActivity.this, SpeechConstant.APPID + "=" + APPID);
        // 1.创建SpeechRecognizer对象，第2个参数：本地听写时传InitListener
        SpeechRecognizer mIat = SpeechRecognizer.createRecognizer(SeasonhandleActivity.this, null);
        // 交互动画
        final RecognizerDialog iatDialog = new RecognizerDialog(SeasonhandleActivity.this, null);
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
    private View.OnClickListener itemOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.mine_camera_btn:
                    if (minePopupWindow.isShowing()) {
                        minePopupWindow.dismiss();
                    }
                    getPicFromCamera();
                    break;
                case R.id.mine_photo_btn:
                    if (minePopupWindow.isShowing()) {
                        minePopupWindow.dismiss();
                    }
                    getPicFromPhoto();
                    break;
                case R.id.mine_cancel_btn:
                    if (minePopupWindow.isShowing()) {
                        minePopupWindow.dismiss();
                    }
                    break;
                default:
                    break;
            }
        }
    };
    public void getPicFromPhoto() {
        Intent intent = new Intent(this, ImgFileListActivity.class);
        intent.putExtra("position", childViewPosition);
        intent.putExtra("isLimitedNumber",true);
        intent.putExtra("maxsize",1);
        startActivityForResult(intent, CHOOSE_PICTURE_CODE);
    }

    /**
     * 照相获取
     */
    public void getPicFromCamera() {
        String path = MyApplication.app.APP_FILE_SAVE_PATH
                + System.currentTimeMillis() + ".jpg";
        File file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//设置Action为拍照
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);//添加这一句表示对目标应用临时授权该Uri所代表的文件
            // 下面这句指定调用相机拍照后的照片存储的路径
            Uri imageUri = FileProvider.getUriForFile(this, MyApplication.TPDZ, file);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//将拍取的照片保存到指定URI
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        }
        setCameraPath(path);
        setChildViewPosition(childViewPosition);
        startActivityForResult(intent, CAMERA_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == CHOOSE_PICTURE_CODE) {//从相册获取照片
            if (intent != null) {
                ArrayList<String> listSelectPic = intent.getStringArrayListExtra("filelist");
                int position = intent.getIntExtra("position", -1);
                if (position != -1 && listSelectPic != null && listSelectPic.size() > 0) {
                    String pathStr = listSelectPic.get(0);
                    final String savePathStr = pathStr.substring(0, pathStr.lastIndexOf("/"));
                    Luban.with(this)
                            .load(listSelectPic)                                  // 传人要压缩的图片列表
                            .ignoreBy(100)                                  // 忽略不压缩图片的大小
                            .setTargetDir(savePathStr)                     // 设置压缩后文件存储位置
                            .setCompressListener(new OnCompressListener() { //设置回调
                                @Override
                                public void onStart() {
                                    // TODO 压缩开始前调用，可以在方法内启动 loading UI
                                }

                                @Override
                                public void onSuccess(File file) {
                                    // TODO 压缩成功后调用，返回压缩后的图片文件
                                    String imgPath = file.getPath();
                                    Bitmap bitmap = Tooklkit.getImageThumbnail(imgPath, Tooklkit.dip2px(SeasonhandleActivity.this, 480),
                                            Tooklkit.dip2px(SeasonhandleActivity.this, 480));
                                    if (type == 1) {
                                        shigongqianyesbig = true;
                                        SGQTP = imgPath;
                                        shigongqian.setImageBitmap(bitmap);
                                        shigongqiande.setVisibility(View.VISIBLE);
                                    } else if (type == 2) {
                                        shigonghoubig = true;
                                        SGHTP = imgPath;
                                        shigonghou.setImageBitmap(bitmap);
                                        shigonghoude.setVisibility(View.VISIBLE);
                                    }else if (type == 3) {
                                        shigongzhongyesbig = true;
                                        SGZTP = imgPath;
                                        shigongzhong.setImageBitmap(bitmap);
                                        shigongzhongde.setVisibility(View.VISIBLE);
                                    }

                                }

                                @Override
                                public void onError(Throwable e) {
                                    // TODO 当压缩过程出现问题时调用
                                    Log.i("图片压缩失败", "");
                                }
                            }).launch();    //启动压缩
                }
            }
        } else if (requestCode == CAMERA_CODE) {//拍照获取照片
            if (resultCode == -1) {
                if (!Utils.isNull(cameraPath) && childViewPosition != -1) {
                    Utils.dealBitmapRotate(cameraPath);
                    Luban.with(this)
                            .load(cameraPath)                                  // 传人要压缩的图片列表
                            .ignoreBy(100)                                  // 忽略不压缩图片的大小
                            .setTargetDir(cameraPath.substring(0, cameraPath.lastIndexOf("/")))                        // 设置压缩后文件存储位置
                            .setCompressListener(new OnCompressListener() { //设置回调
                                @Override
                                public void onStart() {
                                    // TODO 压缩开始前调用，可以在方法内启动 loading UI
                                }

                                @Override
                                public void onSuccess(File file) {
                                    // TODO 压缩成功后调用，返回压缩后的图片文件
                                    String imgPath = file.getPath();
                                    Bitmap bitmap = Tooklkit.getImageThumbnail(imgPath, Tooklkit.dip2px(SeasonhandleActivity.this, 480),
                                            Tooklkit.dip2px(SeasonhandleActivity.this, 480));
                                    if (type == 1) {
                                        SGQTP = imgPath;
                                        shigongqian.setImageBitmap(bitmap);
                                    } else if (type == 2) {
                                        SGHTP = imgPath;
                                        shigonghou.setImageBitmap(bitmap);
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    // TODO 当压缩过程出现问题时调用
                                    Log.i("图片压缩失败", "");
                                }
                            }).launch();    //启动压缩
                }
            }
        }
    }


}
