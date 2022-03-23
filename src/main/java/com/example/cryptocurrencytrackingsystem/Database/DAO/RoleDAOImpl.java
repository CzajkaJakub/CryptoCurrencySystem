package com.example.cryptocurrencytrackingsystem.Database.DAO;

import com.example.cryptocurrencytrackingsystem.Entity.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component("roleDaoImpl")
public class RoleDAOImpl implements RoleDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Role findRoleByName(String theRoleName) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Role> theQuery = currentSession.createQuery("from Role where name=:roleName", Role.class);
		theQuery.setParameter("roleName", theRoleName);

		List<Role> list = theQuery.getResultList();
		if (list == null || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
}
