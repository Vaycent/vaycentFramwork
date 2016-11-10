package vaycent.vaycentproject.DemoPackage.EventPackage;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vaycent.vaycentproject.R;

/**
 * Created by Vaycent on 2016/11/4.
 */

public class EventDemo extends AppCompatActivity {

    private Button testBtn,interruptBtn;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_demo);

        testBtn = (Button)findViewById(R.id.testevent_btn);
        interruptBtn = (Button)findViewById(R.id.interrupt_btn);
        testBtn.setOnClickListener(new ButtonListener());
        interruptBtn.setOnClickListener(new ButtonListener());

    }


    private class ButtonListener implements View.OnClickListener {

        public void onClick(View v) {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction fTransaction = fm.beginTransaction();

            switch(v.getId()){
                case R.id.testevent_btn:
                    TestEventFragment testEventFragment = new TestEventFragment();
                    fTransaction.replace(R.id.event_frame_layout, testEventFragment);
                    fTransaction.commit();
                    break;
                case R.id.interrupt_btn:
                    InterruptResolverFragment interruptResolverFragment = new InterruptResolverFragment();
                    fTransaction.replace(R.id.event_frame_layout,interruptResolverFragment);
                    fTransaction.commit();

                    break;
                default:
                    break;
            }
        }

    }




}
