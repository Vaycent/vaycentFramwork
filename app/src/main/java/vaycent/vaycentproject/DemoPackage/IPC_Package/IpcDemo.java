package vaycent.vaycentproject.DemoPackage.IPC_Package;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vaycent.magicLog.mlog;
import vaycent.vaycentproject.R;

/**
 * Created by Vaycent on 2016/10/27.
 */

public class IPCDemo extends AppCompatActivity{


    private Button CommonBtn,ChatBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mlog.e("onCreate");
        setContentView(R.layout.ipc_demo);

        InitLayout();

        Intent tcpServer = new Intent(this, TCPServerService.class);
        startService(tcpServer);

    }

    private void InitLayout(){
        CommonBtn=(Button)findViewById(R.id.common_btn);
        ChatBtn=(Button)findViewById(R.id.chat_btn);

        CommonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IPC_CommonFragment commonFragment = new IPC_CommonFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fTransaction = fm.beginTransaction();
                fTransaction.replace(R.id.ipc_frame_layout, commonFragment);
                fTransaction.commit();
            }
        });

        ChatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TCP_SocketChatFragment chatFragment = new TCP_SocketChatFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fTransaction = fm.beginTransaction();
                fTransaction.replace(R.id.ipc_frame_layout,chatFragment);
                fTransaction.commit();
            }
        });
    }
}
