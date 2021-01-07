package com.atlanticssoft.mascotasthree.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.atlanticssoft.mascotasthree.Adapters.MascotaAdaptador;
import com.atlanticssoft.mascotasthree.Models.Mascota;
import com.atlanticssoft.mascotasthree.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class RecyclerViewFragment extends Fragment {

    RecyclerView recyclerv_mascotas;
    ArrayList<Mascota> mascotas;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        // Asociamos la clase Fragment java con el respectivo layout xml
        View v = inflater.inflate(R.layout.fragment_recyclerview,container,false);

        FloatingActionButton myfab = v.findViewById(R.id.fab);
        myfab.setColorFilter(Color.WHITE);


        // Enlazo el recyclerview grafico con la lógica
        recyclerv_mascotas = (RecyclerView) v.findViewById(R.id.recyclerv_mascotas);

        // Ahora el codigo será en terminos de recycler view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()); // Organizo todas las tarjetas una seguida de otra
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // Orientación en la que mostraré mi lista

        recyclerv_mascotas.setLayoutManager(linearLayoutManager);
        inicializarListaMascotas();
        inicializarAdaptador();

        return v;
    }

    public void inicializarListaMascotas(){
        mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota(R.drawable.icons8_cat_96 ,"Catty",5));
        mascotas.add(new Mascota(R.drawable.icons8_dog_96, "Ronny",4));
        mascotas.add(new Mascota(R.drawable.misifu ,"Misifú",3));
        mascotas.add(new Mascota(R.drawable.bruno ,"Bruno Morrongueras",2));
        mascotas.add(new Mascota(R.drawable.gallina ,"Gallinila Kirika",2));
        mascotas.add(new Mascota(R.drawable.karla ,"Karla",2));
        mascotas.add(new Mascota(R.drawable.pako ,"Paquito",3));
        mascotas.add(new Mascota(R.drawable.kusko ,"Kusko",3));
    }

    public MascotaAdaptador adaptador;
    private void inicializarAdaptador(){
        adaptador = new MascotaAdaptador(mascotas,getActivity());
        recyclerv_mascotas.setAdapter(adaptador);
    }
}
