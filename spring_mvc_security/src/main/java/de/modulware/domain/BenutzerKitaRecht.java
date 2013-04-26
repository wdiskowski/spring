package de.modulware.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Access(AccessType.PROPERTY)
public class BenutzerKitaRecht {

	private Long id;
	
	private Benutzer benutzer;
	
	private Kita kita;
	
	private PermissionType recht;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name="benutzer_id")
	public Benutzer getBenutzer() {
		return benutzer;
	}

	public void setBenutzer(Benutzer benutzer) {
		this.benutzer = benutzer;
	}

	@ManyToOne
	@JoinColumn(name="kita_id")
	public Kita getKita() {
		return kita;
	}

	public void setKita(Kita kita) {
		this.kita = kita;
	}

	@Enumerated(EnumType.STRING)
	public PermissionType getRecht() {
		return recht;
	}

	public void setRecht(PermissionType recht) {
		this.recht = recht;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE, false);
	}

}
