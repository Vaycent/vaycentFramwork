package Helper;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Vaycent on 2016/11/4.
 */



public class MainGrid extends GridView {

    public MainGrid(Context context) {
        super(context);
    }

    public MainGrid(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}