package de.modulware.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.modulware.domain.Kita;

@Repository
public class KitaDao {
	
	private static final Logger logger = LoggerFactory.getLogger(KitaDao.class);

	@PersistenceContext
	private EntityManager entityManager;
	
	public Kita find(Long id) {
		return entityManager.find(Kita.class, id);
	}
	
	@PostFilter("hasPermission(filterObject, 'READ')")
	@SuppressWarnings("unchecked")
	public List<Kita> getKitasWithKinderForRead() {
		return entityManager.createQuery("select distinct p from Kita p left join fetch p.kinder").getResultList();
	}

	@PostFilter("hasPermission(filterObject, 'WRITE')")
	@SuppressWarnings("unchecked")
	public List<Kita> getKitasWithKinderForWrite() {
		return entityManager.createQuery("select distinct p from Kita p left join fetch p.kinder").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Kita> getKitas() {
		return entityManager.createQuery("select p from Kita p").getResultList();
	}
	
	@Transactional
	public Kita save(Kita kita) {
		logger.info("save Kita " + kita);
		if (kita.getId() == null) {
			entityManager.persist(kita);
			return kita;
		} else {
			return entityManager.merge(kita);
		}		
	}
	
	
}
