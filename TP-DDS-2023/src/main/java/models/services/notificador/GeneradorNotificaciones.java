package models.services.notificador;

import models.entities.usuarios.Usuario;
import models.repositorios.notificaciones.RepoNotificaciones;
import models.repositorios.notificaciones.RepositorioNotificaciones;

public class GeneradorNotificaciones {

    private RepositorioNotificaciones repoNotificaciones;

    public GeneradorNotificaciones(RepositorioNotificaciones repoNotificaciones){
        this.repoNotificaciones = repoNotificaciones;
    }
    public void generarNotificacion(Usuario usuarioANotificar, String mensaje) {
        try{
            if (usuarioANotificar.getMedioDeContacto() != null){
                Notificacion notificacion = new Notificacion(usuarioANotificar, mensaje);
                this.repoNotificaciones.guardar(notificacion);

                usuarioANotificar.getForma().notificar(notificacion);
            }
            else {
                throw new MedioDeContactoException();
            }
        }
        catch (Exception e){

        }

    }
}
