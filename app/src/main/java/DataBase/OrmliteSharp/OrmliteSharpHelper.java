package DataBase.OrmliteSharp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vaycent on 16/9/10.
 */

public class OrmliteSharpHelper extends OrmLiteSqliteOpenHelper
{
    private static final String TAG="OrmliteDatabaseHelper";
    private static OrmliteSharpHelper instance;

    private Map<String, Dao> daos = new HashMap<String, Dao>();

    private static ArrayListClass<? extends Class> classList;
    private static String dbName = "";
    private static int dbVersion= 0;



    public static void initParameter(String name, int version, ArrayListClass<? extends Class> cl){
        classList=cl;
        dbName=name;
        dbVersion=version;
    }

    private OrmliteSharpHelper(Context context, String dbName, int dbVersion) {
        super(context, dbName, null, dbVersion);
    }


    public static synchronized OrmliteSharpHelper getHelper(Context context) {
        if (instance == null)
        {
            synchronized (OrmliteSharpHelper.class)
            {
                if (instance == null)
                    if(dbName.equalsIgnoreCase("")||dbVersion==0){
                        Log.e(TAG,"You should use initParameter() function to setup dbName and dbVersion before you getHelper");
                    }else{
                        instance = new OrmliteSharpHelper(context,dbName,dbVersion);
                    }
            }
        }
        return instance;
    }

    public synchronized Dao getDao(Class clazz) throws SQLException
    {
        Dao dao = null;
        String className = clazz.getSimpleName();
        System.out.println("daos size :"+daos.size());

        for(int i=0;i<daos.size();i++){
            System.out.println("dao:"+daos.get(i).getClass().getSimpleName());
        }



        if (daos.containsKey(className))
        {
            System.out.println("11111");

            dao = daos.get(className);
            System.out.println("22222");

        }
        if (dao == null)
        {
            System.out.println("33333");
            dao = super.getDao(clazz);
            System.out.println("44444");

            daos.put(className, dao);
        }
        return dao;
    }

    /************************  @Override *************************/

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try
        {
            for(int i=0;i<classList.getSize();i++){
                TableUtils.createTable(connectionSource, classList.getVar(i));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          ConnectionSource connectionSource, int oldVersion, int newVersion)
    {
        try
        {
            for(int i=0;i<classList.getSize();i++){
                Class classObj=classList.getVar(i).getClass();
                TableUtils.dropTable(connectionSource, classObj, true);
            }
            onCreate(database, connectionSource);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void close()
    {
        super.close();

        for (String key : daos.keySet())
        {
            Dao dao = daos.get(key);
            dao = null;
        }
    }

}