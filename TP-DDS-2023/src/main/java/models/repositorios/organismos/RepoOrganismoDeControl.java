package models.repositorios.organismos;

import models.entities.organismos.EntidadPrestadora;
import models.entities.servicios.Servicio;
import models.entities.usuarios.Usuario;

import java.util.List;

public interface RepoOrganismoDeControl {
  void agregarOrganismoDeControl(String nombre, Usuario responsable, String email, Servicio servicio, List<EntidadPrestadora> entidades);

}
