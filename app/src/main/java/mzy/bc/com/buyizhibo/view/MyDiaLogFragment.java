package mzy.bc.com.buyizhibo.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mzy.bc.com.buyizhibo.R;
import mzy.bc.com.buyizhibo.adapter.BoFangVPDiaLogAdapter;
import mzy.bc.com.buyizhibo.adapter.GiftAdapter;
import mzy.bc.com.buyizhibo.bean.GiftBean;



/**
 * Created by Administrator on 2017/4/19.
 */

public class MyDiaLogFragment extends DialogFragment {
    private Map<String,Integer> map;
    private MyViewPager viewPagerGift;
    private View view;
    private List<GiftBean> giftList;
    private int a;
    private View view01;
    private GridView gridView;
    private GiftAdapter giftAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bofang_gift_dialog, container);
//        getDialog().getWindow().setGravity(Gravity.BOTTOM);
//        WindowManager.LayoutParams p = getDialog().getWindow().getAttributes();
//        p.width = ViewGroup.LayoutParams.MATCH_PARENT;
//        p.softInputMode = WindowManager.LayoutParams.WRAP_CONTENT;
//        p.x = 200;

//        getDialog().getWindow().setAttributes(p);

        return view;
    }
    public void onStart() {
        super.onStart();
        final DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

        final WindowManager.LayoutParams layoutParams = getDialog().getWindow().getAttributes();
        layoutParams.width = dm.widthPixels;
        layoutParams.height=WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = Gravity.BOTTOM;
        getDialog().getWindow().setAttributes(layoutParams);
//        initGiftDiaLog();
    }
//    public void initGiftDiaLog() {
//        map = new HashMap<>();
//        viewPagerGift = (MyViewPager) view.findViewById(R.id.vp_gift);
//        initViewOneDate();
////        setDian();
//    }
    public void initViewOneDate() {
        giftList = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            GiftBean giftBean01 = new GiftBean();
            giftBean01.setDui(false);
            giftBean01.setGiftImgDa(R.mipmap.li);
            giftBean01.setGiftJingYan("+100经验");
            giftBean01.setGiftMoney(6);
            giftList.add(giftBean01);
        }

        map.put("number", 0);
        giftList.get(0).setDui(true);
        a = (int) Math.ceil(giftList.size() * 1.0 / 8);

        List<View> list = new ArrayList<>();

        final List<GiftAdapter> adapters = new ArrayList<>();

        for (int i = 0; i < a; i++) {
            view01 = View.inflate(getActivity(), R.layout.bofang_gift_dialog_view01, null);
            gridView = (GridView) view01.findViewById(R.id.gv_bf_gift_gridview01);

            list.add(view01);
            BoFangVPDiaLogAdapter boFangVPDiaLogAdapter = new BoFangVPDiaLogAdapter(list);
            viewPagerGift.setAdapter(boFangVPDiaLogAdapter);
            giftAdapter = new GiftAdapter(giftList, getActivity(), i);
            adapters.add(giftAdapter);

            gridView.setAdapter(giftAdapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    map.put("number", position);
                    for (int i = 0; i < giftList.size(); i++) {
                        giftList.get(i).setDui(false);
                    }
//                    giftList.get(position + aa * 8).setDui(true);
                    giftAdapter.setList(giftList);
                    for (int a = 0; a < adapters.size(); a++) {
                        adapters.get(a).notifyDataSetChanged();
                    }
                }
            });
        }
        //setListViewHeightBasedOnChildren(gridView);
    }
}
