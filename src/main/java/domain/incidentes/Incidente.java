package domain.incidentes;

import domain.comunidades.Comunidad;
import domain.comunidades.Miembro;
import domain.servicios.PrestacionDeServicio;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

public class Incidente {
  @Getter
  final private PrestacionDeServicio prestacionDeServicio;
  @Getter
  final private Comunidad comunidad;
  @Getter
  final private String observaciones;
  @Getter
  final private Miembro miembroApertura;
  @Getter
  private Miembro miembroCierre;
  @Getter
  private final LocalDateTime fechaYHoraDeApertura;
  @Getter
  private LocalDateTime getFechaYHoraDeCierre; //tener en cuenta que solo se puede cambiar una vez TODO
  @Getter
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
}
