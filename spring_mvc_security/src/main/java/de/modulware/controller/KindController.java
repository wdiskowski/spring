package de.modulware.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import de.modulware.dao.KindDao;
import de.modulware.dao.KitaDao;
import de.modulware.domain.Kind;
import de.modulware.domain.Kita;

@Controller
@RequestMapping("/kind/")
public class KindController {
	
	private static final String VIEW_LIST = "list";

	private static final String VIEW_EDIT = "edit";

	private static final Logger logger = LoggerFactory.getLogger(KindController.class);

	@Autowired
	private KindDao kindDao;
	
	@Autowired
	private KitaDao kitaDao;

	@Autowired
	private KitaEditor kitaEditor;
	
	
	@RequestMapping(method=RequestMethod.GET,value=VIEW_EDIT)
	public ModelAndView editKind(@RequestParam(value="id",required=false) Long id) {		
		logger.debug("Received request to edit kind id : "+id);				
		ModelAndView mav = new ModelAndView();		
 		mav.setViewName(VIEW_EDIT);
 		Kind kind = null;
 		if (id == null) {
 			kind = new Kind();
 		} else {
 			kind = kindDao.findForWrite(id);
 		}
 		
 		mav.addObject("kind", kind).addObject("kitas", kitaDao.getKitasWithKinderForWrite());
		return mav;
		
	}
	
	@RequestMapping(method=RequestMethod.POST,value=VIEW_EDIT) 
	public String saveKind(@ModelAttribute @Valid Kind kind) {
		logger.debug("Received postback on kind "+kind);		
		kindDao.save(kind);
		return "redirect:../kita/list";
		
	}
	
	@RequestMapping(method=RequestMethod.GET,value=VIEW_LIST)
	public ModelAndView listKinder() {
		logger.debug("Received request to list kinds");
		ModelAndView mav = new ModelAndView();
		List<Kind> kinder = kindDao.getKinder();
		logger.debug("Kind Listing count = "+kinder.size());
		mav.addObject("kinder",kinder);
		mav.setViewName(VIEW_LIST);
		return mav;
		
	}
	
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Kita.class, this.kitaEditor);
    }

}
