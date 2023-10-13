package models.entities.comunidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "comunidad_id", referencedColumnName = "id")
	private Comunidad comunidad;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "persona_id", referencedColumnName = "id")
	private Persona persona;

	@Column(name = "es_admin")
	private boolean esAdmin;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_miembro")
	private TipoMiembro tipoMiembro;

	public Miembro(Comunidad comunidad, Persona persona, boolean esAdmin, TipoMiembro tipoMiembro) {
		this.comunidad = comunidad;
		this.persona = persona;
		this.esAdmin = esAdmin;
		this.tipoMiembro = tipoMiembro;
	}
	public Miembro(){

	}
}
