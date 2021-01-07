package com.atlanticssoft.mascotasthree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

public class Acerca extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca);

        // Habilito mi actionbar_favoritos personalizado, Recuerda Adicionar la propiedad android:parentActivityName=".MainActivity" en el Manifest para que funcione
        Toolbar miActionBar_Favoritos = (Toolbar) findViewById(R.id.miActionBar_Favoritos);
        setSupportActionBar(miActionBar_Favoritos);

        // Habilito la navegación hacia atrás.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // Programo el botón de Volver atrás del teléfono
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {

        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}