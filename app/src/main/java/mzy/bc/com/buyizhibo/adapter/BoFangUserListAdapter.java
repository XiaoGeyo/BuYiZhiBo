package mzy.bc.com.buyizhibo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import mzy.bc.com.buyizhibo.R;

/**
 * Created by Administrator on 2017/4/17.
 */

public class BoFangUserListAdapter extends RecyclerView.Adapter {
    List<Object> list;
    Context context;

    public BoFangUserListAdapter(Context context, List<Object> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        UserListViewHolder userListViewHolder = new UserListViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_bofang_userlist, parent,
                false));
        return userListViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((UserListViewHolder) holder).imageView.setImageResource((Integer) list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class UserListViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public UserListViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img_user_list);
        }
    }


}
