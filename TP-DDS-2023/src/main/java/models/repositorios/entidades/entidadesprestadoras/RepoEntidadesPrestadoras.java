package models.repositorios.entidades.entidadesprestadoras;

import models.entities.entidades.Entidad;
import models.entities.organismos.EntidadPrestadora;
import models.entities.usuarios.Usuario;
import java.util.List;
import models.services.csv.LectorCSV;

public interface RepoEntidadesPrestadoras {
  void agregarEntidadPrestadora(int id, String nombre, Usuario responsable, String email, List<Entidad> entidades);

  List<EntidadPrestadora> devolverPorIds(List<Integer> ids);

  EntidadPrestadora devolverPorId(int id);
}
