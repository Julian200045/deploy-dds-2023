package models.entities.incidentes;

import models.entities.comunidades.Comunidad;
import models.entities.comunidades.Miembro;
import models.entities.servicios.PrestacionDeServicio;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
@Entity
@Table(name = "incidente")
public class Incidente {
  @Id
  @GeneratedValue
  private long id;

  @Getter
  @ManyToOne
  @JoinColumn(name = "prestacion_id", referencedColumnName = "id")
  private PrestacionDeServicio prestacionDeServicio;
  @Getter
  @ManyToOne
  @JoinColumn(name = "comunidad_id", referencedColumnName = "id")
  private Comunidad comunidad;
  @Getter
  @Column(name = "nombre")
  private String observaciones;
  @Getter
  @ManyToOne
  @JoinColumn(name = "miembro_apertura_id", referencedColumnName = "id")
  private Miembro miembroApertura;
  @Getter
  @ManyToOne
  @JoinColumn(name = "miembro_cierre_id", referencedColumnName = "id")
  private Miembro miembroCierre;
  @Getter
  @Column(name = "horario_apertura", columnDefinition = "TIMESTAMP")
  private LocalDateTime fechaYHoraDeApertura;
  @Getter
  @Column(name = "horario_cierre", columnDefinition = "TIMESTAMP")
  private LocalDateTime getFechaYHoraDeCierre;
  @Getter
  @Enumerated(EnumType.STRING)
  private EstadoIncidente estado;

  public Incidente(){

  }
  public Incidente(PrestacionDeServicio prestacionDeServicio, Comunidad comunidad, String observaciones, Miembro miembroApertura) {
    this.prestacionDeServicio = prestacionDeServicio;
    this.comunidad = comunidad;
    this.observaciones = observaciones;
    this.miembroApertura = miembroApertura;

    this.fechaYHoraDeApertura = LocalDateTime.now();
    this.estado = EstadoIncidente.ABIERTO;
  }

  public void cerrar(Miembro miembro){
    if(estado != EstadoIncidente.RESUELTO){
      miembroCierre = miembro;
      getFechaYHoraDeCierre = LocalDateTime.now();
      estado = EstadoIncidente.RESUELTO;
    }
  }
  public String entidadNombre(){
    return prestacionDeServicio.getEstablecimiento().entidad.getNombre();
  }

  public long tiempoDeCierre(){
    return ChronoUnit.HOURS.between(fechaYHoraDeApertura,LocalDateTime.now());
  }
}
