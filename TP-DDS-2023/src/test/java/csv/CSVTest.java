package csv;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import models.entities.entidades.Entidad;
import models.entities.entidades.TipoEntidad;
import models.entities.organismos.EntidadPrestadora;
import models.entities.usuarios.Usuario;
import models.repositorios.RepositorioEntidades;
import models.repositorios.RepositorioEntidadesPrestadoras;
import models.repositorios.RepositorioTipoEntidad;
import models.repositorios.RepositorioUsuarios;
import models.services.csv.LectorCSV;
import models.services.importadorDatosCSV.ImportadorEntidadesPrestadoras;
import org.junit.jupiter.api.Test;

public class CSVTest {

  RepositorioEntidades repoEntidades = new RepositorioEntidades();
  RepositorioUsuarios repoUsuarios = new RepositorioUsuarios();
  RepositorioEntidadesPrestadoras repoEntidadesPrestadoras = new RepositorioEntidadesPrestadoras();

  @Test
  public void lectorLeeAlIniciar() throws java.io.IOException, com.opencsv.exceptions.CsvValidationException {
    LectorCSV lector = new LectorCSV("src/main/resources/template/project.properties", "organismos-de-control-csv-path");
    assertFalse(lector.getDatos().isEmpty());
  }


  @Test
  public void getIdsFunciona() throws java.io.IOException, com.opencsv.exceptions.CsvValidationException {
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
    String[] array = {"afip", "5", "messi@hotmail.com", "1", "2", "3"};
    TipoEntidad tipoBancario = new TipoEntidad("Bancaria", "Entidad bancaria", new ArrayList<>());
    lista.add(array);
    repositorioTipoEntidad.guardar(tipoBancario);
    when(lector.getDatos()).thenReturn(lista);

    Entidad entidad1 = new Entidad("entidad1", tipoBancario);
    Entidad entidad2 = new Entidad("entidad2", tipoBancario);
    Entidad entidad3 = new Entidad("entidad3", tipoBancario);

    repoEntidades.guardar(entidad1, entidad2, entidad3);

    Usuario usuario1 = new Usuario("messi", "1234","asd@asd","1234");

    repoUsuarios.guardar(usuario1);

    importadorEntidadesPrestadoras.cargarDatos();
    List<EntidadPrestadora> entidadesPrestadoras = repositorioEntidadesPrestadoras.buscarTodos();
    assertEquals(1, entidadesPrestadoras.size());
  }

  @Test
  public void seLeeCorrectamnteElCSVCreado() {
    RepositorioEntidadesPrestadoras repositorioEntidadesPrestadoras = new RepositorioEntidadesPrestadoras();
    EntidadPrestadora afip = (EntidadPrestadora) repositorioEntidadesPrestadoras.buscar(6L);
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
    String[] array = {"afip", "5", "messi@hotmail.com", "1", "1"};
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
  }*/
}