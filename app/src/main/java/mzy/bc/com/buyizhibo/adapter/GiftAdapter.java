package mzy.bc.com.buyizhibo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mzy.bc.com.buyizhibo.R;
import mzy.bc.com.buyizhibo.bean.GiftBean;

/**
 * Created by Administrator on 2017/4/17.
 */

public class GiftAdapter extends BaseAdapter {
    private List<GiftBean> list;
    private Context context;
    private int pages;
    public GiftAdapter(List<GiftBean> list, Context context, int pages) {
        this.list = list;
        this.context = context;
        this.pages=pages;
    }

    @Override
    public int getCount() {
        return list.size()>(pages+1)*8?8:(list.size()-pages*8);
    }

    @Override
    public Object getItem(int position) {
        return list.get(position+pages*8);
    }

    @Override
    public long getItemId(int position) {
        return position+pages*8;
    }

    public void setList(List <GiftBean> list){
        this.list=list;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if(convertView==null){
            vh=new ViewHolder();
            convertView=View.inflate(context, R.layout.item_bf_gift,null);
            vh.imgDa=(ImageView) convertView.findViewById(R.id.img_gift_item_da);
            vh.imgHuang=(ImageView) convertView.findViewById(R.id.img_gift_huang);
            vh.imgLian=(ImageView) convertView.findViewById(R.id.img_dia_gift_lian);
            vh.tvMoney=(TextView)convertView.findViewById(R.id.tv_one);
            vh.tvJing=(TextView)convertView.findViewById(R.id.tv_gift_jing);
            convertView.setTag(vh);
        }else{
            vh=(ViewHolder) convertView.getTag();
        }
        int pos = position + pages * 8;
        vh.tvMoney.setText(list.get(position).getGiftMoney().toString());
        if(list.get(pos).isDui()){

            vh.imgLian.setImageResource(R.mipmap.pop_gift_choose);
        }else{
            vh.imgLian.setImageResource(R.mipmap.pop_gift_lian);
        }
        vh.imgHuang.setImageResource(R.mipmap.pay_diamond);
        vh.imgDa.setImageResource((Integer) list.get(pos).getGiftImgDa());
        vh.tvJing.setText(list.get(pos).getGiftJingYan().toString());
        return convertView;
    }
    class ViewHolder{
        private ImageView imgDa,imgHuang,imgLian;
        private TextView tvMoney,tvJing;
    }
}
