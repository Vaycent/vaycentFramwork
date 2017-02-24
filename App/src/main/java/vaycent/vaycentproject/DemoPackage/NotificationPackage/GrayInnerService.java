package vaycent.vaycentproject.DemoPackage.NotificationPackage;

/**
 * Created by vaycent on 2017/2/24.
 */

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * 给 API >= 18 的平台上用的灰色保活手段
 */
public class GrayInnerService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(169, new Notification());
        stopForeground(true);
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

}