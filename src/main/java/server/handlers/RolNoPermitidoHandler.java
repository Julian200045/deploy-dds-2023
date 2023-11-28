package server.handlers;

import io.javalin.Javalin;
import server.exceptions.RolNoPermitidoException;

public class RolNoPermitidoHandler implements IHandler {
	@Override
	public void setHandle(Javalin app) {
		app.exception(RolNoPermitidoException.class, (e, ctx) -> {
			ctx.redirect("/incidentes");
		});
	}
}
