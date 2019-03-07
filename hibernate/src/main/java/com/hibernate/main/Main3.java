package com.hibernate.main;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.config.HibernateConfiguration;
import com.hibernate.persistence.Person;
import com.hibernate.utils.PersonUtils;

/**
 * 
 * @author ramakanth.b Changing the value even after saving the session but
 *         before committing the transaction
 */
public class Main3 {

	public static void main(String[] args) {
		Session session = HibernateConfiguration.getSession();
		Transaction transcation = session.beginTransaction();
		Person p = PersonUtils.getPerson();
		p.setId("007");
		session.save(p);
		p.setAge(24);
		transcation.commit();
		session.close();

	}

}
