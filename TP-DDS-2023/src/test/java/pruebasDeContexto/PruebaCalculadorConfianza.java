package pruebasDeContexto;

import domain.comunidades.Comunidad;
import domain.comunidades.Miembro;
import domain.comunidades.Persona;
import domain.comunidades.TipoMiembro;
import org.checkerframework.checker.units.qual.C;
import services.calculadorConfianza.CalculadorConfianza;
import services.calculadorConfianza.CalculadorConfianzaService;
import services.calculadorConfianza.moldes.CalculadorConfianzaGrupo2;
import services.calculadorConfianza.requests.GeneradorRequestCalculadorConfianza;
import services.calculadorConfianza.requests.RequestCalculadorConfianza;

import java.io.IOException;

public class PruebaCalculadorConfianza {
  public static void main(String[] args) throws IOException {

    CalculadorConfianzaService calculadorConfianza = new CalculadorConfianza("src/main/resources/template/project.properties");
    GeneradorRequestCalculadorConfianza generador = new GeneradorRequestCalculadorConfianza();

    Comunidad comunidad = new Comunidad("Comunidad prueba");

    Persona persona1 = new Persona(1,"Juan1","Carlos1");
    Persona persona2 = new Persona(2,"Juan2","Carlos2");
    Persona persona3 = new Persona(3,"Juan3","Carlos3");
    Persona persona4 = new Persona(4,"Juan4","Carlos4");
    Persona persona5 = new Persona(5,"Juan5","Carlos5");

    Miembro miembro1 = new Miembro(1,comunidad,persona1, TipoMiembro.AFECTADO);
    Miembro miembro2 = new Miembro(2,comunidad,persona2, TipoMiembro.AFECTADO);
    Miembro miembro3 = new Miembro(3,comunidad,persona3, TipoMiembro.AFECTADO);
    Miembro miembro4 = new Miembro(4,comunidad,persona4, TipoMiembro.AFECTADO);
    Miembro miembro5 = new Miembro(5,comunidad,persona5, TipoMiembro.AFECTADO);

    comunidad.agregarMiembro(miembro1);
    comunidad.agregarMiembro(miembro2);
    comunidad.agregarMiembro(miembro3);
    comunidad.agregarMiembro(miembro4);
    comunidad.agregarMiembro(miembro5);

    RequestCalculadorConfianza request = generador.generar(comunidad);
    calculadorConfianza.calcularConfianza(request);

    System.out.println(request);
  }
}
