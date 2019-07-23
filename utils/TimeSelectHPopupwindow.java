package com.zggk.newiroad.utils;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;


import com.tooklkit.Tooklkit;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.R;
import com.zggk.newiroad.main.MainActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


/**
 * Created by dongxiaoqing on 2018/9/25.
 * 日期滚轮选择器
 */

public class TimeSelectHPopupwindow extends PopupWindow implements View.OnClickListener {
    /******************控件声明**********************/
    private Activity activity;
    private TextView vCancelTxt;
    private TextView vOkTxt;

    private WheelView vSelectHour;
    private WheelView vSelectMinute;
    /******************变量声明**********************/
    private ArrayList<String> yearSelectArr;
    private ArrayList<String> monthSelectArr;
    private ArrayList<String> daySelectArr;
    private ArrayList<String> hourSelectArr;
    private ArrayList<String> minuteSelectArr;
    private PopMyDataSelectAdapter yearSelectAdapter;
    private PopMyDataSelectAdapter monthSelectAdapter;
    private PopMyDataSelectAdapter daySelectAdapter;
    private PopMyDataSelectAdapter hourSelectAdapter;
    private PopMyDataSelectAdapter minuteSelectAdapter;
    private String resultSelectStr;

    private String resultSelectHourStr;
    private String resultSelectMinuteStr;
    private Calendar calendarSelect;
    private SimpleDateFormat simpleDateFormat;
    private PopSelectListener listener;
    private int showViewItemNumber=7;//滚轮控件默认可见item个数
    private String yearStr;
    private String monthStr;
    private String dayStr;
    private String hourStr;
    private String minuteStr;
    private String[] months_big = {"1月", "3月", "5月", "7月", "8月", "10月", "12月"};
    private String[] months_little = {"4月", "6月", "9月", "11月"};
    final List<String> list_big = Arrays.asList(months_big);
    final List<String> list_little = Arrays.asList(months_little);
    private WheelView vSelectHour2;
    private WheelView vSelectMinute2;
    private String resultSelectMinuteStr2;
    private String resultSelectHourStr2;
    private String resultStartStr;
    private String resultEndStr;

    public TimeSelectHPopupwindow(Activity activity, PopSelectListener listener) {
        this.activity = activity;
        this.listener = listener;
        View contentView = LayoutInflater.from(activity).inflate(
                R.layout.popu, null);
        setContentView(contentView);
        setWidth(Tooklkit.getWidth(activity)- Tooklkit.dip2px(activity,25));
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setTouchable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable(0));
        setAnimationStyle(R.style.mypopwindow_anim_style);
        initViews(contentView);
    }

    /**
     * 初始化控件
     * @param contentView
     */
    private void initViews(View contentView) {
        vCancelTxt = (TextView) contentView.findViewById(R.id.wheel_time_cancel_btn);
        vOkTxt = (TextView) contentView.findViewById(R.id.wheel_time_ok_btn);
        vSelectHour = (WheelView) contentView.findViewById(R.id.sh);
        vSelectMinute = (WheelView) contentView.findViewById(R.id.sf);

        vSelectHour2 = (WheelView) contentView.findViewById(R.id.es);
        vSelectMinute2 = (WheelView) contentView.findViewById(R.id.ef);
        vCancelTxt.setOnClickListener(this);
        vOkTxt.setOnClickListener(this);
        initWheelAdapterAndDatas();
//        setOnDismissListener(new OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                if(activity instanceof MainActivity){
//                    ((MainActivity)activity).hideZheZhaoView();
//                }
//            }
//        });

    }

    /**
     * 初始化日期选择数据 设置时间选择适配器
     */
    private void initWheelAdapterAndDatas() {

        simpleDateFormat = new SimpleDateFormat("HH时mm分");
        hourStr = "时";
        minuteStr = "分";

        if (TextUtils.isEmpty(resultSelectStr)) {
            calendarSelect = Calendar.getInstance();
            resultSelectHourStr=String.valueOf(calendarSelect.get(Calendar.HOUR_OF_DAY))+hourStr;
            resultSelectMinuteStr=String.valueOf(calendarSelect.get(Calendar.MINUTE))+minuteStr;
        } else {
            try {
                calendarSelect = Calendar.getInstance();
                calendarSelect.setTime(simpleDateFormat.parse(resultSelectStr));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        int hourCurrent = calendarSelect.get(Calendar.HOUR_OF_DAY);//获取时
        int minuteCurrent = calendarSelect.get(Calendar.MINUTE);//获取分
        int lastDayCount = calendarSelect.getActualMaximum(calendarSelect.DAY_OF_MONTH);//获取本月最大天数


        int hourSelectIndex = 0;
        int minuteSelectIndex = 0;


        hourSelectArr = new ArrayList<>();
        minuteSelectArr = new ArrayList<>();
        for (int i = 0; i <= 23; i++) {
            hourSelectArr.add(i + hourStr);
            if (i == hourCurrent) {
                hourSelectIndex = i;
            }
        }
        for (int i = 0; i <= 59; i++) {
            minuteSelectArr.add(i + minuteStr);
            if (i == minuteCurrent) {
                minuteSelectIndex = i;
            }
        }
        hourSelectAdapter = new PopMyDataSelectAdapter(activity, hourSelectArr, hourSelectIndex);
        minuteSelectAdapter = new PopMyDataSelectAdapter(activity, minuteSelectArr, minuteSelectIndex);

        vSelectHour.setViewAdapter(hourSelectAdapter);
        vSelectHour.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                resultSelectHourStr = hourSelectArr.get(newValue);
                String currentText = (String) hourSelectAdapter.getItemText(wheel.getCurrentItem());
                setTextViewStyle(currentText, hourSelectAdapter);
            }
        });
        vSelectHour.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) hourSelectAdapter.getItemText(wheel.getCurrentItem());
                setTextViewStyle(currentText, hourSelectAdapter);
            }
        });
        vSelectHour.setCurrentItem(hourSelectIndex);
        vSelectHour.setVisibleItems(showViewItemNumber);

        vSelectHour2.setViewAdapter(hourSelectAdapter);
        vSelectHour2.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                resultSelectHourStr2 = hourSelectArr.get(newValue);
                String currentText = (String) hourSelectAdapter.getItemText(wheel.getCurrentItem());
                setTextViewStyle(currentText, hourSelectAdapter);
            }
        });
        vSelectHour2.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) hourSelectAdapter.getItemText(wheel.getCurrentItem());
                setTextViewStyle(currentText, hourSelectAdapter);
            }
        });
        vSelectHour2.setCurrentItem(hourSelectIndex);
        vSelectHour2.setVisibleItems(showViewItemNumber);
        vSelectMinute.setViewAdapter(minuteSelectAdapter);
        vSelectMinute.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                resultSelectMinuteStr = minuteSelectArr.get(newValue);
                String currentText = (String) minuteSelectAdapter.getItemText(wheel.getCurrentItem());
                setTextViewStyle(currentText, minuteSelectAdapter);
            }
        });
        vSelectMinute.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) minuteSelectAdapter.getItemText(wheel.getCurrentItem());
                setTextViewStyle(currentText, minuteSelectAdapter);
            }
        });
        vSelectMinute.setCurrentItem(minuteSelectIndex);
        vSelectMinute.setVisibleItems(showViewItemNumber);


        vSelectMinute2.setViewAdapter(minuteSelectAdapter);
        vSelectMinute2.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                resultSelectMinuteStr2 = minuteSelectArr.get(newValue);
                String currentText = (String) minuteSelectAdapter.getItemText(wheel.getCurrentItem());
                setTextViewStyle(currentText, minuteSelectAdapter);
            }
        });
        vSelectMinute2.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) minuteSelectAdapter.getItemText(wheel.getCurrentItem());
                setTextViewStyle(currentText, minuteSelectAdapter);
            }
        });
        vSelectMinute2.setCurrentItem(minuteSelectIndex);
        vSelectMinute2.setVisibleItems(showViewItemNumber);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.wheel_time_cancel_btn:
                close();
                break;
            case R.id.wheel_time_ok_btn:
                try {
                    resultStartStr= resultSelectHourStr+resultSelectMinuteStr;
                    resultEndStr= resultSelectHourStr2+resultSelectMinuteStr2;

                    calendarSelect.setTime(simpleDateFormat.parse(resultStartStr));
                    calendarSelect.setTime(simpleDateFormat.parse(resultEndStr));
                    long startLong = calendarSelect.getTimeInMillis();
                    long endLong = calendarSelect.getTimeInMillis();
                        if (startLong > endLong) {
                            MyApplication.app.customToast("结束日期不能小于开始日期");
                        } else {
                            close();
                            listener.selectResult(resultStartStr, resultEndStr);
                        }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                close();
                break;
        }
    }

    /**
     * 显示筛选窗口
     */
    public void show(View anchor) {
        if (isShowing()) {
            return;
        }
        if (anchor != null) {
            if (!TextUtils.isEmpty(resultSelectStr)) {
                initWheelAdapterAndDatas();
            }
            showAtLocation(anchor, Gravity.BOTTOM, 0, 0);
//            if (activity instanceof MainActivity) {
//                ((MainActivity)activity).showZheZhaoView();
//            }
        }
    }

    /**
     * 关闭筛选窗口
     */
    public void close() {
        if (isShowing()) {
            dismiss();
        }
    }

    /**
     * 设置文字的颜色和大小
     *
     * @param curriteItemText
     * @param adapter
     */
    public void setTextViewStyle(String curriteItemText, PopMyDataSelectAdapter adapter) {
        ArrayList<View> arrayList = adapter.getTestViews();
        int size = arrayList.size();
        String currentText;
        for (int i = 0; i < size; i++) {
            TextView textvew = (TextView) arrayList.get(i);
            currentText = textvew.getText().toString();
            if (curriteItemText.equals(currentText)) {
                textvew.setTextColor(activity.getResources().getColor(R.color.text_3));
            } else {
                textvew.setTextColor(activity.getResources().getColor(R.color.text_9));
            }
        }
    }
}