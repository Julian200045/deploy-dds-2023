package models.entities.servicios;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
@Entity
@Table(name = "servicio")
public class Servicio {
  @Getter
  @Id
  @GeneratedValue
  private long id;

  @Getter
  @Column(name = "nombre")
  private String nombre;

  public Servicio(String nombre) {
    this.nombre = nombre;
  }

  public Servicio( ){

  }
}
