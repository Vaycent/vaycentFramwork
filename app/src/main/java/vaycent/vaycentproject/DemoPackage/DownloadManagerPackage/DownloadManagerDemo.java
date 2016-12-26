package vaycent.vaycentproject.DemoPackage.DownloadManagerPackage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vaycent.vaycentproject.R;

public class DownloadManagerDemo extends AppCompatActivity {

    @BindView(R.id.testDownloadManagerBtn)
    Button mTestDownloadManagerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_manager_demo);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.testDownloadManagerBtn)
    public void onClick() {
        Intent intent = new Intent(this, DownLoadManagerIntentService.class);
        startService(intent);
    }





}
