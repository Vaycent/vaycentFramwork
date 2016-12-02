package vaycent.vaycentproject.DemoPackage.OptimizationPackage;

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

    private Button ShowViewStubBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.optimization_demo);

        ShowViewStubBtn = (Button)findViewById(R.id.show_viewstub_btn);
        ShowViewStubBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.show_viewstub_btn:
                ((ViewStub)findViewById(R.id.stub_import)).setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }
}
