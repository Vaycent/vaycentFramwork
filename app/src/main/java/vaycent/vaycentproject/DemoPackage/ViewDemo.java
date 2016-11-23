package vaycent.vaycentproject.DemoPackage;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import vaycent.magicLog.mlog;
import vaycent.vaycentproject.R;

/**
 * Created by Vaycent on 2016/11/15.
 */

public class ViewDemo extends AppCompatActivity implements View.OnClickListener{
    private Button printScaleBtn,scrollToBtn;
    private float density;
    private RelativeLayout imgLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_demo_layout);

        InitLayout();

        GetScreenSize();

    }

    private void InitLayout(){
        printScaleBtn = (Button)findViewById(R.id.print_scale_btn);
        scrollToBtn = (Button)findViewById(R.id.scroll_to_btn);
        imgLayout = (RelativeLayout)findViewById(R.id.img_layout);
        printScaleBtn.setOnClickListener(this);
        scrollToBtn.setOnClickListener(this);
    }

    private void GetScreenSize(){
        DisplayMetrics dm = this.getApplication().getResources().getDisplayMetrics();
        density = dm.density;
        int widthPixels = dm.widthPixels;
        int heightPixels = dm.heightPixels;
        mlog.d("density:"+density+",widthPixels:"+widthPixels+",heightPixels:"+heightPixels);
    }

    private void PrintScale(){
        int left = imgLayout.getLeft();
        int right = imgLayout.getRight();
        int top = imgLayout.getTop();
        int bottom = imgLayout.getBottom();
        mlog.d("DP---left:"+left/density+",right:"+right/density+",top:"+top/density+",bottom:"+bottom/density);
        mlog.d("left:"+left+",right:"+right+",top:"+top+",bottom:"+bottom);


        float X = imgLayout.getX();
        float Y = imgLayout.getY();
        float translationX = imgLayout.getTranslationX();
        float translationY = imgLayout.getTranslationY();
        mlog.d("X:"+X+",Y:"+Y+",translationX:"+translationX+",translationY:"+translationY);
    }


    private void smoothScrollTo(int destX, int destY){
        Scroller mScroller = new Scroller(this);

        int scrollX = imgLayout.getScrollX();
        int delta = destX - scrollX;
        mScroller.startScroll(scrollX,0,delta,0,2000);
    }

    private void ScrollTo(){
        smoothScrollTo(1000,1000);

//        imgLayout.scrollTo(20,100);
//        imgLayout.scrollBy(-20,-100);
        ObjectAnimator.ofFloat(imgLayout,"translationX",0,1000,500,1000,0).setDuration(4000).start();

//        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams)imgLayout.getLayoutParams();
//        params.leftMargin+=1000;
//        imgLayout.setLayoutParams(params);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.print_scale_btn:
                PrintScale();
                break;
            case R.id.scroll_to_btn:
                ScrollTo();
                break;
            default:
                break;
        }
    }
}
