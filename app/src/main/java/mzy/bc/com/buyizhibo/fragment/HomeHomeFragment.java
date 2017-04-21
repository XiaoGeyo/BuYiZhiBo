package mzy.bc.com.buyizhibo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import mzy.bc.com.buyizhibo.R;
import mzy.bc.com.buyizhibo.adapter.MyFragmentPagerAdapter;

/**
 * Created by Administrator on 2017/4/11.
 */

public class HomeHomeFragment extends Fragment {
    private View view;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private RelativeLayout relativeLayout;

    //    private Application myApplication;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment_home, null);
        initViews();
        return view;
    }

    private void initViews() {
//        myApplication=(MyApplication) getActivity().getApplication();
        relativeLayout = (RelativeLayout) view.findViewById(R.id.home_rl);
//        myApplication.setTextView(relativeLayout);
        List<Fragment> list = new ArrayList<>();
        list.add(new HomeGuanZhuFragment());
        list.add(new HomeReMenFragment());
        list.add(new HomeFuJinFragment());
        list.add(new HomeShiPinFragment());
        list.add(new HomeGameFragment());
        list.add(new HomeCaiYiFragment());
        list.add(new HomeHaoShengYinFragment());
        //使用适配器将ViewPager与Fragment绑定在一起
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager(), list);
        mViewPager.setAdapter(myFragmentPagerAdapter);
        //将TabLayout与ViewPager绑定在一起
        mTabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(2);
    }
}
