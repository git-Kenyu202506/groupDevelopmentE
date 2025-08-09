package com.example.demo.entity;

import java.time.LocalDate;

//MyBatis では、エンティティクラスを POJO（Plain Old Java Object）として作成します。
public class Staff {
	
	private int id;//データベースのレコードを一意に識別する
	private String name;
	private int age;
	private String password;
	private LocalDate startDate;
	private LocalDate endDate;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Staff() {}
	
	public Staff(String name, int age, String password, LocalDate startDate, LocalDate endDate) {
		this.name = name;
		this.age = age;
		this.password = password;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	

    
}
