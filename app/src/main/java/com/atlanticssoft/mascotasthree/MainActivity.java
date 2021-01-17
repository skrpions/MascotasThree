package com.atlanticssoft.mascotasthree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.atlanticssoft.mascotasthree.Adapters.PageAdaptador;
import com.atlanticssoft.mascotasthree.VistaFragments.PerfilMascotaFragment;
import com.atlanticssoft.mascotasthree.VistaFragments.RecyclerViewFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ImageView ivStar_favoritos;
    FloatingActionButton actionButton;

    // View's necesarios para usar Fragments
    Toolbar miActionBar;
    TabLayout tabLayout;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Habilito mi actionbar personalizado Recuerda Adicionar la propiedad android:parentActivityName=".MainActivity" en el Manifest para que funcione
        miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);


        if (miActionBar != null){
            setSupportActionBar(miActionBar);
        }

        //toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        setUpViewPager();


        ivStar_favoritos = (ImageView) findViewById(R.id.ivStar_favoritos);
        ivStar_favoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MascotasFavoritas.class);
                startActivity(intent);
            }
        });
    }

    // Agrego los fragments a la lista
    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();

        // Fragment que irá en el primer tab
        fragments.add(new RecyclerViewFragment());

        // Fragment que irá en el segundo tab
        fragments.add(new PerfilMascotaFragment());

        return fragments;
    }

    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdaptador(getSupportFragmentManager(),agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        // Asignaré los iconos a cada uno de los tab de acuerdo a su posición
        // Pasos: Descargar la img de icon8, luego convertirla en Image Asset: drawable > new > Image Asset
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_dog);
    }

    // Muestro el menú de opciones en el activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    // Controlar las opciones del menú de opciones
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.mnAcerca:
                Intent intentAcerca = new Intent(MainActivity.this, Acerca.class);
                startActivity(intentAcerca);
                break;

            case R.id.mnContacto:
                Intent intentContacto = new Intent(MainActivity.this, FormularioContacto.class);
                startActivity(intentContacto);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}