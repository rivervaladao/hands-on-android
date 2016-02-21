package com.river.app.model;

import java.util.Date;

/**
 * Created by cezar on 20/02/16.
 */
public class Tarefa {
    private String decricao;
    private Date quando;
    private String resumo;
    private CategoriaTarefa categoria;

    public Tarefa() { }

    public Tarefa(String decricao, String resumo, CategoriaTarefa categoria) {
        this();
        this.decricao = decricao;
        this.resumo = resumo;
        this.categoria = categoria;

    }

    public String getDecricao() {
        return decricao;
    }

    public void setDecricao(String decricao) {
        this.decricao = decricao;
    }

    public Date getQuando() {
        return quando;
    }

    public void setQuando(Date quando) {
        this.quando = quando;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public CategoriaTarefa getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaTarefa categoria) {
        this.categoria = categoria;
    }
}
