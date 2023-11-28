package controllers;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.entities.comunidades.Persona;
import models.entities.roles.TipoRol;
import models.entities.roles.Rol;
import models.repositorios.RepositorioRol;
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
import server.dtos.RolDto;
import server.utils.ICrudViewsHandler;

public class UsuariosController implements ICrudViewsHandler {

  private RepositorioPersonas repositorioPersonas;
  private RepositorioRol repositorioRoles;
  private RepositorioUsuarios repositorioUsuarios;
  private ValidadorDeContrasenias validadorDeContrasenias;
  private LectorPropiedades lectorPropiedades;
  private Hasher hasher;

  public UsuariosController(RepositorioUsuarios repositorioUsuarios, RepositorioRol repositorioRoles, RepositorioPersonas repositorioPersonas, ValidadorDeContrasenias validadorDeContrasenias, LectorPropiedades lectorPropiedades, Hasher hasher) {
    this.repositorioUsuarios = repositorioUsuarios;
    this.repositorioRoles = repositorioRoles;
    this.repositorioPersonas = repositorioPersonas;
    this.validadorDeContrasenias = validadorDeContrasenias;
    this.lectorPropiedades = lectorPropiedades;
    this.hasher = hasher;
  }
  @Override
  public void index(Context context) {
    Map<String, Object> model = new HashMap<>();
    List<Rol> roles;
    if (context.queryString() == null || context.queryString().equals("")) {
      List<Usuario> usuarios = this.repositorioUsuarios.buscarTodos();
      List<UsuarioDto> usersDtos = usuarios.stream().map(usuario -> new UsuarioDto(usuario.getId(), usuario.getNombre(), usuario.getMail(), usuario.getNumeroCelular(), usuario.getRol())).toList();
      model.put("users", usersDtos);
    } else {
      String nombreBuscado = context.queryParam("nombre");
      List<Usuario> usuarios = this.repositorioUsuarios.buscarPorNombreSimilar(nombreBuscado);
      List<UsuarioDto> usuariosDtos = usuarios.stream().map(usuario -> new UsuarioDto(usuario.getId(),usuario.getNombre(),usuario.getMail(),usuario.getNumeroCelular(),usuario.getRol())).toList();
      model.put("users", usuariosDtos);
    }
    roles = (List<Rol>) this.repositorioRoles.buscarTodos();
    List<RolDto> rolesDto = roles.stream().map(rol -> new RolDto(rol.getId(),rol.getNombre())).toList();
    model.put("roles",rolesDto);

    Usuario usuario = (Usuario) this.repositorioUsuarios.buscar(context.sessionAttribute("usuario_id"));
    Boolean esAdmin = usuario.getRol().getTipoRol() == TipoRol.ADMIN;
    model.put("esAdmin",esAdmin);

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
    Long rolId = context.formParam("rol") == null ? 3 : Long.parseLong(context.formParam("rol"));
    Rol rol = (Rol) repositorioRoles.buscar(rolId);

    if (this.repositorioUsuarios.buscarPorNombre(nombreUsuario) != null) {
      context.result("Nombre de usuario ya existente");
      return;
    }

    validadorDeContrasenias.agregarValidacion(new ValidacionMayuscula());
    validadorDeContrasenias.agregarValidacion(new ValidacionRepeticionLetras());
    validadorDeContrasenias.agregarValidacion(new ValidacionSimilitudUsuario(nombreUsuario));

    Integer min = lectorPropiedades.getPropiedadInt("min");
    Integer max = lectorPropiedades.getPropiedadInt("max");
    validadorDeContrasenias.agregarValidacion(new ValidacionDeLargo(min, max));

    String pathContrasenias = lectorPropiedades.getPropiedad("password-top-10000-path");
    validadorDeContrasenias.agregarValidacion(new ValidacionMasUsadas(pathContrasenias));

    if (validadorDeContrasenias.esValida(contrasenia)) {
      Usuario usuario = new Usuario(nombreUsuario, hasher.hashear(contrasenia), email, celular);
      usuario.setRol(rol);
      this.repositorioUsuarios.guardar(usuario);
      Persona persona = new Persona(nombre, apellido, usuario);
      this.repositorioPersonas.guardar(persona);
      context.status(HttpStatus.OK);
    }
    else {
      context.status(HttpStatus.IM_A_TEAPOT);
    }
  }

  @Override
  public void edit(Context context) {

  }

    @Override
    public void update(Context context) {
        Long longId = Long.parseLong(context.formParam("id"));
        Usuario user = (Usuario) this.repositorioUsuarios.buscar(longId);
        if(user != null){
            user.setNumeroCelular(context.formParam("telefono"));
            user.setMail(context.formParam("mail"));
            user.setNombre(context.formParam("nombre"));
            this.repositorioUsuarios.actualizar(user);
            context.result("Usuario actualizado");
        } else{
            context.status(HttpStatus.NOT_FOUND);
            context.result("Usuario no encontrado");
        }
    }

  @Override
  public void delete(Context context) {
    Long longId = Long.parseLong(context.queryParam("id"));
    Usuario user = (Usuario) this.repositorioUsuarios.buscar(longId);
    if (user != null) {
      user.setHabilitado(false);
      this.repositorioUsuarios.actualizar(user);
      context.result("Usuario eliminado");
    } else {
      context.status(HttpStatus.NOT_FOUND);
      context.result("Usuario no encontrado");
    }
  }

  public void login(Context context) {
    String nombre = context.formParam("nombre_usuario");
    String contrasenia = context.formParam("contrasenia");

    Usuario usuario = (Usuario) this.repositorioUsuarios.buscarPorNombre(nombre);

    Hasher hasher = new HasherEstandar();
    String contraseniaHash = hasher.hashear(contrasenia);
    if (usuario != null && usuario.getContrasenia().equals(contraseniaHash)) {
      context.sessionAttribute("usuario_id", usuario.getId());
      context.sessionAttribute("usuario_rol", usuario.getRol().getTipoRol().toString());
      context.status(HttpStatus.OK);
    } else {
      context.status(401);
    }
  }

  public void logout(Context context){
    context.sessionAttribute("usuario_id",null);
    context.status(HttpStatus.PERMANENT_REDIRECT).redirect("/login");
  }
}
