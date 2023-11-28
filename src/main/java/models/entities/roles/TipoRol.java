package models.entities.roles;

import io.javalin.security.RouteRole;

public enum TipoRol implements RouteRole {
	ADMIN,
	NORMAL,
	ORGANISMO,
	ENTIDADPRESTADORA
}
