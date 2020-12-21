package com.atlanticssoft.mascotasthree;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.atlanticssoft.mascotasthree.Adapters.MascotaAdaptador;
import com.atlanticssoft.mascotasthree.Models.Mascota;

import java.util.ArrayList;

public class Favoritos extends AppCompatActivity {

    RecyclerView recyclerv_mascotasfavoritas;
    ArrayList<Mascota> mascotas;

    ImageView ivStar_favoritos;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        // Habilito mi actionbar_favoritos personalizado
        Toolbar miAcionBar_Favoritos = (Toolbar) findViewById(R.id.miActionBar_Favoritos);
        setSupportActionBar(miAcionBar_Favoritos);

        // Habilito la navegación hacia atrás
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  
        // Enlazo el recyclerview grafico con la lógica
        recyclerv_mascotasfavoritas = (RecyclerView) findViewById(R.id.recyclerv_mascotasfavoritas);

        // Ahora el codigo será en terminos de recycler view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this); // Organizo todas las tarjetas una seguida de otra
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // Orientación en la que mostraré mi lista

        recyclerv_mascotasfavoritas.setLayoutManager(linearLayoutManager);
        inicializarListaMascotas();
        inicializarAdaptador();

    }

    public void inicializarListaMascotas(){
        mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota(R.drawable.bruno ,"Bruno Morrongueras",2));
        mascotas.add(new Mascota(R.drawable.gallina ,"Gallinila Kirika",2));
        mascotas.add(new Mascota(R.drawable.karla ,"Karla",2));
        mascotas.add(new Mascota(R.drawable.pako ,"Paquito",3));
        mascotas.add(new Mascota(R.drawable.kusko ,"Kusko",3));
    }

    public MascotaAdaptador adaptador;
    private void inicializarAdaptador(){
        adaptador = new MascotaAdaptador(mascotas,this);
        recyclerv_mascotasfavoritas.setAdapter(adaptador);
    }

    @Override public boolean onSupportNavigateUp() {
        onBackPressed();
        return true; }

    //
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            Intent intent = new Intent(Favoritos.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}