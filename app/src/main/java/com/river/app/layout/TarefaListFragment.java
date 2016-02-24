package com.river.app.layout;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.river.app.R;

public class TarefaListFragment extends Fragment {

    private FloatingActionButton fabAddTarefa;
    private Menu menu;
    private boolean isListView;
    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private TarefaListAdapter mAdapter;
    private CoordinatorLayout coordinatorLayout;
    private Bundle savedInstanceState;
    private TarefaListFragmentListener listener;

    public TarefaListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (TarefaListFragmentListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        this.savedInstanceState = savedInstanceState;

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tarefa_list, container, false);

        //habilita apresentacao do menu
        setHasOptionsMenu(true);

        //recupera na ViewGroup da MainActivity
        coordinatorLayout = (CoordinatorLayout) getActivity().findViewById(R.id.coordinatorLayout);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.list);
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);
        mAdapter = new TarefaListAdapter(getContext());

        TarefaListAdapter.OnItemClickListener onItemClickListener = new TarefaListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                listener.onItemListClick(view, position);
            }
        };
        mAdapter.setOnItemClickListener(onItemClickListener);
        mRecyclerView.setAdapter(mAdapter);
        isListView = true;


        fabAddTarefa = (FloatingActionButton) view.findViewById(R.id.fabAddTarefa);
        fabAddTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onAddEditFABClick();
            }
        });
        return view;
    }

    //show menu
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
        this.menu = menu;
    }

    //handle Menu Options
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

    public interface TarefaListFragmentListener {
        public void onAddEditFABClick();

        public void onItemListClick(View view, int position);
    }
}
