package models.entities.roles;

import lombok.Getter;
import models.entities.permisos.Permiso;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "rol")
@Getter
public class Rol {

  @Id
  @GeneratedValue
  public long id;
  @Column(name = "nombre")
  public String nombre;
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
