package org.birthday.birthday;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.Base64;

import org.birthday.birthday.Data.BirthdayList;

public class ConfigManager {
	//Variables
	private static ByteArrayOutputStream baos;
	private static ObjectOutputStream oos;
	
	private static byte[] data;
	private static ObjectInputStream ois;
	
	//Accessors
	public static String serialize(BirthdayList list) throws IOException{
		baos = new ByteArrayOutputStream();
        oos = new ObjectOutputStream(baos);
        oos.writeObject(list);
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray()); 
	}
	
	public static BirthdayList deserialize(String dataString) throws IOException, ClassNotFoundException{
		data = Base64.getDecoder().decode(dataString);
		ois = new ObjectInputStream(new ByteArrayInputStream(data));
	        Object o  = ois.readObject();
	        ois.close();
	        return (BirthdayList) o;
	}
	}
