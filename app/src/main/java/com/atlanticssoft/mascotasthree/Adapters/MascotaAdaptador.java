package com.atlanticssoft.mascotasthree.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.atlanticssoft.mascotasthree.Bd.ConstructorMascotas;
import com.atlanticssoft.mascotasthree.Models.Mascota;
import com.atlanticssoft.mascotasthree.R;

import java.util.ArrayList;

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {

    // Construyo la lista de mascotas
    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity)
    {
        this.mascotas = mascotas;
        this.activity = activity;
    }

    ArrayList<Mascota> mascotas;
    Activity activity;

    // Infla el layout y lo pasará al viewHolder para que el obtenga los views
    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Le estoy dando vida al cardview_mascota
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MascotaViewHolder holder, int position)
    {
        Mascota mascota = mascotas.get(position);

        holder.ivFotoCv.setImageResource(mascota.getFoto());
        holder.tvNombreCv.setText(mascota.getNombre());

        // Debo colocar comillas dobles después de setText cuando se trabaja con números para Convertir de texto a enteros, es decir, setText(""+mascota.getContador());
        // Otra opción valida seria asi:  holder.tvContadorCv.setText(String.valueOf(mascota.getContador()) + " Raits");
        holder.tvContadorCv.setText(""+mascota.getContador()+ " Raits");
        //holder.tvContadorCv.setText(String.valueOf(mascota.getContador()) + " Raits");


        holder.btnHuesoWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(activity, " Raiteaste a "+ mascota.getNombre(), Toast.LENGTH_SHORT).show();

                raitearMascota();
            }


            private void raitearMascota() {

                // Instancio el Constructor de Mascotas
                ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);
                constructorMascotas.darRaitMascota(mascota);

                // Coloco el nuevo valor númerico en el hueso amarillo
                holder.tvContadorCv.setText(String.valueOf(constructorMascotas.obtenerRaitsMascota(mascota)));

                /*

                // Convierto a entero en numero del contador y lo asigno a la variable numero
                int numero = Integer.parseInt(holder.tvContadorCv.getText().toString());

                // Sumo 1 por cada vez que se presione el hueso blanco
                numero += 1;

                // Coloco el nuevo valor númerico en el hueso amarillo
                holder.tvContadorCv.setText(String.valueOf(numero));
                 */
            }
        });
    }

    @Override
    public int getItemCount() {
        // Cantidad de elementos que tiene mi lista de animales
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder{

        // Declaro todos los view que tengo en el cardview_mascota.xml
        private ImageView ivFotoCv;
        private TextView tvNombreCv, tvContadorCv;
        private ImageButton btnHuesoWhite;

        // ViewHolder asociara los objetos para darles vida
        public MascotaViewHolder(View itemView) {
            super(itemView);

            // Enlazo la parte de grafica con la parte logica
            ivFotoCv      = (ImageView) itemView.findViewById(R.id.ivFotoCv);
            tvNombreCv    = (TextView) itemView.findViewById(R.id.tvNombreCv);
            tvContadorCv  = (TextView) itemView.findViewById(R.id.tvContadorCv);
            btnHuesoWhite = (ImageButton) itemView.findViewById(R.id.btnHuesoWhite);
        }
    }
}
