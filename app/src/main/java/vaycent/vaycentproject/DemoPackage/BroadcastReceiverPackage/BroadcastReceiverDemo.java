package vaycent.vaycentproject.DemoPackage.BroadcastReceiverPackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import vaycent.vaycentproject.R;


/**
 * Created by Vaycent on 16/9/9.
 */
public class BroadcastReceiverDemo extends AppCompatActivity {

    private IntentFilter networkIntentFilter, timeIntentFilter, customIntentFilter1,customIntentFilter2;

    private NetworkChangeReceiver ncReceiver = new NetworkChangeReceiver();
    private TimeReceiver tReceiver = new TimeReceiver();
    private CustomBroadcastReceiver ctbReceiver = new CustomBroadcastReceiver();
    private CustomOrderedBroadcastReceiver ctobReceiver = new CustomOrderedBroadcastReceiver();

    private TextView timeHourTx,timeMinTx;

    private Button sendBroadcastBtn, sendOrderedBroadcastBtn;
    private final String cunstomBroadcastStr = "vaycent.custom.broadcast";
    private final String cunstomOrderedBroadcastStr = "vaycent.custom.orderedbroadcast";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcast_receiver_demo);

        initLayout();

        initIntentFilter();

        registerMyReceivers();

        getCurrentTime();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterMyReceivers();
    }

    private void initLayout(){
        timeHourTx = (TextView)findViewById(R.id.time_hour_tx);
        timeMinTx = (TextView)findViewById(R.id.time_min_tx);
        sendBroadcastBtn = (Button)findViewById(R.id.send_broadcast_btn);
        sendOrderedBroadcastBtn = (Button)findViewById(R.id.send_orderedbroadcast_btn);

        sendBroadcastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cunstomBroadcastStr);
                sendBroadcast(intent);
            }
        });

        sendOrderedBroadcastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cunstomOrderedBroadcastStr);
                sendOrderedBroadcast(intent,null);
            }
        });
    }

    private void initIntentFilter(){
        networkIntentFilter = new IntentFilter();
        networkIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");

        timeIntentFilter = new IntentFilter();
        timeIntentFilter.addAction(Intent.ACTION_TIME_TICK);
        timeIntentFilter.addAction(Intent.ACTION_TIME_CHANGED);
        timeIntentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED);

        customIntentFilter1 = new IntentFilter();
        customIntentFilter1.addAction(cunstomBroadcastStr);

        customIntentFilter2 = new IntentFilter();
        customIntentFilter2.setPriority(100); //OrderedBroadcast can set priority
        customIntentFilter2.addAction(cunstomOrderedBroadcastStr);
    }

    private void registerMyReceivers(){
        registerReceiver(ncReceiver,networkIntentFilter);
        registerReceiver(tReceiver,timeIntentFilter);
        registerReceiver(ctbReceiver,customIntentFilter1);
        registerReceiver(ctobReceiver,customIntentFilter2);
    }

    private void unregisterMyReceivers(){
        unregisterReceiver(ncReceiver);
        unregisterReceiver(tReceiver);
        unregisterReceiver(ctbReceiver);
        unregisterReceiver(ctobReceiver);
    }

    private void getCurrentTime(){
        Time time = new Time();
        time.setToNow();
        int year = time.year;
        int month = time.month;
        int day = time.monthDay;
        int hour = time.hour;
        int minute = time.minute;
        int sec = time.second;

        timeHourTx.setText("Hour:"+hour);
        timeMinTx.setText("Minute:"+minute);

    }


    class NetworkChangeReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context,Intent intent){
            Toast.makeText(context,"network status has changed", Toast.LENGTH_SHORT).show();
        }
    }

    class TimeReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context,Intent intent){
            if(intent.getAction().equals(Intent.ACTION_TIME_TICK)){
                getCurrentTime();
            }else if(intent.getAction().equals(Intent.ACTION_TIME_CHANGED)){

            }else if(intent.getAction().equals(Intent.ACTION_TIMEZONE_CHANGED)){
                Toast.makeText(context,"Your timezone has changed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    class CustomBroadcastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(cunstomBroadcastStr)){
                Toast.makeText(context,"Received custom Broadcast", Toast.LENGTH_SHORT).show();
            }
        }
    }

    class CustomOrderedBroadcastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(cunstomOrderedBroadcastStr)){
                Toast.makeText(context,"Received custom OrderedBroadcast and abort it", Toast.LENGTH_SHORT).show();
                //Other Receiver can't receive this broadcast again when use this function
                abortBroadcast();
            }
        }
    }
}
