package mzy.bc.com.buyizhibo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import mzy.bc.com.buyizhibo.R;
import mzy.bc.com.buyizhibo.bean.ReMenBean;
import mzy.bc.com.buyizhibo.custom.GlideCircleTransform;
import mzy.bc.com.buyizhibo.utils.GlideImageLoader;
import mzy.bc.com.buyizhibo.widget.base.MultiTypeBaseAdapter;
import mzy.bc.com.buyizhibo.widget.viewholder.CommonViewHolder;

public class GameAdapter extends MultiTypeBaseAdapter<ReMenBean.ResultBean.ListBean> {
    List<String> imgs = new ArrayList<>();
    private ReMenAdapter.OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private Context mContext;

    public GameAdapter(Context context, List<ReMenBean.ResultBean.ListBean> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
        this.mContext = context;
    }

    @Override
    protected void convert(CommonViewHolder holder, final ReMenBean.ResultBean.ListBean data, int viewType) {
        if (viewType == 0) {
            Banner banner = (Banner) holder.getConvertView().findViewById(R.id.banner);
            setBanner(banner);
        } else {
            /**
             * 1： circleimageview
             2：重写bitmaptransform
             下文用的第二种
             */

            ImageView avator = (ImageView) holder.getConvertView().findViewById(R.id.img_remen_touxiang);
            ImageView pic = (ImageView) holder.getConvertView().findViewById(R.id.img_da);
            TextView name = (TextView) holder.getConvertView().findViewById(R.id.tv_remen_name);
            name.setText(data.getData().getLive_name());
            Glide.with(mContext).load(data.getUser().getUser_data().getAvatar()).transform(new GlideCircleTransform(mContext)).into(avator);//// TODO: 17/4/14 不好使
            Glide.with(mContext).load(data.getData().getPic()).into(pic);
        }
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        if (viewType == 0) {
            return R.layout.recy_item_1;
        }

        return R.layout.item_remen;
    }

    @Override
    protected int getViewType(int position, ReMenBean.ResultBean.ListBean data) {
        if (data == null) {
            return 0;
        } else {
            return 1;
        }


    }

    private void setBanner(Banner banner) {
        imgs.clear();
        //Banner
        // url  图片url
        // target_url 跳转的地方
        // type类型
        imgs.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492059840637&di=dda7a22772760d7c545b9e3eee39df25&imgtype=0&src=http%3A%2F%2Fwww.sznews.com%2Fphoto%2Fimages%2Fattachement%2Fjpg%2Fsite3%2F20150923%2F4439c452e8ec176c977e1d.jpg");
        imgs.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492059840636&di=36b0307eb863de131d0cd6ca762ece7f&imgtype=0&src=http%3A%2F%2Fc11.eoemarket.com%2Fapp0%2F275%2F275112%2Fscreen%2F1525959.jpg");
        imgs.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492059840636&di=cb13964b0dcad556f2793b4b71f62e50&imgtype=0&src=http%3A%2F%2Fliaoning.sinaimg.cn%2F2013%2F1101%2FU8828P1195DT20131101091142.jpg");
        imgs.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492059840662&di=1fdbb0f8f26436468506b90ca0ff739e&imgtype=0&src=http%3A%2F%2Fwww.jder.net%2Fwp-content%2Fuploads%2F2015%2F10%2F20151004135040-23.jpg");
        imgs.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492059840662&di=cf6b64ba6bb82c5655f6a1aab382e44d&imgtype=0&src=http%3A%2F%2Fimg002.21cnimg.com%2Fphotos%2Fgame_0%2F20140419%2Fc100-0-0-600-504_r0%2FB7EA3FF6CE7EC43B3976BA23ABF4D893.jpeg");
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(imgs);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

    }


}
