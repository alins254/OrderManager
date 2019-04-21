package Presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Expression;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import Business.CreateTables;
import Business.ManageClientRequest;
import Business.ManageComandRequest;
import DataAccess.DAO;
import Model.Produs;

public class ControllerOpComenzi {
	OperatiiComenzi view;
	OperatiiProduse viewp;
	
	public ControllerOpComenzi(OperatiiProduse v) {
		viewp = v;
		view = new OperatiiComenzi();
		view.getTrimite().addActionListener(new ButtonListener());
	}
	
	class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(view.getIdcT().getText().equals("")||
				view.getIdpT().getText().equals("")||
				view.getBucatiT().getText().equals("")){
					JOptionPane.showMessageDialog(view,"Input Gresit!");
					return;
				}
			int idc;
			int idp;
			int bucati;
			int idcmd=-1;
			try {
				bucati = Integer.parseInt(view.getBucatiT().getText());
				idc = Integer.parseInt(view.getIdcT().getText());
				idp = Integer.parseInt(view.getIdpT().getText());
				if(idc>=Model.Client.getNextId()||idp>=Model.Produs.getNextId())
					throw new Exception();
				if(!(ManageComandRequest.validateClient(idc)&&ManageComandRequest.validateProduct(idp,bucati)))
					throw new Exception();
			}catch (Exception e) {
				JOptionPane.showMessageDialog(view,"Id invalid");
				return;
			}
			idcmd = ManageComandRequest.addComanda(idc, idp, bucati);
			if(idcmd==-1)
			{
				view.getStatus().setText("Comanda nu a putut fi adaugata! Incercati din nou!");
				return;
			}
			else {
				viewp.setTabel(updateTable());
				view.getStatus().setText("Comanda a fost adaugata cu id-ul: "+idcmd);
			}
				
		}
		
	}
	private JTable updateTable() {
		return CreateTables.createTables(DAO.findAll(new Produs()));
	}
}
