package com.river.app.layout;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.river.app.R;
import com.river.app.model.CategoriaTarefa;
import com.river.app.model.Tarefa;

/**
 * Created by cezar on 21/02/16.
 */
public class TarefaDetailFragment extends Fragment {

    public static final String ARG_RESUMO = "resumo";
    public static final String ARG_DESCRICAO = "descricao";
    public static final String ARG_QUANDO = "quando";
    public static final String ARG_CATEGORIA = "categoria";

    private Context mContext;

    //Obrigatorio para FragmentManager
    public TarefaDetailFragment() {
    }

    private  int getBackGroundColor(String categoria){
        int categoriaColor = 0;
        switch (categoria) {
            case "EDUCACAO":
                categoriaColor = mContext.getResources().getColor(R.color.light_blue);
                break;
            case "SAUDE":
                categoriaColor = mContext.getResources().getColor(R.color.light_green);
                break;
            case "COMPRAS":
                categoriaColor = mContext.getResources().getColor(R.color.light_red);
                break;
            case "LAZER":
                categoriaColor = mContext.getResources().getColor(R.color.light_yellow);
                break;
            case  "TRABALHO":
                categoriaColor = mContext.getResources().getColor(R.color.light_orange);
                break;
        }
        return categoriaColor;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setHasOptionsMenu(true);

        mContext = getActivity();

        final View view = inflater.inflate(R.layout.fragment_detail_tarefa, container, false);
        final View holder = view.findViewById(R.id.tarefaDetailHolder);

        final TextView categoriaTextView = (TextView) view.findViewById(R.id.tarefaDetailCategoria);
        final TextView resumeTextView = (TextView) view.findViewById(R.id.tarefaDetailResumo);
        final TextView descricaoTextView = (TextView) view.findViewById(R.id.tarefaDetailDescricao);
        final TextView dateView = (TextView)  view.findViewById(R.id.tarefaData);

        final Bundle args = getArguments();

        final int backGroundColor = getBackGroundColor(args.getString(ARG_CATEGORIA));
        holder.setBackgroundColor(backGroundColor);

        categoriaTextView.setText(args.getString(ARG_CATEGORIA));
        resumeTextView.setText(args.getString(ARG_RESUMO));
        descricaoTextView.setText(args.getString(ARG_DESCRICAO));
        dateView.setText(args.getString(ARG_QUANDO));
        return view;
    }
}
