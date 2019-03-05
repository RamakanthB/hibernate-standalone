package com.hibernate.config;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

public class HibernateConfiguration {

	private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(HibernateConfiguration.class);

	private static SessionFactory sessionFactory;

	private static final String CONFIG = "config.properties";

	private HibernateConfiguration() {
		super();
	}

	static {

		try {

			Properties prop = new Properties();

			InputStream inputStream = HibernateConfiguration.class.getClassLoader().getResourceAsStream(CONFIG);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + CONFIG + "' not found in the classpath");
			}

			Configuration configuration = new Configuration().setProperties(prop);

			Reflections reflections = new Reflections("com.hibernate.persistence");
			Set<Class<?>> allClasses = reflections.getTypesAnnotatedWith(javax.persistence.Entity.class);

			allClasses.stream().forEach(configuration::addAnnotatedClass);

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();

			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			LOG.info("Done with Hibernate Setting");
		} catch (Exception e) {
			LOG.error("Exception in setting hibernate properties", e);
		}

	}

	public static Session getSession() throws HibernateException {
		return sessionFactory.openSession();
	}
}
