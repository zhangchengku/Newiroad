package com.zggk.newiroad.check;

import android.animation.ValueAnimator;
import android.app.Dialog;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

import com.tooklkit.Tooklkit;
import com.zggk.newiroad.Bean.CheckxqBean;
import com.zggk.newiroad.Bean.checkwaibean;
import com.zggk.newiroad.Bean.ysjsonbean;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.R;
import com.zggk.newiroad.check.checkwai.CheckwaiFragment;
import com.zggk.newiroad.utils.Utils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * Created by 10270 on 2018/11/19.
 */

public class UploadcheckDataDialog {

    private Dialog dialog;// 对话框
    private TextView titleTxt;//标题
    private TextView contentTxt;//提示内容
    private TextView vAnimTxt;
    private ProgressBar progressBar;//进度条
    private Fragment activity;
    private List<checkwaibean.BHLISTBean> listDataLocal;

    private checkwaibean.BHLISTBean baseInfo;//当前正在上传的条目

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

    public Dialog getDialog() {
        return dialog;
    }

    /**
     * 构造方法
     *
     * @param context 使用该对话框的类
     */
    public UploadcheckDataDialog(Fragment context, List<checkwaibean.BHLISTBean> listDataLocal) {
        this.activity = context;
        this.listDataLocal = listDataLocal;

        dialog = new Dialog(context.getActivity(), R.style.CustomDialogStyle);
        dialog.setCanceledOnTouchOutside(false);
        View viewDialog = LayoutInflater.from(context.getActivity()).inflate(
                R.layout.load_offline_data_dialog, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(Tooklkit.dip2px(context.getActivity(), 300),
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
                        if(activity instanceof CheckwaiFragment){
                            ((CheckwaiFragment) activity).refreshDataMethod();
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

    private void saveDiseaseNetwork(checkwaibean.BHLISTBean baseInfo) {
        if (baseInfo != null ) {
            ysjsonbean ysjsonbean = new ysjsonbean();
            ysjsonbean.setBHGUID(baseInfo.getBHID());
            ysjsonbean.setYSDW(MyApplication.spUtils.getString("dqgydwid"));
            ysjsonbean.setYSR(MyApplication.spUtils.getString("dlr"));
            ysjsonbean.setBZ("");
            String tijiaodates = gson.toJson(ysjsonbean);
            OkHttpUtils.post()
                    .tag(this)
                    .addParams("json", tijiaodates)
                    .addParams("sfhg", "1")
                    .url(MyApplication.BASEURL+"MobileYsjl")
                    .build()
                    .execute(new StringCallback()
                    {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                        }
                        @Override
                        public void onResponse(String response, int id) {
                            CheckxqBean videoVos2 = JSON.parseObject(response, CheckxqBean.class);
                            if (videoVos2 != null) {
                                if ("1".equals(videoVos2.getSTATE())) {
                                    currentUploadItemCurrentCount++;
                                    successNext();
                                } else if (!Utils.isNull(videoVos2.getMSG())) {
                                    MyApplication.app.customToast(videoVos2.getMSG());
                                }
                            }else{
                                if(dialog!=null&&dialog.isShowing()){
                                    dialog.cancel();
                                }
                                MyApplication.app.customToast("当前没有网络");
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


    public static String replaceNull(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }
}

