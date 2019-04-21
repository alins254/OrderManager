package Presentation;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class OperatiiComenzi extends JFrame{
	JPanel idc = new JPanel();
	JPanel idp = new JPanel();
	JPanel bucati = new JPanel();
	
	JTextField idcT = new JTextField(5);
	JTextField idpT = new JTextField(5);
	JTextField bucatiT = new JTextField(5);
	
	JLabel status = new JLabel("",JLabel.CENTER);
	JLabel idcL = new JLabel("Id Client: ");
	JLabel idpL = new JLabel("Id Produs: ");
	JLabel bucatiL = new JLabel("Numar produse: ");
	
	JButton trimite = new JButton("Comanda!");
	
	public OperatiiComenzi() {
		setSize(500,200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(5,1));
		initLayout();
		setVisible(true);
	}
	
	public void initLayout() {
		idc.add(idcL);
		idc.add(idcT);
		idp.add(idpL);
		idp.add(idpT);
		bucati.add(bucatiL);
		bucati.add(bucatiT);
		add(idc);
		add(idp);
		add(bucati);
		add(status);
		add(trimite);	
	}

	public JTextField getIdcT() {
		return idcT;
	}

	public JTextField getIdpT() {
		return idpT;
	}

	public JTextField getBucatiT() {
		return bucatiT;
	}

	public JLabel getStatus() {
		return status;
	}

	public JButton getTrimite() {
		return trimite;
	}
	
	
	
}
