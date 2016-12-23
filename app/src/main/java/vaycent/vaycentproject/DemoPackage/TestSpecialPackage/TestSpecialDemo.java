package vaycent.vaycentproject.DemoPackage.TestSpecialPackage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vaycent.vaycentproject.R;

///**
// * Created by Vaycent on 2016/12/22.
// */

public class TestSpecialDemo extends AppCompatActivity {


    @BindView(R.id.testBtn)
    Button mTestBtn;
    @BindView(R.id.backgroundPage)
    LinearLayout mBackgroundPage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_special_demo);
        ButterKnife.bind(this);


    }


    @OnClick(R.id.testBtn)
    public void onClick() {
    }
}
