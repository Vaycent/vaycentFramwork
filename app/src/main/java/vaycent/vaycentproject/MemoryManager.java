package vaycent.vaycentproject;

import android.content.Context;

/**
 * Created by Vaycent on 2016/12/2.
 */

public class MemoryManager {

    private static MemoryManager instance;

    private Context context;

    private MemoryManager(Context context){
        this.context = context;
    }

    public static MemoryManager getInstance(Context context){
        if(instance == null)
            instance = new MemoryManager(context);
        return instance;
    }
}
