package vaycent.vaycentproject.DemoPackage;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vaycent.vaycentproject.MainActivity;
import vaycent.vaycentproject.R;

/**
 * Created by Vaycent on 16/9/9.
 */
public class NotificationDemo extends AppCompatActivity {
    private Button startSmallNotificationBtn,startLargeNotificationBtn,startNoKillNotificationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_demo);

        startSmallNotificationBtn=(Button)findViewById(R.id.startSmallNotificationBtn);
        startLargeNotificationBtn=(Button)findViewById(R.id.startLargeNotificationBtn);
        startNoKillNotificationBtn=(Button)findViewById(R.id.startNoKillNotificationBtn);

        startSmallNotificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pushSmallLocalNotification();
            }
        });

        startLargeNotificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pushLargeLocalNotification();
            }
        });

        startNoKillNotificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pushCanNotKillNotification();
            }
        });
    }


    private void pushSmallLocalNotification(){

        NotificationManager nm=(NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification updateNotification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_face_black_24dp)
                .setContentTitle("notification title")
                .setContentText("notification message")
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND)
                .build();

        nm.notify("smallTest",0,updateNotification);
    }

    private void pushLargeLocalNotification(){

        NotificationManager nm=(NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification updateNotification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_face_black_24dp)
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher))
                .setContentTitle("notification title")
                .setContentText("notification message")
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND)
                .build();

        nm.notify("largeTest",1,updateNotification);
    }

    private void pushCanNotKillNotification(){

        NotificationManager nm=(NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);


        Intent notificationIntent = new Intent();
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        notificationIntent.setClass(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        // notification.setLatestEventInfo(this,"Play Video Service","",
        // pendingIntent);

        Notification.Builder builder = new Notification.Builder(this);

        builder.setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_face_black_24dp)
                // 　　　　　　　　　　　　　　　　　　　　.setLargeIcon(BitmapFactory.decodeResource(res,
                // R.drawable.i5))//下拉下拉列表里面的图标（大图标）
                // 　　　　　　　.setTicker("this is bitch!")
                // //设置状态栏的显示的信息
                .setWhen(System.currentTimeMillis())// 设置时间发生时间
                .setAutoCancel(true)// 设置可以清除
                .setContentTitle("Send Sms")// 设置下拉列表里的标题
                .setContentText("Keep alive services");// 设置上下文内容
        Notification updateNotification = builder.getNotification();

//        // using id of ticker text as notif id
//        this.startForeground(R.string.app_name, notification);

        nm.notify("largeTest",1,updateNotification);
    }


}
