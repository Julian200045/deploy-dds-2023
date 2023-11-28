package server.handlers;

import io.javalin.Javalin;
import server.exceptions.NoAutenticadoException;

public class NoAutenticadoHandler implements IHandler{

	@Override
	public void setHandle(Javalin app) {
		app.exception(NoAutenticadoException.class, (e, ctx) -> {
			ctx.redirect("/login");
		});
	}
}