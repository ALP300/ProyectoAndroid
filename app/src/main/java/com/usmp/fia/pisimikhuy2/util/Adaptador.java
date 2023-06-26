package com.usmp.fia.pisimikhuy2.util;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.usmp.fia.pisimikhuy2.R;
import com.usmp.fia.pisimikhuy2.entity.Cliente;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    Activity activity;
    ArrayList<Cliente> listaClientes;

    public Adaptador(Activity activity, ArrayList<Cliente> listaClientes) {
        this.activity = activity;
        this.listaClientes = listaClientes;
    }

    @Override
    public int getCount() {
        return listaClientes.size();
    }

    @Override
    public Object getItem(int position) {
        return listaClientes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        TextView docId, apePat, apeMat, Nom, gen, fecNac, correo, con, numDoc;
        if(v==null){
            LayoutInflater inflater= activity.getLayoutInflater();
            v=inflater.inflate(R.layout.lyt_item_clientes,null);
        }
        docId = v.findViewById(R.id.txtDocId);
        apePat = v.findViewById(R.id.txtApePat);
        apeMat = v.findViewById(R.id.txtApeMat);
        Nom = v.findViewById(R.id.txtNombre);
        gen = v.findViewById(R.id.txtGen);
        fecNac = v.findViewById(R.id.txtFecNac);
        correo = v.findViewById(R.id.txtCorreo);
        con = v.findViewById(R.id.txtContra);
        numDoc = v.findViewById(R.id.txtNumDoc);
        Cliente cliente = listaClientes.get(position);
        docId.setText("Tipo de documento de identidad: \n"+cliente.getDocumentoIdentidad());
        apePat.setText("Apellido Paterno: \n"+cliente.getApellidoPaterno());
        apeMat.setText("Apellido Materno: \n"+cliente.getApellidoMaterno());
        Nom.setText("Nombres: \n"+cliente.getNombres());
        gen.setText("Genero: \n"+cliente.getGenero());
        fecNac.setText("Fecha de nacimiento: \n"+cliente.getFechaNacimiento());
        correo.setText("Correo: \n"+cliente.getCorreo());
        con.setText("Contraseña: \n"+cliente.getContra());
        numDoc.setText("Numero de documento: \n"+cliente.getNumeroDocumento()+"");

        return v;
    }
}
