package server;

import com.google.gson.Gson;
import models.entities.roles.Rol;
import models.entities.usuarios.Usuario;
import models.repositorios.RepositorioRol;
import models.repositorios.RepositorioUsuarios;
import server.dtos.UsuarioDto;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class mainTestIan {
    public static void main(String[] args) throws IOException {
//        Rol admin = new Rol("Administrador");
//        Rol cliente = new Rol("Cliente");
//        Rol supervisor = new Rol("Supervisor");
        RepositorioRol repositorioRol = new RepositorioRol();
        Rol cliente = (Rol) repositorioRol.buscar(Long.parseLong("14"));
//        repositorioRol.guardar(admin);
//        repositorioRol.guardar(cliente);
//        repositorioRol.guardar(supervisor);
        Usuario julian = new Usuario("Julian ","juliangrupo10", "j@gmail.com","1122334455",LocalDateTime.now(),LocalDateTime.now());
        julian.setRol(cliente);
        Usuario ian = new Usuario("Ian ","iangrupo10", "i@gmail.com","1122334455",LocalDateTime.now(),LocalDateTime.now());
        ian.setRol(cliente);
        Usuario dante = new Usuario("Dante","dantegrupo10", "d@gmail.com","1122334455",LocalDateTime.now(),LocalDateTime.now());
        dante.setRol(cliente);
        Usuario tomas = new Usuario("Tomas","tomasgrupo10", "t@gmail.com","1122334455",LocalDateTime.now(),LocalDateTime.now());
        tomas.setRol(cliente);
        RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();
        repositorioUsuarios.guardar(julian);
        repositorioUsuarios.guardar(ian);
        repositorioUsuarios.guardar(dante);
        repositorioUsuarios.guardar(tomas);
//        Gson gson = new Gson();

//        System.out.println(gson.toJson(repositorioRol.buscarTodos()));
//        System.out.println(gson.toJson(repositorioUsuarios.buscarTodos()));
//        UsuarioDto ianDto = new UsuarioDto(Long.parseLong("100"),"ian","iansasas@asads","1231312321",cliente);
//        System.out.println(gson.toJson(ianDto));
    }
}
