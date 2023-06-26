package com.usmp.fia.pisimikhuy2.controlador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.usmp.fia.pisimikhuy2.entity.Cliente;
import com.usmp.fia.pisimikhuy2.R;
import com.usmp.fia.pisimikhuy2.modelo.DAOCliente;
import com.usmp.fia.pisimikhuy2.util.AdaptadorCarrusel;

import java.util.ArrayList;

public class ActMainCliente extends AppCompatActivity{
    static ArrayList<Cliente> listaClientes;
    static Cliente clienteIni;
    DrawerLayout drawerLayout;
    ImageView menu;
    LinearLayout inicio, catalogo, premio, nosotros,preguntas,informacion, logout;

    DAOCliente daoCliente=new DAOCliente(this);
    ImageView instagram, facebook, youtube;
    String _url ="https://instagram.com/pisi_mikhuy?igshid=YmMyMTA2M2Y=";
    String _url1= "https://www.facebook.com/profile.php?id=100066845842815&mibextid=ZbWKwL";
    String _url2= "https://www.youtube.com/channel/UCDpYeLrYQCLxtkLV8TbXbHw";

    AdaptadorCarrusel adaptadorCarrusel;
    private final long AUTO_SCROLL_DELAY = 2500; // Tiempo de desplazamiento automático en milisegundos
    ViewPager2 viewPager;
    int images[] = {R.drawable.caldomote, R.drawable.cuychactado, R.drawable.pachamanca};
    Runnable autoScrollRunnable;
    private int currentItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_main_cliente);


        recuperarDatos();
        asignarReferencias();
        autoScrollRunnable = new Runnable() {
            @Override
            public void run() {
                if (currentItem == adaptadorCarrusel.getItemCount() - 1) {
                    currentItem = 0;
                } else {
                    currentItem++;
                }
                viewPager.setCurrentItem(currentItem, true);
                viewPager.postDelayed(autoScrollRunnable, AUTO_SCROLL_DELAY);
            }
        };

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                currentItem = position;
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
    @Override
    protected void onResume() {
        super.onResume();
        startAutoScroll();
    }



    private void startAutoScroll() {
        stopAutoScroll(); // Detener cualquier desplazamiento automático anterior antes de iniciar uno nuevo
        viewPager.postDelayed(autoScrollRunnable, AUTO_SCROLL_DELAY);
    }

    private void stopAutoScroll() {
        viewPager.removeCallbacks(autoScrollRunnable);
    }


    private void asignarReferencias() {
        drawerLayout = findViewById(R.id.drawerLayout);
        menu = findViewById(R.id.menu);
        inicio = findViewById(R.id.inicio);
        catalogo = findViewById(R.id.catalogo);
        premio = findViewById(R.id.premio);
        nosotros = findViewById(R.id.nosotros);
        preguntas = findViewById(R.id.preguntas);
        informacion= findViewById(R.id.informacion);
        logout = findViewById(R.id.cerrar);
        viewPager = findViewById(R.id.vpCarrusel);
        adaptadorCarrusel = new AdaptadorCarrusel(this, images);
        viewPager.setAdapter(adaptadorCarrusel);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer(drawerLayout);
            }
        });

            inicio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recreate();
                }
            });
            catalogo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    redirectActivity(ActMainCliente.this, ActCatalogo.class);
                }
            });
            premio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    redirectActivity(ActMainCliente.this, ActPremio.class);
                }
            });
            nosotros.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    redirectActivity(ActMainCliente.this, ActSobreNosotrosCliente.class);
                }
            });
            preguntas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    redirectActivity(ActMainCliente.this, ActPreguntasFrecuentesCliente.class);
                }
            });
            informacion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    redirectActivity(ActMainCliente.this, ActInfoCliente.class);
                }
            });
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder=
                            new AlertDialog.Builder(ActMainCliente.this  );
                    builder.setMessage("¿Desea cerrar sesión?")
                            .setTitle("Confirmación")
                            .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    /*
                                    ArrayList<Cliente> listaCli=daoCliente.getCliente();
                                    for(Cliente c:listaCli){
                                        if(clienteIni.getNumeroDocumento()==c.getNumeroDocumento()){
                                            daoCliente.editarCliente(clienteIni);
                                        }
                                    }

                                     */
                                    redirectActivity(ActMainCliente.this, ActMainNull.class);
                                    Toast.makeText(ActMainCliente.this, "Sesión cerrada", Toast.LENGTH_LONG).show();
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
        stopAutoScroll();
    }

}