package com.zggk.newiroad.thread;

import android.content.Context;
import android.content.res.Resources.NotFoundException;

import java.io.IOException;
import java.lang.ref.WeakReference;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;


/**
 * 网络接口的执行者
 * 
 * @author wangqian
 *
 */
public class NetAPIExecutor {

	private WeakReference<Context> contextReference;// 使用WeakReference确保activity能被回收

	public static NetAPIExecutor newInstance(Context context) {
		return new NetAPIExecutor(context);
	}

	public NetAPIExecutor(Context context) {
		contextReference = new WeakReference<>(context);
	}



	/**
	 * （同步执行）单任务
	 * 
	 * @param call
	 *            接口请求
	 * @return 接口返回的结果
	 */
	public String exec(Call<ResponseBody> call) {
		if (call == null)
			return null;
		try {
			Response<ResponseBody> resp = call.execute();
			return NetUtils.dealResponse(resp, false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * （异步执行）单任务
	 * 
	 * @param tag
	 *            接口访问标识
	 * @param call
	 *            接口请求
	 * @param dialogTipsResId
	 *            对话框的提示信息资源Id，找不到Id则不显示对话框
	 * @param listener
	 *            接口访问成功的回调监听
	 */
	public void async(int tag, Call<ResponseBody> call, int dialogTipsResId, OnNetAPIListener listener) {
		async(tag, call, getStringById(dialogTipsResId), listener);
	}

	/**
	 * （异步执行）单任务
	 * 
	 * @param tag
	 *            接口访问标识
	 * @param call
	 *            接口请求
	 * @param dialogTips
	 *            对话框的提示信息，为空则不显示对话框
	 * @param listener
	 *            接口访问成功的回调监听
	 */
	public void async(int tag, Call<ResponseBody> call, String dialogTips, OnNetAPIListener listener) {
		new NetAPIAsyncTask(contextReference.get(), tag, call, dialogTips, listener).execute();
	}

	/**
	 * 获取资源文件中对应的字符串
	 * 
	 * @param strResId
	 *            字符串的资源Id
	 * @return
	 */
	private String getStringById(int strResId) {
		if(contextReference.get() != null) {
			try {
				return contextReference.get().getResources().getString(strResId);
			} catch (NotFoundException e) {
				// e.printStackTrace();
			}
		}
		return null;
	}
}
