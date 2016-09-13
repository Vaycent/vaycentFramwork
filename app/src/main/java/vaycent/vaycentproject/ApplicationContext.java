package vaycent.vaycentproject;

import android.app.Application;

import com.facebook.stetho.Stetho;

import vaycent.volleysharp.VolleySharp;

/**
 * Created by Vaycent on 16/9/7.
 */
public class ApplicationContext extends Application {

    public VolleySharp volleySharp;


    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);

        initVolleySharp();
    }

    private void initVolleySharp(){
        volleySharp = new VolleySharp(this);
    }



}
