package vaycent.vaycentproject.DemoPackage.OptimizationPackage;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

import vaycent.vaycentproject.R;

/**
 * Created by Vaycent on 2016/12/5.
 */

public class AnrDemo extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {
                testANR();
            }
        }).start();

        SystemClock.sleep(10);
        initView();
    }

    private synchronized void testANR(){
        SystemClock.sleep(30 * 1000);
    }

    private synchronized void initView(){

    }

    @Override
    public void onStart(){
        super.onStart();

    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onStop(){
        super.onStop();
    }



}
