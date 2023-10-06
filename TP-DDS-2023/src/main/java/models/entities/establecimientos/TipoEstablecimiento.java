package models.entities.establecimientos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tipo_establecimiento")
@Setter
@Getter
public class TipoEstablecimiento {
  @Id
  @GeneratedValue
  private long id;
  @Column(name = "nombre")
  private String nombre;
  @Column(name = "descripcion")
  private String descripcion;

  public TipoEstablecimiento(String nombre, String descripcion) {
    this.nombre = nombre;
    this.descripcion = descripcion;
  }
  public TipoEstablecimiento(){

  }
}
