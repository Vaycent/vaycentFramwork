package OrmliteSharp;

import android.content.Context;

import java.util.ArrayList;

import BaseClass.BaseValue;
import OrmliteSharp.Bean.BG;
import OrmliteSharp.Bean.BP;
import vaycent.ormlitesharp.OrmliteDatabaseHelper;
import vaycent.vaycentproject.ApplicationContext;

/**
 * Created by Vaycent on 16/9/7.
 */
public class OrmliteSharp {
    private Context context;
    private ApplicationContext appContext;

    public OrmliteSharp.Bean.Database.DBHelper_BP dbHelper_bp;
    public OrmliteSharp.Database.DBHelper_BG dbHelper_bg;

    private OrmliteDatabaseHelper ormDBHelper;


    public OrmliteSharp(Context ct, ApplicationContext ac){
        context=ct;
        appContext=ac;

        ArrayList<Class> classList = new ArrayList<Class>();
        classList.add(BP.class);
        classList.add(BG.class);
        ormDBHelper.init(BaseValue.DATABASE_NAME,BaseValue.DATABASE_VERSION,classList);

        dbHelper_bp=new OrmliteSharp.Bean.Database.DBHelper_BP(context);
        dbHelper_bg=new OrmliteSharp.Bean.Database.DBHelper_BG(context);

    }




}