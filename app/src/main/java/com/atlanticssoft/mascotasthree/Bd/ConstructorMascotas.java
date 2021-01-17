package com.atlanticssoft.mascotasthree.Bd;

import android.content.ContentValues;
import android.content.Context;

import com.atlanticssoft.mascotasthree.Models.Mascota;
import com.atlanticssoft.mascotasthree.R;

import java.util.ArrayList;

public class ConstructorMascotas
{
    private static final int RAIT = 1;
    private Context context;
    public ConstructorMascotas (Context context)
    {
        this.context = context;
    }

    public ArrayList<Mascota> obtenerDatos()
    {
        /*
        ArrayList<Mascota> mascotas = new ArrayList<>();

        mascotas.add(new Mascota(R.drawable.icons8_cat_96 ,"Catty",5));
        mascotas.add(new Mascota(R.drawable.icons8_dog_96, "Ronny",4));
        mascotas.add(new Mascota(R.drawable.misifu ,"Misifú",3));
        mascotas.add(new Mascota(R.drawable.bruno ,"Bruno",2));
        mascotas.add(new Mascota(R.drawable.gallina ,"Gallinila Kirika",2));
        mascotas.add(new Mascota(R.drawable.karla ,"Karla",2));
        mascotas.add(new Mascota(R.drawable.pako ,"Paquito",3));
        mascotas.add(new Mascota(R.drawable.kusko ,"Kusko",3));

        return mascotas;
         */

        // Ejecuto la conexión a mi base de datos
        BaseDatos db = new BaseDatos(context);
        insertarCincoMascotas(db);
        return db.obtenerTodasLasMascotas();
    }

    // Méodo que me ayuda a insertar mascotas en la bd
    public void insertarCincoMascotas(BaseDatos db)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, "Catty");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.icons8_cat_96);

        db.insertarMascota(contentValues);

        ContentValues contentValues2 = new ContentValues();
        contentValues2.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, "Ronny");
        contentValues2.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.icons8_dog_96);

        db.insertarMascota(contentValues2);

        ContentValues contentValues3 = new ContentValues();
        contentValues3.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, "Bruno");
        contentValues3.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.bruno);

        db.insertarMascota(contentValues3);

        ContentValues contentValues4 = new ContentValues();
        contentValues4.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, "Kirika");
        contentValues4.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.gallina);

        db.insertarMascota(contentValues4);

        ContentValues contentValues5 = new ContentValues();
        contentValues5.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, "Paquito");
        contentValues5.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.pako);

        db.insertarMascota(contentValues5);
    }

    // Este método recibe la mascota a la que le estoy dado rait
    public void darRaitMascota(Mascota mascota)
    {
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_RAITS_MASCOTA_ID, mascota.getIdmascota()); // Inserto el Id de la mascota a la que quiero raitear
        contentValues.put(ConstantesBaseDatos.TABLE_RAITS_CONTADOR, RAIT); // Inesrto el valor del rait, es decir, 1
        db.insertarRaitsMascota(contentValues); // Envio los datos al objeto db

    }

    public int obtenerRaitsMascota(Mascota mascota)
    {
        BaseDatos db = new BaseDatos(context);
        return db.obtenerRaitsMascota(mascota);
    }


}
