package com.usmp.fia.pisimikhuy2.controlador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.usmp.fia.pisimikhuy2.entity.Cliente;
import com.usmp.fia.pisimikhuy2.entity.Comida;
import com.usmp.fia.pisimikhuy2.R;
import com.usmp.fia.pisimikhuy2.modelo.DAOCliente;
import com.usmp.fia.pisimikhuy2.modelo.DAOPlato;

public class SplashActivity extends AppCompatActivity {
    DAOPlato daoPlato=new DAOPlato(this);
    DAOCliente daoCliente = new DAOCliente(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        daoCliente.openDB();
        daoPlato.openDB();
        daoPlato.registrarPlato(new Comida(R.drawable.caldomote,1,"CALDO DE MOTE",
                "Caldo a base de maíz mote y carne de carnero; acompañado de perejil y cebolla china",30));
        daoPlato.registrarPlato(new Comida(R.drawable.pachamanca,1,"PACHAMANCA A LA OLLA",
                "A base de carne de res, de cerdo, cordero o pollo; sazonado con las especias de chincho, culantro y ají; acompañado con papas, camote, yuca, choclo y habas. ",45));
        daoPlato.registrarPlato(new Comida(R.drawable.rocoto,1,"ROCOTO RELLENO",
                "Relleno de carne, almendras y pasas, cubierto por una capa de queso fundido; acompañado de lechuga, trozos de tomate y una porción de pastel de papa'",35));
        daoPlato.registrarPlato(new Comida(R.drawable.cuychactado,1,"CUY CHACTADO",
                "Cuy frito en abundante aceite hecho bajo una piedra, acompañado con papas hervidas, camote, habas y sarza de cebolla.",40));
        daoPlato.registrarPlato(new Comida(R.drawable.chupehabas,1,"CHUPE DE HABAS",
                "Caldo a base de habas, papas picadas, huevo, queso; acompañado de perejil y culantro picado",25));
        daoPlato.registrarPlato(new Comida(R.drawable.ocopa,1,"OCOPA",
                "Salsa a base de huacatay y ají mirasol servido sobre papas hervidas; acompañado de lechuga, huevo cocido y aceitunas negras",20));
        daoCliente.registrarCliente(new Cliente(R.drawable.user,0,25,1,"DNI","ApePat1","ApeMat1","Nom1","Masculino","01/09/2001","usuario1@gmail.com","12345","12345678"));
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this,
                        ActMainNull.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}