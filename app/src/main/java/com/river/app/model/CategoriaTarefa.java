package com.river.app.model;

/**
 * Created by cezar on 20/02/16.
 */
public enum CategoriaTarefa {

    SAUDE,TRABALHO,COMPRAS,EDUCACAO,LAZER;

    public static String[] names() {
        CategoriaTarefa[] states = values();
        String[] names = new String[states.length];

        for (int i = 0; i < states.length; i++) {
            names[i] = states[i].name();
        }

        return names;
    }
}
