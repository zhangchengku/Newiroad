package com.zggk.newiroad.thread;

/**
 * 网络接口访问的回调监听（单任务）
 * 
 * @author wangqian
 *
 */
public interface OnNetAPIListener {

	/**
	 * 接口访问成功的回调（单任务）
	 * 
	 * @param resultJson
	 *            接口访问成功后，返回的JSON字符串
	 * @param taskTag
	 *            接口访问标识
	 */
	void onNetAPISuccess(String resultJson, int taskTag);
}
