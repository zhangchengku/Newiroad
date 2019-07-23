package com.zggk.newiroad.thread;

import android.util.Log;


import com.zggk.newiroad.MyApplication;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class NetUtils {

	private static final String TAG = "NetAPICallback";


	/**
	 * 处理网络接口的返回数据
	 * 
	 * @param response
	 *            具体返回信息
	 * @param isShowTips
	 *            是否提示
	 * @return
	 */
	public static String dealResponse(Response<ResponseBody> response, boolean isShowTips) {
		ResponseBody body = response.body();
		if (body == null) {
			okhttp3.Response rawResponse = response.raw();
			if (rawResponse != null) {
				Log.e(TAG, "rawResponse: " + rawResponse.toString());
				// TODO 未知错误
				if (isShowTips)
					MyApplication.app.customToast("未知错误");
			}
			return null;
		}
		String jsonStr = null;
		try {
			jsonStr = body.string();
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			// TODO 未知错误
			if (isShowTips)
				MyApplication.app.customToast("未知错误");
		}
		return jsonStr;
	}

}
