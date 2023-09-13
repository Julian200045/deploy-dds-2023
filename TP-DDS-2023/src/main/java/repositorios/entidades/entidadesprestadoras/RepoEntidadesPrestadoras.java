package repositorios.entidades.entidadesprestadoras;

import domain.entidades.Entidad;
import domain.organismos.EntidadPrestadora;
import domain.usuarios.Usuario;
import java.util.List;
import services.csv.LectorCSV;

public interface RepoEntidadesPrestadoras {
  void agregarEntidadPrestadora(int id, String nombre, Usuario responsable, String email, List<Entidad> entidades);

  List<EntidadPrestadora> devolverPorIds(List<Integer> ids);

  EntidadPrestadora devolverPorId(int id);
}
