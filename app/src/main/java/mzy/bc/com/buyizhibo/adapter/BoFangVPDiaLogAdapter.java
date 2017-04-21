package mzy.bc.com.buyizhibo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by Administrator on 2017/4/17.
 */

public class BoFangVPDiaLogAdapter extends PagerAdapter {
    private List<View> list;

    public BoFangVPDiaLogAdapter(List<View> list) {

        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    //这个方法用来实例化页卡
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(list.get(position), 0);
        return list.get(position);
    }

    //删除实例化页卡
    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        // TODO Auto-generated method stub
        container.removeView(list.get(position));
    }
}
