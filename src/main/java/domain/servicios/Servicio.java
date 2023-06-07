package domain.servicios;

import lombok.Getter;

public class Servicio {
    @Getter
    public int id;
    public String nombre;

    public Servicio(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
