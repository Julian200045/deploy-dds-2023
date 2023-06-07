package domain.repositorios.servicios;

import domain.servicios.Servicio;

public interface RepoServicios {
	Servicio devolverPorId(int id);
	void agregarServicio(int id, String nombre);
}
