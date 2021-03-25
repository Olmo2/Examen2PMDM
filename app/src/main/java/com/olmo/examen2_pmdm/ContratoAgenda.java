package com.olmo.examen2_pmdm;

import android.provider.BaseColumns;

public final class ContratoAgenda {

    private ContratoAgenda() {}

    public static class Notas implements BaseColumns {
        public static final String TABLA1= "notas";
        public static final String COLUMNA1="_id";
        public static final String COLUMNA2= "nombre";
        public static final String COLUMNA3= "contenido";
        public static final String COLUMNA4= "tipo";
    }
}
