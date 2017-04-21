package mzy.bc.com.buyizhibo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import mzy.bc.com.buyizhibo.R;
import mzy.bc.com.buyizhibo.activity.BoFangActivity;
import mzy.bc.com.buyizhibo.adapter.GameAdapter;
import mzy.bc.com.buyizhibo.adapter.ReMenAdapter;
import mzy.bc.com.buyizhibo.bean.ReMenBean;
import mzy.bc.com.buyizhibo.utils.Api;
import mzy.bc.com.buyizhibo.utils.GsonUtils;
import mzy.bc.com.buyizhibo.utils.MyOkhttpUtils;
import mzy.bc.com.buyizhibo.utils.ParamsUtils;
import mzy.bc.com.buyizhibo.widget.interfaces.OnLoadMoreListener;
import mzy.bc.com.buyizhibo.widget.interfaces.OnMultiTypeItemClickListeners;
import mzy.bc.com.buyizhibo.widget.viewholder.CommonViewHolder;
import okhttp3.Call;

import static android.R.attr.offset;

/**
 * Created by Administrator on 2017/4/11.
 */

public class HomeReMenFragment extends Fragment  {

    private int pos=0;
    private RelativeLayout relativeLayout;
    private View view;
    private RecyclerView mRecyclerView;
    private ReMenAdapter reMenAdapter;
    private MaterialRefreshLayout materialRefreshLayout;
    private LinearLayoutManager linearLayoutManager;
    private int a=1;
    private List<ReMenBean.ResultBean.ListBean> list;





    RecyclerView gameRecy;
    SwipeRefreshLayout refreshLayout;
    ImageView xiaoyuan;
    private GameAdapter mAdapter;
    public boolean isFailed = true;

    private int page = 1;

    public static HomeReMenFragment getFragment() {
        HomeReMenFragment followFragment = new HomeReMenFragment();
        return followFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment_remen, null);
        /**materialRefreshLayout = (MaterialRefreshLayout) view.findViewById(R.id.refresh);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_remen);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        list=new ArrayList<>();
        Map map = new HashMap();
        map.put("type", "1");
        map.put("page", "1");
        new MyOkhttpUtils("http://121.42.26.175:14444/live/find.json", map, HomeReMenFragment.this,1);

        setXiaLa();
         */
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    /*@Override
    public String getResults(String result,int s) {
        materialRefreshLayout.finishRefresh();
        materialRefreshLayout.finishRefreshLoadMore();
        GsonUtils gsonUtils = new GsonUtils();
        ReMenBean reMenBean = new ReMenBean();
        List<ReMenBean.ResultBean.ListBean> listBeen=new ArrayList<>();
        reMenBean = (ReMenBean) gsonUtils.getValues(result, ReMenBean.class);
        for(int i=0;i<reMenBean.getResult().getList().size();i++){
            list.add(reMenBean.getResult().getList().get(i));
            listBeen.add(reMenBean.getResult().getList().get(i));
        }
        if(s==1){
            a=0;
            Log.e("sss","listbean");
            list.clear();
            for(int i=0;i<reMenBean.getResult().getList().size();i++){
                list.add(reMenBean.getResult().getList().get(i));
                listBeen.add(reMenBean.getResult().getList().get(i));
            }


            pos=list.size();
            if(reMenBean.getResult().getList().size()>0){
                mRecyclerView.setAdapter(reMenAdapter = new ReMenAdapter(getActivity(), listBeen));
            }
        }else if(s==2){
            Log.e("sss","list");
            if(pos==0) {
                mRecyclerView.scrollToPosition(pos);
            }else{
                mRecyclerView.scrollToPosition(pos-1);
            }
            pos=list.size();
            if(reMenBean.getResult().getList().size()>0){
                mRecyclerView.setAdapter(reMenAdapter = new ReMenAdapter(getActivity(), list));
            }
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




    /*public void setXiaLa() {


        materialRefreshLayout.setLoadMore(true);
        materialRefreshLayout.autoRefresh();
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
                                                             @Override
                                                             public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                                                                 //下拉刷新...
                                                                 Map map = new HashMap();
                                                                 map.put("type", "1");
                                                                 map.put("page", "1");
                                                                 new MyOkhttpUtils("http://121.42.26.175:14444/live/find.json", map, HomeReMenFragment.this,1);
                                                             }

                                                             @Override
                                                             public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                                                                 Map map = new HashMap();
                                                                 a++;
                                                                 map.put("type", "1");
                                                                 map.put("page", a+"");

                                                                 new MyOkhttpUtils("http://121.42.26.175:14444/live/find.json", map, HomeReMenFragment.this,2);

                                                                 //上拉刷新...
                                                             }
                                                         });

    }
*/

    private void initView() {
        refreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.refresh_layout);
        gameRecy=(RecyclerView) view.findViewById(R.id.game_recy);
        mAdapter = new GameAdapter(getActivity(), null, true);
        refreshLayout.setColorSchemeColors(getActivity().getResources().getColor(R.color.colorAccent));
        //下拉刷新
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                loadData();
            }
        });
        //加载更多
        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                page++;
                loadData();
            }
        });
        loadData();//一进来请求数据
//        refreshLayout.setRefreshing(true);//一进来就刷新方式请求数据  // TODO: 17/4/14 这个有错误
        mAdapter.setAddHaed(true);
        gameRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
        gameRecy.setAdapter(mAdapter);

        mAdapter.setOnMultiTypeItemClickListener(new OnMultiTypeItemClickListeners<ReMenBean.ResultBean.ListBean>() {
            @Override
            public void onItemClick(CommonViewHolder viewHolder, ReMenBean.ResultBean.ListBean data, int position, int viewType) {
                Intent intent = new Intent(getActivity(), BoFangActivity.class);
                //intent.putExtra("page",1);
                startActivity(intent);
            }
        });
    }

    /**
     * 初始化数据
     */
    private void loadData() {
        Map<String, String> map = ParamsUtils.getParams();
        map.put("type", "1");
        map.put("page", page + "");
        OkHttpUtils.post().url(Api.HOME_URL).params(map).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(getActivity(), "白百合去泰国了，服务器没有人维护", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                ReMenBean live = gson.fromJson(response, ReMenBean.class);
                delData(live);
            }
        });

        // test refresh
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                List<String> data = new ArrayList<>();
//                for (int i = 0; i < 12; i++) {
//                    data.add(i + "");
//                }
//                //刷新数据
//                mAdapter.setInitData(data);
//                mAdapter.setInitData(data);
//                refreshLayout.setRefreshing(false);
//                mAdapter.setLoadingView(R.layout.load_loading);
//            }
//        }, 2000);
    }


    private void delData(ReMenBean live) {
        if (live.getError_code() == 0) {
            if (page == 1) {//第一页
                if (live.getResult().getList() != null && live.getResult().getList().size() > 0) {//不为空
                    mAdapter.setInitData(live.getResult().getList());
                    refreshLayout.setRefreshing(false);
                    mAdapter.setLoadingView(R.layout.load_loading);
                } else {
                    refreshLayout.setRefreshing(false);
                    mAdapter.setLoadingView(R.layout.load_end);
                }

            } else {
                if (live.getResult().getList() != null && live.getResult().getList().size() > 0) {//不为空
                    mAdapter.setLoadMoreData(live.getResult().getList());
                } else {
                    mAdapter.setLoadingView(R.layout.load_end);
                }

            }


        } else {
            Toast.makeText(getActivity(), "白百合出轨了", Toast.LENGTH_SHORT).show();
        }


    }
    //test loadmore
//    private void loadMore() {
//        new Handler().postDelayed(new Runnable() {
//
//
//            @Override
//            public void run() {
//                if (mAdapter.getItemCount() > 30 && isFailed) {
//                    isFailed = false;
//                    //加载失败
//                    mAdapter.setLoadFailedView(R.layout.load_failed);
//                } else if (mAdapter.getItemCount() > 50) {
//                    //加载完成
//                    mAdapter.setLoadEndView(R.layout.load_end);
//                } else {
//
//                    final List<String> data = new ArrayList<>();
//                    for (int i = 0; i < 12; i++) {
//                        data.add("我是加载更多" + i);
//                    }
//                    mAdapter.setLoadMoreData(data);
//                }
//            }
//        }, 3000);
//    }
}
