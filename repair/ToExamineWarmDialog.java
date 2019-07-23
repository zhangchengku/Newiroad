package com.zggk.newiroad.repair;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.tooklkit.Tooklkit;
import com.zggk.newiroad.R;
import com.zggk.newiroad.utils.Dialog.CommBtnListener;

/**
 * 审核派发提示框
 * Created by dongxiaoqing on 2018/10/30.
 */

public class ToExamineWarmDialog {
    private Dialog dialog;// 对话框
    private TextView txtCancel;//取消按钮
    private TextView txtOk;//确定按钮

    public Dialog getDialog() {
        return dialog;
    }

    /**
     * 构造方法
     *
     * @param context
     *            使用该对话框的类

     * @param okListener
     *            确定按钮点击监听
     */
    public ToExamineWarmDialog(final Activity context, final CommBtnListener okListener) {
        dialog = new Dialog(context, R.style.CustomDialogStyle);
        dialog.setCanceledOnTouchOutside(false);
        View viewDialog = LayoutInflater.from(context).inflate(
                R.layout.to_examine_warm_dialog_layout, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(Tooklkit.dip2px(context, 300),
                ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.addContentView(viewDialog, params);
        txtOk = (TextView) viewDialog
                .findViewById(R.id.to_examine_warm_dialog_ok_btn);
        txtCancel = (TextView) viewDialog
                .findViewById(R.id.to_examine_warm_dialog_cancel_btn);
        String okStr="确认";
        String cancelStr="取消";
        txtOk.setText(okStr);
        txtCancel.setText(cancelStr);
        // 确定按钮
        txtOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                okListener.CommOkBtnClick();
            }
        });
        // 取消按钮
        txtCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                okListener.CommCancelBtnClick();
                if(dialog.isShowing()){
                    dialog.cancel();
                }
            }
        });
    }


    /**
     * 显示
     */
    public void show() {
        if(dialog!=null){
            dialog.show();
        }
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