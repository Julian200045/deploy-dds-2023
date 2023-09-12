package services.notificador.formas.sinApuros;

import domain.usuarios.Usuario;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import repositorios.notificaciones.RepoNotificaciones;
import services.notificador.EstadoEnvio;
import services.notificador.Notificacion;
import services.notificador.formas.FormasNotificar;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class SinApuros extends FormasNotificar {

    private RepoNotificaciones repoNotificaciones;

    public SinApuros(RepoNotificaciones repoNotificaciones){
        this.repoNotificaciones = repoNotificaciones;
    }
    public void notificar(Notificacion notificacion) throws SchedulerException, IOException {
        Usuario usuario = notificacion.getUsuario();
        if(usuario.estaDisponible(LocalDateTime.now())){
            enviarNotificacion(notificacion);
        }
    }

    public void iniciarEnvioNotificaciones() throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        scheduler.start();

        JobDetail job = newJob(Notificar.class)
            .withIdentity("notificar")
            .build();

        job.getJobDataMap().put("servicioNotificador",this);

        Trigger trigger = newTrigger()
            .withIdentity("everyMinute")
            .startNow()
            .withSchedule(simpleSchedule()
                .withIntervalInMinutes(1)
                .repeatForever())
            .forJob("notificar")
            .build();

        scheduler.scheduleJob(trigger);
    }

    public void notificarPendientes(){
        repoNotificaciones.getAllByEstado(EstadoEnvio.PENDIENTE).forEach(notificacion ->
            {
                try {
                    enviarNotificacion(notificacion);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        );
    }
}
