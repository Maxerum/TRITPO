package com.example.goodmorning;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {
    //4
    private static String DB_PATH; // полный путь к базе данных
    private static String DB_NAME = "food1.db";
    private static final int SCHEMA = 1; // версия базы данных
    static final String TABLE = "foodTable"; // название таблицы в бд
    // названия столбцов
    static final String COLUMN_ID = "_id";
    static final String COLUMN_NAME = "product";
    static final String COLUMN_YEAR = "calories";
    private Context myContext;

    DatabaseHelper(Context context) {
        super(context, DB_NAME, null, SCHEMA);
        this.myContext=context;
        DB_PATH =context.getFilesDir().getPath() + DB_NAME;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
    }

    void create_db(){
        InputStream myInput = null;
        OutputStream myOutput = null;
        try {
            File file = new File(DB_PATH);
            if (!file.exists()) {
                this.getReadableDatabase();
                //получаем локальную бд как поток
                myInput = myContext.getAssets().open(DB_NAME);
                // Путь к новой бд
                String outFileName = DB_PATH;

                // Открываем пустую бд
                myOutput = new FileOutputStream(outFileName);

                // побайтово копируем данные
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }

                myOutput.flush();
                myOutput.close();
                myInput.close();
            }
        }
        catch(IOException ex){
            Log.d("DatabaseHelper", ex.getMessage());
        }
    }
    SQLiteDatabase open()throws SQLException {

        return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
    }
//    private static String DB_NAME = "food.db";
//    private static String DB_PATH = "";
//    private static final int DB_VERSION = 2;
//
//    private SQLiteDatabase mDataBase;
//    private final Context mContext;
//    private boolean mNeedUpdate = false;
//
//    public DatabaseHelper(Context context) {
//        super(context, DB_NAME, null, DB_VERSION);
//        if (android.os.Build.VERSION.SDK_INT >= 17)
//            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
//        else
//            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
//        this.mContext = context;
//
//        copyDataBase();
//
//        this.getReadableDatabase();
//    }
//
//    public void updateDataBase() throws IOException {
//        if (mNeedUpdate) {
//            File dbFile = new File(DB_PATH + DB_NAME);
//            if (dbFile.exists())
//                dbFile.delete();
//
//            copyDataBase();
//
//            mNeedUpdate = false;
//        }
//    }
//
//    private boolean checkDataBase() {
//        File dbFile = new File(DB_PATH + DB_NAME);
//        return dbFile.exists();
//    }
//
//    private void copyDataBase() {
//        if (!checkDataBase()) {
//            this.getReadableDatabase();
//            this.close();
//            try {
//                copyDBFile();
//            } catch (IOException mIOException) {
//                throw new Error("ErrorCopyingDataBase");
//            }
//        }
//    }
//
//    private void copyDBFile() throws IOException {
//        InputStream mInput = mContext.getAssets().open(DB_NAME);
//        //InputStream mInput = mContext.getResources().openRawResource(R.raw.info);
//        OutputStream mOutput = new FileOutputStream(DB_PATH + DB_NAME);
//        byte[] mBuffer = new byte[1024];
//        int mLength;
//        while ((mLength = mInput.read(mBuffer)) > 0)
//            mOutput.write(mBuffer, 0, mLength);
//        mOutput.flush();
//        mOutput.close();
//        mInput.close();
//    }
//
//    public boolean openDataBase() throws SQLException {
//        mDataBase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
//        return mDataBase != null;
//    }
//
//    @Override
//    public synchronized void close() {
//        if (mDataBase != null)
//            mDataBase.close();
//        super.close();
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        if (newVersion > oldVersion)
//            mNeedUpdate = true;
//    }
}
