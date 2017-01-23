package Main;

import java.io.IOException;
import java.util.ArrayList;

import controleur.SystemDeChat;

public class Chat {

	public static ArrayList<SystemDeChat>  sdc = new  ArrayList<SystemDeChat>();
	
	public static void main(String[] args) throws IOException {
		SystemDeChat sr = new SystemDeChat(2237);
		sdc.add(sr) ; 
		sr.run();
		for (int i = 0; i < sr.getLesclients().size(); i++) {
			for (int j = 0; j < sr.getLesclients().get(i).getChatroomslist().size() ; j++) {
				sdc.add(sr.getLesclients().get(i).getChatroomslist().get(j)); 
			}
		}
	}
	
}
