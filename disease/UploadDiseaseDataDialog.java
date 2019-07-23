package com.zggk.newiroad.disease;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

import com.tooklkit.Tooklkit;
import com.zggk.newiroad.Bean.Adddiseasejson;
import com.zggk.newiroad.Bean.adddiseasebean;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.R;
import com.zggk.newiroad.db.dbhelper.CuringDao;
import com.zggk.newiroad.db.dbhelper.CuringDaoImpl;
import com.zggk.newiroad.db.dbhelper.dbInfo.DiseaseBaseInfo;
import com.zggk.newiroad.utils.Utils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 巡查病害列表页面 上传多条待上传数据时显示的进度条对话框
 * Created by dongxiaoqing on 2018/11/5.
 */

public class UploadDiseaseDataDialog {

    private Dialog dialog;// 对话框
    private TextView titleTxt;//标题
    private TextView contentTxt;//提示内容
    private TextView vAnimTxt;
    private ProgressBar progressBar;//进度条
    private Activity activity;
    private ArrayList<DiseaseBaseInfo> listDataLocal;

    private DiseaseBaseInfo baseInfo;//当前正在上传的条目

    private int pro = 0;
    private final int MSG = 1;
    private final int UPLOAD_SUCCESS = 2;
    private Handler mHandler;
    private int total;//总条数
    private Gson gson=new Gson();
    private final int SAVE_DISEASE_TAG = 3;//上传病害数据调用网络接口标识

    private int currentUploadItem=0;//当前正在上传的条目下标

    private int currentUploadItemCurrentCount=0;//当前正在上传的条目包含的病害信息的个数正在上传的病害信息的下标
    private ValueAnimator valueAnimator;
    private String loadingStr;
    private String[] dotText = {""," . ", " . . ", " . . ."};
    private List<String> gcxmidlist = new ArrayList<>();
    private List<String> sllist = new ArrayList<>();

    public Dialog getDialog() {
        return dialog;
    }
    private CuringDao curingDao =new CuringDaoImpl(activity);;
    /**
     * 构造方法
     *
     * @param context 使用该对话框的类
     */
    public UploadDiseaseDataDialog(final Activity context, ArrayList<DiseaseBaseInfo> listDataLocal) {
        this.activity = context;
        this.listDataLocal = listDataLocal;

        dialog = new Dialog(context, R.style.CustomDialogStyle);
        dialog.setCanceledOnTouchOutside(false);
        View viewDialog = LayoutInflater.from(context).inflate(
                R.layout.load_offline_data_dialog, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(Tooklkit.dip2px(context, 300),
                ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.addContentView(viewDialog, params);
        progressBar = (ProgressBar) viewDialog
                .findViewById(R.id.load_offline_data_progressbar);
        titleTxt = (TextView) viewDialog
                .findViewById(R.id.load_offline_data_title_txt);
        contentTxt = (TextView) viewDialog
                .findViewById(R.id.load_offline_data_value_txt);
        vAnimTxt=(TextView)viewDialog.findViewById(R.id.load_offline_data_title_anim_txt);

        titleTxt.setText("数据上传中");
        if (valueAnimator == null) {
            valueAnimator = ValueAnimator.ofInt(0, 4).setDuration(1000);
            valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int i = (int) animation.getAnimatedValue();
                    vAnimTxt.setText(dotText[i % dotText.length]);
                }
            });
        }
        valueAnimator.start();

        total = listDataLocal.size();
        String totalNumber = String.valueOf(total);
        String currentLoadNumber = "1";
        setWarmContent(totalNumber, currentLoadNumber);
        progressBar.setMax(total);
        //创建一个Handler
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //处理消息
                switch (msg.what) {
                    case MSG:
                        //设置滚动条和text的值
                        progressBar.setProgress(pro);
                        String totalNumber = String.valueOf(total);
                        if(pro<total){
                            String currentLoadNumber = String.valueOf(pro + 1);
                            setWarmContent(totalNumber, currentLoadNumber);
                        }
                        break;
                    case UPLOAD_SUCCESS:
                        if(activity instanceof DiseaseActivity){
                            ((DiseaseActivity)activity).refreshDataMethod();
                        }
                        MyApplication.app.customToast("上传成功");
                        close();
                        break;
                    default:
                        break;
                }
            }
        };
        start();
    }
    private void saveDiseaseNetwork(DiseaseBaseInfo baseInfo) {
        if (baseInfo != null) {
            ArrayList<Adddiseasejson> LIST= new ArrayList<>();
            Adddiseasejson adddiseasejson = new Adddiseasejson();
            adddiseasejson.setBHMC(curingDao.queryCzfaByBhmc(baseInfo.getBHMC()).getBHLXID());
            adddiseasejson.setCREATOR(MyApplication.spUtils.getString("dlr"));
            adddiseasejson.setCZFAMC(baseInfo.getSGFS());
            adddiseasejson.setDCLX(curingDao.queryInvestigationByMc(baseInfo.getDCLX()).getDCID());
            adddiseasejson.setDCR(MyApplication.spUtils.getString("dlr"));
            adddiseasejson.setDCSJ(baseInfo.getCJSJ());
            adddiseasejson.setFXDW(MyApplication.spUtils.getString("dqgydwid"));
            adddiseasejson.setGYDW(MyApplication.spUtils.getString("dqgydwid"));
            adddiseasejson.setLXCODE(curingDao.queryLdByLdmc(baseInfo.getLDMC()).getLXID());
            adddiseasejson.setQDZH(baseInfo.getZH());
            adddiseasejson.setSFJL("0");
            adddiseasejson.setSHBW(baseInfo.getBHLX());
            adddiseasejson.setWZFX(baseInfo.getWZFX());
            ArrayList<Adddiseasejson.CZFABean> cZFAlist= new ArrayList<>();
            gcxmidlist =  Arrays.asList(baseInfo.getGGXMID().split(","));//id
            sllist =  Arrays.asList(baseInfo.getSL().split(","));//id
            for (int i = 0;i<gcxmidlist.size();i++){
                Adddiseasejson.CZFABean   cZFABean  =    new Adddiseasejson.CZFABean();
                cZFABean.setGCXMID(gcxmidlist.get(i));
                cZFABean.setJSGS(sllist.get(i));
                cZFAlist.add(cZFABean);
            }
            adddiseasejson.setCZFA(cZFAlist);
            if (!Utils.isNull(baseInfo.getTPDZ())) {
                String[] strArr = baseInfo.getTPDZ().split(",");
                if (strArr != null && strArr.length > 0) {
                    ArrayList<Adddiseasejson.PICBean> listPic = new ArrayList<>();
                    for (int i = 0; i < strArr.length; i++) {
                        Adddiseasejson.PICBean picInfo = new Adddiseasejson.PICBean();
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
                    adddiseasejson.setPIC(listPic);
                }
            }
            LIST.add(adddiseasejson);
            String tijiaodates = gson.toJson(LIST);
            OkHttpUtils.post()
                    .tag(this)
                    .addParams("json",tijiaodates )
                    .url(MyApplication.BASEURL+"MobileUpdatebh")
                    .build()
                    .execute(new StringCallback()
                    {
                        @Override
                        public void onError(okhttp3.Call call, Exception e, int id) {
                        }
                        @Override
                        public void onResponse(String response, int id) {
                            adddiseasebean baseInfo = JSON.parseObject(response, adddiseasebean.class);
                            if (baseInfo != null) {
                                if ("1".equals(baseInfo.getState())) {
                                    Log.i("病害采集提交成功", "病害采集提交成功===" + currentUploadItem + " - " + currentUploadItemCurrentCount);
                                    currentUploadItemCurrentCount++;
                                    successNext();
                                } else if (!Utils.isNull(baseInfo.getData())) {
                                    MyApplication.app.customToast(baseInfo.getData());
                                }
                            } else {
                                if (dialog != null && dialog.isShowing()) {
                                    dialog.cancel();
                                }
                                MyApplication.app.customToast("您当前网络状态不好");
                            }


                        }

                    });

        }
    }

    /**
     * 上传一条成功之后继续上传下一条
     */
    public void successNext() {
        pro += 1;
        Message msg = new Message();
        msg.what = MSG;
        mHandler.sendMessage(msg);
        if (pro >= total) {
            //如果是编辑页面的立即上传 上传成功后需要删除数据库里的记录\

            for (int i = 0; i < listDataLocal.size(); i++) {
                DiseaseBaseInfo diseaseBaseInfo = listDataLocal.get(i);
                if (diseaseBaseInfo != null) {
                    curingDao.deleteBingHaiBaseById(diseaseBaseInfo.getBHJBID());
                }
            }
            Message msgLoad = new Message();
            msgLoad.what = UPLOAD_SUCCESS;
            mHandler.sendMessageDelayed(msgLoad,500);
        } else {
            currentUploadItem+=1;
            start();
        }
    }

    private void start(){
        baseInfo=listDataLocal.get(currentUploadItem);
        currentUploadItemCurrentCount=0;
        saveDiseaseNetwork(baseInfo);
    }



    /**
     * 设置对话框的提示内容
     *
     * @param totalNumber       总共需要上传的病害的个数
     * @param currentLoadNumber 当前正在上传第几个病害
     */
    public void setWarmContent(String totalNumber, String currentLoadNumber) {
        String content = String.format(activity.getString(R.string.disease_data_uploading_content), totalNumber, currentLoadNumber);
        contentTxt.setText(content);
    }

    /**
     * 显示
     */
    public void show() {
        dialog.show();
    }

    /**
     * 关闭
     */
    public void close() {
        if(dialog!=null&&dialog.isShowing()){
            dialog.cancel();
        }
    }


    }
