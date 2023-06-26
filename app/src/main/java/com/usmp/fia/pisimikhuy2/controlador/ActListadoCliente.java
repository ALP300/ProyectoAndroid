package com.usmp.fia.pisimikhuy2.controlador;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.usmp.fia.pisimikhuy2.util.Adaptador;
import com.usmp.fia.pisimikhuy2.entity.Cliente;
import com.usmp.fia.pisimikhuy2.R;
import com.usmp.fia.pisimikhuy2.modelo.DAOCliente;

import java.util.ArrayList;

public class ActListadoCliente extends AppCompatActivity {
    ListView lstListClientes;
    static ArrayList<Cliente> listaClientes;
    DrawerLayout drawerLayout;
    ImageView menu;
    LinearLayout inicio, listaCli, logout, cata,editCliente;

    DAOCliente daoCliente=new DAOCliente(this);
    Cliente c;
    ImageView instagram, facebook, youtube;
    String _url = "https://instagram.com/pisi_mikhuy?igshid=YmMyMTA2M2Y=";
    String _url1 = "https://www.facebook.com/profile.php?id=100066845842815&mibextid=ZbWKwL";
    String _url2 = "https://www.youtube.com/channel/UCDpYeLrYQCLxtkLV8TbXbHw";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_lista_clientes);
        daoCliente.openDB();
        recuperarDatos();
        asignarReferencias();

        listarClientes();
    }

    /*private void capturarDatos() {
        dni=Integer.parseInt(edtDni.getText().toString());
        nom=edtNom.getText()+"";
        ape=edtApe.getText()+"";
        cor=edtCor.getText()+"";
        p=new Persona(dni,nom,ape,cor);
    }*/

    private void listarClientes() {
        listaClientes = daoCliente.getCliente();
        ArrayList<String> lista = new ArrayList<>();
        for (Cliente cli : listaClientes) {
            lista.add(cli.getIdFoto() + "" + cli.getPlatosTotales() + "" + cli.getPlatosFaltantes()
                    + "" + cli.getPremio() + "" + cli.getDocumentoIdentidad() + "" + cli.getApellidoPaterno()
                    + "" + cli.getApellidoMaterno() + "" + cli.getNombres() + "" + cli.getGenero() + "" + cli.getFechaNacimiento()
                    + "" + cli.getCorreo() + "" + cli.getContra() + "" + cli.getNumeroDocumento());
        }
        Adaptador adaptador = new Adaptador(this, listaClientes);
        /*ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,lista);*/
        lstListClientes.setAdapter(adaptador);
    }

    private void recuperarDatos() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            Toast.makeText(this, "Falta registrar Datos", Toast.LENGTH_LONG);
        } else {
            listaClientes = (ArrayList<Cliente>) bundle.getSerializable("data");
        }
    }

    private void asignarReferencias() {
        lstListClientes = findViewById(R.id.lstClientes);

        drawerLayout = findViewById(R.id.drawerLayout3);
        menu = findViewById(R.id.menu);
        inicio = findViewById(R.id.inicio);
        listaCli = findViewById(R.id.listaCli);
        logout = findViewById(R.id.cerrar);
        cata= findViewById(R.id.cata);
        editCliente=findViewById(R.id.editarClientes);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer(drawerLayout);
            }
        });

        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(ActListadoCliente.this, ActMainAdmin.class);
            }
        });
        listaCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();

            }
        });
        cata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(ActListadoCliente.this, ActPlatoEdit.class);
            }
        });
        editCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(ActListadoCliente.this, ActClientesEdit.class);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=
                        new AlertDialog.Builder(ActListadoCliente.this  );
                builder.setMessage("¿Desea cerrar sesión?")
                        .setTitle("Confirmación")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                redirectActivity(ActListadoCliente.this, ActMainNull.class);
                                Toast.makeText(ActListadoCliente.this, "Sesión cerrada", Toast.LENGTH_LONG).show();
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

        instagram = findViewById(R.id.instagram);
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri _link = Uri.parse(_url);
                Intent i = new Intent(Intent.ACTION_VIEW, _link);
                startActivity(i);
            }
        });

        facebook = findViewById(R.id.facebook);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri _link = Uri.parse(_url1);
                Intent i = new Intent(Intent.ACTION_VIEW, _link);
                startActivity(i);
            }
        });

        youtube = findViewById(R.id.yt);
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
