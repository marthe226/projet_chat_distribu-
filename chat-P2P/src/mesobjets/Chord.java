package mesobjets;

import java.util.ArrayList;

public class Chord {
	ArrayList<Pair> mesnoeuds;
	
	public Chord(){
		this.mesnoeuds= new ArrayList<>();
	}
	
	
	//methode permettant d'ajouter un nouveau pair dans le chord

	public void joinMainChord(Pair pair){
		
		if(mesnoeuds.size()==0){
			System.out.println("cas où il n'y a aucun noeud sur notre anneau");
			mesnoeuds.add(pair);
			pair.suivant= pair.client.getIpAdress().toString();
			pair.precedent= pair.client.getIpAdress().toString();
			System.out.println("moi="+ pair.client.getIpAdress().toString()+ "a pour suivant="+ pair.client.getIpAdress().toString()+"et"
					+ "pour precedent=" + pair.client.getIpAdress().toString());
			
		}
		
		else if (mesnoeuds.size()==1){
			System.out.println("cas où il n'y a un noeud sur notre anneau");
			mesnoeuds.add(pair);
			int tailleListe= mesnoeuds.size();
			
			//indice des pairs dans le arrayList
			int indiceprecedent= tailleListe-2;
			int indicesuivant= tailleListe-tailleListe;
			
			//adresse ip des suivants et des prédecesseurs
			pair.precedent= mesnoeuds.get(indiceprecedent).client.getIpAdress().toString();
			pair.suivant=mesnoeuds.get(indicesuivant).client.getIpAdress().toString();
			
			
			//mise à jour des noeuds
			mesnoeuds.get(indicesuivant).suivant= pair.client.getIpAdress().toString();
			mesnoeuds.get(indiceprecedent).precedent= pair.client.getIpAdress().toString();
			
			System.out.println("moi="+ pair.client.getIpAdress().toString()+ "a pour suivant="+ pair.client.getIpAdress().toString()+"et"
					+ "pour precedent=" + pair.client.getIpAdress().toString());
			
		}
		else{
			mesnoeuds.add(pair);
			int tailleListe= mesnoeuds.size();
			//indice des pairs dans le arrayList
			int indiceprecedent= tailleListe-2;
			int indicesuivant= tailleListe-tailleListe;
			
			//adresse ip des suivants et des prédecesseurs
			pair.precedent= mesnoeuds.get(indiceprecedent).client.getIpAdress().toString();
			pair.suivant=mesnoeuds.get(indicesuivant).client.getIpAdress().toString();
			
			
			//mise à jour des noeuds
			mesnoeuds.get(indiceprecedent).suivant= pair.client.getIpAdress().toString();
			System.out.println("moi="+ pair.client.getIpAdress().toString()+ " a pour suivant= "+ pair.client.getIpAdress().toString()+"et"
					+ " pour precedent=" + pair.client.getIpAdress().toString());
		}
		
	}
	
	
	//methode appélée par un pair ayant pour key comme clef 
	//et souhaitant rejoindre l'anneau
public Long findMainChord(Long key){
	return (long) 5;
			
	
	}


//met à jour les liens de l'anneau virtuel
public void leaveMainChord(Pair pair){
	
	
}

//cette methode permet à un pair de fair circuler un message vers ses succeurs
public void forwardMessage(){	
}

}
