package domain.servicios;

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
  public long id;
  @Column(name = "nombre")
  public String nombre;

  public Servicio(int id, String nombre) {
    this.id = id;
    this.nombre = nombre;
  }

  public Servicio( ){

  }
}
