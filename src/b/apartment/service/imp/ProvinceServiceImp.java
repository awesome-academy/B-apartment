package b.apartment.service.imp;

import b.apartment.dao.ProvinceDAO;
import b.apartment.entity.Provinces;
import b.apartment.model.ProvinceModel;
import b.apartment.service.ProvinceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ProvinceServiceImp implements ProvinceService {

    private static Logger log = LoggerFactory.getLogger(ProvinceServiceImp.class);
    @Autowired
    private ProvinceDAO provinceDAO;

    public void setProvinceDao(ProvinceDAO provinceDAO) {
        this.provinceDAO = provinceDAO;
    }

    public List<ProvinceModel> getAllProvince() {
        log.info("Fetching all province in the database");
        List<ProvinceModel> provinceModelList = new ArrayList<ProvinceModel>();
        try {
            List<Provinces> provinceList = provinceDAO.allProvince();
            for (Provinces provinces : provinceList) {
                ProvinceModel provinceModel = new ProvinceModel();
                BeanUtils.copyProperties(provinces, provinceModel);
                provinceModelList.add(provinceModel);
            }
        } catch (Exception e) {
            log.error("An error occurred while fetching all projects from the database", e);
        }
        return provinceModelList;
    }
}
