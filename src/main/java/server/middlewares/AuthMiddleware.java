package server.middlewares;

import io.javalin.config.JavalinConfig;
import io.javalin.http.Context;
import java.util.ArrayList;
import java.util.List;
import models.entities.roles.TipoRol;
import server.exceptions.NoAutenticadoException;
import server.exceptions.RolNoPermitidoException;

public class AuthMiddleware {

	private  static List<String> pathsPermitidosSinUsuario = new ArrayList<>() {{
		add("/login");
		add("/usuarios/login");
		add("/usuarios/crear");
		add("/usuarios");
	}};


	public static void apply(JavalinConfig config){
		config.accessManager(((handler, context, routeRoles) ->{
			TipoRol userRol = getUserRoleType(context);

			if(context.sessionAttribute("usuario_id") == null &&	!pathsPermitidosSinUsuario.contains(context.path())) {//verifica el logeo
				throw new NoAutenticadoException();
			}
			else if(routeRoles.size() != 0 && !routeRoles.contains(userRol)){//verifica el rol
				throw new RolNoPermitidoException();
			}
			else {
				handler.handle(context);
			}
		}));
	}

	private static TipoRol getUserRoleType(Context context){
		return context.sessionAttribute("usuario_rol") != null?
				TipoRol.valueOf(context.sessionAttribute("usuario_rol")): null;
	}
}

