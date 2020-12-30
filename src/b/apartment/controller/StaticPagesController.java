package b.apartment.controller;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import b.apartment.interceptor.Flash;
import b.apartment.model.ApartmentModel;
import b.apartment.service.ApartmentService;


@Controller
public class StaticPagesController {

	private static final Logger logger = LoggerFactory.getLogger(StaticPagesController.class);
	@Autowired
	@Qualifier("apartmentService")
	ApartmentService apartmentService;
	
	@Resource
	private Flash flash;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String root(Locale locale, Model model, final RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		logger.info("Home Page Requested, locale = " + locale);
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("flash", "user.create.success");
		flash.info("user.create.success");
		flash.keep();
		return "home.page";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model,
			@RequestParam(name = "page", required = false) Optional<Integer> page, Authentication authentication) {
		logger.info("Home Page Requested, locale = " + locale);
		List<ApartmentModel> apartments = apartmentService.apartmentsHot();
		model.addAttribute("apartments", apartments);
		return "static_pages/home";
	}

	@RequestMapping(value = "/help", method = RequestMethod.GET)
	public String help(Locale locale, Model model) {
		logger.info("Home Page Requested, locale = " + locale);
		return "static_pages/help";
	}

	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about(Locale locale, Model model) {
		logger.info("Home Page Requested, locale = " + locale);
		return "static_pages/about";
	}

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contact(Locale locale, Model model) {
		logger.info("Home Page Requested, locale = " + locale);
		return "static_pages/contact";
	}
	
	@RequestMapping(value = { "/access_denied" }, method = RequestMethod.GET)
	public String accessDenied() {
		logger.info("Access denied");
		return "access_denied";
	}
	
	@GetMapping(value = "/search", produces = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
	public List<ApartmentModel> search(Locale locale, Model model, Authentication authentication,
			@RequestParam String key)
	{
		ApartmentModel apartmentModel = new ApartmentModel();
		apartmentModel.setName(key);
		
		List<ApartmentModel> apartmentModelList = apartmentService.searchApartments(apartmentModel);
        model.addAttribute("apartmentModel", apartmentModelList);
        return apartmentModelList;
	}
}
