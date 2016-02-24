package com.river.app.layout;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.river.app.R;
import com.river.app.helper.ListViewHelper;
import com.river.app.model.Tarefa;

/**
 * Created by cezar on 20/02/16.
 */
public class TarefaListAdapter extends RecyclerView.Adapter<TarefaListAdapter.ViewHolder> {
    Context mContext;
    OnItemClickListener mItemClickListener;

    public TarefaListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_resumo_tarefa, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Tarefa tarefa = ListViewHelper.getInstance(mContext).tarefaList().get(position);

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

        holder.rowCardView.setCardBackgroundColor(categoriaColor);

    }

    @Override
    public int getItemCount() {
        return ListViewHelper.getInstance(mContext).tarefaList().size();
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    //manipulador para click no item ausente no RecyclerView
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public LinearLayout tarefaHolder;
        public TextView tarefaResumo;
        public TextView tarefaCategoria;
        public TextView tarefaDescricao;
        public TextView tarefaData;

        public CardView rowCardView;

        public ViewHolder(View itemView) {
            super(itemView);
            tarefaHolder = (LinearLayout) itemView.findViewById(R.id.tarefaRowHolder);
            tarefaHolder.setOnClickListener(this);
            //tarefaDescricao = (TextView) itemView.findViewById(R.id.tarefaDescricao);
            tarefaResumo = (TextView) itemView.findViewById(R.id.tarefaResumo);
            rowCardView = (CardView) itemView.findViewById(R.id.tarefaCard);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(itemView, getPosition());
            }
        }

    }
}
