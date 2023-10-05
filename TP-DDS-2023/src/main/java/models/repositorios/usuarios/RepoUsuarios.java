package models.repositorios.usuarios;

import models.entities.usuarios.Usuario;

public interface RepoUsuarios {
  void nuevoUsuario(int id, String nombre, String contrasenia) throws java.io.IOException;
  Usuario devolverPorId(int id);
}
