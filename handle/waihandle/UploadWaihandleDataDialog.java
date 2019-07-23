package com.zggk.newiroad.handle.waihandle;

/**
 * Created by 张成昆 on 2019-5-27.
 */

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

import com.zggk.newiroad.Bean.addhandlejson;
import com.zggk.newiroad.Bean.waiHandleItembean;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.R;

import com.zggk.newiroad.db.dbhelper.CuringDao;
import com.zggk.newiroad.db.dbhelper.CuringDaoImpl;
import com.zggk.newiroad.db.dbhelper.dbInfo.ConstructionUploadInfo;
import com.zggk.newiroad.utils.Utils;
import com.tooklkit.Tooklkit;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class UploadWaihandleDataDialog {

    private Dialog dialog;// 对话框
    private TextView titleTxt;//标题
    private TextView contentTxt;//提示内容
    private TextView vAnimTxt;
    private ProgressBar progressBar;//进度条
    private Fragment activity;
    private List<ConstructionUploadInfo> listDataLocal;

    private ConstructionUploadInfo baseInfo;//当前正在上传的条目

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
    private List<String> hdlist= new ArrayList<>();

    public Dialog getDialog() {
        return dialog;
    }

    /**
     * 构造方法
     *
     * @param context 使用该对话框的类
     */
    public UploadWaihandleDataDialog(final Fragment context, List<ConstructionUploadInfo> listDataLocal) {
        this.activity = context;
        this.listDataLocal = listDataLocal;

        dialog = new Dialog(activity.getContext(), R.style.CustomDialogStyle);
        dialog.setCanceledOnTouchOutside(false);
        View viewDialog = LayoutInflater.from(activity.getContext()).inflate(
                R.layout.load_offline_data_dialog, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(Tooklkit.dip2px(activity.getContext(), 300),
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
                        if(activity instanceof WaihandleFragment){
                            ((WaihandleFragment)activity).refreshDataMethod();
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
    private void saveDiseaseNetwork(ConstructionUploadInfo baseInfo) {
        if (baseInfo != null) {
            addhandlejson addhandlejson = new addhandlejson();
            addhandlejson.setBHGUID(baseInfo.getBHID());
            addhandlejson.setQDZH(baseInfo.getQDZH());
            addhandlejson.setJLDW(baseInfo.getDW());
            addhandlejson.setSGDW(MyApplication.app.spUtils.getString("dqgydwid"));
            addhandlejson.setSGFZR(baseInfo.getDCR());
            addhandlejson.setBZ(baseInfo.getSGMX());
            ArrayList<addhandlejson.PICBean> listPic = new ArrayList<>();
            if (!Utils.isNull(baseInfo.getSGQTP())) {
                addhandlejson.PICBean picInfo = new addhandlejson.PICBean();
                String nameStr = baseInfo.getSGQTP().substring(baseInfo.getSGQTP().lastIndexOf("/") + 1, baseInfo.getSGQTP().length());
                String typeStr = baseInfo.getSGQTP().substring(baseInfo.getSGQTP().lastIndexOf(".") + 1, baseInfo.getSGQTP().length());
                picInfo.setPicFileName(nameStr);
                picInfo.setPicFileType(typeStr);
                String strBlob = Utils.bmpToBase64String(baseInfo.getSGQTP());
                picInfo.setPicDataBlob(strBlob);
                picInfo.setWJLX("0");
                listPic.add(picInfo);
            }
            if (!Utils.isNull(baseInfo.getSGZTP())) {
                addhandlejson.PICBean picInfo = new addhandlejson.PICBean();
                String nameStr = baseInfo.getSGZTP().substring(baseInfo.getSGZTP().lastIndexOf("/") + 1, baseInfo.getSGZTP().length());
                String typeStr = baseInfo.getSGZTP().substring(baseInfo.getSGZTP().lastIndexOf(".") + 1, baseInfo.getSGZTP().length());
                picInfo.setPicFileName(nameStr);
                picInfo.setPicFileType(typeStr);
                String strBlob = Utils.bmpToBase64String(baseInfo.getSGZTP());
                picInfo.setPicDataBlob(strBlob);
                picInfo.setWJLX("1");
                listPic.add(picInfo);
            }
            if (!Utils.isNull(baseInfo.getSGHTP())) {
                addhandlejson.PICBean picInfo = new addhandlejson.PICBean();
                String nameStr = baseInfo.getSGHTP().substring(baseInfo.getSGHTP().lastIndexOf("/") + 1, baseInfo.getSGHTP().length());
                String typeStr = baseInfo.getSGHTP().substring(baseInfo.getSGHTP().lastIndexOf(".") + 1, baseInfo.getSGHTP().length());
                picInfo.setPicFileName(nameStr);
                picInfo.setPicFileType(typeStr);
                String strBlob = Utils.bmpToBase64String(baseInfo.getSGHTP());
                picInfo.setPicDataBlob(strBlob);
                picInfo.setWJLX("2");
                listPic.add(picInfo);
            }
            addhandlejson.setPIC(listPic);



            gcxmidlist =  Arrays.asList(baseInfo.getGCXMID().split(","));//id
            sllist =  Arrays.asList(baseInfo.getSL().split(","));//id
            hdlist =  Arrays.asList(baseInfo.getHD().split(","));//id
            ArrayList<addhandlejson.CZFABean> cZFAlist = new ArrayList<>();
            for (int i = 0;i<gcxmidlist.size();i++){
                addhandlejson.CZFABean   cZFABean  =    new addhandlejson.CZFABean();
                cZFABean.setGCXMID(gcxmidlist.get(i));
                cZFABean.setHD(hdlist.get(i));
                cZFABean.setJSGS(sllist.get(i));
                cZFAlist.add(cZFABean);
            }
            addhandlejson.setCZFA(cZFAlist);
            ArrayList<addhandlejson>  datelist =  new ArrayList<>();
            datelist.add(addhandlejson);
            String json = gson.toJson(datelist);
            OkHttpUtils.post()
                    .tag(this)
                    .addParams("json",json )
                    .url(MyApplication.BASEURL+"MobileYhwx")
                    .build()
                    .execute(new StringCallback()
                    {
                        @Override
                        public void onError(okhttp3.Call call, Exception e, int id) {
                        }
                        @Override
                        public void onResponse(String response, int id) {
                            waiHandleItembean baseInfo = JSON.parseObject(response, waiHandleItembean.class);
                            if (baseInfo != null) {
                                if ("1".equals(baseInfo.getSTATE())) {
                                    Log.i("病害采集提交成功", "病害采集提交成功===" + currentUploadItem + " - " + currentUploadItemCurrentCount);
                                    currentUploadItemCurrentCount++;
                                    successNext();
                                } else if (!Utils.isNull(baseInfo.getMSG())) {
                                    MyApplication.app.customToast(baseInfo.getMSG());
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
            CuringDao curingDao=new CuringDaoImpl(activity.getContext());
            for (int i = 0; i < listDataLocal.size(); i++) {
                ConstructionUploadInfo diseaseBaseInfo = listDataLocal.get(i);
                if (diseaseBaseInfo != null) {
                    curingDao.deleteSgwxUploadById(diseaseBaseInfo.getSGWXID());
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
        String content = String.format(activity.getString(R.string.handle_data_uploading_content), totalNumber, currentLoadNumber);
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