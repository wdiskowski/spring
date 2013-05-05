package de.modulware.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import de.modulware.dao.KitaDao;
import de.modulware.domain.Kita;

@Controller
@RequestMapping("/kita/")
public class KitaController {
	
	private static final String VIEW_LIST = "list";

	private static final Logger logger = LoggerFactory.getLogger(KitaController.class);

	@Autowired
	private KitaDao kitaDao;
	
	
	@RequestMapping(method=RequestMethod.GET,value=VIEW_LIST)
	public ModelAndView listKitas() {
		logger.debug("Received request to list kitas");
		ModelAndView mav = new ModelAndView();
		List<Kita> kitasForRead = kitaDao.getKitasWithKinderForRead();
		List<Kita> kitasForWrite = kitaDao.getKitasWithKinderForWrite();
		logger.debug("Kita Listing count = "+ (kitasForRead.size() + kitasForWrite.size()));
		mav.addObject("kitasForRead", kitasForRead);
		mav.addObject("kitasForWrite", kitasForWrite);
		mav.setViewName(VIEW_LIST);
		return mav;
		
	}

}
