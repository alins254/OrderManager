package Presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import Business.CreateTables;
import Business.ManageProductRequest;
import DataAccess.DAO;
import Model.Produs;


public class ControllerOpProduse {

	OperatiiProduse view;
	
	public ControllerOpProduse() {
		view = new OperatiiProduse(CreateTables.createTables(DAO.findAll(new Produs())));
		view.getTrimiteAdd().addActionListener(new AdaugareClientListener());
		view.getAdaugare().addActionListener(new AddClientListener());
		view.getInapoi().addActionListener(new BackButtonListener());
		view.getEditare().addActionListener(new EditClientListener());
		view.getTrimiteEdit().addActionListener(new EditareClientListener());
		view.getStergere().addActionListener(new DeleteClientListener());
		view.getTrimiteDelete().addActionListener(new StergeClientListener());
	}
	
	class AdaugareClientListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(view.getBucatiT().getText().equals("")||
			   view.getPretT().getText().equals("")||
			   view.getNumeT().getText().equals("")) {
					JOptionPane.showMessageDialog(view,"Input Gresit");
					return;
			}

			int status;
			int bucati=0;
			int pret=0;
			try {
				bucati = Integer.parseInt(view.getBucatiT().getText());
				pret = Integer.parseInt(view.getPretT().getText());
				if(bucati<0||pret<=0)
					throw new Exception();
			}catch (Exception e) {
				JOptionPane.showMessageDialog(view,"Input Gresit");
				return;
			}
			status = ManageProductRequest.addProdus(view.getNumeT().getText(),bucati,pret);
			if(status != -1)
				view.getStatusL().setText("Clientul a fost adaugat si are id "+status);
			else
				view.getStatusL().setText("Clientul nu a putut fi adaugat. Incercati din nou!");
			view.setTabel(updateTable());
		}
		
	}
	
	class AddClientListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			view.addProdus();
		}
		
	}
	
	class EditClientListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			view.editProdus();
		}
		
	}
	
	class DeleteClientListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			view.deleteProdus();
		}
		
	}
	
	class EditareClientListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(view.getBucatiT().getText().equals("")||
			   view.getPretT().getText().equals("")||
			   view.getNumeT().getText().equals("")||
			   view.getIdT().getText().equals("")) {
				JOptionPane.showMessageDialog(view,"Input Gresit");
				return;
			}
			int id;	
			int status;
			try {
				id=Integer.parseInt(view.getIdT().getText());
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(view,"Input Gresit");
				return;
			}
			status = ManageProductRequest.updateProdus(id,view.getNumeT().getText(),view.getBucatiT().getText(),view.getPretT().getText());
			if(status == 0)
				view.getStatusL().setText("Produsul a fost actualizat!");
			else
				view.getStatusL().setText("Produsul nu a putut fi actualizat!\nVerificati id!");
			view.setTabel(updateTable());
		}
		
	}
	
	class StergeClientListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(view.getIdT().getText().equals("")) {
				JOptionPane.showMessageDialog(view,"Input Gresit");
				return;
			}
			int id;	
			int status;
			try {
				id=Integer.parseInt(view.getIdT().getText());
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(view,"Input Gresit");
				return;
			}
			status = ManageProductRequest.deleteProdus(id);
			if(status == 0)
				view.getStatusL().setText("Produsul a fost actualizat!");
			else
				view.getStatusL().setText("Produsul nu a putut fi actualizat!\nVerificati id!");
			view.setTabel(updateTable());
		}
		
	}

	
	class BackButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			view.initOptionPane();;
		}
		
	}
	private JTable updateTable() {
		return CreateTables.createTables(DAO.findAll(new Produs()));
	}
}
