package com.usmp.fia.pisimikhuy2.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.usmp.fia.pisimikhuy2.entity.Comida;
import com.usmp.fia.pisimikhuy2.util.BDPlato;
import com.usmp.fia.pisimikhuy2.util.Constantes;

import java.util.ArrayList;

public class DAOPlato {
    BDPlato dbPlato;
    SQLiteDatabase database;

    public DAOPlato(Context context){
        dbPlato=new BDPlato(context);
    }

    public void openDB(){
        database=dbPlato.getWritableDatabase();
    }

    public void close(){
        dbPlato.close();
        database.close();
    }

    public void registrarPlato(Comida comida){
        try{
            ContentValues values=new ContentValues();
            values.put("idFoto",comida.getIdFoto());
            values.put("cantidad",comida.getCantidad());
            values.put("nombre",comida.getNombre());
            values.put("descripcion",comida.getDescripcion());
            values.put("precio",comida.getPrecio());

            database.insert(Constantes.NOMBRETABLA, null, values);
        } catch (Exception e) {

        }
    }
    public void editarPlato(Comida comida) {
        try{
            ContentValues values=new ContentValues();
            values.put("nombre",comida.getNombre());
            values.put("descripcion",comida.getDescripcion());
            values.put("precio",comida.getPrecio());
            database.update(Constantes.NOMBRETABLA,values,"id=?",
                    new String[]{comida.getId() + ""});
            //database.update(Constantes.NOMBRETABLA,values,"id="+persona.getId(),null);
        } catch (Exception e) {

        }
    }

    public void eliminarPlato(int id){
        try{
            database.delete(Constantes.NOMBRETABLA,"id="+id,null);
        } catch (Exception e) {

        }
    }

    public ArrayList<Comida> getComida(){
        ArrayList<Comida> listaComida=new ArrayList<>();
        try{
            Cursor c=database.rawQuery("select * from "+
                    Constantes.NOMBRETABLA,null);
            while(c.moveToNext()){
                listaComida.add(new Comida(c.getInt(0),c.getInt(1),c.getInt(2),c.getString(3),c.getString(4),c.getDouble(5)));
            }
        } catch (Exception e) {

        }
        return listaComida;
    }
}
