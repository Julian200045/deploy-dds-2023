import domain.comunidades.Comunidad;
import domain.incidentes.Incidente;
import domain.informes.rankings.RankeadorMayorCantidadReportes;
import domain.informes.rankings.RankeadorTiempoPromedioCierre;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RankeadorTest {


	@Test
	public void rankingMayorCantidadReportesCreaSuLista(){
		Incidente incidente1 = mock(Incidente.class);
		Incidente incidente2 = mock(Incidente.class);
		Incidente incidente3 = mock(Incidente.class);
		Incidente incidente4 = mock(Incidente.class);
		Incidente incidente5 = mock(Incidente.class);
		Incidente incidente6 = mock(Incidente.class);

		when(incidente1.entidadNombre()).thenReturn("entidad del medio");
		when(incidente2.entidadNombre()).thenReturn("entidad de arriba");
		when(incidente3.entidadNombre()).thenReturn("entidad de abajo");
		when(incidente4.entidadNombre()).thenReturn("entidad del medio");
		when(incidente5.entidadNombre()).thenReturn("entidad de arriba");
		when(incidente6.entidadNombre()).thenReturn("entidad de arriba");

		//mockeo las horas para que no sean rechazados por ser incidentes muy recientes
		LocalDateTime fecha1 = LocalDateTime.now();
		when(incidente3.getFechaYHoraDeApertura()).thenReturn(fecha1);
		when(incidente1.getFechaYHoraDeApertura()).thenReturn(fecha1.minusDays(2));
		when(incidente2.getFechaYHoraDeApertura()).thenReturn(fecha1.minusDays(4));
		when(incidente4.getFechaYHoraDeApertura()).thenReturn(fecha1.minusDays(6));
		when(incidente5.getFechaYHoraDeApertura()).thenReturn(fecha1.minusDays(8));
		when(incidente6.getFechaYHoraDeApertura()).thenReturn(fecha1.minusDays(10));


		List<Incidente> incidentesSemanales = new ArrayList<>();
		incidentesSemanales.add(incidente1);
		incidentesSemanales.add(incidente2);
		incidentesSemanales.add(incidente3);
		incidentesSemanales.add(incidente4);
		incidentesSemanales.add(incidente5);
		incidentesSemanales.add(incidente6);

		RankeadorMayorCantidadReportes ranking = new RankeadorMayorCantidadReportes(incidentesSemanales);

		System.out.println(ranking.getListaRanking());
		assertEquals(ranking.getListaRanking().get(0).get(1), "entidad de arriba");
		assertEquals(ranking.getListaRanking().get(2).get(1), "entidad de abajo");

	}

	@Test
	public void rankingmayorCantidadReportesFiltraListaIncidentes() {
		RankeadorMayorCantidadReportes ranking = new RankeadorMayorCantidadReportes(null);

		Incidente incidente1 = mock(Incidente.class);
		Incidente incidente2 = mock(Incidente.class);
		Incidente incidente3 = mock(Incidente.class);

		when(incidente1.entidadNombre()).thenReturn("entidad del medio");
		when(incidente2.entidadNombre()).thenReturn("entidad de arriba");
		when(incidente3.entidadNombre()).thenReturn("entidad de abajo");

		LocalDateTime fecha1 = LocalDateTime.of(2001,7,6,6,6);
		LocalDateTime fecha2 = LocalDateTime.of(2000,4,3,3,3);
		LocalDateTime fecha3 = LocalDateTime.of(1999,1,1,1,1);

		when(incidente3.getFechaYHoraDeApertura()).thenReturn(fecha1);
		when(incidente1.getFechaYHoraDeApertura()).thenReturn(fecha2);
		when(incidente2.getFechaYHoraDeApertura()).thenReturn(fecha3);

		List<Incidente> incidentes = new ArrayList<>();
		incidentes.add(incidente1);
		incidentes.add(incidente2);
		incidentes.add(incidente3);

		List<Incidente> listaFiltrada = ranking.filtraIncidentes(incidentes);
		for(int i = 0; i<listaFiltrada.size(); i++){
			System.out.println(listaFiltrada.get(i).entidadNombre());
		}
	}

	@Test
	public void rankingTiempoPromedioCierreCreaSuLista(){
		Comunidad comunidad = new Comunidad("comunidad");
		Incidente incidente1 = mock(Incidente.class);
		Incidente incidente2 = mock(Incidente.class);
		Incidente incidente3 = mock(Incidente.class);

		when(incidente1.entidadNombre()).thenReturn("entidad del medio");
		when(incidente2.entidadNombre()).thenReturn("entidad de arriba");
		when(incidente3.entidadNombre()).thenReturn("entidad de abajo");

		long horas1 = 1;
		long horas2 = 2;
		long horas3 = 3;
		when(incidente3.tiempoDeCierre()).thenReturn(horas1);
		when(incidente1.tiempoDeCierre()).thenReturn(horas2);
		when(incidente2.tiempoDeCierre()).thenReturn(horas3);

		when(incidente1.getComunidad()).thenReturn(comunidad);
		when(incidente2.getComunidad()).thenReturn(comunidad);
		when(incidente3.getComunidad()).thenReturn(comunidad);


		List<Incidente> incidentesSemanales = new ArrayList<>();
		incidentesSemanales.add(incidente1);
		incidentesSemanales.add(incidente1);
		incidentesSemanales.add(incidente2);
		incidentesSemanales.add(incidente3);

		RankeadorTiempoPromedioCierre ranking = new RankeadorTiempoPromedioCierre(comunidad, incidentesSemanales);

		System.out.println(ranking.entradaTabla());
		assertEquals(ranking.entradaTabla().get(0).get(1), "entidad de arriba");
		assertEquals(ranking.entradaTabla().get(2).get(1), "entidad de abajo");

	}
}
