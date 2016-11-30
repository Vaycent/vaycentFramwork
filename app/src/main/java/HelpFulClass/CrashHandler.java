package HelpFulClass;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Vaycent on 2016/11/30.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static final String TAG = "CrashHandler";
    private static final boolean DEBUG = true;

    private static final String PATH = Environment.getExternalStorageDirectory().getPath() + "/CrashTest/log/";
    private static final String FILE_NAME = "crash";
    private static final String FILE_NAME_SUFFIX = ".trace";

    private static CrashHandler sInstance = new CrashHandler();
    private Thread.UncaughtExceptionHandler mDefaultCrashHandler;
    private Context mContext;

    private CrashHandler(){

    }

    public static CrashHandler getsInstance(){
        return sInstance;
    }

    public void init(Context context){
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        mContext = context.getApplicationContext();
    }

    @Override
    public void uncaughtException(Thread t, Throwable ex) {
        try{
            //Send to SD Card
            dumpExceptionToSDCard(ex);
            //Upload Exception to Server
            uploadExceptionToServer();
        }catch (Exception e){
            e.printStackTrace();
        }
        ex.printStackTrace();

        //Is system know how to do? Otherwise I will take up
        if(mDefaultCrashHandler != null){
            mDefaultCrashHandler.uncaughtException(t,ex);
        }else{
            Process.killProcess(Process.myPid());
        }
    }

    private void dumpExceptionToSDCard(Throwable ex) throws IOException{
        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            if(DEBUG){
                Log.w(TAG,"sdcard unmounted, skip dump exception");
                return;
            }
        }

        File dir = new File(PATH);
        if(!dir.exists())
            dir.mkdirs();

        long current = System.currentTimeMillis();
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(current));
        File file = new File(PATH + FILE_NAME + time + FILE_NAME_SUFFIX);

        try{
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            pw.println(time);
            dumpPhoneInfo(pw);
            pw.println();
            ex.printStackTrace(pw);
            pw.close();
        }catch (Exception e){
            Log.e(TAG, "dump crash info failed");
        }
    }

    private void dumpPhoneInfo(PrintWriter pw) throws PackageManager.NameNotFoundException{
        PackageManager pm = mContext.getPackageManager();
        PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(),PackageManager.GET_ACTIVITIES);
        pw.print("App Version Name:");
        pw.print(pi.versionName);
        pw.print(", App Version Code:");
        pw.print(pi.versionCode);

        pw.print("\nOS Version:");
        pw.print(Build.VERSION.RELEASE);
        pw.print(", SDK Version:");
        pw.print(Build.VERSION.SDK_INT);

        pw.print("\nVendor:");
        pw.print(Build.MANUFACTURER);
        pw.print(", Model:");
        pw.print(Build.MODEL);
        pw.print(", CPU ABI:");
        pw.print(Build.CPU_ABI);
    }

    private void uploadExceptionToServer(){
        //TODO Upload Exception Message To Server
        Log.w(TAG, "TODO, now upload to server");
    }


}
