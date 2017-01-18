package mesobjets;

import java.net.ServerSocket;
import java.net.SocketAddress;

import javax.swing.JOptionPane;

import controleur.SystemDeChat;


public class Client {
	
	private SocketAddress ipAdress;
	private ServerSocket seversocket;
	private String Identifiant = null;
	private SystemDeChat server;

	
	
	
	public  Client() {
		
	}
	
	public Client(SocketAddress remoteSocketAddress, SystemDeChat server) {
		this.ipAdress = remoteSocketAddress;
		this.server= server;
		this.Identifiant = JOptionPane.showInputDialog(null, "Entrez votre id: ", " Identifiant",JOptionPane.PLAIN_MESSAGE);
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
	
	

}
