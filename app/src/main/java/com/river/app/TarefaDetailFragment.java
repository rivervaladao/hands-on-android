package com.river.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.river.app.model.CategoriaTarefa;
import com.river.app.model.Tarefa;

/**
 * Created by cezar on 21/02/16.
 */
public class TarefaDetailFragment extends Fragment {

    private static final String ARG_RESUMO = "resumo";
    private static final String ARG_DESCRICAO = "descricao";
    private static final String ARG_QUANDO = "quando";
    private static final String ARG_CATEGORIA = "categoria";
    private static Context mContext;
    private static int mBackgroundColor=0;
    //Obrigatorio para FragmentManager
    public TarefaDetailFragment() {
    }

    public static TarefaDetailFragment newInstance(Context context,Tarefa tarefa){
        mContext = context;
        final Bundle args = new Bundle();

        args.putString(ARG_RESUMO,tarefa.getResumo());
        args.putString(ARG_DESCRICAO,tarefa.getDecricao());
        args.putString(ARG_QUANDO, tarefa.getQuando().toString());
        args.putString(ARG_CATEGORIA, tarefa.getCategoria().toString());

        final TarefaDetailFragment fragment = new TarefaDetailFragment();
        fragment.setArguments(args);
        setBackGroundColor(tarefa.getCategoria());
        return fragment;
    }
    private static void setBackGroundColor(CategoriaTarefa categoria){
        int categoriaColor = 0;
        switch (categoria) {
            case EDUCACAO:
                categoriaColor = mContext.getResources().getColor(R.color.light_blue);
                break;
            case SAUDE:
                categoriaColor = mContext.getResources().getColor(R.color.light_green);
                break;
            case COMPRAS:
                categoriaColor = mContext.getResources().getColor(R.color.light_red);
                break;
            case LAZER:
                categoriaColor = mContext.getResources().getColor(R.color.light_yellow);
                break;
            case TRABALHO:
                categoriaColor = mContext.getResources().getColor(R.color.light_orange);
                break;
        }
        TarefaDetailFragment.mBackgroundColor = categoriaColor;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_detail_tarefa, container, false);
        final View holder = view.findViewById(R.id.tarefaDetailHolder);

        holder.setBackgroundColor(mBackgroundColor);

        final TextView categoriaTextView = (TextView) view.findViewById(R.id.tarefaDetailCategoria);
        final TextView resumeTextView = (TextView) view.findViewById(R.id.tarefaDetailResumo);
        final TextView descricaoTextView = (TextView) view.findViewById(R.id.tarefaDetailDescricao);
        final TextView dateView = (TextView)  view.findViewById(R.id.tarefaData);

        final Bundle args = getArguments();
        categoriaTextView.setText(args.getString(ARG_CATEGORIA));
        resumeTextView.setText(args.getString(ARG_RESUMO));
        descricaoTextView.setText(args.getString(ARG_DESCRICAO));
        dateView.setText(args.getString(ARG_QUANDO));
        return view;
    }
}
