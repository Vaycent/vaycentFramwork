package vaycent.vaycentproject.DemoPackage.ViewPackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

/**
 * Created by Vaycent on 2016/11/28.
 */

public class MoveView extends View {
    int mLastX,mLastY;

    public MoveView(Context context) {
        super(context);
    }

    public MoveView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    public void onLayout(boolean changed, int left, int top, int right, int bottom){
        super.onLayout(changed,left,top,right,bottom);
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        int r = getMeasuredWidth() / 2;
        int centerX = getLeft() + r;
        int centerY = getTop() + r;
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawCircle(centerX, centerY, r, paint);
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
                Log.d("MoveView","detalX:"+deltaX+"detalY:"+deltaY);
                int translationX = (int) ViewHelper.getTranslationX(this)+deltaX;
                int translationY = (int) ViewHelper.getTranslationY(this)+deltaY;
                ViewHelper.setTranslationX(this,translationX);
                ViewHelper.setTranslationY(this,translationY);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }

        mLastX = x;
        mLastY =y;
        return true;
    }
}
