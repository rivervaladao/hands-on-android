package com.river.app;

import android.content.Context;
import android.database.Cursor;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;
import android.test.RenamingDelegatingContext;
import android.test.suitebuilder.annotation.SmallTest;

import com.river.app.dao.TarefaDao;
import com.river.app.helper.DatabaseHelper;
import com.river.app.model.Tarefa;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by cezar on 23/02/16.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class TarefaDaoInstrumentationTest{
    Context context;

    private TarefaDao dao;

    @Before
    public void setUp() throws Exception {
        Context context = new RenamingDelegatingContext(
                InstrumentationRegistry.getInstrumentation().getTargetContext(), "test_");
        dao = new TarefaDao(context);
    }

    @After
    public void tearDown() throws Exception {
        dao.close();
    }

    @Test
    public void insertsAndRemove() {
        populate();
        Assert.assertEquals(DBTarefas.tarefaList().size(), dao.listarTarefas().size());
        dao.removerTodasAsTarefas();
        Assert.assertEquals(0, dao.listarTarefas().size());

    }
    @Test
    public void getById(){
        Cursor cursor = dao.getDb().query(DatabaseHelper.Todo.TABLE_NAME,DatabaseHelper.Todo.COLUMNS,null,null,null,null,null);
        int _ids=-1;
        String _resume=null;
        if(cursor.moveToFirst()){
            _ids = cursor.getInt(cursor.getColumnIndex("_id"));
            _resume = cursor.getString(cursor.getColumnIndex("resumo"));
            cursor.close();
            Tarefa t = dao.buscarTarefaPorId(_ids);
            Assert.assertNotNull(t);
            Assert.assertEquals(t.getResumo(),_resume);

        }

    }
    public void populate(){
        for (Tarefa tarefa : DBTarefas.tarefaList()) {
            dao.inserir(tarefa);
        }
    }


}
