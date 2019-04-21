package Business;

import DataAccess.DAO;
import Model.Client;
import Model.Produs;

public class ManageProductRequest {
	
	/**
	 * It adds a product into the database
	 * @param nume - the name of the product
	 * @param bucati - the number of pieces of the product
	 * @param pret - the price of the product
	 * @return the product id if the insertion was succesfull
	 *  -1 otherwise
	 */
	public static int addProdus(String nume, int bucati, int pret) {
		Produs p = new Produs(nume,bucati,pret);
		if(DAO.insert(p)==0)
			return p.getId();
		else
			return -1;
	}
	
	/**
	 * It updates a product into the database
	 * @param id - the id of the product
	 * @param nume - the name of the product
	 * @param bucati - the number of pieces of the product
	 * @param pret - the price of the product
	 * @return 0 if the insertion was succesfull
	 *  -5 otherwise
	 */
	public static int updateProdus(int id,String nume, int bucati, int pret) {
		Produs p = (Produs) DAO.findById(new Produs(), id);
		if(p==null)
			return -5;
		else {
			p.setNume(nume);
			p.setBucati(bucati);
			p.setPret(pret);
			return DAO.update(p);
		}	
	}
	
	/**
	 * It adds a product into the database
	 * @param id - the id of the product
	 * @return 0 if the insertion was succesfull
	 *  -1 otherwise
	 */
	public static int deleteProdus(int id) {
		Client p = (Client) DAO.findById(new Produs(), id);
		if(p==null)
			return -5;
		else {
			return DAO.deleteById(p,id);
		}	
	}
	
}
