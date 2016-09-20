package DataBase.Database;

import android.content.Context;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import vaycent.ormlitesharp.OrmliteSharp;


/**
 * Created by Vaycent on 16/9/8.
 */
public class DB_BG {
    private Context context;

    private String bgValue;
    private String type;
    private String recordtime;

    private OrmliteSharp ormliteSharp;

    public DB_BG(Context ct, OrmliteSharp os){
        this.context=ct;
        this.ormliteSharp=os;
    }

//    public void addBG() {
//
//        randomValue();
//        BG u1 = new BG(bgValue, type,recordtime);
//        ormliteSharp.addToDB(BG.class,u1);
//
//
//        OrmliteSharpHelper ormliteSharpHelper = ormliteSharp.synchronizedDB();
//
//        try
//        {
//            ormliteSharp.getOS_Dao(ormliteSharpHelper,BG.class).create(u1);
//
//            randomValue();
//            u1 = new BG(bgValue, type,recordtime);
//            ormliteSharp.getOS_Dao(ormliteSharpHelper,BG.class).create(u1);
//
//            randomValue();
//            u1 = new BG(bgValue, type,recordtime);
//            ormliteSharp.getOS_Dao(ormliteSharpHelper,BG.class).create(u1);
//
//
//        } catch (SQLException e)
//        {
//            e.printStackTrace();
//        }
//    }
//
//    public void deleteBG() {
//        OrmliteSharpHelper ormliteSharpHelper = ormliteSharp.synchronizedDB();
//        try
//        {
//            ormliteSharp.getOS_Dao(ormliteSharpHelper,BG.class).deleteById(2);
//        } catch (SQLException e)
//        {
//            e.printStackTrace();
//        }
//    }
//
//    public void deleteAllBG() {
//        OrmliteSharpHelper ormliteSharpHelper = ormliteSharp.synchronizedDB();
//        try
//        {
//            ormliteSharp.getOS_Dao(ormliteSharpHelper,BG.class).deleteById(2);
//        } catch (SQLException e)
//        {
//            e.printStackTrace();
//        }
//    }
//
//    public void updateBG() {
//        OrmliteSharpHelper ormliteSharpHelper = ormliteSharp.synchronizedDB();
//
//        try
//        {
//            randomValue();
//
//            BG u1 = new BG(bgValue, type,recordtime);
//            u1.setId(3);
//            ormliteSharp.getOS_Dao(ormliteSharpHelper,BG.class).update(u1);
//
//        } catch (SQLException e)
//        {
//            e.printStackTrace();
//        }
//    }
//
//    public void testList() {
//        OrmliteSharpHelper ormliteSharpHelper = ormliteSharp.synchronizedDB();
//
//        try
//        {
//            randomValue();
//
//            BG u1 = new BG(bgValue, type,recordtime);
//            u1.setId(2);
//            List<BG> bgs = ormliteSharp.getOS_Dao(ormliteSharpHelper,BG.class).queryForAll();
//            Log.e("TAG", bgs.toString());
//        } catch (SQLException e)
//        {
//            e.printStackTrace();
//        }
//    }


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
