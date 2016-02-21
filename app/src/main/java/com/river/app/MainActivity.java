package com.river.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;

/**
 * Created by cezar on 20/02/16.
 */
public class MainActivity extends Activity {
    private Menu menu;
    private boolean isListView;
    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private TarefasListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);
        mAdapter = new TarefasListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }
}
