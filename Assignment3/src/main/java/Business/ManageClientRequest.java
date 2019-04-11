package Business;

import DataAccess.DAO;
import Model.Client;

public class ManageClientRequest {
	
	public static int addClient(String nume, String adresa, String email) {
		Client c = new Client(nume,adresa,email);
		if(DAO.insert(c)==0)
			return c.getId();
		else
			return -1;
	}
	
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
	
	public static int deleteClient(int id) {
		Client c = (Client) DAO.findById(new Client(), id);
		if(c==null)
			return -5;
		else {
			return DAO.deleteById(c,id);
		}	
	}
}
