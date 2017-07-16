package vaycent.vaycentproject.DemoPackage.LanguageTest;

import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.Locale;

import vaycent.vaycentproject.R;

/**
 * Created by vaycent on 2017/7/16.
 */

public class LanguageTestActivity extends AppCompatActivity {

    private TextView mMainTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_test);

        mMainTv = (TextView)findViewById(R.id.tv_main);

        mMainTv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        mMainTv.getPaint().setAntiAlias(true);


        lanTest();

    }


    private void lanTest(){
        Locale locale = Locale.getDefault();
        Log.e("Vaycent",locale.getLanguage() + "-" + locale.getCountry());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            LocaleList localeList = getResources().getConfiguration().getLocales();
            for (int i = 0; i < localeList.size(); i++) {
                Log.e("Vaycent",i + " >1> " + localeList.get(i).getLanguage() + "-" + localeList.get(i).getCountry());
            }

            LocaleList localeList2 = LocaleList.getAdjustedDefault();
            for (int i = 0; i < localeList2.size(); i++) {
                Log.e("Vaycent",i + " >2> " + localeList2.get(i).getLanguage() + "-" + localeList2.get(i).getCountry());
            }

            LocaleList localeList3 = LocaleList.getDefault();
            for (int i = 0; i < localeList3.size(); i++) {
                Log.e("Vaycent",i + " >3> " + localeList3.get(i).getLanguage() + "-" + localeList3.get(i).getCountry());
            }

            LocaleList localeList4 = LocaleList.getEmptyLocaleList();
            for (int i = 0; i < localeList4.size(); i++) {
                Log.e("Vaycent",i + " >4> " + localeList4.get(i).getLanguage() + "-" + localeList4.get(i).getCountry());
            }
        }
    }
}
