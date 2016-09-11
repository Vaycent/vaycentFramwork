package BaseClass;

import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import OrmliteSharp.Bean.BG;
import OrmliteSharp.Bean.BP;

/**
 * Created by Vaycent on 16/9/7.
 */

public class BaseValue {
    public static final String PACKAGE_NAME="";  //global packet name value
    public static final boolean DEBUG = true;    //to choose Log how to print
    public static final boolean IS_PRODUCTION = false;   //to choose the server api verion
    public static final String API_DOMAIN0= IS_PRODUCTION ? "":"";
    public static final String API_DOMAIN1 = IS_PRODUCTION ?"":"";
    public static final String API_DOMAIN2 = "";
    public static final int versionControl=1;

    public static final String DATABASE_NAME="vaycent.db";
    public static final int DATABASE_VERSION=1;



    /* ＊**************************************************
    	magicLog
     ＊************************************************** */

    public final static int PRINT_LOG_LEVEL=-10;

    public final static File LOG_FILE_PATH=new File(Environment.getExternalStorageDirectory().getPath() + "/Log");
    public final static String LOGFILE_FILTER_PRIORITY="v"; //v,d,i,w,e,s
    public final static String LOGFILE_FILTER_TAG=""; // ""for do not filter the tag




    public static final ExecutorService SINGLE_TASK_EXECUTOR = (ExecutorService) Executors.newSingleThreadExecutor();    //to call AsyncTask one by one
    public static final ExecutorService LIMITED_TASK_EXECUTOR = (ExecutorService) Executors.newFixedThreadPool(7); 		//max count 7 to call AsyncTask
    public static final ExecutorService FULL_TASK_EXECUTOR=(ExecutorService) Executors.newCachedThreadPool(); ;  		//to call all the AsyncTask

    public static final String BLUE_TOOTH_KEY0 = "0000";     //the key is the deafult password use to auto connect to bluetooth device
    public static final String BLUE_TOOTH_KEY1 = "1234";

    public static final int STATUS_ON=1;     //to control on/off status value
    public static final int STATUS_OFF=0;

    public static final SimpleDateFormat TIME_FORMAT_YM = new SimpleDateFormat("yyyy MM");       //time format type
    public static final SimpleDateFormat TIME_FORMAT_YMD = new SimpleDateFormat("yyyy MM dd");
    public static final SimpleDateFormat TIME_FORMAT_YMDH = new SimpleDateFormat("yyyy MM dd HH");
    public static final SimpleDateFormat TIME_FORMAT_YMDHM = new SimpleDateFormat("yyyy MM dd HH:mm");
    public static final SimpleDateFormat TIME_FORMAT_YMDHMS = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
    public static final SimpleDateFormat TIME_FORMAT_MD = new SimpleDateFormat("MM dd");
    public static final SimpleDateFormat TIME_FORMAT_HM = new SimpleDateFormat("HH:mm");
    public static final SimpleDateFormat TIME_FORMAT_MS = new SimpleDateFormat("mm:ss");
    public static final SimpleDateFormat TIME_FORMAT_DM = new SimpleDateFormat("dd MM");
    public static final SimpleDateFormat TIME_FORMAT_DMY = new SimpleDateFormat("dd MM yyyy");
    public static final SimpleDateFormat TIME_FORMAT_MY = new SimpleDateFormat("MM yyyy");

    public static boolean isNFCLoad=false;








}
