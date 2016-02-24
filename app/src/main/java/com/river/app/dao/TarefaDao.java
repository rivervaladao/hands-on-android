package com.river.app.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.river.app.helper.DatabaseHelper;
import com.river.app.model.Tarefa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cezar on 23/02/16.
 */
public class TarefaDao {

    private DatabaseHelper helper;
    private SQLiteDatabase db;

    public TarefaDao(Context context) {
        helper = new DatabaseHelper(context);
    }

    public SQLiteDatabase getDb() {
        if (db == null) {
            db = helper.getWritableDatabase();
        }
        return db;
    }
    public void close() {
        helper.close();
    }

    private Tarefa criarTarefa(Cursor cursor) {
        Tarefa tarefa = new Tarefa(
                cursor.getLong(cursor.getColumnIndex(
                        DatabaseHelper.Todo.COLUMN_ID)),

                cursor.getString(cursor.getColumnIndex(
                        DatabaseHelper.Todo.COLUMN_RESUMO)),

                cursor.getString(cursor.getColumnIndex(
                        DatabaseHelper.Todo.COLUMN_DESC)),

                cursor.getString(cursor.getColumnIndex(
                        DatabaseHelper.Todo.COLUMN_CATEGORIA)),

                new Date(cursor.getLong(cursor.getColumnIndex(
                        DatabaseHelper.Todo.COLUMN_DATA)))
        );
        return tarefa;
    }

    public List<Tarefa> listarTarefas() {
        Cursor cursor = getDb().query(DatabaseHelper.Todo.TABLE_NAME,
                DatabaseHelper.Todo.COLUMNS,
                null, null, null, null, null);

        List<Tarefa> tarefas = new ArrayList<Tarefa>();

        while (cursor.moveToNext()) {
            Tarefa tarefa = criarTarefa(cursor);
            tarefas.add(tarefa);
        }
        cursor.close();
        return tarefas;
    }

    public Tarefa buscarTarefaPorId(Integer id) {

        Cursor cursor = getDb().query(DatabaseHelper.Todo.TABLE_NAME,
                DatabaseHelper.Todo.COLUMNS,
                DatabaseHelper.Todo.COLUMN_ID + " = ?",
                new String[]{id.toString()},
                null, null, null);

        if (cursor.moveToNext()) {
            Tarefa tarefa = criarTarefa(cursor);
            cursor.close();
            return tarefa;
        }

        return null;
    }

    public long inserir(Tarefa tarefa) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.Todo.COLUMN_RESUMO,
                tarefa.getResumo());

        values.put(DatabaseHelper.Todo.COLUMN_DESC,
                tarefa.getDecricao());

        values.put(DatabaseHelper.Todo.COLUMN_CATEGORIA,
                tarefa.getCategoria().toString());

        values.put(DatabaseHelper.Todo.COLUMN_DATA,
                tarefa.getQuando().getTime());

        return getDb().insert(DatabaseHelper.Todo.TABLE_NAME,
                null, values);
    }

    public boolean removerTarefa(Long id) {
        String whereClause = DatabaseHelper.Todo.COLUMN_ID + " = ?";
        String[] whereArgs = new String[]{id.toString()};
        int removidos = getDb().delete(DatabaseHelper.Todo.TABLE_NAME,
                whereClause, whereArgs);
        return removidos > 0;
    }

    public void removerTodasAsTarefas() {
        getDb().execSQL("DELETE FROM "+DatabaseHelper.Todo.TABLE_NAME);
    }
}
