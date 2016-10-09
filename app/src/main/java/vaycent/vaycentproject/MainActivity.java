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

import vaycent.vaycentproject.DemoPackage.AnimationDemo;
import vaycent.vaycentproject.DemoPackage.BroadcastReceiverPackage.BroadcastReceiverDemo;
import vaycent.vaycentproject.DemoPackage.ContentProviderPackage.ContentProviderDemo;
import vaycent.vaycentproject.DemoPackage.NotificationPackage.NotificationDemo;
import vaycent.vaycentproject.DemoPackage.OrmliteSharpDemo;
import vaycent.vaycentproject.DemoPackage.TextViewDemo;
import vaycent.vaycentproject.DemoPackage.VolleysharpDemo;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ApplicationContext appContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appContext = ((ApplicationContext) this.getApplication());
        initLayout();
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
        } else if (id == R.id.textview_demo) {
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
    }



}
