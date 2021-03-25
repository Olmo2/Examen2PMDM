package com.olmo.examen2_pmdm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class VerNotas extends AppCompatActivity {

    private EditText nombre, contenido,tipo;
    private Button anterior, siguiente;
    public ArrayList<Nota> notas;
    public Nota nota;
    public MiBaseDeDatos bd;
    private int posicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_notas);
        inicializar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bd.cerrar();
    }

    private void mostarNota(int position) {

        if ((notas.size() > 0) && (notas.size() > position) && (position >= 0)) {
            nota = notas.get(position);
            nombre.setText(nota.getNombre());
            contenido.setText(nota.getContenido());
            tipo.setText(nota.getTipo());
        }

        if (notas.size() == 0) {
            mostrarMensaje("La lista está vacía");
        }

        if (position == (notas.size() - 1)) {
            siguiente.setEnabled(false);
        } else {
            siguiente.setEnabled(true);
        }

        if (position == 0) {
            anterior.setEnabled(false);
        } else {
            anterior.setEnabled(true);
        }

    }

    public void anteriorNota(View view) {
        posicion--;
        mostarNota(posicion);
    }

    public void siguienteNota(View view) {
        posicion++;
        mostarNota(posicion);
    }

    private void inicializar() {
        nombre = (EditText) findViewById(R.id.etNombre);
        contenido = (EditText) findViewById(R.id.etContenido);
        tipo = (EditText) findViewById(R.id.etTipo);
        anterior = (Button) findViewById(R.id.btnAnteriorNota);
        siguiente = (Button) findViewById(R.id.btnSiguienteNota);

        bd = new MiBaseDeDatos(this);

        notas = bd.obtenerNota();
        posicion = 0;
        mostarNota(posicion);
    }

    private void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

}