package pruebasDeContexto;

import java.io.IOException;
import java.time.LocalDateTime;

import com.google.gson.Gson;
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
import models.services.calculadorConfianza.moldes.InformeConfianza;
import models.services.calculadorConfianza.requests.GeneradorRequestCalculadorConfianza;
import models.services.calculadorConfianza.requests.RequestCalculadorConfianza;
import models.services.fusionadorComunidades.moldes.Propuesta;
import models.services.notificador.GeneradorNotificaciones;
import org.quartz.SchedulerException;

public class PruebaCalculadorConfianza {
  public static void main(String[] args) throws IOException, SchedulerException {

    CalculadorConfianzaService calculadorConfianza = new CalculadorConfianza("src/main/resources/template/project.properties");
    GeneradorRequestCalculadorConfianza generador = new GeneradorRequestCalculadorConfianza();

    RepositorioComunidades repositorioComunidades = new RepositorioComunidades();

    Comunidad comunidad = (Comunidad) repositorioComunidades.buscar(1L);

    RequestCalculadorConfianza request = generador.generar(comunidad);
    InformeConfianza informeConfianza = calculadorConfianza.calcularConfianza(request);

    Gson gson = new Gson();
    System.out.println(gson.toJson(informeConfianza));
  }
}
