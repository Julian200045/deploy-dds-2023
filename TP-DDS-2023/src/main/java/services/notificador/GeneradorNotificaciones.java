package services.notificador;

import domain.usuarios.Usuario;
import repositorios.notificaciones.RepoNotificaciones;

public class GeneradorNotificaciones {

    private RepoNotificaciones repoNotificaciones;

    public GeneradorNotificaciones(RepoNotificaciones repoNotificaciones){
        this.repoNotificaciones = repoNotificaciones;
    }
    public void generarNotificacion(Usuario usuarioANotificar, String mensaje) {
        try{
            if (usuarioANotificar.getMedioDeContacto() != null){
                Notificacion notificacion = new Notificacion(usuarioANotificar, mensaje);
                this.repoNotificaciones.add(notificacion);

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
