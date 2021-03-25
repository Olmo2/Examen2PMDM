package com.olmo.examen2_pmdm;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button crear, buscar , ver, lista;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        crear =  findViewById(R.id.btnCrearNotas);
        crear.setOnClickListener(this);

        buscar =  findViewById(R.id.btnBuscarNota);
        buscar.setOnClickListener(this);

        ver =  findViewById(R.id.btnVerTodasNotas);
        ver.setOnClickListener(this);

        lista =  findViewById(R.id.btnListaNotas);
        lista.setOnClickListener(this);
    }

    /*Para configurar la barra de tareas*/
    @Override public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true; /** true -> el menú ya está visible */
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case  R.id.anadir:
                crear();
                return true;

            case  R.id.buscar:
                buscar();
                return true;
            case  R.id.ver:
                ver();
                return true;


        }

        return super.onOptionsItemSelected(item);
    }
    public void crear (){
        Intent intent=new Intent(this,CrearNota.class);
        startActivity(intent);
    }

    public void buscar (){
        Intent intent=new Intent(this,BuscarNota.class);
        startActivity(intent);
    }

    public void ver (){
        Intent intent=new Intent(this, VerNotas.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnCrearNotas:
             intent  =new Intent(this,CrearNota.class);
                startActivity(intent);
                break;
            case R.id.btnBuscarNota:
                 intent=new Intent(this,BuscarNota.class);
                startActivity(intent);
                break;
            case R.id.btnVerTodasNotas:
                intent=new Intent(this, VerNotas.class);
                startActivity(intent);
                break;
            case  R.id.btnListaNotas:
                intent=new Intent(this, ListaNotas.class);
                startActivity(intent);
                break;
        }
    }
}