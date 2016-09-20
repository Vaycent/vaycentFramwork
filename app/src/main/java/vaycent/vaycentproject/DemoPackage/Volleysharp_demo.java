package vaycent.vaycentproject.DemoPackage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import Request.Request_Helper;
import vaycent.vaycentproject.ApplicationContext;
import vaycent.vaycentproject.R;

/**
 * Created by Vaycent on 16/9/20.
 */
public class Volleysharp_demo extends AppCompatActivity {

    private ApplicationContext appContext;
    private Request_Helper requestHelper;

    private Button get_request_btn,post_request_btn,json_request_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volleysharp_demo);

        appContext = ((ApplicationContext) this.getApplication());
        requestHelper = new Request_Helper(this, appContext);

        initLayout();

    }

    private void initLayout(){
        get_request_btn=(Button)findViewById(R.id.get_request_btn);
        post_request_btn=(Button)findViewById(R.id.post_request_btn);
        json_request_btn=(Button)findViewById(R.id.json_request_btn);

        get_request_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestHelper.testGetRequest();
            }
        });

        post_request_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestHelper.testPostRequest(); //use to test post request and xml dom parser
            }
        });

        json_request_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestHelper.testJsonRequest();
            }
        });

    }

}