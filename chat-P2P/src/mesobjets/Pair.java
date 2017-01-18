package mesobjets;

import java.util.ArrayList;

public class Pair {
	
	Client client;
	String suivant;
	String precedent;
	Long id;
	
	//nos m�thodes
	
	
	
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
	
	/*public  ArrayList<Integer> GetChatRoomsList() {
		ArrayList<Integer> idlist = new ArrayList<Integer>();
		for (int i = 0; i < chatroomslist.size(); i++) {
			idlist.add(chatroomslist.get(i).getPort());
		}
		return idlist;
	}
	
	public void JoinChatRoom(int chatkey) throws IOException {
		// rejoint la salle si elle existe, la crée sinon.
		int l= 0;
    	for (int i = 0; i <chatroomslist.size(); i++) {
			if (chatroomslist.get(i).getPort()==chatkey){
				ArrayList<Client> users = chatroomslist.get(i).getLesclients();
				users.add(this.client);
				chatroomslist.get(i).setLesclients(users);
			}  else {
				l=l+1 ;
			}
			if (l==chatroomslist.size()){
				System_de_chat sdc = new System_de_chat(chatkey);
				ArrayList<Client> user1 = new ArrayList<Client>() ;
				user1.add(this.client);
				sdc.setLesclients(user1);
			}
		}
		
	}
	
	public void SendToChatRoom(String s, long chatkey) {

	}

	public void ReadChatRoom(long chatkey) {

	}*/

}
