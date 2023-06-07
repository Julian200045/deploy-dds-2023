package domain.establecimientos;

import domain.servicios.PrestacionDeServicio;
import domain.servicios.Servicio;
import domain.ubicaciones.Ubicacion;
import java.util.List;

public class Establecimiento {
  private String nombre;
  private Ubicacion ubicacion;
  private List<PrestacionDeServicio> prestaciones;

  public Establecimiento(String nombre, Ubicacion ubicacion) {
    this.nombre = nombre;
    this.ubicacion = ubicacion;
  }

  public void agregarPrestacionDeServicio(PrestacionDeServicio prestacion) {
    prestaciones.add(prestacion);
  }
}
