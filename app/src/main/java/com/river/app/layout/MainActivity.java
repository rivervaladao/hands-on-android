package com.river.app.layout;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.river.app.R;
import com.river.app.dao.TarefaDao;
import com.river.app.helper.ListViewHelper;
import com.river.app.model.Tarefa;

/**
 * Created by cezar on 20/02/16.
 */
public class MainActivity extends AppCompatActivity implements
        TarefaListFragment.TarefaListFragmentListener,
        AddEditFragment.AddEditFragmentListener {

    private static final String FRAGMENT_KEY = "tarefaListFragment";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (toolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setElevation(5);
        }
        Fragment tarefaListFragment = new TarefaListFragment();

        View container = findViewById(R.id.fragmentContainer);

        if (savedInstanceState == null && container != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(container.getId(), tarefaListFragment, FRAGMENT_KEY)
                    .commit();
        }

    }

    private void showAddEditFragment(int viewID) {
        AddEditFragment addEditFragment = new AddEditFragment();
        getSupportActionBar().hide();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(viewID, addEditFragment)
                .addToBackStack(null)
                .commit();

    }

    private void showTarefaDetailFragment(int viewID, int position) {
        TarefaDetailFragment tarefaDetailFragment = new TarefaDetailFragment();

        final Bundle args = new Bundle();
        //recuperar dados da celula
        Tarefa tarefa = ListViewHelper.getInstance(this).tarefaList().get(position);

        args.putString(TarefaDetailFragment.ARG_RESUMO, tarefa.getResumo());
        args.putString(TarefaDetailFragment.ARG_DESCRICAO, tarefa.getDecricao());
        args.putString(TarefaDetailFragment.ARG_QUANDO, tarefa.getQuando().toString());
        args.putString(TarefaDetailFragment.ARG_CATEGORIA, tarefa.getCategoria().toString());

        tarefaDetailFragment.setArguments(args);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(viewID, tarefaDetailFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onAddEditCompleted(Uri taskUri) {
        getSupportFragmentManager().popBackStack();
        //update list with notifyDataSetChanged
        getSupportActionBar().show();

    }

    @Override
    public void onAddEditFABClick() {
        showAddEditFragment(R.id.fragmentContainer);
    }

    @Override
    public void onItemListClick(View view, int position) {
        showTarefaDetailFragment(R.id.fragmentContainer, position);
    }
}


