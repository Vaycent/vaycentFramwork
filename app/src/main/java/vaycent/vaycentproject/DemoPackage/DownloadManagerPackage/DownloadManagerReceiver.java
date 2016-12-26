package vaycent.vaycentproject.DemoPackage.DownloadManagerPackage;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;

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
                    //downComplete(Uri.decode(c.getString(c.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI)))) ;
                    downComplete( c.getString(c.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME))) ;
                } else {
                    //int reason = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_REASON));
                }
            }
        }
        if (intent.getAction().equals(DownloadManager.ACTION_NOTIFICATION_CLICKED)) {
            long[] ids = intent.getLongArrayExtra(DownloadManager.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS);
            //点击通知栏取消下载
            dm.remove(ids);
//            ToastUtil.showShortDefault(mContext, "已经取消下载");
        }
    }
    private void downComplete(String filePath) {
        System.out.println("filePath : " + filePath);
        File _file = new File(filePath.indexOf("file://")!=-1?filePath.substring(filePath.indexOf("://")+3):filePath);
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");// 向用户显示数据
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// 以新压入栈
        intent.addCategory("android.intent.category.DEFAULT");
        Uri abc = Uri.fromFile(_file);
        intent.setDataAndType(abc, "application/vnd.android.package-archive");
        mContext.startActivity(intent);
    }
}
