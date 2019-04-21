package Business;



import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import Model.Client;
import Model.Comanda;
import Model.Produs;
public class CreatePDF {
	
	/** Generate the a file for each given comand querry.
	 * @param the client who ordered
	 * @param the product that had been ordered
	 * @param the comand itself
	 * @param the number of items bought
	 * @return 0 if the file was created, 
	 * 			-1 otherwise
	 */
	public static int createFile(Client c, Produs p, Comanda cmd, int bucati) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("Comanda"+cmd.getId()+".txt", "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		writer.println("Comanda "+cmd.getId());
		writer.println(c.toString()+" a achizitionat: ");
		writer.println(p.toString()+" in numar de "+bucati+" bucati.");
		writer.println("Total: "+cmd.getSuma()+" lei");
		
		writer.close();
		
		return 0;
	}
}
