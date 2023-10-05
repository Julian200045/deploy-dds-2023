package models.repositorios.servicios;

import models.entities.servicios.Servicio;

public interface RepoServicios {
  Servicio devolverPorId(int id);

  void add(Servicio servicio);
}
