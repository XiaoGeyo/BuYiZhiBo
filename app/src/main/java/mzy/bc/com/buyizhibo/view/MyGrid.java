package mzy.bc.com.buyizhibo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.GridView;



public class MyGrid extends GridView {
	public MyGrid(Context context) {
        super(context);
    }
    public MyGrid(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MyGrid(Context context, AttributeSet attrs,
        int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
        MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
