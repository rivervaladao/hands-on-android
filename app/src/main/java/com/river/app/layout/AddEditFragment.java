package com.river.app.layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.river.app.R;
import com.river.app.model.CategoriaTarefa;

/**
 * Created by cezar on 23/02/16.
 */
public class AddEditFragment extends Fragment {

    private AddEditFragmentListener listener; // MainActivity
    private boolean addingNewTask = true; // adding (true) or editing
    private final View.OnClickListener saveTaskButtonClicked =
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // hide the virtual keyboard
                    ((InputMethodManager) getActivity().getSystemService(
                            Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                            getView().getWindowToken(), 0);
                    saveTask(); // save contact to the database
                }
            };

    private TextInputLayout resumeTextInputLayout;
    private TextInputLayout descriptionTextInputLayout;
    //private TextInputLayout categoryTextInputLayout;
    private String selectedCategory;
    private TextInputLayout dateTextInputLayout;
    private AppCompatSpinner categorySpinner;
    private FloatingActionButton saveTaskFAB;
    private CoordinatorLayout coordinatorLayout;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (AddEditFragmentListener) context;
    }

    // remove AddEditFragmentListener when Fragment detached
    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //super.onCreateView(inflater, container, savedInstanceState);
        setHasOptionsMenu(false);

        View view = inflater.inflate(R.layout.fragment_add_edit, container, false);

        categorySpinner = (AppCompatSpinner) view.findViewById(R.id.categorySpinner);

        categorySpinner.setAdapter(
                new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item, CategoriaTarefa.names()));

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCategory = (String) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //nothing
            }
        });

        //categoryTextInputLayout = (TextInputLayout) view.findViewById(R.id.categoryTextInputLayout);

        resumeTextInputLayout = (TextInputLayout) view.findViewById(R.id.resumeTextInputLayout);

        descriptionTextInputLayout = (TextInputLayout) view.findViewById(R.id.descriptionTextInputLayout);

        dateTextInputLayout = (TextInputLayout) view.findViewById(R.id.dateTextInputLayout);

        //aplicando mascara na data



        saveTaskFAB = (FloatingActionButton) view.findViewById(R.id.editButton);
        saveTaskFAB.setOnClickListener(saveTaskButtonClicked);

        coordinatorLayout = (CoordinatorLayout) getActivity().findViewById(R.id.coordinatorLayout);
        Bundle arguments = getArguments();

        if (arguments != null) {
            addingNewTask = false;
        }

        return view;
    }

    private void saveTask() {
        // create ContentValues object containing contact's key-value pairs
        /*
        ContentValues contentValues = new ContentValues();
        contentValues.put(Tarefa.COLUMN_CATEGORY,
                categoryTextInputLayout.getEditText().getText().toString());
        contentValues.put(Tarefa.COLUMN_RESUME,
                resumeTextInputLayout.getEditText().getText().toString());
        contentValues.put(Tarefa.COLUMN_DESCRIPTION,
                descriptionTextInputLayout.getEditText().getText().toString());
        contentValues.put(Tarefa.COLUMN_DATE,
                dateTextInputLayout.getEditText().getText().toString());
        */
        if (addingNewTask) {
            listener.onAddEditCompleted(null);
            /*
            Uri newTaskUri = getActivity().getContentResolver().insert(
                    Todo.CONTENT_URI, contentValues);

            if (newTaskUri != null) {
                Snackbar.make(coordinatorLayout,
                        R.string.task_added, Snackbar.LENGTH_LONG).show();
                listener.onAddEditCompleted(newTaskUri);
            }else {
                Snackbar.make(coordinatorLayout,
                        R.string.task_not_added, Snackbar.LENGTH_LONG).show();
            }
            */
        } else {
            listener.onAddEditCompleted(null);
            /*
            int updatedRows = getActivity().getContentResolver().update(
                    taskUri, contentValues, null, null);
            if (updatedRows > 0) {
                listener.onAddEditCompleted(taskUri);
                Snackbar.make(coordinatorLayout,
                        R.string.task_updated, Snackbar.LENGTH_LONG).show();
            }
            else {
                Snackbar.make(coordinatorLayout,
                        R.string.task_not_updated, Snackbar.LENGTH_LONG).show();
            }
            */
        }
    }

    public interface AddEditFragmentListener {
        public void onAddEditCompleted(Uri taskUri);
    }

}
