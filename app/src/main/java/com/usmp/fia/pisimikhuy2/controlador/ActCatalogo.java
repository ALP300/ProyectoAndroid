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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.usmp.fia.pisimikhuy2.util.AdaptadorCatalogo;
import com.usmp.fia.pisimikhuy2.entity.Cliente;
import com.usmp.fia.pisimikhuy2.entity.Comida;
import com.usmp.fia.pisimikhuy2.R;
import com.usmp.fia.pisimikhuy2.modelo.DAOPlato;

import java.util.ArrayList;
import java.util.List;

public class ActCatalogo extends AppCompatActivity {
    DrawerLayout drawerLayout;
    static ArrayList<Cliente> listaClientes;
    static Cliente clienteIni;
    ImageView menu;
    LinearLayout inicio, catalogo, premio, nosotros,preguntas,informacion, logout;

    ImageView instagram, facebook, youtube;
    String _url ="https://instagram.com/pisi_mikhuy?igshid=YmMyMTA2M2Y=";
    String _url1= "https://www.facebook.com/profile.php?id=100066845842815&mibextid=ZbWKwL";
    String _url2= "https://www.youtube.com/channel/UCDpYeLrYQCLxtkLV8TbXbHw";
    TextView txtTotCom;
    Button btnCarrito;
    RecyclerView rvListaProductos;
    AdaptadorCatalogo adaptador;
    ArrayList<Comida> listComi=new ArrayList<>();
    List<Comida> carroCompras = new ArrayList<>();
    DAOPlato daoPlato= new DAOPlato(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_lista_comidas);
        daoPlato.openDB();

        recuperarDatos();
        asignarReferencias();
    }
    private void recuperarDatos() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            listaClientes = new ArrayList<>();
        } else {
            listaClientes = (ArrayList<Cliente>) bundle.getSerializable("data");
            clienteIni=(Cliente) bundle.getSerializable("cliente");
        }
    }
    private void asignarReferencias() {
        txtTotCom = findViewById(R.id.txtTotCom);
        btnCarrito = findViewById(R.id.btnCarrito);
        rvListaProductos = findViewById(R.id.rvListaProductos);
        rvListaProductos.setLayoutManager(new GridLayoutManager(ActCatalogo.this, 1));


        listComi=daoPlato.getComida();
        AdaptadorCatalogo adaptador = new AdaptadorCatalogo(ActCatalogo.this, txtTotCom, btnCarrito, listComi, carroCompras,listaClientes,clienteIni);
        rvListaProductos.setAdapter(adaptador);
        /*AdaptadorCatalogo adaptador=new AdaptadorCatalogo(this,listComi);
        lstComi.setAdapter(adaptador);*/


        drawerLayout = findViewById(R.id.drawerLayout);
        menu = findViewById(R.id.menu);
        inicio = findViewById(R.id.inicio);
        catalogo = findViewById(R.id.catalogo);
        premio = findViewById(R.id.premio);
        nosotros = findViewById(R.id.nosotros);
        preguntas = findViewById(R.id.preguntas);
        informacion = findViewById(R.id.informacion);
        logout = findViewById(R.id.cerrar);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer(drawerLayout);
            }
        });
        catalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });
        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(ActCatalogo.this, ActMainCliente.class);
            }
        });
        premio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(ActCatalogo.this, ActPremio.class);
            }
        });
        nosotros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(ActCatalogo.this, ActSobreNosotrosCliente.class);
            }
        });
        preguntas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(ActCatalogo.this, ActPreguntasFrecuentesCliente.class);
            }
        });
        informacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(ActCatalogo.this, ActInfoCliente.class);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=
                        new AlertDialog.Builder(ActCatalogo.this  );
                builder.setMessage("¿Desea cerrar sesión?")
                        .setTitle("Confirmación")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                redirectActivity(ActCatalogo.this, ActMainNull.class);
                                Toast.makeText(ActCatalogo.this, "Sesión cerrada", Toast.LENGTH_LONG).show();
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
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
}

