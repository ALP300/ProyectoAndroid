package com.usmp.fia.pisimikhuy2.controlador;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.usmp.fia.pisimikhuy2.entity.Cliente;
import com.usmp.fia.pisimikhuy2.R;
import com.usmp.fia.pisimikhuy2.util.AdaptadorCarrusel;

import java.util.ArrayList;

public class ActMainNull extends AppCompatActivity{

    static ArrayList<Cliente> listaClientes;
    DrawerLayout drawerLayout;
    ImageView menu;
    LinearLayout inicio,nosotros,preguntas,registro,iniSesion;

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
        setContentView(R.layout.lyt_main_null);

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
        drawerLayout = findViewById(R.id.drawerLayout2);
        menu = findViewById(R.id.menu);
        inicio = findViewById(R.id.inicio);
        nosotros = findViewById(R.id.nosotros);
        preguntas = findViewById(R.id.preguntas);
        registro = findViewById(R.id.registro);
        iniSesion = findViewById(R.id.iniSesion);
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

            registro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    redirectActivity(ActMainNull.this, ActRegistroCliente.class);
                }
            });
            iniSesion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    redirectActivity(ActMainNull.this, ActIniSesion.class);
                }
            });
            nosotros.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    redirectActivity(ActMainNull.this, ActSobreNosotrosNull.class);
                }
            });
            preguntas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    redirectActivity(ActMainNull.this, ActPreguntasFrecuentesNull.class);
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
        stopAutoScroll();

    }

}