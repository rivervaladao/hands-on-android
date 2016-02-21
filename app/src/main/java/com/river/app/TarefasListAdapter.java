package com.river.app;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.river.app.model.DBTarefas;
import com.river.app.model.Tarefa;

/**
 * Created by cezar on 20/02/16.
 */
public class TarefasListAdapter extends RecyclerView.Adapter<TarefasListAdapter.ViewHolder> {
    Context mContext;

    public TarefasListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_resumo_tarefa, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Tarefa tarefa = DBTarefas.tarefaList().get(position);
        holder.tarefaResumo.setText(tarefa.getResumo());
        int categoriaColor = 0;
        switch (tarefa.getCategoria()) {
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
        //holder.tarefaCategoriaImage.setImageDrawable(categoriaColor);
        holder.rowCardView.setCardBackgroundColor(categoriaColor);
        ;
    }

    @Override
    public int getItemCount() {
        return DBTarefas.tarefaList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tarefaResumo;
        public TextView tarefaCategoria;
        public TextView tarefaDescricao;
        public CardView rowCardView;

        public ViewHolder(View itemView) {
            super(itemView);
            //tarefaDescricao = (TextView) itemView.findViewById(R.id.tarefaDescricao);
            tarefaResumo = (TextView) itemView.findViewById(R.id.tarefaResumo);
            rowCardView = (CardView) itemView.findViewById(R.id.tarefaCard);
        }
    }
}
