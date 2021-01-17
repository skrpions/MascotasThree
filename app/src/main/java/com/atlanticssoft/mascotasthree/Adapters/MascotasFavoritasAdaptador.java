package com.atlanticssoft.mascotasthree.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.atlanticssoft.mascotasthree.Models.Mascota;
import com.atlanticssoft.mascotasthree.R;

import java.util.ArrayList;

public class MascotasFavoritasAdaptador extends RecyclerView.Adapter<MascotasFavoritasAdaptador.MascotasFavoritasViewHolder> {

    ArrayList<Mascota> mascotas;
    Activity activity;

    // Construyo la lista de mascotas
    public MascotasFavoritasAdaptador(ArrayList<Mascota> mascotas, Activity activity){

        this.mascotas = mascotas;
        this.activity  = activity;
    }

    @NonNull
    @Override
    public MascotasFavoritasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);

        return new MascotasFavoritasViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MascotasFavoritasViewHolder holder, int position) {

        // Pasamos la lista de contactos
        // Seteamos los valores

        Mascota mascota = mascotas.get(position);

        holder.ivFotoCv.setImageResource(mascota.getFoto());
        holder.tvNombreCv.setText(mascota.getNombre());
        // Debo colocar comillas dobles después de setText cuando se trabaja con números para Convertir de texto a enteros, es decir, setText(""+mascota.getContador());
        holder.tvContadorCv.setText(""+mascota.getContador()+ " Raits");
    }

    @Override
    public int getItemCount() {
        // Cantidad de elementos que tiene mi lista de mascotas favoritas
        return mascotas.size();
    }

    public static class MascotasFavoritasViewHolder extends RecyclerView.ViewHolder {

        // Declaro todos los view que tengo en el cardview_mascota.xml
        private ImageView ivFotoCv;
        private TextView tvNombreCv, tvContadorCv;
        private ImageButton btnHuesoWhite;

        public MascotasFavoritasViewHolder(View itemView) {
            super(itemView);

            // Enlazo la parte de grafica con la parte logica
            ivFotoCv      = (ImageView) itemView.findViewById(R.id.ivFotoCv);
            tvNombreCv    = (TextView) itemView.findViewById(R.id.tvNombreCv);
            tvContadorCv  = (TextView) itemView.findViewById(R.id.tvContadorCv);
            btnHuesoWhite = (ImageButton) itemView.findViewById(R.id.btnHuesoWhite);
        }
    }
}
