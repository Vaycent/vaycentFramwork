package vaycent.vaycentproject.DemoPackage.TestPackage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vaycent.magicLog.mlog;
import vaycent.vaycentproject.R;

/**
 * Created by Vaycent on 2016/10/27.
 */

public class TestActivity2 extends AppCompatActivity {
    private Button switchToActivity3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mlog.e("onCreate");
        setContentView(R.layout.activity_main);

        switchToActivity3=(Button)findViewById(R.id.switchToActivity2);
        switchToActivity3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(TestActivity2.this, TestActivity3.class);
                startActivity(intent);
            }
        });
    }
}
