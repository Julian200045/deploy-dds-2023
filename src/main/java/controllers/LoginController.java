package controllers;

import io.javalin.http.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.entities.incidentes.Incidente;
import models.entities.usuarios.Usuario;
import models.repositorios.RepositorioUsuarios;

public class LoginController {

	private RepositorioUsuarios repositorioUsuarios;

	public void show(Context context){


		context.render("login.hbs");
	}


	public void autenticate(Context context){
		/*
		String user = context.formParam("usuario");
		String password = context.formParam("contrasena");


		if(repositorioUsuarios.existeUsername(user)){
			Usuario usuario = repositorioUsuarios.buscarPorUsername(user);
			if (usuario.getContrasenia() == password){
				context.redirect("/inicio");
			}
		}*/

	}
	public LoginController(RepositorioUsuarios repositorioUsuarios){
		this.repositorioUsuarios = repositorioUsuarios;
	}
}
