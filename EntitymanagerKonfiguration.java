


  
  hibernate.properties:

jdbc.driverClassName=oracle.jdbc.driver.OracleDriver
jdbc.url=jdbc:oracle:thin:@host:1547/sid
jdbc.username=user
jdbc.password=pass

hibernate.max_fetch_depth=3
hibernate.jdbc.fetch_size=50
hibernate.jdbc.batch_size=10
hibernate.show_sql=true
hibernate.dialect=org.hibernate.dialect.Oracle10gDialect
		
packagestoscan=paket


1.jdbc.driverClassName=oracle.jdbc.driver.OracleDriver
1.datasource.jdbc-url=jdbc:oracle:thin:@host:1547/sid
1.datasource.username=u
1.datasource.password=o


2.jdbc.driverClassName=oracle.jdbc.driver.OracleDriver
2.jdbc-url=jdbc:oracle:thin:@host:1547/sid
2.username=user
2.password=pass

#2.datasource.jndi=java:/jdbc//2
2.datasource.jndi=${2.datasource.jndi}

===========================================================================

BeanKonfiguration Klasse:


package de.itzbund.stplf.bsdd.beteiligtenverwaltung.dao.config;

import java.util.Optional;
import java.util.Properties;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@PropertySource(value = { "classpath:hibernate.properties" })
public class BeanKonfiguration
{
    private static final Logger log = LoggerFactory.getLogger(BeanKonfiguration.class);
    private static final String PROPERTY_NAME_HIBERNATE_DIALECT         = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_MAX_FETCH_DEPTH = "hibernate.max_fetch_depth";
    private static final String PROPERTY_NAME_HIBERNATE_JDBC_FETCH_SIZE = "hibernate.jdbc.fetch_size";
    private static final String PROPERTY_NAME_HIBERNATE_JDBC_BATCH_SIZE = "hibernate.jdbc.batch_size";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL        = "hibernate.show_sql";

    @Autowired
    private Environment         env;

    @Bean(name = "EntityManager")
    public EntityManager getEntityManager()
    {
        final EntityManagerFactory emf;
        if (Optional.ofNullable(entityManagerFactoryBean().getObject()).isPresent())
        {
            emf = entityManagerFactoryBean().getObject();

            if (Optional.ofNullable(emf).isPresent())
            {
                return emf.createEntityManager();
            }
        }
        return null;
    }


    @Bean(name = "entityManagerFactory-sch")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
         final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
         entityManagerFactoryBean.setJpaVendorAdapter(vendorAdaptor());
         entityManagerFactoryBean.setDataSource(dataSource());
         entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
         entityManagerFactoryBean.setPackagesToScan(this.env.getProperty("packagestoscan"));
         entityManagerFactoryBean.setJpaProperties(Optional.ofNullable(jpaHibernateProperties()).orElse(new Properties()));

         return entityManagerFactoryBean;
     }


     @Bean(name = "datasource-sch")
     public DataSource dataSource()
     {
         final BasicDataSource dataSource = new BasicDataSource();

         dataSource.setDriverClassName(this.env.getProperty("jdbc.driverClassName"));
         dataSource.setUrl(this.env.getProperty("jdbc.url"));
         dataSource.setUsername(this.env.getProperty("jdbc.username"));
         dataSource.setPassword(this.env.getProperty("jdbc.password"));

         return dataSource;
     }


     @Bean(name = "EntityManager1")
     public EntityManager getEntityManager1()
     {
         final EntityManagerFactory emf;
         if (Optional.ofNullable(entityManagerFactoryBean1().getObject()).isPresent())
         {
             emf = entityManagerFactoryBean1().getObject();

             if (Optional.ofNullable(emf).isPresent())
             {
                 return emf.createEntityManager();
             }
         }
         return null;
     }


     @Bean(name = "entityManagerFactory1")
     public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean1()
     {
         final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
         entityManagerFactoryBean.setJpaVendorAdapter(vendorAdaptor());
         entityManagerFactoryBean.setDataSource(dataSource1());
         entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
         entityManagerFactoryBean.setPackagesToScan(this.env.getProperty("packagestoscan"));
         entityManagerFactoryBean.setJpaProperties(Optional.ofNullable(jpaHibernateProperties()).orElse(new Properties()));

         return entityManagerFactoryBean;
     }


     @Bean(name = "datasource1")
     public DataSource dataSource1()
     {
         final BasicDataSource dataSource = new BasicDataSource();

         dataSource.setDriverClassName(this.env.getProperty("jdbc.driverClassName"));
         dataSource.setUrl(this.env.getProperty("1.datasource.jdbc-url"));
         dataSource.setUsername(this.env.getProperty("1.datasource.username"));
         dataSource.setPassword(this.env.getProperty("1.datasource.password"));

         return dataSource;
     }


     @Bean(name = "2EntityManager")
     public EntityManager get2EntityManager()
     {
         final EntityManagerFactory emf;
         if (Optional.ofNullable(entityManagerFactoryBean2().getObject()).isPresent())
         {
             emf = entityManagerFactoryBeanBetv().getObject();

             if (Optional.ofNullable(emf).isPresent())
             {
                 return emf.createEntityManager();
             }
         }
         return null;
     }


     @Bean(name = "entityManagerFactory2")
     public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean2()
     {
         final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
         entityManagerFactoryBean.setJpaVendorAdapter(vendorAdaptor());
         try
         {
             entityManagerFactoryBean.setDataSource(dataSource2());
         } catch (final NamingException e)
         {
             // TODO Automatisch generierter Erfassungsblock
             e.printStackTrace();
         }
         entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
         entityManagerFactoryBean.setPackagesToScan(this.env.getProperty("packagestoscan"));
         entityManagerFactoryBean.setJpaProperties(Optional.ofNullable(jpaHibernateProperties()).orElse(new Properties()));

         return entityManagerFactoryBean;
     }


     @Bean(name = "datasource2")
     public DataSource dataSource2()
     {
         final BasicDataSource dataSource = new BasicDataSource();

         dataSource.setDriverClassName(this.env.getProperty("jdbc.driverClassName"));
         dataSource.setUrl(this.env.getProperty("2.datasource.jdbc-url"));
         dataSource.setUsername(this.env.getProperty("2.datasource.username"));
         dataSource.setPassword(this.env.getProperty("2.datasource.password"));

         return dataSource;
     }


//mit jndi:
     @Bean(name = "datasource-2")
     public DataSource dataSource2() throws NamingException
     {
         return (DataSource) new JndiTemplate().lookup(this.env.getProperty("bsdd.2.datasource.jndi"));
     }

     private Properties jpaHibernateProperties() {

         final Properties properties = new Properties();

         properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, this.env.getProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
         properties.put(PROPERTY_NAME_HIBERNATE_MAX_FETCH_DEPTH,
                 this.env.getProperty(PROPERTY_NAME_HIBERNATE_MAX_FETCH_DEPTH));
         properties.put(PROPERTY_NAME_HIBERNATE_JDBC_FETCH_SIZE,
                 this.env.getProperty(PROPERTY_NAME_HIBERNATE_JDBC_FETCH_SIZE));
         properties.put(PROPERTY_NAME_HIBERNATE_JDBC_BATCH_SIZE,
                 this.env.getProperty(PROPERTY_NAME_HIBERNATE_JDBC_BATCH_SIZE));
         properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, this.env.getProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));

         return properties;       
     }
	 
	     private HibernateJpaVendorAdapter vendorAdaptor() {
         final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
         vendorAdapter.setShowSql(true);
         return vendorAdapter;
    }


}
