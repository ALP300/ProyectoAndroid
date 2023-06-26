package com.usmp.fia.pisimikhuy2.controlador;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.usmp.fia.pisimikhuy2.entity.Cliente;
import com.usmp.fia.pisimikhuy2.entity.Premio;
import com.usmp.fia.pisimikhuy2.R;

import java.util.ArrayList;

public class ActInfoCliente extends AppCompatActivity{
    static ArrayList<Cliente> listaClientes;
    static Cliente clienteIni;
    DrawerLayout drawerLayout;
    ImageView menu;
    LinearLayout inicio, catalogo, premio, nosotros,preguntas,informacion, logout;
    TextView txtInfoNom,txtInfoApePa,txtInfoApeMa,txtInfoPlaTot,txtInfoPlaFal,txtInfoPre;
    Spinner spInfoPre;
    ListView lstFacturas;
    ImageView imgInfoCli;
    ImageView instagram, facebook, youtube;
    String premios[];
    String facturas[];
    ArrayList<Premio> listaPremios=new ArrayList<>();
    ArrayList<String> listaFacturas=new ArrayList<>();
    String _url ="https://instagram.com/pisi_mikhuy?igshid=YmMyMTA2M2Y=";
    String _url1= "https://www.facebook.com/profile.php?id=100066845842815&mibextid=ZbWKwL";
    String _url2= "https://www.youtube.com/channel/UCDpYeLrYQCLxtkLV8TbXbHw";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_informacion_cliente);
        recuperarDatos();
        asignarReferencias();

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

    private void recuperarDatos() {
        Bundle bundle=getIntent().getExtras();
        if(bundle==null){
            listaClientes=new ArrayList<>();
        }
        else{
            listaClientes=(ArrayList<Cliente>) bundle.getSerializable("data");
            clienteIni=(Cliente) bundle.getSerializable("cliente");
        }
    }


    private void asignarReferencias() {
        imgInfoCli=findViewById(R.id.imgInfoCli);
        txtInfoNom=findViewById(R.id.txtInfoNom);
        txtInfoApePa=findViewById(R.id.txtInfoApePa);
        txtInfoApeMa=findViewById(R.id.txtInfoApeMa);
        txtInfoPlaTot=findViewById(R.id.txtInfoPlaTot);
        txtInfoPlaFal=findViewById(R.id.txtInfoPlaFal);
        txtInfoPre=findViewById(R.id.txtInfoPre);
        spInfoPre=findViewById(R.id.spInfoPre);
        lstFacturas=findViewById(R.id.lstFacturas);
        listaPremios=clienteIni.getListaPremios();
        premios=new String[listaPremios.size()];
        for (int i=0;i<listaPremios.size();i++){
            premios[i]=listaPremios.get(i).getMsj();
        }

        ArrayAdapter<String> adapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, premios);
        spInfoPre.setAdapter(adapter);

        listaFacturas=clienteIni.getListaFacturas();
        facturas=new String[listaFacturas.size()];
        for(int i=0;i<listaFacturas.size();i++){
            facturas[i]=listaFacturas.get(i);
        }

        ArrayAdapter<String> adapter1=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,facturas);
        lstFacturas.setAdapter(adapter1);
        imgInfoCli.setImageResource(clienteIni.getIdFoto());
        txtInfoNom.setText(clienteIni.getNombres());
        txtInfoApePa.setText(clienteIni.getApellidoPaterno());
        txtInfoApeMa.setText(clienteIni.getApellidoMaterno());
        txtInfoPlaTot.setText(clienteIni.getPlatosTotales()+"");
        txtInfoPlaFal.setText(clienteIni.getPlatosFaltantes()+"");
        txtInfoPre.setText(clienteIni.getPremio()+"");



        drawerLayout = findViewById(R.id.drawerLayout);
        menu = findViewById(R.id.menu);
        inicio = findViewById(R.id.inicio);
        catalogo = findViewById(R.id.catalogo);
        premio = findViewById(R.id.premio);
        nosotros = findViewById(R.id.nosotros);
        preguntas = findViewById(R.id.preguntas);
        informacion= findViewById(R.id.informacion);
        logout = findViewById(R.id.cerrar);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer(drawerLayout);
            }
        });

            inicio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    redirectActivity(ActInfoCliente.this, ActMainCliente.class);
                }
            });
            catalogo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    redirectActivity(ActInfoCliente.this, ActCatalogo.class);
                }
            });
            premio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    redirectActivity(ActInfoCliente.this, ActPremio.class);
                }
            });
            nosotros.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    redirectActivity(ActInfoCliente.this, ActSobreNosotrosCliente.class);
                }
            });
            preguntas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    redirectActivity(ActInfoCliente.this, ActPreguntasFrecuentesCliente.class);
                }
            });
            informacion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recreate();

                }
            });
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder=
                            new AlertDialog.Builder(ActInfoCliente.this  );
                    builder.setMessage("¿Desea cerrar sesión?")
                            .setTitle("Confirmación")
                            .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    redirectActivity(ActInfoCliente.this, ActMainNull.class);
                                    Toast.makeText(ActInfoCliente.this, "Sesión cerrada", Toast.LENGTH_LONG).show();
                                }
                            })
                            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    dialogInterface.cancel();
                                }
                            });
                    AlertDialog dialog= builder.create();
                    dialog.show();

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
    public static void redirectActivity(Activity activity, Class secondActivity ){
        Intent intent = new Intent(activity, secondActivity);
        Bundle bundle1=new Bundle();
        bundle1.putSerializable("data",listaClientes);
        bundle1.putSerializable("cliente",clienteIni);
        intent.putExtras(bundle1);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);;
        activity.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }

}