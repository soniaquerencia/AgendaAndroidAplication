package ipo2.agendadb;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sonia on 24/4/17.
 */

public class AdaptadorListaContactos extends ArrayAdapter<Contacto> {
    private Activity context;
    private ArrayList<Contacto> contactos;

    public AdaptadorListaContactos(Activity context, ArrayList<Contacto> contactos) {
        super(context, R.layout.layout_item_lista, contactos);
        this.context = context;
        this.contactos = contactos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View v = inflater.inflate(R.layout.layout_item_lista, null);

        TextView lblNombre = (TextView) v.findViewById(R.id.lblNombre);
        lblNombre.setText(contactos.get(position).

                getNombre());
        TextView lblTelefono = (TextView) v.findViewById(R.id.lblTelefono);
        lblTelefono.setText(contactos.get(position).

                getTelefono());
        return v;
    }
}