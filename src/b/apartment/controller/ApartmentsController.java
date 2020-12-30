
package b.apartment.controller;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import b.apartment.model.ApartmentModel;
import b.apartment.service.ProvinceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import b.apartment.interceptor.Flash;
import b.apartment.model.ProjectsModel;
import b.apartment.service.ApartmentService;
import b.apartment.service.FavouriteService;
import b.apartment.service.ProjectService;
import b.apartment.model.UserModel;
import b.apartment.uploader.ImageUpload;
import b.apartment.uploader.ImageUploader;

@Controller
@EnableWebMvc
public class ApartmentsController {
	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

	@Autowired
	MessageSource messageSource;

	@Autowired
	@Qualifier("apartmentService")
	ApartmentService apartmentService;

	@Autowired
	@Qualifier("projectService")
	ProjectService projectService;

	@Autowired
	@Qualifier("provinceService")
	ProvinceService provinceService;

	@Autowired
	@Qualifier("imageUploader")
	ImageUploader imageUploader;
	
	@Autowired
	@Qualifier("favouriteService")
	FavouriteService favouriteService;
	
	@Resource
	Flash flash;
	
	@ModelAttribute("apartment")
	public ApartmentModel apartment(@PathVariable(required = false) Integer id) {
		if (id == null) {
			return new ApartmentModel();
		} else {
			logger.info("Fetching user(" + id + ") info from database");
			ApartmentModel apartmentModel = apartmentService.findApartment(id);
			return apartmentModel;
		}
	}
	
	@GetMapping(value = "/apartments/add")
	public String add(Locale locale, Model model) {
		List<ProjectsModel> projects = projectService.findAll();
        model.addAttribute("projects", projects);
		return "apartments/add";
	}

	@PostMapping(value = "/apartments")
	public String create(@ModelAttribute("apartment") @Validated ApartmentModel apartmentModel, BindingResult bindingResult,
						 Model model, final RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {

		UserModel userModel = (UserModel) request.getSession().getAttribute("user");
		apartmentModel.setUser_id(userModel.getId());

		ImageUpload imageUpload = imageUploader.uploadFile(apartmentModel.getPicture());
		if (imageUpload != null) {
			apartmentModel.setUpload(imageUpload);
		}

		ApartmentModel apartment = apartmentService.addApartment(apartmentModel);
		// Add message to flash scope
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("flash", "apartment.create.success");
		flash.success("apartment.create.success");
		flash.keep();
		return "redirect: " + request.getContextPath() + "/";
	}
	
	@GetMapping(value = "/apartments")
	public String index(@RequestParam(name = "page", required = false) Optional<Integer> page, Locale locale,
			Model model) {
		ApartmentModel apartmentModel = new ApartmentModel();
		apartmentModel.setPage(page.orElse(1));
		Page<ApartmentModel> apartments = apartmentService.paginate(apartmentModel);
		model.addAttribute("apartments", apartments);
		return "apartments/index";
	}

	@GetMapping(value = "/apartments/province")
	public String apartmentsByProvince(Locale locale, Model model, @RequestParam(name = "province", required = false) Optional<Integer> province) {
		ApartmentModel apartmentModel = new ApartmentModel();
		if(province.isPresent()) {
			apartmentModel.setProvinceId(province.get());
		}
		List<ApartmentModel> apartments = apartmentService.apartmentsByProvince(apartmentModel);
		model.addAttribute("apartments", apartments);
		return "apartment_by_province/index";
	}
	
	@GetMapping(value = "/apartments/{id}")
	public String show(@PathVariable Integer id, Model model, 
			HttpServletRequest request,
			Authentication authentication) throws Exception {
		model.addAttribute("apartment", apartmentService.findApartment(id));
		UserModel userModel = (UserModel) request.getSession().getAttribute("user");
		boolean favourited = favouriteService.checkFavourite(userModel.getId(), id);
		model.addAttribute("favourited", favourited);
		return "apartments/show";
	}
}
