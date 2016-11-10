package vaycent.vaycentproject.DemoPackage.EventPackage;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.Button;

import vaycent.magicLog.mlog;

/**
 * Created by Vaycent on 2016/11/4.
 */

public class ChildButton extends Button {

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mlog.e("This is ChildButton dispatchTouchEvent");
        boolean result = super.dispatchTouchEvent(ev);
        mlog.i("This is ChildButton dispatchTouchEvent result:"+result);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mlog.e("This is ChildButton onTouchEvent");
        boolean result = super.onTouchEvent(event);
        mlog.i("This is ChildButton onTouchEvent result:"+result);
        return result;
    }



    public ChildButton(Context context) {
        super(context);
    }
}
