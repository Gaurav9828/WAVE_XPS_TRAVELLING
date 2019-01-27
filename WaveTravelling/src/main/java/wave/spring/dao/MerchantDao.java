package wave.spring.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import wave.spring.Constants.AdminConstantsI;
import wave.spring.Constants.AdminMessageConstants;
import wave.spring.Constants.MailMessagesConstants;
import wave.spring.Constants.SystemConstants;
import wave.spring.Hibernate.HibernateUtils;
import wave.spring.model.EmailDetails;
import wave.spring.model.EmployeeDetails;
import wave.spring.model.MerchantDetails;
import wave.spring.model.VehicleAttributes;
import wave.spring.model.VehicleDetails;

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
			message = AdminMessageConstants.REGISTRAION_REQUEST_SUCCESSFUL;
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

	public String rejectApplication(int merchantId) {
		String message = "";
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.getTransaction();
			transaction.begin();

			// Delete a transient object
			MerchantDetails merchantDetails = new MerchantDetails();
			merchantDetails.setMarchantId(merchantId);
			session.delete(merchantDetails);
			transaction.commit();
			message = AdminMessageConstants.REJECTION_SUCCESSFUL;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			message = SystemConstants.ERROR;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		HibernateUtils.shutdown();
		return message;
	}

	public String insertMerchantAdmin(EmployeeDetails employeeDetails) {
		String message = "";
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(employeeDetails);
			transaction.commit();
			session.close();
			message = SystemConstants.ACTIVE;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			message = SystemConstants.INACTIVE;
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

	public void updateMerchant(MerchantDetails merchantDetails) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.update(merchantDetails);
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

	public List<EmailDetails> getPendingEmailDetails() {
		List<EmailDetails> list = new ArrayList<EmailDetails>();
		Session session = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<EmailDetails> criteriaQuery = builder.createQuery(EmailDetails.class);
			Root<EmailDetails> applicantList = criteriaQuery.from(EmailDetails.class);
			criteriaQuery.select(applicantList);
			criteriaQuery
					.where(builder.equal(applicantList.get("emailStatus"), AdminConstantsI.MAIL_WAITING));
			criteriaQuery.orderBy(builder.asc(applicantList.get("dateTime")));
			list = session.createQuery(criteriaQuery).getResultList();
			session.close();
			if (list.isEmpty()) {
				return list;
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

	// added Gaurav Srivastava
	public List<MerchantDetails> getApplicantMerchantList(String stauts) {
		List<MerchantDetails> list = new ArrayList<MerchantDetails>();
		Session session = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<MerchantDetails> criteriaQuery = builder.createQuery(MerchantDetails.class);
			Root<MerchantDetails> applicantList = criteriaQuery.from(MerchantDetails.class);
			criteriaQuery.select(applicantList);
			criteriaQuery.where(builder.equal(applicantList.get("status"), stauts));
			criteriaQuery.orderBy(builder.asc(applicantList.get("submissionDate")));
			list = session.createQuery(criteriaQuery).getResultList();
			session.close();
			if (list.isEmpty()) {
				return list;
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
	
	public MerchantDetails getMerchantDetails(String marchantCode) {
		List<MerchantDetails> details = null;
		Session session = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<MerchantDetails> criteriaQuery = builder.createQuery(MerchantDetails.class);
			Root<MerchantDetails> merchantDetails = criteriaQuery.from(MerchantDetails.class);
			criteriaQuery.select(merchantDetails);
			criteriaQuery.where(builder.equal(merchantDetails.get("marchantCode"), marchantCode));
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
	
	public VehicleDetails vehicleMarchantAlreadyExist(String vehicleNumber) {
		List<VehicleDetails> details = null;
		Session session = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<VehicleDetails> criteriaQuery = builder.createQuery(VehicleDetails.class);
			Root<VehicleDetails> vNumber = criteriaQuery.from(VehicleDetails.class);
			criteriaQuery.select(vNumber);
			criteriaQuery.where(builder.equal(vNumber.get("vehicleNumber"), vehicleNumber));
			details = session.createQuery(criteriaQuery).getResultList();
			if (details.isEmpty()) {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return (VehicleDetails) details;
		} finally {
			try {
				HibernateUtils.getSessionFactory().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			session.close();
		}
		VehicleDetails loggerDetails = (VehicleDetails) details.get(0);
		return loggerDetails;
	}
	
	public String saveMarchantVehicle(VehicleDetails vehicleDetails) {
		String message = "";
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(vehicleDetails);
			transaction.commit();
			session.close();
			message = SystemConstants.TRUE;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			message = SystemConstants.FALSE;
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

	
	public String updateMarchantVehicle(VehicleDetails vehicleDetails) {
		String message = "";
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.update(vehicleDetails);
			transaction.commit();
			session.close();
			message = SystemConstants.TRUE;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			message = SystemConstants.FALSE;
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
	
	public String deleteMarchantVehicle(VehicleDetails vehicleDetails) {
		String message = "";
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.delete(vehicleDetails);
			transaction.commit();
			session.close();
			message = SystemConstants.TRUE;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			message = SystemConstants.FALSE;
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
	
	public ArrayList<VehicleDetails> getListOfVehicleFromVehicleDetails(String status){
		ArrayList<VehicleDetails> details = null;
		Session session = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<VehicleDetails> criteriaQuery = builder.createQuery(VehicleDetails.class);
			Root<VehicleDetails> vd = criteriaQuery.from(VehicleDetails.class);
			criteriaQuery.select(vd);
			criteriaQuery.where(builder.equal(vd.get("status"), status));
			details = (ArrayList<VehicleDetails>) session.createQuery(criteriaQuery).getResultList();
			if (details.isEmpty()) {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				HibernateUtils.getSessionFactory().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			session.close();
		}
		return details;
	}

	public ArrayList<VehicleDetails> getMerchantVehicleDetails(String ownerId){
		ArrayList<VehicleDetails> details = null;
		Session session = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<VehicleDetails> criteriaQuery = builder.createQuery(VehicleDetails.class);
			Root<VehicleDetails> vd = criteriaQuery.from(VehicleDetails.class);
			criteriaQuery.select(vd);
			criteriaQuery.where(builder.equal(vd.get("ownerId"), ownerId));
			details = (ArrayList<VehicleDetails>) session.createQuery(criteriaQuery).getResultList();
			if (details.isEmpty()) {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				HibernateUtils.getSessionFactory().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			session.close();
		}
		return details;
	}


}
