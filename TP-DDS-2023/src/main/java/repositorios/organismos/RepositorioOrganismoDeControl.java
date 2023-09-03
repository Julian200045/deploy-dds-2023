package repositorios.organismos;

import domain.organismos.EntidadPrestadora;
import domain.organismos.OrganismoDeControl;
import domain.servicios.Servicio;
import domain.usuarios.Usuario;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class RepositorioOrganismoDeControl implements RepoOrganismoDeControl {
  @Getter
  List<OrganismoDeControl> organismosDeControl = new ArrayList<>();
  public void agregarOrganismoDeControl(String nombre, Usuario responsable, String email, Servicio servicio, List<EntidadPrestadora> entidades) {
    organismosDeControl.add(new OrganismoDeControl(nombre, responsable, email, entidades, servicio));
  }
}
