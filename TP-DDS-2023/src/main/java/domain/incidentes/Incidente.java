package domain.incidentes;

import domain.comunidades.Comunidad;
import domain.comunidades.Miembro;
import domain.entidades.Entidad;
import domain.servicios.PrestacionDeServicio;
import java.time.temporal.ChronoUnit;
import javax.persistence.*;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
@Entity
@Table(name = "incidente")
public class Incidente {
  @Id
  @GeneratedValue
  long id;

  @Getter
  @ManyToOne
  @JoinColumn(name = "prestacion_id", referencedColumnName = "id")
  final private PrestacionDeServicio prestacionDeServicio;
  @Getter
  @ManyToOne
  @JoinColumn(name = "comunidad_id", referencedColumnName = "id")
  final private Comunidad comunidad;
  @Getter
  @Column
  final private String observaciones;
  @Getter
  @ManyToOne
  @JoinColumn(name = "miembro_apertura_id", referencedColumnName = "id")
  final private Miembro miembroApertura;
  @Getter
  @ManyToOne
  @JoinColumn(name = "miembro_cierre_id", referencedColumnName = "id")
  private Miembro miembroCierre;
  @Getter
  @Column
  private final LocalDateTime fechaYHoraDeApertura;
  @Getter
  @Column
  private LocalDateTime getFechaYHoraDeCierre;
  @Getter
  @Enumerated(EnumType.STRING)
  private EstadoIncidente estado;

  public Incidente(PrestacionDeServicio prestacionDeServicio, Comunidad comunidad, String observaciones, Miembro miembroApertura) {
    this.prestacionDeServicio = prestacionDeServicio;
    this.comunidad = comunidad;
    this.observaciones = observaciones;
    this.miembroApertura = miembroApertura;

    fechaYHoraDeApertura = LocalDateTime.now();
    estado = EstadoIncidente.ABIERTO;
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
