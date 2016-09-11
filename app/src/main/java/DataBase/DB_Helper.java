package DataBase;

import android.content.Context;

import java.util.ArrayList;

import BaseClass.BaseValue;
import DataBase.Bean.BG;
import DataBase.Bean.BP;
import DataBase.Database.DBHelper_BG;
import DataBase.Database.DBHelper_BP;
import vaycent.ormlitesharp.OrmliteSharp;
import vaycent.vaycentproject.ApplicationContext;

/**
 * Created by Vaycent on 16/9/7.
 */
public class DB_Helper {
    private Context context;
    private ApplicationContext appContext;

    public DBHelper_BP dbHelper_bp;
    public DBHelper_BG dbHelper_bg;

    private OrmliteSharp ormDBHelper;


    public DB_Helper(Context ct, ApplicationContext ac){
        context=ct;
        appContext=ac;

        ArrayList<Class> classList = new ArrayList<Class>();
        classList.add(BP.class);
        classList.add(BG.class);
        ormDBHelper.init(BaseValue.DATABASE_NAME,BaseValue.DATABASE_VERSION,classList);

        dbHelper_bp=new DBHelper_BP(context);
        dbHelper_bg=new DBHelper_BG(context);

    }




}