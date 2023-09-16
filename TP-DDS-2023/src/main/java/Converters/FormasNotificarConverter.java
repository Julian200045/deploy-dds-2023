package Converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import services.notificador.formas.EnElMomento;
import services.notificador.formas.FormasNotificar;
import services.notificador.formas.sinApuros.SinApuros;

@Converter(autoApply = true)
public class FormasNotificarConverter implements AttributeConverter<FormasNotificar, String> {
	@Override
	public String convertToDatabaseColumn(FormasNotificar formasNotificar) {
		String medioEnBase = null;

		if(formasNotificar.getClass().getName().equals("EnElMomento")) {
			medioEnBase = "enElMomento";
		}
		else if(formasNotificar.getClass().getName().equals("SinApuros")) {
			medioEnBase = "sinApuros";
		}
		return medioEnBase;
	}

	@Override
	public FormasNotificar convertToEntityAttribute(String s) {
		FormasNotificar forma = null;

		if(s.equals("enElMomento")) {
			forma = new EnElMomento();
		}
		else if(s.equals("sinApuros")) {
			forma = new SinApuros(null);
		}
		return forma;
	}
}