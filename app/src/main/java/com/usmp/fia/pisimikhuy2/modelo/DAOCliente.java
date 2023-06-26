package com.usmp.fia.pisimikhuy2.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.usmp.fia.pisimikhuy2.entity.Cliente;
import com.usmp.fia.pisimikhuy2.util.BDCliente;
import com.usmp.fia.pisimikhuy2.util.ConstantesCliente;

import java.util.ArrayList;

public class DAOCliente {

    BDCliente dbCliente;
    SQLiteDatabase database1;

    public DAOCliente(Context context){
        dbCliente=new BDCliente(context);
    }

    public void openDB(){
        database1=dbCliente.getWritableDatabase();
    }

    public void close(){
        dbCliente.close();
        database1.close();
    }

    public void registrarCliente(Cliente cliente){
        try{
            ContentValues values=new ContentValues();
            values.put("idFotoCli",cliente.getIdFoto());
            values.put("platosTotales",cliente.getPlatosTotales());
            values.put("platosFaltantes",cliente.getPlatosFaltantes());
            values.put("premio",cliente.getPremio());
            values.put("documentoIdentidad",cliente.getDocumentoIdentidad());
            values.put("apellidoPaterno",cliente.getApellidoPaterno());
            values.put("apellidoMaterno",cliente.getApellidoMaterno());
            values.put("nombres",cliente.getNombres());
            values.put("genero",cliente.getGenero());
            values.put("fechaNacimiento",cliente.getFechaNacimiento());
            values.put("correo",cliente.getCorreo());
            values.put("contra",cliente.getContra());
            values.put("numeroDocumento",cliente.getNumeroDocumento());
            database1.insert(ConstantesCliente.CLIENTETABLA,null, values);
        } catch (Exception e) {

        }
    }

    public void editarCliente(Cliente cliente){
        try{
            ContentValues values=new ContentValues();

            values.put("apellidoPaterno",cliente.getApellidoPaterno());
            values.put("apellidoMaterno",cliente.getApellidoMaterno());
            values.put("nombres",cliente.getNombres());
            values.put("contra",cliente.getContra());
            values.put("numeroDocumento",cliente.getNumeroDocumento());
            database1.update(ConstantesCliente.CLIENTETABLA,values,"id=?",
                    new String[]{cliente.getId() + ""});
            //database.update(Constantes.CLIENTETABLA,values,"id="+cliente.getId(),null);
        } catch (Exception e) {

        }
    }

    public void eliminarCliente(int id){
        try{
            database1.delete(ConstantesCliente.CLIENTETABLA,"id="+id,null);
        } catch (Exception e) {

        }
    }

    public ArrayList<Cliente> getCliente(){
        ArrayList<Cliente> listaCli=new ArrayList<>();
        try{
            Cursor c=database1.rawQuery("select * from "+
                    ConstantesCliente.CLIENTETABLA,null);
            while(c.moveToNext()){
                listaCli.add(new Cliente(c.getInt(0),c.getInt(1),c.getInt(2),c.getInt(3),
                        c.getInt(4),c.getString(5),c.getString(6),c.getString(7),c.getString(8),c.getString(9),c.getString(10),c.getString(11),c.getString(12),c.getString(13)));
            }
        } catch (Exception e) {

        }
        return listaCli;
    }
}
