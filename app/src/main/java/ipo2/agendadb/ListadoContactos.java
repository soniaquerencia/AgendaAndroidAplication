package ipo2.agendadb;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListadoContactos extends AppCompatActivity {
    private EditText txtNombre;
    private EditText txtTelefono;
    private ImageButton btnAniadirContacto;
    private ImageButton btnListarContactos;
    private ListView lstContactos;
    private ConectorBD conectorBD;
    private ArrayList<Contacto> contactos = new ArrayList<>();
    private AdaptadorListaContactos adaptadorLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtTelefono = (EditText) findViewById(R.id.txtTelefono);
        btnAniadirContacto = (ImageButton) findViewById(R.id.btnAniadirContacto);
        btnListarContactos = (ImageButton) findViewById(R.id.btnListarContactos);
        lstContactos = (ListView) findViewById(R.id.lstContactos);
        setContentView(R.layout.activity_listado_contactos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
        conectorBD = new ConectorBD(this);
        btnAniadirContacto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                conectorBD.abrir();
                conectorBD.insertarContacto(txtNombre.getText().toString(),
                        txtTelefono.getText().toString());
                conectorBD.cerrar();
                Toast.makeText(getBaseContext(), "Se añadió un nuevo contacto a la BD local!", Toast.LENGTH_SHORT).show();
            }
        });
        adaptadorLista = new AdaptadorListaContactos(this, contactos);
        lstContactos.setAdapter(adaptadorLista);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_listado_contactos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick (View v){
        contactos.removeAll(contactos);
        conectorBD.abrir();
        Cursor c = conectorBD.listarContactos();
        if (c.moveToFirst()) {
            do {
                Contacto contacto = new Contacto(null, null);
                contacto.setNombre(c.getString(0));
                contacto.setTelefono(c.getString(1));
                contactos.add(contacto);
            } while (c.moveToNext());
        }
        c.close();
        conectorBD.cerrar();
        ((BaseAdapter) lstContactos.getAdapter()).notifyDataSetChanged();
    }
}
