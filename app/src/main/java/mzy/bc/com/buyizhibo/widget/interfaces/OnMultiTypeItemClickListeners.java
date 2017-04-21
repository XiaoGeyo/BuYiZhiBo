package mzy.bc.com.buyizhibo.widget.interfaces;


import mzy.bc.com.buyizhibo.widget.viewholder.CommonViewHolder;

/**
 * @author xiaoyuan
 */
public interface OnMultiTypeItemClickListeners<T> {
    void onItemClick(CommonViewHolder viewHolder, T data, int position, int viewType);
}
