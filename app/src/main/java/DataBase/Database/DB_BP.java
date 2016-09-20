package DataBase.Database;

import android.content.Context;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import DataBase.Bean.BP;
import DataBase.Bean.BPList;
import vaycent.ormlitesharp.DbObjectClass;
import vaycent.ormlitesharp.OrmliteSharp;


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



    public void addBP() {
        randomValue();
        BP bpObj = new BP(sys, dia, hr, recordtime);
        ormliteSharp.addToDB(BP.class, bpObj);

        randomValue();
        bpObj = new BP(sys, dia, hr, recordtime);
        ormliteSharp.addToDB(BP.class, bpObj);

        randomValue();
        bpObj = new BP(sys, dia, hr, recordtime);
        ormliteSharp.addToDB(BP.class, bpObj);
    }

    public void deleteBPById(int Id) {
        DbObjectClass myClass=new DbObjectClass();
        myClass.setClassData(BP.class);
        ormliteSharp.deleteById(myClass,Id);
    }

    public BP selectBPById(int Id){
        DbObjectClass myClass=new DbObjectClass();
        myClass.setClassData(BP.class);
        BP bpObj=new BP();
        bpObj= (BP) ormliteSharp.selectBPById(myClass,Id);
        return bpObj;
    }

    public void updateBPById(int Id) {
        DbObjectClass myClass=new DbObjectClass();
        myClass.setClassData(BP.class);

        BP bpObj = new BP("11", "22", "33", "44");
        bpObj.setId(Id);

        ormliteSharp.updateToDB(myClass,bpObj);
    }

    public BPList selectBPAll(){
        DbObjectClass myClass=new DbObjectClass();
        myClass.setClassData(BP.class);
        List list=ormliteSharp.selectAll(myClass);

        BPList bpList=new BPList();
        for(int i=0;i<list.size();i++){
            bpList.add((BP) list.get(i));
        }
        return bpList;
    }

    public void deleteBPTable() {
        DbObjectClass myClass=new DbObjectClass();
        myClass.setClassData(BP.class);
        ormliteSharp.deleteTable(myClass);
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
