package controllers;

import models.repositorios.RepositorioComunidades;
import models.repositorios.RepositorioEntidades;
import models.repositorios.RepositorioEntidadesPrestadoras;
import models.repositorios.RepositorioIncidentes;
import models.repositorios.RepositorioNotificaciones;
import models.repositorios.RepositorioOrganismoDeControl;
import models.repositorios.RepositorioPersonas;
import models.repositorios.RepositorioPrestacionesDeServicio;
import models.repositorios.RepositorioRol;
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

import javax.persistence.EntityManager;

import static server.App.entityManagerFactory;

public class FactoryController {

  public static Object controller(String nombre) {
    EntityManager em = entityManagerFactory.createEntityManager();
    Object controller = null;

    switch (nombre) {
      case "Incidentes":
        controller = new IncidentesController(new RepositorioIncidentes(em),
            new RepositorioPersonas(em),
            new RepositorioUsuarios(em),
            new RepositorioPrestacionesDeServicio(em),
            new IncidentesService(new RepositorioIncidentes(em), new GeneradorNotificaciones(new RepositorioNotificaciones(em)))
        );
        break;
      case "Usuarios":
        controller = new UsuariosController(new RepositorioUsuarios(em), new RepositorioRol(em), new RepositorioPersonas(em), new ValidadorDeContraseniasPorValidaciones(), new LectorPropiedades("template/project.properties"), new HasherEstandar());
        break;
      case "Comunidades":
        controller = new ComunidadesController(new RepositorioComunidades(em), new RepositorioUsuarios(em), new RepositorioPersonas(em));
        break;
      case "Organismos":
        controller = new OrganismosController(new LectorCSV(), new ImportadorOrganismosDeControl(new RepositorioEntidadesPrestadoras(em), new RepositorioUsuarios(em), new RepositorioServicios(em), new RepositorioOrganismoDeControl(em)), new ImportadorEntidadesPrestadoras(new RepositorioEntidades(em), new RepositorioUsuarios(em), new RepositorioEntidadesPrestadoras(em)), new RepositorioUsuarios(em));
        break;
    }
    return controller;
  }
}
