package Ormlite.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import BaseClass.BaseValue;
import Ormlite.Bean.BG;
import Ormlite.Bean.BP;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper
{

    private static DatabaseHelper instance;
    private Map<String, Dao> daos = new HashMap<String, Dao>();

    private static final String dbName = BaseValue.DATABASE_NAME;
    private static final int dbVersion= BaseValue.DATABASE_VERSION;

    private DatabaseHelper(Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try
        {
            TableUtils.createTable(connectionSource, BP.class);
            TableUtils.createTable(connectionSource, BG.class);
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
            TableUtils.dropTable(connectionSource, BP.class, true);
            TableUtils.dropTable(connectionSource, BG.class,true);
            onCreate(database, connectionSource);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


    public static synchronized DatabaseHelper getHelper(Context context) {
        if (instance == null)
        {
            synchronized (DatabaseHelper.class)
            {
                if (instance == null)
                    instance = new DatabaseHelper(context);
            }
        }
        return instance;
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

    public synchronized Dao getDao(Class clazz) throws SQLException
    {
        Dao dao = null;
        String className = clazz.getSimpleName();

        if (daos.containsKey(className))
        {
            dao = daos.get(className);
        }
        if (dao == null)
        {
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }
        return dao;
    }




}
