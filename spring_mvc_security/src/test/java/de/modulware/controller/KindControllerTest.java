package de.modulware.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import de.modulware.domain.Kind;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class KindControllerTest {
	
	@Autowired
	private DataInitializer dataInitializer;
	
	@Autowired
	private KindController kindController;
		
	@Before
	public void before() {
		dataInitializer.initData();
	}
	
	@Test
	public void shouldReturnKindListView() {
		ModelAndView mav = kindController.listKinder();
		assertEquals("list",mav.getViewName());
		
		@SuppressWarnings("unchecked")
		List<Kind> kinder = (List<Kind>) mav.getModelMap().get("kinder");
		assertNotNull(kinder);		
		assertEquals(DataInitializer.PERSON_COUNT,kinder.size());		
	}
	
	

	public void shouldReturnNewKindWithEditMav() {
		ModelAndView mav = kindController.editKind(null);
		assertNotNull(mav);
		assertEquals("edit", mav.getViewName());
		Object object = mav.getModel().get("kind");
		assertTrue(Kind.class.isAssignableFrom(object.getClass()));
		Kind kind = (Kind) object;
		assertNull(kind.getId());
		assertNull(kind.getFirstName());
		assertNull(kind.getLastName());		
	}
	
	@Test
	public void shouldReturnSecondKindWithEditMav() {
		Long template = dataInitializer.kindIds.get(1);
		ModelAndView mav = kindController.editKind(template);
		assertNotNull(mav);
		assertEquals("edit", mav.getViewName());
		Object object = mav.getModel().get("kind");
		assertTrue(Kind.class.isAssignableFrom(object.getClass()));
		Kind kind = (Kind) object;
		assertEquals(template,kind.getId());
	}
	
}
