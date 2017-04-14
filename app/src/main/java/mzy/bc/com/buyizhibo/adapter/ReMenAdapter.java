package mzy.bc.com.buyizhibo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

import java.util.ArrayList;
import java.util.List;

import mzy.bc.com.buyizhibo.R;
import mzy.bc.com.buyizhibo.bean.ReMenBean;
import mzy.bc.com.buyizhibo.utils.GlideImageLoader;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/4/12.
 */

public class ReMenAdapter extends RecyclerView.Adapter implements View.OnClickListener{
    private List<String> images;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    Context context;
    List<ReMenBean.ResultBean.ListBean> list;
    public ReMenAdapter(Context context,List<ReMenBean.ResultBean.ListBean> list) {
        this.context = context;
        this.list=list;
        Log.d("ReMenAdapter","size="+list.size());
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v,(String)v.getTag());
        }
    }
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , String data);
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        images=new ArrayList<>();
        images.add("http://img3.imgtn.bdimg.com/it/u=2981621185,1491200394&fm=23&gp=0.jpg");
        images.add("http://img5.imgtn.bdimg.com/it/u=2784455070,472339001&fm=23&gp=0.jpg");
        images.add("http://img5.imgtn.bdimg.com/it/u=4023628722,3156634869&fm=23&gp=0.jpg");
        images.add("http://img3.imgtn.bdimg.com/it/u=3525088218,3987730843&fm=23&gp=0.jpg");

        if(viewType==0){
            BannerViewHolder holder01 = new BannerViewHolder(LayoutInflater.from(
                    context).inflate(R.layout.banner_layout, parent,
                    false));
            return holder01;
        }else{
            View view=LayoutInflater.from(context).inflate(R.layout.item_remen, parent,false);
            MyViewHolder holder02 = new MyViewHolder(view);
            view.setOnClickListener(this);
            return holder02;
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {



        if(holder instanceof BannerViewHolder){

            //设置banner样式
            ((BannerViewHolder)holder).banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            //设置图片加载器
            ((BannerViewHolder)holder).banner.setImageLoader(new GlideImageLoader());
            //设置图片集合
            ((BannerViewHolder)holder).banner.setImages(images);
            //设置banner动画效果
            ((BannerViewHolder)holder).banner.setBannerAnimation(Transformer.DepthPage);
            //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(titles);
            //设置自动轮播，默认为true
            ((BannerViewHolder)holder).banner.isAutoPlay(true);
            //设置轮播时间
            ((BannerViewHolder)holder).banner.setDelayTime(1500);
            //设置指示器位置（当banner模式中有指示器时）
            ((BannerViewHolder)holder).banner.setIndicatorGravity(BannerConfig.CENTER);
            //banner设置方法全部调用完毕时最后调用
            ((BannerViewHolder)holder).banner.start();
        }else{
            Picasso.with(context).load(list.get(position).getData().getPic()).into(((MyViewHolder)holder).imgDa);
            ((MyViewHolder)holder).tvName.setText(list.get(position).getUser().getUser_data().getUser_name().toString());
            Picasso.with(context).load(list.get(position).getUser().getUser_data().getAvatar()).into(((MyViewHolder)holder).imgTou);
        }


    }
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgTou,imgDa;
        TextView tvName,tvNumber;

        public MyViewHolder(View view)
        {
            super(view);
            tvName = (TextView) view.findViewById(R.id.tv_remen_name);
            tvNumber = (TextView) view.findViewById(R.id.tv_remen_number);
            imgTou=(ImageView) view.findViewById(R.id.img_remen_touxiang);
            imgDa=(ImageView) view.findViewById(R.id.img_da);
        }
    }
    class BannerViewHolder extends  RecyclerView.ViewHolder{
        private Banner banner;
        public BannerViewHolder(View itemView) {
            super(itemView);
            banner = (Banner) itemView.findViewById(R.id.banner);
        }
    }



    public void setImage(String url, final ImageView imageView){
        OkHttpUtils.get().url(url).build().execute(new BitmapCallback() {
            @Override
            public void onResponse(Bitmap bitmap, int i) {
                try {
                    imageView.setImageBitmap(bitmap);
                } catch (Exception e) {
                }
            }
            @Override
            public void onError(Call arg0, Exception arg1, int arg2) {
                // TODO Auto-generated method stub
            }
        });

    }
}
