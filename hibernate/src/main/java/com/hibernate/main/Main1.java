package com.hibernate.main;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;

import com.hibernate.config.HibernateConfiguration;
import com.hibernate.persistence.Person;
import com.hibernate.utils.PersonUtils;
/**
 * 
 * @author ramakanth.b
 *Desc: Saving Java object in database
 */
public class Main1 {

	private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(Main1.class);
	
	public static void main(String[] args) {
		Session session = HibernateConfiguration.getSession();
		Transaction transaction = session.beginTransaction();
		Person p = PersonUtils.getPerson();
		session.save(p);
		transaction.commit();
		session.close();
		LOG.info("Person Object saved to database");

	}



}
