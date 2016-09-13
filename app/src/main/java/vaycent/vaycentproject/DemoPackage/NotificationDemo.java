package vaycent.vaycentproject.DemoPackage;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vaycent.vaycentproject.R;

/**
 * Created by Vaycent on 16/9/9.
 */
public class NotificationDemo extends AppCompatActivity {
    private Button startNotificationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_demo);

        startNotificationBtn=(Button)findViewById(R.id.startNotificationBtn);
        startNotificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pushLocationNotification1();
            }
        });
    }


    private void pushLocationNotification1(){
//        Intent actiivtyIntent=null;
//        actiivtyIntent=handleIntentByDefault(context,uri,strType);



        NotificationManager nm=(NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);


//        Notification.BigTextStyle bigTextStyle = new Notification.BigTextStyle();
//        bigTextStyle.setBigContentTitle(title);
//        bigTextStyle.bigText(message);

        Notification updateNotification = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
//                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_launcher))//notification
                .setContentTitle("notification title")
                .setContentText("notification message")
//                .setStyle(bigTextStyle)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND)
//                .setContentIntent(PendingIntent.getActivity(this,0,actiivtyIntent,0))
                .build();


        nm.notify("test",0,updateNotification);
    }
}
