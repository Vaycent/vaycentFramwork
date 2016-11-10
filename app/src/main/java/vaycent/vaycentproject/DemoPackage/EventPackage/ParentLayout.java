package vaycent.vaycentproject.DemoPackage.EventPackage;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import vaycent.magicLog.mlog;

/**
 * Created by Vaycent on 2016/11/4.
 */

public class ParentLayout extends RelativeLayout {

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mlog.e("This is ParentLayout dispatchTouchEvent");
        boolean result = super.dispatchTouchEvent(ev);
        mlog.i("This is ParentLayout dispatchTouchEvent result:"+result);
        return result;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        mlog.e("This is ParentLayout onInterceptTouchEvent");
        boolean result = super.onInterceptTouchEvent(ev);
        mlog.i("This is ParentLayout onInterceptTouchEvent result:"+result);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mlog.e("This is ParentLayout onTouchEvent");
        boolean result = super.onTouchEvent(event);
        mlog.i("This is ParentLayout onTouchEvent result:"+result);
        return result;
    }

    public ParentLayout(Context context) {
        super(context);
    }
}
