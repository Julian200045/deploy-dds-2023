package domain.roles;

import domain.permisos.Permiso;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
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

    @OneToMany
    @JoinColumn(name = "rol_id", referencedColumnName = "id")
    public List<Permiso> permisos;

    public boolean tenesPermiso(Permiso permiso) {

        return permisos.contains(permiso);
    }
}
