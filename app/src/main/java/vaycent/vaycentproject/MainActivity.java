package vaycent.vaycentproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import vaycent.magicLog.mlog;
import vaycent.vaycentproject.DemoPackage.AnimationDemo;
import vaycent.vaycentproject.DemoPackage.BroadcastReceiverPackage.BroadcastReceiverDemo;
import vaycent.vaycentproject.DemoPackage.ContentProviderPackage.ContentProviderDemo;
import vaycent.vaycentproject.DemoPackage.FragmentPackage.FragmentDemo;
import vaycent.vaycentproject.DemoPackage.IPC_Package.IpcDemo;
import vaycent.vaycentproject.DemoPackage.IPC_Package.Person;
import vaycent.vaycentproject.DemoPackage.NotificationPackage.NotificationDemo;
import vaycent.vaycentproject.DemoPackage.OrmliteSharpDemo;
import vaycent.vaycentproject.DemoPackage.RecycleViewPackage.RecycleViewDemo;
import vaycent.vaycentproject.DemoPackage.TextViewDemo;
import vaycent.vaycentproject.DemoPackage.VolleysharpDemo;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ApplicationContext appContext;

    private Button switchToActivity2;

    private WebView mainWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mlog.e("onCreate");
        setContentView(R.layout.activity_main);

        appContext = ((ApplicationContext) this.getApplication());
        initLayout();
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
            Intent intent = new Intent(this, IpcDemo.class);
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



        switchToActivity2=(Button)findViewById(R.id.switchToActivity2);
        switchToActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setClass(MainActivity.this, TestActivity2.class);
//                intent.setAction(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
//                startActivity(intent);

                try{
                    File appPath=new File(getApplicationContext().getFilesDir().getAbsolutePath()+"/cache.txt");

                    ObjectInputStream in = new ObjectInputStream(new FileInputStream(appPath));
                    Person newPerson = (Person)in.readObject();
                    in.close();


                    mlog.d("newPerson id:"+newPerson.getId());
                    mlog.d("newPerson name:"+newPerson.getName());
                    mlog.d("newPerson age:"+newPerson.getAge());

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });



//        mainWebView=(WebView) findViewById(R.id.mainWebView);
//        mainWebView.getSettings().setJavaScriptEnabled(true);
//        mainWebView.loadUrl("https://wwwtest.smartone.com/SmarToneCARE/page_index.html?sl=co&add=no#!MainPage_m0");
//        mainWebView.loadUrl("javascript:apk2page('PutDeviceID','" + 123456 + "')");
    }



}
