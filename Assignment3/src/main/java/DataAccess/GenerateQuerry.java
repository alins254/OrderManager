package DataAccess;

import java.lang.reflect.Field;

public class GenerateQuerry {
	
	/** Generate the delete all querry.
	 * @param obj whichs class will be used to select the right table
	 * @return the String representing the querry
	 */
	public static String generateDeleteAllQ(Object obj) {
		String s = new String();
		s += "DELETE FROM ";
		s += obj.getClass().getSimpleName();
		return s;
	}
	
	/** Generate the delete by id querry.
	 * @param obj whichs class will be used to select the right table
	 * @return the String representing the querry
	 */	
	public static String generateDeleteByIdQ(Object obj) {
		String s = new String();
		s += "DELETE FROM ";
		s += obj.getClass().getSimpleName();
		s += " WHERE id = ?";
		return s;
	}
	
	/** Generate the find by id querry.
	 * @param obj whichs class will be used to select the right table
	 * @return the String representing the querry
	 */
	public static String generateFindByIdQ(Object obj) {
		String s = new String();
		s += "SELECT * FROM ";
		s += obj.getClass().getSimpleName();
		s += " WHERE id = ?";
		return s;
	}
	
	/** Generate the find all querry.
	 * @param obj whichs class will be used to select the right table
	 * @return the String representing the querry
	 */
	public static String generateFindAllQ(Object obj) {
		String s = new String();
		s += "SELECT * FROM ";
		s += obj.getClass().getSimpleName();
		return s;
	}
	
	/** Generate the insert querry.
	 * @param obj whichs class will be used to select the right table
	 * @return the String representing the querry
	 */
	public static String generateInsertQ(Object obj) {
		String s = new String();
		s += "INSERT INTO ";
		s += obj.getClass().getSimpleName();
		s += " (";
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
	
	/** Generate the update querry.
	 * @param obj whichs class will be used to select the right table
	 * @return the String representing the querry
	 */
	public static String generateUpdateQ(Object obj) {
		String s = new String();
		int id=-1;
		s += "UPDATE ";
		s += obj.getClass().getSimpleName();
		s += " SET ";
		for(Field f: obj.getClass().getDeclaredFields()) {
			f.setAccessible(true);
			if(f.getName().equals("nextId"))
				continue;
			if(f.getName().equals("id")) {
				try {
					id = f.getInt(obj);
				}catch (Exception e) {
					return new String();
				}
				continue;
			}
			try {
				s+=f.getName()+"="+f.getInt(obj)+", ";
			} catch (Exception e) {
				try {
					s+=f.getName()+"="+"'"+((String)f.get(obj))+"'"+", ";
				} catch (Exception e1) {
					return new String();
				}
			}
		}
		s=s.substring(0, s.length()-2);
		s+=" WHERE id="+id;
		return s;
	}
}
