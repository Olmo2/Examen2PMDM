package com.olmo.examen2_pmdm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MiBaseDeDatos extends SQLiteOpenHelper {

    private static final String NOMBRE_BD = "Agenda.sqlite3";
    private static final int VERSION_BD = 2;
    private static final String CREAR_TABLA1 =
            "CREATE TABLE "+ ContratoAgenda.Notas.TABLA1 + "(" +
                    ContratoAgenda.Notas.COLUMNA1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ContratoAgenda.Notas.COLUMNA2 + " TEXT," +
                    ContratoAgenda.Notas.COLUMNA4 + " TEXT," +
                    ContratoAgenda.Notas.COLUMNA3 +" TEXT)";
    private SQLiteDatabase bd;

    public MiBaseDeDatos(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
        bd = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL(CREAR_TABLA1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int versionAnterior, int versionNueva) {
        bd.execSQL("DROP TABLE IF EXISTS " + ContratoAgenda.Notas.TABLA1);
        onCreate(bd);
    }

    public void insertarNota(String nombre,String contenido,String tipo){
        ContentValues cv = new ContentValues();
        cv.put(ContratoAgenda.Notas.COLUMNA2, nombre);
        cv.put(ContratoAgenda.Notas.COLUMNA3, contenido);
        cv.put(ContratoAgenda.Notas.COLUMNA4, tipo);
        bd.insert(ContratoAgenda.Notas.TABLA1, null, cv);
    }

    public void actualizarNota(int id, String nombre, String contenido,String tipo){
        ContentValues cv = new ContentValues();
        cv.put(ContratoAgenda.Notas.COLUMNA2, nombre);
        cv.put(ContratoAgenda.Notas.COLUMNA3, contenido);
        cv.put(ContratoAgenda.Notas.COLUMNA4, tipo);
        bd.update(ContratoAgenda.Notas.TABLA1, cv,ContratoAgenda.Notas.COLUMNA1 + " = " + id,null);
    }

    public void borrarNota(int id) {
        bd.delete(ContratoAgenda.Notas.TABLA1, ContratoAgenda.Notas.COLUMNA1 + " = " + id, null);
    }

    public void cerrar() {
        bd.close();
    }

    public ArrayList<Nota> obtenerNota(){
        ArrayList<Nota> notas=new ArrayList<Nota>();
        String sentenciaSql = "SELECT " + ContratoAgenda.Notas.COLUMNA1 + ", " +
                ContratoAgenda.Notas.COLUMNA2 + ", " + ContratoAgenda.Notas.COLUMNA3 + ", " + ContratoAgenda.Notas.COLUMNA4 +
                " FROM " + ContratoAgenda.Notas.TABLA1;
        Cursor c = bd.rawQuery(sentenciaSql, null);

        if (c != null && c.getCount()>0) {
            c.moveToFirst();
            do {
                int id=c.getInt(c.getColumnIndex(ContratoAgenda.Notas.COLUMNA1));
                String nombre = c.getString(c.getColumnIndex(ContratoAgenda.Notas.COLUMNA2));
                String contenido = c.getString(c.getColumnIndex(ContratoAgenda.Notas.COLUMNA3));
                String tipo = c.getString(c.getColumnIndex(ContratoAgenda.Notas.COLUMNA4));
                Nota nota =new Nota(id,nombre,contenido,tipo);
                notas.add(nota);
            } while (c.moveToNext());
        }
        c.close();
        return notas;
    }

}

