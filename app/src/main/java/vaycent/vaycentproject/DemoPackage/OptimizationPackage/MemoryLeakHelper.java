package vaycent.vaycentproject.DemoPackage.OptimizationPackage;

import android.content.Context;

/**
 * Created by Vaycent on 2016/12/2.
 */

public class MemoryLeakHelper {

    private static MemoryLeakHelper instance;

    private Context context;

    //***** This is the wrong instance *****
    private MemoryLeakHelper(Context context){
        this.context = context;
    }

    public static MemoryLeakHelper getInstance(Context context){
        if(instance == null)
            instance = new MemoryLeakHelper(context);
        return instance;
    }


    //***** This is the right instance *****
    private MemoryLeakHelper(Context context,boolean isApp){
        this.context = context.getApplicationContext();
    }

    public static MemoryLeakHelper getApplicationInstance(Context context){
        if(instance == null){
            instance = new MemoryLeakHelper(context,true);
        }
        return instance;
    }
}
