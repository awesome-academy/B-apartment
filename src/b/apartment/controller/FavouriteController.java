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
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String relationship(@RequestParam Integer apartmentId, Authentication authentication,
			HttpServletRequest request, Model model, @Validated FavouriteModel favouriteModel) throws Exception {
		
		UserModel userModel = (UserModel) request.getSession().getAttribute("user");
		favouriteModel.setUserId(userModel.getId());
		
		List<FavouriteModel> favourites = favouriteService.findAll();
		FavouriteModel favouritesModel = new FavouriteModel();
		model.addAttribute("favourites", favouritesModel);
		System.out.println("show danh sach favourite thanh cong");
		return "favourite/index";
	}

}
