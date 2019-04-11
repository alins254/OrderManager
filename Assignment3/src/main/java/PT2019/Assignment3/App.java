package PT2019.Assignment3;


import java.sql.Connection;
import java.util.LinkedList;

import Business.CreateTables;
import DataAccess.ConnectionFactory;
import DataAccess.DAO;
import Model.Client;
import Model.Comanda;
import Model.Produs;
import Presentation.ControllerOpClienti;
import Presentation.ControllerOpProduse;
import Presentation.OperatiiClienti;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //LinkedList<Object> clienti = new LinkedList<Object>();
        //clienti.add(new Client("ion","Str. industriei nr.24","abc@abc.abc"));
        //clienti.add(new Client("gheorghe","Str. industriei nr.25","abc1@abc.abc"));
        //clienti.add(new Client("vasile","Str. industriei nr.26","abc2@abc.abc"));
       
        //Connection dbConnection = ConnectionFactory.getConnection();
        //DAO.insert(new Client(10,"ion","Str. industriei nr.24","abc@abc.abc"));
       //DAO.findAll(new Client());
        
       // DAO.findAll(new Client());
       // System.out.println("Delete all a returnat"+DAO.deleteAll(new Client()));
       // System.out.println("Lista clienti:"+DAO.findAll(new Client()));
       //DAO.insert(new Client(1,"abc","a12 .dsa","abc@abc"));
       //DAO.insert(new Client(2,"abc","a12 .dsa","abc@abc"));
      // DAO.insert(new Client(3,"3abc","3a12","33a@abc"));
       // DAO.update(new Client(1,"cba","a1235 .dsa","abc123@abc"));
       // System.out.println(DAO.findById(new Client(), 2));
       // System.out.println("Delete by a returnat "+DAO.deleteById(new Client(), 3));
       // OperatiiClienti oc  = new OperatiiClienti(CreateTables.createTables(DAO.findAll(new Client())));
        ControllerOpClienti a = new ControllerOpClienti();
        ControllerOpProduse b = new ControllerOpProduse();
        //DAO.insert(new Produs(1,"abc",20,30));
       // DAO.insert(new Comanda(1,1,1,50));
       // DAO.insert(new Comanda(2,5,10,50));
    }
}

/*

*/