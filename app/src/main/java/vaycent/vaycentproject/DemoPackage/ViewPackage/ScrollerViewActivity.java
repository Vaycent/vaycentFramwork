package vaycent.vaycentproject.DemoPackage.ViewPackage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vaycent.vaycentproject.R;

/**
 * Created by Vaycent on 2016/11/28.
 */


public class ScrollerViewActivity extends AppCompatActivity implements View.OnClickListener{

    private ScrollerView mScrollerView;
    private Button ScrollerStartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroller_view_layout);

        mScrollerView = (ScrollerView)findViewById(R.id.my_scroll_view);
        ScrollerStartBtn = (Button)findViewById(R.id.scroller_start);
        ScrollerStartBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.scroller_start:
                mScrollerView.smoothScrollTo(20,100);

                break;
            default:
                break;
        }
    }
}
