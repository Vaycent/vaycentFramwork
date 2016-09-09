package Ormlite;

import android.content.Context;

import Ormlite.Database.DBHelper_BG;
import Ormlite.Database.DBHelper_BP;
import vaycent.vaycentproject.ApplicationContext;

/**
 * Created by Vaycent on 16/9/7.
 */
public class OrmliteHelper {
    private Context context;
    private ApplicationContext appContext;

    public DBHelper_BP dbHelper_bp;
    public DBHelper_BG dbHelper_bg;


    public OrmliteHelper(Context ct, ApplicationContext ac){
        context=ct;
        appContext=ac;

        dbHelper_bp=new DBHelper_BP(context);
        dbHelper_bg=new DBHelper_BG(context);

    }




}