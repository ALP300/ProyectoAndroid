package com.usmp.fia.pisimikhuy2.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.usmp.fia.pisimikhuy2.R;
import com.usmp.fia.pisimikhuy2.controlador.ActCarrito;
import com.usmp.fia.pisimikhuy2.entity.Cliente;
import com.usmp.fia.pisimikhuy2.entity.Comida;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AdaptadorCatalogo extends RecyclerView.Adapter<AdaptadorCatalogo.ProductosViewHolder> {

    Activity activity;
    int cantidad;
    TextView tvCantProductos;
    Button btnVerCarro;
    List<Comida> listaProductos;
    List<Comida> carroCompra;

    ArrayList<Cliente> listaClientes;
    Cliente clienteIni;
    public AdaptadorCatalogo(Activity activity, TextView tvCantProductos, Button btnVerCarro, List<Comida> listaProductos, List<Comida> carroCompra, ArrayList<Cliente> listaClientes, Cliente clienteIni) {
        this.activity=activity;
        this.tvCantProductos = tvCantProductos;
        this.btnVerCarro = btnVerCarro;
        this.listaProductos = listaProductos;
        this.carroCompra = carroCompra;
        this.listaClientes= listaClientes;
        this.clienteIni = clienteIni;
    }
    @NonNull
    @Override
    public ProductosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lyt_item_comida, null, false);
        return new AdaptadorCatalogo.ProductosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductosViewHolder productosViewHolder, @SuppressLint("RecyclerView") final int i) {
        productosViewHolder.tvNomProducto.setText(listaProductos.get(i).getNombre());
        productosViewHolder.tvDescripcion.setText(listaProductos.get(i).getDescripcion());
        productosViewHolder.tvPrecio.setText(""+listaProductos.get(i).getPrecio());
        productosViewHolder.imgComida.setImageResource(listaProductos.get(i).getIdFoto());
        productosViewHolder.spCantidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                productosViewHolder.cbCarro.setChecked(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        productosViewHolder.cbCarro.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cantidad=Integer.parseInt(productosViewHolder.spCantidad.getSelectedItem().toString());
                listaProductos.get(i).setCantidad(cantidad);
                if(productosViewHolder.cbCarro.isChecked() == true) {
                    tvCantProductos.setText(""+(Integer.parseInt(tvCantProductos.getText().toString().trim()) + 1));
                    carroCompra.add(listaProductos.get(i));

                } else if(productosViewHolder.cbCarro.isChecked() == false) {
                    tvCantProductos.setText(""+(Integer.parseInt(tvCantProductos.getText().toString().trim()) - 1));
                    carroCompra.remove(listaProductos.get(i));
                }
            }
        });
        btnVerCarro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ActCarrito.class);
                intent.putExtra("CarroCompras", (Serializable) carroCompra);
                Bundle bundle=new Bundle();
                bundle.putSerializable("listaClientes",listaClientes);
                bundle.putSerializable("cliente",clienteIni);
                intent.putExtras(bundle);
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ProductosViewHolder extends RecyclerView.ViewHolder {

        TextView tvNomProducto, tvDescripcion, tvPrecio;
        ImageView imgComida;
        Spinner spCantidad;
        CheckBox cbCarro;
        String cantidad []= {"1","2","3","4","5","6","7","8","9","10"};

        public ProductosViewHolder(@NonNull View itemView) {
            super(itemView);
            imgComida=itemView.findViewById(R.id.imgComi);
            tvNomProducto = itemView.findViewById(R.id.txtNombre);
            tvDescripcion = itemView.findViewById(R.id.txtDes);
            tvPrecio = itemView.findViewById(R.id.txtPrecio);
            spCantidad= itemView.findViewById(R.id.spCant);
            ArrayAdapter<String> adapter= new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1,cantidad);
            spCantidad.setAdapter(adapter);

            cbCarro = itemView.findViewById(R.id.cbCarro);
        }
    }
}
