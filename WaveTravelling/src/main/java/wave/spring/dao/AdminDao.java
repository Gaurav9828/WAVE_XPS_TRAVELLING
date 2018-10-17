package wave.spring.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import wave.spring.Hibernate.HibernateUtils;

import wave.spring.model.EmployeeDetails;
public class AdminDao implements AdminDaoI {
	@Override
	//added by Gaurav Srivastava
	public EmployeeDetails getEmployeeDetails(String employeeId) {
		List<EmployeeDetails> details = null;
		Session session = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
		    CriteriaBuilder builder = session.getCriteriaBuilder();
		    CriteriaQuery<EmployeeDetails> criteriaQuery = builder.createQuery(EmployeeDetails.class);
		    Root<EmployeeDetails> userDetails = criteriaQuery.from(EmployeeDetails.class);
		    criteriaQuery.select(userDetails);
			criteriaQuery.where(builder.equal(userDetails.get("employeeId"),employeeId)); 
			details = session.createQuery(criteriaQuery).getResultList();
		    if (details.isEmpty()) {
				return null;
		    }
		} catch (Exception e) {
			e.printStackTrace();
			return (EmployeeDetails) details;
		}	  
		finally {		
			try {
				HibernateUtils.getSessionFactory().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			session.close();
		}
		EmployeeDetails loggerDetails = (EmployeeDetails) details.get(0);
		return loggerDetails;
	}
	
	//added by Gaurav Srivastava
	public void setInvalidPasswordAttempt(int userId, int attemptNumber) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
		    transaction = session.beginTransaction();
		    EmployeeDetails details = (EmployeeDetails) session.get(EmployeeDetails.class,userId);
			details.setInvalidPasswordAttempts(attemptNumber);	
		    transaction.commit();
		    session.close();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		finally {		
			try {
				HibernateUtils.getSessionFactory().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			session.close();
		}
	}

	//added by Gaurav Srivastava
	  public void setAdminUserLoggedIn(EmployeeDetails employeeDetails) {
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
			}
			finally {		
				try {
					HibernateUtils.getSessionFactory().close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				session.close();
			}
	  }

  }
