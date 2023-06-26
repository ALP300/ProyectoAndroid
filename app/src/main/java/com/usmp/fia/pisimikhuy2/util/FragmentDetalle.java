package com.usmp.fia.pisimikhuy2.util;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.usmp.fia.pisimikhuy2.R;
import com.usmp.fia.pisimikhuy2.entity.Persona;

public class FragmentDetalle extends Fragment {

    TextView txtNom,txtCargo,txtCarrera,txtEdad;
    ImageView imgFoto;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.lyt_detalle,container,false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        txtNom=getView().findViewById(R.id.textNom);
        txtCargo=getView().findViewById(R.id.textCargo);
        txtCarrera=getView().findViewById(R.id.textCarrera);
        txtEdad=getView().findViewById(R.id.textEdad);
        imgFoto=getView().findViewById(R.id.imgFoto);
    }

    public void mostrarDatos(Persona p){
        txtNom.setText(p.getNom());
        txtCargo.setText(p.getCargo());
        txtCarrera.setText(p.getCarrera());
        txtEdad.setText(p.getEdad());
        imgFoto.setImageResource(p.getIdImg());
    }
}
