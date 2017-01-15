package vaycent.vaycentproject.DemoPackage.DownloadManagerPackage;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.widget.Toast;

import java.io.File;

/**
 * Created by Vaycent on 2016/12/26.
 */

public class DownloadManagerReceiver extends BroadcastReceiver {

    private Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        DownloadManager dm = (DownloadManager) mContext
                .getSystemService(mContext.DOWNLOAD_SERVICE);

        if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
            long downId = intent.getLongExtra(
                    DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            Cursor c = dm.query(new DownloadManager.Query().setFilterById(downId));
            if (c.moveToFirst()) {
                int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
                if (status == DownloadManager.STATUS_SUCCESSFUL) {
                    downCompleted(c.getString(c.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME))) ;
                } else {
                    int reason = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_REASON));
                }
            }
        }
        if (intent.getAction().equals(DownloadManager.ACTION_NOTIFICATION_CLICKED)) {
            long[] ids = intent.getLongArrayExtra(DownloadManager.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS);
            dm.remove(ids);
            Toast.makeText(mContext,"Cancel this download",Toast.LENGTH_SHORT).show();
        }
    }
    private void downCompleted(String filePath) {
        File _file = new File(filePath.indexOf("file://")!=-1?filePath.substring(filePath.indexOf("://")+3):filePath);
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory("android.intent.category.DEFAULT");
        Uri abc = Uri.fromFile(_file);
        intent.setDataAndType(abc, "application/vnd.android.package-archive");
        mContext.startActivity(intent);
    }
}
