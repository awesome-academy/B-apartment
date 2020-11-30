package b.apartment.service.imp;

import b.apartment.dao.DistrictDAO;
import b.apartment.entity.Districts;
import b.apartment.entity.Provinces;
import b.apartment.model.DistrictModel;
import b.apartment.model.ProvinceModel;
import b.apartment.service.DistrictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class DistrictServiceImp implements DistrictService {
    private static Logger log = LoggerFactory.getLogger(DistrictServiceImp.class);
    @Autowired
    private DistrictDAO districtDAO;

    public void setDistrictDao(DistrictDAO districtDAO) {
        this.districtDAO = districtDAO;
    }

    public List<DistrictModel> getDistrictByProvince(Integer provinceId) {
        log.info("Filter district for province in the database");
        List<DistrictModel> districtModelList = new ArrayList<DistrictModel>();
        try {
            List<Districts> districList = districtDAO.getDistrictByProvince(provinceId);
            for (Districts districs : districList) {
                DistrictModel districtModel = new DistrictModel();
                BeanUtils.copyProperties(districs, districtModel);
                districtModelList.add(districtModel);
            }
        } catch (Exception e) {
            log.error("An error occurred while fetching all projects from the database", e);
        }
        return districtModelList;
    }

}
