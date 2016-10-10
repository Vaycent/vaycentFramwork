package vaycent.vaycentproject.DemoPackage.ContentProviderPackage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Vaycent on 2016/10/10.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private Context mContext;

    private static final String CREATE_BOOK = "create table Book ("
            +"id integer primary key autoincrement, "
            +"author text, "
            +"price real, "
            +"pages integer, "
            +"name text)";

    private static final String CREATE_CATEGORY="create table Category ("
            +"id integer primary key autoincrement, "
            +"category_name text, "
            +"category_code integer)";

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
