package com.zggk.newiroad.thread;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;


import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.utils.Utils;

import java.io.IOException;
import java.lang.ref.WeakReference;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * 网络接口访问的异步任务（单任务）
 *
 * @author wangqian
 */
class NetAPIAsyncTask extends AsyncTask<Void, Void, String> implements CommNetWorkDialogCancelListener{

    private WeakReference<Context> contextReference;// Context上下文对象的弱引用
    private LoadDataDialog dialog;// 网络加载框
    private int tag;// 接口访问标识
    private Call<ResponseBody> call;// 接口的调用者
    private OnNetAPIListener listener;// 接口访问成功后，回调监听

    public NetAPIAsyncTask(Context context, int tag, Call<ResponseBody> call,
                           String dialogTips, OnNetAPIListener listener) {
        super();
        this.contextReference = new WeakReference<>(context);
        this.tag = tag;
        this.call = call;
        this.listener = listener;
        if(!Utils.isNull(dialogTips)){
            initDialog(dialogTips);
        }
    }

    /**
     * 初始化对话框，如果为空则不显示对话框
     *
     * @param dialogTips 对话框的提示信息
     */
    private void initDialog(String dialogTips) {
        Context context = contextReference.get();
        if (TextUtils.isEmpty(dialogTips) || context == null || !(context instanceof Activity)||!isContextValid(context)) {
            return;
        }
        dialog = new LoadDataDialog((Activity) context);
        dialog.setOnDialogCloseListener(this);
        dialog.setTitleStr(dialogTips);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (contextReference.get() != null && dialog != null) {
            dialog.show();
        }
    }

    @Override
    protected String doInBackground(Void... params) {
        if (call == null || !isContextValid(contextReference.get()))
            return null;
        try {
            Response<ResponseBody> resp = call.execute();
            if (contextReference.get() == null) {
                return null;
            }
            if (isCancelled()) {
                call.cancel();
            } else {
                return NetUtils.dealResponse(resp, false);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onCancelled(String result) {
        super.onCancelled(result);
        if (isContextValid(contextReference.get())) {
            if (dialog != null) {
                dialog.close();
            }
            if (result == null && isCancelled()) {
                MyApplication.app.customToast("操作取消");
            }
            if (listener != null) {
                listener.onNetAPISuccess(result, tag);
            }
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (isContextValid(contextReference.get())) {
            if (dialog != null) {
                dialog.close();
            }
            if (listener != null) {
                listener.onNetAPISuccess(result, tag);
            }
        }
    }

    @Override
    public void closeDialog() {
        cancel(true);
        if(dialog!=null&&dialog.isShowing()){
            dialog.cancel();
        }
    }

    private boolean isContextValid(Context context){
        boolean result=true;
        if(context==null){
            result=false;
        }else if((context instanceof Activity)&&((Activity) context).isFinishing()){
            result=false;
        }
        return result;
    }
}
