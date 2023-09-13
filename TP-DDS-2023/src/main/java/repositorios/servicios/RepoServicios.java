package repositorios.servicios;

import domain.servicios.Servicio;

public interface RepoServicios {
  Servicio devolverPorId(int id);

  void add(Servicio servicio);
}
