package de.modulware.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import de.modulware.controller.DataInitializer;
import de.modulware.domain.Kind;
import de.modulware.domain.Kita;

@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class KindDaoTest {

	@Autowired
	private KindDao kindDao;
	@Autowired
	private KitaDao kitaDao;

	@Autowired
	private DataInitializer dataInitializer;

	@Before
	public void prepareData() {
		dataInitializer.initData();
	}

	@Test
	public void shouldSaveAKind() {
		Kita kita = new Kita();
		kita.setName("kita1");
		kitaDao.save(kita);
		Assert.assertNotNull(kita.getId());
		Kind p = new Kind();
		p.setFirstName("Andy");
		p.setLastName("Gibson");
		p.setKita(kita);
		kindDao.save(p);
		Long id = p.getId();
		Assert.assertNotNull(id);
	}

	@Test
	public void shouldLoadAKind() {
		Long template = dataInitializer.kindIds.get(0);
		Kind p = kindDao.findForWrite(template);

		Assert.assertNotNull("Kind not found!", p);
		Assert.assertEquals(template, p.getId());
	}

	public void shouldListKinder() {
		List<Kind> kinder = kindDao.getKinder();
		Assert.assertEquals(DataInitializer.PERSON_COUNT, kinder.size());

	}

}
