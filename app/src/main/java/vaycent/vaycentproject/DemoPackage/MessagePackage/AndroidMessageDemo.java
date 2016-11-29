package vaycent.vaycentproject.DemoPackage.MessagePackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import vaycent.magicLog.mlog;
import vaycent.vaycentproject.R;

import static vaycent.vaycentproject.DemoPackage.MessagePackage.UploadImgService.ACTION_UPLOAD_IMG;
import static vaycent.vaycentproject.DemoPackage.MessagePackage.UploadImgService.EXTRA_IMG_PATH;

/**
 * Created by Vaycent on 2016/11/28.
 */

public class AndroidMessageDemo extends AppCompatActivity {

    private ThreadLocal<Boolean> mBooleanThreadLocal = new ThreadLocal<Boolean>();

    public static final String UPLOAD_RESULT = "com.zhy.blogcodes.intentservice.UPLOAD_RESULT";

    private LinearLayout mLyTaskContainer;

    private int i = 0;

    private LocalBroadcastManager lbm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_message_layout);

        mLyTaskContainer = (LinearLayout) findViewById(R.id.id_ll_taskcontainer);

        lbm = LocalBroadcastManager.getInstance(this);

        registerReceiver();

        //        ****************** Test Thread *******************
//        mBooleanThreadLocal.set(true);
//        mlog.d("Thread#main,mBooleanThreadLocal="+mBooleanThreadLocal.get());
//        new Thread("Thread#1"){
//            @Override
//            public void run(){
//                mBooleanThreadLocal.set(false);
//                mlog.d("Thread#1,mBooleanThreadLocal="+mBooleanThreadLocal.get());
//            }
//        }.start();
//        new Thread("Thread#2"){
//            @Override
//            public void run(){
//                mlog.d("Thread#2,mBooleanThreadLocal="+mBooleanThreadLocal.get());
//                Looper.prepare();
//                Handler handler = new Handler();
//                Looper.loop();
//
//            }
//        }.start();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        lbm.unregisterReceiver(uploadImgReceiver);
    }


    private BroadcastReceiver uploadImgReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            if (intent.getAction() == UPLOAD_RESULT)
            {
                String path = intent.getStringExtra(EXTRA_IMG_PATH);

                TextView tv = (TextView) mLyTaskContainer.findViewWithTag(path);
                tv.setText(path + " upload success ~~~ ");

            }

        }
    };

    private void registerReceiver()
    {


        IntentFilter filter = new IntentFilter();
        filter.addAction(UPLOAD_RESULT);
        lbm.registerReceiver(uploadImgReceiver, filter);
    }


    public void addTask(View view)
    {
        //模拟路径
        String path = "/sdcard/imgs/" + (++i) + ".png";
        startUploadImg(this, path);

        TextView tv = new TextView(this);
        mLyTaskContainer.addView(tv);
        tv.setText(path + " is uploading ...");
        tv.setTag(path);
    }



    public static void startUploadImg(Context context, String path)
    {
        Intent intent = new Intent(context, UploadImgService.class);
        intent.setAction(ACTION_UPLOAD_IMG);
        intent.putExtra(EXTRA_IMG_PATH, path);
        context.startService(intent);
        mlog.w("startService---UploadImgService");
    }



}
