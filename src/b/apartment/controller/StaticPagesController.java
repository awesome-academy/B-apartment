package b.apartment.controller;

import java.util.Locale;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import b.apartment.interceptor.Flash;


@Controller
public class StaticPagesController {

	private static final Logger logger = LoggerFactory.getLogger(StaticPagesController.class);
	
	
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

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model,
			@RequestParam(name = "page", required = false) Optional<Integer> page, Authentication authentication) {
		logger.info("Home Page Requested, locale = " + locale);
//		CustomUserDetails userDetails = (CustomUserDetails)authentication.getPrincipal();
//		model.addAttribute("user", userService.findUser(userDetails.getUser().getId()));
//		MicropostModel micropostModel = new MicropostModel();
//		micropostModel.setUser_id(userDetails.getUser().getId());
//		micropostModel.setPage(page.orElse(1));
//		Page<MicropostModel> microposts = micropostService.paginate(micropostModel);
//		model.addAttribute("microposts", microposts);
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
//	
//	@PostMapping(value = "/contacts")
//	public String create(@ModelAttribute("contact") ContactModel contactModel, BindingResult bindingResult,
//			Model model, final RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {
//		
//		ContactModel contact = contactService.addContact(contactModel);
//		// Add message to flash scope
//		return "static_pages/contact";
//	}
	

	@RequestMapping(value = { "/access_denied" }, method = RequestMethod.GET)
	public String accessDenied() {
		logger.info("Access denied");
		return "access_denied";
	}
}
