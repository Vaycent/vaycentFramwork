package vaycent.vaycentproject.DemoPackage.RecycleViewPackage;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vaycent.vaycentproject.R;

/**
 * Created by Vaycent on 2016/10/24.
 */

public class RecycleViewDemo extends AppCompatActivity implements View.OnClickListener{

    private Button listBtn, gridBtn, fallsBtn;

    @Override
    protected void onCreate(Bundle saveIntanceState){
        super.onCreate(saveIntanceState);
        setContentView(R.layout.recycleview_demo);

        InitLayout();

    }

    private void InitLayout(){
        listBtn = (Button)findViewById(R.id.list_btn);
        gridBtn = (Button)findViewById(R.id.grid_btn);
        fallsBtn = (Button)findViewById(R.id.falls_btn);

        listBtn.setOnClickListener(this);
        gridBtn.setOnClickListener(this);
        fallsBtn.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.list_btn:
                ListFragment fragment = new ListFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.recycleview_frame_layout,fragment);
                break;
            case R.id.grid_btn:
                break;
            case R.id.falls_btn:
                break;
            default:
                break;
        }
    }


}
