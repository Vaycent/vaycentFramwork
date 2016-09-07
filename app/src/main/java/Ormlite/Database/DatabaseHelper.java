package Ormlite.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import Ormlite.Bean.BP;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper
{

    private static DatabaseHelper instance;



    private static final String TABLE_NAME = "vaycent.test.db";
    /**
     * Should create once Dao for every table
     */
    private Dao<BP, Integer> bpDao;

    private DatabaseHelper(Context context)
    {
        super(context, TABLE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase database,
                         ConnectionSource connectionSource)
    {
        try
        {
            TableUtils.createTable(connectionSource, BP.class);
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
            onCreate(database, connectionSource);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


    /**
     * 单例获取该Helper
     *
     * @param context
     * @return
     */
    public static synchronized DatabaseHelper getHelper(Context context)
    {
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

    /**
     * 获得userDao
     *
     * @return
     * @throws SQLException
     */
    public Dao<BP, Integer> getBPDao() throws SQLException
    {
        if (bpDao == null)
        {
            bpDao = getDao(BP.class);
        }
        return bpDao;
    }

    /**
     * 释放资源
     */
    @Override
    public void close()
    {
        super.close();
        bpDao = null;
    }

}
