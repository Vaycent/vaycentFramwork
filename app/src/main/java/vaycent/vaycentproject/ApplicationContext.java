package vaycent.vaycentproject;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
import com.taobao.hotfix.HotFixManager;
import com.taobao.hotfix.PatchLoadStatusListener;
import com.taobao.hotfix.util.PatchStatusCode;
import com.zxy.recovery.core.Recovery;

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

//        InitRecovery();
//        initHotfix();
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

    private void InitRecovery(){
//        Recovery.getInstance()
//                .debug(true)
//                .recoverInBackground(false)
//                .recoverStack(true)
//                .mainPage(MainActivity.class)
//                .callback(new MyCrashCallback())
//                .init(this);

        Recovery.getInstance()
                .debug(true)
                .recoverInBackground(false)
                .silent(false, Recovery.SilentMode.RECOVER_ACTIVITY_STACK)
                .init(this);
    }


    private void initHotfix() {
        String appId = "88210-1";
        String appVersion;
        try {
            appVersion = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
        } catch (Exception e) {
            appVersion = "1.0.0";
        }
        HotFixManager.getInstance().setContext(this)
                .setAppVersion(appVersion)
                .setAppId(appId)
                .setAesKey("1234567891234567")
                .setSupportHotpatch(true)
                .setEnableDebug(true)
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onload(final int mode, final int code, final String info, final int handlePatchVersion) {
                        // 补丁加载回调通知
                        if (code == PatchStatusCode.CODE_SUCCESS_LOAD) {
                            // TODO: 10/24/16 表明补丁加载成功
                            Log.w("BaiChuanHotFix","Success Load");
                        } else if (code == PatchStatusCode.CODE_ERROR_NEEDRESTART) {
                            // TODO: 10/24/16 表明新补丁生效需要重启. 业务方可自行实现逻辑, 提示用户或者强制重启, 建议: 用户可以监听进入后台事件, 然后应用自杀
                            Log.w("BaiChuanHotFix","Need Restart");
                        } else if (code == PatchStatusCode.CODE_ERROR_INNERENGINEFAIL) {
                            // 内部引擎加载异常, 推荐此时清空本地补丁, 但是不清空本地版本号, 防止失败补丁重复加载
                            Log.w("BaiChuanHotFix","Code Error");
                            HotFixManager.getInstance().cleanPatches(false);
                        } else {
                            Log.w("BaiChuanHotFix","Other Error");
                            // TODO: 10/25/16 其它错误信息, 查看PatchStatusCode类说明
                        }
                    }
                }).initialize();

//        if (Build.VERSION.SDK_INT >= 23) {
//            requestExternalStoragePermission();
//        }
    }
}
