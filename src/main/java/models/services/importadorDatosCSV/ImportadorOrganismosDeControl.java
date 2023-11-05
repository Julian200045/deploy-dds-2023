package models.services.importadorDatosCSV;

import java.util.ArrayList;
import java.util.List;
import models.entities.organismos.EntidadPrestadora;
import models.entities.organismos.OrganismoDeControl;
import models.entities.servicios.Servicio;
import models.entities.usuarios.Usuario;
import models.repositorios.RepositorioEntidadesPrestadoras;
import models.repositorios.RepositorioOrganismoDeControl;
import models.repositorios.RepositorioServicios;
import models.repositorios.RepositorioUsuarios;

public class ImportadorOrganismosDeControl implements ImportadorDatosCSV {
  RepositorioServicios repoServicios;
  RepositorioEntidadesPrestadoras repoEntidadesPrestadoras;
  RepositorioUsuarios repoUsuarios;
  RepositorioOrganismoDeControl repoOrganismoDeControl;

  public ImportadorOrganismosDeControl(RepositorioEntidadesPrestadoras repoEntidades, RepositorioUsuarios repoUsuarios, RepositorioServicios repoServicio, RepositorioOrganismoDeControl repoOrganismoDeControl) {
    this.repoEntidadesPrestadoras = repoEntidades;
    this.repoUsuarios = repoUsuarios;
    this.repoServicios = repoServicio;
    this.repoOrganismoDeControl = repoOrganismoDeControl;
  }

  public void cargarDatos(List<String[]> datosACargar) {
    int i = 0;
    List<String[]> datosInvalidos = new ArrayList<>();
    while (!datosACargar.isEmpty()) {
      String[] lineaDeDatos = datosACargar.get(i);
      Usuario responsable = (Usuario) repoUsuarios.buscarPorNombre(lineaDeDatos[1]);
      Servicio servicio = (Servicio) repoServicios.buscarPorNombre(lineaDeDatos[3]);
      if (responsable == null || servicio == null) {
        datosInvalidos.add(datosACargar.get(i));
        datosACargar.remove(i);
        i++;
        continue;
      }
      OrganismoDeControl organismoDeControl = new OrganismoDeControl(lineaDeDatos[0], responsable, lineaDeDatos[2], this.leerEntidadesPrestadoras(lineaDeDatos), servicio);
      repoOrganismoDeControl.guardar(organismoDeControl);
      datosACargar.remove(i);
      i++;
    }
  }

  public List<Long> getIds(String[] datos) {
    List<Long> ids = new ArrayList<>();
    for (int i = 4; i < datos.length; i++) {
      ids.add(Long.parseLong(datos[i]));
    }
    return ids;
  }

  public List<EntidadPrestadora> leerEntidadesPrestadoras(String[] lineaDeDatos) {
    List<EntidadPrestadora> entidadesPrestadoras = new ArrayList<>();
    for (int i = 4; i < lineaDeDatos.length; i++) {
      entidadesPrestadoras.add((EntidadPrestadora) this.repoEntidadesPrestadoras.buscarPorNombre(lineaDeDatos[i]));
    }
    return entidadesPrestadoras;
  }
}
