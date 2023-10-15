package pruebasDeContexto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import models.entities.comunidades.Comunidad;
import models.entities.comunidades.Miembro;
import models.entities.comunidades.Persona;
import models.entities.comunidades.TipoMiembro;
import models.entities.establecimientos.Establecimiento;
import models.entities.incidentes.Incidente;
import models.entities.servicios.PrestacionDeServicio;
import models.entities.servicios.Servicio;
import models.entities.usuarios.Usuario;
import models.repositorios.comunidades.RepositorioComunidades;
import models.repositorios.incidentes.RepositorioIncidentes;
import models.repositorios.notificaciones.RepositorioNotificaciones;
import models.repositorios.personas.RepositorioPersona;
import models.repositorios.prestaciondeservicios.RepositorioPrestacionesDeServicio;
import models.services.IncidentesService;
import models.services.calculadorConfianza.CalculadorConfianza;
import models.services.calculadorConfianza.CalculadorConfianzaService;
import models.services.calculadorConfianza.moldes.InformeConfianza;
import models.services.calculadorConfianza.requests.GeneradorRequestCalculadorConfianza;
import models.services.calculadorConfianza.requests.RequestCalculadorConfianza;
import models.services.notificador.GeneradorNotificaciones;
import org.quartz.SchedulerException;

import java.io.IOException;
import java.time.LocalDateTime;

public class PruebaCalculadorConfianza {
  public static void main(String[] args) throws IOException, SchedulerException {

    CalculadorConfianzaService calculadorConfianza = new CalculadorConfianza("src/main/resources/template/project.properties");
    GeneradorRequestCalculadorConfianza generador = new GeneradorRequestCalculadorConfianza();
    RepositorioPrestacionesDeServicio repositorioPrestacionesDeServicio = new RepositorioPrestacionesDeServicio();
    RepositorioPersona repositorioPersona = new RepositorioPersona();
    RepositorioIncidentes repositorioIncidentes = new RepositorioIncidentes();
    RepositorioNotificaciones repositorioNotificaciones = new RepositorioNotificaciones();
    RepositorioComunidades repositorioComunidades = new RepositorioComunidades();

    Comunidad comunidad = new Comunidad("Comunidad prueba");

    repositorioComunidades.guardar(comunidad);

    Usuario usuario = new Usuario("tomas","1234", LocalDateTime.now(), LocalDateTime.now());

    Persona persona1 = new Persona("Juan1","Carlos1",usuario);
    Persona persona2 = new Persona("Juan2","Carlos2",usuario);
    Persona persona3 = new Persona("Juan3","Carlos3",usuario);
    Persona persona4 = new Persona("Juan4","Carlos4",usuario);
    Persona persona5 = new Persona("Juan5","Carlos5",usuario);

    Miembro miembro1 = new Miembro(comunidad,persona1, TipoMiembro.AFECTADO);
    Miembro miembro2 = new Miembro(comunidad,persona2, TipoMiembro.AFECTADO);
    Miembro miembro3 = new Miembro(comunidad,persona3, TipoMiembro.AFECTADO);
    Miembro miembro4 = new Miembro(comunidad,persona4, TipoMiembro.AFECTADO);
    Miembro miembro5 = new Miembro(comunidad,persona5, TipoMiembro.AFECTADO);

    persona1.agregarMembresia(miembro1);
    persona2.agregarMembresia(miembro2);
    persona3.agregarMembresia(miembro3);
    persona4.agregarMembresia(miembro4);
    persona5.agregarMembresia(miembro5);

    comunidad.agregarMiembro(miembro1);
    comunidad.agregarMiembro(miembro2);
    comunidad.agregarMiembro(miembro3);
    comunidad.agregarMiembro(miembro4);
    comunidad.agregarMiembro(miembro5);

    repositorioComunidades.actualizar(comunidad);

    repositorioPersona.guardar(persona1,persona2,persona3,persona4,persona5);

    Servicio servicio = new Servicio("Servicio1");
    Establecimiento establecimiento = new Establecimiento("Establecimiento1",null,null);

    PrestacionDeServicio prestacionDeServicio = new PrestacionDeServicio(servicio,establecimiento);

    repositorioPrestacionesDeServicio.guardar(prestacionDeServicio);

    comunidad.agregarPrestacionDeInteres(prestacionDeServicio);

    repositorioComunidades.actualizar(comunidad);

    GeneradorNotificaciones generadorNotificaciones = new GeneradorNotificaciones(repositorioNotificaciones);
    IncidentesService incidentesService = new IncidentesService(repositorioIncidentes,generadorNotificaciones);

    incidentesService.darDeAltaIncidente(miembro1,prestacionDeServicio,"Observacion de carozo");
    incidentesService.darDeAltaIncidente(miembro2,prestacionDeServicio,"Observacion de pepe");

    incidentesService.darDeBajaIncidentesDeLaPrestacion(miembro1,prestacionDeServicio);

    RequestCalculadorConfianza request = generador.generar(comunidad);
    InformeConfianza informeConfianza = calculadorConfianza.calcularConfianza(request);

    Gson gson = new Gson();

    System.out.println(gson.toJson(informeConfianza));
  }
}
