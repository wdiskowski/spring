package de.modulware.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.modulware.domain.Kind;
import de.modulware.domain.Kita;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class DataInitializer {

	public static final int PERSON_COUNT = 3;

	@PersistenceContext
	private EntityManager entityManager;

	public List<Long> kindIds = new ArrayList<Long>();
	public List<Long> kitaIds = new ArrayList<Long>();

	public void initData() {
		kitaIds.clear();
		Kita kita1 = createKita("wiesbaden1");
		Kita kita2 = createKita("moembris1");
		kindIds.clear();// clear out the previous list of kinder
		addKind("Jim", "Smith", kita1);
		addKind("Tina", "Marsh", kita1);
		addKind("Steve", "Blair", kita2);
		entityManager.flush();
		entityManager.clear();
	}
	
	public Kita createKita(String name) {
		Kita kita = new Kita();
		kita.setName(name);
		entityManager.persist(kita);
		kitaIds.add(kita.getId());
		return kita;
	}


	public void addKind(String firstName, String lastName, Kita kita) {
		Kind p = new Kind();
		p.setFirstName(firstName);
		p.setLastName(lastName);
		p.setKita(kita);
		entityManager.persist(p);
		kindIds.add(p.getId());
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
}
