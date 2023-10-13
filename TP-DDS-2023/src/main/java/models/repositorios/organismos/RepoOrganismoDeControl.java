package models.repositorios.organismos;

import models.entities.organismos.EntidadPrestadora;
import models.entities.organismos.OrganismoDeControl;
import models.entities.servicios.Servicio;
import models.entities.usuarios.Usuario;

import java.util.List;

public interface RepoOrganismoDeControl {
  public void add(OrganismoDeControl organismoDeControl);
  public void modificar(OrganismoDeControl organismoDeControl);
  public void eliminar(OrganismoDeControl organismoDeControl);

}
