package DataAccess;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO {
	protected static final Logger LOGGER = Logger.getLogger(DAO.class.getName());
	
	/**
	 * Update a row in the table equal to the object type.
	 * The obj's type must exist as a table in the database - there has to
	 * be a perfect match between the Class Name and the Tabel Name (not case sensitive).
	 * @param obj will be updated in the table with the name equal with it's class name
	 * @return 0 if the delete was succesful, otherwise -1 if the insert failed,
	 * -2 if the Object type isn't right, -3 if the line don't exist in the table
	 */
	public static int update(Object obj) {
		int id = -1;
		for(Field f:obj.getClass().getDeclaredFields()) {
			if(f.getName().equals("id"))
				try {
					f.setAccessible(true);
					id = f.getInt(obj);
					break;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		if(id==-1)
			return -2;
		if(deleteById(obj, id)==-1)
			return -3;
		return insert(obj);
	}
	
	/**
	 * Insert a row in the table equal to the object type.
	 * The obj's type must exist as a table in the database - there has to
	 * be a perfect match between the Class Name and the Tabel Name (not case sensitive).
	 * @param obj will be introduced in the table with the name equal with it's class name
	 * @return 0 if the delete was succesful, otherwise -1
	 */
	public static int insert(Object obj) {
		Connection db = ConnectionFactory.getConnection();
		PreparedStatement insert = null;
		try {
			insert = db.prepareStatement(GenerateQuerry.generateInsertQ(obj));
			int k=1;
			for(Field f: obj.getClass().getDeclaredFields()) {
				if(f.getName().equals("nextId"))
					continue;
				f.setAccessible(true);
				try {
					insert.setInt(k, f.getInt(obj));
				}catch(Exception e) {
					insert.setString(k,(String)f.get(obj));
				}
				k++;
			}
			insert.executeUpdate();
		}
		catch(Exception e) {
			LOGGER.log(Level.WARNING, "DAO:insert "+e.getMessage());
			return -1;
		} finally {
			ConnectionFactory.close(insert);
			ConnectionFactory.close(db);
		}
		return 0;
	}
	
	/**
	 * Deletes all the data the table equal to the object type.
	 * The obj's type must exist as a table in the database - there has to
	 * be a perfect match between the Class Name and the Tabel Name (not case sensitive).
	 * @param obj is used to select the table form which the data will be deleted
	 * @return 0 if the delete was succesful, otherwise -1
	 */
	public static int deleteAll(Object obj) {
		Connection db = ConnectionFactory.getConnection();
		PreparedStatement statem = null;
		int rs=-1;
		try {
			statem = db.prepareStatement(GenerateQuerry.generateDeleteAllQ(obj));
			rs = statem.executeUpdate();
		}
		catch(Exception e) {
			LOGGER.log(Level.WARNING, "DAO:deleteAll "+e.getMessage());
		} finally {
			ConnectionFactory.close(statem);
			ConnectionFactory.close(db);
		}
		if(rs==0) return -1;
		return 0;
	}
	
	/**
	 * Deletes one row from the table equal to the object type, which has the given id.
	 * The obj's type must exist as a table in the database - there has to
	 * be a perfect match between the Class Name and the Tabel Name (not case sensitive).
	 * @param obj is used to select the table form which the data will be deleted
	 * @param id the value used to find the line which is going to be deleted from the table 
	 * @return 0 if the delete was succesful, otherwise -1
	 */
	public static int deleteById(Object obj, int id) {
		Connection db = ConnectionFactory.getConnection();
		PreparedStatement statem = null;
		int rs=-1;
		try {
			statem = db.prepareStatement(GenerateQuerry.generateDeleteByIdQ(obj));
			statem.setInt(1, id);
			rs = statem.executeUpdate();
		}
		catch(Exception e) {
			LOGGER.log(Level.WARNING, "DAO:deleteById "+e.getMessage());
		} finally {
			ConnectionFactory.close(statem);
			ConnectionFactory.close(db);
		}
		if(rs==0) return -1;
		return 0;
	}
	
	/**
	 * Finds one row from the table equal to the object type, which has the given id.
	 * The obj's type must exist as a table in the database - there has to
	 * be a perfect match between the Class Name and the Tabel Name (not case sensitive).
	 * Returns null if the line with the given doesn't exists. 
	 * @param obj is used to select the table where the line will be searched
	 * @param id the value used to find the line
	 * @return the object with the given id
	 */
	public static Object findById(Object obj, int id) {
		Connection db = ConnectionFactory.getConnection();
		PreparedStatement statem = null;
		ResultSet rs=null;
		Object obiect = null;
		try {
			statem = db.prepareStatement(GenerateQuerry.generateFindByIdQ(obj));
			statem.setInt(1, id);
			rs = statem.executeQuery();
			obiect = DataToList.rsToObj(obj, rs); // nu ai garantia ca primul element e cel cu id-ul dat
		}
		catch(Exception e) {
			LOGGER.log(Level.WARNING, "DAO:findById "+e.getMessage());
		} finally {
			ConnectionFactory.close(statem);
			ConnectionFactory.close(db);
		}
		return obiect;
	}
	
	/**
	 * Return the list of objects generated with a ResultSet.
	 * The obj's type must exist as a table in the database - there has to
	 * be a perfect match between the Class Name and the Tabel Name (not case sensitive).
	 * The obj argument will be the type of the objects in the list.
	 * @param obj is used to select the table form which the data will selected. 
	 * It also give the type of the elements in the LinkedList
	 * @return a LinkedList of objects retrieved from the database
	 */
	public static LinkedList<Object> findAll(Object obj) {
		Connection db = ConnectionFactory.getConnection();
		PreparedStatement findall = null;
		LinkedList<Object> lista = null;
		try {
			findall = db.prepareStatement(GenerateQuerry.generateFindAllQ(obj));
			ResultSet rs=null;
			rs = findall.executeQuery();
			lista = DataToList.viewAll(obj,rs);
		}
		catch(Exception e) {
			LOGGER.log(Level.WARNING, "DAO:findall "+e.getMessage());
		} finally {
			ConnectionFactory.close(findall);
			ConnectionFactory.close(db);
		}
		DataToList.updateIds(lista);
		return lista;
	}
	
}
