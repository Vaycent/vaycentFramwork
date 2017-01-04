package vaycent.vaycentproject.DemoPackage.DownloadManagerPackage;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vaycent.vaycentproject.R;

public class DownloadManagerDemo extends AppCompatActivity {

    @BindView(R.id.testDownloadManagerBtn)
    Button mTestDownloadManagerBtn;
    private String name="vaycentAPK.apk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_manager_demo);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.testDownloadManagerBtn)
    public void onClick() {
        final String url ="http://count.liqucn.com/d.php?id=21886&urlos=android&from_type=web";

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You want to download App from\""+ url + "\"?");
        builder.setTitle("Alert");
        builder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog,int which) {

                if(TextUtils.isEmpty(url))
                    return ;

                Intent intent = new Intent(DownloadManagerDemo.this, DownLoadManagerIntentService.class);
                intent.putExtra("downloadurl",url);
                startService(intent);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog,int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

}

