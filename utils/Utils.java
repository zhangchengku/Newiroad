package com.zggk.newiroad.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import com.tooklkit.Tooklkit;
import com.zggk.newiroad.MyApplication;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 16/12/08
 *     desc  : Utils初始化相关
 * </pre>
 */
public class Utils {

    private static Context context;
    private static SPUtils spUtils;
    private static int x;                  // 日期属性：年
    private static int y;                  // 日期属性：月
    private static int z;                  // 日期属性：日
    private static Calendar localTime = Calendar.getInstance();    // 当前日期
    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        Utils.context = context.getApplicationContext();
        spUtils = new SPUtils("utilcode");
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) return context;
        throw new NullPointerException("u should init first");
    }

    public static SPUtils getSpUtils() {
        return spUtils;
    }
    public static void setStatusBarColor(Activity activity, int colorId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.setStatusBarColor(activity.getResources().getColor(colorId));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //使用SystemBarTint库使4.4版本状态栏变色，需要先将状态栏设置为透明
//            transparencyBar(activity);
//            SystemBarTintManager tintManager = new SystemBarTintManager(activity);
//            tintManager.setStatusBarTintEnabled(true);
//            tintManager.setStatusBarTintResource(colorId);
        }
    }
    public static String thisMonth() {
        String strY = null;
        x = localTime.get(Calendar.YEAR);
        y = localTime.get(Calendar.MONTH) + 1;
        strY = y >= 10 ? String.valueOf(y) : ("0" + y);
        return x + "-" + strY + "-01";
    }

    public static String thisMonthEnd() {
        String strY = null;
        String strZ = null;
        boolean leap = false;
        x = localTime.get(Calendar.YEAR);
        y = localTime.get(Calendar.MONTH) + 1;
        if (y == 1 || y == 3 || y == 5 || y == 7 || y == 8 || y == 10 || y == 12) {
            strZ = "31";
        }
        if (y == 4 || y == 6 || y == 9 || y == 11) {
            strZ = "30";
        }
        if (y == 2) {
            leap = leapYear(x);
            if (leap) {
                strZ = "29";
            } else {
                strZ = "28";
            }
        }
        strY = y >= 10 ? String.valueOf(y) : ("0" + y);
        return x + "-" + strY + "-" + strZ;
    }
    public static boolean leapYear(int year) {
        boolean leap;
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) leap = true;
                else leap = false;
            } else leap = true;
        } else leap = false;
        return leap;
    }
    public static boolean isNull(String str) {
        if (str != null) {
            str = str.trim();
        }
        if (str == null || "".equals(str)) {
            return true;
        }
        return false;
    }
    public static boolean isNetworkAvailable(Context context) {
        if (context != null) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (manager != null) {
                NetworkInfo info = manager.getActiveNetworkInfo();
                if (info != null) {
                    return info.isAvailable();
                }
            }
        }
        return false;
    }
    public static String getZHMCByZH(String zhStr) {
        String result = "";
        if (!isNull(zhStr)) {
            if (zhStr.contains(".")) {
                String zhfStr = zhStr.substring(0, zhStr.indexOf("."));
                String zhaStr = zhStr.substring(zhStr.indexOf(".") + 1, zhStr.length());
                if (zhaStr.length() == 1) {
                    zhaStr += "00";
                } else if (zhaStr.length() == 2) {
                    zhaStr += "0";
                }
                result = " K" + zhfStr + "+" + zhaStr;
            } else {
                result = " K" + zhStr;
            }
        }
        return result;
    }
    public static void hideInputWindow(Activity context) {
        if (context == null) {
            return;
        }
        final View v = context.getWindow().peekDecorView();
        if (v != null && v.getWindowToken() != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
    /**
     * 处理图片旋转
     *
     * @param path
     */
    public static void dealBitmapRotate(String path) {
        Bitmap bit = Tooklkit.getImageThumbnail(path, 480, 800);
        if (bit != null) {
            int degree = getRotateDegree(path);
            Log.i("拍照后图片的旋转角度:", degree + "");
            if (degree == 90) {
                int width = bit.getWidth();
                int height = bit.getHeight();
                Bitmap bitmapTemp = rotate(bit, degree, width, height, true);
                saveBitmapRotate(bitmapTemp, path);
            }
        }
    }
    public static void saveBitmapRotate(Bitmap image, String path) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 80;
        while (baos.toByteArray().length / 1024 > 300) {// 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            options -= 10;// 每次都减少10
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
        }
        String tempPath = MyApplication.app.APP_FILE_SAVE_PATH;
        String name = path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf("."));
        saveCompressImg(baos, tempPath, name);
    }
    public static Bitmap rotate(Bitmap src, int degrees, int width, int height, boolean recycle) {
        if (src == null) return null;
        if (degrees == 0) return src;
        Matrix matrix = new Matrix();
        matrix.postRotate(degrees);
        Bitmap ret = Bitmap.createBitmap(src, 0, 0, width, height, matrix, true);
        if (recycle && !src.isRecycled()) src.recycle();
        return ret;
    }
    public static int getRotateDegree(String filePath) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(filePath);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }
    public static boolean saveCompressImg(ByteArrayOutputStream bos, String savePath, String imageName) {

        if (TextUtils.isEmpty(savePath)) {
            return false;
        }
        File file = new File(savePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileOutputStream fos = null;
        try {
            if (TextUtils.isEmpty(imageName)) {
                imageName = System.currentTimeMillis() + "";
            }
            File f = new File(file, imageName + ".png");
            fos = new FileOutputStream(f);
            fos.write(bos.toByteArray());
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }
    public static String bmpToBase64String(String imgPath) {

        // 用io流读取到要上传的图片，用Base64编码成字节流的字符串,得到uploadBuffer（要传到webservice接口上）
        FileInputStream fis = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String uploadBuffer = "";
        try {
            if (Utils.isNull(imgPath)) {
                return "";
            }
            fis = new FileInputStream(imgPath);
            byte[] buffer = new byte[16];
            int count = -1;
            while ((count = fis.read(buffer)) >= 0) {
                baos.write(buffer, 0, count);
            }
            // 进行Base64编码得到字符串
            uploadBuffer = Base64.encodeToString(baos.toByteArray(), Base64.NO_WRAP);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uploadBuffer;
    }
    public static int calculateDialogWidth(Context context) {
        Point screenSize = getScreenMetrics(context);
        int dialogW = Math.min(screenSize.x, screenSize.y) * 4 / 5;
        int dp_360 = Tooklkit.dip2px(context, 360);
        if (dialogW <= 0 || dialogW > dp_360) {
            dialogW = dp_360;
        }
        return dialogW;
    }
    public static Point getScreenMetrics(Context context) {
        if (context == null) return new Point();
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        int h_screen = dm.heightPixels;
        return new Point(w_screen, h_screen);
    }
}