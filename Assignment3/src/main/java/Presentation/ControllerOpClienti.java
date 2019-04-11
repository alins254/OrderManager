package Presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import Business.CreateTables;
import Business.ManageClientRequest;
import DataAccess.DAO;
import Model.Client;

public class ControllerOpClienti {
	
	OperatiiClienti view;
	
	public ControllerOpClienti() {
		view = new OperatiiClienti(CreateTables.createTables(DAO.findAll(new Client())));
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
			if(view.getAdresaT().getText().equals("")||
			   view.getEmailT().getText().equals("")||
			   view.getNumeT().getText().equals(""))
					JOptionPane.showMessageDialog(view,"Input Gresit");
			int status;
			status = ManageClientRequest.addClient(view.getNumeT().getText(),view.getAdresaT().getText(),view.getEmailT().getText());
			if(status != -1)
				view.getStatusL().setText("Clientul a fost adaugat si are id "+status);
			else
				view.getStatusL().setText("Clientul nu a putut fi adaugat. Incercati din nou!");
			view.setTabel(updateTable());
		}
		
	}
	
	class AddClientListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			view.addClient();
		}
		
	}
	
	class EditClientListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			view.editClient();
		}
		
	}
	
	class DeleteClientListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			view.deleteClient();
		}
		
	}
	
	class EditareClientListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(view.getAdresaT().getText().equals("")||
			   view.getEmailT().getText().equals("")||
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
			status = ManageClientRequest.updateClient(id,view.getNumeT().getText(),view.getAdresaT().getText(),view.getEmailT().getText());
			if(status == 0)
				view.getStatusL().setText("Clientul a fost actualizat!");
			else
				view.getStatusL().setText("Clientul nu a putut fi actualizat!\nVerificati id!");
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
			status = ManageClientRequest.deleteClient(id);
			if(status == 0)
				view.getStatusL().setText("Clientul a fost actualizat!");
			else
				view.getStatusL().setText("Clientul nu a putut fi actualizat!\nVerificati id!");
			view.setTabel(updateTable());
		}
		
	}

	
	class BackButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			view.initOptionPane();;
		}
		
	}
	private JTable updateTable() {
		return CreateTables.createTables(DAO.findAll(new Client()));
	}
}
