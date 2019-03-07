package com.hibernate.main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.config.HibernateConfiguration;
import com.hibernate.persistence.Person;
import com.hibernate.utils.PersonUtils;
/**
 * 
 * @author ramakanth.b
 * Desc: Storing multiple objects in database
 */
public class Main2 {

	public static void main(String[] args) {
		Session session = HibernateConfiguration.getSession();
		Transaction transcation = session.beginTransaction();
		List<Person> personList = PersonUtils.getPersonList();
		personList.stream().forEach(session::save);
		transcation.commit();
		session.close();

	}



}
