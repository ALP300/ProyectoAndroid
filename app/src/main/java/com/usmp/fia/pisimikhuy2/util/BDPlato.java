package com.usmp.fia.pisimikhuy2.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDPlato extends SQLiteOpenHelper {
    public BDPlato(@Nullable Context context) {
        super(context, Constantes.NOMBREDB, null, Constantes.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Constantes.NOMBRETABLA + "" +
                "(id integer primary key autoincrement," +
                "idFoto int not null," +
                "cantidad int not null,"+
                "nombre String not null," +
                "descripcion String not null," +
                "precio double not null);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
