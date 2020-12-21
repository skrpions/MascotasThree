package com.atlanticssoft.mascotasthree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.atlanticssoft.mascotasthree.Adapters.MascotaAdaptador;
import com.atlanticssoft.mascotasthree.Models.Mascota;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerv_mascotas;
    ArrayList<Mascota> mascotas;

    ImageView ivStar_favoritos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Habilito mi actionbar personalizado
        Toolbar miAcionBar = (Toolbar) findViewById(R.id.miAcionBar);
        setSupportActionBar(miAcionBar);

        FloatingActionButton myfab = findViewById(R.id.fab);
        myfab.setColorFilter(Color.WHITE);


        // Enlazo el recyclerview grafico con la lógica
        recyclerv_mascotas = (RecyclerView) findViewById(R.id.recyclerv_mascotas);

        // Ahora el codigo será en terminos de recycler view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this); // Organizo todas las tarjetas una seguida de otra
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // Orientación en la que mostraré mi lista

        recyclerv_mascotas.setLayoutManager(linearLayoutManager);
        inicializarListaMascotas();
        inicializarAdaptador();


        ivStar_favoritos = (ImageView) findViewById(R.id.ivStar_favoritos);
        ivStar_favoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Favoritos.class);
                startActivity(intent);
                finish();
            }
        });

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
        adaptador = new MascotaAdaptador(mascotas,this);
        recyclerv_mascotas.setAdapter(adaptador);
    }
}