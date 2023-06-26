package com.usmp.fia.pisimikhuy2.util;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.usmp.fia.pisimikhuy2.R;
import com.usmp.fia.pisimikhuy2.entity.IPerson;
import com.usmp.fia.pisimikhuy2.entity.Persona;

import java.util.ArrayList;

public class FragmentLista extends Fragment {

    ArrayList<Persona> lista;
    ListView lstPer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.lyt_lista,null);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       lstPer=getView().findViewById(R.id.lstPer);

       lista=new ArrayList<>();

        lista.add(new Persona(1,"Lucas Espinoza","20 años","Ingeniería de Computación y Sistemas","Líder del proyecto Pisi Mikhuy",R.drawable.lucas));
        lista.add(new Persona(2,"Katherine Hernandez","20 años","Ingeniería de Computación y Sistemas","Integrante del proyecto Pisi Mikhuy",R.drawable.kati));
        lista.add(new Persona(3,"Aitor Palomino","19 años","Ingeniería de Computación y Sistemas","Integrante del proyecto Pisi Mikhuy",R.drawable.aitor));
        lista.add(new Persona(4,"Rodrigo Carrasco","18 años","Ingeniería de Computación y Sistemas","Integrante del proyecto Pisi Mikhuy",R.drawable.rodrigo));
        lista.add(new Persona(5,"Fabrizio Gutierrez","23 años","Ingeniería de Computación y Sistemas","Integrante del proyecto Pisi Mikhuy",R.drawable.fabrizio));
        lista.add(new Persona(6,"José Villegas","20 años","Ingeniería de Computación y Sistemas","Integrante del proyecto Pisi Mikhuy",R.drawable.ryan));


        ArrayList<String> nombres=new ArrayList<>();
        for(Persona p:lista){
            nombres.add(p.getNom());
        }

        ArrayAdapter<String> adapter=new ArrayAdapter<>(getActivity(),
                                                            android.R.layout.simple_list_item_1,nombres);
        lstPer.setAdapter(adapter);
        lstPer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ((IPerson)getActivity()).seleccionarPersona(lista.get(position));
            }
        });
    }
}
