package domain.servicios;

import domain.establecimientos.Establecimiento;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "prestacion_Servicio")
public class PrestacionDeServicio {
  @Id
  @GeneratedValue
  private long id;
  @ManyToOne
  @JoinColumn(name = "servicio_id", referencedColumnName = "id")
  public Servicio servicio;
  @Getter
  @ManyToOne
  @JoinColumn(name = "establecimiento_id", referencedColumnName = "id")
  public Establecimiento establecimiento;

  @Getter
  @Column(name = "habilitado")
  public boolean estaHabilitado;

  public PrestacionDeServicio(Servicio servicio, Establecimiento establecimiento) {
    this.servicio = servicio;
    this.establecimiento = establecimiento;
    this.estaHabilitado = true;
  }

  public PrestacionDeServicio(){

  }
}
