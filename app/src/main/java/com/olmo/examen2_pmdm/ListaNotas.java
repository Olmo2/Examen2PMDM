package com.olmo.examen2_pmdm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class ListaNotas extends AppCompatActivity {


    private EditText nombre, contenido,tipo;
    private Button anterior, siguiente;
    public ArrayList<Nota> notas;
    public Nota nota;
    public MiBaseDeDatos bd;
    private int posicion;
     ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);
        inicializar();
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
        list=findViewById(R.id.listView);
        list.setAdapter(new ListaAdaptador(this, R.layout.entrada, notas){
            @Override
            public void onEntrada(Object entrada, View view) {
                if (entrada != null) {
                    TextView tvNombre = (TextView) view.findViewById(R.id.tvNombre);
                    if (tvNombre != null)
                        tvNombre.setText(((Nota) entrada).getNombre());


                }
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
                Nota elegido = (Nota) pariente.getItemAtPosition(posicion);

                CharSequence texto = "ID: " + elegido.getId() +"\nNOMBRE: "+ elegido.getNombre() +"\nCONTENIDO: "+ elegido.getContenido()+"\nTIPO: "+ elegido.getTipo();
              /*  Toast toast = Toast.makeText(com.olmo.listaanimales.MainActivity.this, texto, Toast.LENGTH_LONG);
                toast.show();*/
                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaNotas.this);
                alerta.setTitle("NOTA ELEGIDA");
                alerta.setMessage(texto);
                alerta.create();
                alerta.show();

               /* Intent intent = new Intent(ListaNotas.this, NotaInfo.class);
                intent.putExtra("nota", (Serializable) elegido);
                startActivity(intent);*/
            }
        });
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
    private void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}