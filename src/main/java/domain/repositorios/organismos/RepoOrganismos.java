package domain.repositorios.organismos;

import domain.entidades.Entidad;
import domain.usuarios.Usuario;
import services.csv.LectorCSV;

import java.util.List;

public interface RepoOrganismos {
  void cargarOrganismos(LectorCSV lector) throws java.io.FileNotFoundException ,java.io.IOException, com.opencsv.exceptions.CsvValidationException ;
  List<Integer> getIds(String[] datos);
  void agregarOrganismo(String nombre, Usuario responsable, String email, List<Entidad> entidades);

}
