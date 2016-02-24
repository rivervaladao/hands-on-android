package com.river.app.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by cezar on 21/02/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "TODO_SQLITE_DB";

    static final int DATABASE_VERSION = 2;

    public static class Todo {

        public static final String TABLE_NAME = "todo";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_RESUMO = "resumo";
        public static final String COLUMN_DESC = "descricao";
        public static final String COLUMN_DATA = "data";
        public static final String COLUMN_CATEGORIA = "categoria";

        public static final String[] COLUMNS = new String[]{
                COLUMN_ID,
                COLUMN_RESUMO,
                COLUMN_CATEGORIA,
                COLUMN_DESC,
                COLUMN_DATA
        };
    }
    static final String CREATE_TABLE_TODO =
            " CREATE TABLE " + Todo.TABLE_NAME +
                    " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " resumo TEXT NOT NULL, " +
                    " descricao TEXT, " +
                    " categoria TEXT, " +
                    " data DATETIME);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null,
                DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TODO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int
            oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +Todo.TABLE_NAME);
        onCreate(db);
    }

}
