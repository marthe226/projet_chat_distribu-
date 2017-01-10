package mesobjets;

import java.net.SocketAddress;

import javax.swing.JOptionPane;

import controleur.System_de_chat;


public class User {
	
	public SocketAddress ipAdress;
	public String Identifiant = null;
	public System_de_chat server;
	
	
	public  User() {
		
	}
	
	public User(SocketAddress remoteSocketAddress, System_de_chat server) {
		this.ipAdress = remoteSocketAddress;
		this.server= server;
		this.Identifiant = JOptionPane.showInputDialog(null, "Entrez votre id: ", " Identifiant",JOptionPane.PLAIN_MESSAGE);
	}
	public System_de_chat getServer() {
		return server;
	}
	public void setServer(System_de_chat server) {
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

}
