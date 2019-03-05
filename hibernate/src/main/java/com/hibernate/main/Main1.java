package com.hibernate.main;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;

import com.hibernate.config.HibernateConfiguration;
import com.hibernate.persistence.Person;

public class Main1 {

	private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(Main1.class);
	
	public static void main(String[] args) {
		Session session = HibernateConfiguration.getSession();
		Transaction transaction = session.beginTransaction();
		Person p = getPerson();
		session.save(p);
		transaction.commit();
		LOG.info("Person Object saved to database");

	}

	public static Person getPerson() {
		return new Person("916", "Ramakanth", 90723746234l, 27);
	}

}
