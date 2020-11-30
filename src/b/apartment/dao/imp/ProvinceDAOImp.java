package b.apartment.dao.imp;

import b.apartment.dao.ProvinceDAO;
import b.apartment.entity.Provinces;
import b.apartment.service.imp.ProvinceServiceImp;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate5.HibernateCallback;

import java.util.List;

public class ProvinceDAOImp extends GenericDAOImp<Provinces, Integer> implements ProvinceDAO {
    private static Logger log = LoggerFactory.getLogger(ProvinceServiceImp.class);
    public ProvinceDAOImp() {
        super(Provinces.class);
    }

    public List<Provinces> allProvince(){
        try {
            return getHibernateTemplate().execute(new HibernateCallback<List<Provinces>>() {
                public List<Provinces> doInHibernate(Session session) throws HibernateException {
                    Query<Provinces> query = session.createQuery("FROM Provinces");
                    return query.list();
                }
            });
        } catch (Exception e) {
            log.error("Not found province", e);
            return null;
        }
    }
}
