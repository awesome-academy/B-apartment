package b.apartment.dao;

import b.apartment.entity.Districts;

import java.util.List;

public interface DistrictDAO extends GenericDAO<Districts, Integer>{
    public List<Districts> getDistrictByProvince(Integer provinceId);
}
