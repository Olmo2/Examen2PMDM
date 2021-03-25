package com.olmo.examen2_pmdm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public abstract class ListaAdaptador extends BaseAdapter {

    private ArrayList<?> notas;
    private int R_layout_IdView;
    private Context contexto;

    public ListaAdaptador(Context contexto, int R_layout_IdView, ArrayList<?> notas) {
        super();
        this.contexto = contexto;
        this.notas = notas;
        this.R_layout_IdView = R_layout_IdView;
    }

    @Override
    public View getView(int posicion, View view, ViewGroup pariente) {
        if (view == null) {
            LayoutInflater vi = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = vi.inflate(R_layout_IdView, null);
        }
        onEntrada (notas.get(posicion), view);
        return view;
    }

    @Override
    public int getCount() {
        return notas.size();
    }

    @Override
    public Object getItem(int posicion) {
        return notas.get(posicion);
    }

    @Override
    public long getItemId(int posicion) {
        return posicion;
    }

    /** Devuelve cada una de las entradas con cada una de las vistas a la que debe de ser asociada
     * @param entrada La entrada que será la asociada a la view. La entrada es del tipo del paquete/handler
     * @param view View particular que contendrá los datos del paquete/handler
     */
    public abstract void onEntrada(Object entrada, View view);
}
