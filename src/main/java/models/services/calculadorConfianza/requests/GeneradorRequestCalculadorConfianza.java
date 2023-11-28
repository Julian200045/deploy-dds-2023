package models.services.calculadorConfianza.requests;
import models.entities.comunidades.Comunidad;
import models.entities.incidentes.Incidente;
import models.repositorios.RepositorioIncidentes;

import javax.persistence.EntityManager;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static server.App.entityManagerFactory;

public class GeneradorRequestCalculadorConfianza {

  EntityManager em = entityManagerFactory.createEntityManager();
  RepositorioIncidentes repoIncidentes = new RepositorioIncidentes(em);
  final static DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE_TIME;
  public RequestCalculadorConfianza generar(Comunidad comunidad){
    List<Incidente> incidentes = repoIncidentes.buscarPorComunidad(comunidad);
    List<IncidenteMolde> incidentesMolde = incidentes.stream().map(incidente ->
        new IncidenteMolde(incidente.getId(),
            Long.toString(incidente.getPrestacionDeServicio().getEstablecimiento().getId()),
            Long.toString(incidente.getPrestacionDeServicio().getServicio().getId()),
            incidente.getFechaYHoraDeApertura().format(FORMATTER).substring(0,17),
            incidente.getGetFechaYHoraDeCierre().format(FORMATTER).substring(0,17),
            incidente.getMiembroApertura().getPersona().getId(),
            incidente.getMiembroCierre().getPersona().getId()))
        .toList();

    List<UsuarioMolde> usuariosMolde = comunidad.getMiembros().stream().map(miembro ->
        new UsuarioMolde(miembro.getPersona().getId(),
                         miembro.getPersona().getGradoConfianza()))
        .toList();

    return new RequestCalculadorConfianza(usuariosMolde,incidentesMolde);
  }

}
