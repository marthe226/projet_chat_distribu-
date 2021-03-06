package controleur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import mesobjets.Client;
import mesobjets.Pair;

public class MesThreads implements Runnable  {

	SystemDeChat server;
	Client client;
	Pair pair ; 
	Socket connexion ;
	Boolean vrai = true, stopfinal=true;
	InputStreamReader monInputStream;
	
	//flux pour envoyer
	PrintWriter flux_sortant;
	
	//flux pour recevoir
	BufferedReader flux_entrant;
	
	int c_port;
	String c_ip;
	String message = null;
	
	//recuperer le message envoyer
	Scanner scan = new Scanner(System.in);
	
	
	public MesThreads (SystemDeChat server, Client client, Socket ma_connexion){
		this.server= server;
		this.connexion= ma_connexion;
		this.client= client;
		this.pair = new Pair(client); 
		try {
			monInputStream = new InputStreamReader(ma_connexion.getInputStream());
			flux_entrant = new BufferedReader(monInputStream);
			flux_sortant = new PrintWriter(ma_connexion.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}


	@Override
	public void run() {
		while (stopfinal) {

			c_port = connexion.getPort();
			c_ip = connexion.getInetAddress().toString();
			System.out.println("Un client est arrivé avec IP : " +c_ip+ " sur le port "+ c_port );
			for (int j = 0; j < server.getLesclients().size(); j++) {
				 server.getLesclients().get(j).getFenetre().setTextarea(client.getIdentifiant() +" est entré(e) dans cette conversation sur le port " +c_port +"\n");
			}
			try {

				stopfinal=Service_Client(connexion);

			} catch (IOException e) {
				System.exit(-1);
			}
			try {
				if (connexion != null) {
					connexion.close();
					System.out.format("Service du client  %s sur le port %d terminé\n", c_ip, c_port);
				}
			} catch (IOException e) {
				System.out.println("Erreur en fermant la socket   : " + e);
				System.exit(-1);
			}
		}
	}
	public synchronized Boolean Service_Client(Socket la_connection) throws IOException {

		final String Finish = "stop";
		Boolean stop = false;
		InputStreamReader isr = new InputStreamReader(connexion.getInputStream(), "UTF-8");
		BufferedReader flux_entrant = new BufferedReader(isr);
		flux_sortant = new PrintWriter(connexion.getOutputStream(), true);
		//System.out.println("Mon Tampon pour ecrire  attache ");
		//System.out.format("Pret Ã  servir  IP %s  sur le port %d\n", c_ip, c_port);
		flux_sortant.format("Hello %s  sur le port %d", c_ip, c_port);
		//String nom_Client = "un anonyme";
		String message_lu = new String();
		int line_num = 0;
		Boolean connexion_non_terminee = true;
		StringBuffer Donnees_Recues = new StringBuffer();

		while (connexion_non_terminee && ((message_lu = flux_entrant.readLine()) != null)) {
			System.out.format("%d: ->  [%s]\n", line_num, message_lu);
			for (int i = 0; i < server.listeThreads.size(); i++) {
			server.listeThreads.get(i).flux_sortant.format(client.getIdentifiant()+" : " + message_lu+"\n");
		
			}
			for (int j = 0; j < server.getLesclients().size(); j++) {
				 server.getLesclients().get(j).getFenetre().setTextarea(client.getIdentifiant()+" : " + message_lu+"\n");
			}
			line_num++;
			if (message_lu.contains(Finish)) {

				System.out.println("Reception de  " + Finish + " -> Transmission finie");
				connexion_non_terminee = false;
				la_connection.close();
				byte[] hash = Hachage.gethashkey(Donnees_Recues.toString());
				System.out.format("lol \n %d caracteres recu  ... hash hexadÃ©cimale  %s:\n", Donnees_Recues.length(),
						Hachage.bytesToHex(hash));
				System.out.println("Je termine la connection avec" + client.getIdentifiant() );
				stop = true;
				return (stop);
			}
			Donnees_Recues.append(message_lu + "\n");
		}
		

		

		return stop;
	}
}
