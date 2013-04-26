package de.modulware.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.modulware.domain.Kind;

@Repository
public class KindDao {
	
	private static final Logger logger = LoggerFactory.getLogger(KindDao.class);

	@PersistenceContext
	private EntityManager entityManager;
	
	@PostAuthorize("hasPermission(returnObject, 'WRITE')")
	public Kind findForWrite(Long id) {
		return entityManager.find(Kind.class, id);
	}

	@PostAuthorize("hasPermission(returnObject, 'READ')")
	public Kind findForRead(Long id) {
		return entityManager.find(Kind.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Kind> getKinder() {
		return entityManager.createQuery("select p from Kind p").getResultList();
	}
	
	@PreAuthorize("hasPermission(#kind, 'WRITE')")
	@Transactional
	public Kind save(Kind kind) {
		logger.info("save Kind " + kind);
		if (kind.getId() == null) {
			entityManager.persist(kind);
			entityManager.flush();
			return kind;
		} else {
			entityManager.merge(kind);
			entityManager.flush();
			return kind;
		}		
	}	
	
}
