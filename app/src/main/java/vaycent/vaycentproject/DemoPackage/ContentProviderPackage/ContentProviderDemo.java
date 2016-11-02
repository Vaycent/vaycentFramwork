package vaycent.vaycentproject.DemoPackage.ContentProviderPackage;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import vaycent.magicLog.mlog;
import vaycent.vaycentproject.R;


/**
 * Created by Vaycent on 2016/10/8.
 */

public class ContentProviderDemo extends AppCompatActivity {

    private Button contactsBtn,insertBtn,queryAllBtn,deleteAllBtn,
            updateBtn,deleteBtn,queryBtn;
    private ListView stringListView;
    private ArrayAdapter<String> stringArrayAdapter;
    private List<String> stringList = new ArrayList<String>();

    private final int contactsPermissionRequestCode=0;

    private final String providerUri="content://vaycent.vaycentproject.provider";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_provider_demo);

        initLayout();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == contactsPermissionRequestCode) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getContactsResolver();
            }
        }
    }


    private void initLayout(){
        stringListView=(ListView)findViewById(R.id.string_listview);
        stringArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,stringList);
        stringListView.setAdapter(stringArrayAdapter);



        contactsBtn=(Button)findViewById(R.id.contacts_btn);
        insertBtn=(Button)findViewById(R.id.insert_btn);
        queryAllBtn=(Button)findViewById(R.id.query_all_btn);
        deleteAllBtn=(Button)findViewById(R.id.delete_all_btn);

        updateBtn=(Button)findViewById(R.id.update_btn);
        deleteBtn=(Button)findViewById(R.id.delete_btn);
        queryBtn=(Button)findViewById(R.id.query_btn);


        contactsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermissions(Manifest.permission.READ_CONTACTS,contactsPermissionRequestCode);
            }
        });

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertMyProvider();
            }
        });

        queryAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                queryAllMyProvider();
            }
        });


        deleteAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAllProvider();
            }
        });




        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMyProvider();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteMyProvider();
            }
        });

        queryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                queryMyProvider();
            }
        });
    }



    private void insertMyProvider(){
        Uri uri = Uri.parse(providerUri+"/book");
        for(int i=0;i<3;i++){
            ContentValues values = new ContentValues();
            values.put("name","A Clash of Kings "+i);
            values.put("author","George Martin "+i);
            values.put("pages",1040+i);
            values.put("price",10.23+i);
            Uri newUri = getContentResolver().insert(uri,values);
            mlog.d("insert:"+newUri);
        }

    }

    private void deleteAllProvider(){
        Uri uri = Uri.parse(providerUri+"/book/");
        getContentResolver().delete(uri,null,null);
    }

    private void queryAllMyProvider(){
        stringList.clear();

        Uri uri = Uri.parse(providerUri+"/book");
        Cursor cursor = null;
        try{
            cursor= getContentResolver().query(uri,null,null,null,null);
            if(cursor!=null){
                while (cursor.moveToNext()){
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String author = cursor.getString(cursor.getColumnIndex("author"));
                    String pages = cursor.getString(cursor.getColumnIndex("pages"));
                    String price = cursor.getString(cursor.getColumnIndex("price"));
                    stringList.add("name:"+name+"\nauthor:"+author+"\npages:"+pages+"\nprice:"+price);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            stringArrayAdapter.notifyDataSetChanged();
            if(cursor!=null)
                cursor.close();
        }

    }



    private void updateMyProvider(){
        Uri uri = Uri.parse(providerUri+"/book/"+1);
        ContentValues values = new ContentValues();
        values.put("name","This is my favorite book");
        values.put("author","Me");
        values.put("pages",1);
        values.put("price",99.99);
        int updateRows = getContentResolver().update(uri,values,null,null);
        mlog.d("update rows:"+updateRows);
    }

    private void deleteMyProvider(){
        Uri uri = Uri.parse(providerUri+"/book/"+3);
        getContentResolver().delete(uri,null,null);
    }

    private void queryMyProvider(){
        stringList.clear();

        Uri uri = Uri.parse(providerUri+"/book/"+1);
        Cursor cursor = null;
        try{
            cursor= getContentResolver().query(uri,null,null,null,null);
            if(cursor!=null){
                while (cursor.moveToNext()){
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String author = cursor.getString(cursor.getColumnIndex("author"));
                    String pages = cursor.getString(cursor.getColumnIndex("pages"));
                    String price = cursor.getString(cursor.getColumnIndex("price"));
                    stringList.add("name:"+name+"\nauthor:"+author+"\npages:"+pages+"\nprice:"+price);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            stringArrayAdapter.notifyDataSetChanged();
            if(cursor!=null)
                cursor.close();
        }
    }


    private void getContactsResolver(){
        stringList.clear();

        Cursor cursor = null;
        try{
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
            while(cursor.moveToNext()){
                String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                stringList.add(displayName+"\n"+number);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            stringArrayAdapter.notifyDataSetChanged();
            if(cursor!=null)
                cursor.close();
        }
    }

    private void checkPermissions(String permission, int requestCode) {
        if ( Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            getContactsResolver();
            return;
        }

        int permissionCheckStatus = ContextCompat.checkSelfPermission(this, permission);

        boolean isGranted=permissionCheckStatus == PackageManager.PERMISSION_GRANTED?true:false;
        boolean shouldShowPermission= ActivityCompat.shouldShowRequestPermissionRationale(this, permission);
        System.out.println("shouldShowPermission:"+shouldShowPermission);
        if (isGranted){
            getContactsResolver();
            return;
        } else{
            requestPermissions(new String[]{permission}, requestCode);
        }
        //showMessageOKCancel
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
