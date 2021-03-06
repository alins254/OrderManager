package DataAccess;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.LinkedList;

public class DataToList {
	
	/**
	 * Converts data from resultSet to a list of objects.
	 * Return the list of objects generated by the data from the resultset sent as parameter.
	 * The obj argument will be the type of the objects in the list.
	 * 
	 * The method will return a null pointer if rs is empty.
	 * 
	 * @param obj an Object giving the type of the objects in the list
	 * @param rs a ResultSet which contains the data to be converted in a list
	 * @return a LinkedList with the object's type, same as the obj parameter
	 */
	public static LinkedList<Object> viewAll(Object obj,ResultSet rs){
		LinkedList<Object> lista = new LinkedList<Object>();
		try {
			while(rs.next()) {
				@SuppressWarnings("rawtypes")
				Constructor c = obj.getClass().getConstructor();
				Object obj2 = c.newInstance();
				for(Field f:obj2.getClass().getDeclaredFields()) {
					f.setAccessible(true);
					if(!f.getName().equals("nextId")) {
						try {
							f.set(obj2, Integer.parseInt(rs.getString(f.getName())));
						}catch (Exception e) {
							f.set(obj2, rs.getString(f.getName()));
						}
					}
				}
				lista.add(obj2);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	/** Converts data from resultSet to an object.
	 * Return the object generated by the data from the resultset sent as parameter.
	 * 
	 * The method will return a null pointer if rs is empty.
	 * 
	 * @param obj an Object giving the type of the objects in the list
	 * @param rs a ResultSet which contains the data to be converted
	 * @return an Object with the object's type, same as the obj parameter
	 */
	public static Object rsToObj(Object obj,ResultSet rs){
		try {
			if(rs.next()) {
				@SuppressWarnings("rawtypes")
				Constructor c = obj.getClass().getConstructor();
				Object obj2 = c.newInstance();
				for(Field f:obj2.getClass().getDeclaredFields()) {
					f.setAccessible(true);
					if(!f.getName().equals("nextId")) {
						try {
							f.set(obj2, Integer.parseInt(rs.getString(f.getName())));
						}catch (Exception e) {
							f.set(obj2, rs.getString(f.getName()));
						}
					}
				}
				return obj2;
			}
			else
				return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/** Updates the field "nextId" from the class with the same type
	 * as the Objects in the list sent as parameter.
	 * @param lista - list of the objects found into the table
	 */
	public static void updateIds(LinkedList<Object> lista) {
		if(lista.isEmpty())
			return;
		int maxId=-1;
		Object obj=null;
		for(Object o:lista) {
			for(Field f:o.getClass().getDeclaredFields()) {
				f.setAccessible(true);
				if(f.getName().equals("id"))
					try {
						if(maxId<f.getInt(o))
							maxId = f.getInt(o);
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
			obj = o;
		}
		maxId++;
		for(Field f: obj.getClass().getDeclaredFields()) {
			f.setAccessible(true);
			if(f.getName().equals("nextId"))
				try {
					System.out.println(f.getName()+" "+maxId);
					f.set(obj, maxId);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}	
	}
	
}
