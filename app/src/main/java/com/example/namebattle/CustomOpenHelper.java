package com.example.namebattle;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;

public class CustomOpenHelper extends SQLiteOpenHelper {

    // データーベースのバージョン
    private static final int DATABASE_VERSION = 1;
    // データーベース名
    private static final String DATABASE_NAME = "CharaList";
    private static final String TABLE_NAME = "CHARACTERS";
    private static final String NAME = "name";
    private static final String JOB = "job";
    private static final String HP = "hp";
    private static final String STR = "str";
    private static final String MP = "mp";
    private static final String DEF = "def";
    private static final String AGI = "agi";
    private static final String LUCK = "luck";
    private static final String  CREATE_AT = "create_at";



    String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + " ("
            + NAME + " TEXT PRIMARY KEY,"
            + JOB + " TEXT,"
            + HP + " INTEGER,"
            + STR + " INTEGER,"
            + MP + " INTEGER,"
            + DEF + " INTEGER,"
            + AGI + " INTEGER,"
            + LUCK + " INTEGER,"
            + CREATE_AT + " INTEGER)";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME ;


    public CustomOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // テーブル作成
        // SQLiteファイルがなければSQLiteファイルが作成される
        db.execSQL(
                SQL_CREATE_ENTRIES
        );

        Log.d("debug", "onCreate(SQLiteDatabase db)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // アップデートの判別
        db.execSQL(
                SQL_DELETE_ENTRIES
        );
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}