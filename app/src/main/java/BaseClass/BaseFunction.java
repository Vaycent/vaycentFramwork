package BaseClass;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by Vaycent on 16/9/7.
 */
public class BaseFunction {

    public static boolean checkPermission(String permission,Context context) {
        PackageManager pm = context.getPackageManager();
        String packageName = context.getPackageName();
        return (PackageManager.PERMISSION_GRANTED == pm.checkPermission(permission, packageName));
    }


}
