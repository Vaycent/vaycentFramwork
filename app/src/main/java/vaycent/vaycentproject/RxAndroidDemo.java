package vaycent.vaycentproject;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class RxAndroidDemo extends AppCompatActivity {

    private static final String TAG = "RxAndroidSamples";

//    private final CompositeDisposable disposables = new CompositeDisposable();

    private TabLayout mMainTabLayout;
    private ViewPager mMainViewPager;
    private List<String> mTitleList = new ArrayList<>();
    private List<View> mViewList = new ArrayList<>();
    private View view1, view2, view3, view4, view5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_android_demo);

        mMainViewPager = (ViewPager) findViewById(R.id.mMainViewPager);
        mMainTabLayout = (TabLayout) findViewById(R.id.mMainTabLayout);

        LayoutInflater mInflater = LayoutInflater.from(this);
        view1 = mInflater.inflate(R.layout.content_main, null);
        view2 = mInflater.inflate(R.layout.content_main, null);
        view3 = mInflater.inflate(R.layout.content_main, null);
        view4 = mInflater.inflate(R.layout.content_main, null);
        view5 = mInflater.inflate(R.layout.content_main, null);

        mViewList.add(view1);
        mViewList.add(view2);
        mViewList.add(view3);
        mViewList.add(view4);
        mViewList.add(view5);

        mTitleList.add("No:1");
        mTitleList.add("No:2");
        mTitleList.add("No:3");
        mTitleList.add("No:4");
        mTitleList.add("No:5");

        mMainTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mMainTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mMainTabLayout.addTab(mMainTabLayout.newTab().setText(mTitleList.get(0)));//添加tab选项卡
        mMainTabLayout.addTab(mMainTabLayout.newTab().setText(mTitleList.get(1)));
        mMainTabLayout.addTab(mMainTabLayout.newTab().setText(mTitleList.get(2)));
        mMainTabLayout.addTab(mMainTabLayout.newTab().setText(mTitleList.get(3)));
        mMainTabLayout.addTab(mMainTabLayout.newTab().setText(mTitleList.get(4)));


        MyPagerAdapter mAdapter = new MyPagerAdapter(mViewList);
        mMainViewPager.setAdapter(mAdapter);//给ViewPager设置适配器
        mMainTabLayout.setupWithViewPager(mMainViewPager);//将TabLayout和ViewPager关联起来。
        mMainTabLayout.setTabsFromPagerAdapter(mAdapter);//给Tabs设置适配器
    }

    //ViewPager适配器
    class MyPagerAdapter extends PagerAdapter {
        private List<View> mViewList;

        public MyPagerAdapter(List<View> mViewList) {
            this.mViewList = mViewList;
        }

        @Override
        public int getCount() {
            return mViewList.size();//页卡数
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;//官方推荐写法
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViewList.get(position));//添加页卡
            return mViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViewList.get(position));//删除页卡
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);//页卡标题
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

//    @OnClick(R.id.button_run_scheduler)
//    public void onClick() {
//        {
//            Observable.create(new ObservableOnSubscribe<Integer>() {
//                @Override
//                public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                    emitter.onNext(1);
//                    emitter.onNext(2);
//                    emitter.onNext(3);
//                    emitter.onComplete();
//                }
//            }).subscribe(new Observer<Integer>() {
//                @Override
//                public void onSubscribe(Disposable d) {
//                    Log.d(TAG, "subscribe");
//                }
//
//                @Override
//                public void onNext(Integer value) {
//                    Log.d(TAG, "" + value);
//                }
//
//                @Override
//                public void onError(Throwable e) {
//                    Log.d(TAG, "error");
//                }
//
//                @Override
//                public void onComplete() {
//                    Log.d(TAG, "complete");
//                }
//            });
//        }
//    }
}
