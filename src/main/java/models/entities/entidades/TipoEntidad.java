package models.entities.entidades;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import models.entities.establecimientos.TipoEstablecimiento;

@Entity
@Table(name = "tipo_entidad")
public class TipoEntidad {
  @Id
  @GeneratedValue
  private long id;
  @Column(name = "nombre")
  public String nombre;
  @Column(name = "descripcion")
  public String descripcion;
  @Getter
  @ManyToMany
  private List<TipoEstablecimiento> tiposDeEstablecimientosPermitidos;

  public TipoEntidad(String nombre, String descripcion, List<TipoEstablecimiento> tiposDeEstablecimientosPermitidos) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.tiposDeEstablecimientosPermitidos = tiposDeEstablecimientosPermitidos;
  }

  public TipoEntidad() {

  }
}
