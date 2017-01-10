package controleur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import mesobjets.Node;
import mesobjets.Ring;
import mesobjets.User;


public class System_de_chat implements Runnable {
	
	int port;
	public ServerSocket serveur;
	Ring anneau;
	ArrayList<User> lesclients;
	ArrayList<MesThreads> listeThreads;
	
	//les constructeurs
	public System_de_chat(int numPort) throws IOException{
	this.port= numPort;
	this.serveur= new ServerSocket(this.port);	
	this.anneau= new Ring();
	this.lesclients = new ArrayList<>();
	this.listeThreads= new ArrayList<MesThreads>();
	}
	//surcharge de constructeurs
	public System_de_chat() throws IOException{
	this.port=0;	
	this.serveur= new ServerSocket(this.port);	
	this.anneau= new Ring();
	this.lesclients = new ArrayList<>();
	this.listeThreads= new ArrayList<MesThreads>();
	}

	@SuppressWarnings("unused")
	@Override
	public synchronized void run() {
		Socket socket = null;
		 while(true){
			 try {
				socket= this.serveur.accept();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(-1);
			}
			 User client = new User( socket.getRemoteSocketAddress(),this);
			 MesThreads mesthread= new MesThreads(this, client, socket);
			 Thread thread = new Thread(mesthread);
			 this.listeThreads.add(mesthread);
			 lesclients.add(client);
			 Node noeud = new Node(client);
			 anneau.AlgoAjoutNoeudChord(noeud);
			 thread.start();
			 
			 if(socket == null){
				 try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.exit(-1);
				}
				 
			 }
			 
		 }
		
	}
	public ArrayList<User> getLesclients() {
		return lesclients;
	}

	public void setLesclients(ArrayList<User> lesclients) {
		this.lesclients = lesclients;
	}
	public static void main(String[] args) throws IOException {
		System_de_chat sr = new System_de_chat(1998);
		sr.run();
	}
}
