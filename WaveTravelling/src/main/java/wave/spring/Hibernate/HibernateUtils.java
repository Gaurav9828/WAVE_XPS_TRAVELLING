package wave.spring.Hibernate;

import java.util.HashMap;
import java.util.Map;


import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import wave.spring.Constants.SystemConstants;
import wave.spring.model.Employee1MenuList;
import wave.spring.model.EmployeeDetails;


public class HibernateUtils {
  private static StandardServiceRegistry registry;
  private static SessionFactory sessionFactory;

  public static SessionFactory getSessionFactory() {
	  
    if (sessionFactory == null || sessionFactory.isClosed()) {
      try {
          Map<String, String> settings = new HashMap<String, String>();
         
          //connection of data base with project environment
          settings.put(Environment.DRIVER, SystemConstants.DRIVER_CLASS_NAME);
          settings.put(Environment.URL, SystemConstants.DATABASE_URL);
          settings.put(Environment.USER, SystemConstants.USER_NAME);
          settings.put(Environment.PASS, SystemConstants.PASSWORD);
          settings.put(Environment.DIALECT, SystemConstants.DILECT);
          settings.put(Environment.HBM2DDL_AUTO, SystemConstants.HBM2DDL_AUTO);
          settings.put(Environment.C3P0_TIMEOUT, SystemConstants.C3P0_TIMEOUT);
       
          StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
          registryBuilder.applySettings(settings);
          registry = registryBuilder.build();
          
          //addition of bean classes to Hibernate for data base queries 
          MetadataSources sources = new MetadataSources(registry).addAnnotatedClass(EmployeeDetails.class).addAnnotatedClass(Employee1MenuList.class);
          Metadata metadata = sources.getMetadataBuilder().build();
          sessionFactory = metadata.getSessionFactoryBuilder().build();
      } catch (Exception e) {
    	  e.printStackTrace();
    	  if (registry != null) {
    		  StandardServiceRegistryBuilder.destroy(registry);
    	  }
      }
    }
    return sessionFactory;
  }

  public static void shutdown() {
    if (registry != null) {
      StandardServiceRegistryBuilder.destroy(registry);
    }
  }
}