package vaycent.vaycentproject;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import HelpFulClass.RoundTransform;
import Helper.MainGrid;
import Helper.NineGridViewAdapter;
import Helper.ViewPageAdapter;
import vaycent.base.router.XRouter;
import vaycent.base.router.XRules;
import vaycent.magicLog.mlog;
import vaycent.vaycentproject.DemoPackage.AnimationDemo;
import vaycent.vaycentproject.DemoPackage.AnnotationPackage.AnnotationDemo;
import vaycent.vaycentproject.DemoPackage.BackgroundControlPackage.BackgroundControlDemo;
import vaycent.vaycentproject.DemoPackage.BroadcastReceiverPackage.BroadcastReceiverDemo;
import vaycent.vaycentproject.DemoPackage.ButterKnifePackage.ButterKnifeDemo;
import vaycent.vaycentproject.DemoPackage.CardIoPackage.CardIoDemo;
import vaycent.vaycentproject.DemoPackage.ContentProviderPackage.ContentProviderDemo;
import vaycent.vaycentproject.DemoPackage.DownloadManagerPackage.DownloadManagerDemo;
import vaycent.vaycentproject.DemoPackage.EventPackage.EventDemo;
import vaycent.vaycentproject.DemoPackage.FragmentPackage.FragmentDemo;
import vaycent.vaycentproject.DemoPackage.IPC_Package.IpcDemo;
import vaycent.vaycentproject.DemoPackage.Matisse.MatisseActivity;
import vaycent.vaycentproject.DemoPackage.MessagePackage.AndroidMessageDemo;
import vaycent.vaycentproject.DemoPackage.NotificationPackage.NotificationDemo;
import vaycent.vaycentproject.DemoPackage.Observer.ObserverActivity;
import vaycent.vaycentproject.DemoPackage.OptimizationPackage.OptimizationDemo;
import vaycent.vaycentproject.DemoPackage.OrmliteSharpDemo;
import vaycent.vaycentproject.DemoPackage.RecycleViewPackage.RecycleViewDemo;
import vaycent.vaycentproject.DemoPackage.ShortcutsPackage.ShortcutsDemo;
import vaycent.vaycentproject.DemoPackage.SmsPackage.SmsDemo;
import vaycent.vaycentproject.DemoPackage.TestSpecialPackage.TestSpecialDemo;
import vaycent.vaycentproject.DemoPackage.TextViewDemo;
import vaycent.vaycentproject.DemoPackage.ViewPackage.ViewDemo;
import vaycent.vaycentproject.DemoPackage.ViewPackage.ViewDemo2;
import vaycent.vaycentproject.DemoPackage.VolleysharpDemo;
import vaycent.vaycentproject.DemoPackage.WebPackage.WebViewDemo;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView listview;

    private String TAG = "MainActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mlog.e("onCreate");
        setContentView(R.layout.activity_main);

        GetDeviceScreenWidth();

        InitLayout();

        InitListView();

        AddHeadView();

        mlog.StartWriteLog(this);


//        Intent intent =new Intent();
//        ComponentName cn = new ComponentName("com.csair.mbp", "com.csair.mbp.WelcomeActivity");
//        intent.setComponent(cn);
//        startActivity(intent);


    }

    private String getSoftwareVersion(){
        PackageInfo pi;
        try {
            pi = getPackageManager().getPackageInfo(getPackageName(), 0);

            return this.getPackageName()+"_"+pi.versionName;
        } catch (final PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "na";
        }
    }

    private String getChannel(Context context) {
        try {
            PackageManager pm = context.getPackageManager();

            ApplicationInfo appInfo = pm.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            return appInfo.metaData.getString("CHANNEL");
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return "";
    }


    @Override
    protected void onStart() {
        super.onStart();
        mlog.e("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mlog.e("onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        mlog.e("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        mlog.e("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mlog.e("onDestroy");

        mlog.StopWriteLog(this);
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
            startActivity(new Intent(this, RxAndroidDemo.class));
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
        } else if (id == R.id.volleysharp_demo) {
            Intent intent = new Intent(this, VolleysharpDemo.class);
            startActivity(intent);
        } else if (id == R.id.recycleview_demo) {
            Intent intent = new Intent(this, RecycleViewDemo.class);
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
        } else if (id == R.id.fragment_demo) {
            Intent intent = new Intent(this, FragmentDemo.class);
            startActivity(intent);
        } else if (id == R.id.ipc_demo) {
            Intent intent = new Intent(this, IpcDemo.class);
            startActivity(intent);
        } else if (id == R.id.event_demo) {
            Intent intent = new Intent(this, EventDemo.class);
            startActivity(intent);
        }else if (id == R.id.view_demo) {
            Intent intent = new Intent(this, ViewDemo.class);
            startActivity(intent);
        }
        else if (id == R.id.view_demo2) {
            Intent intent = new Intent(this, ViewDemo2.class);
            startActivity(intent);
        }else if (id == R.id.sms_demo) {
            Intent intent = new Intent(this, SmsDemo.class);
            startActivity(intent);
        }else if (id == R.id.androidmessage_demo) {
            Intent intent = new Intent(this, AndroidMessageDemo.class);
            startActivity(intent);
        }else if (id == R.id.optimization_demo) {
            Intent intent = new Intent(this, OptimizationDemo.class);
            startActivity(intent);
        }else if (id == R.id.webview_demo) {
            Intent intent = new Intent(this, WebViewDemo.class);
            startActivity(intent);
        }else if (id == R.id.shortcut_demo) {
            Intent intent = new Intent(this, ShortcutsDemo.class);
            startActivity(intent);
        }else if (id == R.id.butterknife_demo) {
            Intent intent = new Intent(this, ButterKnifeDemo.class);
            startActivity(intent);
        } else if (id == R.id.test_special_demo) {
            Intent intent = new Intent(this, TestSpecialDemo.class);
            startActivity(intent);
        }else if (id == R.id.background_control_demo) {
            Intent intent = new Intent(this, BackgroundControlDemo.class);
            startActivity(intent);
        }else if (id == R.id.download_manager_demo) {
            Intent intent = new Intent(this, DownloadManagerDemo.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT);
            startActivity(intent);
        }else if (id == R.id.databingding_demo) {
//            Intent intent = new Intent(this, DataBindingDemo.class);
//            startActivity(intent);
            XRouter.getRaw(XRules.VaycentDataBindingDemo.class, this).start();
        }else if (id == R.id.cardio_demo) {
            Intent intent = new Intent(this, CardIoDemo.class);
            startActivity(intent);
        }else if (id == R.id.annotation_demo) {
            Intent intent = new Intent(this, AnnotationDemo.class);
            startActivity(intent);
        }else if (id == R.id.matisse_demo) {
            Intent intent = new Intent(this, MatisseActivity.class);
            startActivity(intent);
        }else if (id == R.id.observer_demo) {
            Intent intent = new Intent(this, ObserverActivity.class);
            startActivity(intent);
        }







        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void InitLayout() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, getSoftwareVersion()+"_"+getChannel(MainActivity.this), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

//                Snackbar.make(view, "I am the tinker package", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        listview = (ListView) this.findViewById(R.id.main_listview);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addTab(tabLayout.newTab().setText("Home"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 4"));


    }

    private void InitListView(){
        final String data[] = {"111", "222", "111", "222", "111", "222", "111", "222", "111", "222", "111", "222",
                "111", "222", "111", "222", "111", "222", "111", "222", "111", "222", "111", "222", "111", "222", "111", "222"};

        listview.setAdapter(new ArrayAdapter<String>(this, R.layout.list_item2, data));

    }

    private void AddHeadView() {
        View view = LayoutInflater.from(this).inflate(R.layout.head_viewpager, null);

        ViewPager topViewPager = (ViewPager) view.findViewById(R.id.top_view_pager);
        MainGrid nineGridView = (MainGrid) view.findViewById(R.id.nine_grid_view);
        ImageView bigImg = (ImageView) view.findViewById(R.id.big_img);

        InitTopViewPage(topViewPager);
        InitNineGridView(nineGridView);
        InitBigImage(bigImg);

        listview.addHeaderView(view);
    }

    private void InitTopViewPage(final ViewPager topViewPager){
        final int[] topViewPagerSet = {R.mipmap.banner1, R.mipmap.banner2,
                R.mipmap.banner3, R.mipmap.banner4, };

        ArrayList<ImageView> viewPageList = new ArrayList<ImageView>();
        for (int i = 0; i < topViewPagerSet.length; i++) {
            ImageView img = new ImageView(this);
            img.setLayoutParams(new ViewGroup.LayoutParams(200,200));//ViewGroup.LayoutParams.WRAP_CONTENT
            img.setScaleType(ImageView.ScaleType.FIT_XY);

            Glide.with(this).load(topViewPagerSet[i])
            .transform(new RoundTransform(this,1)).into(img);//.sizeMultiplier(0.1f)
            viewPageList.add(img);
        }

        ViewPageAdapter viewadapter = new ViewPageAdapter(viewPageList);
        topViewPager.setAdapter(viewadapter);

        final Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int currentPosition = topViewPager.getCurrentItem();
                if(currentPosition == topViewPager.getAdapter().getCount() - 1){
                    topViewPager.setCurrentItem(0);
                }else{
                    topViewPager.setCurrentItem(currentPosition + 1);
                }
                mHandler.postDelayed(this,3000);
            }
        },3000);
    }

    private void InitNineGridView( MainGrid nineGridView ) {
        final int[] nineGridPicSet = {R.drawable.heigh01, R.drawable.heigh02,
                R.drawable.heigh03, R.drawable.heigh04, R.drawable.heigh05,
                R.drawable.heigh06, R.drawable.heigh07, R.drawable.heigh08,
                R.drawable.heigh09};

        NineGridViewAdapter myAdapter = new NineGridViewAdapter(this,nineGridPicSet);
        nineGridView.setAdapter(myAdapter);
    }

    private void InitBigImage(ImageView bigImg) {
          Glide.with(this).load(R.drawable.codebrain).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(bigImg);

//        String gifLink = "http://www.dogame.com.cn/bbs/attachments/20100402_d78786dafb1b470dcd6ctUVU4OaCMkyA.gif";
//        Glide.with(this).load(gifLink).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(bigImg);

//        Glide.with(this).load(R.drawable.heigh01).thumbnail(0.001f).into(bigImg);
//        Glide.with(this).load("http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg").into(bigImg);

    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        super.onMultiWindowModeChanged(isInMultiWindowMode);
        mlog.e("****** onMultiWindowModeChanged() ******");
        Toast.makeText(this,"This is onMultiWindowModeChanged:"+isInMultiWindowMode,Toast.LENGTH_SHORT).show();


    }

    private void GetDeviceScreenWidth(){
        DisplayMetrics dm = this.getApplication().getResources().getDisplayMetrics();
        float density = dm.density;
        int widthPixels = dm.widthPixels;
        int heightPixels = dm.heightPixels;
        mlog.d("density:"+density+",widthPixels:"+widthPixels+",heightPixels:"+heightPixels);
        mlog.d("Width dp:"+widthPixels/density+",Heigh dp:"+heightPixels/density);
    }
}
