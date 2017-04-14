package mzy.bc.com.buyizhibo.utils;

import android.util.Log;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/4/12.
 */

public class MyOkhttpUtils {
    private String url;
    public Map map;
    public GetValues getValues;
    public MyOkhttpUtils(String url, Map map,GetValues getValues) {
        super();
        this.url=url;
        this.map=map;
        this.getValues=getValues;
        httpPosst();
    }
    public interface GetValues {
        String getResults(String result);
    }
    public void httpPosst(){

        OkHttpUtils.post().params(map).url(url).build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        getValues.getResults(response);
                    }
                });
    }
}
