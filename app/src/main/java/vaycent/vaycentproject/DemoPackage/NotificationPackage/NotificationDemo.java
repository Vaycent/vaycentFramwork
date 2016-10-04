package vaycent.vaycentproject.DemoPackage.NotificationPackage;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import vaycent.vaycentproject.R;
import vaycent.volleysharp.VolleySharp;



/**
 * Created by Vaycent on 16/9/9.
 */
public class NotificationDemo extends AppCompatActivity {
    private Button smallNotificationBtn,largeNotificationBtn,
            remoteViewNotificationBtn,headSupNotificationBtn,testNotificationLevelBtn,
            startNoKillNotificationBtn,stopNoKillNotificationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_demo);

        smallNotificationBtn=(Button)findViewById(R.id.smallNotificationBtn);
        largeNotificationBtn=(Button)findViewById(R.id.largeNotificationBtn);

        remoteViewNotificationBtn=(Button)findViewById(R.id.remoteViewNotificationBtn);
        headSupNotificationBtn=(Button)findViewById(R.id.headSupNotificationBtn);
        testNotificationLevelBtn=(Button)findViewById(R.id.testNotificationLevelBtn);

        startNoKillNotificationBtn=(Button)findViewById(R.id.startNoKillNotificationBtn);
        stopNoKillNotificationBtn=(Button)findViewById(R.id.stopNoKillNotificationBtn);

        smallNotificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pushSmallLocalNotification();
            }
        });

        largeNotificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pushLargeLocalNotification();

            }
        });



        remoteViewNotificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pushRemoteViewNotification();
            }
        });

        headSupNotificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pushHeadSupNotification();
            }
        });

        testNotificationLevelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testNotificationLevel();
            }
        });


        startNoKillNotificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNoKillNotificationService();
            }
        });

        stopNoKillNotificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopNoKillNotificationService();
            }
        });
    }


    private void pushSmallLocalNotification(){

        NotificationManager nm=(NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification updateNotification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_face_black_24dp)
                .setContentTitle("SmallIcon Notification title")
                .setContentText("SmallIcon Notification message")
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
                .setContentTitle("LargeIcon Notification Title")
                .setContentText("LargeIcon Notification Message")
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND)
                .build();

        nm.notify("largeTest",1,updateNotification);
    }



    private void pushRemoteViewNotification(){

        Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.csdn.net/itachi85/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, mIntent, 0);


        NotificationManager nm=(NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification updateNotification = new Notification.Builder(this)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_face_black_24dp)
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher))
                .setContentTitle("LargeIcon Notification Title")
                .setContentText("LargeIcon Notification Message")
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND)
                .build();
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.remoteview_notification);
        updateNotification.bigContentView = remoteViews;
        //updateNotification.contentView = remoteViews;

        nm.notify("remoteViewTest",3,updateNotification);
    }

    private void pushHeadSupNotification(){
        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setContentTitle("悬挂式通知")
                .setContentText("这是一个悬挂式通知");

        Intent push = new Intent();
        push.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        push.setClass(this, VolleySharp.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, push, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentText("android5.0悬挂式通知")
                .setFullScreenIntent(pi, true);

        NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        nm.notify("headsupTest",4, builder.build());
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void testNotificationLevel(){
        /**
         * VISIBILITY_PUBLIC
         * VISIBILITY_PRIVATE
         * VISIBILITY_SECRET
         * */

        NotificationManager nm=(NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification updateNotification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_face_black_24dp)
                .setContentTitle("Level Notification title")
                .setContentText("Level Notification message")
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setVisibility(Notification.VISIBILITY_SECRET)
                .build();

        nm.notify("levelTest",6,updateNotification);

    }

    private void startNoKillNotificationService(){
        Intent intent = new Intent();
        intent.setClass(NotificationDemo.this,NoKillService.class);
        startService(intent);
    }

    private void stopNoKillNotificationService(){
        Intent intent = new Intent();
        intent.setClass(NotificationDemo.this,NoKillService.class);
        stopService(intent);
    }



}
