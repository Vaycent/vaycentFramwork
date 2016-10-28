package vaycent.vaycentproject.DemoPackage.TestPackage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import vaycent.magicLog.mlog;
import vaycent.vaycentproject.R;

/**
 * Created by Vaycent on 2016/10/27.
 */

public class TestActivity3 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mlog.e("onCreate");
        setContentView(R.layout.activity_main);


    }
}
