package Business;

import DataAccess.DAO;
import Model.Client;
import Model.Comanda;
import Model.Produs;

public class ManageComandRequest {
	
	/**
	 * Validate if the client exist in the database.
	 * @param id - id of the client
	 * @return true if the client exist
	 * 			false if the client don't exist
	 */
	public static boolean validateClient(int id) {
		Client c = (Client) DAO.findById(new Client(), id);
		if(c==null)
			return false;
		return true;
	}
	
	/**
	 * Validate if the product exist in the database.
	 * @param id - id of the product
	 * @return true if the product exist
	 * 			false if the product don't exist
	 */
	public static boolean validateProduct(int id, int bucati) {
		Produs p = (Produs) DAO.findById(new Produs(), id);
		if(p==null)
			return false;
		if(p.getBucati()<bucati)
			return false;
		return true;
	}
	
	/**
	 * It adds the Order into the database.
	 * @param idc - the id of the client who made the order
	 * @param idp - the id of the product ordered
	 * @param bucati - the number of pieces ordered
	 * @return 0 if the order was added, -1 otherwise
	 */
	public static int addComanda(int idc, int idp, int bucati) {
		Produs p = (Produs) DAO.findById(new Produs(), idp);
		Client client = (Client) DAO.findById(new Client(), idc);
		DAO.findAll(new Comanda());
		Comanda c = new Comanda(idc, idp, bucati*p.getPret());
		if(DAO.insert(c)==0) {
			ManageProductRequest.updateProdus(idp, p.getNume(), p.getBucati()-bucati, p.getPret());
			CreatePDF.createFile(client, p, c,bucati);
			return c.getId();
		}
		else
			return -1;
	}
}
