package Model;


public class Comanda {
	private static int nextId=0;
	private int id;
	private int idcumparator;
	private int idprodus;
	private int suma;
	public Comanda(int id, int cumparator, int idprodus, int suma) {
		super();
		this.id = id;
		this.idcumparator = cumparator;
		this.suma = suma;
		this.idprodus = idprodus;
	}
	public Comanda(int cumparator, int idprodus, int suma) {
		super();
		this.id = nextId++;
		this.idcumparator = cumparator;
		this.suma = suma;
		this.idprodus = idprodus;
	}
	
	public Comanda() {}
	
	public static int getNextId() {
		return nextId;
	}
	public static void setNextId(int nextId) {
		Comanda.nextId = nextId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdcumparator() {
		return idcumparator;
	}
	public void setIdcumparator(int idcumparator) {
		this.idcumparator = idcumparator;
	}
	public int getIdprodus() {
		return idprodus;
	}
	public void setIdprodus(int idprodus) {
		this.idprodus = idprodus;
	}
	public int getSuma() {
		return suma;
	}
	public void setSuma(int suma) {
		this.suma = suma;
	}
	
	
	
	
}

