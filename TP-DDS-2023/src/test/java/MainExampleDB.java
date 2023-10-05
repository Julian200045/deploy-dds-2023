import domain.establecimientos.TipoEstablecimiento;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import javax.persistence.EntityManager;

public class MainExampleDB implements WithSimplePersistenceUnit{
	public static void main(String[] args){
		new MainExampleDB().start();
	}

	public void start(){
		TipoEstablecimiento tipo = new TipoEstablecimiento();
		tipo.setNombre("tipoPrueba");
		entityManager().persist(tipo);
	}
}

