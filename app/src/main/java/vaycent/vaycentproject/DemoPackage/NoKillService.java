package vaycent.vaycentproject.DemoPackage;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.annotation.Nullable;

import vaycent.vaycentproject.MainActivity;
import vaycent.vaycentproject.R;


/**
 * Created by Vaycent on 16/9/28.
 */
public class NoKillService extends Service{
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        pushCanNotKillNotification();

        return START_STICKY;
        /**
         * START_STICKY:
         * Run onStartCommand function again if this service be killed, but no intent
         *
         * START_NOT_STICKY:
         * Service will not start again if be killed, until new intent comes(startService)
         *
         * START_REDELIVER_INTENT
         * Service will onStartCommand again if be killed, and get the last intent
         * */
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
    }



    private void pushCanNotKillNotification(){



        Intent notificationIntent = new Intent();
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        notificationIntent.setClass(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        // notification.setLatestEventInfo(this,"Play Video Service","",
        // pendingIntent);


        NotificationManager nm=(NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);


        Notification updateNotification = new Notification.Builder(this)
//                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_face_black_24dp)
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher))
                .setWhen(System.currentTimeMillis())// 设置时间发生时间
                .setAutoCancel(true)
                .setContentTitle("NoKill Notification Title")// 设置下拉列表里的标题
                .setContentText("NoKill Notification Message")// 设置上下文内容
                .build();
       // nm.notify("nokillTest",2,updateNotification);


//        // using id of ticker text as notif id
        this.startForeground(R.string.app_name, updateNotification);

    }


}
