package b.apartment.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import b.apartment.model.ApartmentModel;
import b.apartment.model.FavouriteModel;
import b.apartment.model.RatingModel;
import b.apartment.model.UserModel;
import b.apartment.service.ApartmentService;
import b.apartment.service.RatingService;

@Controller
@EnableWebMvc
public class RatingController {
	private static final Logger logger = LoggerFactory.getLogger(RatingController.class);
	
	@Autowired
	@Qualifier("ratingService")
	RatingService ratingService;
	
	@Autowired
	@Qualifier("apartmentService")
	ApartmentService apartmentService;
	
	@PostMapping(value = "/rating", produces = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public boolean rating(@RequestParam Integer apartmentId, @RequestParam Integer star, Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserModel userModel = (UserModel) request.getSession().getAttribute("user");
		boolean ratingModel = ratingService.rating(apartmentId, userModel.getId(), star);
		String contectType = request.getContentType();
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		return ratingModel;
	}
}
