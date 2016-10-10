package vaycent.vaycentproject.DemoPackage.ContentProviderPackage;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import BaseClass.BaseValue;
import vaycent.magicLog.mlog;

/**
 * Created by Vaycent on 2016/10/10.
 */

public class MyProvider extends ContentProvider {

    public static final int BOOK_DIR=0;
    public static final int BOOK_ITEM=1;
    public static final int CATEGORY_DIR=2;
    public static final int CATEGORY_ITEM=3;

    private static UriMatcher uriMatcher;

    private static final String PROVIDER_AUTHORITY="vaycent.vaycentproject.provider";

    private MySQLiteOpenHelper dbHelper;

    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_AUTHORITY,"book",BOOK_DIR);
        uriMatcher.addURI(PROVIDER_AUTHORITY,"book/#",BOOK_ITEM);
        uriMatcher.addURI(PROVIDER_AUTHORITY,"category",CATEGORY_DIR);
        uriMatcher.addURI(PROVIDER_AUTHORITY,"category/#",CATEGORY_ITEM);
    }

    @Override
    public boolean onCreate() {
        mlog.d("MyProvider onCreate");
        dbHelper = new MySQLiteOpenHelper(getContext(),"BookStore.db",null, BaseValue.DATABASE_VERSION);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        System.out.println("MyProvider uri:"+uri);
        System.out.println("uriMatcher.match(uri):"+uriMatcher.match(uri));

        Cursor cursor = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                cursor = db.query("Book",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case BOOK_ITEM:
                String bookId= uri.getPathSegments().get(1);
                cursor = db.query("Book",projection,"id = ?",new String[]{bookId},null,null,sortOrder);
                break;
            case CATEGORY_DIR:
                break;
            case CATEGORY_ITEM:
                break;
            default:
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {

        Uri uriReturn=null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                long newBookId = db.insert("Book",null,values);
                uriReturn = Uri.parse("content://"+PROVIDER_AUTHORITY+"/Book/"+newBookId);
                break;
            case BOOK_ITEM:
                break;
            case CATEGORY_DIR:
                break;
            case CATEGORY_ITEM:
                break;
            default:
                break;
        }
        return uriReturn;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        int deleteRows=0;

        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                deleteRows=db.delete("Book",selection,selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                deleteRows=db.delete("Book","id = ?",new String[]{bookId});
                break;
            case CATEGORY_DIR:
                break;
            case CATEGORY_ITEM:
                break;
            default:
                break;
        }
        return deleteRows;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        int updateRows=0;

        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                updateRows=db.update("Book",values,"id = ?", new String[]{bookId});
                break;
            case CATEGORY_DIR:
                break;
            case CATEGORY_ITEM:
                break;
            default:
                break;
        }
        return updateRows;
    }


    @Nullable
    @Override
    public String getType(Uri uri) {
        mlog.d("MyProvider getType:"+uri);
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                return "vnd.android.cursor.dir/vnd."+PROVIDER_AUTHORITY+".book";
            case BOOK_ITEM:
                return "vnd.android.cursor.item/vnd."+PROVIDER_AUTHORITY+".book";
            case CATEGORY_DIR:
                return "vnd.android.cursor.dir/vnd."+PROVIDER_AUTHORITY+".category";
            case CATEGORY_ITEM:
                return "vnd.android.cursor.item/vnd."+PROVIDER_AUTHORITY+".category";
            default:
                break;
        }
        return null;
    }
}
