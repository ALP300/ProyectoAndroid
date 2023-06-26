package com.usmp.fia.pisimikhuy2.controlador;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.usmp.fia.pisimikhuy2.util.FragmentDetalle;
import com.usmp.fia.pisimikhuy2.util.FragmentLista;
import com.usmp.fia.pisimikhuy2.entity.IPerson;
import com.usmp.fia.pisimikhuy2.entity.Persona;
import com.usmp.fia.pisimikhuy2.R;

public class ActPrincipal extends AppCompatActivity implements IPerson {

    FragmentLista fragmentLista;
    FragmentDetalle fragmentDetalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_principal);
        asignarReferencias();
    }

    private void asignarReferencias() {
        fragmentLista=(FragmentLista) getSupportFragmentManager().findFragmentById(R.id.fgtLista);
        fragmentDetalle=(FragmentDetalle) getSupportFragmentManager().findFragmentById(R.id.fgtDetalle);
    }

    @Override
    public void seleccionarPersona(Persona p) {
        fragmentDetalle.mostrarDatos(p);
    }
}