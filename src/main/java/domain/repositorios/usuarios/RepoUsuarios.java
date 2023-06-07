package domain.repositorios.usuarios;

import domain.usuarios.Usuario;

public interface RepoUsuarios {
  void nuevoUsuario(int id, String nombre, String contrasenia) throws java.io.IOException;
  Usuario devolverPorId(int id);
}
