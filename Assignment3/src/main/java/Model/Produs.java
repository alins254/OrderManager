package Model;

public class Produs {
	private static int nextId=0;
	private int id;
	private String nume;
	private int bucati;
	private int pret;
	
	public Produs(String nume, int bucati, int pret) {
		super();
		this.id = nextId++;
		this.nume = nume;
		this.bucati = bucati;
		this.pret = pret;
	}
	
	public Produs() {}
	
	public static int getNextId() {
		return nextId;
	}
	public static void setNextId(int nextId) {
		Produs.nextId = nextId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	public int getBucati() {
		return bucati;
	}
	public void setBucati(int bucati) {
		this.bucati = bucati;
	}
	public int getPret() {
		return pret;
	}
	public void setPret(int pret) {
		this.pret = pret;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Produsul "+nume+" cu pretul de "+pret+" de lei";
	}
}
