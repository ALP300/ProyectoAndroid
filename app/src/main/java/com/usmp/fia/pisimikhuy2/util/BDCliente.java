package com.usmp.fia.pisimikhuy2.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDCliente extends SQLiteOpenHelper {

    public BDCliente(@Nullable Context context) {
        super(context, ConstantesCliente.CLIENTEDB, null, Constantes.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ConstantesCliente.CLIENTETABLA+ "" +
                "(id integer primary key autoincrement,"+
                "idFotoCli int not null,"+
                "platosTotales integer not null," +
                "platosFaltantes integer not null," +
                "premio integer not null," +
                "documentoIdentidad text not null," +
                "apellidoPaterno text not null," +
                "apellidoMaterno text not null," +
                "nombres text not null," +
                "genero text not null," +
                "fechaNacimiento text not null," +
                "correo text not null," +
                "contra text not null," +
                "numeroDocumento text not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
