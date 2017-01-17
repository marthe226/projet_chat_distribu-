package controleur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import mesobjets.Client;
import mesobjets.Pair;
import mesobjets.Chord;
import mesobjets.Client;


public class System_de_chat implements Runnable {
	
	int port;
	public ServerSocket serveur;
	Chord anneau;
	ArrayList<Client> lesclients;
	ArrayList<MesThreads> listeThreads;
	private Socket socket;
	
	//les constructeurs
	public System_de_chat(int numPort) throws IOException{
	this.port= numPort;
	this.serveur= new ServerSocket(this.port);	
	this.anneau= new Chord();
	this.lesclients = new ArrayList<>();
	this.listeThreads= new ArrayList<MesThreads>();
	}
	//surcharge de constructeurs
	public System_de_chat() throws IOException{
	this.port=0;	
	this.serveur= new ServerSocket(this.port);	
	this.anneau= new Chord();
	this.lesclients = new ArrayList<>();
	this.listeThreads= new ArrayList<MesThreads>();
	}

	
	@Override
	public synchronized void run() {
		socket = new Socket();
		 while(true){
			 try {
				socket= this.serveur.accept();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(-1);
			}
			 
			 //le nouveau client qui veut integrer l'anneau
			 Client client = new Client( socket.getRemoteSocketAddress(),this);
			 MesThreads mesthread= new MesThreads(this, client, socket);
			 Thread thread = new Thread(mesthread);
			 this.listeThreads.add(mesthread);
			 lesclients.add(client);
			 Pair noeud = new Pair(client);
			 anneau.joinMainChord(noeud);
			 thread.start();
			
			 try {
			 if(socket == null){
				
					socket.close();
					System.out.println("socket du client "+ socket.getInetAddress()+"sur le port"+ socket.getPort()+"est terminé");
				} }
			 catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(); 
					System.out.println("erreur de fermeture de socket");
					System.exit(-1);
				}
				 
			 }
			 
		 }
	public ArrayList<Client> getLesclients() {
		return lesclients;
	}

	public void setLesclients(ArrayList<Client> lesclients) {
		this.lesclients = lesclients;
	}
	public static void main(String[] args) throws IOException {
		System_de_chat sr = new System_de_chat(1995);
		sr.run();
	}
}
