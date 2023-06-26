package com.usmp.fia.pisimikhuy2.controlador;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.usmp.fia.pisimikhuy2.entity.Cliente;
import com.usmp.fia.pisimikhuy2.entity.Comida;
import com.usmp.fia.pisimikhuy2.R;
import com.usmp.fia.pisimikhuy2.modelo.DAOPlato;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class ActPlatoEdit extends AppCompatActivity {
    EditText edtNom, edtDes, edtPrecio;
    ImageView imgVerPlato;
    Drawable imgDraw;
    Button btnEditar,btnEliminar,btnSubirImg,btnCrear;
    ListView lstPlato;
    ArrayList<Comida> listaPlato=new ArrayList<>();
    DAOPlato daoPlato=new DAOPlato(this);
    static ArrayList<Cliente> listaClientes;
    DrawerLayout drawerLayout;
    ImageView menu;
    LinearLayout inicio, listaCli, logout, cata,editCliente;

    double precio;
    String nom,des;
    Integer id,idFoto,cantidad;
    Comida c;
    Comida crear;
    Spinner spEditPlato;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_comida_admin);
        daoPlato.openDB();
        asignarReferencias();
        recuperarDatos();

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
                redirectActivity(ActPlatoEdit.this, ActMainAdmin.class);
            }
        });
        listaCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(ActPlatoEdit.this, ActListadoCliente.class);
            }
        });
        cata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();

            }
        });
        editCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(ActPlatoEdit.this, ActClientesEdit.class);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=
                        new AlertDialog.Builder(ActPlatoEdit.this  );
                builder.setMessage("¿Desea cerrar sesión?")
                        .setTitle("Confirmación")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                redirectActivity(ActPlatoEdit.this, ActMainNull.class);
                                Toast.makeText(ActPlatoEdit.this, "Sesión cerrada", Toast.LENGTH_LONG).show();
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


        edtNom=findViewById(R.id.edtNomPla);
        edtDes =findViewById(R.id.edtDesPla);
        edtPrecio= findViewById(R.id.edtPrecioP);

        //btnCrear=findViewById(R.id.btnCrearPla);
        btnEliminar=findViewById(R.id.btnEliminarPer);
        btnEditar= findViewById(R.id.btnEditarPer);
        imgVerPlato=findViewById(R.id.imgVerPlato);
        spEditPlato= findViewById(R.id.spPersonaEdit);
        ArrayList<Comida> listaPlato=daoPlato.getComida();
        String lstPlato[]=new String[listaPlato.size()+1];
        lstPlato[0]="PLATOS";
        for(int i=1;i<(listaPlato.size()+1);i++){
            lstPlato[i]=listaPlato.get(i-1).getNombre();
        }
        final Comida[] plato = {new Comida()};
        ArrayAdapter<String> adapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lstPlato);
        spEditPlato.setAdapter(adapter);
        spEditPlato.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(i==0){
                    edtNom.setText("");
                    edtDes.setText("");
                    edtPrecio.setText("");
                    imgVerPlato.setImageResource(R.drawable.comida_edit);

                }else{
                    plato[0] = listaPlato.get(i-1);
                    setId(plato[0].getId());
                    edtNom.setText(plato[0].getNombre());
                    edtDes.setText(plato[0].getDescripcion());
                    edtPrecio.setText(plato[0].getPrecio()+"");
                    imgVerPlato.setImageResource(plato[0].getIdFoto());

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        capturarEventos();




    }
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK) {
            Uri path = data.getData();
            Drawable foto = getDrawableFromUri(ActPlatoEdit.this, path);
            imgDraw=foto;
            imgVerPlato.setImageDrawable(foto);

        }
    }

    public Drawable getDrawableFromUri(Activity activity, Uri uri) {
        try {
            InputStream inputStream = activity.getContentResolver().openInputStream(uri);
            return Drawable.createFromStream(inputStream, uri.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void subirImg(View view){
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent.createChooser(intent,"Seleccionar la Aplicación"),10);
    }

 */

    private void capturarEventos() {
        /*
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearPlato();
                daoPlato.registrarPlato(crear);
                Toast.makeText(ActPlatoEdit.this,"PLATO CREADO",Toast.LENGTH_LONG).show();
                limpiar();
            }
        });
        */

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capturarDatos();
                c.setId(id);
                daoPlato.editarPlato(c);
                Toast.makeText(ActPlatoEdit.this,"CAMBIOS REALIZADOS",Toast.LENGTH_LONG).show();
                limpiar();
            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daoPlato.eliminarPlato(id);
                Toast.makeText(ActPlatoEdit.this,"PLATO ELIMINADO",Toast.LENGTH_LONG).show();
                limpiar();
            }
        });
    }
    /*
    private void crearPlato(){
        nom=edtNom.getText()+"";
        des=edtDes.getText()+"";
        precio= Double.parseDouble(edtPrecio.getText()+"");
        cantidad=1;


        Drawable fotoEdit=getResources().getDrawable(R.drawable.comida_edit);
        imgVerPlato.setImageDrawable(fotoEdit);


        imgVerPlato.setTag(imgDraw);
        int drawableId = Integer.parseInt(imgVerPlato.getTag().toString());

        idFoto=drawableId;

        crear=new Comida(idFoto,cantidad,nom,des,precio);
    }
    */

    private void setId(int indice) {
        id=indice;
    }

    private void capturarDatos() {

        nom=edtNom.getText()+"";
        des=edtDes.getText()+"";
        precio= Double.parseDouble(edtPrecio.getText()+"");
        c=new Comida(nom,des,precio);
    }

    private void limpiar() {
        spEditPlato.setSelection(0);
        edtPrecio.setText("");
        edtNom.setText("");
        edtDes.setText("");
        imgVerPlato.setImageResource(R.drawable.logo);

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
