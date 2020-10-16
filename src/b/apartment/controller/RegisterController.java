package b.apartment.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import b.apartment.service.UserService;

import b.apartment.model.UserModel;
import b.apartment.validator.UserValidator;

@Controller
public class RegisterController {

	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

	@Autowired
	MessageSource messageSource;
	
	@Autowired
	@Qualifier("userService")
	UserService userService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new UserValidator());
	}
	
	@ModelAttribute("user")
	public UserModel createUserModel() {
		// ModelAttribute value should be same as used in the empSave.jsp
		return new UserModel();
	}
	
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Locale locale, Model model) {
		return "sessions/signup";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@ModelAttribute("user") @Validated UserModel userModel, BindingResult bindingResult,
            Model model, final RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {
		if (bindingResult.hasErrors()) {
			logger.info("Returning register.jsp page, validate failed");
			return "sessions/signup";
	}
	
	UserModel user = userService.addUser(userModel);
	
	return "static_pages/home";
	}
}		
