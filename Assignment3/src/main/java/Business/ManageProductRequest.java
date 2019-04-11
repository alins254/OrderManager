package Business;

import DataAccess.DAO;
import Model.Client;
import Model.Produs;

public class ManageProductRequest {
	
	public static int addProdus(String nume, int bucati, int pret) {
		Produs p = new Produs(nume,bucati,pret);
		if(DAO.insert(p)==0)
			return p.getId();
		else
			return -1;
	}
	
	public static int updateProdus(int id,String nume, String adresa, String email) {
		Produs p = (Produs) DAO.findById(new Produs(), id);
		if(p==null)
			return -5;
		else {

			return DAO.update(p);
		}	
	}
	
	public static int deleteProdus(int id) {
		Client p = (Client) DAO.findById(new Produs(), id);
		if(p==null)
			return -5;
		else {
			return DAO.deleteById(p,id);
		}	
	}
	
}
