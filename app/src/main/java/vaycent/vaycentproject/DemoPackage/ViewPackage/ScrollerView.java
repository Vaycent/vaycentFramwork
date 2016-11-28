package vaycent.vaycentproject.DemoPackage.ViewPackage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by Vaycent on 2016/11/28.
 */

public class ScrollerView extends View {

    private Scroller mScroller;

    public ScrollerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
    }

//    public void smoothScrollTo(int destX, int destY,int duration){
//        int scrollX = getScrollX();
//        int scrollY = getScrollY();
//
//        int deltaX = destX - scrollX;
//        int deltaY = destY - scrollY;
//
//        mScroller.startScroll(scrollX,scrollY,deltaX,deltaY,duration);
//        invalidate();
//    }

    //调用此方法滚动到目标位置
    public void smoothScrollTo(int fx, int fy) {
        int dx = fx - mScroller.getFinalX();
        int dy = fy - mScroller.getFinalY();
        smoothScrollBy(dx, dy);
        System.out.println("test here123");
    }

    //调用此方法设置滚动的相对偏移
    public void smoothScrollBy(int dx, int dy) {

        //设置mScroller的滚动偏移量
        mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), dx, dy);
        invalidate();//这里必须调用invalidate()才能保证computeScroll()会被调用，否则不一定会刷新界面，看不到滚动效果
    }

    @Override
    public void computeScroll(){
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }
}
