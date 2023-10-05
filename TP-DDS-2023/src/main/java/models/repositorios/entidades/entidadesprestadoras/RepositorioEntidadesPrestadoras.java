package models.repositorios.entidades.entidadesprestadoras;

import models.entities.entidades.Entidad;
import models.entities.organismos.EntidadPrestadora;
import models.entities.usuarios.Usuario;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

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
