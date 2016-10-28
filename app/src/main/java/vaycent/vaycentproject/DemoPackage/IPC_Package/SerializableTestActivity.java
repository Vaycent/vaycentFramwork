package vaycent.vaycentproject.DemoPackage.IPC_Package;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import vaycent.vaycentproject.R;

public class SerializableTestActivity extends AppCompatActivity {

    private TextView resultTx;

    private File appPath=new File(Environment.getExternalStorageDirectory().getPath()+"/cache.txt");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serializable_test);

        resultTx=(TextView)findViewById(R.id.serializable_test_result_tx);

        DeserializeIn();
    }

    private void DeserializeIn(){

        Thread deserializeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    FileInputStream inputStream = new FileInputStream(appPath);
                    ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                    Person person = (Person)objectInputStream.readObject();
                    objectInputStream.close();

                    SetResultText(person);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        deserializeThread.start();

    }

    private void SetResultText(Person person){
        String resultStr = "Person id:"+person.getId()+"\n";
        resultStr+="Person name:"+person.getName()+"\n";
        resultStr+="Person age:"+person.getAge()+"\n";

        resultTx.setText(resultStr);
    }
}
