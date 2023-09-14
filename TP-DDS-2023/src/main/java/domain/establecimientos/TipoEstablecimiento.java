package domain.establecimientos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tipoEstablecimiento")
@Setter
@Getter
public class TipoEstablecimiento {
  @Id
  @GeneratedValue
  long id;
  @Column
  private String nombre;
  @Column
  private String descripcion;

  public TipoEstablecimiento(){

  }
}
