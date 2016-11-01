package vaycent.vaycentproject.DemoPackage.IPC_Package;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import vaycent.vaycentproject.R;

public class TCPClientActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int MESSAGE_RECEIVE_NEW_MSG=1;
    private static final int MESSAGE_SOCKET_CONNECTED=2;

    private Button mSendButton;
    private TextView mMessageTextView;
    private EditText mMessageEditText;

    private PrintWriter mPrintWriter;
    private Socket mClientSocket;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            switch (msg.what){
                case MESSAGE_RECEIVE_NEW_MSG:{
                    mMessageTextView.setText(mMessageTextView.getText()+(String)msg.obj);
                    break;
                }
                case MESSAGE_SOCKET_CONNECTED:{
                    mSendButton.setEnabled(true);
                    break;
                }
                default:{
                    break;
                }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMessageTextView=(TextView)findViewById(R.id.msg_container);
        mSendButton=(Button)findViewById(R.id.send);
        mMessageEditText=(EditText)findViewById(R.id.msg);

        Intent service = new Intent(this, TCPServerService.class);
        startService(service);

        new Thread(){
            @Override
            public void run(){
               connectTCPServer();
            }
        }.start();
    }

    @Override
    protected void onDestroy(){
        if(mClientSocket!=null){
            try {
                mClientSocket.shutdownInput();
                mClientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if(v==mSendButton){
            final String msg = mMessageEditText.getText().toString();
            if(!msg==null&&mPrintWriter!=null){
                mPrintWriter.println(msg);
                mMessageEditText.setText("");
                String time = formatDateTime(System.currentTimeMillis());
                final String showedMsg = "self "+time+":"+msg+"\n";
                mMessageTextView.setText(mMessageTextView.getText() + showedMsg);
            }
        }
    }

    @SuppressLint("SimpleDataFormat")
    private String formatDateTime(long time){
        return new SimpleDateFormat("(HH:mm:ss)").format(new Date(time));
    }

    private void connectTCPServer(){
        Socket socket = null;
        while (socket==null){
            try{
                socket = new Socket("localhost",8688);
                mClientSocket = socket;

                OutputStream toServerOutputStream = socket.getOutputStream();
                OutputStreamWriter toServerOutputStreamWriter = new OutputStreamWriter(toServerOutputStream);
                Writer toServerWriter = new BufferedWriter(toServerOutputStreamWriter);
                mPrintWriter = new PrintWriter(toServerWriter,true);

                mHandler.sendEmptyMessage(MESSAGE_SOCKET_CONNECTED);
                System.out.println("connect server success");
            }catch (IOException e){
                e.printStackTrace();
            }

            try{
                //Get message from server
                InputStream toClientInputStream = socket.getInputStream();
                InputStreamReader toClientInputStreamReader = new InputStreamReader(toClientInputStream);
                Reader toClientReader = new BufferedReader(toClientInputStreamReader);
                BufferedReader br = new BufferedReader(toClientReader);

                while (!TCPClientActivity.this.isFinishing()){
                    String msg = br.readLine();
                    System.out.println("receive:"+msg);
                    if(msg!=null){
                        String time = formatDateTime(System.currentTimeMillis());
                        final String showedMsg = "server "+time+":"+msg+"\n";
                        mHandler.obtainMessage(MESSAGE_RECEIVE_NEW_MSG,showedMsg).sendToTarget();
                    }
                }
                System.out.println("quit...");
                mPrintWriter.close();
                br.close();
                socket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }


}
