package controllers;

import com.google.gson.Gson;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import models.entities.incidentes.Incidente;
import models.entities.usuarios.Usuario;
import models.repositorios.RepositorioUsuarios;
import server.dtos.UsuarioDto;
import server.utils.ICrudViewsHandler;

import java.util.*;

public class UsuariosController implements ICrudViewsHandler  {

    private RepositorioUsuarios repositorioUsuarios;
    public UsuariosController(RepositorioUsuarios repositorioUsuarios) {
        this.repositorioUsuarios = repositorioUsuarios;
    }

    @Override
    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();

        List<Usuario> usuarios = this.repositorioUsuarios.buscarTodos();
        List<UsuarioDto> usuarioDtos = usuarios.stream().map(usuario -> new UsuarioDto(usuario.getId(),usuario.getNombre(),usuario.getMail(),usuario.getNumeroCelular(),usuario.getRol())).toList();
        Gson gson = new Gson();
        System.out.println(gson.toJson(usuarioDtos));
        System.out.println(usuarios.stream().map(usuario -> usuario.getRol().nombre).toList());
        model.put("users", usuarioDtos);
        context.render("administracion_usuarios.hbs", model);
    }

    @Override
    public void show(Context context) {
        String tipoBusqueda = context.queryParam("tipoBusqueda");
        List<Usuario> users = new ArrayList<>();
        switch (tipoBusqueda){
            case "nombre":
                String nombre = context.queryParam("nombre");
                 users = this.repositorioUsuarios.buscarPorNombre(nombre);
                break;
            case "id":
                String id = context.queryParam("id");
                users = (List<Usuario>) this.repositorioUsuarios.buscar(Long.parseLong(id));
                break;
        }
        if(!users.isEmpty()){
            List<UsuarioDto> usersDto = users.stream().map(usuario -> new UsuarioDto(usuario.getId(),usuario.getNombre(), usuario.getMail(), usuario.getNumeroCelular(), usuario.getRol())).toList();
            System.out.println(usersDto);
            context.json(usersDto);
        }
        else {
            context.status(HttpStatus.NOT_FOUND);
            context.result("Usuario no encontrado");
        }

    }

    @Override
    public void create(Context context) {

    }

    @Override
    public void save(Context context) {

    }

    @Override
    public void edit(Context context) {

    }

    @Override
    public void update(Context context) {
        Long longId = Long.parseLong(context.queryParam("id"));
        Usuario user = (Usuario) this.repositorioUsuarios.buscar(longId).get(0);
        if(user != null){
            user.setNumeroCelular(context.queryParam("telefono"));
            user.setMail(context.queryParam("mail"));
            user.setNombre(context.queryParam("nombre"));
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
        if(user != null){
            this.repositorioUsuarios.eliminar(user);
            context.result("Usuario eliminado");
        } else{
            context.status(HttpStatus.NOT_FOUND);
            context.result("Usuario no encontrado");
        }
    }
}
