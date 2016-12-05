package vaycent.vaycentproject.DemoPackage.OptimizationPackage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;

import vaycent.vaycentproject.R;

/**
 * Created by Vaycent on 2016/12/1.
 */

public class OptimizationDemo extends AppCompatActivity implements View.OnClickListener{

    private Button visibleViewStubBtn,goneViewStubBtn,wrongMemoryLeakBtn,rightMemoryLeakBtn;
    private Button anrDemoBtn;
    private View myView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.optimization_demo);

        visibleViewStubBtn = (Button)findViewById(R.id.visible_viewstub_btn);
        goneViewStubBtn = (Button)findViewById(R.id.gone_viewstub_btn);
        wrongMemoryLeakBtn = (Button)findViewById(R.id.wrong_memory_leak_btn);
        rightMemoryLeakBtn = (Button)findViewById(R.id.right_memory_leak_btn);
        visibleViewStubBtn.setOnClickListener(this);
        goneViewStubBtn.setOnClickListener(this);
        wrongMemoryLeakBtn.setOnClickListener(this);
        rightMemoryLeakBtn.setOnClickListener(this);

        anrDemoBtn = (Button)findViewById(R.id.anr_demo_btn);
        anrDemoBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.visible_viewstub_btn:
                if(myView==null)
                    myView = ((ViewStub)findViewById(R.id.stub_import)).inflate();
                myView.setVisibility(View.VISIBLE);
                break;
            case R.id.gone_viewstub_btn:
                if(myView!=null)
                    myView.setVisibility(View.GONE);
                break;
            case R.id.wrong_memory_leak_btn:
                MemoryLeakHelper.getInstance(this);
                onBackPressed();
                break;
            case R.id.right_memory_leak_btn:
                MemoryLeakHelper.getApplicationInstance(this);
                onBackPressed();
                break;
            case R.id.anr_demo_btn:
                Intent intent = new Intent();
                intent.setClass(this,AnrDemo.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }


}
