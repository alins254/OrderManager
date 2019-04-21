package Business;

import DataAccess.DAO;
import Model.Client;

public class ManageClientRequest {
	
	/**
	 * It adds a client into the database
	 * @param nume - the name of the client
	 * @param adresa - the address of the client
	 * @param email - the email of the client
	 * @return the client id if the insertion was succesfull
	 *  -1 otherwise
	 */
	public static int addClient(String nume, String adresa, String email) {
		Client c = new Client(nume,adresa,email);
		if(DAO.insert(c)==0)
			return c.getId();
		else
			return -1;
	}
	
	/**
	 * It updates a client into the database
	 * @param id - the id of the client
	 * @param nume - the new name of the client
	 * @param adresa - the new address of the client
	 * @param email - the new email of the client
	 * @return 0 if the insertion was succesfull
	 *  -5 otherwise
	 */
	public static int updateClient(int id,String nume, String adresa, String email) {
		Client c = (Client) DAO.findById(new Client(), id);
		if(c==null)
			return -5;
		else {
			c.setNume(nume);
			c.setAdress(adresa);
			c.setEmail(email);
			return DAO.update(c);
		}	
	}
	
	/**
	 * It deletes a client into the database
	 * @param id - the id of the client to be deleted
	 * @return 0 if the insertion was succesfull
	 *  -5 otherwise
	 */
	public static int deleteClient(int id) {
		Client c = (Client) DAO.findById(new Client(), id);
		if(c==null)
			return -5;
		else {
			return DAO.deleteById(c,id);
		}	
	}
}
