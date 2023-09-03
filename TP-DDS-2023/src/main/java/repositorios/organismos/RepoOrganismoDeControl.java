package repositorios.organismos;

import domain.organismos.EntidadPrestadora;
import domain.servicios.Servicio;
import domain.usuarios.Usuario;

import java.util.List;

public interface RepoOrganismoDeControl {
  void agregarOrganismoDeControl(String nombre, Usuario responsable, String email, Servicio servicio, List<EntidadPrestadora> entidades);

}
