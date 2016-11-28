package vaycent.vaycentproject.DemoPackage.MessagePackage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import vaycent.magicLog.mlog;
import vaycent.vaycentproject.R;

/**
 * Created by Vaycent on 2016/11/28.
 */

public class AndroidMessageDemo extends AppCompatActivity {
    private ThreadLocal<Boolean> mBooleanThreadLocal = new ThreadLocal<Boolean>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_message_layout);


        //        ****************** Test Thread *******************
        mBooleanThreadLocal.set(true);
        mlog.d("Thread#main,mBooleanThreadLocal="+mBooleanThreadLocal.get());
        new Thread("Thread#1"){
            @Override
            public void run(){
                mBooleanThreadLocal.set(false);
                mlog.d("Thread#1,mBooleanThreadLocal="+mBooleanThreadLocal.get());
            }
        }.start();
        new Thread("Thread#2"){
            @Override
            public void run(){
                mlog.d("Thread#2,mBooleanThreadLocal="+mBooleanThreadLocal.get());

            }
        }.start();

    }
}
