package vaycent.vaycentproject.DemoPackage.FragmentPackage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import vaycent.vaycentproject.R;

/**
 * Created by Vaycent on 2016/10/19.
 */

public class FragmentDemo extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_demo);
    }
}



