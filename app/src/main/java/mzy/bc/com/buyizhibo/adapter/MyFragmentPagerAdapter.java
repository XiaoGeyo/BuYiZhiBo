package mzy.bc.com.buyizhibo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/4/11.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles = new String[]{"关注", "热门", "附近","视频","游戏","才艺","好声音"};
    private List<Fragment> list;
    public MyFragmentPagerAdapter(FragmentManager fm,List<Fragment> list) {

        super(fm);
        this.list=list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
