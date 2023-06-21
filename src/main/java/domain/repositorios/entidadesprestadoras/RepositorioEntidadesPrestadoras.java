package domain.repositorios.entidadesprestadoras;

import domain.repositorios.entidades.RepoEntidades;
import domain.repositorios.entidadesprestadoras.RepoEntidadesPrestadoras;
import domain.repositorios.usuarios.RepoUsuarios;
import java.util.stream.Collectors;
import services.csv.LectorCSV;
import domain.entidades.Entidad;
import domain.organismos.EntidadPrestadora;
import domain.repositorios.entidades.RepoEntidades;
import domain.repositorios.usuarios.RepoUsuarios;
import domain.usuarios.Usuario;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import services.csv.LectorCSV;

public class RepositorioEntidadesPrestadoras implements RepoEntidadesPrestadoras {
  @Getter
  List<EntidadPrestadora> entidadesPrestadoras = new ArrayList<>();

  public void agregarEntidadPrestadora(int id, String nombre, Usuario responsable, String email, List<Entidad> entidades) {
    entidadesPrestadoras.add(new EntidadPrestadora(id, nombre, responsable, email, entidades));
  }

  public EntidadPrestadora devolverPorId(int id) {
    EntidadPrestadora entidad;

    entidad = entidadesPrestadoras.stream().filter(entidad1 -> entidad1.getId() == id).toList().get(0);
    return entidad;
  }

  public List<EntidadPrestadora> devolverPorIds(List<Integer> ids) {
    List<EntidadPrestadora> listaEntidades = new ArrayList<>();
    for (int i = 0; i < ids.size(); i++) {
      listaEntidades.add(devolverPorId(ids.get(i)));
    }
    return listaEntidades;
  }
}
