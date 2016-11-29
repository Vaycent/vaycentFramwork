package vaycent.vaycentproject.DemoPackage.MessagePackage;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

/**
 * Created by Vaycent on 2016/11/29.
 */

public class UploadImgService extends IntentService {

    public static final String ACTION_UPLOAD_IMG = "com.zhy.blogcodes.intentservice.action.UPLOAD_IMAGE";
    public static final String EXTRA_IMG_PATH = "com.zhy.blogcodes.intentservice.extra.IMG_PATH";




    public UploadImgService()
    {
        super("UploadImgService");
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        if (intent != null)
        {
            final String action = intent.getAction();
            System.out.println("action is :"+action);
            if (ACTION_UPLOAD_IMG.equals(action))
            {
                final String path = intent.getStringExtra(EXTRA_IMG_PATH);
                handleUploadImg(path);
            }
        }
    }

    private void handleUploadImg(String path)
    {
        try
        {
            //模拟上传耗时
            Thread.sleep(3000);

//            Intent intent = new Intent(AndroidMessageDemo.UPLOAD_RESULT);
//            intent.putExtra(EXTRA_IMG_PATH, path);
//            sendBroadcast(intent);
//            mlog.w("This intentservice is finished");

            LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
            Intent intent = new Intent(AndroidMessageDemo.UPLOAD_RESULT);
            intent.putExtra(EXTRA_IMG_PATH, path);
            lbm.sendBroadcast(intent);


        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }


    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.e("TAG","onCreate");
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.e("TAG","onDestroy");
    }
}
