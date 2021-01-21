package com.atlanticssoft.mascotasthree.Bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.atlanticssoft.mascotasthree.Models.Mascota;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {

    // Esta clase es el puente directo a la base de datos

    private Context context;

    public BaseDatos(@Nullable Context context) {
        // Aquí se crea la base de datos, y ya exite simplemente la abre.
        // Las ConstantesBaseDatos.DATABASE_NAME y ConstantesBaseDatos.DATABASE_VERSION están en la clase ConstantesBaseDatos.java
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);

        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Aquí se crea toda la estructura de la bd, es decir, se crean las Tablas

        // Tabla :: Mascota :: Ojo. Los espacios juegan un papel muy importante, si le coloco espacios de más no funcionara
        String queryCrearTablaMascota = "CREATE TABLE " + ConstantesBaseDatos.TABLE_MASCOTA + "("+
                ConstantesBaseDatos.TABLE_MASCOTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE + " TEXT, " +
                ConstantesBaseDatos.TABLE_MASCOTA_FOTO + " INTEGER " +

                ")";

        // Tabla :: Raits
        String queryCrearTablaRaits = "CREATE TABLE " + ConstantesBaseDatos.TABLE_RAITS + "(" +

                ConstantesBaseDatos.TABLE_RAITS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_RAITS_MASCOTA_ID + " INTEGER, " + // Llave foránea
                ConstantesBaseDatos.TABLE_RAITS_CONTADOR + " INTEGER, " +

                // Declaro el campo que será foreign key
                "FOREIGN KEY (" + ConstantesBaseDatos.TABLE_RAITS_MASCOTA_ID + ") " +
                "REFERENCES " + ConstantesBaseDatos.TABLE_MASCOTA + "("+ ConstantesBaseDatos.TABLE_MASCOTA_ID +")"+ ")";

        // Ejecuto la creación de las tablas en Orden: Primero las tablas independientes y luego las dependientes
        db.execSQL(queryCrearTablaMascota);
        db.execSQL(queryCrearTablaRaits);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // De esta manera afectaré la base de datos cuando desee hacer una reestructuración en la misma
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_MASCOTA);
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_RAITS);

        // Vuelvo a crear las tablas
        onCreate(db);
    }

    // Método para obtener todas las mascotas
    public ArrayList<Mascota> obtenerTodasLasMascotas()
    {
        ArrayList<Mascota> mascotas = new ArrayList<>();

        // Consulta para obtener las mascotas
        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTA;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor registros =  db.rawQuery(query,null);

        while (registros.moveToNext())
        {
            // Empiezo a llenar el objeto Mascota
            Mascota mascotaActual = new Mascota();
            mascotaActual.setIdmascota(registros.getInt(0)); // 0 es la posición que ocupa el idmascota en la tabla mascota
            mascotaActual.setNombre(registros.getString(1)); // 1 es la posición que ocupa el nombre en la tabla mascota
            mascotaActual.setFoto(registros.getInt(2)); // 1 es la posición que ocupa la foto en la tabla mascota

            // Ejecuto un query que me recupere todos los raits de cada mascota
            String queryConsultarRaits = "SELECT COUNT("+ConstantesBaseDatos.TABLE_RAITS_CONTADOR+") as raits " +
                    " FROM " + ConstantesBaseDatos.TABLE_RAITS +
                    " WHERE " + ConstantesBaseDatos.TABLE_RAITS_MASCOTA_ID + "=" + mascotaActual.getIdmascota();

            // Recupero el query
            Cursor registrosRaits = db.rawQuery(queryConsultarRaits, null);

            if (registrosRaits.moveToNext())
            {
                mascotaActual.setContador(registrosRaits.getInt(0));
            }
            else {
                mascotaActual.setContador(0);
            }
            mascotas.add(mascotaActual);
        }
        //db.close();
        return mascotas;
    }


    // Método para agregar Mascotas
    public void insertarMascota(ContentValues contentValues)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_MASCOTA,null, contentValues);
        //db.close();
    }

    // Método para agregar los raits en las tabla Raits
    public void insertarRaitsMascota(ContentValues contentValues)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_RAITS,null, contentValues); // Inserto los datos en la tabla Raits

        // db.close();
    }

    // Método para obtener la cantidad de raits que se le ha dado a cada mascota
    public int obtenerRaitsMascota(Mascota mascota)
    {
        int raits = 0;

        String queryConsultarRaits = "SELECT COUNT("+ConstantesBaseDatos.TABLE_RAITS_CONTADOR+")" +
            " FROM " + ConstantesBaseDatos.TABLE_RAITS +
            " WHERE " + ConstantesBaseDatos.TABLE_RAITS_MASCOTA_ID + "="+mascota.getIdmascota();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(queryConsultarRaits, null);

        if (registros.moveToNext())
        {
            raits = registros.getInt(0);
        }

        // db.close(); // Cierro la conexión

        return raits;
    }

    // Método para obtener las últimas 5 mascotas favoritas
    public ArrayList<Mascota> obtenerTopCincoFavoritas() {
        ArrayList<Mascota> listaMascotasF = new ArrayList<>();


        String query =
                " SELECT mascota.idmascota, mascota.nombre, mascota.foto, raits.idmascota, raits.contador, SUM(raits.contador) AS TOTAL " +
                " FROM " + ConstantesBaseDatos.TABLE_MASCOTA +
                " INNER JOIN " + ConstantesBaseDatos.TABLE_RAITS +
                " ON " + ConstantesBaseDatos.TABLE_MASCOTA+"."+ConstantesBaseDatos.TABLE_MASCOTA_ID +
                " = " + ConstantesBaseDatos.TABLE_RAITS+"."+ConstantesBaseDatos.TABLE_MASCOTA_ID +
                " GROUP BY " + ConstantesBaseDatos.TABLE_MASCOTA+"."+ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE +
                " ORDER BY TOTAL " +
                " DESC LIMIT 5";

        Log.i("TETTO MTZ", query);


        SQLiteDatabase db= this.getReadableDatabase();
        Cursor registros = db.rawQuery(query, null);



        while (registros.moveToNext()){
            Mascota mascotaActual = new Mascota();
            mascotaActual.setIdmascota(registros.getInt(0));
            mascotaActual.setNombre(registros.getString(1));
            mascotaActual.setFoto(registros.getInt(2));


            String queryLikes = "SELECT COUNT("+ ConstantesBaseDatos.TABLE_RAITS_CONTADOR+") as likes " +
                    " FROM " + ConstantesBaseDatos.TABLE_RAITS +
                    " WHERE " + ConstantesBaseDatos.TABLE_RAITS_MASCOTA_ID + "=" + mascotaActual.getIdmascota();

            Cursor registrosLikes = db.rawQuery(queryLikes, null);
            if (registrosLikes.moveToNext()){

                mascotaActual.setContador(registrosLikes.getInt(0));
            }else {
                mascotaActual.setContador(0);
            }
            listaMascotasF.add(mascotaActual);
        }

        //db.close();

        return listaMascotasF;
    }
}
