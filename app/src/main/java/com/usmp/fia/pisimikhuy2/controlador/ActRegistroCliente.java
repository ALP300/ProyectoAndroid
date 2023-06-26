package com.usmp.fia.pisimikhuy2.controlador;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.usmp.fia.pisimikhuy2.entity.Cliente;
import com.usmp.fia.pisimikhuy2.R;
import com.usmp.fia.pisimikhuy2.modelo.DAOCliente;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class ActRegistroCliente extends AppCompatActivity {
    static ArrayList<Cliente> listaClientes;
    DrawerLayout drawerLayout;
    ImageView menu;
    LinearLayout inicio,nosotros,preguntas,registro,iniSesion;
    EditText edtNumDoc, edtApePat, edtApeMat, edtNom, edtFecNac, edtCorreo, edtCon;
    Spinner sprDocId;
    RadioButton rdMas, rdFem;

    Button btnRegistrar, btnIniSesion;
    String DocId[] = {"DNI","Carné de extranjeria","Pasaporte"};

    ImageView instagram, facebook, youtube;
    String _url ="https://instagram.com/pisi_mikhuy?igshid=YmMyMTA2M2Y=";
    String _url1= "https://www.facebook.com/profile.php?id=100066845842815&mibextid=ZbWKwL";
    String _url2= "https://www.youtube.com/channel/UCDpYeLrYQCLxtkLV8TbXbHw";
    DAOCliente daoCliente=new DAOCliente(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_registro_cliente);
        daoCliente.openDB();
        recuperarDatos();
        asignarReferencias();

        btnIniSesion= findViewById(R.id.btnRegresar);
        btnRegistrar= findViewById(R.id.btnRegistrar);

        /*btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager=getSupportFragmentManager();
                DialogoAlerta da=new DialogoAlerta();
                da.show(fragmentManager,"tagAlerta");
            }
        });*/


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
    public void calendarioCliente(View view){
        DatePickerDialog d = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth){
                edtFecNac.setText(dayOfMonth + "/" + (month+1) + "/" + year);
            }
        }, 2023, 4, 23);
        d.show();
    }
    private void asignarReferencias() {
        edtNumDoc = findViewById(R.id.edtNumDoc);
        edtApePat = findViewById(R.id.edtApePat);
        edtApeMat = findViewById(R.id.edtApeMat);
        edtNom = findViewById(R.id.edtNom);
        edtFecNac = findViewById(R.id.edtFecNac);
        edtCorreo = findViewById(R.id.edtCorreo);
        edtCon = findViewById(R.id.edtCon);
        sprDocId = findViewById(R.id.sprDocId);
        ArrayAdapter<String> adapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, DocId);
        sprDocId.setAdapter(adapter);
        rdMas= findViewById(R.id.rdMas);
        rdFem = findViewById(R.id.rdFem);


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
                redirectActivity(ActRegistroCliente.this, ActMainNull.class);

            }
        });
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();


            }
        });
        iniSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(ActRegistroCliente.this, ActIniSesion.class);

            }
        });
        nosotros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(ActRegistroCliente.this, ActSobreNosotrosNull.class);

            }
        });
        preguntas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(ActRegistroCliente.this, ActPreguntasFrecuentesNull.class);
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
    private Boolean validar() {
        boolean retorno = true;
        String c1 = edtNumDoc.getText().toString();
        String c2 = edtFecNac.getText().toString();
        String c3 = edtCon.getText().toString();
        String c4 = edtApeMat.getText().toString();
        String c5 = edtNom.getText().toString();
        String c6 = edtCorreo.getText().toString();
        String c7 = edtApePat.getText().toString();
        if (c1.isEmpty()) {
            edtNumDoc.setError("Este campo no puede quedar vacío");
            retorno = false;
        }
        if (c2.isEmpty()) {
            edtFecNac.setError("Este campo no puede quedar vacío");
            retorno = false;
        }
        if (c3.isEmpty()) {
            edtCon.setError("Este campo no puede quedar vacío");
            retorno = false;
        }
        if (c4.isEmpty()) {
            edtApeMat.setError("Este campo no puede quedar vacío");
            retorno = false;
        }

        if (c5.isEmpty()) {
            edtNom.setError("Este campo no puede quedar vacío");
            retorno = false;
        }
        if (c6.isEmpty()) {
            edtCorreo.setError("Este campo no puede quedar vacío");
            retorno = false;
        }
        if (c7.isEmpty()) {
            edtApePat.setError("Este campo no puede quedar vacío");
            retorno = false;
        }
        return retorno;
    }
    public void dialogo(View view){
        AlertDialog.Builder builder=
                new AlertDialog.Builder(this  );

        builder.setMessage("¿Confirma registrarse?")
                .setTitle("Confirmación")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                       Registrar(view);

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
    public void Registrar(View view){
        String DocId, NumDocLocal, NumDoc = "", ApePat, ApeMat, Nom, FecNac, Correo, Con, Gen;
        int idFoto,platosTotales=0,platosFaltantes=0,premio=1;
        DocId = sprDocId.getSelectedItem().toString();
        ApePat = edtApePat.getText().toString();
        ApeMat = edtApeMat.getText().toString();
        Nom = edtNom.getText().toString();
        FecNac = edtFecNac.getText().toString();
        Correo = edtCorreo.getText().toString();
        Con = edtCon.getText().toString();
        if(rdMas.isChecked()){
            Gen="Masculino";
            idFoto=R.drawable.admin1;
        } else {
            Gen="Femenino";
            idFoto=R.drawable.admin;
        }
        NumDocLocal = edtNumDoc.getText().toString();
        switch(DocId){
            case "DNI":  if(NumDocLocal.length()<9){NumDoc=NumDocLocal;} else {Toast.makeText(this, "El dni tiene 8 caracteres", Toast.LENGTH_LONG).show();} ; break;
            case "Carné de extranjeria": if(NumDocLocal.length()<21){NumDoc=NumDocLocal;} else {Toast.makeText(this, "El carné de extranjeria tiene hasta 20 caracteres", Toast.LENGTH_LONG).show();} ; break;
            case "Pasaporte": if(NumDocLocal.length()<21){NumDoc=NumDocLocal;} else {Toast.makeText(this, "El pasaporte tiene hasta 20 caracteres", Toast.LENGTH_LONG).show();} ; break;
        }

        if(validar()){
            if (!validarEmail(Correo)){
                edtCorreo.setError("Email no válido");
            }else {
                daoCliente.registrarCliente(new Cliente(idFoto,platosTotales,platosFaltantes,premio, DocId, ApePat, ApeMat, Nom, Gen, FecNac, Correo, Con, NumDoc));
                listaClientes.add(new Cliente(idFoto,platosTotales,platosFaltantes,premio, DocId, ApePat, ApeMat, Nom, Gen, FecNac, Correo, Con, NumDoc));
                    limpiar();
            }
        }

    }
    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
    private void limpiar() {
        sprDocId.setSelection(0);
        edtApePat.setText("");
        edtApeMat.setText("");
        edtNom.setText("");
        edtFecNac.setText("");
        edtCorreo.setText("");
        edtCon.setText("");
        rdMas.setChecked(false);
        rdFem.setChecked(false);
        edtNumDoc.setText("");
        edtNom.requestFocus();
    }

    public void iniciarSesion(View view){
        Intent intent=new Intent(this,ActIniSesion.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("data",listaClientes);
        intent.putExtras(bundle);
        startActivity(intent);
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
}
