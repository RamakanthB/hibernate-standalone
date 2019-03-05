package com.hibernate.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Person {

	@Id
	private String id;
	private String name;
	private Long mobileno;
	private int age;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getMobileno() {
		return mobileno;
	}

	public void setMobileno(Long mobileno) {
		this.mobileno = mobileno;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", mobileno=" + mobileno + ", age=" + age + "]";
	}

	public Person(String id, String name, Long mobileno, int age) {
		super();
		this.id = id;
		this.name = name;
		this.mobileno = mobileno;
		this.age = age;
	}
}
