package domain.repositorios.organismos;

import domain.entidades.Entidad;
import domain.organismos.EntidadPrestadora;
import domain.usuarios.Usuario;
import services.csv.LectorCSV;

import java.util.List;

public interface RepoEntidadesPrestadoras {
  void cargarEntidadesPrestadoras(LectorCSV lector) throws java.io.FileNotFoundException ,java.io.IOException, com.opencsv.exceptions.CsvValidationException ;
  List<Integer> getIds(String[] datos);
  void agregarEntidadPrestadora(int id, String nombre, Usuario responsable, String email, List<Entidad> entidades);

  List<EntidadPrestadora> devolverPorIds(List<Integer> ids);

  EntidadPrestadora devolverPorId(int id);
}
