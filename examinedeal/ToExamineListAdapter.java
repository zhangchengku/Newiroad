package com.zggk.newiroad.examinedeal;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.zggk.newiroad.Bean.ToExamineInfo;
import com.zggk.newiroad.MyApplication;
import com.zggk.newiroad.R;
import com.zggk.newiroad.home.distribute.DistributeActivity;
import com.zggk.newiroad.lookdisease.LookdiseaseActivity;
import com.zggk.newiroad.repair.RepairActivity;
import com.zggk.newiroad.repair.lookrepair.LookRepairActivity;
import com.zggk.newiroad.repair.lookrepairrely.LookRepairRelyActivity;
import com.zggk.newiroad.utils.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * 审核派发列表适配器
 * Created by dongxiaoqing on 2018/10/30.
 */

public class ToExamineListAdapter extends BaseAdapter {

    private  String GZZLBH;
    private Context context;
    private ArrayList<ToExamineInfo> listData;
    private ArrayList<Boolean> listSelectTag;
    private boolean isAvaiableChoose = true;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss yyyy/MM/dd");
    private SimpleDateFormat simpleDateFormatOld = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public  ToExamineListAdapter(Context context, ArrayList<ToExamineInfo> listData, boolean isAvaiableChoose,String GZZLBH) {
        this.context = context;
        this.listData = listData;
        this.isAvaiableChoose = isAvaiableChoose;
        this.GZZLBH  =GZZLBH;
        listSelectTag = new ArrayList<>();
        int size = listData.size();
        for (int i = 0; i < size; i++) {
            listSelectTag.add(false);
        }
    }

    public ArrayList<Boolean> getListSelectTag() {
        return listSelectTag;
    }

    public void setListSelectTag(ArrayList<Boolean> listSelectTag) {
        this.listSelectTag = listSelectTag;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.to_examine_list_adapter_item, parent, false);
            vh.checkBox = (CheckBox) convertView.findViewById(R.id.to_examine_list_item_sel_check);
            vh.stateTxt = (TextView) convertView.findViewById(R.id.to_examine_list_item_state_txt);
            vh.timeTxt = (TextView) convertView.findViewById(R.id.to_examine_list_item_time_txt);
            vh.positionTxt = (TextView) convertView.findViewById(R.id.to_examine_list_item_position_txt);
            vh.typeTxt = (TextView) convertView.findViewById(R.id.to_examine_list_item_type_txt);
            vh.unitTxt = (TextView) convertView.findViewById(R.id.to_examine_list_item_unit_txt);
            vh.selectLayout = (LinearLayout) convertView.findViewById(R.id.to_examine_list_item_select_layout);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        if (position % 2 == 0) {
            convertView.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.com_listview_selector));
        } else {
            convertView.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.shpf_list_item_bg_sel));
        }

        if (isAvaiableChoose) {
            final int fpos = position;
            vh.selectLayout.setVisibility(View.VISIBLE);
            vh.checkBox.setOnCheckedChangeListener(null);
            if (listSelectTag != null && listSelectTag.size() > position) {
                vh.checkBox.setChecked(listSelectTag.get(position));
            }
            vh.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (fpos < listSelectTag.size()) {
                        listSelectTag.set(fpos, isChecked);
                    }
                }
            });
        } else {
            vh.selectLayout.setVisibility(View.GONE);
        }

        final ToExamineInfo info = listData.get(position);
        String state = info.getBHZT();
        if ("1".equals(state)) {
            vh.stateTxt.setTextColor(context.getResources().getColor(R.color.pf_xin_tian_jia_text_color));
        } else if ("2".equals(state)) {
            vh.stateTxt.setTextColor(context.getResources().getColor(R.color.pf_xin_bing_hai_text_color));
        } else if ("3".equals(state)) {
            vh.stateTxt.setTextColor(context.getResources().getColor(R.color.disease_flag_text_bg_ypf_yys_ywx));
        } else {
            vh.stateTxt.setTextColor(context.getResources().getColor(R.color.text_6));
        }

        String stateStr = getNameByState(state);
        vh.stateTxt.setText(stateStr);

        String str = info.getDCSJ().replace("T", " ");
        try {
            String timeStr = simpleDateFormat.format(simpleDateFormatOld.parse(str));
            vh.timeTxt.setText(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String[] arrStr = info.getLXBM().split(" ");
        String beforeStr = arrStr[0];
        String afterStr = "";
        String afterStrZh = "";
        if (arrStr.length == 2) {
            afterStr = arrStr[1];
        }
        if (!Utils.isNull(afterStr)) {
            afterStrZh = Utils.getZHMCByZH(afterStr);
        }
        String weizhiStr = beforeStr + "\n" + afterStrZh;
        vh.positionTxt.setText(weizhiStr);
        vh.typeTxt.setText(info.getBHMC());
        vh.unitTxt.setText(info.getDCR());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (GZZLBH.equals("已办")){
                        Intent intent = new Intent(context, LookRepairRelyActivity.class);
                        intent.putExtra("cjbhid", info.getBHID());
                        context.startActivity(intent);
                    }else {
                        if (MyApplication.GYDWID.equals("NO")){
                            MyApplication.app.customToast("请选择施工单位");
                        }else {
                            Intent intent = new Intent(context, LookRepairActivity.class);
                            intent.putExtra("cjbhid", info.getBHID());
                            intent.putExtra("BHZT", info.getBHZT());
                            context.startActivity(intent);
                        }

                    }
            }
        });

        return convertView;
    }

    class ViewHolder {
        CheckBox checkBox;
        TextView stateTxt;
        TextView timeTxt;
        TextView positionTxt;
        TextView typeTxt;
        TextView unitTxt;
        LinearLayout selectLayout;
    }

    public void setAllSelect() {
        if (listSelectTag != null && listSelectTag.size() > 0) {
            for (int i = 0; i < listSelectTag.size(); i++) {
                listSelectTag.set(i, true);
            }
            notifyDataSetChanged();
        }
    }

    public void setAllNoSelect() {
        if (listSelectTag != null && listSelectTag.size() > 0) {
            for (int i = 0; i < listSelectTag.size(); i++) {
                listSelectTag.set(i, false);
            }
            notifyDataSetChanged();
        }
    }

    /**
     * 根据状态获取状态对应的名称
     *
     * @param stateStr
     * @return
     */

    private String getNameByState(String stateStr) {
        String result = "";
        if ("0".equals(stateStr)) {
            result = context.getResources().getString(R.string.state_dai_shang_chuan);
        } else if ("1".equals(stateStr)) {
            result = context.getResources().getString(R.string.state_xin_tian_jia);
        } else if ("2".equals(stateStr)) {
            result = context.getResources().getString(R.string.state_xin_bing_hai);
        } else if ("3".equals(stateStr)) {
            result = context.getResources().getString(R.string.state_yi_pai_fa);
        } else if ("4".equals(stateStr)) {
            result = context.getResources().getString(R.string.state_yi_wei_xiu);
        } else if ("5".equals(stateStr)) {
            result = context.getResources().getString(R.string.state_yi_yan_shou);
        } else if ("6".equals(stateStr)) {
            result = context.getResources().getString(R.string.state_yan_shou_tui_hui);
        } else if ("7".equals(stateStr)) {
            result = context.getResources().getString(R.string.state_fa_qi_kao_he);
        } else if ("8".equals(stateStr)) {
            result = context.getResources().getString(R.string.state_kao_he_tui_hui);
        } else if ("9".equals(stateStr)) {
            result = context.getResources().getString(R.string.state_pi_liang_tui_hui);
        } else if ("10".equals(stateStr)) {
            result = context.getResources().getString(R.string.state_yi_kao_he);
        } else if ("11".equals(stateStr)) {
            result = context.getResources().getString(R.string.state_yi_ji_liang);
        } else if ("12".equals(stateStr)) {
            result = context.getResources().getString(R.string.state_dai_kao_he);
        } else if ("13".equals(stateStr)) {
            result = context.getResources().getString(R.string.state_shi_tong_he_ge);
        } else if ("14".equals(stateStr)) {
            result = context.getResources().getString(R.string.state_shi_gong_dan_wei_bing_hai);
        }else if ("15".equals(stateStr)){
            result = context.getResources().getString(R.string.state_shi_na_ru_da_xiu);
        }else if("17".equals(stateStr)){
            result = context.getResources().getString(R.string.yi_shang_bao);
        }else if("18".equals(stateStr)){
            result = context.getResources().getString(R.string.shen_pi_tong_guo);
        }else if("19".equals(stateStr)){
            result = context.getResources().getString(R.string.shen_pi_bu_tong_guo);
        }else if("20".equals(stateStr)){
            result = context.getResources().getString(R.string.shi_chu_yi_pai_fa);
        }
        return result;
    }
}

