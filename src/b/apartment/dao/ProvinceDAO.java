package b.apartment.dao;

import b.apartment.entity.Provinces;

import java.util.List;

public interface ProvinceDAO extends GenericDAO<Provinces, Integer>{
    public List<Provinces> allProvince();
}
