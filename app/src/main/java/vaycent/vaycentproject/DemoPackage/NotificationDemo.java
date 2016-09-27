package vaycent.vaycentproject.DemoPackage;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import vaycent.vaycentproject.R;

/**
 * Created by Vaycent on 16/9/9.
 */
public class NotificationDemo extends AppCompatActivity {
    private Button startSmallNotificationBtn,startLargeNotificationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_demo);

        startSmallNotificationBtn=(Button)findViewById(R.id.startSmallNotificationBtn);
        startLargeNotificationBtn=(Button)findViewById(R.id.startLargeNotificationBtn);

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

        nm.notify("test",0,updateNotification);
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

        nm.notify("test",0,updateNotification);
    }
}
