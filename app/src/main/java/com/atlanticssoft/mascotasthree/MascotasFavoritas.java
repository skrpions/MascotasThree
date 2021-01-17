package com.atlanticssoft.mascotasthree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.atlanticssoft.mascotasthree.Adapters.MascotaAdaptador;
import com.atlanticssoft.mascotasthree.Adapters.PageAdaptador;
import com.atlanticssoft.mascotasthree.Models.Mascota;
import com.atlanticssoft.mascotasthree.VistaFragments.MascotasFavoritasFragment;

import java.util.ArrayList;

public class MascotasFavoritas extends AppCompatActivity {

    RecyclerView recyclerv_mascotasfavoritas;
    ArrayList<Mascota> mascotas;
    MascotaAdaptador adaptador;
    ViewPager viewPager;

    ImageView ivStar_favoritos;
    TextView tvPresioname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas_favoritas);

        // Habilito mi actionbar_favoritos personalizado, Recuerda Adicionar la propiedad android:parentActivityName=".MainActivity" en el Manifest para que funcione
        Toolbar miActionBar_Favoritos = (Toolbar) findViewById(R.id.miActionBar_Favoritos);
        setSupportActionBar(miActionBar_Favoritos);

        // Habilito la navegación hacia atrás.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Habilito el viewPagerFavoritos
        viewPager = (ViewPager) findViewById(R.id.viewPagerFavoritas);
        setUpViewPager();

        /*
        // Activo el menu de contexto
        //tvPresioname = (TextView) findViewById(R.id.tvPresioname);
        //registerForContextMenu(tvPresioname);

        // Enlazo el recyclerview grafico con la lógica
        recyclerv_mascotasfavoritas = (RecyclerView) findViewById(R.id.recyclerv_mascotasfavoritas);

        // Ahora el codigo será en terminos de recycler view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this); // Organizo todas las tarjetas una seguida de otra
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // Orientación en la que mostraré mi lista

        recyclerv_mascotasfavoritas.setLayoutManager(linearLayoutManager);
        inicializarListaMascotas();
        inicializarAdaptador();
        */
    }

    private void setUpViewPager() {
        viewPager.setAdapter(new PageAdaptador(getSupportFragmentManager(), agregarFragments()));
    }

    private ArrayList<Fragment> agregarFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new MascotasFavoritasFragment());

        return fragments;
    }

    /*
    public void inicializarListaMascotas(){
        mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota(R.drawable.bruno ,"Bruno",2));
        mascotas.add(new Mascota(R.drawable.gallina ,"Kirika",2));
        mascotas.add(new Mascota(R.drawable.karla ,"Karla",2));
        mascotas.add(new Mascota(R.drawable.pako ,"Paquito",3));
        mascotas.add(new Mascota(R.drawable.kusko ,"Kusko",3));
    }


    public MascotaAdaptador adaptador;
    private void inicializarAdaptador(){
        adaptador = new MascotaAdaptador(mascotas,this);
        recyclerv_mascotasfavoritas.setAdapter(adaptador);
    }

     */

    /* Programo el botón de Volver atrás del teléfono
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            Intent intent = new Intent(MascotasFavoritas.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    } */

    // Muestro el menú de opciones en el activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    // Controlar las opciones del menú de opciones
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.mnAcerca:
                Intent intentAcerca = new Intent(MascotasFavoritas.this, Acerca.class);
                startActivity(intentAcerca);
                break;

            case R.id.mnContacto:
                Intent intentContacto = new Intent(MascotasFavoritas.this, Configuraciones.class);
                startActivity(intentContacto);
                break;

            /*
            case R.id.mnConfiguraciones:
                Intent intentConfiguraciones = new Intent(MascotasFavoritas.this, Configuraciones.class);
                startActivity(intentConfiguraciones);
                break;

            case R.id.mnSalir:
                break;

                */

        }

        return super.onOptionsItemSelected(item);
    }

    // Muestro el menú de contexto en el activity cuando se mantenga presionado el view
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.menu_contexto, menu);
    }

    // Controlaré las opciones del menú de contexto que seleccione
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.mnEditar:
                Intent intentAcerca = new Intent(MascotasFavoritas.this, Acerca.class);
                startActivity(intentAcerca);
                break;

            case R.id.mnEliminar:
                Intent intentConfiguraciones = new Intent(MascotasFavoritas.this, Configuraciones.class);
                startActivity(intentConfiguraciones);
                break;

        }
        return super.onContextItemSelected(item);
    }

    /* Codigo para mostrar el menu de un Popup
    public void levantarMenuPopup(View v){
        ImageView imageView = (ImageView) findViewById(R.id.btnClickeame);
        PopupMenu popupMenu = new PopupMenu(this, imageView);
        popupMenu.getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());

        // Controlaré las opciones del Popup
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()){
                    case R.id.mnView:
                        Toast.makeText(getBaseContext(),getResources().getString(R.string.mnView_Titulo),Toast.LENGTH_SHORT).show();
                        //Intent intentAcerca = new Intent(MascotasFavoritas.this, Acerca.class);
                        //startActivity(intentAcerca);
                        break;

                    case R.id.mnViewDetail:
                        Toast.makeText(getBaseContext(),getResources().getString(R.string.mn_Detalles),Toast.LENGTH_SHORT).show();
                        //Intent intentConfiguraciones = new Intent(MascotasFavoritas.this, Configuraciones.class);
                        //startActivity(intentConfiguraciones);
                        break;

                }

                return true;
            }
        });

        popupMenu.show();
    }  */
}