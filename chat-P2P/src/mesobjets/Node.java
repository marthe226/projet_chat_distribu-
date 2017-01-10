package mesobjets;

public class Node {
	User client;
	String clientsuivant;
	String clientprecedent;
	
	public Node(){
		
	}
	
	public Node (User unclient){
		this.client= unclient;
	}

}
