package wave.spring.Hibernate;

import java.util.HashMap;
import java.util.Map;


import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import wave.spring.model.EmployeeDetails;


public class HibernateUtils {
  private static StandardServiceRegistry registry;
  private static SessionFactory sessionFactory;

  public static SessionFactory getSessionFactory() {
	  
    if (sessionFactory == null || sessionFactory.isClosed()) {
      try {
          Map<String, String> settings = new HashMap<String, String>();
         
          //connection of data base with project environment
          settings.put(Environment.DRIVER, "oracle.jdbc.driver.OracleDriver");
          settings.put(Environment.URL, "jdbc:oracle:thin:@localhost:1521:XE");
          settings.put(Environment.USER, "MYDATABASE");
          settings.put(Environment.PASS, "GAV9828GaV");
          settings.put(Environment.DIALECT, "org.hibernate.dialect.Oracle10gDialect");
          settings.put(Environment.HBM2DDL_AUTO, "update");
          settings.put(Environment.C3P0_TIMEOUT, "1000");
       
          StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
          registryBuilder.applySettings(settings);
          registry = registryBuilder.build();
          
          //addition of bean classes to Hibernate for data base queries 
          MetadataSources sources = new MetadataSources(registry).addAnnotatedClass(EmployeeDetails.class);
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