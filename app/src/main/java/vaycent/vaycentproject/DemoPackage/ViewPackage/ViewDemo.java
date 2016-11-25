package vaycent.vaycentproject.DemoPackage.ViewPackage;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import vaycent.magicLog.mlog;
import vaycent.vaycentproject.R;

/**
 * Created by Vaycent on 2016/11/15.
 */

public class ViewDemo extends AppCompatActivity implements View.OnClickListener{

    private Button printScaleBtn,srollToBtn,scrollByBtn;
    private Button toObjectAnimationFragment,toMarginFragment;
    private RelativeLayout fragmentLayout;
    private float density;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_demo_layout);

        InitLayout();

        GetScreenSize();
    }

    private void InitLayout(){
        srollToBtn=(Button)findViewById(R.id.scroll_to_btn);
        scrollByBtn=(Button)findViewById(R.id.scroll_by_btn);
        printScaleBtn=(Button)findViewById(R.id.print_scale_btn);
        srollToBtn.setOnClickListener(this);
        scrollByBtn.setOnClickListener(this);
        printScaleBtn.setOnClickListener(this);

        toObjectAnimationFragment = (Button)findViewById(R.id.to_objectanimation_fragment);
        toMarginFragment = (Button)findViewById(R.id.to_margin_fragment);
        toObjectAnimationFragment.setOnClickListener(this);
        toMarginFragment.setOnClickListener(this);

        fragmentLayout = (RelativeLayout)findViewById(R.id.fragment_layout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.print_scale_btn:
                PrintScale();
                break;
            case R.id.scroll_to_btn:
                ScrollToFunction();
                break;
            case R.id.scroll_by_btn:
                ScrollByFunction();
                break;
            case R.id.to_objectanimation_fragment:
                ObjectAnimationTest();
                break;
            case R.id.to_margin_fragment:
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams)fragmentLayout.getLayoutParams();
                params.leftMargin+=300;
                fragmentLayout.setLayoutParams(params);
                break;
            default:
                break;
        }
    }

    public void ScrollToFunction(){
        fragmentLayout.scrollTo(20,100);
    }

    public void ScrollByFunction(){
        fragmentLayout.scrollBy(20,100);
    }

    private void GetScreenSize(){
        DisplayMetrics dm = this.getApplication().getResources().getDisplayMetrics();
        density = dm.density;
        int widthPixels = dm.widthPixels;
        int heightPixels = dm.heightPixels;
        mlog.d("density:"+density+",widthPixels:"+widthPixels+",heightPixels:"+heightPixels);
    }

    public void PrintScale(){
        int left = fragmentLayout.getLeft();
        int right = fragmentLayout.getRight();
        int top = fragmentLayout.getTop();
        int bottom = fragmentLayout.getBottom();
        mlog.d("DP---left:"+left/density+",right:"+right/density+",top:"+top/density+",bottom:"+bottom/density);
        mlog.d("left:"+left+",right:"+right+",top:"+top+",bottom:"+bottom);


        float X = fragmentLayout.getX();
        float Y = fragmentLayout.getY();
        float translationX = fragmentLayout.getTranslationX();
        float translationY = fragmentLayout.getTranslationY();
        mlog.d("X:"+X+",Y:"+Y+",translationX:"+translationX+",translationY:"+translationY);
    }

    private void smoothScrollTo(int destX, int destY){
        Scroller mScroller = new Scroller(this);

        int scrollX = fragmentLayout.getScrollX();
        int delta = destX - scrollX;
        mScroller.startScroll(scrollX,0,delta,0,2000);
    }

    private void ObjectAnimationTest(){
        smoothScrollTo(1000,1000);
        ObjectAnimator.ofFloat(fragmentLayout,"translationX",0,1000,500,1000,0).setDuration(4000).start();
    }
}
