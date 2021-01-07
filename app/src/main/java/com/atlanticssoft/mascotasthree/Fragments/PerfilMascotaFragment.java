package com.atlanticssoft.mascotasthree.Fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atlanticssoft.mascotasthree.Adapters.MascotaAdaptador;
import com.atlanticssoft.mascotasthree.Adapters.PerfilMascotaAdaptador;
import com.atlanticssoft.mascotasthree.Models.Mascota;
import com.atlanticssoft.mascotasthree.Models.PerfilMascota;
import com.atlanticssoft.mascotasthree.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PerfilMascotaFragment extends Fragment {

    RecyclerView recyclerv_perfilmascota;
    ArrayList<PerfilMascota> mascotas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Asociamos la clase Fragment java con el respectivo layout xml
        View v = inflater.inflate(R.layout.fragment_perfil_mascota, container, false);

        FloatingActionButton myfab = v.findViewById(R.id.fab);
        myfab.setColorFilter(Color.WHITE);


        // Enlazo el recyclerview grafico con la lógica
        recyclerv_perfilmascota = (RecyclerView) v.findViewById(R.id.recyclerv_perfilmascota);


        // Inicializo la cantidad de columanas que tendra el GridLayoutManager
        int numberOfColumns = 3;

        // Ahora el codigo será en terminos de recycler view
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), numberOfColumns); // Organizo todas las tarjetas en forma de grilla con 3 columnas
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL); // Orientación en la que mostraré mi lista

        recyclerv_perfilmascota.setLayoutManager(gridLayoutManager);
        inicializarListaFotosMascota();
        inicializarAdaptador();

        return v;
    }

    public void inicializarListaFotosMascota() {
        mascotas = new ArrayList<PerfilMascota>();

        mascotas.add(new PerfilMascota(R.drawable.icons8_dog_96, 5));
        mascotas.add(new PerfilMascota(R.drawable.icons8_dog_96, 4));
        mascotas.add(new PerfilMascota(R.drawable.icons8_dog_96, 3));
        mascotas.add(new PerfilMascota(R.drawable.icons8_dog_96, 6));
        mascotas.add(new PerfilMascota(R.drawable.icons8_dog_96, 3));
        mascotas.add(new PerfilMascota(R.drawable.icons8_dog_96, 4));
        mascotas.add(new PerfilMascota(R.drawable.icons8_dog_96, 1));
        mascotas.add(new PerfilMascota(R.drawable.icons8_dog_96, 6));
        mascotas.add(new PerfilMascota(R.drawable.icons8_dog_96, 2));
        mascotas.add(new PerfilMascota(R.drawable.icons8_dog_96, 2));
        mascotas.add(new PerfilMascota(R.drawable.icons8_dog_96, 1));
        mascotas.add(new PerfilMascota(R.drawable.icons8_dog_96, 5));

    }

    public PerfilMascotaAdaptador adaptador;

    private void inicializarAdaptador() {
        adaptador = new PerfilMascotaAdaptador(mascotas, getActivity());
        recyclerv_perfilmascota.setAdapter(adaptador);
    }
}