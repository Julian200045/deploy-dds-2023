import domain.incidentes.Incidente;
import domain.informes.rankings.RankingMayorCantidadReportes;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RankingTest {


	@Test
	public void rankingMayorCantidadReportesCreaSuLista(){
		Incidente incidente1 = mock(Incidente.class);
		Incidente incidente2 = mock(Incidente.class);
		Incidente incidente3 = mock(Incidente.class);

		when(incidente1.entidadNombre()).thenReturn("entidad del medio");
		when(incidente2.entidadNombre()).thenReturn("entidad de arriba");
		when(incidente3.entidadNombre()).thenReturn("entidad de abajo");

		//mockeo las horas para que no sean rechazados por ser incidentes muy recientes
		long horas = 25;
		when(incidente3.horasDesdeQueSeAbrio()).thenReturn(horas);
		when(incidente2.horasDesdeQueSeAbrio()).thenReturn(horas);
		when(incidente1.horasDesdeQueSeAbrio()).thenReturn(horas);

		List<Incidente> incidentesSemanales = new ArrayList<>();
		incidentesSemanales.add(incidente1);
		incidentesSemanales.add(incidente1);
		incidentesSemanales.add(incidente2);
		incidentesSemanales.add(incidente2);
		incidentesSemanales.add(incidente2);
		incidentesSemanales.add(incidente3);

		RankingMayorCantidadReportes ranking = new RankingMayorCantidadReportes(incidentesSemanales);

		System.out.println(ranking.getListaRanking());
		assertEquals(ranking.getListaRanking().get(0).get(1), "entidad de arriba");
		assertEquals(ranking.getListaRanking().get(2).get(1), "entidad de abajo");

	}
}
