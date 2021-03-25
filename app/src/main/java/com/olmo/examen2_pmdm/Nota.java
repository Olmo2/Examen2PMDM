package com.olmo.examen2_pmdm;

public class Nota {

    int id;
    String nombre;
    String contenido;
    String tipo;

    public Nota(int _id,String _nombre,String _contenido,String _tipo){
        id=_id;
        nombre=_nombre;
        contenido=_contenido;
        tipo =_tipo;
    }

    public int getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public String getContenido(){
        return contenido;
    }

    public String getTipo() {return tipo; }

    @Override
    public String toString() {
        return nombre;
    }

}
