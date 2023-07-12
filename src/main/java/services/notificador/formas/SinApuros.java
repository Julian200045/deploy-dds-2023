package services.notificador.formas;

import domain.usuarios.Usuario;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import services.notificador.Notificacion;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.quartz.TriggerBuilder.newTrigger;

public class SinApuros extends FormasNotificar {

    public void notificar(Notificacion notificacion) throws SchedulerException {

        Usuario usuario = notificacion.getUsuario();

        if(usuario.estaDisponible(LocalDateTime.now())){
            enviarNotificacion(notificacion);
        }
        else{
            if(!usuario.tieneNotificacionesPendientes()){

                Date horarioNotificacionIn = new Date();
                LocalDateTime ldt = LocalDateTime.ofInstant(horarioNotificacionIn.toInstant(), ZoneId.systemDefault());
                Date horarioNotificacion = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());

                Job notificarPendientes = new NotificarSinApuros(this,notificacion);

                JobDetail jobDetail = JobBuilder.newJob(notificarPendientes.getClass())
                    .withIdentity("NotificarSinApuros")
                    .build();

                SimpleTrigger trigger = (SimpleTrigger) newTrigger()
                    .withIdentity("NotificarPendientes")
                    .startAt(horarioNotificacion)
                    .forJob("NotificarSinApuros")
                    .build();

            }
            notificacion.getUsuario().agregarNotificacion(notificacion);
        }

    }
}
