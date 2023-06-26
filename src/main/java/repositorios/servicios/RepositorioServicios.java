package repositorios.servicios;

import domain.servicios.Servicio;
import java.util.ArrayList;
import java.util.List;

public class RepositorioServicios implements RepoServicios {
  List<Servicio> servicios = new ArrayList<>();

  public Servicio devolverPorId(int id) {
    Servicio servicio;
    servicio = servicios.stream().filter(entidad1 -> entidad1.getId() == id).toList().get(0); //rompe aca en caso de que se pida un id que no existe
    return servicio;
  }

  public void agregarServicio(int id, String nombre) {
    servicios.add(new Servicio(id, nombre));
  }
}
