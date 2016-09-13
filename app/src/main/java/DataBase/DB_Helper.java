package DataBase;

import android.content.Context;

import BaseClass.BaseValue;
import DataBase.Bean.BG;
import DataBase.Bean.BP;
import DataBase.Database.DB_BG;
import DataBase.Database.DB_BP;
import DataBase.OrmliteSharp.ArrayListClass;
import DataBase.OrmliteSharp.OrmliteSharp;
import vaycent.vaycentproject.ApplicationContext;

/**
 * Created by Vaycent on 16/9/7.
 */
public class DB_Helper {
    private Context context;
    private ApplicationContext appContext;

    private OrmliteSharp ormliteSharp;
    public DB_BP db_bp;
    public DB_BG db_bg;



    public DB_Helper(Context ct, ApplicationContext ac){
        context=ct;
        appContext=ac;


        initOrmliteSharp();
        db_bp =new DB_BP(context,ormliteSharp);
        db_bg =new DB_BG(context,ormliteSharp);

    }

    private void initOrmliteSharp(){

        ArrayListClass classList = new ArrayListClass();
        classList.setVar(BP.class);
        classList.setVar(BG.class);

        ormliteSharp = new OrmliteSharp(context, BaseValue.DATABASE_NAME,BaseValue.DATABASE_VERSION,classList);
    }


}