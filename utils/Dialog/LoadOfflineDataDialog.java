package com.zggk.newiroad.utils.Dialog;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.tooklkit.Tooklkit;
import com.zggk.newiroad.Bean.LiXianbean;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.R;
import com.zggk.newiroad.thread.DownloadTxtThread;

import java.util.ArrayList;

/**
 * 加载离线包显示的对话框
 * Created by dongxiaoqing on 2018/11/4.
 */

public class LoadOfflineDataDialog {

    private Dialog dialog;// 对话框
    private TextView titleTxt;//标题
    private TextView contentTxt;//提示内容
    private TextView vAnimTxt;
    private ProgressBar progressBar;//进度条
    private Activity activity;
    private ArrayList<LiXianbean> listInfo;
    private boolean isLoadOfflineData;
    private int pro = 0;
    private final int MSG = 1;
    private final int LOAD_SUCCESS = 2;
    private final int REFRESH_SUCCESS = 3;
    private Handler mHandler;
    private int total;
    private InsertDatabaseSuccessListener listener;
    private ValueAnimator valueAnimator;
    private String loadingStr;
    private String[] dotText = {"", " . ", " . . ", " . . ."};

    public Dialog getDialog() {
        return dialog;
    }

    /**
     * 构造方法
     *
     * @param context 使用该对话框的类
     */
    public LoadOfflineDataDialog(final Activity context, ArrayList<LiXianbean> listInfo) {
        this.activity = context;
        this.listInfo = listInfo;
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
        vAnimTxt = (TextView) viewDialog.findViewById(R.id.load_offline_data_title_anim_txt);
        isLoadOfflineData = MyApplication.spUtils.getBoolean("isLoadOfflineData", false);
        if (isLoadOfflineData) {
            loadingStr = "离线数据包更新中";
            titleTxt.setText(loadingStr);
        } else {
            loadingStr = "离线数据包下载中";
            titleTxt.setText(loadingStr);
        }

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

        total = listInfo.size();
        String totalNumber = String.valueOf(total);
        String currentLoadNumber = "1";
        setWarmContent(totalNumber, currentLoadNumber);

        listener = new InsertDatabaseSuccessListener() {
            @Override
            public void insertSuccess() {
                successNext();
            }
        };

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
                        if (pro < total) {
                            String currentLoadNumber = String.valueOf(pro + 1);
                            setWarmContent(totalNumber, currentLoadNumber);
                        }
                        break;
                    case LOAD_SUCCESS:
                        MyApplication.app.customToast("离线数据包下载成功");
                        MyApplication.spUtils.put("isLoadOfflineData", true);
                        close();
                        break;
                    case REFRESH_SUCCESS:
                        MyApplication.app.customToast("离线数据包更新成功");
                        close();
                        break;
                    default:
                        break;
                }
            }
        };
        start();
    }

    /**
     * 开始下载数据
     */
    public void start() {
        LiXianbean info = listInfo.get(pro);
        Thread thread = new DownloadTxtThread(info, listener);
        thread.start();
    }

    /**
     * 插入成功之后继续插入下一个表
     */
    public void successNext() {
        pro += 1;
        Message msg = new Message();
        msg.what = MSG;
        mHandler.sendMessage(msg);
        if (pro >= total) {
            if (isLoadOfflineData) {
                Message msgRefresh = new Message();
                msgRefresh.what = REFRESH_SUCCESS;
                mHandler.sendMessageDelayed(msgRefresh,500);
            } else {
                Message msgLoad = new Message();
                msgLoad.what = LOAD_SUCCESS;
                mHandler.sendMessageDelayed(msgLoad,500);
            }
        } else {
            start();
        }
    }

    /**
     * 设置对话框的提示内容
     *
     * @param totalNumber       总共需要下载的离线包的个数
     * @param currentLoadNumber 当前正在下载第几个离线包
     */
    public void setWarmContent(String totalNumber, String currentLoadNumber) {
        String content = "";
        if (isLoadOfflineData) {
            content = String.format(activity.getString(R.string.offline_data_refresh_content), totalNumber, currentLoadNumber);
        } else {
            content = String.format(activity.getString(R.string.offline_data_loading_content), totalNumber, currentLoadNumber);
        }
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
        dialog.cancel();
    }
}
