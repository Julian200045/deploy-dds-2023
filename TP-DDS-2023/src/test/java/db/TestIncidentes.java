package db;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.io.IOException;
import java.time.LocalDateTime;
import models.entities.comunidades.Comunidad;
import models.entities.comunidades.Miembro;
import models.entities.comunidades.Persona;
import models.entities.comunidades.TipoMiembro;
import models.entities.establecimientos.Establecimiento;
import models.entities.establecimientos.TipoEstablecimiento;
import models.entities.incidentes.Incidente;
import models.entities.servicios.PrestacionDeServicio;
import models.entities.servicios.Servicio;
import models.entities.ubicaciones.Ubicacion;
import models.entities.usuarios.Usuario;
import models.repositorios.RepositorioIncidentes;
import org.junit.jupiter.api.Test;

public class TestIncidentes implements WithSimplePersistenceUnit {
  @Test
  void cargaIncidentes() throws IOException {
    Comunidad comunidad1 = new Comunidad("comunidad1");
    Incidente incidente = new Incidente(new PrestacionDeServicio(
        new Servicio("servicio1"),
        new Establecimiento("establecimiento1",
            new Ubicacion(10.0, 10.0),
            new TipoEstablecimiento("tipo1", "hola"))),
        comunidad1,
        "Observaciones varias",
        new Miembro(comunidad1, new Persona("Tomas", "Gomez", new Usuario("tomas","1234", LocalDateTime.now(), LocalDateTime.now())), false, TipoMiembro.AFECTADO)
        );
    RepositorioIncidentes repositorioIncidentes = new RepositorioIncidentes();
    repositorioIncidentes.guardar(incidente);
  }
}
