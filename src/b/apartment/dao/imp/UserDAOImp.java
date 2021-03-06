package b.apartment.dao.imp;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import b.apartment.dao.UserDAO;
import b.apartment.entity.Users;
import b.apartment.service.imp.UserServiceImp;
import b.apartment.util.CommonUtil;



@Repository
public class UserDAOImp extends GenericDAOImp<Users, Integer> implements UserDAO {
	private static Logger log = LoggerFactory.getLogger(UserServiceImp.class);

	public UserDAOImp() {
		super(Users.class);
	}
	
	public Users findUserByEmail(String email) {
		log.info("Finding the user by email in the database");
		try {
			return getHibernateTemplate().execute(new HibernateCallback<Users>() {
				public Users doInHibernate(Session session) throws HibernateException {
					Query<Users> query = session.createQuery("FROM Users WHERE email = :email", Users.class);
					query.setParameter("email", email);
					return query.uniqueResult();
				}
			});
		} catch (Exception e) {
			log.error("An error occurred while fetching the user details by email from the database", e);
			return null;
		}
	}
	
	public Users findUser(Users user) {
		log.info("Tim nguoi dung trong co so du lieu");
		try {
			List<Users> userList = (List<Users>) getHibernateTemplate().findByExample(user);
			if(!CommonUtil.isEmpty(userList)) {
				return userList.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("Da xay ra loi khi tim chi tiet nguoi dung tu co so du lieu", e);
			return null;
		}
	}

	
}
