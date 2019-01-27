package wave.spring.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import wave.spring.Hibernate.HibernateUtils;
import wave.spring.model.EmailDetails;
import wave.spring.model.VehicleAttributes;

public class SystemDao implements SystemDaoI{
	public void saveEmailDetails(EmailDetails emailDetails) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(emailDetails);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			try {
				HibernateUtils.getSessionFactory().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			session.close();
		}
		return;
	}
	
	public List<VehicleAttributes> getVehicleList(){
		List<VehicleAttributes> vehicles = new ArrayList<VehicleAttributes>();
		Session session = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<VehicleAttributes> criteriaQuery = builder.createQuery(VehicleAttributes.class);
			Root<VehicleAttributes> vehicleList = criteriaQuery.from(VehicleAttributes.class);
			criteriaQuery.select(vehicleList);
			criteriaQuery.orderBy(builder.asc(vehicleList.get("vehicleCompany")));
			vehicles = session.createQuery(criteriaQuery).getResultList();
			session.close();
			if (vehicles.isEmpty()) {
				return vehicles;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return vehicles;
		} finally {
			try {
				HibernateUtils.getSessionFactory().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			session.close();
		}
		return vehicles;
	}


}
