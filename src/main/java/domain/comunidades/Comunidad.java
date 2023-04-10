package domain.comunidades;

import java.util.ArrayList;
import java.util.List;

public class Comunidad{
    private String nombre;
    private List<Miembro> miembros = new ArrayList<>();

    public Comunidad(String nombre) {
        this.nombre = nombre;
    }
}
