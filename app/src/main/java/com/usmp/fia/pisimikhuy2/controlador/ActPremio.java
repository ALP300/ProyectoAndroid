package com.usmp.fia.pisimikhuy2.controlador;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.usmp.fia.pisimikhuy2.entity.Cliente;
import com.usmp.fia.pisimikhuy2.entity.Premio;
import com.usmp.fia.pisimikhuy2.R;

import java.util.ArrayList;

public class ActPremio extends AppCompatActivity {

    static ArrayList<Cliente> listaClientes;
    static Cliente clienteIni;
    int regalo;
    DrawerLayout drawerLayout;
    ImageView menu;
    Button btnPremio;
    LinearLayout inicio, catalogo, premio, nosotros,preguntas,informacion, logout;
    ImageView imgView;
    TextView txtPremio2;
    ImageView instagram, facebook, youtube;
    String _url ="https://instagram.com/pisi_mikhuy?igshid=YmMyMTA2M2Y=";
    String _url1= "https://www.facebook.com/profile.php?id=100066845842815&mibextid=ZbWKwL";
    String _url2= "https://www.youtube.com/channel/UCDpYeLrYQCLxtkLV8TbXbHw";

    int images [] = {R.drawable.tapa, R.drawable.caja};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_premio);
        recuperarDatos();
        asignarReferencias();
        imgView.setImageResource(images[0]);
    }
    private void recuperarDatos() {
        Bundle bundle=getIntent().getExtras();
        if(bundle==null){
            Toast.makeText(this,"Falta registrar Datos",Toast.LENGTH_LONG);
        }else{
            listaClientes=(ArrayList<Cliente>) bundle.getSerializable("data");
            clienteIni=(Cliente) bundle.getSerializable("cliente");
        }
    }

    private void asignarReferencias() {
        imgView=findViewById(R.id.iVPremio);


        txtPremio2=findViewById(R.id.txtPremio2);
        btnPremio=findViewById(R.id.btnPremio);

        regalo=clienteIni.getPremio();

            btnPremio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(regalo!=0) {
                        regalo = regalo - 1;
                        premio();
                        clienteIni.setPremio(regalo);
                    }
                }
            });

        drawerLayout=findViewById(R.id.drawerLayout);
        menu=findViewById(R.id.menu);
        inicio=findViewById(R.id.inicio);
        catalogo=findViewById(R.id.catalogo);
        premio=findViewById(R.id.premio);
        nosotros=findViewById(R.id.nosotros);
        preguntas=findViewById(R.id.preguntas);
        informacion=findViewById(R.id.informacion);
        logout=findViewById(R.id.cerrar);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer(drawerLayout);
            }
        });
        preguntas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(ActPremio.this, ActPreguntasFrecuentesCliente.class);
            }
        });
        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(ActPremio.this, ActMainCliente.class);

            }
        });
        catalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(ActPremio.this, ActCatalogo.class);

            }
        });
        premio.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                recreate();

            }
        });
        nosotros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(ActPremio.this, ActSobreNosotrosCliente.class);
            }
        });
        informacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(ActPremio.this, ActInfoCliente.class);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=
                        new AlertDialog.Builder(ActPremio.this  );
                builder.setMessage("¿Desea cerrar sesión?")
                        .setTitle("Confirmación")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                redirectActivity(ActPremio.this, ActMainNull.class);
                                Toast.makeText(ActPremio.this, "Sesión cerrada", Toast.LENGTH_LONG).show();
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
    public void premio(){
        Integer ale=0;
        int cantP=clienteIni.getPlatosTotales();
        if(cantP<40){
            ale=Integer.valueOf((int) Math.round(Math.random()*(1-0+0)+0));
        }else if(cantP>=40 && cantP<70){
            ale=Integer.valueOf((int) Math.round(Math.random()*(2-0+0)+0));
        }else if(cantP>=70 && cantP<100){
            ale=Integer.valueOf((int) Math.round(Math.random()*(3-0+0)+0));
        }else if(cantP>=100){
            ale=Integer.valueOf((int) Math.round(Math.random()*(4-0+0)+0));
        }
        String msj="";
        ArrayList<Premio> listaPremio=clienteIni.getListaPremios();
        Premio premioUltimo=listaPremio.get(listaPremio.size()-1);
        Premio premioDes=new Premio();
        Double descuento;
        imgView.setImageResource(images[1]);
        if(ale==0){
            msj="6% DE DESCUENTO";
            descuento=0.06;
        }else if(ale==1){
            msj="8% DE DESCUENTO";
            descuento=0.08;
        }else if(ale==2){
            msj="10% DE DESCUENTO";
            descuento=0.10;
        }else if(ale==3){
            msj="12% DE DESCUENTO";
            descuento=0.12;
        }else{
            msj="14% DE DESCUENTO";
            descuento=0.14;
        }
        premioDes.setId(premioUltimo.getId()+1);
        premioDes.setDescuento(descuento);
        premioDes.setMsj(msj);
        listaPremio.add(premioDes);
        txtPremio2.setText("¡¡¡GANASTE!!!\n"+msj);
        clienteIni.setListaPremios(listaPremio);
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
