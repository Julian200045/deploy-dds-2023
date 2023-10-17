package server;

import models.entities.roles.Rol;
import models.entities.usuarios.Usuario;
import models.repositorios.RepositorioRol;
import models.repositorios.RepositorioUsuarios;

import java.io.IOException;
import java.time.LocalDateTime;

public class mainTestIan {
    public static void main(String[] args) throws IOException {
//        Rol admin = new Rol("Administrador");
//        Rol cliente = new Rol("Cliente");
//        Rol supervisor = new Rol("Supervisor");
        RepositorioRol repositorioRol = new RepositorioRol();
//        repositorioRol.guardar(admin);
//        repositorioRol.guardar(cliente);
//        repositorioRol.guardar(supervisor);
//        Usuario julian = new Usuario("Julian ","juliangrupo10", "j@gmail.com","1122334455",LocalDateTime.now(),LocalDateTime.now());
//        julian.setRol(admin);
//        Usuario ian = new Usuario("Ian ","iangrupo10", "i@gmail.com","1122334455",LocalDateTime.now(),LocalDateTime.now());
//        ian.setRol(cliente);
//        Usuario dante = new Usuario("Dante","dantegrupo10", "d@gmail.com","1122334455",LocalDateTime.now(),LocalDateTime.now());
//        dante.setRol(supervisor);
//        Usuario tomas = new Usuario("Tomas","tomasgrupo10", "t@gmail.com","1122334455",LocalDateTime.now(),LocalDateTime.now());
//        tomas.setRol(cliente);
        RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();
//        repositorioUsuarios.guardar(julian);
//        repositorioUsuarios.guardar(ian);
//        repositorioUsuarios.guardar(dante);
//        repositorioUsuarios.guardar(tomas);
        System.out.println(repositorioRol.buscarTodos());
        System.out.println(repositorioUsuarios.buscarTodos());
    }
}
