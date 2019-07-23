package com.zggk.newiroad.check;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.zggk.newiroad.Bean.ItemSgjdInfo;
import com.zggk.newiroad.R;

import java.util.ArrayList;

/**
 * Created by 张成昆 on 2019-3-26.
 */

public class sgjdAdapter extends BaseAdapter {


    private ArrayList<ItemSgjdInfo> listda;
    Context context;
    LayoutInflater inflater;
    public sgjdAdapter(Context context, ArrayList<ItemSgjdInfo> listda) {
        this.inflater=LayoutInflater.from(context);
        this.context=context;
        this.listda=listda;
    }

    @Override
    public int getCount() {
        return listda.size();
    }

    @Override
    public Object getItem(int position) {
        return listda.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView){
            convertView = inflater.inflate(R.layout.item_sgjd,null);
            holder =new ViewHolder(convertView,position);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.nr_hd.setText(listda.get(position).getGCXM());
        holder.editText.setHint(listda.get(position).getJSGS());
        holder.dw.setText("=  "+listda.get(position).getSL()+"  "+listda.get(position).getDW());
        if (listda.get(position).getHD()!=null){
         holder.editText_hd.setHint(listda.get(position).getHD());
        }
        return convertView;
    }
    class ViewHolder{
        EditText editText;
        TextView dw;

        TextView nr_hd;
        EditText editText_hd;
        TextView dw_hd;
        public ViewHolder(View view,int pisition){
            editText= (EditText) view.findViewById(R.id.ed);
            dw= (TextView) view.findViewById(R.id.dw);
            nr_hd = (TextView) view.findViewById(R.id.nr1);
            editText_hd= (EditText) view.findViewById(R.id.ed_hd);
            dw_hd= (TextView) view.findViewById(R.id.hd);
            editText_hd.setTag(pisition);
            editText_hd.addTextChangedListener(new TextSwitcher2(this));
            editText.setTag(pisition);//存tag值
            editText.addTextChangedListener(new TextSwitcher(this));
        }
    }
    class TextSwitcher2 implements TextWatcher {
        private ViewHolder mHolder;

        public TextSwitcher2(ViewHolder mHolder) {
            this.mHolder = mHolder;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            int position = (int) mHolder.editText_hd.getTag();//取tag值
            listda.get(position).setHD(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }


    class TextSwitcher implements TextWatcher {
        private ViewHolder mHolder;

        public TextSwitcher(ViewHolder mHolder) {
            this.mHolder = mHolder;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            int position = (int) mHolder.editText.getTag();//取tag值
            listda.get(position).setJSGS(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
    public ArrayList<ItemSgjdInfo> get(){
        return listda;
    }

}