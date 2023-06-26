package com.usmp.fia.pisimikhuy2.controlador;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.usmp.fia.pisimikhuy2.entity.Cliente;
import com.usmp.fia.pisimikhuy2.entity.Premio;
import com.usmp.fia.pisimikhuy2.R;
import com.usmp.fia.pisimikhuy2.modelo.DAOCliente;

import java.util.ArrayList;

public class ActIniSesion extends AppCompatActivity {

    static ArrayList<Cliente> listaClientes;
    DrawerLayout drawerLayout;
    ImageView menu;
    DAOCliente daoCliente=new DAOCliente(this);
    LinearLayout inicio,nosotros,preguntas,registro,iniSesion;
    Spinner spIde;
    String data[]={"CARNE DE ESTRANJERIA","PASAPORTE","DNI"};

    EditText edtDni,edtCon;

    ImageView instagram, facebook, youtube;
    String _url ="https://instagram.com/pisi_mikhuy?igshid=YmMyMTA2M2Y=";
    String _url1= "https://www.facebook.com/profile.php?id=100066845842815&mibextid=ZbWKwL";
    String _url2= "https://www.youtube.com/channel/UCDpYeLrYQCLxtkLV8TbXbHw";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_login);
        daoCliente.openDB();
        recuperarDatos();
        asignarReferencias();



    }
    private void recuperarDatos() {
        Bundle bundle=getIntent().getExtras();
        if(bundle==null){
            listaClientes=new ArrayList<>();
        }
        else{
            listaClientes=(ArrayList<Cliente>) bundle.getSerializable("data");
        }
    }

    private void asignarReferencias() {
        edtDni=findViewById(R.id.edtDni);
        edtCon=findViewById(R.id.edtCon);
        spIde=findViewById(R.id.spIde);

        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,data);
        spIde.setAdapter(adapter);

        drawerLayout=findViewById(R.id.drawerLayout);
        menu=findViewById(R.id.menu);
        inicio = findViewById(R.id.inicio);
        nosotros = findViewById(R.id.nosotros);
        preguntas = findViewById(R.id.preguntas);
        registro = findViewById(R.id.registro);
        iniSesion = findViewById(R.id.iniSesion);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer(drawerLayout);
            }
        });
        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(ActIniSesion.this, ActMainNull.class);

            }
        });
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(ActIniSesion.this, ActRegistroCliente.class);

            }
        });
        iniSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();

            }
        });
        nosotros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(ActIniSesion.this, ActSobreNosotrosNull.class);

            }
        });
        preguntas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(ActIniSesion.this, ActPreguntasFrecuentesNull.class);
            }
        });

        instagram= findViewById(R.id.instagram);
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri _link = Uri.parse(_url);
                Intent i = new Intent(Intent.ACTION_VIEW, _link);
                startActivity(i);
            }
        });

        facebook=findViewById(R.id.facebook);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri _link = Uri.parse(_url1);
                Intent i = new Intent(Intent.ACTION_VIEW, _link);
                startActivity(i);
            }
        });

        youtube=findViewById(R.id.yt);
        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri _link = Uri.parse(_url2);
                Intent i = new Intent(Intent.ACTION_VIEW, _link);
                startActivity(i);
            }
        });
    }
    public static void openDrawer(DrawerLayout drawerLayout){
        drawerLayout.openDrawer(GravityCompat.START);
    }
    public static void closeDrawer(DrawerLayout drawerLayout){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    public void redirectActivity(Activity activity, Class secondActivity){

        Intent intent = new Intent(activity, secondActivity);
        Bundle bundle1=new Bundle();
        bundle1.putSerializable("data",listaClientes);
        intent.putExtras(bundle1);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);;
        activity.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
    public void inicioSesion(View view){
        listaClientes=daoCliente.getCliente();
        String username = edtDni.getText().toString();
        String password = edtCon.getText().toString();
        for(Cliente cli:listaClientes){
            if(username.equals("73035832") && password.equals("321A")) {
                    Intent intent = new Intent(this, ActMainAdmin.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data", listaClientes);

                    intent.putExtras(bundle);
                    startActivity(intent);
                    Toast.makeText(this,"Bienvenido Administrador",Toast.LENGTH_LONG).show();

            }else{
                if(cli.getNumeroDocumento().equals(username)){
                    if(cli.getContra().equals(password)){
                        Intent intent = new Intent(this, ActMainCliente.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("data", listaClientes);
                        Premio premio= new Premio();
                        premio.setId(0);
                        premio.setDescuento(0);
                        premio.setMsj("Escoger Descuento");
                        ArrayList<Premio> listaPremio=new ArrayList<>();
                        listaPremio.add(premio);
                        cli.setListaPremios(listaPremio);
                        String factura ="ACA ENCUENTRAS TUS FACTURAS";
                        ArrayList<String> listaFac=new ArrayList<>();
                        listaFac.add(factura);
                        cli.setListaFacturas(listaFac);
                        cli.setPlatosFaltantes(25);
                        bundle.putSerializable("cliente",cli);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        Toast.makeText(this,"Bienvenido "+cli.getNombres(),Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(this,"Contraseña incorrecta",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(this,"Cliente no encontrado",Toast.LENGTH_LONG).show();
                }

            }
        }


    }

}
