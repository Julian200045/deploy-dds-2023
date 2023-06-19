package domain.entidades;

import domain.establecimientos.Establecimiento;
import java.util.List;
import java.util.stream.Collectors;

import domain.localizaciones.Localidad;
import lombok.Getter;

public class Entidad {
  @Getter
  public int id;
  public String nombre;
  private List<Establecimiento> establecimientos;
  public TipoEntidad tipo;

  public Entidad(int id, String nombre,TipoEntidad tipo) {
    this.id = id;
    this.nombre = nombre;
    this.tipo = tipo;
  }

  public List<Localidad> localidades(){
    return establecimientos.stream().map(establecimiento -> establecimiento.getLocalidad()).distinct().toList();
  }

  public void agregarEstablecimiento(Establecimiento establecimiento){
    if(tipo.getTiposDeEstablecimientosPermitidos().contains(establecimiento.tipo)){
      establecimientos.add(establecimiento);
    }
  }
}
