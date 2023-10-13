package services.calculadorConfianza.requests;

import domain.comunidades.Comunidad;
import repositorios.incidentes.RepoIncidentes;
import repositorios.incidentes.RepositorioIncidentes;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class GeneradorRequestCalculadorConfianza {
  RepoIncidentes repoIncidentes = new RepositorioIncidentes();
  final static DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE_TIME;
  public RequestCalculadorConfianza generar(Comunidad comunidad){
    List<IncidenteMolde> incidentesMolde = repoIncidentes.getByComunidad(comunidad).stream().map(incidente ->
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
