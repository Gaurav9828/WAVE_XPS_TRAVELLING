package wave.spring.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import wave.spring.Constants.AdminConstantsI;
import wave.spring.Constants.SystemConstants;
import wave.spring.Hibernate.HibernateUtils;
import wave.spring.model.EmailDetails;
import wave.spring.model.MerchantDetails;
import wave.spring.model.VechileAttributes;

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
	
	public List<VechileAttributes> getVechileList(){
		List<VechileAttributes> vechiles = new ArrayList<VechileAttributes>();
		Session session = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<VechileAttributes> criteriaQuery = builder.createQuery(VechileAttributes.class);
			Root<VechileAttributes> vechileList = criteriaQuery.from(VechileAttributes.class);
			criteriaQuery.select(vechileList);
			criteriaQuery.orderBy(builder.asc(vechileList.get("vechileCompany")));
			vechiles = session.createQuery(criteriaQuery).getResultList();
			session.close();
			if (vechiles.isEmpty()) {
				return vechiles;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return vechiles;
		} finally {
			try {
				HibernateUtils.getSessionFactory().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			session.close();
		}
		return vechiles;
	}


}
