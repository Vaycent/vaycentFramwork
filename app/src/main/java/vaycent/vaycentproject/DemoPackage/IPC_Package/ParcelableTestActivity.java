package vaycent.vaycentproject.DemoPackage.IPC_Package;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import vaycent.vaycentproject.R;

public class ParcelableTestActivity extends AppCompatActivity {

    private TextView resultTx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcelable_test);

        resultTx=(TextView)findViewById(R.id.parcelable_test_result_tx);

        if(getIntent() != null && getIntent().getParcelableExtra("userParcelable") != null){
            User user = (User)getIntent().getParcelableExtra("userParcelable");

            String resultStr = "User id:"+user.getId()+"\n";
            resultStr+="User name:"+user.getName()+"\n";
            resultStr+="User age:"+user.getAge()+"\n";

            resultTx.setText(resultStr);
        }
    }
}
