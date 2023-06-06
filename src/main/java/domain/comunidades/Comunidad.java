package domain.comunidades;

import domain.servicios.PrestacionDeServicio;

import java.util.ArrayList;
import java.util.List;

public class Comunidad {
    private String nombre;
    private List<Miembro> miembros = new ArrayList<>();
    private List<Miembro> administradores = new ArrayList<>();
    private List<PrestacionDeServicio> prestacionDeServicios = new ArrayList<>();

    public Comunidad(String nombre) {
        this.nombre = nombre;
    }
}