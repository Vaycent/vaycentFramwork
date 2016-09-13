package DataBase.Database;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import DataBase.Bean.BP;
import DataBase.OrmliteSharp.OrmliteSharp;
import DataBase.OrmliteSharp.OrmliteSharpHelper;


/**
 * Created by Vaycent on 16/9/8.
 */
public class DB_BP {
    private Context context;

    private String sys;
    private String dia;
    private String hr;
    private String recordtime;
    private OrmliteSharp ormliteSharp;

    public DB_BP(Context ct, OrmliteSharp os){
        this.context=ct;
        this.ormliteSharp=os;
    }


    public void testBPFunc(){
        randomValue();
        BP bpObj = new BP(sys, dia,hr,recordtime);

//        OrmliteSharpHelper ormliteSharpHelper = ormliteSharp.synchronizedDB();

//        helper.getUserDao().create(u2);

        try{
            OrmliteSharpHelper ormliteSharpHelper = ormliteSharp.synchronizedDB();
            Dao testDao=ormliteSharp.getOS_Dao(ormliteSharpHelper,BP.class);
            testDao.create(bpObj);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void addBP() {
        randomValue();
        BP u1 = new BP(sys, dia,hr,recordtime);

        OrmliteSharpHelper ormliteSharpHelper = ormliteSharp.synchronizedDB();

        try
        {

            ormliteSharp.getOS_Dao(BP.class).create(u1);
            randomValue();
            u1 = new BP(sys, dia,hr,recordtime);
            ormliteSharp.getOS_Dao(BP.class).create(u1);

            randomValue();
            u1 = new BP(sys, dia,hr,recordtime);
            ormliteSharp.getOS_Dao(BP.class).create(u1);

            randomValue();
            u1 = new BP(sys, dia,hr,recordtime);
            ormliteSharp.getOS_Dao(BP.class).create(u1);

            randomValue();
            u1 = new BP(sys, dia,hr,recordtime);
            ormliteSharp.getOS_Dao(BP.class).create(u1);

            randomValue();
            u1 = new BP(sys, dia,hr,recordtime);
            ormliteSharp.getOS_Dao(BP.class).create(u1);

            randomValue();
            u1 = new BP(sys, dia,hr,recordtime);
            ormliteSharp.getOS_Dao(BP.class).create(u1);

            testList();


        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteBP() {
        try
        {
            ormliteSharp.getOS_Dao(BP.class).deleteById(2);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteAllBP() {
        try
        {
            ormliteSharp.getOS_Dao(BP.class).deleteById(2);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void updateBP() {
        try
        {
            randomValue();

            BP u1 = new BP(sys, dia,hr,recordtime);
            u1.setId(3);
            ormliteSharp.getOS_Dao(BP.class).update(u1);

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void testList() {
        try
        {
            randomValue();

            BP u1 = new BP(sys, dia,hr,recordtime);
            u1.setId(2);
            List<BP> bps = ormliteSharp.getOS_Dao(BP.class).queryForAll();
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
