package DataBase.Database;

import android.content.Context;
import android.util.Log;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import DataBase.Bean.BP;
import vaycent.ormlitesharp.OrmliteSharp;
import vaycent.ormlitesharp.OrmliteSharpHelper;

/**
 * Created by Vaycent on 16/9/8.
 */
public class DBHelper_BP {
    private Context context;

    private String sys;
    private String dia;
    private String hr;
    private String recordtime;

    public DBHelper_BP(Context ct){
        this.context=ct;
    }

    public void addBP() {



        randomValue();
        BP u1 = new BP(sys, dia,hr,recordtime);
        OrmliteSharp helper = OrmliteSharp.getHelper(context);
        try
        {
            helper.getBPDao().create(u1);

            randomValue();
            u1 = new BP(sys, dia,hr,recordtime);
            helper.getBPDao().create(u1);

            randomValue();
            u1 = new BP(sys, dia,hr,recordtime);
            helper.getBPDao().create(u1);

            randomValue();
            u1 = new BP(sys, dia,hr,recordtime);
            helper.getBPDao().create(u1);

            randomValue();
            u1 = new BP(sys, dia,hr,recordtime);
            helper.getBPDao().create(u1);

            randomValue();
            u1 = new BP(sys, dia,hr,recordtime);
            helper.getBPDao().create(u1);

            randomValue();
            u1 = new BP(sys, dia,hr,recordtime);
            helper.getBPDao().create(u1);

            testList();


        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteBP() {
        OrmliteSharp helper = OrmliteSharp.getHelper(context);
        try
        {
            helper.getBPDao().deleteById(2);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteAllBP() {
        OrmliteSharp helper = OrmliteSharp.getHelper(context);
        try
        {
            helper.getBPDao().deleteById(2);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void updateBP() {
        OrmliteSharp helper = OrmliteSharp.getHelper(context);
        try
        {
            randomValue();

            BP u1 = new BP(sys, dia,hr,recordtime);
            u1.setId(3);
            helper.getBPDao().update(u1);

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void testList() {
        OrmliteSharp helper = OrmliteSharp.getHelper(context);
        try
        {
            randomValue();

            BP u1 = new BP(sys, dia,hr,recordtime);
            u1.setId(2);
            List<BP> bps = helper.getBPDao().queryForAll();
            Log.e("TAG", bps.toString());
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


    private String getRecordtime(){
        Date time = new Date();
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String temp=format1.format(time);
        return temp;
    }

    private void randomValue(){
        sys=  (int)(Math.random()*100)+"";
        dia=  (int)( Math.random()*100)+"";
        hr=  (int)(Math.random()*100)+"";
        recordtime= getRecordtime();
    }
}
