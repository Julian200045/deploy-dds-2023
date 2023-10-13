package models.services.importadorDatosCSV;

import java.util.ArrayList;
import java.util.List;
import models.entities.entidades.Entidad;
import models.entities.organismos.EntidadPrestadora;
import models.entities.usuarios.Usuario;
import models.repositorios.entidades.RepositorioEntidades;
import models.repositorios.entidadesprestadoras.RepositorioEntidadesPrestadoras;
import models.repositorios.usuarios.RepositorioUsuarios;
import models.services.csv.LectorCSV;

public class ImportadorEntidadesPrestadoras {
  LectorCSV lector;
  RepositorioEntidades repoEntidades;
  RepositorioUsuarios repoUsuarios;
  RepositorioEntidadesPrestadoras repoEntidadesPrestadoras;

  public ImportadorEntidadesPrestadoras(LectorCSV lector, RepositorioEntidades repoEntidades, RepositorioUsuarios repoUsuarios, RepositorioEntidadesPrestadoras repoEntidadesPrestadoras) {
    this.lector = lector;
    this.repoEntidades = repoEntidades;
    this.repoUsuarios = repoUsuarios;
    this.repoEntidadesPrestadoras = repoEntidadesPrestadoras;
  }

  public void cargarDatos() throws java.io.FileNotFoundException, java.io.IOException, com.opencsv.exceptions.CsvValidationException {

    while (!lector.getDatos().isEmpty()) {
      String[] datos = lector.getDatos().get(0);
      List<Long> ids = getIds(datos);
      List<Entidad> entidades = ids.stream().map(id -> (Entidad) repoEntidades.buscar(id)).toList();
      Usuario responsable = (Usuario) repoUsuarios.buscar(Long.parseLong(datos[1]));
      EntidadPrestadora entidadPrestadora = new EntidadPrestadora(datos[0], responsable, datos[2], entidades);
      repoEntidadesPrestadoras.guardar(entidadPrestadora);
      lector.getDatos().remove(0);
    }
  }

  public List<Long> getIds(String[] datos) {
    List<Long> ids = new ArrayList<>();
    for (int i = 3; i < datos.length; i++) {
      ids.add(Long.parseLong(datos[i]));
    }
    return ids;
  }
}
