package b.apartment.service;

import java.util.List;

import org.springframework.data.domain.Page;

import b.apartment.entity.Apartments;
import b.apartment.model.ApartmentModel;

public interface ApartmentService {
	
	public ApartmentModel addApartment(ApartmentModel apartment) throws Exception;
	
	public ApartmentModel findApartment(Integer id);
	
	public List<ApartmentModel> findAll();
	
	public Page<ApartmentModel> paginate(ApartmentModel apartmentModel);

	public List<ApartmentModel> apartmentsHot();

	public List<ApartmentModel> apartmentsByProvince(ApartmentModel apartmentModel);
	
	public List<ApartmentModel> searchApartments(ApartmentModel search);
}
