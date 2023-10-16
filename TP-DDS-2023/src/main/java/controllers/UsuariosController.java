package controllers;

import io.javalin.http.Context;
import models.entities.incidentes.Incidente;
import models.entities.usuarios.Usuario;
import models.repositorios.RepositorioUsuarios;
import server.utils.ICrudViewsHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsuariosController implements ICrudViewsHandler  {

    private RepositorioUsuarios repositorioUsuarios;
    public UsuariosController(RepositorioUsuarios repositorioUsuarios) {
        this.repositorioUsuarios = repositorioUsuarios;
    }

    @Override
    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();

        //String filtroPorNombre = context.queryParam("filtro");
        // this.repositorioDeIncidente.getAll(filtroPorNombre);

        List<Usuario> usuarios = this.repositorioUsuarios.buscarTodos();

        model.put("usuarios", usuarios);

        context.render("administracionUsuarios.hbs", model);
    }

    @Override
    public void show(Context context) {

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

    }

    @Override
    public void delete(Context context) {

    }
}
