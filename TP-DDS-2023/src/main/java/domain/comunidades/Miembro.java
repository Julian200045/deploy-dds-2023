package domain.comunidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "miembro")
@Setter
@Getter
public class Miembro {
	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	@JoinColumn(name = "comunidad_id", referencedColumnName = "id")
	private Comunidad comunidad;

	@ManyToOne
	@JoinColumn(name = "persona_id", referencedColumnName = "id")
	private Persona persona;

	@Column
	private boolean esAdmin;

	@Enumerated(EnumType.STRING)
	private TipoMiembro tipoMiembro;

	public Miembro(){

	}
}
