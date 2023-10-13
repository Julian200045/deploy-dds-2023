package models.repositorios.usuarios;

import models.entities.usuarios.Usuario;

public interface RepoUsuarios {
  Usuario devolverPorId(int id);
}
