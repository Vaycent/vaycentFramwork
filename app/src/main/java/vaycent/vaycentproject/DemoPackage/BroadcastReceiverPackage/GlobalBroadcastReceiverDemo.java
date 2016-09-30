package vaycent.vaycentproject.DemoPackage.BroadcastReceiverPackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Vaycent on 16/9/30.
 */
public class GlobalBroadcastReceiverDemo extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        switch(intent.getAction()){
            case Intent.ACTION_BOOT_COMPLETED:
                Toast.makeText(context,"I know u have launched your phone",Toast.LENGTH_LONG).show();
                Log.e("GlobalBR","I know u have launched your phone");
                break;
            case Intent.ACTION_BATTERY_CHANGED:
                int currLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);  //当前电量
                int total = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 1);      //总电量
                int percent = currLevel * 100 / total;
                Toast.makeText(context,"I know your battery has changed:"+percent,Toast.LENGTH_LONG).show();
                Log.e("GlobalBR","I know your battery has changed:"+percent);
                break;
            case Intent.ACTION_PACKAGE_REMOVED:
                Toast.makeText(context,"I know u have uninstall:"+intent.getDataString(),Toast.LENGTH_LONG).show();
                Log.e("GlobalBR", intent.getDataString()+"has be uninstalled:"+intent.getBooleanExtra(Intent.EXTRA_DATA_REMOVED, false));
                Log.e("GlobalBR", "Is it Reinstall?:"+intent.getBooleanExtra(Intent.EXTRA_REPLACING, false));
                Log.e("GlobalBR","User Id:"+intent.getIntExtra(Intent.EXTRA_UID, 0));
                break;
            case Intent.ACTION_PACKAGE_REPLACED:
                Toast.makeText(context,"I know u have replaced:"+intent.getDataString(),Toast.LENGTH_LONG).show();
                Log.e("GlobalBR", intent.getDataString()+"has be Reinstall:"+intent.getBooleanExtra(Intent.EXTRA_DATA_REMOVED, false));
                break;

            default:
                break;
        }

    }
}
