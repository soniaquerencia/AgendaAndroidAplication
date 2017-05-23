package ipo2.agendadb;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactosSQLiteHelper extends SQLiteOpenHelper {
    /*Sentencia SQL para crear la tabla de Contactos*/
    String sqlCrearTabla = "CREATE TABLE Contactos(nombre TEXT, telefono TEXT)";

    public ContactosSQLiteHelper(Context contexto, String nombreBD, SQLiteDatabase.CursorFactory factory, int versionBD) {
        super(contexto, nombreBD, factory, versionBD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    /*Se ejecuta la sentencia SQL de creaci n de la tabla*/

        try {
            db.execSQL(sqlCrearTabla);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
/*NOTA: Por simplicidad del ejemplo aquí utilizamos directamente la opci n de eliminar la tabla anterior
y crearla de nuevo vac a con el nuevo formato. Sin embargo lo normal ser  que haya que migrar datos de
la tabla antigua a la nueva, por lo que este m todo deber a ser m s elaborado.*/
        try {
            /*Se elimina la versi n anterior de la table*/
            db.execSQL("DROP TABLE IF EXISTS Contactos");
            /*Se crea la nueva versi n de la table*/
            db.execSQL(sqlCrearTabla);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

