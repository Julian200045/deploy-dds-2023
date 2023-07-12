package services.notificador.enviadores;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import services.notificador.Notificacion;

public class EnviadorWPP {

        // Find your Account SID and Auth Token at twilio.com/console
        // and set the environment variables. See http://twil.io/secure
        public static final String ACCOUNT_SID = "AC7d09492bf9ac11c70922c23192c422f2";
        public static final String AUTH_TOKEN = "c720d5976d5bc6316b996d54cf182310";

        public static void enviar(Notificacion notificacion) {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(
                            new com.twilio.type.PhoneNumber("whatsapp:+12344152261"), //nro del que envia
                            new com.twilio.type.PhoneNumber("whatsapp:+14155238886"), //nro del receptor
                            notificacion.getIncidente().getObservaciones())
                    .create();

            System.out.println(message.getSid());
        }
    }
