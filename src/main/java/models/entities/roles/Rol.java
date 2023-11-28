package models.entities.roles;

import lombok.Getter;
import lombok.Setter;
import models.entities.comunidades.TipoMiembro;
import models.entities.permisos.Permiso;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "rol")
@Getter
@Setter
public class Rol {

  @Id
  @GeneratedValue
  public long id;
  @Column(name = "nombre")
  public String nombre;

  @Enumerated(EnumType.STRING)
  @Column(name = "tipo_rol")
  private TipoRol tipoRol;
  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "rol_id", referencedColumnName = "id")
  public List<Permiso> permisos;


  public Rol(String nombre){
    this.nombre = nombre;
  }
  public Rol(){

  }
  public boolean tenesPermiso(Permiso permiso) {

    return permisos.contains(permiso);
  }
}
