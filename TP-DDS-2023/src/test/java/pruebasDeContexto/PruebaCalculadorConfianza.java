package pruebasDeContexto;

import java.io.IOException;
import java.time.LocalDateTime;
import models.entities.comunidades.Comunidad;
import models.entities.comunidades.Miembro;
import models.entities.comunidades.Persona;
import models.entities.comunidades.TipoMiembro;
import models.entities.establecimientos.Establecimiento;
import models.entities.servicios.PrestacionDeServicio;
import models.entities.servicios.Servicio;
import models.entities.usuarios.Usuario;
import models.repositorios.RepositorioIncidentes;
import models.repositorios.RepositorioNotificaciones;
import models.repositorios.RepositorioComunidades;
import models.repositorios.RepositorioPersonas;
import models.repositorios.RepositorioPrestacionesDeServicio;
import models.services.IncidentesService;
import models.services.calculadorConfianza.CalculadorConfianza;
import models.services.calculadorConfianza.CalculadorConfianzaService;
import models.services.calculadorConfianza.requests.GeneradorRequestCalculadorConfianza;
import models.services.notificador.GeneradorNotificaciones;
import org.quartz.SchedulerException;

public class PruebaCalculadorConfianza {
  public static void main(String[] args) throws IOException, SchedulerException {

    CalculadorConfianzaService calculadorConfianza = new CalculadorConfianza("src/main/resources/template/project.properties");
    GeneradorRequestCalculadorConfianza generador = new GeneradorRequestCalculadorConfianza();
    RepositorioPrestacionesDeServicio repositorioPrestacionesDeServicio = new RepositorioPrestacionesDeServicio();
    RepositorioPersonas repositorioPersona = new RepositorioPersonas();
    RepositorioIncidentes repositorioIncidentes = new RepositorioIncidentes();
    RepositorioNotificaciones repositorioNotificaciones = new RepositorioNotificaciones();
    RepositorioComunidades repositorioComunidades = new RepositorioComunidades();

    Comunidad comunidad = new Comunidad("Comunidad prueba");
    Comunidad comunidad2 = new Comunidad("Pepitos house");

    repositorioComunidades.guardar(comunidad);
    repositorioComunidades.guardar(comunidad2);

    Usuario usuario1 = new Usuario("tomas", "1234", "hola@gmail.com","112233",LocalDateTime.now(), LocalDateTime.now());
    Usuario usuario2 = new Usuario("julian", "1234", "hola@gmail.com","112233",LocalDateTime.now(), LocalDateTime.now());
    Usuario usuario3 = new Usuario("exequiel", "1234", "hola@gmail.com","112233", LocalDateTime.now(), LocalDateTime.now());
    Usuario usuario4 = new Usuario("renzo", "1234","hola@gmail.com","112233", LocalDateTime.now(), LocalDateTime.now());
    Usuario usuario5 = new Usuario("gomaaazo", "1234","hola@gmail.com", "112233",LocalDateTime.now(), LocalDateTime.now());

    Persona persona1 = new Persona("Juan1", "Carlos1", usuario1);
    Persona persona2 = new Persona("Juan2", "Carlos2", usuario2);
    Persona persona3 = new Persona("Juan3", "Carlos3", usuario3);
    Persona persona4 = new Persona("Juan4", "Carlos4", usuario4);
    Persona persona5 = new Persona("Juan5", "Carlos5", usuario5);

    Miembro miembro1 = new Miembro(comunidad, persona1, TipoMiembro.AFECTADO);
    Miembro miembro11 = new Miembro(comunidad2, persona1, TipoMiembro.AFECTADO);
    Miembro miembro2 = new Miembro(comunidad, persona2, TipoMiembro.AFECTADO);
    Miembro miembro3 = new Miembro(comunidad, persona3, TipoMiembro.AFECTADO);
    Miembro miembro4 = new Miembro(comunidad, persona4, TipoMiembro.AFECTADO);
    Miembro miembro5 = new Miembro(comunidad, persona5, TipoMiembro.AFECTADO);

    repositorioPersona.guardar(persona1, persona2, persona3, persona4, persona5);

    repositorioComunidades.actualizar(comunidad);
    repositorioComunidades.actualizar(comunidad2);

    Servicio servicio = new Servicio("Servicio1");
    Establecimiento establecimiento = new Establecimiento("Establecimiento1", null, null);

    Servicio servicio2 = new Servicio("Servicio2");

    PrestacionDeServicio prestacionDeServicio = new PrestacionDeServicio(servicio, establecimiento);
    PrestacionDeServicio prestacionDeServicio2 = new PrestacionDeServicio(servicio2, establecimiento);

    repositorioPrestacionesDeServicio.guardar(prestacionDeServicio);
    repositorioPrestacionesDeServicio.guardar(prestacionDeServicio2);

    comunidad.agregarPrestacionDeInteres(prestacionDeServicio);
    comunidad.agregarPrestacionDeInteres(prestacionDeServicio2);
    comunidad2.agregarPrestacionDeInteres(prestacionDeServicio);
    comunidad2.agregarPrestacionDeInteres(prestacionDeServicio2);


    repositorioComunidades.actualizar(comunidad);
    repositorioComunidades.actualizar(comunidad2);


    GeneradorNotificaciones generadorNotificaciones = new GeneradorNotificaciones(repositorioNotificaciones);
    IncidentesService incidentesService = new IncidentesService(repositorioIncidentes, generadorNotificaciones);

    incidentesService.darDeAltaIncidente(persona1, prestacionDeServicio, "Observacion de carozo");
    incidentesService.darDeAltaIncidente(persona2, prestacionDeServicio2, "Observacion de pepe");

    //incidentesService.darDeBajaIncidentesDeLaPrestacion(miembro1, prestacionDeServicio);

    //RequestCalculadorConfianza request = generador.generar(comunidad);
    //InformeConfianza informeConfianza = calculadorConfianza.calcularConfianza(request);

    //Gson gson = new Gson();

    //System.out.println(gson.toJson(informeConfianza));
  }
}
