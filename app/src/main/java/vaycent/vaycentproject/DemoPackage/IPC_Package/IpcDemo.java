package vaycent.vaycentproject.DemoPackage.IPC_Package;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import vaycent.magicLog.mlog;
import vaycent.vaycentproject.R;

/**
 * Created by Vaycent on 2016/10/27.
 */

public class IPCDemo extends AppCompatActivity implements View.OnClickListener{

    private File appPath=new File(Environment.getExternalStorageDirectory().getPath()+"/cache.txt");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mlog.e("onCreate");
        setContentView(R.layout.ipc_demo);

//        appPath=new File(getApplicationContext().getFilesDir().getAbsolutePath()+"/cache.txt");
        InitLayout();
    }

    private void InitLayout(){
        findViewById(R.id.serializable_out).setOnClickListener(this);
        findViewById(R.id.serializable_in).setOnClickListener(this);
        findViewById(R.id.parcelable_test).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.serializable_out:
                SerializeOut();
                break;
            case R.id.serializable_in:
                Intent intent = new Intent();
                intent.setClass(IPCDemo.this,SerializableTestActivity.class);
                startActivity(intent);
                break;
            case R.id.parcelable_test:
                ParcelableTest();
                break;
        }
    }

    private void SerializeOut(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Person person = new Person(0,"jack",19);
                try{
                    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(appPath));
                    out.writeObject(person);
                    out.close();
                    mlog.d("Finish Serializable output");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void ParcelableTest(){
        User user = new User(0,"tom",21);
        Intent intent = new Intent(this, ParcelableTestActivity.class);
        intent.putExtra("userParcelable", user);
        startActivity(intent);
    }
}
