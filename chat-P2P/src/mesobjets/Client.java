package mesobjets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Fenetre.Fenetre;
import controleur.SystemDeChat;


public class Client {
	
	private SocketAddress ipAdress;
	private ServerSocket seversocket;
	private String Identifiant = null;
	private SystemDeChat server;
	private Fenetre fenetre ; 
	ArrayList<SystemDeChat> chatroomslist = new ArrayList<SystemDeChat> ();
	private String adresse; 	

	
	public  Client() {
		this.fenetre = new Fenetre(this) ; 
	}
	
	public Client(SocketAddress remoteSocketAddress, SystemDeChat server) {
		this.ipAdress = remoteSocketAddress;
		this.server= server;
		this.Identifiant = JOptionPane.showInputDialog(null, "Entrez votre id: ", " Identifiant",JOptionPane.PLAIN_MESSAGE);
		this.fenetre = new Fenetre(this) ; 
	}
	public Client(String identifiant, String adresse) {
		// TODO Auto-generated constructor stub
		this.Identifiant= identifiant;
		this.adresse= adresse;
	}

	public SystemDeChat getServer() {
		return server;
	}
	public void setServer(SystemDeChat server) {
		this.server = server;
	}

	public SocketAddress getIpAdress() {
		return ipAdress;
	}
	public void setIpAdress(SocketAddress ipAdress) {
		this.ipAdress = ipAdress;
	}
	public String getIdentifiant() {
		return Identifiant;
	}
	public void setIdentifiant(String identifiant) {
		Identifiant = identifiant;
	}

	public Fenetre getFenetre() {
		return fenetre;
	}

	public void setFenetre(Fenetre fenetre) {
		this.fenetre = fenetre;
	}
	
	
	
	public ArrayList<SystemDeChat> getChatroomslist() {
		return chatroomslist;
	}

	public void setChatroomslist(SystemDeChat chatroomslist) {
		this.chatroomslist.add(chatroomslist);
	}
	
	public void JoinChatRoom(int chatkey) throws IOException {
		// rejoint la salle si elle existe, la crée sinon.
		int l= 0;
    	for (int i = 0; i <chatroomslist.size(); i++) {
			if (chatroomslist.get(i).getLePort()==chatkey){
				ArrayList<Client> users = chatroomslist.get(i).getLesclients();
				users.add(this);
				chatroomslist.get(i).setLesclients(users);
			}  else {
				l=l+1 ;
			}
			if (l==chatroomslist.size()){
				SystemDeChat sdc = new SystemDeChat(chatkey);
				this.setChatroomslist(sdc);
				ArrayList<Client> user1 = new ArrayList<Client>() ;
				user1.add(this);
				sdc.setLesclients(user1);
			}
		}
		
	}


}
