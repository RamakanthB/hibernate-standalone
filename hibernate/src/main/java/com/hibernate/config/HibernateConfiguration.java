package com.hibernate.config;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.reflections.Reflections;

import static com.hibernate.utils.Constants.DIALECT;
import static com.hibernate.utils.Constants.HIBERNATEPASSWORD;
import static com.hibernate.utils.Constants.HIBERNATEURL;
import static com.hibernate.utils.Constants.HIBERNATEUSERNAME;

public class HibernateConfiguration {

	private static final SessionFactory sessionFactory;

	private static final String CONFIG = "config.properties";

	static {

		try {

			Properties prop = new Properties();

			InputStream inputStream = HibernateConfiguration.class.getClassLoader().getResourceAsStream(CONFIG);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + CONFIG + "' not found in the classpath");
			}

			prop.setProperty(HIBERNATEURL, prop.getProperty(HIBERNATEURL));
			prop.setProperty(HIBERNATEUSERNAME, prop.getProperty(HIBERNATEUSERNAME));
			prop.setProperty(HIBERNATEPASSWORD, prop.getProperty(HIBERNATEPASSWORD));
			prop.setProperty(DIALECT, prop.getProperty(DIALECT));

			Configuration configuration = new Configuration().configure();

			Reflections reflections = new Reflections("com.hibernate.persistence");

			Set<Class<? extends Object>> allClasses = reflections.getSubTypesOf(Object.class);

			allClasses.stream().forEach(configuration::addAnnotatedClass);

			sessionFactory = configuration.buildSessionFactory();
		} catch (Exception e) {
			System.out.println("Exception in setting hibernate properties");
		}

	}

	public static Session getSession() throws HibernateException {
		return sessionFactory.openSession();
	}
}
