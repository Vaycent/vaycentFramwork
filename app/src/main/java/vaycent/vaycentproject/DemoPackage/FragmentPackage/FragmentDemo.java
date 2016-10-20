package vaycent.vaycentproject.DemoPackage.FragmentPackage;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import vaycent.vaycentproject.R;

/**
 * Created by Vaycent on 2016/10/19.
 */

public class FragmentDemo extends AppCompatActivity implements View.OnClickListener {
    private ImageButton messageBtn, contactsBtn, cloudBtn, personBtn;
    private ContentFragment contentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_demo);

        initLayout();
    }

    private void initLayout(){
        messageBtn=(ImageButton)findViewById(R.id.message_btn);
        contactsBtn=(ImageButton)findViewById(R.id.contacts_btn);
        cloudBtn=(ImageButton)findViewById(R.id.cloud_btn);
        personBtn=(ImageButton)findViewById(R.id.person_btn);
        
    }

    @Override
    public void onClick(View v) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        switch (v.getId()){
            case R.id.message_btn:

                break;
            case R.id.contacts_btn:

                break;
            case R.id.cloud_btn:

                break;
            case R.id.person_btn:

                break;
            default:
                break;
        }
    }
}



