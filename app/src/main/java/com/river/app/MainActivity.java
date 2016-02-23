package com.river.app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.river.app.model.DBTarefas;
import com.river.app.model.Tarefa;

/**
 * Created by cezar on 20/02/16.
 */
public class MainActivity extends AppCompatActivity {
    private Menu menu;
    private boolean isListView;
    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private TarefaListAdapter mAdapter;
    FloatingActionButton fab;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(toolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setElevation(7);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);
        mAdapter = new TarefaListAdapter(this);
        TarefaListAdapter.OnItemClickListener onItemClickListener = new TarefaListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (savedInstanceState == null) {
                    final Tarefa tarefa = DBTarefas.tarefaList().get(position);

                    final TarefaDetailFragment tarefaDetailFragment = TarefaDetailFragment.newInstance(MainActivity.this,tarefa);

                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_layout, tarefaDetailFragment, "tarefaDeatailFragment")
                            .addToBackStack(null)
                            .commit();
                    //ESCONDE
                    fab.hide();
                }
            }
        };
        mAdapter.setOnItemClickListener(onItemClickListener);
        mRecyclerView.setAdapter(mAdapter);
        isListView = true;

        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(findViewById(R.id.main_layout), "I'm a Snackbar", Snackbar.LENGTH_LONG).setAction("Action", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Snackbar Action", Toast.LENGTH_LONG).show();
                    }
                }).show();
            }
        });

        fab.show();

    }

    //show menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_toggle) {
            toggle();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void toggle() {
        MenuItem item = menu.findItem(R.id.action_toggle);
        if (isListView) {
            mStaggeredLayoutManager.setSpanCount(2);
            item.setIcon(R.drawable.ic_action_list);
            item.setTitle("Show as list");
            isListView = false;
        } else {
            mStaggeredLayoutManager.setSpanCount(1);
            item.setIcon(R.drawable.ic_action_grid);
            item.setTitle("Show as grid");
            isListView = true;
        }
    }

    @Override
    public void onBackPressed() {
        //mostra FAB
        fab.show();
        super.onBackPressed();
    }
}
