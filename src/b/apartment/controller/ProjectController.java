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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
@EnableWebMvc
public class ProjectController {
    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);
    @Autowired
    @Qualifier("projectService")
    ProjectService projectService;

    @Autowired
    @Qualifier("provinceService")
    ProvinceService provinceService;

    @Autowired
    @Qualifier("districtService")
    DistrictService districtService;

    @GetMapping(value = "/projects")
    public String projects(@RequestParam(name = "page", required = false) Optional<Integer> page, Locale locale,
                           Model model) {
        ProjectsModel projectsModel = new ProjectsModel();
        projectsModel.setPage(page.orElse(1));
        Page<ProjectsModel> projects = projectService.paginate(projectsModel);
        model.addAttribute("projects", projects);
        return "projects/index";
    }

    @GetMapping(value = "/projects/add")
    public String addProject(Locale locale, Model model) {
        ProjectsModel project = new ProjectsModel();
        model.addAttribute("project", project);
        List<ProvinceModel> provinces = provinceService.getAllProvince();
        model.addAttribute("provinces", provinces);
        return "projects/add";
    }
}
