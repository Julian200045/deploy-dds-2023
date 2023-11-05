package controllers;

import models.repositorios.RepositorioComunidades;
import models.repositorios.RepositorioEntidades;
import models.repositorios.RepositorioEntidadesPrestadoras;
import models.repositorios.RepositorioIncidentes;
import models.repositorios.RepositorioNotificaciones;
import models.repositorios.RepositorioOrganismoDeControl;
import models.repositorios.RepositorioPersonas;
import models.repositorios.RepositorioPrestacionesDeServicio;
import models.repositorios.RepositorioServicios;
import models.repositorios.RepositorioUsuarios;
import models.services.IncidentesService;
import models.services.LectorPropiedades;
import models.services.csv.LectorCSV;
import models.services.hasher.HasherEstandar;
import models.services.importadorDatosCSV.ImportadorEntidadesPrestadoras;
import models.services.importadorDatosCSV.ImportadorOrganismosDeControl;
import models.services.notificador.GeneradorNotificaciones;
import models.services.validadorDeContrasenia.ValidadorDeContraseniasPorValidaciones;

public class FactoryController {

  public static Object controller(String nombre) {
    Object controller = null;

    switch (nombre) {
      case "Incidentes":
        controller = new IncidentesController(new RepositorioIncidentes(),
            new RepositorioPersonas(),
            new RepositorioUsuarios(),
            new RepositorioPrestacionesDeServicio(),
            new IncidentesService(new RepositorioIncidentes(), new GeneradorNotificaciones(new RepositorioNotificaciones()))
        );
        break;
      case "Usuarios":
        controller = new UsuariosController(new RepositorioUsuarios(), new RepositorioPersonas(), new ValidadorDeContraseniasPorValidaciones(), new LectorPropiedades("src/main/resources/template/project.properties"), new HasherEstandar());
        break;
      case "Comunidades":
        controller = new ComunidadesController(new RepositorioComunidades(), new RepositorioUsuarios(), new RepositorioPersonas());
        break;
      case "Organismos":
        controller = new OrganismosController(new LectorCSV(), new ImportadorOrganismosDeControl(new RepositorioEntidadesPrestadoras(), new RepositorioUsuarios(), new RepositorioServicios(), new RepositorioOrganismoDeControl()), new ImportadorEntidadesPrestadoras(new RepositorioEntidades(), new RepositorioUsuarios(), new RepositorioEntidadesPrestadoras()));
        break;
    }
    return controller;
  }
}
