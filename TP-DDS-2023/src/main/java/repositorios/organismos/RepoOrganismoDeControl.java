package repositorios.organismos;

import domain.organismos.EntidadPrestadora;
import domain.organismos.OrganismoDeControl;
import domain.servicios.Servicio;
import domain.usuarios.Usuario;

import java.util.List;

public interface RepoOrganismoDeControl {
  public void add(OrganismoDeControl organismoDeControl);
  public void modificar(OrganismoDeControl organismoDeControl);
  public void eliminar(OrganismoDeControl organismoDeControl);

}
