package com.zggk.newiroad.log.looklog;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
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
import com.tooklkit.Tooklkit;
import com.zggk.newiroad.Bean.AddLogbean;
import com.zggk.newiroad.Bean.AddLogjson;
import com.zggk.newiroad.Bean.Basebean;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.R;
import com.zggk.newiroad.disease.AddLogPictureGridAdapter;
import com.zggk.newiroad.disease.SendTopicPictureGridAdapter;
import com.zggk.newiroad.db.dbhelper.CuringDaoImpl;
import com.zggk.newiroad.db.dbhelper.dbInfo.LogRecordInfo;
import com.zggk.newiroad.db.dbhelper.dbInfo.RoadSectionInfo;
import com.zggk.newiroad.db.dbhelper.dbInfo.TypeOfInvestigation;
import com.zggk.newiroad.handle.LoadDataDialog;
import com.zggk.newiroad.kdxf.DictationResult;
import com.zggk.newiroad.log.add.AddActivity;
import com.zggk.newiroad.mvp.MVPBaseActivity;
import com.zggk.newiroad.popuwindo.popuwindosa;
import com.zggk.newiroad.popuwindo.popuwindosaa;
import com.zggk.newiroad.popuwindo.popuwindowsslistener;
import com.zggk.newiroad.utils.DiseaeNewSelectObjectPopupWindow;
import com.zggk.newiroad.utils.DiseaseNewSelectObjectListener;
import com.zggk.newiroad.utils.PopSelectListener;
import com.zggk.newiroad.utils.Utils;
import com.zggk.newiroad.utils.WheelDateAndTimeSelectPopupWindow;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class LooklogActivity extends MVPBaseActivity<LooklogContract.View, LooklogPresenter> implements LooklogContract.View {


    @Bind(R.id.go_back)
    LinearLayout goBack;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.right)
    TextView right;
    @Bind(R.id.xcdw)
    TextView xcdw;
    @Bind(R.id.lxre)
    RecyclerView lxre;
    @Bind(R.id.xclx_layout)
    LinearLayout xclxLayout;
    @Bind(R.id.xcrq)
    TextView xcrq;
    @Bind(R.id.xcrq_layout)
    LinearLayout xcrqLayout;

    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.dqtq)
    TextView dqtq;
    @Bind(R.id.dqtq_layout)
    LinearLayout dqtqLayout;
    @Bind(R.id.dclx)
    TextView dclx;
    @Bind(R.id.dclx_layout)
    LinearLayout dclxLayout;
    @Bind(R.id.disease_new_bh_content_layout)
    LinearLayout diseaseNewBhContentLayout;
    @Bind(R.id.layoutt)
    LinearLayout layoutt;
    @Bind(R.id.save)
    TextView save;
    @Bind(R.id.add_log)
    TextView addlog;
    private EditText edqclj, edqcss, edqczc, edwjj, edgj, edlmyh, edgfj, edysc;
    private CuringDaoImpl curingDao;
    private LogRecordInfo Lookdbbean;
    private List<RoadSectionInfo> LDlist;
    private String MRld, MRqd, MRzd;
    private List<String> LXlist = new ArrayList<>();
    private CommonAdapter<String> lxadapter;
    private ArrayList<String> listLdResult;
    private popuwindosa ldPop;
    private WheelDateAndTimeSelectPopupWindow popupWindow;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private ArrayList<String> tqlist = new ArrayList<>();
    private DiseaeNewSelectObjectPopupWindow tqPop;
    private List<TypeOfInvestigation> listDclxInfoResult;
    private ArrayList<String> listDclxResult = new ArrayList<>();
    private DiseaeNewSelectObjectPopupWindow dclxPop;
    private EditText czwtet;
    private TextView czwtbu;
    private static String APPID = "5bf211f5";
    private String dictationResultStr;
    private List<String> xf = new ArrayList<>();
    private List<String> zong = new ArrayList<>();
    private LinearLayout diseasenewbh;
    private GridView caiJiPictureAddGrid;
    private Map<Integer, LooklogGredAdapter> mapAdapter = new HashMap<>();
    private String cameraPath;//拍照获取图片的地址
    private int childViewPosition;//拍照获取图片的地址
    private String nrstring;
    private LooklogGredAdapter addPictureAdapter;
    private Gson gson = new Gson();
    private final int CHOOSE_PICTURE_CODE = 1;
    private final int CAMERA_CODE = 2;
    private LoadDataDialog loadDataDialog;

    public void setCameraPath(String cameraPath) {
        this.cameraPath = cameraPath;
    }

    private List<String> LXidlist = new ArrayList<>();

    public void setChildViewPosition(int childViewPosition) {
        this.childViewPosition = childViewPosition;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setStatusBarColor(this, R.color.theme_color);
        setContentView(R.layout.act_lookdb);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        ButterKnife.bind(this);
        initview();
        initdate();
        setdate();
        setadapter();
        initpopu();
        linstener();
    }

    private void initpopu() {
        popupWindow = new WheelDateAndTimeSelectPopupWindow(this, new PopSelectListener() {
            @Override
            public void selectResult(Object... result) {
                String selDataStr = result[0].toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
                try {
                    Date dateResult = sdf.parse(selDataStr);
                    selDataStr = simpleDateFormat.format(dateResult);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                xcrq.setText(selDataStr);
            }
        });
        tqlist.add("晴");
        tqlist.add("多云");
        tqlist.add("小雨");
        tqlist.add("中雨");
        tqPop = new DiseaeNewSelectObjectPopupWindow(LooklogActivity.this, "请选择当前天气", tqlist, new DiseaseNewSelectObjectListener() {
            @Override
            public void selectPosition(int position) {
                dqtq.setText(tqlist.get(position));
            }
        });
        queryDclxInfoMethod();
        dclxPop = new DiseaeNewSelectObjectPopupWindow(this, "请选择调查类型", listDclxResult, new DiseaseNewSelectObjectListener() {
            @Override
            public void selectPosition(int position) {
                dclx.setText(listDclxResult.get(position));
            }
        });
    }

    private void linstener() {
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        xcrqLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.show(layoutt);
            }
        });
        dqtqLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tqPop.show(layoutt);
            }
        });
        dclxLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dclxPop.show(layoutt);
            }
        });
        czwtbu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getkdxf(czwtet);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getnr();
                showLoadingDialogMethod("上传中");
                save.setClickable(false);
                LogRecordInfo logRecordInfo =           new  LogRecordInfo();
                logRecordInfo.setGYDWID(MyApplication.spUtils.getString("dqgydwid"));
                logRecordInfo.setGYDWMC(MyApplication.spUtils.getString("dqgydwmc"));
                if (LXlist.size() == 0) {
                    logRecordInfo.setXCLD(replaceNull(LDlist.get(0).getLDID()));//路段id
                } else {
                    logRecordInfo.setXCLD(listToString(LXidlist));//路段id
                }
                if (LXlist.size() == 0) {
                    logRecordInfo.setLDMC(replaceNull(MRld + getZHMCByZH(MRqd) + getZHMCByZH(MRzd)));//路段名称
                } else {
                    logRecordInfo.setLDMC(listToString(LXlist));//路段名称
                }
                logRecordInfo.setJLSJ(replaceNull(xcrq.getText().toString()));
                logRecordInfo.setTQ(replaceNull(dqtq.getText().toString()));
                logRecordInfo.setXCXZMC(replaceNull(replaceNull(dclx.getText().toString())));
                logRecordInfo.setCLJL(replaceNull(czwtet.getText().toString()));
                logRecordInfo.setQCLJ(replaceNull(edqclj.getText().toString()));
                logRecordInfo.setQCSS(replaceNull(edqcss.getText().toString()));
                logRecordInfo.setQCZC(replaceNull(edqczc.getText().toString()));
                logRecordInfo.setWJJ(replaceNull(edwjj.getText().toString()));
                logRecordInfo.setWG(replaceNull(edgj.getText().toString()));
                logRecordInfo.setLMZHYH(replaceNull(edlmyh.getText().toString()));
                logRecordInfo.setGFJ(replaceNull(edgfj.getText().toString()));
                logRecordInfo.setYSC(replaceNull(edysc.getText().toString()));
                String imgStr = "";
                ArrayList<String> listImgUrl = addPictureAdapter.getListImgUrl();
                if (listImgUrl != null && listImgUrl.size() > 0) {
                    for (int k = 0; k < listImgUrl.size(); k++) {
                        if (k == 0) {
                            imgStr += listImgUrl.get(k);
                        } else {
                            imgStr += "," + listImgUrl.get(k);
                        }
                    }
                }
                logRecordInfo.setTPDZ(imgStr);
                logRecordInfo.setRZID(Integer.valueOf(getIntent().getStringExtra("RZID")).intValue());
                int i = curingDao.updateRzjl(logRecordInfo);
                if (loadDataDialog != null && loadDataDialog.isShowing()) {
                    loadDataDialog.cancel();
                }
                if(i!=0){
                    MyApplication.app.customToast("保存本地成功");
                    Intent intent = new Intent();
                    setResult(2, intent);
                    finish();

                }
            }
        });
        addlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getnr();
                addlog.setClickable(false);
                if (Utils.isNetworkAvailable(LooklogActivity.this) == false) {//没网
                    MyApplication.app.customToast("您当前没有网络");
                } else {//有网
                    AddLogjson addLogjsons = new AddLogjson();
                    List<AddLogjson.XCRZBean> addLogjsonlist = new ArrayList<AddLogjson.XCRZBean>();
                    AddLogjson.XCRZBean addLogjson = new AddLogjson.XCRZBean();
                    addLogjson.setGYDW(MyApplication.spUtils.getString("dqgydwid"));
                    if (LXlist.size() == 0) {
                        addLogjson.setXCLD(replaceNull(LDlist.get(0).getLDID()));//路段id
                    } else {
                        addLogjson.setXCLD(listToString(LXidlist));//路段id
                    }
                    if (LXlist.size() == 0) {
                        addLogjson.setLDMC(replaceNull(MRld + getZHMCByZH(MRqd) + getZHMCByZH(MRzd)));//路段名称
                    } else {
                        addLogjson.setLDMC(listToString(LXlist));//路段名称
                    }
                    addLogjson.setXCSJ((xcrq.getText().toString()).replace("-", "/"));
                    addLogjson.setXCR(MyApplication.app.spUtils.getString("dlr"));
                    addLogjson.setCREATOR(MyApplication.app.spUtils.getString("dlr"));//空
                    if (curingDao.queryInvestigationByMc(dclx.getText().toString()).getDCID()!=null){
                        addLogjson.setXCXZ(curingDao.queryInvestigationByMc(dclx.getText().toString()).getDCID());
                    }
                    addLogjson.setCLJL(replaceNull(czwtet.getText().toString()));
                    addLogjson.setXCNR(nrstring);
                    addLogjson.setTQ(dqtq.getText().toString());
                    String imgStr = "";
                    ArrayList<String> listImgUrl = addPictureAdapter.getListImgUrl();
                    if (listImgUrl != null && listImgUrl.size() > 0) {
                        for (int k = 0; k < listImgUrl.size(); k++) {
                            if (k == 0) {
                                imgStr += listImgUrl.get(k);
                            } else {
                                imgStr += "," + listImgUrl.get(k);
                            }
                        }
                    }
                    if (!Utils.isNull(imgStr)) {
                        String[] strArr = imgStr.split(",");
                        if (strArr != null && strArr.length > 0) {
                            ArrayList<AddLogjson.PICBean> listPic = new ArrayList<>();
                            for (int i = 0; i < strArr.length; i++) {
                                AddLogjson.PICBean picInfo = new AddLogjson.PICBean();
                                String imgUrl = strArr[i];
                                String nameStr = imgUrl.substring(imgUrl.lastIndexOf("/") + 1, imgUrl.length());
                                String typeStr = imgUrl.substring(imgUrl.lastIndexOf(".") + 1, imgUrl.length());
                                picInfo.setPicFileName(nameStr);
                                picInfo.setPicFileType(typeStr);
                                String strBlob = Utils.bmpToBase64String(imgUrl);
                                Log.i("图片提交的二进制流", "=====" + strBlob);
                                picInfo.setPicDataBlob(strBlob);
                                listPic.add(picInfo);
                            }
                            addLogjson.setPICRZ(listPic);
                        }
                    }
                    addLogjsonlist.add(addLogjson);
                    addLogjsons.setXCRZ(addLogjsonlist);
                    String tijiaodates = gson.toJson(addLogjsons);
                    Log.i("图片", "点击了来个脑残");
                    mPresenter.add(tijiaodates);
                }
            }
        });
    }


    private void setadapter() {
        lxadapter = new CommonAdapter<String>(this,
                R.layout.item_lxlog, LXlist) {
            @Override
            protected void convert(ViewHolder holder, final String tubiaoVo, int position) {

                holder.setText(R.id.neirongte, tubiaoVo);

            }
        };
        lxre.setAdapter(lxadapter);
        lxadapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                ldPop.show(layoutt);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    private void setdate() {
        xcdw.setText(replaceNull(Lookdbbean.getGYDWMC()));
        xcrq.setText(replaceNull(Lookdbbean.getJLSJ()));
        dqtq.setText(replaceNull(Lookdbbean.getTQ()));
        dclx.setText(replaceNull(Lookdbbean.getXCXZMC()));
        czwtet.setText(replaceNull(Lookdbbean.getCLJL()));
        edqclj.setText(replaceNull(Lookdbbean.getQCLJ()));
        edqcss.setText(replaceNull(Lookdbbean.getQCSS()));
        edqczc.setText(replaceNull(Lookdbbean.getQCZC()));
        edwjj.setText(replaceNull(Lookdbbean.getWJJ()));
        edgj.setText(replaceNull(Lookdbbean.getWG()));
        edlmyh.setText(replaceNull(Lookdbbean.getLMZHYH()));
        edgfj.setText(replaceNull(Lookdbbean.getGFJ()));
        edysc.setText(replaceNull(Lookdbbean.getYSC()));

    }

    private void initdate() {
        LXlist.clear();
        curingDao = new CuringDaoImpl(this);
        Lookdbbean = curingDao.queryRzjlById(Integer.valueOf(getIntent().getStringExtra("RZID")).intValue());
        LDlist = curingDao.queryLdByGydwid(MyApplication.app.spUtils.getString("dqgydwid", ""));//路段列表集合
        if (Lookdbbean.getLDMC().indexOf(",") != -1) {
            String str = Lookdbbean.getLDMC().replace(" ", "");
            LXlist = Arrays.asList(str.split(","));//名称
        } else {
            LXlist.add(Lookdbbean.getLDMC());
        }
        MRld = LDlist.get(0).getLDMC();//默认的路段
        MRqd = LDlist.get(0).getQDZH();//默认的起点桩号
        MRzd = LDlist.get(0).getZDZH();//默认的终点桩号
        listLdResult = new ArrayList<String>();
        for (int i = 0; i < LDlist.size(); i++) {
            listLdResult.add(LDlist.get(i).getLDMC() );
        }
        ldPop = new popuwindosa(LooklogActivity.this, "请选择路线", listLdResult, new popuwindowsslistener() {//路线弹窗
            @Override
            public void selectPosition(List<String> lxlistdate, List<Integer> lxposition) {
                if (lxlistdate.size() == 0) {

                } else {
                    LXlist=null;
                    LXlist = new ArrayList<>();
                    LXlist.addAll(lxlistdate);
                    setadapter();
                    for (int i = 0; i < LXlist.size(); i++) {
                        LXidlist.add(LDlist.get(lxposition.get(i)).getLDID());
                    }
                }

            }
        });
        ArrayList<Drawable> listPicture = new ArrayList<>();
        ArrayList<String> listImgUrl = new ArrayList<>();
        if (Lookdbbean.getTPDZ() == null || Lookdbbean.getTPDZ().equals("")) {

        } else {
            listImgUrl.addAll(Arrays.asList(Lookdbbean.getTPDZ().split(",")));
            for (int i = 0; i < listImgUrl.size(); i++) {
                Bitmap bitmap = null;
                bitmap = Tooklkit.getImageThumbnail(listImgUrl.get(i), Tooklkit.dip2px(this, 480),
                        Tooklkit.dip2px(this, 480));
                if (bitmap != null) {
                    Drawable drawable = new BitmapDrawable(bitmap);
                    listPicture.add(drawable);
                }
            }
        }
        Drawable addPicture = getResources().getDrawable(R.drawable.sjq_add_tupian_sel);
        listPicture.add(addPicture);
        final int childPosition = diseasenewbh.getChildCount();
        addPictureAdapter = new LooklogGredAdapter(this, listPicture, listImgUrl, childPosition);
        caiJiPictureAddGrid.setAdapter(addPictureAdapter);
        mapAdapter.put(childPosition, addPictureAdapter);

    }

    private void initview() {
        title.setText("日志记录");
        lxre.setLayoutManager(new LinearLayoutManager(this));
        View diseaseView = LayoutInflater.from(LooklogActivity.this).inflate(R.layout.layout_looklog, null, false);
        diseaseNewBhContentLayout.addView(diseaseView);
        edqclj = (EditText) diseaseView.findViewById(R.id.ed_qclj);
        edqcss = (EditText) diseaseView.findViewById(R.id.ed_qcss);
        edqczc = (EditText) diseaseView.findViewById(R.id.ed_qczc);
        edwjj = (EditText) diseaseView.findViewById(R.id.ed_wjj);
        edgj = (EditText) diseaseView.findViewById(R.id.ed_gj);
        edlmyh = (EditText) diseaseView.findViewById(R.id.ed_lmyh);
        edgfj = (EditText) diseaseView.findViewById(R.id.ed_gfj);
        edysc = (EditText) diseaseView.findViewById(R.id.ed_ysc);
        czwtet = (EditText) diseaseView.findViewById(R.id.czwt_et);
        czwtbu = (TextView) diseaseView.findViewById(R.id.czwt_bu);
        caiJiPictureAddGrid = (GridView) diseaseView.findViewById(R.id.cai_ji_picture_add_grid);
        diseasenewbh = (LinearLayout) diseaseView.findViewById(R.id.disease_new_bh_content_layou);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void getData(Basebean videoVos2) {
        if (loadDataDialog != null && loadDataDialog.isShowing()) {
            loadDataDialog.cancel();
        }
        if (videoVos2.getSTATE().equals("1")) {
            curingDao.deleteRzjlById(Integer.valueOf(getIntent().getStringExtra("RZID")).intValue());
            MyApplication.app.customToast("上传成功");
            Intent intent = new Intent();
            setResult(2, intent);
            finish();
        } else {

        }
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

    public static String getZHMCByZH(String zhStr) {
        String result = "";
        if (zhStr != null) {
            if (zhStr.contains(".")) {
                String zhfStr = zhStr.substring(0, zhStr.indexOf("."));
                String zhaStr = zhStr.substring(zhStr.indexOf(".") + 1, zhStr.length());
                if (zhaStr.length() == 1) {
                    zhaStr += "00";
                } else if (zhaStr.length() == 2) {
                    zhaStr += "0";
                }
                result = " K" + zhfStr + "+" + zhaStr;
            } else {
                result = " K" + zhStr;
            }
        }
        return result;
    }

    private void queryDclxInfoMethod() {
        listDclxInfoResult = curingDao.queryAllInvestigation();
        if (listDclxInfoResult != null) {
            listDclxResult.clear();
            for (int i = 0; i < listDclxInfoResult.size(); i++) {
                TypeOfInvestigation typeInfo = listDclxInfoResult.get(i);
                listDclxResult.add(typeInfo.getDCMC());
            }
        }
    }

    private void getkdxf(final EditText kdaxfdate) {
        dictationResultStr = "[";
        // 语音配置对象初始化
        SpeechUtility.createUtility(LooklogActivity.this, SpeechConstant.APPID + "=" + APPID);
        // 1.创建SpeechRecognizer对象，第2个参数：本地听写时传InitListener
        SpeechRecognizer mIat = SpeechRecognizer.createRecognizer(LooklogActivity.this, null);
        // 交互动画
        final RecognizerDialog iatDialog = new RecognizerDialog(LooklogActivity.this, null);
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
                    kdaxfdate.setText(listToString2(zong));
                    kdaxfdate.requestFocus();
                    kdaxfdate.setSelection(listToString2(zong).length());
                }
            }

            @Override
            public void onError(SpeechError error) {
                error.getPlainDescription(true);
            }

        });
        iatDialog.show();

    }

    public static String listToString2(List<String> list) {
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

    public static String listToString(List<String> list) {
        StringBuilder sb = new StringBuilder();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (i < list.size() - 1) {
                    sb.append(list.get(i) + ",");
                } else {
                    sb.append(list.get(i));
                }
            }
        }
        return sb.toString();
    }

    private void getnr() {
        nrstring = replaceNull2(edqclj.getText().toString()) + "," +
                replaceNull2(edqcss.getText().toString()) + "," +
                replaceNull2(edqczc.getText().toString()) + "," +
                replaceNull2(edwjj.getText().toString()) + "," +
                replaceNull2(edgj.getText().toString()) + "," +
                replaceNull2(edlmyh.getText().toString()) + "," +
                replaceNull2(edgfj.getText().toString()) + "," +
                replaceNull2(edysc.getText().toString());
    }
    public static String replaceNull2(String str) {
        if (str.equals("")) {
            return "0";
        }
        return str;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == CHOOSE_PICTURE_CODE) {//从相册获取照片
            if (intent != null) {
                ArrayList<String> listSelectPic = intent.getStringArrayListExtra("filelist");
                int position = intent.getIntExtra("position", -1);
                if (position != -1 && listSelectPic != null && listSelectPic.size() > 0) {
                    final LooklogGredAdapter addPictureAdapter = mapAdapter.get(position);
                    final ArrayList<Drawable> listPicture = addPictureAdapter.getListPicture();
                    final ArrayList<String> listImgUrl = addPictureAdapter.getListImgUrl();

                    String pathStr = listSelectPic.get(0);
                    final String savePathStr = pathStr.substring(0, pathStr.lastIndexOf("/"));
                    Luban.with(this)
                            .load(listSelectPic)                                  // 传人要压缩的图片列表
                            .ignoreBy(100)                                  // 忽略不压缩图片的大小
//                            .setTargetDir(savePathStr)                     // 设置压缩后文件存储位置
                            .setCompressListener(new OnCompressListener() { //设置回调
                                @Override
                                public void onStart() {
                                    // TODO 压缩开始前调用，可以在方法内启动 loading UI
                                }

                                @Override
                                public void onSuccess(File file) {
                                    // TODO 压缩成功后调用，返回压缩后的图片文件
                                    String imgPath = file.getPath();
                                    listPicture.remove(listPicture.size() - 1);
                                    Bitmap bitmap = Tooklkit.getImageThumbnail(imgPath, Tooklkit.dip2px(LooklogActivity.this, 480),
                                            Tooklkit.dip2px(LooklogActivity.this, 480));
                                    if (bitmap != null) {
                                        Log.i("图片压缩成功", savePathStr + "地址" + imgPath);
                                        Drawable drawable = new BitmapDrawable(bitmap);
                                        listPicture.add(drawable);
                                        listImgUrl.add(imgPath);
                                        Drawable addPicture = getResources().getDrawable(R.drawable.sjq_add_tupian_sel);
                                        listPicture.add(addPicture);
                                        addPictureAdapter.notifyDataSetChanged();
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
                    final LooklogGredAdapter addPictureAdapter = mapAdapter.get(childViewPosition);
                    final ArrayList<Drawable> listPicture = addPictureAdapter.getListPicture();
                    final ArrayList<String> listImgUrl = addPictureAdapter.getListImgUrl();

                    Luban.with(this)
                            .load(cameraPath)                                  // 传人要压缩的图片列表
                            .ignoreBy(100)                                  // 忽略不压缩图片的大小
//                            .setTargetDir(cameraPath.substring(0, cameraPath.lastIndexOf("/")))                        // 设置压缩后文件存储位置
                            .setCompressListener(new OnCompressListener() { //设置回调
                                @Override
                                public void onStart() {
                                    // TODO 压缩开始前调用，可以在方法内启动 loading UI
                                }

                                @Override
                                public void onSuccess(File file) {
                                    // TODO 压缩成功后调用，返回压缩后的图片文件
                                    String imgPath = file.getPath();
                                    listPicture.remove(listPicture.size() - 1);
                                    Bitmap bitmap = Tooklkit.getImageThumbnail(imgPath, Tooklkit.dip2px(LooklogActivity.this, 480),
                                            Tooklkit.dip2px(LooklogActivity.this, 480));
                                    if (bitmap != null) {
                                        Log.i("图片压缩成功", cameraPath + "地址" + imgPath);
                                        Drawable drawable = new BitmapDrawable(bitmap);
                                        listPicture.add(drawable);
                                        listImgUrl.add(imgPath);
                                        Drawable addPicture = getResources().getDrawable(R.drawable.sjq_add_tupian_sel);
                                        listPicture.add(addPicture);
                                        addPictureAdapter.notifyDataSetChanged();
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
    private void showLoadingDialogMethod(String str) {
        if (loadDataDialog == null) {
            loadDataDialog = new LoadDataDialog(this);
        }
        loadDataDialog.setTitleStr(str);
        loadDataDialog.show();
    }
}
