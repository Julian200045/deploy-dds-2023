package csv;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import models.entities.entidades.Entidad;
import models.entities.entidades.TipoEntidad;
import models.entities.organismos.EntidadPrestadora;
import models.entities.servicios.Servicio;
import models.entities.usuarios.Usuario;
import models.repositorios.entidades.RepositorioEntidades;
import models.repositorios.entidadesprestadoras.RepositorioEntidadesPrestadoras;
import models.repositorios.organismos.RepositorioOrganismoDeControl;
import models.repositorios.servicios.RepositorioServicios;
import models.repositorios.tipoentidad.RepositorioTipoEntidad;
import models.repositorios.usuarios.RepositorioUsuarios;
import models.services.csv.LectorCSV;
import models.services.importadorDatosCSV.ImportadorEntidadesPrestadoras;
import models.services.importadorDatosCSV.ImportadorOrganismos;
import org.junit.jupiter.api.Test;

public class CSVTest {

  RepositorioEntidades repoEntidades = new RepositorioEntidades();
  RepositorioUsuarios repoUsuarios = new RepositorioUsuarios();
  RepositorioServicios repoServicios = new RepositorioServicios();
  RepositorioEntidadesPrestadoras repoEntidadesPrestadoras = new RepositorioEntidadesPrestadoras();

  @Test
  public void lectorLeeAlIniciar() throws java.io.IOException, com.opencsv.exceptions.CsvValidationException {
    LectorCSV lector = new LectorCSV("src/main/resources/template/project.properties", "organismos-de-control-csv-path");
    assertTrue(lector.getDatos().size() > 0);
  }


  @Test
  public void getIdsFunciona() throws java.io.FileNotFoundException, java.io.IOException, com.opencsv.exceptions.CsvValidationException {
    LectorCSV lector = new LectorCSV("src/main/resources/template/project.properties", "entidades-prestadoras-csv-path");
    ImportadorEntidadesPrestadoras importadorEntidadesPrestadoras = new ImportadorEntidadesPrestadoras(lector, repoEntidades, repoUsuarios, repoEntidadesPrestadoras);
    String[] datos = {"nombre", "usuario", "email", "1", "2", "3"};
    assertEquals(3, importadorEntidadesPrestadoras.getIds(datos).size());
  }

  @Test
  public void lasEntidadesPrestadorasSeCreanSegunCSV() throws java.io.IOException, com.opencsv.exceptions.CsvValidationException {
    LectorCSV lector = mock(LectorCSV.class);
    RepositorioEntidadesPrestadoras repositorioEntidadesPrestadoras = new RepositorioEntidadesPrestadoras();
    RepositorioTipoEntidad repositorioTipoEntidad = new RepositorioTipoEntidad();
    ImportadorEntidadesPrestadoras importadorEntidadesPrestadoras = new ImportadorEntidadesPrestadoras(lector, repoEntidades, repoUsuarios, repositorioEntidadesPrestadoras);

    List<String[]> lista = new ArrayList<>();
    String[] array = {"afip", "1", "messi@hotmail.com", "1", "2", "3"};
    TipoEntidad tipoBancario = new TipoEntidad("Bancaria", "Entidad bancaria", new ArrayList<>());
    lista.add(array);
    repositorioTipoEntidad.guardar(tipoBancario);
    when(lector.getDatos()).thenReturn(lista);

    Entidad entidad1 = new Entidad("entidad1", tipoBancario);
    Entidad entidad2 = new Entidad("entidad2", tipoBancario);
    Entidad entidad3 = new Entidad("entidad3", tipoBancario);

    repoEntidades.guardar(entidad1, entidad2, entidad3);

    Usuario usuario1 = new Usuario("messi", "1234", LocalDateTime.now(), LocalDateTime.now());

    repoUsuarios.guardar(usuario1);

    importadorEntidadesPrestadoras.cargarDatos();
    assertEquals(1, repositorioEntidadesPrestadoras.buscarTodos().size());
    EntidadPrestadora afip = (EntidadPrestadora) repositorioEntidadesPrestadoras.buscarTodos().get(0);
    assertEquals("afip", afip.getNombre());
    assertEquals("messi", afip.getUsuario().getNombre());
  }
/*
  @Test
  public void losOrganismosSeCreanSegunCSV() throws java.io.IOException, com.opencsv.exceptions.CsvValidationException {

    RepositorioOrganismoDeControl repositorioOrganismoDeControl = new RepositorioOrganismoDeControl();
    LectorCSV lector = mock(LectorCSV.class);
    ImportadorOrganismos importadorOrganismos = new ImportadorOrganismos(lector, repoEntidadesPrestadoras, repoUsuarios, repoServicios, repositorioOrganismoDeControl);

    List<String[]> lista = new ArrayList<>();
    String[] array = {"afip", "1", "messi@hotmail.com", "1", "1"};
    lista.add(array);
    when(lector.getDatos()).thenReturn(lista);

    repoEntidadesPrestadoras.agregarEntidadPrestadora(1, "nombre", new Usuario(1, "messi", "contraseña", LocalDateTime.now(), LocalDateTime.now()), "messi", new ArrayList<Entidad>());
    Servicio servicio = new Servicio("Transporte");
    repoServicios.add(servicio);

    repoUsuarios.nuevoUsuario(1, "messi", "1234");

    importadorOrganismos.cargarDatos();
    assertEquals(1, repositorioOrganismoDeControl.getOrganismosDeControl().size());
    assertEquals("afip", repositorioOrganismoDeControl.getOrganismosDeControl().get(0).getNombre());
    assertEquals("messi", repositorioOrganismoDeControl.getOrganismosDeControl().get(0).getUsuario().getNombre());
  }¨*/
}