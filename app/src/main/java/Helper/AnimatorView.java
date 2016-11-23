package Helper;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

import vaycent.magicLog.mlog;



/**
 * Created by Vaycent on 2016/11/16.
 */

public class AnimatorView extends View {

    private int mLastX=0;
    private int mLastY=0;

    public AnimatorView(Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        int x = (int)event.getRawX();
        int y = (int)event.getRawY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                mlog.d("move,deltaX:"+deltaX+",deltaY:"+deltaY);
                int translationX = (int) ViewHelper.getTranslationX(this)+deltaX;
                int translationY = (int)ViewHelper.getTranslationY(this)+deltaY;
                ViewHelper.setTranslationX(this,translationX);
                ViewHelper.setTranslationY(this,translationY);

                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }

        mLastX = x;
        mLastY = y;
        return true;
    }
}
