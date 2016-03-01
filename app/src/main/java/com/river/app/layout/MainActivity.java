package com.river.app.layout;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.river.app.R;
import com.river.app.helper.ListViewHelper;
import com.river.app.model.Tarefa;

/**
 * Created by cezar on 20/02/16.
 */
public class MainActivity extends AppCompatActivity implements
        TarefaListFragment.TarefaListFragmentListener,
        AddEditFragment.AddEditFragmentListener {

    private static final String FRAGMENT_KEY = "tarefaListFragment";
    private View fragmentContainer;

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

        if (savedInstanceState == null && findViewById(R.id.phoneFragmentContainer) != null) {
            fragmentContainer = findViewById(R.id.phoneFragmentContainer);
            Fragment tarefaListFragment = new TarefaListFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(fragmentContainer.getId(), tarefaListFragment, FRAGMENT_KEY)
                    .commit();
        } else {
            fragmentContainer = findViewById(R.id.tabletFragmentContainer);
        }

    }

    private void showAddEditFragment(int viewID) {
        AddEditFragment addEditFragment = new AddEditFragment();

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


    }

    @Override
    public void onAddEditFABClick() {
        if (findViewById(R.id.phoneFragmentContainer) != null)
            showAddEditFragment(R.id.phoneFragmentContainer);
        else //tablet put to rigth side
            showAddEditFragment(R.id.rightPaneContainer);

    }

    @Override
    public void onItemListClick(View view, int position) {
        if (findViewById(R.id.phoneFragmentContainer) != null)
            showTarefaDetailFragment(R.id.phoneFragmentContainer, position);
        else
            showTarefaDetailFragment(R.id.rightPaneContainer, position);
    }
}


