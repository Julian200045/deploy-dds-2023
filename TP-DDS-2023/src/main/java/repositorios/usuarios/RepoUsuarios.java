package repositorios.usuarios;

import domain.usuarios.Usuario;

public interface RepoUsuarios {
  Usuario devolverPorId(int id);
}
