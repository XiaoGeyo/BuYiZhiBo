package mzy.bc.com.buyizhibo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mzy.bc.com.buyizhibo.R;

/**
 * Created by Administrator on 2017/4/17.
 */

public class BoFangCaoZuoOne extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bofang_fragment_caozuo_one, null);
        return view;
    }
}
