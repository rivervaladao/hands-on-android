package com.river.app.helper;

import android.content.Context;

import com.river.app.dao.TarefaDao;
import com.river.app.model.Tarefa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cezar on 23/02/16.
 */
public class ListViewHelper {

    private ListViewHelper(){};
    private TarefaDao tarefaDao;
    private static Context mContext;

    public static ListViewHelper getInstance(Context context){
        ListViewHelper.mContext = context;
        return new ListViewHelper();
    }
    public List<Tarefa> tarefaList() {
        return new TarefaDao(mContext).listarTarefas();
    }
}
