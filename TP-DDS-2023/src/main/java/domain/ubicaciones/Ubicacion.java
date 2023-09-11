package domain.ubicaciones;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ubicacion")
public class Ubicacion {
  @Id
  @GeneratedValue
  long id;
  @Column
  private final Double latitud;
  @Column
  private final Double longitud;

  public Ubicacion(Double latitud, Double longitud) {
    this.latitud = latitud;
    this.longitud = longitud;
  }
}
