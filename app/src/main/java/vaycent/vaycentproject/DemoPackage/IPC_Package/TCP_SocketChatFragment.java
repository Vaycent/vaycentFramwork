package vaycent.vaycentproject.DemoPackage.IPC_Package;


import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import vaycent.magicLog.mlog;
import vaycent.vaycentproject.R;



public class TCP_SocketChatFragment extends Fragment{

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
    public void onAttach(Context context){
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle saveIntanceState){
        super.onCreate(saveIntanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.tcpclient_activity,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        mMessageTextView=(TextView)view.findViewById(R.id.msg_container);
        mSendButton=(Button)view.findViewById(R.id.send);
        mMessageEditText=(EditText)view.findViewById(R.id.msg);

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String msg = mMessageEditText.getText().toString();
                if(!TextUtils.isEmpty(msg)&&mPrintWriter!=null){
                    mPrintWriter.println(msg);
                    mMessageEditText.setText("");
                    String time = formatDateTime(System.currentTimeMillis());
                    final String showedMsg = "self "+time+":"+msg+"\n";
                    mMessageTextView.setText(mMessageTextView.getText() + showedMsg);
                }

            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);


        new Thread(){
            @Override
            public void run(){
                connectTCPServer();
            }
        }.start();
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onStop(){
        super.onStop();
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
    }

    @Override
    public void onDestroy(){
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
    public void onDetach(){
        super.onDetach();
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

                String testMsg = br.readLine();
                mlog.d("testMsg:"+testMsg);




                IpcDemo ipcDemo = (IpcDemo)getActivity();
                mlog.d("ipcDemo.isFinishing():"+ipcDemo.isFinishing());
                while (!ipcDemo.isFinishing()){
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
