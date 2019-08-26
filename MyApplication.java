package com.zggk.newiroad;

import android.app.Application;
import android.view.Gravity;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;
import com.zggk.newiroad.db.dbhelper.CuringDao;
import com.zggk.newiroad.db.dbhelper.CuringDaoImpl;
import com.zggk.newiroad.utils.SPUtils;
import com.zggk.newiroad.utils.Utils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by 张成昆 on 2019-3-1.
 */

public class MyApplication extends Application {
    public static SPUtils spUtils;
    public static String BASEURL="http://106.37.229.146:5569/CurrencyRCYH/";
    public static String TPDZ= "com.zggk.newiroad.fileprovider";
    public final String APP_FILE_SAVE_PATH = "/mnt/sdcard/zggkiroad/";
    public static MyApplication app;// 应用实
    public static final int SHOW_TOAST_TIMES = 500;// 土司通知显示时长
    public CuringDao curingDao;
    public RequestOptions options;
    public static String GYDWID = "NO";
    public static String GZZLBH = "NO";
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Utils.init(this);
        curingDao=new CuringDaoImpl(this);
        spUtils = new SPUtils(LocalConfiguration.SP_NAME);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10*1000L, TimeUnit.MILLISECONDS)
                .readTimeout(10*1000L,TimeUnit.MILLISECONDS)
                .addInterceptor(new LoggerInterceptor("OkHttpUtils",true))
                .build();
        OkHttpUtils.initClient(okHttpClient);
        options=new RequestOptions()
                .placeholder(R.drawable.morentu)
                .error(R.drawable.morentu)
                .fallback(R.drawable.morentu);
    }
    public void customToast(String textId) {
        if (textId == null)
            textId = "";
        customToast(Gravity.CENTER, SHOW_TOAST_TIMES, textId);
    }
    public void customToast(int location, int duration, String showTxt) {
        Toast.makeText(this,showTxt,duration).show();
    }
}
