package dtos;

import containers.Comunidad;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RespuestaFusionComunidades {
  List<Comunidad> fusiones;
}
