package controllers;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.entities.comunidades.Comunidad;
import models.entities.comunidades.Miembro;
import models.entities.comunidades.Persona;
import models.entities.usuarios.Usuario;
import models.repositorios.RepositorioComunidades;
import models.repositorios.RepositorioPersonas;
import models.repositorios.RepositorioUsuarios;
import org.quartz.SchedulerException;
import server.dtos.ComunidadDto;
import server.dtos.PrestacionDto;
import server.utils.ICrudViewsHandler;

public class ComunidadesController implements ICrudViewsHandler {

  private RepositorioComunidades repositorioComunidades;
  private RepositorioUsuarios repositorioUsuarios;
  private RepositorioPersonas repositorioPersonas;

  public ComunidadesController(RepositorioComunidades repositorioComunidades, RepositorioUsuarios repositorioUsuarios, RepositorioPersonas repositorioPersonas) {
    this.repositorioComunidades = repositorioComunidades;
    this.repositorioUsuarios = repositorioUsuarios;
    this.repositorioPersonas = repositorioPersonas;
  }

  @Override
  public void index(Context context) {
    Long idUsuarioEnSesion = context.sessionAttribute("usuario_id");
    if (idUsuarioEnSesion == null) {
      context.redirect("/login", HttpStatus.SEE_OTHER);
      return;
    }
    Usuario usuario = (Usuario) this.repositorioUsuarios.buscar(idUsuarioEnSesion);
    Persona personaEnSesion = (Persona) this.repositorioPersonas.buscarPorUsuario(usuario);

    Map<String, Object> model = new HashMap<>();
    List<Comunidad> comunidades = this.repositorioComunidades.buscarTodos();
    comunidades = comunidades.stream().filter(comunidad ->
        !comunidad.esMiembro(personaEnSesion)
    ).toList();
    List<ComunidadDto> comunidadesDtos = comunidades.stream().map(c ->
        new ComunidadDto(
            c.getId(),
            c.getNombre(),
            c.getPrestacionDeServicios().stream().map(p ->
                new PrestacionDto(
                    p.getId(),
                    p.getEstablecimiento().getNombre(),
                    p.getServicio().getNombre()
                )
            ).toList(),
            c.getGradoConfianza()
        )
    ).toList();
    model.put("comunidades", comunidadesDtos);
    context.render("comunidades.hbs", model);
  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {

  }

  @Override
  public void save(Context context) throws SchedulerException {

  }

  @Override
  public void edit(Context context) {

  }

  @Override
  public void update(Context context) {
    Long idUsuarioEnSesion = context.sessionAttribute("usuario_id");
    if (idUsuarioEnSesion == null) {
      context.redirect("/login");
    }
    Usuario usuarioEnSesion = (Usuario) repositorioUsuarios.buscar(idUsuarioEnSesion);
    Persona personaEnSesion = (Persona) repositorioPersonas.buscarPorUsuario(usuarioEnSesion);

    Long idComunidad = Long.parseLong(context.pathParam("id"));
    Comunidad comunidad = (Comunidad) repositorioComunidades.buscar(idComunidad);

    Miembro miembro = new Miembro(comunidad, personaEnSesion);

    this.repositorioComunidades.actualizar(comunidad);
    context.result("Membresia agregada correctamente.");
  }

  @Override
  public void delete(Context context) {

  }
}
