package domain.roles;

import domain.permisos.Permiso;

import java.util.List;

public class RolDelMiembro {

    public String nombre;
    public List<Permiso> permisos;

    public boolean tenesPermiso(Permiso permiso) {

        return permisos.contains(permiso);
    }
}
