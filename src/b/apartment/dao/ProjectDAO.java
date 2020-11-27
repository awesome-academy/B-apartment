package b.apartment.dao;

import b.apartment.entity.Apartments;
import b.apartment.entity.Projects;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectDAO extends GenericDAO<Projects, Integer> {

    public Page<Projects> paginate(Projects projects, Pageable pageable);

    public List<Projects> allProject();

}
