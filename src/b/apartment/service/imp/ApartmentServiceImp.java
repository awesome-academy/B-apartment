package b.apartment.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import b.apartment.dao.ApartmentDAO;
import b.apartment.entity.Apartments;
import b.apartment.model.ApartmentModel;
import b.apartment.model.ProjectsModel;
import b.apartment.service.ApartmentService;
import b.apartment.service.imp.ApartmentServiceImp;


public class ApartmentServiceImp implements ApartmentService {
	private static Logger log = LoggerFactory.getLogger(ApartmentServiceImp.class);
	
	@Autowired
	private ApartmentDAO apartmentDAO;

	private ApartmentServiceImp() {
	}

	public void setApartmentDao(ApartmentDAO apartmentDAO) {
		this.apartmentDAO = apartmentDAO;
	}
	
	@Transactional
	public ApartmentModel addApartment(ApartmentModel apartmentModel) throws Exception {
		log.info("Them moi can ho vao co so du lieu");
		try {
			Apartments condition = new Apartments();
			condition.setId(apartmentModel.getId());
			condition.setName(apartmentModel.getName());
			condition.setFloor(apartmentModel.getFloor());
			condition.setBedrooms(apartmentModel.getBedrooms());
			condition.setBathrooms(apartmentModel.getBathrooms());
			condition.setCost(apartmentModel.getCost());
			condition.setArea(apartmentModel.getArea());
			condition.setProject_id(apartmentModel.getProject_id());
			condition.setUser_id(apartmentModel.getUser_id());
			condition.setDescription(apartmentModel.getDescription());
			Apartments user = apartmentDAO.makePersistent(condition);
			apartmentModel = new ApartmentModel();
			BeanUtils.copyProperties(user, apartmentModel);
			return apartmentModel;
		} catch (Exception e) {
			log.error("An error occurred while adding the user details to the database", e);
			throw e;
		}
	}
	
	@Transactional(readOnly = true)
	public ApartmentModel findApartment(Integer apartmentId) {
		log.info("Kiem tra can ho trong co so du lieu");
		try {
			Apartments apartment = apartmentDAO.find(apartmentId);
			ApartmentModel apartmentModel = new ApartmentModel();
			BeanUtils.copyProperties(apartment, apartmentModel);
			
			ProjectsModel projectsModel = new ProjectsModel();
			BeanUtils.copyProperties(apartment.getProject(), projectsModel);
			apartmentModel.setProject(projectsModel);
			return apartmentModel;
		} catch (Exception e) {
			log.error("An error occurred while fetching the apartment details from the database", e);
			return null;
		}
	}
	
	public List<ApartmentModel> findAll() {
		log.info("Tim nap tat ca cac can ho trong co so du lieu");
		List<ApartmentModel> apartmentModelList = new ArrayList<ApartmentModel>();
		try {
			List<Apartments> apartmentList = apartmentDAO.findAll();
			for (Apartments apartment : apartmentList) {
				ApartmentModel apartmentModel = new ApartmentModel();
				BeanUtils.copyProperties(apartment, apartmentModel);
				apartmentModelList.add(apartmentModel);
			}
		} catch (Exception e) {
			log.error("An error occurred while fetching all apartments from the database", e);
		}
		return apartmentModelList;
	}
	
	@Override
	public Page<ApartmentModel> paginate(ApartmentModel apartmentModel) {
		try {
			Apartments condition = new Apartments();
			apartmentModel.setPerPage(10);
			Page<Apartments> apartments = apartmentDAO.paginate(condition, apartmentModel.getPageable());
			return apartments.map(apartment -> {
				ApartmentModel model = new ApartmentModel();
				BeanUtils.copyProperties(apartment, model);
				return model;
			});
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}

}
