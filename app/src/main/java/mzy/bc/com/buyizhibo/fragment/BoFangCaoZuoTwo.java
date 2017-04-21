package mzy.bc.com.buyizhibo.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mzy.bc.com.buyizhibo.R;
import mzy.bc.com.buyizhibo.activity.GiftControl;
import mzy.bc.com.buyizhibo.adapter.BoFangUserListAdapter;
import mzy.bc.com.buyizhibo.adapter.BoFangVPDiaLogAdapter;
import mzy.bc.com.buyizhibo.adapter.GiftAdapter;
import mzy.bc.com.buyizhibo.adapter.GiftMsgAdapter;
import mzy.bc.com.buyizhibo.bean.GiftBean;
import mzy.bc.com.buyizhibo.view.SpacesItemDecoration;
import org.dync.giftlibrary.widget.GiftFrameLayout;
import org.dync.giftlibrary.widget.GiftModel;

/**
 * Created by Administrator on 2017/4/17.
 */

public class BoFangCaoZuoTwo extends Fragment implements View.OnClickListener {
    private View view, viewGift;
    private RecyclerView recyclerView;
    private ImageView imgGift, imgClose, imgShare, imgPrivateChat, imgPublicChat;
    private ViewPager viewPagerGift;
    private View view01;
    private GridView gridView;
    private List<GiftBean> giftList;
    private GiftAdapter giftAdapter;
    private Map<String, Integer> map;
    private RelativeLayout rlSet;
    private LinearLayout rlDian;
    private Dialog dialog;
    private List<ImageView> listimg;
    private int a;
    int aa = 0;
    private GiftFrameLayout giftFrameLayout1;
    private GiftFrameLayout giftFrameLayout2;
    private GiftControl giftControl;
    private Button btnFaSong;
    private RelativeLayout rlChong;
    private GiftMsgAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bofang_fragment_caozuo_two, null);
        initView();
        return view;
    }

    public void initView() {
        imgGift = (ImageView) view.findViewById(R.id.img_bofang_gift);
        imgClose = (ImageView) view.findViewById(R.id.img_bofang_close);
        imgShare = (ImageView) view.findViewById(R.id.img_bofang_share);
        imgPrivateChat = (ImageView) view.findViewById(R.id.img_bofang_privatechat);
        imgPublicChat = (ImageView) view.findViewById(R.id.img_bofang_publicchat);
        rlSet = (RelativeLayout) view.findViewById(R.id.rl_bofang_set);
        imgGift.setOnClickListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_bofang_user_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(linearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(R.mipmap.qaz);
        }

        BoFangUserListAdapter boFangUserListAdapter = new BoFangUserListAdapter(getActivity(), list);
        recyclerView.addItemDecoration(new SpacesItemDecoration(20));
        recyclerView.setAdapter(boFangUserListAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_bofang_gift:
                showGiftDiaLog();
                break;
            case R.id.img_bofang_close:
                break;
            case R.id.img_bofang_share:
                break;
            case R.id.img_bofang_privatechat:
                break;
            case R.id.img_bofang_publicchat:
                break;
            case R.id.btn_gift_fasong:
                giftFrameLayout1 = (GiftFrameLayout) view.findViewById(R.id.gift_layout1);
                giftFrameLayout2 = (GiftFrameLayout) view.findViewById(R.id.gift_layout2);
                giftControl = new GiftControl(getActivity());
                giftControl.setGiftLayout(giftFrameLayout1, giftFrameLayout2);
                Toast.makeText(getActivity(),"dian",Toast.LENGTH_LONG).show();
                adapter=new GiftMsgAdapter(getActivity());
                giftControl.loadGift(new GiftModel("礼物", "礼物名字", 2, "", "1234", "吕靓茜", "", System.currentTimeMillis()));
                adapter.add("礼物");
                break;
        }
    }

    /**
     * 显示礼物Dialog
     */
    public void showGiftDiaLog() {
        dialog = new Dialog(getActivity(), R.style.DialogStyle01);
        viewGift = View.inflate(getActivity(), R.layout.bofang_gift_dialog, null);
        rlDian = (LinearLayout) viewGift.findViewById(R.id.rl_dian);
        rlChong = (RelativeLayout) viewGift.findViewById(R.id.dia_chong);
        btnFaSong= (Button) viewGift.findViewById(R.id.btn_gift_fasong);
        btnFaSong.setOnClickListener(this);
        initGiftDiaLog();
        //将布局设置给Dialog
        dialog.setContentView(viewGift);
        //设置点击之外可消失
        dialog.setCanceledOnTouchOutside(true);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        int as = setListViewHeightBasedOnChildren(gridView);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = (int) (rlSet.getHeight() * 1.3) + as * 2 - 3;

        dialogWindow.setAttributes(lp);
        xiaoyuandian();
        dialog.show();
        if (dialog.isShowing()) {
            TranslateAnimation animation = new TranslateAnimation(0, 0, 0, 200);
            animation.setDuration(600);//设置动画持续时间
            animation.setRepeatCount(0);//设置重复次数
            animation.setRepeatMode(Animation.REVERSE);//设置反方向执行
            animation.setFillAfter(true);
            rlSet.startAnimation(animation);
        }

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                aa = 0;
                TranslateAnimation animation = new TranslateAnimation(0, 0, 200, 0);
                animation.setDuration(600);//设置动画持续时间
                animation.setRepeatCount(0);//设置重复次数
                animation.setRepeatMode(Animation.REVERSE);//设置反方向执行
                animation.setFillAfter(true);

                rlSet.startAnimation(animation);
            }
        });
    }

    /**
     * 设置小圆点方法
     */
    public void xiaoyuandian() {
        listimg = new ArrayList();
        //小圆点与图片的数量一致
        for (int i = 0; i < a; i++) {
            // 创建imageView
            ImageView imageView = new ImageView(getActivity());
            listimg.add(imageView);
            //小圆点的资源文件
            imageView.setBackgroundResource(R.drawable.dian02);
            // LayoutParams对象的类型,取决于该控件的父控件类型
            LinearLayout.LayoutParams layoutparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            //设置右边距
            layoutparams.rightMargin = 15;
            //设置imageView属性
            imageView.setLayoutParams(layoutparams);
            if (i == 0) {
                imageView.setEnabled(true);
            } else {
                imageView.setEnabled(false);
            }
            rlDian.addView(imageView);/////lin_xiaodian  小圆点的布局LinearLayout
        }
        listimg.get(0).setBackgroundResource(R.drawable.dian);

    }

    public void initGiftDiaLog() {
        map = new HashMap<>();
        viewPagerGift = (ViewPager) viewGift.findViewById(R.id.vp_gift);
        initViewOneDate();
        setDian();
    }

    /**
     * 初始化数据
     */
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
        //初始化礼物√
        map.put("number", 0);
        giftList.get(0).setDui(true);
        //viewpager页数
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
                    giftList.get(position + aa * 8).setDui(true);
                    giftAdapter.setList(giftList);
                    for (int a = 0; a < adapters.size(); a++) {
                        adapters.get(a).notifyDataSetChanged();
                    }
                }
            });
        }
    }

    /**
     * viewpager滑动监听
     */
    public void setDian() {
        viewPagerGift.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                aa = position;
                for (int i = 0; i < a; i++) {
                    listimg.get(i).setBackgroundResource(R.drawable.dian02);
                }
                listimg.get(position).setBackgroundResource(R.drawable.dian);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
    /**
     * 动态获取GridView高度
     */

    public static int setListViewHeightBasedOnChildren(GridView gridView) {
        // gridView
        ListAdapter listAdapter = gridView.getAdapter();
        // 固定列宽，有多少列
        int col = 2;// gridView.getNumColumns();
        int totalHeight = 0;
        // i每次加4，相当于listAdapter.getCount()小于等于4时 循环一次，计算一次item的高度，
        // listAdapter.getCount()小于等于8时计算两次高度相加
        for (int i = 0; i < listAdapter.getCount(); i += col) {
            // 获取listview的每一个item
            View listItem = listAdapter.getView(i, null, gridView);
            listItem.measure(0, 0);
            // 获取item的高度和
            totalHeight += listItem.getMeasuredHeight();
        }
        return totalHeight;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dialog.dismiss();
    }
}
