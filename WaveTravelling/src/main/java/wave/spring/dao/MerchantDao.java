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
import wave.spring.model.Employee1MenuList;
import wave.spring.model.EmployeeDetails;
import wave.spring.model.MerchantDetails;

public class MerchantDao implements MerchantDaoI {
	public MerchantDetails merchantAlreadyExist(String mobileNumber) {
		List<MerchantDetails> details = null;
		Session session = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<MerchantDetails> criteriaQuery = builder.createQuery(MerchantDetails.class);
			Root<MerchantDetails> merchantDetails = criteriaQuery.from(MerchantDetails.class);
			criteriaQuery.select(merchantDetails);
			criteriaQuery.where(builder.equal(merchantDetails.get("mobileNumber"), mobileNumber));
			details = session.createQuery(criteriaQuery).getResultList();
			if (details.isEmpty()) {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return (MerchantDetails) details;
		} finally {
			try {
				HibernateUtils.getSessionFactory().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			session.close();
		}
		MerchantDetails loggerDetails = (MerchantDetails) details.get(0);
		return loggerDetails;
	}

	public String applyForWatingMerchantship(MerchantDetails merchantDetails) {
		String message = "";
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(merchantDetails);
			transaction.commit();
			session.close();
			message = AdminConstantsI.REGISTRAION_REQUEST_SUCCESSFUL;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			message = SystemConstants.ERROR;
		} finally {
			try {
				HibernateUtils.getSessionFactory().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			session.close();
		}
		return message;
	}

	// added Gaurav Srivastava
	public List<MerchantDetails> getAcceptedMerchantList() {
		List<MerchantDetails> list = new ArrayList<MerchantDetails>();
		Session session = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<MerchantDetails> criteriaQuery = builder.createQuery(MerchantDetails.class);
			Root<MerchantDetails> applicantList = criteriaQuery.from(MerchantDetails.class);
			criteriaQuery.select(applicantList);
			criteriaQuery.where(builder.equal(applicantList.get("status"), AdminConstantsI.ALREADY_REGISTERED));
			criteriaQuery.orderBy(builder.desc(applicantList.get("submissionDate")));
			list = session.createQuery(criteriaQuery).getResultList();
			session.close();
			if (list.isEmpty()) {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return list;
		} finally {
			try {
				HibernateUtils.getSessionFactory().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			session.close();
		}
		return list;
	}
}
