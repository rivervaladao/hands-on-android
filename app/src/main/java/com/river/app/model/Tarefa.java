package com.river.app.model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
        //random date
        Calendar gc = Calendar.getInstance();
        int start= 2016;
        int end = 2017;
        int year =  start + (int)Math.round(Math.random() * (end - start));

        gc.set(gc.YEAR, year);

        start = 1;
        end = gc.getActualMaximum(gc.DAY_OF_YEAR);
        int dayOfYear = start + (int)Math.round(Math.random() * (end - start));

        gc.set(gc.DAY_OF_YEAR, dayOfYear);
        this.quando = gc.getTime();

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
