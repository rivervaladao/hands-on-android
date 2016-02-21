package com.river.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.View;

import com.river.app.model.DBTarefas;
import com.river.app.model.Tarefa;
import com.river.app.model.TarefaDetailFragment;

/**
 * Created by cezar on 20/02/16.
 */
public class MainActivity extends AppCompatActivity {
    private Menu menu;
    private boolean isListView;
    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private TarefaListAdapter mAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                }
            }
        };
        mAdapter.setOnItemClickListener(onItemClickListener);
        mRecyclerView.setAdapter(mAdapter);

    }
}
