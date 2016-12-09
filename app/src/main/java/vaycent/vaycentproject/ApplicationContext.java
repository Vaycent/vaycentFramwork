package vaycent.vaycentproject;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

import BaseClass.BaseValue;
import HelpFulClass.CrashHandler;
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

        InitMagicLog();

        CrashHandler crashHandler = CrashHandler.getsInstance();
        crashHandler.init(this);

        InitLeakCanary();

    }

    @Override
    protected void attachBaseContext(Context base){
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private void InitVolleySharp() {
        volleySharp = new VolleySharp(this);
    }

    private void InitMagicLog(){
        mlog.setLogFilePath(BaseValue.LOG_FILE_PATH);
    }

    private void InitLeakCanary(){
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
    }
}
