package domain.roles;

import domain.permisos.Permiso;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "rol")
public class Rol {

  @Id
  @GeneratedValue
  public long id;
  @Column(name = "nombre")
  public String nombre;
  @OneToMany
  @JoinColumn(name = "rol_id", referencedColumnName = "id")
  public List<Permiso> permisos;

  public boolean tenesPermiso(Permiso permiso) {

    return permisos.contains(permiso);
  }
}
