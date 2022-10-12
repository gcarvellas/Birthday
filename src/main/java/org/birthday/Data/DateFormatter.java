package org.birthday.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
	//Variables
	private static SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	
	//Accessor Methods
	
	public static int getMonth(Date birthday) {
		return birthday.getMonth() + 1;
	}
	
	public static int getDate(Date birthday) {
		return birthday.getDate();
	}
	
	public static int getYear(Date birthday) {
		return birthday.getYear();
	}
	
	//Mutator Methods
	
	public static Date formatDate(String birthday) throws ParseException {
		if(sdf.isLenient()) { sdf.setLenient(false); }
		try {
			return sdf.parse(birthday);
		}
		catch(ParseException e) {
			throw e;
		}
	}
	
	public static String formatDate(Date birthday) {
		return sdf.format(birthday);
	}
	
}
