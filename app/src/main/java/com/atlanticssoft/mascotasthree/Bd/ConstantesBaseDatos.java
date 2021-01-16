package com.atlanticssoft.mascotasthree.Bd;

public final class ConstantesBaseDatos {

    // Declaro el nombre de la base de datos
    public static final String DATABASE_NAME = "mascotas_cloud";

    // Declaro la versión de la base de datos
    public static final int DATABASE_VERSION = 1;

    // Declaro los campos de las tabla Mascota
    public static final String TABLE_MASCOTAS = "mascota";
    public static final String TABLE_MASCOTAS_ID = "idmascota";
    public static final String TABLE_MASCOTAS_NOMBRE = "nombre";
    public static final String TABLE_MASCOTAS_FOTO = "foto";

    // Declaro los campos de las tabla Raits
    public static final String TABLE_RAITS = "raits";
    public static final String TABLE_RAITS_ID = "idraits";
    public static final String TABLE_RAITS_CONTADOR = "contador";
    public static final String TABLE_RAITS_MASCOTAS_ID = "mascotaid"; // Llave foránea


}
