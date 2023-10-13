package models.services.importadorDatosCSV;

import java.util.ArrayList;
import java.util.List;
import models.entities.organismos.EntidadPrestadora;
import models.entities.organismos.OrganismoDeControl;
import models.entities.servicios.Servicio;
import models.entities.usuarios.Usuario;
import models.repositorios.entidadesprestadoras.RepositorioEntidadesPrestadoras;
import models.repositorios.organismos.RepositorioOrganismoDeControl;
import models.repositorios.servicios.RepositorioServicios;
import models.repositorios.usuarios.RepositorioUsuarios;
import models.services.csv.LectorCSV;

public class ImportadorOrganismos implements ImportadorDatosCSV {
  LectorCSV lector;
  RepositorioServicios repoServicios;
  RepositorioEntidadesPrestadoras repoEntidadesPrestadoras;
  RepositorioUsuarios repoUsuarios;
  RepositorioOrganismoDeControl repoOrganismoDeControl;

  public ImportadorOrganismos(LectorCSV lector, RepositorioEntidadesPrestadoras repoEntidades, RepositorioUsuarios repoUsuarios, RepositorioServicios repoServicio, RepositorioOrganismoDeControl repoOrganismoDeControl) {
    this.lector = lector;
    this.repoEntidadesPrestadoras = repoEntidades;
    this.repoUsuarios = repoUsuarios;
    this.repoServicios = repoServicio;
    this.repoOrganismoDeControl = repoOrganismoDeControl;
  }

  public void cargarDatos() throws java.io.FileNotFoundException, java.io.IOException, com.opencsv.exceptions.CsvValidationException {
    while (!lector.getDatos().isEmpty()) {
      String[] datos = lector.getDatos().get(0);
      List<Long> ids = getIds(datos);
      List<EntidadPrestadora> entidades = ids.stream().map(id -> (EntidadPrestadora) repoEntidadesPrestadoras.buscar(id)).toList();
      Usuario responsable = (Usuario) repoUsuarios.buscar(Long.parseLong(datos[1]));
      Servicio servicio = (Servicio) repoServicios.buscar(Long.parseLong(datos[3]));
      OrganismoDeControl organismoDeControl = new OrganismoDeControl(datos[0], responsable, datos[2], entidades, servicio);
      repoOrganismoDeControl.guardar(organismoDeControl);
      lector.getDatos().remove(0);
    }
  }

  public List<Long> getIds(String[] datos) {
    List<Long> ids = new ArrayList<>();
    for (int i = 4; i < datos.length; i++) {
      ids.add(Long.parseLong(datos[i]));
    }
    return ids;
  }

}
