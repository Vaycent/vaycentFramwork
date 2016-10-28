package vaycent.vaycentproject.DemoPackage.IPC_Package;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MyService extends Service {
    public class MyServiceImpl extends IMyService.Stub {
        @Override
        public String getValue() throws RemoteException {
            String valStr = "Android/OPhone开发讲义";
            return valStr;
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        MyServiceImpl object = new MyServiceImpl();
        return object;
    }

}