package models.entities.permisos;

import javax.persistence.*;

@Entity
@Table(name = "permiso")
public class Permiso {
  @Id
  @GeneratedValue
  public long id;

  @Column(name = "nombre")
  public String nombre;
}
