package OrmliteSharp.Bean.Database;

import android.content.Context;
import android.util.Log;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import OrmliteSharp.Bean.BG;

/**
 * Created by Vaycent on 16/9/8.
 */
public class DBHelper_BG {
    private Context context;

    private String bgValue;
    private String type;
    private String recordtime;

    public DBHelper_BG(Context ct){
        this.context=ct;
    }

    public void addBG() {



        randomValue();
        BG u1 = new BG(bgValue, type,recordtime);
        DatabaseHelper helper = DatabaseHelper.getHelper(context);
        try
        {
            helper.getBGDao().create(u1);

            randomValue();
            u1 = new BG(bgValue, type,recordtime);
            helper.getBGDao().create(u1);

            randomValue();
            u1 = new BG(bgValue, type,recordtime);
            helper.getBGDao().create(u1);

            randomValue();
            u1 = new BG(bgValue, type,recordtime);
            helper.getBGDao().create(u1);

            randomValue();
            u1 = new BG(bgValue, type,recordtime);
            helper.getBGDao().create(u1);

            randomValue();
            u1 = new BG(bgValue, type,recordtime);
            helper.getBGDao().create(u1);

            randomValue();
            u1 = new BG(bgValue, type,recordtime);
            helper.getBGDao().create(u1);

            testList();


        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteBG() {
        DatabaseHelper helper = DatabaseHelper.getHelper(context);
        try
        {
            helper.getBGDao().deleteById(2);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteAllBG() {
        DatabaseHelper helper = DatabaseHelper.getHelper(context);
        try
        {
            helper.getBGDao().deleteById(2);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void updateBG() {
        DatabaseHelper helper = DatabaseHelper.getHelper(context);
        try
        {
            randomValue();

            BG u1 = new BG(bgValue, type,recordtime);
            u1.setId(3);
            helper.getBGDao().update(u1);

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void testList() {
        DatabaseHelper helper = DatabaseHelper.getHelper(context);
        try
        {
            randomValue();

            BG u1 = new BG(bgValue, type,recordtime);
            u1.setId(2);
            List<BG> bgs = helper.getBGDao().queryForAll();
            Log.e("TAG", bgs.toString());
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
        bgValue=  (int)(Math.random()*100)+"";
        type="F";
        recordtime= getRecordtime();
    }
}
