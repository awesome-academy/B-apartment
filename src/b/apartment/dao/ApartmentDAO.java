package b.apartment.dao;

import java.util.List;

import b.apartment.model.ApartmentModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import b.apartment.dao.GenericDAO;
import b.apartment.entity.Apartments;

public interface ApartmentDAO extends GenericDAO<Apartments, Integer> {
	
	public Apartments findApartment(Apartments apartments);
	
	public Page<Apartments> paginate(Apartments apartments, Pageable pageable);
	
	public List<Apartments> findApartmentByProjectId(int project_id);

	public List<Apartments> apartmentsHot();

	public List<Apartments> apartmentsByProvince(ApartmentModel apartmentModel);
	
	public List<Apartments> searchApartments(Apartments searchA);
}
