package com.example.ejercicio14;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Contactos {
    SQLiteConexion conexion;
    // Nombre de la BDD
    public static final String NameDatabase = "ejercicio4";

    // Creacion de la tabla de la BDD
    public static final String tablaContactos = "contactos";

    //Campos de la tabla
    public static final String id = "id";
    public static final String nombres = "nombres";
    public static final String descripcion = "descripcion";
    public static final String imagen = "imagen";

    // Sentencias SQL para la creacion de tabla
    public static final String CreateTableContactos = "CREATE TABLE personasT ( id INTEGER PRIMARY KEY AUTOINCREMENT, nombres TEXT," +
            "descripcion TEXT, imagen TEXT";

    public static final String DroptableContactos = "DROP TABLE IF EXISTS contactos";



}
