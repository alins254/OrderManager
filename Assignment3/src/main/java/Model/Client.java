package Model;

public class Client {
	private static int nextId=0;
	private int id;
	private String nume;
	private String address;
	private String email;
	public Client(String nume, String adress, String email) {
		super();
		this.id = nextId++;
		this.nume = nume;
		this.address = adress;
		this.email = email;
	}
	
	
	public Client(int id, String nume, String address, String email) {
		super();
		this.id = id;
		this.nume = nume;
		this.address = address;
		this.email = email;
	}
	
	public Client() {
		
	}

	public static int getNextId() {
		return nextId;
	}
	public static void setNextId(int nextId) {
		Client.nextId = nextId;
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
	public String getAddress() {
		return address;
	}
	public void setAdress(String adress) {
		this.address = adress;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id = " + id;
	}
	
}
