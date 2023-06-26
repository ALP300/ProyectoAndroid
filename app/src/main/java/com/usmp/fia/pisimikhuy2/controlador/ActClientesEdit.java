package com.usmp.fia.pisimikhuy2.controlador;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.usmp.fia.pisimikhuy2.entity.Cliente;
import com.usmp.fia.pisimikhuy2.R;
import com.usmp.fia.pisimikhuy2.modelo.DAOCliente;

import java.util.ArrayList;

public class ActClientesEdit extends AppCompatActivity {
    TextView edtNomPer, edtApePaPer, edtApeMaPer,edtContraPer;
    Button btnEditar,btnEliminar;
    static ArrayList<Cliente> listaClientes;
    DAOCliente daoCliente=new DAOCliente(this);
    String apellidoPaterno, apellidoMaterno, nombres, contra;
    DrawerLayout drawerLayout;
    ImageView menu;
    LinearLayout inicio, listaCli, logout, cata,editCliente;
    Integer id;
    Cliente cli;
    Spinner spEditPlato;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_edit_cliente_admin);
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
                redirectActivity(ActClientesEdit.this, ActMainAdmin.class);
            }
        });
        listaCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(ActClientesEdit.this, ActListadoCliente.class);
            }
        });
        cata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(ActClientesEdit.this, ActPlatoEdit.class);
            }
        });
        editCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                recreate();

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=
                        new AlertDialog.Builder(ActClientesEdit.this  );
                builder.setMessage("¿Desea cerrar sesión?")
                        .setTitle("Confirmación")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                redirectActivity(ActClientesEdit.this, ActMainNull.class);
                                Toast.makeText(ActClientesEdit.this, "Sesión cerrada", Toast.LENGTH_LONG).show();
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





        edtApePaPer=findViewById(R.id.edtNomPla);
        edtApeMaPer =findViewById(R.id.edtDesPla);
        edtNomPer =findViewById(R.id.edtNomPer);
        edtContraPer =findViewById(R.id.edtContraPer);
        btnEliminar=findViewById(R.id.btnEliminarPer);
        btnEditar= findViewById(R.id.btnEditarPer);
        spEditPlato= findViewById(R.id.spPersonaEdit);
        ArrayList<Cliente> listaCliente=daoCliente.getCliente();
        String lstCli[]=new String[listaCliente.size()+1];
        lstCli[0]="CLIENTES";
        for(int i=1;i<(listaCliente.size()+1);i++){
            lstCli[i]=listaCliente.get(i-1).getNumeroDocumento();
        }
        final Cliente[] cliente = {new Cliente()};
        ArrayAdapter<String> adapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lstCli);
        spEditPlato.setAdapter(adapter);
        spEditPlato.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    edtNomPer.setText("");
                    edtApeMaPer.setText("");
                    edtApePaPer.setText("");
                    edtContraPer.setText("");
                }else{
                    cliente[0] = listaCliente.get(i-1);
                    setId(cliente[0].getId());
                    edtNomPer.setText(cliente[0].getNombres());
                    edtApeMaPer.setText(cliente[0].getApellidoMaterno());
                    edtApePaPer.setText(cliente[0].getApellidoPaterno());;
                    edtContraPer.setText(cliente[0].getContra());
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        capturarEventos();
    }

    private void capturarEventos() {


        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capturarDatos();
                cli.setId(id);
                daoCliente.editarCliente(cli);
                Toast.makeText(ActClientesEdit.this,"CAMBIOS REALIZADOS",Toast.LENGTH_LONG).show();
                limpiar();
            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daoCliente.eliminarCliente(id);
                Toast.makeText(ActClientesEdit.this,"CLIENTE ELIMINADO",Toast.LENGTH_LONG).show();
                limpiar();
            }
        });
    }

    private void setId(int indice) {
        id=indice;
    }

    private void capturarDatos() {

        nombres=edtNomPer.getText()+"";
        apellidoMaterno=edtApeMaPer.getText()+"";
        apellidoPaterno=edtApePaPer.getText()+"";
        contra=edtContraPer.getText()+"";

        cli=new Cliente(apellidoPaterno,apellidoMaterno,nombres,contra);
    }

    private void limpiar() {
        edtNomPer.setText("");
        edtContraPer.setText("");
        edtApeMaPer.setText("");
        edtApePaPer.setText("");

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

