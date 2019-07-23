package com.zggk.newiroad.lookdisease;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.zggk.newiroad.Bean.BhDetailInfo;
import com.zggk.newiroad.Bean.CZFADATABean;
import com.zggk.newiroad.Bean.ItemBeanInfo;
import com.zggk.newiroad.Bean.TPListInfo;
import com.zggk.newiroad.R;
import com.zggk.newiroad.disease.AddLogPictureGridAdapter;
import com.zggk.newiroad.disease.ListAdapter;

import java.util.ArrayList;

/**
 * 病害详情病害信息列表适配器
 * Created by dongxiaoqing on 2018/10/11.
 */

public class DiseaseDetailAdapter extends BaseAdapter {

    private  String dclxmc;
    private  ArrayList<CZFADATABean> czfaList= new ArrayList<>();
    private Context context;
    private ArrayList<BhDetailInfo> listInfo;
    private ArrayList<TPListInfo> tpList;//详情图片集合
    private ListAdapter disadapter;
    private ArrayList<ItemBeanInfo> gcxmlist = new ArrayList<>();

    public DiseaseDetailAdapter(Context context, ArrayList<BhDetailInfo> listInfo, ArrayList<TPListInfo> tpList,ArrayList<CZFADATABean> czfaList) {
        this.context = context;
        this.listInfo = listInfo;
        this.tpList = tpList;
        this.czfaList = czfaList;
        this.dclxmc=dclxmc;
    }

    @Override
    public int getCount() {
        return listInfo.size();
    }

    @Override
    public Object getItem(int position) {
        return listInfo.get(position);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.disease_custom_item, parent, false);
            vh.bhmcTxt = (TextView) convertView.findViewById(R.id.disease_custom_item_bhmc_txt);
            vh.cjtpLayout = (LinearLayout) convertView.findViewById(R.id.disease_custom_item_cjtp_layout);
            vh.vPictureGridView = (GridView) convertView.findViewById(R.id.cai_ji_picture_show_grid);
            vh.bhlxTxt = (TextView) convertView.findViewById(R.id.bhlx);
            vh.sgfsTxt = (TextView) convertView.findViewById(R.id.sgfs);
            vh.bhlx=(TextView)convertView.findViewById(R.id.bhlx);
            vh.sgfs=(TextView)convertView.findViewById(R.id.sgfs);
            vh.ddiseaselist = (ListView) convertView.findViewById(R.id.add_ddisease_list_view);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        BhDetailInfo diseaseInfo = listInfo.get(position);
        vh.bhlx.setText(diseaseInfo.getSHBW());
        vh.bhmcTxt.setText(diseaseInfo.getBHMC());
        vh.sgfs.setText(diseaseInfo.getCZFAMC());
          if (czfaList != null && czfaList.size() > 0) {
            for (int i = 0;i<czfaList.size();i++){
                ItemBeanInfo itembean = new ItemBeanInfo();
                itembean.setXmmc(czfaList.get(i).getGCXM());
                itembean.setJldw(czfaList.get(i).getDW());
                itembean.setJsgs(czfaList.get(i).getJSGS());
                gcxmlist.add(itembean);
            }
            disadapter = new ListAdapter(context,gcxmlist);
            vh.ddiseaselist.setAdapter(disadapter);
        }
        ArrayList<String> listImgUrl = new ArrayList<>();
        if (tpList != null && tpList.size() > 0) {
            for(int k=0;k<tpList.size();k++){
                TPListInfo tpinfo=tpList.get(k);
                if(tpinfo!=null){
                    String imgUrl=tpinfo.getFILEPATH();
                    listImgUrl.add(imgUrl);
                }
            }
            AddLogPictureGridAdapter imgAdapter = new AddLogPictureGridAdapter(context, listImgUrl);
            vh.vPictureGridView.setAdapter(imgAdapter);
        }
        return convertView;
    }



    class ViewHolder {
        TextView bhmcTxt;//病害名称
        ListView ddiseaselist;
        LinearLayout cjtpLayout;//图片采集列表适配器
        GridView vPictureGridView;//采集的图片显示列表
        TextView bhlxTxt;//工程量计算公式结果
        TextView sgfsTxt;//工程量计算公式结果
        TextView bhlx;
        TextView sgfs;
    }
}
