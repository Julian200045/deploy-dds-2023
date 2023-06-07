package domain.repositorios.organismos;

import domain.entidades.Entidad;
import domain.organismos.EntidadPrestadora;
import domain.organismos.OrganismoDeControl;
import domain.repositorios.entidades.RepoEntidades;
import domain.repositorios.servicios.RepoServicios;
import domain.repositorios.usuarios.RepoUsuarios;
import domain.servicios.Servicio;
import domain.usuarios.Usuario;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import services.csv.LectorCSV;

public interface RepoOrganismoDeControl {
  void agregarOrganismoDeControl(String nombre, Usuario responsable, String email, Servicio servicio, List<EntidadPrestadora> entidades);

  List<Integer> getIds(String[] datos);

  void cargarOrganismosDeControl(LectorCSV lector) throws java.io.FileNotFoundException, java.io.IOException, com.opencsv.exceptions.CsvValidationException;

}
