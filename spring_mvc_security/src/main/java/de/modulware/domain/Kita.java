package de.modulware.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@Entity
@Access(AccessType.PROPERTY)
public class Kita implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private String name;
	
	private transient List<Kind> kinder = new ArrayList<Kind>();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	@OneToMany(mappedBy="kita", cascade=CascadeType.ALL, orphanRemoval=true)
	public List<Kind> getKinder() {
		return kinder;
	}

	public void setKinder(List<Kind> kinder) {
		this.kinder = kinder;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE, false);
	}

}
