package com.zggk.newiroad.thread;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zggk.newiroad.R;


/**
 * 接口调用通用转圈提示框
 * Created by mobo-pc on 2018/9/7.
 */
public class LoadDataDialog extends Dialog{
    private View vDialogLy;//弹出框布局
    private WindowManager.LayoutParams params;// 外框params
    private CommNetWorkDialogCancelListener customCancelListener;
    private TextView txtTitle;

    public LoadDataDialog(Context context) {
        super(context);
        vDialogLy = View.inflate(context, R.layout.comm_loaddata_layout,null);
        txtTitle =(TextView)vDialogLy.findViewById(R.id.com_loaddate_tv);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标头
        getWindow().getDecorView().getBackground().setAlpha(0);// 设置透明度
        params = getWindow().getAttributes();// 获取params
        getWindow().setContentView(vDialogLy);// 加载xml
        getWindow().setAttributes(
                (WindowManager.LayoutParams) params);
        // 获得屏幕宽
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        params.width = LinearLayout.LayoutParams.WRAP_CONTENT;
        setCanceledOnTouchOutside(false);
    }

    /**
     * 设置关闭的监听事件
     *
     * @param listener
     */
    public void setOnDialogCloseListener(
            CommNetWorkDialogCancelListener listener) {
        customCancelListener=listener;
    }

    /**
     * 关闭
     */
    public void close() {
        if(customCancelListener!=null){
            customCancelListener.closeDialog();
            customCancelListener=null;
        }
    }

    /**
     * 设置加载提示语内容
     * @param str
     */
    public void setTitleStr(String str){
        if(txtTitle!=null){
            txtTitle.setText(str);
        }
    }
}
