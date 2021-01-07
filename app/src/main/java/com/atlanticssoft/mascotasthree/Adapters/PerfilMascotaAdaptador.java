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
import com.atlanticssoft.mascotasthree.Models.PerfilMascota;
import com.atlanticssoft.mascotasthree.R;

import java.util.ArrayList;

public class PerfilMascotaAdaptador extends RecyclerView.Adapter<PerfilMascotaAdaptador.PerfilMascotaViewHolder> {


    // Construyo la lista de las fotos de la mascota
    public PerfilMascotaAdaptador(ArrayList<PerfilMascota> mascotas, Activity activity)
    {
        this.mascotas = mascotas;
        this.activity = activity;
    }

    ArrayList<PerfilMascota> mascotas;
    Activity activity;

    // Infla el layout y lo pasará al viewHolder para que el obtenga los views
    @NonNull
    @Override
    public PerfilMascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Le estoy dando vida al cardview_perfilmascota
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_perfil_mascota, parent, false);
        return new PerfilMascotaAdaptador.PerfilMascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PerfilMascotaViewHolder holder, int position) {

        PerfilMascota mascota = mascotas.get(position);

        holder.ivFotoPerfilMascotaCv.setImageResource(mascota.getFoto());

        // Debo colocar comillas dobles después de setText cuando se trabaja con números para Convertir de texto a enteros, es decir, setText(""+mascota.getContador());
        holder.tvContadorPerfilMascotaCv.setText(""+mascota.getContador());
    }

    @Override
    public int getItemCount()
    {
        // Cantidad de elementos que tiene mi lista de animales
        return mascotas.size();
    }

    public static class PerfilMascotaViewHolder extends RecyclerView.ViewHolder{

        // Declaro todos los view que tengo en el cardview_perfilmascota.xml
        private ImageView ivFotoPerfilMascotaCv;
        private TextView tvContadorPerfilMascotaCv;

        // ViewHolder asociara los objetos para darles vida
        public PerfilMascotaViewHolder(View itemView) {
            super(itemView);

            // Enlazo la parte de grafica con la parte logica
            ivFotoPerfilMascotaCv      = (ImageView) itemView.findViewById(R.id.ivFotoPerfilMascotaCv);
            tvContadorPerfilMascotaCv  = (TextView) itemView.findViewById(R.id.tvContadorPerfilMascotaCv);
        }
    }
}
