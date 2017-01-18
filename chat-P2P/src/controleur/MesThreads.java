package controleur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import mesobjets.ChatMessage;
import mesobjets.Client;

public class MesThreads implements Runnable  {

	SystemDeChat server;
	Client client;
	Socket ma_connexion ;
	Boolean vrai = true;
	InputStreamReader monInputStream;
	
	//flux pour envoyer
	PrintWriter flux_sortant;
	
	//flux pour recevoir
	BufferedReader flux_entrant;
	
	int c_port;
	String c_ip;
	ChatMessage message;
	//String message = null;
	
	//recuperer le message envoyer
	Scanner scan = new Scanner(System.in);
	
	
	public MesThreads (SystemDeChat server, Client client, Socket ma_connexion){
		this.server= server;
		this.ma_connexion= ma_connexion;
		this.client= client;
		
		
		
		
	}


	@Override
	public void run() {
		/*try {
			//ma_connexion.setSoTimeout(10);
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		try {
			monInputStream = new InputStreamReader(ma_connexion.getInputStream());
			flux_entrant = new BufferedReader(monInputStream);
			flux_sortant = new PrintWriter(ma_connexion.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (true) {
			c_port = ma_connexion.getPort();
			c_ip = ma_connexion.getInetAddress().toString();
			
			System.out.println("Un client est arriv� avec IP : " +c_ip+ " sur le port "+ c_port );
			System.out.println("Welcome "+ client.getIdentifiant());

			try {

				Service_Client(ma_connexion);

			} catch (IOException e) {
				System.exit(-1);
			}
			
			try {
				if (ma_connexion != null) {
					flux_entrant.close();
					flux_sortant.close();
					ma_connexion.close();
					System.out.format("Service du client  %s sur le port %d terminé\n", c_ip, c_port);
				}
			} catch (IOException e) {
				System.out.println("Erreur en fermant la socket   : " + e);
				System.exit(-1);
			}
		}
	}
	public synchronized void Service_Client(Socket la_connection) throws IOException {

		final String Finish = "stop";
		Boolean stop = false;
		InputStreamReader isr = new InputStreamReader(la_connection.getInputStream(), "UTF-8");
		BufferedReader flux_entrant = new BufferedReader(isr);
		flux_sortant = new PrintWriter(la_connection.getOutputStream(), true);
		System.out.println("Mon Tampon pour ecrire  attache ");
		System.out.format("Pret à servir  IP %s  sur le port %d\n", c_ip, c_port);
		flux_sortant.format("Hello %s  sur le port %d,  vous etes, pour faire simple, disons Admis\n", c_ip, c_port);
		flux_sortant.println("Pour quitter la conversation,saisir 'stop'");
		String nom_Client = "un anonyme";
		String message_lu ;
		int line_num = 0;
		Boolean connexion_non_terminee = true;
		StringBuffer Donnees_Recues = new StringBuffer();

		while (connexion_non_terminee) {
		
			 message_lu = flux_entrant.readLine();
			System.out.format("%d: ->  [%s]\n", line_num, message_lu);
			for (int i = 0; i < server.listeThreads.size(); i++) {
				server.listeThreads.get(i).flux_sortant.format(client.getIdentifiant()+" : " + message_lu+"\n");

			}
			line_num++;
			if (message_lu.contains(Finish)) {

				System.out.println("Reception de  " + Finish + " -> Transmission finie");
				connexion_non_terminee = false;
				Donnees_Recues.append(message_lu + "\n");
				la_connection.close();
				//break;
				byte[] hash = Hachage.gethashkey(Donnees_Recues.toString());
				System.out.format("lol \n %d caracteres recu  ... hash hexadécimale  %s:\n", Donnees_Recues.length(),
						Hachage.bytesToHex(hash));
				System.out.println("Je termine la connection avec" + client.getIdentifiant());
				stop = true;
				//return (stop);
				
			}
			Donnees_Recues.append(message_lu);
			
			
		}

		//return stop;
	}
}

