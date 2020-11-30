package b.apartment.controller;

import b.apartment.model.DistrictModel;
import b.apartment.model.ProjectsModel;
import b.apartment.model.ProvinceModel;
import b.apartment.service.DistrictService;
import b.apartment.service.ProjectService;
import b.apartment.service.ProvinceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
@EnableWebMvc
public class DistrictController {
    private static final Logger logger = LoggerFactory.getLogger(DistrictController.class);

    @Autowired
    @Qualifier("districtService")
    DistrictService districtService;

    @GetMapping(value = "/districts", produces = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<DistrictModel> projects(@RequestParam(name = "province_id") Integer province_id, Locale locale,
                           Model model) {
        List<DistrictModel> districts = districtService.getDistrictByProvince(province_id);
        return districts;
    }
}
