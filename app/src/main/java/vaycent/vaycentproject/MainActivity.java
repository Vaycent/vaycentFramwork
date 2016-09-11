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
import android.widget.Button;

import DataBase.DB_Helper;
import Request.Request_Helper;
import vaycent.vaycentproject.DemoPackage.AnimationDemo;
import vaycent.vaycentproject.DemoPackage.NotificationDemo;
import vaycent.vaycentproject.DemoPackage.TextViewDemo;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ApplicationContext appContext;
    private Request_Helper myVolleySharp;
    private DB_Helper myOrmliteSharp;

    private Button db_add_btn,db_update_btn,db_delete_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appContext = ((ApplicationContext) this.getApplication());
        myVolleySharp=new Request_Helper(this, appContext);
        myOrmliteSharp =new DB_Helper(this, appContext);

        initLayout();

//        vsHelper.testGetRequest();
        myVolleySharp.testPostRequest(); //use to test post request and xml dom parser
        myVolleySharp.testJsonRequest();


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

        if (id == R.id.textview_demo) {
            Intent intent = new Intent(this, TextViewDemo.class);
            startActivity(intent);
        } else if (id == R.id.notification_demo) {
            Intent intent = new Intent(this, NotificationDemo.class);
            startActivity(intent);
        } else if (id == R.id.animation_demo) {
            Intent intent = new Intent(this, AnimationDemo.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

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



        db_add_btn=(Button)findViewById(R.id.db_add_btn);
        db_update_btn=(Button)findViewById(R.id.db_update_btn);
        db_delete_btn=(Button)findViewById(R.id.db_delete_btn);

        db_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myOrmliteSharp.dbHelper_bp.addBP();
                myOrmliteSharp.dbHelper_bg.addBG();
            }
        });
        db_update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myOrmliteSharp.dbHelper_bp.updateBP();
                myOrmliteSharp.dbHelper_bg.updateBG();
            }
        });
        db_delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myOrmliteSharp.dbHelper_bp.deleteBP();
                myOrmliteSharp.dbHelper_bg.deleteBG();
            }
        });
    }



}
