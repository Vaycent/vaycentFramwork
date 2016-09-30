package vaycent.vaycentproject.DemoPackage.BroadcastReceiverPackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.widget.TextView;
import android.widget.Toast;

import vaycent.vaycentproject.R;


/**
 * Created by Vaycent on 16/9/9.
 */
public class BroadcastReceiverDemo extends AppCompatActivity {

    private IntentFilter networkIntentFilter, timeIntentFilter;

    private NetworkChangeReceiver ncReceiver = new NetworkChangeReceiver();
    private TimeReceiver tReceiver = new TimeReceiver();

    private TextView timeHourTx,timeMinTx;

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
    }


    private void initIntentFilter(){
        networkIntentFilter = new IntentFilter();
        networkIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");

        timeIntentFilter = new IntentFilter();
        timeIntentFilter.addAction(Intent.ACTION_TIME_TICK);
        timeIntentFilter.addAction(Intent.ACTION_TIME_CHANGED);
        timeIntentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED);

    }

    private void registerMyReceivers(){
        registerReceiver(ncReceiver,networkIntentFilter);
        registerReceiver(tReceiver,timeIntentFilter);
    }

    private void unregisterMyReceivers(){
        unregisterReceiver(ncReceiver);
        unregisterReceiver(tReceiver);
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
}
