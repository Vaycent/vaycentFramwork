package vaycent.vaycentproject.DemoPackage.IPC_Package;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

import vaycent.magicLog.mlog;

/**
 * Created by Vaycent on 2016/11/1.
 */

public class TCPServerService extends Service {

    private boolean mISServiceDestroyed = false;
    private String[] mDefindMessages = new String[]{
            "Hello,haha",
            "What's your name?",
            "Today weather is so good",
            "You know, I can talk to multiple people on the same time",
            "Ha ha~~"
    };

    @Override
    public void onCreate(){
        new Thread(new TcpServer()).start();
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy(){
        mISServiceDestroyed=true;
        mlog.e("TCPServerService onDestroy");
        super.onDestroy();
    }

    private class TcpServer implements Runnable{
        @Override
        public void run() {
            ServerSocket serverSocket = null;
            try{
                serverSocket = new ServerSocket(8688);
            }catch (IOException e){
                e.printStackTrace();
                return;
            }
            System.out.println("TcpServer has started and listen to 8688 port");

            while (!mISServiceDestroyed){
                try {
                    final Socket client = serverSocket.accept();
                    System.out.println("Server Accept");
                    new Thread(){
                        @Override
                        public void run(){
                            try{
                                responClient(client);
                            }catch (IOException e){
                                e.printStackTrace();
                            }
                        }
                    }.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private void responClient(Socket client) throws IOException{
        //Use to accept the message from client
        InputStream toServerInputStream = client.getInputStream();
        Reader toServerReader = new InputStreamReader(toServerInputStream);
        BufferedReader in = new BufferedReader(toServerReader);

        //Use to send message to client
        OutputStream toClientOutputStream = client.getOutputStream();
        OutputStreamWriter toClientOutputStreamWriter = new OutputStreamWriter(toClientOutputStream);
        Writer toClientWriter = new BufferedWriter(toClientOutputStreamWriter);
        PrintWriter out = new PrintWriter(toClientWriter);
        out.print("Welcome to this chat!");
        System.out.println("1234567890");

        while(!mISServiceDestroyed){
            String str = in.readLine();
            System.out.println("msg from client:"+str);
            if(str==null){
                break;
            }

            int i = new Random().nextInt(mDefindMessages.length);
            String msg = mDefindMessages[i];
            out.print(msg);
            System.out.println("send:"+msg);
        }
        System.out.println("client quit");
        in.close();
        out.close();
        client.close();

    }


}
