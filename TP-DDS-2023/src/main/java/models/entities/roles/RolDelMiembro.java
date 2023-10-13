package models.entities.roles;

import models.entities.permisos.Permiso;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "rol_miembro")
public class RolDelMiembro {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "nombre")
    public String nombre;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_id", referencedColumnName = "id")
    public List<Permiso> permisos;

    public boolean tenesPermiso(Permiso permiso) {

        return permisos.contains(permiso);
    }
}
