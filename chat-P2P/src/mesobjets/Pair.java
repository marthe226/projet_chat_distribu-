package mesobjets;

public class Pair {
	
	Client client;
	String suivant;
	String precedent;
	
	//nos méthodes
	
	
	
	//le constructeur
	public Pair(Client client) {
		//super();
		this.client = client;
	}
	
	public Pair() {
		//super();
		
	}
	
	
	//les get et set de nos differents attributs
	public Client getClient() {
		return client;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	public String getSuivant() {
		return suivant;
	}
	public void setSuivant(String suivant) {
		this.suivant = suivant;
	}
	public String getPrecedent() {
		return precedent;
	}
	public void setPrecedent(String precedent) {
		this.precedent = precedent;
	}

}
