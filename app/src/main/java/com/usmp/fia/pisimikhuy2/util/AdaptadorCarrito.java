package com.usmp.fia.pisimikhuy2.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.usmp.fia.pisimikhuy2.R;
import com.usmp.fia.pisimikhuy2.controlador.ActMainCliente;
import com.usmp.fia.pisimikhuy2.entity.Cliente;
import com.usmp.fia.pisimikhuy2.entity.Comida;
import com.usmp.fia.pisimikhuy2.entity.Premio;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorCarrito extends RecyclerView.Adapter<AdaptadorCarrito.ProductosViewHolder> {

    Activity activity;
    List<Comida> carroCompra;
    TextView tvTotal;
    Spinner spListPremio;
    Cliente clienteIni;
    Button btnPagar;
    ArrayList<Cliente> listaClientes;
    double total = 0;

    public AdaptadorCarrito(Activity activity, List<Comida> carroCompra, TextView tvTotal,Cliente clienteIni,Spinner spListPremio,Button btnPagar,ArrayList<Cliente> listaClientes) {
        this.activity=activity;
        this.carroCompra = carroCompra;
        this.tvTotal = tvTotal;
        this.clienteIni=clienteIni;
        this.spListPremio=spListPremio;
        this.btnPagar=btnPagar;
        this.listaClientes= listaClientes;
        for(int i = 0 ; i < carroCompra.size() ; i++) {
            total = total + Double.parseDouble(""+carroCompra.get(i).getPrecio())*Integer.parseInt(""+carroCompra.get(i).getCantidad());
        }


        ArrayList<Premio> listPremio=clienteIni.getListaPremios();
        String descuentos[] = new String[listPremio.size()];

        for(int i=0;i<listPremio.size();i++){
            descuentos[i]=listPremio.get(i).getMsj();
        }
        ArrayAdapter<String> adapter= new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, descuentos);
        spListPremio.setAdapter(adapter);
        final Double[] totalDes = new Double[1];
        final Premio[] pEliminar = new Premio[1];
        spListPremio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                totalDes[0] = total-Double.valueOf(Math.round((total*listPremio.get(i).getDescuento())*100))/100;
                pEliminar[0]=null;
                if(i!=0){
                    pEliminar[0] =listPremio.get(i);
                }

                tvTotal.setText(totalDes[0]+"");


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });






        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CANTIDAD DE PLATOS COMPRADOS EN ESTA OCASION
                int cantidadP = 0;
                ArrayList<String> listaFacturas=new ArrayList<>();
                String factura = null;

                for(int i = 0 ; i < carroCompra.size() ; i++) {
                    cantidadP = cantidadP + Integer.parseInt(""+carroCompra.get(i).getCantidad());
                    if(factura==null){
                        factura="Plato: "+carroCompra.get(i).getNombre()+"\nCantidad: "+carroCompra.get(i).getCantidad()+"\n";
                    }else {
                        factura=factura+"Plato: "+carroCompra.get(i).getNombre()+"\nCantidad: "+carroCompra.get(i).getCantidad()+"\n";
                    }




                }
                int platosTotales= clienteIni.getPlatosTotales() + cantidadP;
                clienteIni.setPlatosTotales(platosTotales);

                //INICIAR PLATOS FALTANTES (0-4)
                int platosFaltantes=clienteIni.getPlatosFaltantes();

                int platosComprados=25-platosFaltantes+cantidadP;

                //PLATOS COMPRADOS<25
                if(platosComprados<25){
                    platosFaltantes=25-platosComprados;
                    clienteIni.setPlatosFaltantes(platosFaltantes);
                }else {//PLATOS COMPRADOS >=25
                    int premio=(int)Math.floor(platosComprados/25);
                    premio=premio+clienteIni.getPremio();
                    clienteIni.setPremio(premio);
                    platosComprados=platosComprados-premio*25;
                    platosFaltantes=25-platosComprados;
                    clienteIni.setPlatosFaltantes(platosFaltantes);
                }
                if(pEliminar[0]!=null){
                    listPremio.remove(pEliminar[0]);
                }

                clienteIni.setListaPremios(listPremio);

                ArrayList<String> facturasLista=new ArrayList<>();
                facturasLista=clienteIni.getListaFacturas();

                factura=factura+"\nTotal Pagado: S/."+totalDes[0]+"\n";
                facturasLista.add(factura);
                clienteIni.setListaFacturas(facturasLista);

                Intent intent = new Intent(activity, ActMainCliente.class);
                Bundle bundle1=new Bundle();
                Toast.makeText(activity,"Compra Finalizada",Toast.LENGTH_LONG).show();
                bundle1.putSerializable("data",listaClientes);
                bundle1.putSerializable("cliente",clienteIni);
                intent.putExtras(bundle1);
                activity.startActivity(intent);
            }
        });

    }

    @NonNull
    @Override
    public ProductosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lyt_item_carrito, null, false);
        return new AdaptadorCarrito.ProductosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductosViewHolder productosViewHolder, final int i) {
        productosViewHolder.tvNomProducto.setText(carroCompra.get(i).getNombre());
        productosViewHolder.tvDescripcion.setText(carroCompra.get(i).getDescripcion());
        productosViewHolder.tvPrecio.setText(""+carroCompra.get(i).getPrecio());
        productosViewHolder.tvCantidad.setText(""+carroCompra.get(i).getCantidad());
        productosViewHolder.imgComida.setImageResource(carroCompra.get(i).getIdFoto());

    }

    @Override
    public int getItemCount() {
        return carroCompra.size();
    }

    public class ProductosViewHolder extends RecyclerView.ViewHolder {

        TextView tvNomProducto, tvDescripcion, tvPrecio,tvCantidad;
        ImageView imgComida;
        public ProductosViewHolder(@NonNull View itemView) {
            super(itemView);
            imgComida=itemView.findViewById(R.id.imgComi);
            tvNomProducto = itemView.findViewById(R.id.txtNombre);
            tvDescripcion = itemView.findViewById(R.id.txtDescripcion);
            tvPrecio = itemView.findViewById(R.id.txtPrecio);
            tvCantidad = itemView.findViewById(R.id.txtCantidad);
        }
    }
}
