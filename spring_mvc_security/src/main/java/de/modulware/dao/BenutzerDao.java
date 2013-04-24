package de.modulware.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import de.modulware.domain.Benutzer;

@Repository
public class BenutzerDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public Benutzer findByAlias(String alias) {
		List<Benutzer> list = entityManager.createQuery("select p from Benutzer p where p.alias = ?1").setParameter(1, alias).getResultList();
		return list.size() == 1 ? list.get(0) : null;
	}
	
}
