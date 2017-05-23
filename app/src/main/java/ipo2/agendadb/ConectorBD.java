package ipo2.agendadb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ConectorBD {
    static final String NOMBRE_BD = "AgendaLocal";
    private ContactosSQLiteHelper dbHelper;
    private SQLiteDatabase db;




    /*Constructor*/
    public ConectorBD(Context ctx) {
        dbHelper = new ContactosSQLiteHelper(ctx, NOMBRE_BD, null, 1);
    }

    /*Abre la conexión con la base de datos*/
    public ConectorBD abrir() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    /*Cierra la conexión con la base de datos*/
    public void cerrar() {
        if (db != null) db.close();
    }

/*inserta un contacto en la BD*/
    public long insertarContacto(String nombre, String telefono) {
        ContentValues nuevoContacto = new ContentValues();
        nuevoContacto.put("nombre", nombre); nuevoContacto.put("telefono", telefono);
        return db.insert("Contactos", null, nuevoContacto);
    }
    /*devuelve todos los contactos*/
    public Cursor listarContactos() {
        return db.query("Contactos", new String[]{"nombre", "telefono"},
                null, null, null, null, null);
    }
}