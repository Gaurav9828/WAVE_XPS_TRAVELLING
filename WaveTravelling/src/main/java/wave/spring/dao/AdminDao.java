package wave.spring.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

import wave.spring.Constants.AdminConstantsI;
import wave.spring.Constants.SystemConstants;
import wave.spring.Hibernate.HibernateUtils;
import wave.spring.model.MenuDetails;
import wave.spring.model.EmployeeDetails;
import wave.spring.model.MerchantDetails;
import wave.spring.model.VehicleAttributes;

public class AdminDao implements AdminDaoI {
	@Override
	// added by Gaurav Srivastava
	public EmployeeDetails getEmployeeDetails(String employeeId) {
		List<EmployeeDetails> details = null;
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<EmployeeDetails> criteriaQuery = builder.createQuery(EmployeeDetails.class);
			Root<EmployeeDetails> userDetails = criteriaQuery.from(EmployeeDetails.class);
			criteriaQuery.select(userDetails);
			criteriaQuery.where(builder.equal(userDetails.get("employeeId"), employeeId));
			details = session.createQuery(criteriaQuery).getResultList();
			if (details.isEmpty()) {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return (EmployeeDetails) details;
		} finally {
			try {
				HibernateUtils.getSessionFactory().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			session.close();
		}
		return (EmployeeDetails) details.get(0);
	}

	// added by Gaurav Srivastava
	public void setInvalidPasswordAttempt(int userId, int attemptNumber) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			EmployeeDetails details = (EmployeeDetails) session.get(EmployeeDetails.class, userId);
			details.setInvalidPasswordAttempts(attemptNumber);
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
	}

	// added by Gaurav Srivastava
	public void updateEmployeeDetails(EmployeeDetails employeeDetails) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.update(employeeDetails);
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
	}

	// added by Gaurav Srivastava
	public List<MenuDetails> getEmployeeMenuList(String employeeLevel) {
		List<MenuDetails> employeeMenuList = null;
		int optionalMenuLevel = 0;
		if (employeeLevel.equals("2") || employeeLevel.equals("3")) {
			optionalMenuLevel = 4;
		} else {
			optionalMenuLevel = 0;
		}
		Session session = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<MenuDetails> criteriaQuery = builder.createQuery(MenuDetails.class);
			Root<MenuDetails> menuList = criteriaQuery.from(MenuDetails.class);
			criteriaQuery.select(menuList);
			criteriaQuery.where(builder.and(
					builder.or(builder.equal(menuList.get("menuAdminLevel"), employeeLevel),
							builder.equal(menuList.get("menuAdminLevel"), optionalMenuLevel)),
					builder.equal(menuList.get("menuVisibility"), SystemConstants.ACTIVE)));
			criteriaQuery.orderBy(builder.asc(menuList.get("menuName")));
			employeeMenuList = session.createQuery(criteriaQuery).getResultList();
			session.close();
			if (employeeMenuList.isEmpty()) {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return employeeMenuList;
		} finally {
			try {
				HibernateUtils.getSessionFactory().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			session.close();
		}
		return employeeMenuList;
	}

	public String setAdminPassword(int employeeId, String password) {
		String message = SystemConstants.TRUE;
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			EmployeeDetails details = (EmployeeDetails) session.get(EmployeeDetails.class, employeeId);
			details.setPassword(password);
			transaction.commit();
			session.close();
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

	public String saveVechielDetails(VehicleAttributes vechielAttributes) {
		String message = SystemConstants.TRUE;
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(vechielAttributes);
			transaction.commit();
			session.close();
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

	public VehicleAttributes checkVehicleAlreadyExist(String company, String model) {
		List<VehicleAttributes> details = null;
		Session session = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<VehicleAttributes> criteriaQuery = builder.createQuery(VehicleAttributes.class);
			Root<VehicleAttributes> vd = criteriaQuery.from(VehicleAttributes.class);
			criteriaQuery.select(vd);
			criteriaQuery.where(builder.and(builder.equal(vd.get("vehicleCompany"), company),
					builder.equal(vd.get("vehicleModel"), model)));
			details = session.createQuery(criteriaQuery).getResultList();
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
		return details.get(0);
	}

	public ArrayList<VehicleAttributes> getVehicleList(String type, String value) {
		ArrayList<VehicleAttributes> details = null;
		Session session = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<VehicleAttributes> criteriaQuery = builder.createQuery(VehicleAttributes.class);
			Root<VehicleAttributes> vd = criteriaQuery.from(VehicleAttributes.class);
			criteriaQuery.select(vd);
			criteriaQuery.where(builder.equal(vd.get(type), value));
			details = (ArrayList<VehicleAttributes>) session.createQuery(criteriaQuery).getResultList();
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

	public void updateVehicleDetails(VehicleAttributes vehicleAttributes) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.update(vehicleAttributes);
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

	public ArrayList<EmployeeDetails> getEmployeeDetailsList() {
		ArrayList<EmployeeDetails> details = null;
		Session session = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<EmployeeDetails> criteriaQuery = builder.createQuery(EmployeeDetails.class);
			Root<EmployeeDetails> adminDetails = criteriaQuery.from(EmployeeDetails.class);
			criteriaQuery.select(adminDetails);
			criteriaQuery.where(builder.or(builder.equal(adminDetails.get("adminLevel"), "2"),
					builder.equal(adminDetails.get("adminLevel"), "3")));
			details = (ArrayList<EmployeeDetails>) session.createQuery(criteriaQuery).getResultList();
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
			criteriaQuery.where(builder.notEqual(applicantList.get("status"), AdminConstantsI.ALREADY_REGISTERED));
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

	// added Gaurav Srivastava
	public List<EmployeeDetails> getUnderProcessBlockMerchantList(String stauts, String status2) {
		List<EmployeeDetails> list = new ArrayList<EmployeeDetails>();
		Session session = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<EmployeeDetails> criteriaQuery = builder.createQuery(EmployeeDetails.class);
			Root<EmployeeDetails> applicantList = criteriaQuery.from(EmployeeDetails.class);
			criteriaQuery.select(applicantList);
			criteriaQuery.where(builder.or(builder.equal(applicantList.get("status"), stauts),
					builder.equal(applicantList.get("status"), status2)));
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

	public String updateEmployeeStatus(int userId, String status) {
		String message = SystemConstants.TRUE;
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			EmployeeDetails details = (EmployeeDetails) session.get(EmployeeDetails.class, userId);
			details.setStatus(status);
			transaction.commit();
			session.close();
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

	public ArrayList<MenuDetails> getMenuDetails() {
		ArrayList<MenuDetails> details = null;
		Session session = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<MenuDetails> criteriaQuery = builder.createQuery(MenuDetails.class);
			Root<MenuDetails> menuDetails = criteriaQuery.from(MenuDetails.class);
			criteriaQuery.select(menuDetails);
			details = (ArrayList<MenuDetails>) session.createQuery(criteriaQuery).getResultList();
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

	public String updateMenuDetails(MenuDetails menuDetails) {
		String message = SystemConstants.TRUE;
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.update(menuDetails);
			transaction.commit();
			session.close();
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


}
