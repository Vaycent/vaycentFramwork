package vaycent.vaycentproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import Helper.ViewPageAdapter;
import ImageLoader.ImageLoaderSharp;
import vaycent.magicLog.mlog;
import vaycent.vaycentproject.DemoPackage.AnimationDemo;
import vaycent.vaycentproject.DemoPackage.BroadcastReceiverPackage.BroadcastReceiverDemo;
import vaycent.vaycentproject.DemoPackage.ContentProviderPackage.ContentProviderDemo;
import vaycent.vaycentproject.DemoPackage.FragmentPackage.FragmentDemo;
import vaycent.vaycentproject.DemoPackage.IPC_Package.IPCDemo;
import vaycent.vaycentproject.DemoPackage.NotificationPackage.NotificationDemo;
import vaycent.vaycentproject.DemoPackage.OrmliteSharpDemo;
import vaycent.vaycentproject.DemoPackage.RecycleViewPackage.RecycleViewDemo;
import vaycent.vaycentproject.DemoPackage.TextViewDemo;
import vaycent.vaycentproject.DemoPackage.VolleysharpDemo;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ApplicationContext appContext;

    ListView listview = null;

    private final String data[] = { "111","222","111","222","111","222","111","222","111","222","111","222",
            "111","222","111","222","111","222","111","222","111","222","111","222","111","222","111","222"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mlog.e("onCreate");
        setContentView(R.layout.activity_main);

        appContext = ((ApplicationContext) this.getApplication());
        initLayout();

        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        Log.d("TAG", "Max memory is " + maxMemory + "KB");

        initHeadView();
    }

    private void initHeadView()
    {
        listview = (ListView)this.findViewById(R.id.lv_fir);

        View view = LayoutInflater.from(this).inflate(R.layout.head_viewpager, null);
        ViewPager viewpager = (ViewPager)view.findViewById(R.id.headviewpager);
        List<ImageView> listtemp = new ArrayList<ImageView>();
        for(int i = 0;i<4;i++)
        {
            ImageView img = new ImageView(this);
            img.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,100));
            img.setScaleType(ImageView.ScaleType.FIT_XY);


            ImageLoaderSharp imageLoader = new ImageLoaderSharp(this,img,R.drawable.heigh01);

            if(i==0)
                img.setBackgroundResource(R.drawable.heigh01);
            if(i==1)
                img.setBackgroundResource(R.drawable.heigh02);
            if(i==2)
                img.setBackgroundResource(R.drawable.heigh03);
            if(i==3)
                img.setBackgroundResource(R.drawable.heigh04);
            listtemp.add(img);
        }

        ViewPageAdapter viewadapter = new ViewPageAdapter(listtemp);

        listview.addHeaderView(view);
        listview.addFooterView(view);
        listview.setAdapter(new ArrayAdapter<String>(this,R.layout.list_item2,data));
        viewpager.setAdapter(viewadapter);
//        listview.addHeaderView(LayoutInflater.from(this).inflate(R.layout.headlayout,null));

//		setListViewHeightBasedOnChildren(listview);
    }

    @Override
    protected void onStart(){
        super.onStart();
        mlog.e("onStart");
    }

    @Override
    protected void onResume(){
        super.onResume();
        mlog.e("onResume");
    }

    @Override
    protected void onPause(){
        super.onPause();
        mlog.e("onPause");
    }

    @Override
    protected void onStop(){
        super.onStop();
        mlog.e("onStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mlog.e("onDestroy");
    }





    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.ormlitesharp_demo) {
            Intent intent = new Intent(this, OrmliteSharpDemo.class);
            startActivity(intent);
        }else if (id == R.id.volleysharp_demo) {
            Intent intent = new Intent(this, VolleysharpDemo.class);
            startActivity(intent);
        } else if (id == R.id.recycleview_demo) {
            Intent intent = new Intent(this, RecycleViewDemo.class);
            startActivity(intent);
        }else if (id == R.id.textview_demo) {
            Intent intent = new Intent(this, TextViewDemo.class);
            startActivity(intent);
        } else if (id == R.id.notification_demo) {
            Intent intent = new Intent(this, NotificationDemo.class);
            startActivity(intent);
        } else if (id == R.id.animation_demo) {
            Intent intent = new Intent(this, AnimationDemo.class);
            startActivity(intent);
        } else if (id == R.id.broadcast_receive_demo) {
            Intent intent = new Intent(this, BroadcastReceiverDemo.class);
            startActivity(intent);
        } else if (id == R.id.content_provider_demo) {
            Intent intent = new Intent(this, ContentProviderDemo.class);
            startActivity(intent);
        } else if (id == R.id.fragment_demo) {
            Intent intent = new Intent(this, FragmentDemo.class);
            startActivity(intent);
        }else if (id == R.id.ipc_demo) {
            Intent intent = new Intent(this, IPCDemo.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initLayout(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);









//        mainWebView=(WebView) findViewById(R.id.mainWebView);
//        mainWebView.getSettings().setJavaScriptEnabled(true);
//        mainWebView.loadUrl("https://wwwtest.smartone.com/SmarToneCARE/page_index.html?sl=co&add=no#!MainPage_m0");
//        mainWebView.loadUrl("javascript:apk2page('PutDeviceID','" + 123456 + "')");
    }



}
