package vaycent.vaycentproject.DemoPackage.DownloadManagerPackage;

import android.app.DownloadManager;
import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.webkit.MimeTypeMap;

import java.io.File;

import vaycent.magicLog.mlog;

/**
 * Created by Vaycent on 2016/12/26.
 */

public class DownLoadManagerIntentService extends IntentService {

    private long mTaskId;
    private DownloadManager downloadManager;

    private final String downloadUrl = "https://s3.amazonaws.com/codota_pages/images/codebrain.gif";
//    private final String fileName = new File(getFilesDir().getAbsolutePath())+"/downloads/";
    private final String fileName = new File(Environment.getExternalStorageDirectory().getPath()) + "/vaycentDownloadMan/";



    public DownLoadManagerIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        downloadAPK(downloadUrl,fileName);
    }


    private void downloadAPK(String versionUrl, String versionName) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(versionUrl));
        request.setDestinationInExternalPublicDir("/download/", versionName);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        request.setVisibleInDownloadsUi(true);
        request.setTitle("Vaycent test download");
        request.setDescription("Vaycent test description");
//        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        request.setAllowedOverRoaming(false);//漫游网络是否可以下载
        //设置文件类型，可以在下载结束后自动打开该文件
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        String mimeString = mimeTypeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(versionUrl));
        request.setMimeType(mimeString);

        downloadManager = (DownloadManager) this.getSystemService(Context.DOWNLOAD_SERVICE);
        mTaskId = downloadManager.enqueue(request);

        this.registerReceiver(receiver,
                new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        mlog.d("versionName:"+versionName);
    }


    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            checkDownloadStatus();
        }

    };


    private void checkDownloadStatus() {

        DownloadManager.Query query = new DownloadManager.Query();

        query.setFilterById(mTaskId);//筛选下载任务，传入任务ID，可变参数

        Cursor c = downloadManager.query(query);

        if (c.moveToFirst()) {

            int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));

            switch (status) {

                case DownloadManager.STATUS_PAUSED:

                    mlog.d(">>>下载暂停");

                case DownloadManager.STATUS_PENDING:

                    mlog.d(">>>下载延迟");

                case DownloadManager.STATUS_RUNNING:

                    mlog.d(">>>正在下载");

                    break;

                case DownloadManager.STATUS_SUCCESSFUL:

                    mlog.d(">>>下载完成");

                    //下载完成安装APK

                    //downloadPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator + versionName;

                    installAPK(new File(fileName));

                    break;

                case DownloadManager.STATUS_FAILED:

                    mlog.d(">>>下载失败");

                    break;

            }

        }

    }



    protected void installAPK(File file) {

        if (!file.exists()) return;

        Intent intent = new Intent(Intent.ACTION_VIEW);

        Uri uri = Uri.parse("file://" + file.toString());

        intent.setDataAndType(uri, "application/vnd.android.package-archive");

        //在服务中开启activity必须设置flag,后面解释

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        this.startActivity(intent);

    }
}
