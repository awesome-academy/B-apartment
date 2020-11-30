package b.apartment.service;

import b.apartment.model.DistrictModel;

import java.util.List;

public interface DistrictService {
    public List<DistrictModel> getDistrictByProvince(Integer provinceId);
}
