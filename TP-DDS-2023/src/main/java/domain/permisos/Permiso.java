package domain.permisos;

import javax.persistence.*;

@Entity
@Table(name = "permiso")
public class Permiso {
  @Id
  @GeneratedValue
  public long id;

  @Column
  public String nombre;
}
