package models.entities.ubicaciones;

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
  private long id;
  @Column(name = "latitud")
  private Double latitud;
  @Column(name = "longitud")
  private Double longitud;

  public Ubicacion(Double latitud, Double longitud) {
    this.latitud = latitud;
    this.longitud = longitud;
  }

  public Ubicacion(){

  }
}
