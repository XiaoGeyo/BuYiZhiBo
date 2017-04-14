package mzy.bc.com.buyizhibo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mzy.bc.com.buyizhibo.R;
import mzy.bc.com.buyizhibo.adapter.ReMenAdapter;
import mzy.bc.com.buyizhibo.bean.ReMenBean;
import mzy.bc.com.buyizhibo.utils.GsonUtils;
import mzy.bc.com.buyizhibo.utils.MyOkhttpUtils;

/**
 * Created by Administrator on 2017/4/11.
 */

public class HomeReMenFragment extends Fragment implements MyOkhttpUtils.GetValues {


    private RelativeLayout relativeLayout;
    private View view;
    private RecyclerView mRecyclerView;
    private ReMenAdapter reMenAdapter;
    private MaterialRefreshLayout materialRefreshLayout;
    private LinearLayoutManager linearLayoutManager;
    private int a=1;
    private List<ReMenBean.ResultBean.ListBean> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment_remen, null);
        materialRefreshLayout = (MaterialRefreshLayout) view.findViewById(R.id.refresh);
        Log.d("HomeReMenFragment", "HomeReMenFragment");
        list=new ArrayList<>();
        Map map = new HashMap();
        map.put("type", "1");
        map.put("page", "1");
        new MyOkhttpUtils("http://121.42.26.175:14444/live/find.json", map, HomeReMenFragment.this);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_remen);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        setXiaLa();


        return view;
    }

    @Override
    public String getResults(String result) {
        materialRefreshLayout.finishRefresh();
        materialRefreshLayout.finishRefreshLoadMore();
        GsonUtils gsonUtils = new GsonUtils();
        ReMenBean reMenBean = new ReMenBean();
        reMenBean = (ReMenBean) gsonUtils.getValues(result, ReMenBean.class);
        for(int i=0;i<reMenBean.getResult().getList().size();i++){
            list.add(reMenBean.getResult().getList().get(i));
        }
        if(reMenBean.getResult().getList().size()>0){
            mRecyclerView.setAdapter(reMenAdapter = new ReMenAdapter(getActivity(), list));
        }
        reMenAdapter.setOnItemClickListener(new ReMenAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data) {
                int childAdapterPosition = mRecyclerView.getChildAdapterPosition(view);
                Toast.makeText(getActivity(), childAdapterPosition+"", Toast.LENGTH_LONG).show();
            }
        });
        return null;
    }




    public void setXiaLa() {


        materialRefreshLayout.setLoadMore(true);
        materialRefreshLayout.autoRefresh();
//        materialRefreshLayout.autoRefreshLoadMore();
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
                                                             @Override
                                                             public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                                                                 //下拉刷新...
                                                                 Map map = new HashMap();
                                                                 map.put("type", "1");
                                                                 map.put("page", "1");
                                                                 new MyOkhttpUtils("http://121.42.26.175:14444/live/find.json", map, HomeReMenFragment.this);
                                                             }

                                                             @Override
                                                             public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                                                                 Map map = new HashMap();
                                                                 a++;
                                                                 map.put("type", "1");
                                                                 map.put("page", a+"");

                                                                 new MyOkhttpUtils("http://121.42.26.175:14444/live/find.json", map, HomeReMenFragment.this);
                                                                 //上拉刷新...
                                                             }
                                                         });

    }
//加载更多事件

//    public void jiaZai() {
//        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                int totalcount = linearLayoutManager.getItemCount();
//                int lastPosition = linearLayoutManager.findLastVisibleItemPosition();
//                if (totalcount == lastPosition + 1) {
//                    Toast.makeText(getActivity(), "加载更多", Toast.LENGTH_LONG).show();
//                    Log.e("asd","加载");
//                }
//            }
//        });
//        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    int lastVisiblePosition = linearLayoutManager.findLastVisibleItemPosition();
//                    if (lastVisiblePosition >= linearLayoutManager.getItemCount() - 1) {
//                        a++;
//                        Map map = new HashMap();
//                        map.put("type", "1");
//                        map.put("page", a+"");
//                        new MyOkhttpUtils("http://121.42.26.175:14444/live/find.json", map, HomeReMenFragment.this);
////                        System.out.println("====自动加载");
//                    }
//                }
//            }
//        });
//    }
}
