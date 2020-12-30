package b.apartment.controller;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import b.apartment.model.ApartmentModel;
import b.apartment.model.FavouriteModel;
import b.apartment.model.UserModel;
import b.apartment.service.FavouriteService;

@Controller
@EnableWebMvc
public class FavouriteController {
	
	private static final Logger logger = LoggerFactory.getLogger(FavouriteController.class);

	@Autowired
	@Qualifier("favouriteService")
	FavouriteService favouriteService;

	@GetMapping(value = "/favourites")
	public String relationship(Authentication authentication,
			HttpServletRequest request, Model model) throws Exception {
		UserModel userModel = (UserModel) request.getSession().getAttribute("user");
		List<FavouriteModel> favourites = favouriteService.findFavouriteByUserId(userModel.getId());
		model.addAttribute("favourites", favourites);
		return "favourite/index";
	}
	
	@PostMapping(value = "/favourites", produces = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public FavouriteModel favourite(@RequestParam Integer apartmentId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("abc: " + apartmentId);
//		Integer apartmentId = Integer.parseInt(request.getParameter("apartmentId"));
		UserModel userModel = (UserModel) request.getSession().getAttribute("user");
		
		if(favouriteService.checkFavourite(userModel.getId(), apartmentId) == false)
		{
			FavouriteModel favouriteModel = favouriteService.favourite(userModel.getId(), apartmentId);
			String contectType = request.getContentType();
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			return favouriteModel;
		}
		else
		{
			System.out.println("ban da luu can ho nay");
			return null;
		}
	}
}
