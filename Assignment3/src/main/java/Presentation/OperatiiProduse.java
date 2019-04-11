package Presentation;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class OperatiiProduse extends JFrame{

	private static final long serialVersionUID = 1L;
	JPanel optionsP = new JPanel();
	
	JPanel deleteProdusP = new JPanel();
	JPanel viewClientsP = new JPanel();
	
	JTable tabel;
	JScrollPane scroll = new JScrollPane();
	
	JLabel optionsL = new JLabel();
	
	JLabel numeL = new JLabel("Nume: ",JLabel.CENTER);
	JLabel bucatiL = new JLabel("Bucati: ",JLabel.CENTER);
	JLabel pretL = new JLabel("Pret: ",JLabel.CENTER);
	JLabel statusL = new JLabel("",JLabel.CENTER);
	JTextField numeT = new JTextField(5);
	JTextField bucatiT = new JTextField(5);
	JTextField pretT = new JTextField(5);
	JPanel resultP = new JPanel(new GridLayout(2,1));
	
	JLabel idL = new JLabel("Id: ",JLabel.CENTER);
	JTextField idT = new JTextField(20);
	
	JButton adaugare = new JButton("Add Product");
	JButton editare = new JButton("Edit Product");
	JButton stergere = new JButton("Delete Product");
	JButton trimiteAdd = new JButton("Send");
	JButton trimiteEdit = new JButton("Send");
	JButton trimiteDelete = new JButton("Send");
	JButton inapoi = new JButton("Back");
	
	public OperatiiProduse(JTable tabel) {
		setLayout(new GridLayout(2,1));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 700);
		
		initOptionPane();
		add(optionsP);
		
		this.tabel = tabel;
		scroll = new JScrollPane(tabel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(scroll);
		
		setVisible(true);
		
	}
	
	public void initOptionPane() {
		optionsP.removeAll();
		optionsP.setLayout(new GridLayout(3,1));
		optionsP.add(adaugare);
		optionsP.add(editare);
		optionsP.add(stergere);
		repaint();
		setVisible(true);
		
	}
	
	public void addProdus() {
		optionsP.removeAll();
		optionsP.setLayout(new GridLayout(4,2));
		statusL.setText("");
		optionsP.add(numeL);
		optionsP.add(numeT);
		optionsP.add(bucatiL);
		optionsP.add(bucatiT);
		optionsP.add(pretL);
		optionsP.add(pretT);
		optionsP.add(statusL);
		resultP.removeAll();
		resultP.add(trimiteAdd);
		resultP.add(inapoi);
		optionsP.add(resultP);
		repaint();
		setVisible(true);
	}
	
	public void editProdus() {
		optionsP.removeAll();
		optionsP.setLayout(new GridLayout(5,2));
		statusL.setText("");
		optionsP.add(idL);
		optionsP.add(idT);
		optionsP.add(numeL);
		optionsP.add(numeT);
		optionsP.add(bucatiL);
		optionsP.add(bucatiT);
		optionsP.add(pretL);
		optionsP.add(pretT);
		optionsP.add(statusL);
		resultP.removeAll();
		resultP.add(trimiteEdit);
		resultP.add(inapoi);
		optionsP.add(resultP);
		repaint();
		setVisible(true);
	}
	
	public void deleteProdus() {
		optionsP.removeAll();
		optionsP.setLayout(new GridLayout(3,1));
		statusL.setText("");
		optionsP.add(idL);
		deleteProdusP.add(idT);
		optionsP.add(deleteProdusP);
		resultP.removeAll();
		resultP.add(trimiteDelete);
		resultP.add(inapoi);
		optionsP.add(resultP);
		repaint();
		setVisible(true);
	}

	
	public JButton getTrimiteDelete() {
		return trimiteDelete;
	}

	public JTextField getNumeT() {
		return numeT;
	}

	public JTextField getBucatiT() {
		return bucatiT;
	}

	public JTextField getPretT() {
		return pretT;
	}

	public JLabel getStatusL() {
		return statusL;
	}

	public JButton getAdaugare() {
		return adaugare;
	}

	public JButton getEditare() {
		return editare;
	}

	public JButton getStergere() {
		return stergere;
	}

	public JButton getTrimiteAdd() {
		return trimiteAdd;
	}
	
	public JButton getTrimiteEdit() {
		return trimiteEdit;
	}

	public JButton getInapoi() {
		return inapoi;
	}
	
	
	public JTextField getIdT() {
		return idT;
	}

	public void setTabel(JTable tabel) {
		this.tabel = tabel;
		remove(scroll);
		scroll = new JScrollPane(tabel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(scroll);
		repaint();
		setVisible(true);
	}
	
	
	
	
}
