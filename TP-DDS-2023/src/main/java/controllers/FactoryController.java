package controllers;

import models.repositorios.RepositorioComunidades;
import models.repositorios.RepositorioIncidentes;
import models.repositorios.RepositorioNotificaciones;
import models.repositorios.RepositorioPersonas;
import models.repositorios.RepositorioPrestacionesDeServicio;
import models.repositorios.RepositorioUsuarios;
import models.services.IncidentesService;
import models.services.LectorPropiedades;
import models.services.hasher.HasherEstandar;
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
    }
    return controller;
  }
}
