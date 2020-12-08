package com.example.goodmorning;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class trainDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "trainlist.db"; // название бд
    private static final int SCHEMA = 3; // версия базы данных
    static final String TABLE = "trains"; // название таблицы в бд
    // названия столбцов
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EXE = "exe";

    public trainDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE trains (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME
                + " TEXT, " + COLUMN_EXE + " TEXT);");
        // добавление начальных данных
        db.execSQL("INSERT INTO "+ TABLE +" (" + COLUMN_NAME
                + ", " + COLUMN_EXE  + ") VALUES ('Моя треня', 'Приседания,Бла-бла');");


//        db.execSQL("CREATE TABLE trains (" + COLUMN_ID
//                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME
//                + " TEXT);");
//        // добавление начальных данных
//        db.execSQL("INSERT INTO "+ TABLE +" (" + COLUMN_NAME + ") " +
//                "VALUES ('Приседания');");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }
}

