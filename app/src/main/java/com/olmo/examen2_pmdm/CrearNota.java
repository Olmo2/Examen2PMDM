package com.olmo.examen2_pmdm;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CrearNota extends AppCompatActivity {

    private EditText nombre,contenido , tipo;
    public ArrayList<Nota> notas;
    public MiBaseDeDatos bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_nota);
        inicializar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bd.cerrar();
        mostrarMensaje("Base de datos cerrada");
    }

    private void inicializar(){
        nombre=(EditText)findViewById(R.id.etNombre);
        contenido=(EditText)findViewById(R.id.etContenido);
        tipo=(EditText)findViewById(R.id.etTipo);
        bd=new MiBaseDeDatos(this);
        mostrarMensaje("Base de datos abierta");
        notas=bd.obtenerNota();
    }

    public void crearNota (View view) {
        if ((nombre.getText().length()>0) && (contenido.getText().length()>0) && (tipo.getText().length()>0)) {
            bd.insertarNota(nombre.getText().toString(), contenido.getText().toString(),tipo.getText().toString());
            mostrarMensaje("Nota creada con éxito");
            limpiar();
        }
        else {
            mostrarMensaje("Debes introducir el nombre, contenido de la nota y un tipo ;)");
        }
    }

    private void mostrarMensaje(String mensaje){
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    private void limpiar(){
        contenido.clearFocus();
        nombre.setFocusable(true);
        nombre.requestFocus();
        nombre.setText("");
        contenido.setText("");
        tipo.setText("");
    }

}