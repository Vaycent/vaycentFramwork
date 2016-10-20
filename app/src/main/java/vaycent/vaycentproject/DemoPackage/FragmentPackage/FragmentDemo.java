package vaycent.vaycentproject.DemoPackage.FragmentPackage;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import vaycent.vaycentproject.R;

/**
 * Created by Vaycent on 2016/10/19.
 */

public class FragmentDemo extends AppCompatActivity {
    private ImageButton messageBtn, contactsBtn, cloudBtn, personBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_demo);

        initLayout();

        //It will change the text in content fragment if run this func
        getContentFragmentFunc();
    }

    private void initLayout(){
        messageBtn=(ImageButton)findViewById(R.id.message_btn);
        contactsBtn=(ImageButton)findViewById(R.id.contacts_btn);
        cloudBtn=(ImageButton)findViewById(R.id.cloud_btn);
        personBtn=(ImageButton)findViewById(R.id.person_btn);

        messageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MessageFragment messageFragment = new MessageFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fTransaction = fm.beginTransaction();
                fTransaction.replace(R.id.frame_layout, messageFragment);
                fTransaction.commit();
            }
        });

        contactsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContactsFragment contactsFragment = new ContactsFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fTransaction = fm.beginTransaction();
                fTransaction.replace(R.id.frame_layout, contactsFragment);
                fTransaction.commit();
            }
        });

        cloudBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CloudFragment cloudFragment = new CloudFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fTransaction = fm.beginTransaction();
                fTransaction.replace(R.id.frame_layout, cloudFragment);
                fTransaction.commit();
            }
        });

        personBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PersonFragment personFragment = new PersonFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fTransaction = fm.beginTransaction();
                fTransaction.replace(R.id.frame_layout, personFragment);
                fTransaction.commit();
            }
        });

    }

    private void getContentFragmentFunc(){
        ContentFragment contentFragment = (ContentFragment) getFragmentManager().findFragmentById(R.id.id_fragment_content);
        View view = contentFragment.getView() ;
        TextView textView = (TextView)findViewById(R.id.content_fragment_tx);
        String textStr="I have changed this content fragment text";
        textView.setText(textStr);
    }

}



