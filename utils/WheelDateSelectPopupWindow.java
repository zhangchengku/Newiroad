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


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


/**
 * Created by dongxiaoqing on 2018/9/25.
 * 日期滚轮选择器
 */

public class WheelDateSelectPopupWindow extends PopupWindow implements View.OnClickListener {
    /******************
     * 控件声明
     **********************/
    private Activity activity;
    private TextView vCancelTxt;
    private TextView vOkTxt;
    private WheelView vStartYear;
    private WheelView vStartMonth;
    private WheelView vStartDay;
    private WheelView vEndYear;
    private WheelView vEndMonth;
    private WheelView vEndDay;
    private TextView vDaoTxt;//两个日期选择时 到 字的文本
    /******************
     * 变量声明
     **********************/
    private ArrayList<String> yearStartArr;
    private ArrayList<String> yearEndArr;
    private ArrayList<String> monthStartArr;
    private ArrayList<String> monthEndArr;
    private ArrayList<String> dayStartArr;
    private ArrayList<String> dayEndArr;
    private PopMyDataSelectAdapter yearStartAdapter;
    private PopMyDataSelectAdapter yearEndAdapter;
    private PopMyDataSelectAdapter monthStartAdapter;
    private PopMyDataSelectAdapter monthEndAdapter;
    private PopMyDataSelectAdapter dayStartAdapter;
    private PopMyDataSelectAdapter dayEndAdapter;
    private String resultStartStr;
    private String resultEndStr;
    private String resultStartYearStr;
    private String resultStartMonthStr;
    private String resultStartDayStr;
    private String resultEndYearStr;
    private String resultEndMonthStr;
    private String resultEndDayStr;
    private Calendar calendarStart;
    private Calendar calendarEnd;
    private SimpleDateFormat simpleDateFormat;
    private PopSelectListener listener;
    private int showViewItemNumber = 7;//滚轮控件默认可见item个数
    private String yearStr;
    private String monthStr;
    private String dayStr;
    private String[] months_big = {"1月", "3月", "5月", "7月", "8月", "10月", "12月"};
    private String[] months_little = {"4月", "6月", "9月", "11月"};
    final List<String> list_big = Arrays.asList(months_big);
    final List<String> list_little = Arrays.asList(months_little);
    private int dataSelType;//日期选择类型  1两个日期的选择 2一个日期的选择


    public WheelDateSelectPopupWindow(Activity activity, int dataSelType, PopSelectListener listener) {
        this.activity = activity;
        this.listener = listener;
        this.dataSelType = dataSelType;
        View contentView = LayoutInflater.from(activity).inflate(
                R.layout.popup_wheel_date, null);
        setContentView(contentView);
        setWidth(Tooklkit.getWidth(activity) - Tooklkit.dip2px(activity, 25));
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
     *
     * @param contentView
     */
    private void initViews(View contentView) {
        vCancelTxt = (TextView) contentView.findViewById(R.id.wheel_time_cancel_btn);
        vOkTxt = (TextView) contentView.findViewById(R.id.wheel_time_ok_btn);
        vStartYear = (WheelView) contentView.findViewById(R.id.wheel_time_start_year);
        vStartMonth = (WheelView) contentView.findViewById(R.id.wheel_time_start_mounth);
        vStartDay = (WheelView) contentView.findViewById(R.id.wheel_time_start_day);
        vEndYear = (WheelView) contentView.findViewById(R.id.wheel_time_end_year);
        vEndMonth = (WheelView) contentView.findViewById(R.id.wheel_time_end_mounth);
        vEndDay = (WheelView) contentView.findViewById(R.id.wheel_time_end_day);
        vDaoTxt = (TextView) contentView.findViewById(R.id.wheel_time_sel_dao_txt);

        if (dataSelType == 2) {
            vEndYear.setVisibility(View.GONE);
            vEndMonth.setVisibility(View.GONE);
            vEndDay.setVisibility(View.GONE);
            vDaoTxt.setVisibility(View.GONE);
        }

        vCancelTxt.setOnClickListener(this);
        vOkTxt.setOnClickListener(this);
        initWheelAdapterAndDatas();
//        setOnDismissListener(new OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                if (activity instanceof DiseaseListActivity) {
//                    ((DiseaseListActivity) activity).hideZheZhaoView();
//                }
//            }
//        });
    }

    /**
     * 初始化日期选择数据 设置时间选择适配器
     */
    private void initWheelAdapterAndDatas() {

        simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        yearStr = "年";
        monthStr ="月";
        dayStr = "日";

        if (TextUtils.isEmpty(resultStartStr) && TextUtils.isEmpty(resultEndStr)) {
            calendarStart = Calendar.getInstance();
            calendarEnd = Calendar.getInstance();
            calendarStart.set(Calendar.DAY_OF_MONTH, 1);
            calendarEnd.set(Calendar.DAY_OF_MONTH, calendarEnd.getActualMaximum(Calendar.DAY_OF_MONTH));

            resultStartYearStr = String.valueOf(calendarStart.get(Calendar.YEAR)) + yearStr;
            resultStartMonthStr = String.valueOf(calendarStart.get(Calendar.MONTH) + 1) + monthStr;
            resultStartDayStr = String.valueOf(calendarStart.get(Calendar.DAY_OF_MONTH)) + dayStr;

            resultEndYearStr = String.valueOf(calendarEnd.get(Calendar.YEAR)) + yearStr;
            resultEndMonthStr = String.valueOf(calendarEnd.get(Calendar.MONTH) + 1) + monthStr;
            resultEndDayStr = String.valueOf(calendarEnd.get(Calendar.DAY_OF_MONTH)) + dayStr;
        } else {
            try {
                calendarStart = Calendar.getInstance();
                calendarStart.setTime(simpleDateFormat.parse(resultStartStr));
                calendarEnd = Calendar.getInstance();
                calendarEnd.setTime(simpleDateFormat.parse(resultEndStr));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        int yearCurrentStart = calendarStart.get(Calendar.YEAR);//获取年
        int monthCurrentStart = calendarStart.get(Calendar.MONTH) + 1;//获取月份，0表示1月份
        int dayCurrentStart = calendarStart.get(Calendar.DAY_OF_MONTH);//获取当前天数
        int lastStart = calendarStart.getActualMaximum(calendarStart.DAY_OF_MONTH);//获取本月最大天数
        int yearCurrentEnd = calendarEnd.get(Calendar.YEAR);//获取年
        int monthCurrentEnd = calendarEnd.get(Calendar.MONTH) + 1;//获取月份，0表示1月份
        int dayCurrentEnd = calendarEnd.get(Calendar.DAY_OF_MONTH);//获取当前天数
        int lastEnd = calendarEnd.getActualMaximum(calendarEnd.DAY_OF_MONTH);//获取本月最大天数

        int yearStartIndex = 0;
        int yearEndIndex = 0;
        int monthStartIndex = 0;
        int monthEndIndex = 0;
        int dayStartIndex = 0;
        int dayEndIndex = 0;

        yearStartArr = new ArrayList<>();
        yearEndArr = new ArrayList<>();
        monthStartArr = new ArrayList<>();
        monthEndArr = new ArrayList<>();
        dayStartArr = new ArrayList<>();
        dayEndArr = new ArrayList<>();

        for (int i = 1990; i < 2101; i++) {
            yearStartArr.add(i + yearStr);
        }
        for (int i = 1990; i < 2101; i++) {
            yearEndArr.add(i + yearStr);
        }
        for (int i = 1; i < 13; i++) {
            monthStartArr.add(i + monthStr);
            if (i == monthCurrentStart) {
                monthStartIndex = i - 1;
            }
        }
        for (int i = 1; i < 13; i++) {
            monthEndArr.add(i + monthStr);
            if (i == monthCurrentEnd) {
                monthEndIndex = i - 1;
            }
        }
        for (int i = 1; i <= lastStart; i++) {
            dayStartArr.add(i + dayStr);
            if (i == dayCurrentStart) {
                dayStartIndex = i - 1;
            }
        }
        for (int i = 1; i <= lastEnd; i++) {
            dayEndArr.add(i + dayStr);
            if (i == dayCurrentEnd) {
                dayEndIndex = i - 1;
            }
        }

        for (int i = 0; i < yearStartArr.size(); i++) {
            if (yearStartArr.get(i).equals(String.valueOf(yearCurrentStart) + yearStr)) {
                yearStartIndex = i;
            }
            if (yearEndArr.get(i).equals(String.valueOf(yearCurrentEnd) + yearStr)) {
                yearEndIndex = i;
            }
        }

        yearStartAdapter = new PopMyDataSelectAdapter(activity, yearStartArr, yearStartIndex);
        yearEndAdapter = new PopMyDataSelectAdapter(activity, yearEndArr, yearEndIndex);
        monthStartAdapter = new PopMyDataSelectAdapter(activity, monthStartArr, monthStartIndex);
        monthEndAdapter = new PopMyDataSelectAdapter(activity, monthEndArr, monthEndIndex);
        dayStartAdapter = new PopMyDataSelectAdapter(activity, dayStartArr, dayStartIndex);
        dayEndAdapter = new PopMyDataSelectAdapter(activity, dayEndArr, dayEndIndex);

        vStartYear.setViewAdapter(yearStartAdapter);
        vStartYear.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                resultStartYearStr = yearStartArr.get(newValue);
                String currentText = (String) yearStartAdapter.getItemText(wheel.getCurrentItem());
                setTextViewStyle(currentText, yearStartAdapter);
                refreshStartDayPoup();
            }
        });
        vStartYear.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) yearStartAdapter.getItemText(wheel.getCurrentItem());
                setTextViewStyle(currentText, yearStartAdapter);
            }
        });
        vStartYear.setCurrentItem(yearStartIndex);
        vStartYear.setVisibleItems(showViewItemNumber);


        vStartMonth.setViewAdapter(monthStartAdapter);
        vStartMonth.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                resultStartMonthStr = monthStartArr.get(newValue);
                String currentText = (String) monthStartAdapter.getItemText(wheel.getCurrentItem());
                setTextViewStyle(currentText, monthStartAdapter);
                refreshStartDayPoup();
            }
        });
        vStartMonth.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) monthStartAdapter.getItemText(wheel.getCurrentItem());
                setTextViewStyle(currentText, monthStartAdapter);
            }
        });
        vStartMonth.setCurrentItem(monthStartIndex);
        vStartMonth.setVisibleItems(showViewItemNumber);


        vStartDay.setViewAdapter(dayStartAdapter);
        vStartDay.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                resultStartDayStr = dayStartArr.get(newValue);
                String currentText = (String) dayStartAdapter.getItemText(wheel.getCurrentItem());
                setTextViewStyle(currentText, dayStartAdapter);
            }
        });
        vStartDay.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) dayStartAdapter.getItemText(wheel.getCurrentItem());
                setTextViewStyle(currentText, dayStartAdapter);
            }
        });
        vStartDay.setCurrentItem(dayStartIndex);
        vStartDay.setVisibleItems(showViewItemNumber);


        vEndYear.setViewAdapter(yearEndAdapter);
        vEndYear.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                resultEndYearStr = yearEndArr.get(newValue);
                String currentText = (String) yearEndAdapter.getItemText(wheel.getCurrentItem());
                setTextViewStyle(currentText, yearEndAdapter);
                refreshEndDayPoup();
            }
        });
        vEndYear.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) yearEndAdapter.getItemText(wheel.getCurrentItem());
                setTextViewStyle(currentText, yearEndAdapter);
            }
        });
        vEndYear.setCurrentItem(yearEndIndex);
        vEndYear.setVisibleItems(showViewItemNumber);


        vEndMonth.setViewAdapter(monthEndAdapter);
        vEndMonth.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                resultEndMonthStr = monthEndArr.get(newValue);
                String currentText = (String) monthEndAdapter.getItemText(wheel.getCurrentItem());
                setTextViewStyle(currentText, monthEndAdapter);
                refreshEndDayPoup();
            }
        });
        vEndMonth.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) monthEndAdapter.getItemText(wheel.getCurrentItem());
                setTextViewStyle(currentText, monthEndAdapter);
            }
        });
        vEndMonth.setCurrentItem(monthEndIndex);
        vEndMonth.setVisibleItems(showViewItemNumber);


        vEndDay.setViewAdapter(dayEndAdapter);
        vEndDay.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                resultEndDayStr = dayEndArr.get(newValue);
                String currentText = (String) dayEndAdapter.getItemText(wheel.getCurrentItem());
                setTextViewStyle(currentText, dayEndAdapter);
            }
        });
        vEndDay.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) dayEndAdapter.getItemText(wheel.getCurrentItem());
                setTextViewStyle(currentText, dayEndAdapter);
            }
        });
        vEndDay.setCurrentItem(dayEndIndex);
        vEndDay.setVisibleItems(showViewItemNumber);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.wheel_time_cancel_btn:
                close();
                break;
            case R.id.wheel_time_ok_btn:
                try {
                    resultStartStr = resultStartYearStr + resultStartMonthStr + resultStartDayStr;
                    resultEndStr = resultEndYearStr + resultEndMonthStr + resultEndDayStr;
                    calendarStart.setTime(simpleDateFormat.parse(resultStartStr));
                    calendarEnd.setTime(simpleDateFormat.parse(resultEndStr));
                    long startLong = calendarStart.getTimeInMillis();
                    long endLong = calendarEnd.getTimeInMillis();
                    if (dataSelType == 2) {
                        listener.selectResult(resultStartStr);
                        close();
                    } else {
                        if (startLong > endLong) {
                            MyApplication.app.customToast("结束日期不能小于开始日期");
                        } else {
                            close();
                            listener.selectResult(resultStartStr, resultEndStr);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
            if (!TextUtils.isEmpty(resultStartStr)) {
                initWheelAdapterAndDatas();
            }
            showAtLocation(anchor, Gravity.BOTTOM, 0, 0);
//            if (activity instanceof LogActivity) {
//                ((LogActivity) activity).showZheZhaoView();
//            }
        }
    }

    /**
     * 设置初始开始时间
     */
    public void setInitStartDate(String dateStr) {
        resultStartStr = dateStr;
        resultEndStr = dateStr;
        initWheelAdapterAndDatas();
    }

    /**
     * 设置初始结束时间
     */
    public void setInitStartAndEndDate(String startStr,String endStr) {
        resultStartStr = startStr;
        resultEndStr=endStr;
        initWheelAdapterAndDatas();
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

    /**
     * 刷新开始日期 日的适配器
     */
    private void refreshStartDayPoup() {
        int lastStart = 30;
        if (list_big.contains(resultStartMonthStr)) {
            lastStart = 31;
        } else if (list_little.contains(resultStartMonthStr)) {
            lastStart = 30;
        } else {
            Integer selYear = Integer.parseInt(resultStartYearStr.substring(0, resultStartYearStr.length() - 1));
            if (isLeapYear(selYear)) {
                lastStart = 29;
            } else {
                lastStart = 28;
            }
        }
        if (lastStart != dayStartArr.size()) {
            dayStartArr.clear();
            for (int i = 1; i <= lastStart; i++) {
                dayStartArr.add(i + dayStr);
            }
            int currentIndex = 0;
            if (lastStart < vStartDay.getCurrentItem()) {
                currentIndex = lastStart - 1;
            } else {
                currentIndex = vStartDay.getCurrentItem();
            }
            dayStartAdapter = new PopMyDataSelectAdapter(activity, dayStartArr, currentIndex);
            vStartDay.setViewAdapter(dayStartAdapter);
        }
    }

    /**
     * 刷新结束日期 日的适配器
     */
    private void refreshEndDayPoup() {
        int lastEnd = 30;
        if (list_big.contains(resultEndMonthStr)) {
            lastEnd = 31;
        } else if (list_little.contains(resultEndMonthStr)) {
            lastEnd = 30;
        } else {
            Integer selYear = Integer.parseInt(resultEndYearStr.substring(0, resultEndYearStr.length() - 1));
            if (isLeapYear(selYear)) {
                lastEnd = 29;
            } else {
                lastEnd = 28;
            }
        }
        if (lastEnd != dayEndArr.size()) {
            dayEndArr.clear();
            for (int i = 1; i <= lastEnd; i++) {
                dayEndArr.add(i + dayStr);
            }
            int currentIndex = 0;
            if (lastEnd < vEndDay.getCurrentItem()) {
                currentIndex = lastEnd - 1;
            } else {
                currentIndex = vEndDay.getCurrentItem();
            }
            dayEndAdapter = new PopMyDataSelectAdapter(activity, dayEndArr, currentIndex);
            vEndDay.setViewAdapter(dayEndAdapter);
        }
    }

    /**
     * 判断是否是闰年
     *
     * @param selYear
     * @return
     */
    private boolean isLeapYear(int selYear) {
        boolean result;
        if ((selYear % 4 == 0 && selYear % 100 != 0) || selYear % 400 == 0) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

}
