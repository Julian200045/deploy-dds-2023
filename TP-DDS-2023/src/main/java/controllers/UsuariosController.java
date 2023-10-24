package controllers;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.entities.comunidades.Persona;
import models.entities.usuarios.Usuario;
import models.repositorios.RepositorioPersonas;
import models.repositorios.RepositorioUsuarios;
import models.services.LectorPropiedades;
import models.services.hasher.Hasher;
import models.services.hasher.HasherEstandar;
import models.services.validadorDeContrasenia.Validacion.ValidacionDeLargo;
import models.services.validadorDeContrasenia.Validacion.ValidacionMasUsadas;
import models.services.validadorDeContrasenia.Validacion.ValidacionMayuscula;
import models.services.validadorDeContrasenia.Validacion.ValidacionRepeticionLetras;
import models.services.validadorDeContrasenia.Validacion.ValidacionSimilitudUsuario;
import models.services.validadorDeContrasenia.ValidadorDeContrasenias;
import server.dtos.UsuarioDto;
import server.utils.ICrudViewsHandler;

public class UsuariosController implements ICrudViewsHandler {

  private RepositorioPersonas repositorioPersonas;
  private RepositorioUsuarios repositorioUsuarios;
  private ValidadorDeContrasenias validadorDeContrasenias;
  private LectorPropiedades lectorPropiedades;
  private Hasher hasher;

  public UsuariosController(RepositorioUsuarios repositorioUsuarios, RepositorioPersonas repositorioPersonas, ValidadorDeContrasenias validadorDeContrasenias, LectorPropiedades lectorPropiedades, Hasher hasher) {
    this.repositorioUsuarios = repositorioUsuarios;
    this.repositorioPersonas = repositorioPersonas;
    this.validadorDeContrasenias = validadorDeContrasenias;
    this.lectorPropiedades = lectorPropiedades;
    this.hasher = hasher;
  }

  @Override
  public void index(Context context) {
    Map<String, Object> model = new HashMap<>();

    if (context.queryString() == null || context.queryString().equals("")) {
      List<Usuario> usuarios = this.repositorioUsuarios.buscarTodos();
      List<UsuarioDto> usersDtos = usuarios.stream().map(usuario -> new UsuarioDto(usuario.getId(), usuario.getNombre(), usuario.getMail(), usuario.getNumeroCelular(), usuario.getRol())).toList();
      model.put("users", usersDtos);
    } else {
      String nombreBuscado = context.queryParam("nombre");
      Usuario usuario = (Usuario) this.repositorioUsuarios.buscarPorNombre(nombreBuscado);
      UsuarioDto userDto = new UsuarioDto(usuario.getId(), usuario.getNombre(), usuario.getMail(), usuario.getNumeroCelular(), usuario.getRol());
      model.put("users", userDto);
    }
    context.render("administracion_usuarios.hbs", model);
  }

  @Override
  public void show(Context context) {
    String tipoBusqueda = context.queryParam("tipoBusqueda");
    Usuario usuario = new Usuario();
    switch (tipoBusqueda) {
      case "nombre":
        String nombre = context.queryParam("nombre");
        usuario = (Usuario) this.repositorioUsuarios.buscarPorNombre(nombre);
        break;
      case "id":
        String id = context.queryParam("id");
        usuario = (Usuario) this.repositorioUsuarios.buscar(Long.parseLong(id));
        break;
    }
    if (usuario != null) {
      UsuarioDto userDto = new UsuarioDto(usuario.getId(), usuario.getNombre(), usuario.getMail(), usuario.getNumeroCelular(), usuario.getRol());
      System.out.println(userDto);
      context.json(userDto);
    } else {
      context.status(HttpStatus.NOT_FOUND);
      context.result("Usuario no encontrado");
    }

  }

  @Override
  public void create(Context context) {
    context.render("crear-usuario.hbs");
  }

  @Override
  public void save(Context context) {
    String nombreUsuario = context.formParam("nombre_usuario");
    String contrasenia = context.formParam("contrasenia");
    String email = context.formParam("email");
    String celular = context.formParam("celular");
    String nombre = context.formParam("nombre");
    String apellido = context.formParam("apellido");

    if (this.repositorioUsuarios.buscarPorNombre(nombreUsuario) != null) {
      context.result("Nombre de usuario ya existente");
      return;
    }

    validadorDeContrasenias.agregarValidacion(new ValidacionMayuscula());
    validadorDeContrasenias.agregarValidacion(new ValidacionRepeticionLetras());
    validadorDeContrasenias.agregarValidacion(new ValidacionSimilitudUsuario(nombreUsuario));
    validadorDeContrasenias.agregarValidacion(new ValidacionDeLargo(lectorPropiedades));
    validadorDeContrasenias.agregarValidacion(new ValidacionMasUsadas(lectorPropiedades));

    if (validadorDeContrasenias.esValida(contrasenia)) {
      Usuario usuario = new Usuario(nombreUsuario, hasher.hashear(contrasenia), email, celular);
      this.repositorioUsuarios.guardar(usuario);
      Persona persona = new Persona(nombre, apellido, usuario);
      this.repositorioPersonas.guardar(persona);
      context.result("Usuario creado correctamente");
      context.redirect("/login");
    }
  }

  @Override
  public void edit(Context context) {

  }

  @Override
  public void update(Context context) {
    Long longId = Long.parseLong(context.queryParam("id"));
    Usuario user = (Usuario) this.repositorioUsuarios.buscar(longId);
    if (user != null) {
      user.setNumeroCelular(context.queryParam("telefono"));
      user.setMail(context.queryParam("mail"));
      user.setNombre(context.queryParam("nombre"));
      this.repositorioUsuarios.actualizar(user);
      context.result("Usuario actualizado");
    } else {
      context.status(HttpStatus.NOT_FOUND);
      context.result("Usuario no encontrado");
    }
  }

  @Override
  public void delete(Context context) {
    Long longId = Long.parseLong(context.queryParam("id"));
    Usuario user = (Usuario) this.repositorioUsuarios.buscar(longId);
    if (user != null) {
      this.repositorioUsuarios.eliminar(user);
      context.result("Usuario eliminado");
    } else {
      context.status(HttpStatus.NOT_FOUND);
      context.result("Usuario no encontrado");
    }
  }

  public void login(Context context) {
    String nombre = context.formParam("nombre");
    String contrasenia = context.formParam("contrasenia");

    Usuario usuario = (Usuario) this.repositorioUsuarios.buscarPorNombre(nombre);

    Hasher hasher = new HasherEstandar();
    String contraseniaHash = hasher.hashear(contrasenia);
    if (usuario != null && usuario.getContrasenia().equals(contraseniaHash)) {
      context.sessionAttribute("usuario_id", usuario.getId());
      context.redirect("/incidentes");
    } else {
      context.status(401);
      context.html("Credenciales incorrectas. <a href='/login'>Volver</a>");
    }
  }
}
