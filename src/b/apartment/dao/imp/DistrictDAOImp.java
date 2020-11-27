package b.apartment.dao.imp;

import b.apartment.dao.DistrictDAO;
import b.apartment.entity.Districts;
import b.apartment.service.imp.DistrictServiceImp;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate5.HibernateCallback;

import java.util.List;

public class DistrictDAOImp extends GenericDAOImp<Districts, Integer> implements DistrictDAO {
    private static Logger log = LoggerFactory.getLogger(DistrictServiceImp.class);
    public DistrictDAOImp() {
        super(Districts.class);
    }

    public List<Districts> getDistrictByProvince(Integer provinceId) {
        try {
            return getHibernateTemplate().execute(new HibernateCallback<List<Districts>>() {
                public List<Districts> doInHibernate(Session session) throws HibernateException {
                    Query<Districts> query = session.createQuery("FROM Districts WHERE provinceId =: provinceId");
                    query.setParameter("provinceId", provinceId);
                    return query.list();
                }
            });
        } catch (Exception e) {
            log.error("Not found province", e);
            return null;
        }
    }
}
