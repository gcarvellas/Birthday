package org.birthday.birthday.Data;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.NavigableSet;
import java.util.TreeSet;

public class BirthdayList implements Serializable{
	//Variables
	private TreeSet<Birthday> birthdayList;
	private final static long serialVersionUID = 1;
	private String today;
	
	//Constructor
	public BirthdayList() {
		birthdayList = new TreeSet<Birthday>();
	}
	
	//Accessor
	public String getBirthdayByName(String name) {
		Birthday result = birthdayList.parallelStream()
				.filter(Birthday -> name.equals(Birthday.getName()))
				.findAny()
				.orElse(null);
		if (result == null) { return null; }
		return result.getBirthday();
	}
	
	public ArrayList<String> getUsersByBirthday(String birthday){
		
		ArrayList<String> result = new ArrayList<String>();
		
		try {
		birthdayList.parallelStream()
		.filter(Birthday -> Birthday.getBirthday().contentEquals(birthday))
		.forEach(Birthday -> result.add(Birthday.getName()));
		} catch (NullPointerException e) {
			return null;
		}
		
		return result;	
	}
	
	public int size() {
		return birthdayList.size();
	}
	
	public NavigableSet<Birthday> getSet() {
		return birthdayList.descendingSet();
	}
	
	public boolean userExistsByName(String name) {
		for(Birthday user : birthdayList) {
			if (user.getName().equalsIgnoreCase(name)){
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Birthday> getTodaysBirthdays(){
		ArrayList<Birthday> result = new ArrayList<Birthday>();
		today = DateFormatter.formatDate(Calendar.getInstance().getTime());
		for (Birthday bday : birthdayList) {
			if (bday.getBirthday().equalsIgnoreCase(today))
				result.add(bday);
		}
		return result;
	}
	
	//Mutator
	
	public void add(Birthday birthday) {
		birthdayList.add(birthday);
	}
	
	public void add(String name, String date) throws ParseException{
		try {
			birthdayList.add(new Birthday(name, date));
		} catch (ParseException e) {
			throw e;
		}
	}
	
	public void remove(Birthday birthday){
		birthdayList.remove(birthday);
	}

}
