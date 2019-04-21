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
		view.getTrimiteAdd().addActionListener(new AdaugareProdusListener());
		view.getAdaugare().addActionListener(new AddProdusListener());
		view.getInapoi().addActionListener(new BackButtonListener());
		view.getEditare().addActionListener(new EditProdusListener());
		view.getTrimiteEdit().addActionListener(new EditareProdusListener());
		view.getStergere().addActionListener(new DeleteProdusListener());
		view.getTrimiteDelete().addActionListener(new StergeProdusListener());
	}
	
	class AdaugareProdusListener implements ActionListener{

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
				view.getStatusL().setText("Produsul a fost adaugat si are id "+status);
			else
				view.getStatusL().setText("Produsul nu a putut fi adaugat. Incercati din nou!");
			view.setTabel(updateTable());
		}
		
	}
	
	class AddProdusListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			view.addProdus();
		}
		
	}
	
	class EditProdusListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			view.editProdus();
		}
		
	}
	
	class DeleteProdusListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			view.deleteProdus();
		}
		
	}
	
	class EditareProdusListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(view.getBucatiT().getText().equals("")||
			   view.getPretT().getText().equals("")||
			   view.getNumeT().getText().equals("")||
			   view.getIdT().getText().equals("")) {
				JOptionPane.showMessageDialog(view,"Input Gresit");
				return;
			}
			int id;	
			int bucati;
			int pret;
			int status;
			try {
				id=Integer.parseInt(view.getIdT().getText());
				bucati = Integer.parseInt(view.getBucatiT().getText());
				pret = Integer.parseInt(view.getPretT().getText());
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(view,"Input Gresit");
				return;
			}
			status = ManageProductRequest.updateProdus(id,view.getNumeT().getText(),bucati,pret);
			if(status == 0)
				view.getStatusL().setText("Produsul a fost actualizat!");
			else
				view.getStatusL().setText("Produsul nu a putut fi actualizat!\nVerificati id!");
			view.setTabel(updateTable());
		}
		
	}
	
	class StergeProdusListener implements ActionListener{

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
	public OperatiiProduse getView() {
		return view;
	}
	
	
}
