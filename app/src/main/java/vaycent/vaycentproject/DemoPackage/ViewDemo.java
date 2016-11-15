package vaycent.vaycentproject.DemoPackage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import vaycent.magicLog.mlog;
import vaycent.vaycentproject.R;

/**
 * Created by Vaycent on 2016/11/15.
 */

public class ViewDemo extends AppCompatActivity implements View.OnClickListener{
    private ImageView imgView;
    private Button printScaleBtn,scrollToBtn;
    private float density;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_demo_layout);

        InitLayout();

        GetScreenSize();

    }

    private void InitLayout(){
        imgView = (ImageView)findViewById(R.id.test_img);

        printScaleBtn = (Button)findViewById(R.id.print_scale_btn);
        scrollToBtn = (Button)findViewById(R.id.scroll_to_btn);
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
        int left = imgView.getLeft();
        int right = imgView.getRight();
        int top = imgView.getTop();
        int bottom = imgView.getBottom();
        mlog.d("DP---left:"+left/density+",right:"+right/density+",top:"+top/density+",bottom:"+bottom/density);
        mlog.d("left:"+left+",right:"+right+",top:"+top+",bottom:"+bottom);


        float X = imgView.getX();
        float Y = imgView.getY();
        float translationX = imgView.getTranslationX();
        float translationY = imgView.getTranslationY();
        mlog.d("X:"+X+",Y:"+Y+",translationX:"+translationX+",translationY:"+translationY);
    }

    private void ScrollTo(){
//        imgView.scrollTo(500,500);
        imgView.scrollBy(100,100);
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
