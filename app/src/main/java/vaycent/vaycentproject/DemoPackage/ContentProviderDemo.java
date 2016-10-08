package vaycent.vaycentproject.DemoPackage;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import vaycent.vaycentproject.R;


/**
 * Created by Vaycent on 2016/10/8.
 */

public class ContentProviderDemo extends AppCompatActivity {

    private ListView contactsListView;
    private ArrayAdapter<String> contactsAdapter;
    private List<String> contactsList = new ArrayList<String>();

    private final int contactsPermissionRequestCode=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_provider_demo);

        contactsListView=(ListView)findViewById(R.id.contacts_listview);

        checkPermissions(Manifest.permission.READ_CONTACTS,contactsPermissionRequestCode);

        bindContacts();
    }

    private void bindContacts(){
        contactsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contactsList);
        contactsListView.setAdapter(contactsAdapter);

        Cursor cursor = null;
        try{
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
            while(cursor.moveToNext()){
                String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contactsList.add(displayName+"\n"+number);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cursor!=null)
                cursor.close();
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == contactsPermissionRequestCode) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted , Access contacts here or do whatever you need.
                Log.e("123456","HERE 123");
                bindContacts();

            }
        }
    }

    private void checkPermissions(String permission,int requestCode) {
        if ( Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }

        int permissionCheckStatus = ContextCompat.checkSelfPermission(this, permission);

        boolean isGranted=permissionCheckStatus == PackageManager.PERMISSION_GRANTED?true:false;
        boolean shouldShowPermission= ActivityCompat.shouldShowRequestPermissionRationale(this, permission);
        System.out.println("shouldShowPermission:"+shouldShowPermission);
        if (isGranted){
            return;
        } else{
            requestPermissions(new String[]{permission}, requestCode);
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", okListener)
//				.setPositiveButton("Cancel",okListener)
                .create()
                .show();
    }

}
