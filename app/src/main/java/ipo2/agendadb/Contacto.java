package ipo2.agendadb;

/**
 * Created by sonia on 24/4/17.
 */

public class Contacto {
    private String nombre;
    private String telefono;
    public Contacto(String nombre, String telefono) { this.nombre = nombre;
        this.telefono = telefono;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) { this.nombre = nombre;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}