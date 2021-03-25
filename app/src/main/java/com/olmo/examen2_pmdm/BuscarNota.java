package com.olmo.examen2_pmdm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class BuscarNota extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText nombre, contenido, tipo;
    private Spinner spinNotas;
    private Button eliminar, editar, guardar, cancelar;
    private ArrayAdapter spinnerAdapter;
    public ArrayList<Nota> notas;
    public Nota nota;
    public MiBaseDeDatos bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_nota);
        inicializar();
        desactivarVistas();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bd.cerrar();
        mostrarMensaje("Base de datos cerrada");
    }

    private void inicializar(){

        nombre = (EditText) findViewById(R.id.etNombre);
        contenido = (EditText) findViewById(R.id.etContenido);
        tipo = (EditText) findViewById(R.id.etTipo);
        spinNotas = (Spinner) findViewById(R.id.spinnerNotas);
        eliminar = (Button) findViewById(R.id.btnEliminarNota);
        editar = (Button) findViewById(R.id.btnEditarNota);
        guardar = (Button) findViewById(R.id.btnGuardarNota);
        cancelar = (Button) findViewById(R.id.btnCancelarEdicion);

        bd = new MiBaseDeDatos(this);
        mostrarMensaje("Base de datos abierta");
        notas = bd.obtenerNota();

        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, notas);
        spinNotas.setAdapter(spinnerAdapter);
        spinNotas.setOnItemSelectedListener(this);
    }

    private void desactivarVistas(){

        nombre.setEnabled(false);
        contenido.setEnabled(false);
        tipo.setEnabled(false);
        editar.setEnabled(false);
        guardar.setEnabled(false);
        eliminar.setEnabled(false);
        cancelar.setEnabled(false);
    }

    private void recargarSpinner(){

        notas = bd.obtenerNota();
        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, notas);
        spinNotas.setAdapter(spinnerAdapter);
        nota=null;
    }

    public void verNota(View view) {

        if (nota != null) {
            nombre.setText(nota.getNombre());
            contenido.setText(nota.getContenido());
            tipo.setText(nota.getTipo());
            editar.setEnabled(true);
            eliminar.setEnabled(true);
        } else {
            mostrarMensaje("No hay ninguna nota");
        }
    }

    public void editarNota(View view) {
        nombre.setEnabled(true);
        contenido.setEnabled(true);
        tipo.setEnabled(true);
        editar.setEnabled(false);
        guardar.setEnabled(true);
        eliminar.setEnabled(false);
        cancelar.setEnabled(true);
    }

    public void guardarNota(View view) {
        if ((nombre.getText().length()>0) && (contenido.getText().length()>0) && (tipo.getText().length()>0)) {
            bd.actualizarNota(nota.getId(), nombre.getText().toString(), contenido.getText().toString(),tipo.getText().toString());
            mostrarMensaje("Nota con Id: " + nota.getId() + " actualizada");
            recargarSpinner();
            vaciarCampos();
            desactivarVistas();
        }else{
            mostrarMensaje("Debes rellenar el nombre , contenido de la nota y el tipo ;)");
        }
    }

    public void borrarNota(View view) {
        if (nota != null) {
            mostrarAlertaConfirmacion();
            mostrarMensaje("Nota borrada");
        }
    }

    private void vaciarCampos(){
        nombre.setText("");
        contenido.setText("");
        tipo.setText("");
    }

    private void mostrarMensaje(String mensaje){
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    private void mostrarAlertaConfirmacion() {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("CONFIRMACIÓN DE BORRADO");
        alerta.setMessage("¿Quieres borrar la NOTA?");
        alerta.setCancelable(false);
        alerta.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
                aceptarBorrar();
            }
        });
        alerta.setNegativeButton("NO", null);
        alerta.create();
        alerta.show();
    }

    private void aceptarBorrar() {
        bd.borrarNota(nota.getId());
        recargarSpinner();
        vaciarCampos();
        desactivarVistas();
    }

    public void cancelarEdicion(View view) {
        vaciarCampos();
        desactivarVistas();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.spinnerNotas) {
            if (notas.size() > 0) {
                nota = notas.get(position);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

}
