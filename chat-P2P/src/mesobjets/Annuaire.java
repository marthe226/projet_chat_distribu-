package mesobjets;

import java.util.ArrayList;

import javax.swing.JList;

public class Annuaire {

	ArrayList<String> listeusers;
	ArrayList<String>listeID;
	ArrayList<Client> listeClient;
	ArrayList<Client>listeDeconnecte;
	
	
	public Annuaire(){
		this.listeClient= new ArrayList<Client>();
		this.listeDeconnecte=new ArrayList<Client>();
		this.listeID= new ArrayList<String>();
		this.listeusers= new ArrayList<String>();
	}
	
	public synchronized void addNewUserAnnuaire(String adresse,String identifiant ){
		
		this.listeusers.add(identifiant);
		this.listeID.add(adresse);
		Client newClient= new Client(adresse, identifiant);
		this.listeClient.add(newClient);
		System.out.println(identifiant+ " à l'adresse"+ adresse+" ajouté dans l'annuaire");
		
	}
	
	public synchronized void refreshAnnuaire(JList<String>vueAnnuaire,String identifiant){
		for(int i=0; i<listeusers.size();i++){
			if(this.listeusers.get(i).equals(identifiant)){
				Client client= new Client(listeID.get(i), listeusers.get(i));
				listeDeconnecte.add(client);
				listeusers.remove(i);
				listeID.remove(i);
				listeClient.remove(i);
				vueAnnuaire.setVisible(false);
				vueAnnuaire.setVisible(true);
				System.out.println("refresh de la liste");
			}
		}
	}

	public ArrayList<Client> getListeClient() {
		return listeClient;
	}

	public ArrayList<Client> getListeDeconnecte() {
		return listeDeconnecte;
	}
}
