package mesobjets;

import java.util.ArrayList;

public class Ring {
	ArrayList<Node> mesnoeuds;
	
	public Ring(){
		this.mesnoeuds= new ArrayList<>();
	}

	public void AlgoAjoutNoeudChord(Node noeud){
		
		if(mesnoeuds.size()==0){
			System.out.println("cas où il n'y a aucun noeud sur notre anneau");
			mesnoeuds.add(noeud);
			
		}
		
	}
}
