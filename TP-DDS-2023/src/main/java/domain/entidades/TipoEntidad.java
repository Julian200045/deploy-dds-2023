package domain.entidades;

import domain.establecimientos.TipoEstablecimiento;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;

import java.util.List;
@Entity
@Table(name = "tipoEntidad")
public class TipoEntidad {
  @Id
  @GeneratedValue
  long id;
  @Column
  public String nombre;
  @Column
  public String descripcion;
  @Getter
  @ManyToMany
  private List<TipoEstablecimiento> tiposDeEstablecimientosPermitidos;

  public TipoEntidad(String nombre, String descripcion, List<TipoEstablecimiento> tiposDeEstablecimientosPermitidos) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.tiposDeEstablecimientosPermitidos = tiposDeEstablecimientosPermitidos;
  }
}
