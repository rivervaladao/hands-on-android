package com.river.app.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by cezar on 21/02/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "HANDS_ON_DB";

    public static final String TODO_TABLE_NAME="todo";

    static final int DATABASE_VERSION= 1;

    static final String CREATE_DB_TABLE =
            " CREATE TABLE " + TODO_TABLE_NAME +
                    " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " resumo TEXT NOT NULL, " +
                    " descricao TEXT NOT NULL, " +
                    " data TEXT NOT NULL, " +
                    " happiness INT NOT NULL);";
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null,
                DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_DB_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int
            oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS " +_TABLE_NAME);
        onCreate(db);
    }
}
