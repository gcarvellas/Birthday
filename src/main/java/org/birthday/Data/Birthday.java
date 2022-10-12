package org.birthday.Data;

import java.text.ParseException;
import java.util.Date;
import java.io.Serializable;

public class Birthday implements Comparable<Birthday>, Serializable{
	//Variables
	private String name;
	private Date birthday;
	private final static long serialVersionUID = 1;
	
	//Constructor
	public Birthday(String name, String birthdate) throws ParseException {
		setName(name);
		setBirthday(birthdate);
	}
	
	//Accessor Methods
	
	public String getName() {
		return name;
	}
	
	public String getBirthday(){
		return DateFormatter.formatDate(birthday);
	}
	
	public int getBirthMonth() {
		return DateFormatter.getMonth(birthday);
	}
	
	public int getBirthdate() {
		return DateFormatter.getDate(birthday);
	}
	
	public int getBirthYear() {
		return DateFormatter.getYear(birthday);
	}
	
	@Override
	public int compareTo(Birthday birthday) {
		return name.compareTo(birthday.getName());
	}
	
	//Mutator Methods
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setBirthday(String birthdate) throws ParseException{
			this.birthday = DateFormatter.formatDate(birthdate);
	}
	
}
