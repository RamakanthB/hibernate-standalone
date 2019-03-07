package com.hibernate.utils;

import java.util.Arrays;
import java.util.List;

import com.hibernate.persistence.Person;

public class PersonUtils {

	private PersonUtils() {
		super();
	}

	public static Person getPerson() {
		return new Person("916", "Ramakanth", 90723746234l, 27);
	}

	public static List<Person> getPersonList() {
		return Arrays.asList(new Person("918", "Silpa", 9072246234l, 26), new Person("920", "Deepak", 90711746234l, 29),
				new Person("866", "Darshan", 90123746234l, 27), new Person("678", "Teju", 91223746234l, 26),
				new Person("990", "Swapna", 90443746234l, 32));
	}
}
