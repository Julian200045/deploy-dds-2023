package domain.localizaciones;

import domain.ubicaciones.Ubicacion;
import java.util.List;
import lombok.Getter;


public class Provincia implements Localizacion {
  public Integer id;
  public String nombre;
  public Ubicacion ubicacion;
  @Getter
  private final List<Municipio> municipios;
  @Getter
  private final List<Departamento> departamentos;

  public Provincia(Integer id, String nombre, Ubicacion ubicacion, List<Municipio> municipios, List<Departamento> departamentos) {
    this.id = id;
    this.nombre = nombre;
    this.ubicacion = ubicacion;
    this.municipios = municipios;
    this.departamentos = departamentos;
  }

  @Override
  public Boolean seEncuentraEn(Ubicacion ubicacion) {
    return
        ubicacion == this.ubicacion
            || municipios.stream().anyMatch(municipio -> municipio.seEncuentraEn(ubicacion))
            || departamentos.stream().anyMatch(departamento -> departamento.seEncuentraEn(ubicacion));
  }

  public List<Municipio> municipios() {
    return municipios;
  }
}
