package models.entities.servicios;

import models.entities.establecimientos.Establecimiento;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "prestacion_Servicio")
public class PrestacionDeServicio {
  @Getter
  @Id
  @GeneratedValue
  private long id;

  @Getter
  @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
  @JoinColumn(name = "servicio_id", referencedColumnName = "id")
  private Servicio servicio;
  @Getter
  @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
  @JoinColumn(name = "establecimiento_id", referencedColumnName = "id")
  private Establecimiento establecimiento;

  @Getter
  @Column(name = "habilitado")
  private boolean estaHabilitado;

  public PrestacionDeServicio(Servicio servicio, Establecimiento establecimiento) {
    this.servicio = servicio;
    this.establecimiento = establecimiento;
    this.estaHabilitado = true;
  }

  public PrestacionDeServicio(){

  }
}
