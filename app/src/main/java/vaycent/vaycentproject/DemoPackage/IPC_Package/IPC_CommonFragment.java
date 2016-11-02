package vaycent.vaycentproject.DemoPackage.IPC_Package;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import vaycent.magicLog.mlog;
import vaycent.vaycentproject.R;

/**
 * Created by Vaycent on 2016/11/2.
 */

public class IPC_CommonFragment extends Fragment {

    private File appPath=new File(Environment.getExternalStorageDirectory().getPath()+"/cache.txt");

    private Button SerialOutBtn,SerialInBtn,ParcelTestBtn;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.ipc_common_fragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState){
        SerialOutBtn=(Button)view.findViewById(R.id.serializable_out);
        SerialInBtn=(Button)view.findViewById(R.id.serializable_in);
        ParcelTestBtn=(Button)view.findViewById(R.id.parcelable_test);

        super.onViewCreated(view,savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){

        SerialOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SerializeOut();
            }
        });

        SerialInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SerializeIn();
            }
        });

        ParcelTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParcelableTest();
            }
        });



        super.onActivityCreated(savedInstanceState);
    }

    private void SerializeOut(){
        if (!appPath.exists()) {
            try {
                appPath.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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

    private void SerializeIn(){
        IPCDemo ipcDemo= (IPCDemo)getActivity();
        Intent intent = new Intent();
        intent.setClass(ipcDemo,SerializableTestActivity.class);
        startActivity(intent);
    }

    private void ParcelableTest(){
        User user = new User(0,"tom",21);
        IPCDemo ipcDemo = (IPCDemo)getActivity();
        Intent intent = new Intent(ipcDemo, ParcelableTestActivity.class);
        intent.putExtra("userParcelable", user);
        startActivity(intent);
    }

}
