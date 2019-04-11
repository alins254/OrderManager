package DataAccess;

import java.lang.reflect.Field;

public class GenerateQuerry {
	
	public static String generateDeleteAllQ(Object obj) {
		String s = new String();
		s += "DELETE FROM ";
		s += obj.getClass().getSimpleName();
		return s;
	}
	
	public static String generateDeleteByIdQ(Object obj) {
		String s = new String();
		s += "DELETE FROM ";
		s += obj.getClass().getSimpleName();
		s += " WHERE id = ?";
		return s;
	}
	
	public static String generateFindByIdQ(Object obj) {
		String s = new String();
		s += "SELECT * FROM ";
		s += obj.getClass().getSimpleName();
		s += " WHERE id = ?";
		return s;
	}
	
	public static String generateFindAllQ(Object obj) {
		String s = new String();
		s += "SELECT * FROM ";
		s += obj.getClass().getSimpleName();
		return s;
	}
	
	public static String generateInsertQ(Object obj) {
		String s = new String();
		s += "INSERT INTO ";
		s += obj.getClass().getSimpleName();
		s += " (";
		//(id,nume,address,email) 
		for(Field f: obj.getClass().getDeclaredFields()) {
			if(f.getName().equals("nextId"))
				continue;
			s+=f.getName()+",";
		}
		s=s.substring(0, s.length()-1);
		s+=")";
		s += " VALUES (?,?,?,?)";
		return s;
	}
}
