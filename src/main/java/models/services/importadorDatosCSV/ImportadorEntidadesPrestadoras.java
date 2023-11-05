package models.services.importadorDatosCSV;

import java.util.ArrayList;
import java.util.List;
import models.entities.entidades.Entidad;
import models.entities.organismos.EntidadPrestadora;
import models.entities.usuarios.Usuario;
import models.repositorios.RepositorioEntidades;
import models.repositorios.RepositorioEntidadesPrestadoras;
import models.repositorios.RepositorioUsuarios;

public class ImportadorEntidadesPrestadoras implements ImportadorDatosCSV {
  RepositorioEntidades repoEntidades;
  RepositorioUsuarios repoUsuarios;
  RepositorioEntidadesPrestadoras repoEntidadesPrestadoras;

  public ImportadorEntidadesPrestadoras(RepositorioEntidades repoEntidades, RepositorioUsuarios repoUsuarios, RepositorioEntidadesPrestadoras repoEntidadesPrestadoras) {
    this.repoEntidades = repoEntidades;
    this.repoUsuarios = repoUsuarios;
    this.repoEntidadesPrestadoras = repoEntidadesPrestadoras;
  }

  public void cargarDatos(List<String[]> datosACargar) {
    int i = 0;
    List<String[]> datosInvalidos = new ArrayList<>(); //TODO: devolverle al usuario las lineas invalidas como feedback
    while (!datosACargar.isEmpty()) {
      String[] lineaDeDatos = datosACargar.get(i);
      Usuario responsable = (Usuario) repoUsuarios.buscarPorNombre(lineaDeDatos[1]);
      if (responsable == null) {
        datosInvalidos.add(datosACargar.get(i));
        datosACargar.remove(i);
        i++;
        continue;
      }
      EntidadPrestadora entidadPrestadora = new EntidadPrestadora(lineaDeDatos[0], responsable, lineaDeDatos[2], this.leerEntidades(lineaDeDatos));
      repoEntidadesPrestadoras.guardar(entidadPrestadora);
      datosACargar.remove(i);
    }
  }

  public List<Long> getIds(String[] datos) {
    List<Long> ids = new ArrayList<>();
    for (int i = 3; i < datos.length; i++) {
      ids.add(Long.parseLong(datos[i]));
    }
    return ids;
  }

  public List<Entidad> leerEntidades(String[] lineaDeDatos) {
    List<Entidad> entidades = new ArrayList<>();
    for(int i = 3; i < lineaDeDatos.length; i++) {
      entidades.add((Entidad) this.repoEntidades.buscarPorNombre(lineaDeDatos[i]));
    }
    return entidades;
  }
}
