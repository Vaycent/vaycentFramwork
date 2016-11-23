package vaycent.vaycentproject;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import BaseClass.BaseValue;
import vaycent.magicLog.mlog;
import vaycent.volleysharp.VolleySharp;

/**
 * Created by Vaycent on 16/9/7.
 */
public class ApplicationContext extends Application {

    public VolleySharp volleySharp;


    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);

        InitVolleySharp();

        InitImageLoader();

        InitMagicLog();

    }

    private void InitVolleySharp() {
        volleySharp = new VolleySharp(this);
    }

    private void InitImageLoader(){
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(this);
//        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
//                .writeDebugLogs()
//                .build();
        ImageLoader.getInstance().init(configuration);
    }

    private void InitMagicLog(){
        mlog.setLogFilePath(BaseValue.LOG_FILE_PATH);
    }
}
