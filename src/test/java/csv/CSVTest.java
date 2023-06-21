package csv;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.entidades.Entidad;
import domain.entidades.TipoEntidad;
import domain.repositorios.entidades.RepoEntidades;
import domain.repositorios.entidadesprestadoras.RepoEntidadesPrestadoras;
import domain.repositorios.entidadesprestadoras.RepositorioEntidadesPrestadoras;
import domain.repositorios.organismos.RepositorioOrganismoDeControl;
import domain.repositorios.servicios.RepoServicios;
import domain.repositorios.servicios.RepositorioServicios;
import domain.repositorios.usuarios.RepoUsuarios;
import domain.usuarios.Usuario;
import services.csv.LectorCSV;
import domain.repositorios.entidades.RepositorioEntidades;

import domain.repositorios.usuarios.RepositorioUsuarios;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import services.importadorDatosCSV.ImportadorEntidadesPrestadoras;
import services.importadorDatosCSV.ImportadorOrganismos;

import static org.mockito.Mockito.*;

public class CSVTest {

	RepoEntidades repoEntidades = new RepositorioEntidades();
	RepoUsuarios repoUsuarios = new RepositorioUsuarios();
	RepoServicios repoServicios = new RepositorioServicios();
	RepoEntidadesPrestadoras repoEntidadesPrestadoras = new RepositorioEntidadesPrestadoras();

	@Test
	public void lectorLeeAlIniciar() throws java.io.IOException, com.opencsv.exceptions.CsvValidationException{
		LectorCSV lector = new LectorCSV("src/main/resources/template/project.properties", "organismos-de-control-csv-path");
		assertTrue(lector.getDatos().size() > 0);
	}


	@Test
	public void getIdsFunciona() throws java.io.FileNotFoundException, java.io.IOException, com.opencsv.exceptions.CsvValidationException{
		LectorCSV lector = new LectorCSV("src/main/resources/template/project.properties", "entidades-prestadoras-csv-path");
		ImportadorEntidadesPrestadoras importadorEntidadesPrestadoras = new ImportadorEntidadesPrestadoras(lector, repoEntidades, repoUsuarios, repoEntidadesPrestadoras);
		String[] datos = {"nombre","usuario","email","1","2", "3"};
		assertEquals(3, importadorEntidadesPrestadoras.getIds(datos).size());
	}

	@Test
	public void lasEntidadesPrestadorasSeCreanSegunCSV() throws  java.io.IOException, com.opencsv.exceptions.CsvValidationException{
		LectorCSV lector = mock(LectorCSV.class);
		RepositorioEntidadesPrestadoras repositorioEntidadesPrestadoras = new RepositorioEntidadesPrestadoras();
		ImportadorEntidadesPrestadoras importadorEntidadesPrestadoras = new ImportadorEntidadesPrestadoras(lector, repoEntidades, repoUsuarios, repositorioEntidadesPrestadoras);

		List<String[]> lista = new ArrayList<>();
		String[] array = {"afip","1","messi@hotmail.com","1","2","3"};
		TipoEntidad tipoBancario = new TipoEntidad("Bancaria","Entidad bancaria",new ArrayList<>());
		lista.add(array);
		when(lector.getDatos()).thenReturn(lista);

		repoEntidades.agregarEntidad(1,"entidad1",tipoBancario);
		repoEntidades.agregarEntidad(2,"entidad2",tipoBancario);
		repoEntidades.agregarEntidad(3,"entidad3",tipoBancario);

		repoUsuarios.nuevoUsuario(1,"messi","1234");

		importadorEntidadesPrestadoras.cargarDatos();
		assertEquals(1, repositorioEntidadesPrestadoras.getEntidadesPrestadoras().size());
		assertEquals("afip", repositorioEntidadesPrestadoras.getEntidadesPrestadoras().get(0).getNombre());
		assertEquals("messi", repositorioEntidadesPrestadoras.getEntidadesPrestadoras().get(0).getUsuario().getNombre());
	}

	@Test
	public void losOrganismosSeCreanSegunCSV() throws  java.io.IOException, com.opencsv.exceptions.CsvValidationException{

		RepositorioOrganismoDeControl repositorioOrganismoDeControl = new RepositorioOrganismoDeControl();
		LectorCSV lector = mock(LectorCSV.class);
		ImportadorOrganismos importadorOrganismos = new ImportadorOrganismos(lector, repoEntidadesPrestadoras, repoUsuarios, repoServicios, repositorioOrganismoDeControl);

		List<String[]> lista = new ArrayList<>();
		String[] array = {"afip","1","messi@hotmail.com","1","1"};
		lista.add(array);
		when(lector.getDatos()).thenReturn(lista);

		repoEntidadesPrestadoras.agregarEntidadPrestadora(1,"nombre",new Usuario(1,"messi", "contraseña"), "messi", new ArrayList<Entidad>());
		repoServicios.agregarServicio(1, "transporte");

		repoUsuarios.nuevoUsuario(1,"messi","1234");

		importadorOrganismos.cargarDatos();
		assertEquals(1, repositorioOrganismoDeControl.getOrganismosDeControl().size());
		assertEquals("afip", repositorioOrganismoDeControl.getOrganismosDeControl().get(0).getNombre());
		assertEquals("messi", repositorioOrganismoDeControl.getOrganismosDeControl().get(0).getUsuario().getNombre());
	}





}
