package mzy.bc.com.buyizhibo.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Administrator on 2017/3/20.
 */

public class GsonUtils {
    public Object getValues(String string, Class<?> myClass) {
        Gson gson = new Gson();
        return gson.fromJson(string, myClass);
    }

    public static <T> List<T> getListFromJson(String json, TypeToken<List<T>> tt) {
        Gson gson = new Gson();
        return gson.fromJson(json, tt.getType());
    }
}
