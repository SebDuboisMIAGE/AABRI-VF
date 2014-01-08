import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class AABRI {
	public ABRI racine;
	
	public AABRI(){
		racine = null;
	}
	
	public AABRI(String path) // lecture du fichier passé en paramètre
	{
		racine = null;
		try{
			InputStream ips = new FileInputStream(path); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne = "";
			while ((ligne = br.readLine())!=null){	// lecture de chacune des lignes
				//System.out.println(ligne);			// écriture de la ligne
				String[] strArray1 = ligne.split(";");	// split par ";" afin de récupérer les bornes min et max
				String[] strArrayMinMax = strArray1[0].split(":");	// split par ":" pour garder le min et la max
				String[] strArrayArbre = strArray1[1].split(":");	// split par ":" pour parser les différentes valeurs des noeuds
				int[] intArray1 = new int[strArrayMinMax.length];	
				int[] intArray2 = new int[strArrayArbre.length];
				
				for(int i = 0; i < strArrayMinMax.length; i++) {	// création d'un tableau d'entier des valeurs des noeuds
				    intArray1[i] = Integer.parseInt(strArrayMinMax[i]);
				}
				
				ABRI arbre = new ABRI();
				for(int i = 0; i < strArrayArbre.length; i++) {	
				    intArray2[i] = Integer.parseInt(strArrayArbre[i]);
				}
				arbre.CreerABRI(intArray1[0], intArray1[1], intArray2);	 // on créé un ABRI avec le min et max et les différents noeuds			
				racine = inserer(arbre, racine);		// ensuite on insère l'arbre dans l'AABRI
				//arbre.parcoursPrefixe();				// On lit le parcours préfixe pour contrôler 
			}
			br.close(); 
		}
		catch (Exception e){
			System.out.println(e.toString());
		}
	}
	
	public void ecrireAABRIFichier(String savepath)
	{
		String chaine = "";
		if(this.racine != null){
			chaine = racine.ecrireToutArbreFichier();
		}		
		try {
			FileWriter fw = new FileWriter (savepath);
			BufferedWriter bw = new BufferedWriter (fw);
			PrintWriter fichierSortie = new PrintWriter (bw); 
			fichierSortie.println (chaine + "\n"); 
			fichierSortie.close();
		}
		catch (Exception e){
			System.out.println(e.toString());
		}
	}
	
	public AABRI genererAabriAleatoire(Scanner saisie){
		int nbnoeud = 0;
		int bornemax = 0;
		int nbvalpossible = 0;
		boolean existedeja = false;
		int[] listeintervalle;
		int[] intArray1;	
		int[] intArray2;
		int nbn = 0;
		int element = 0;
		System.out.println("Nombre de noeuds ? ");
		nbnoeud = saisie.nextInt();
		System.out.println("Borne max ? ");
		bornemax = saisie.nextInt();
		listeintervalle = new int[nbnoeud*2];
		for(int j = 0; j != nbnoeud * 2; j++){   // on prend toutes les valeurs pour les intervalles
			int a = (int) (Math.random()*bornemax);
			existedeja = false;
			for(int k = 0; k != listeintervalle.length; k++){
				if(listeintervalle[k] == a){
					existedeja = true;
				}
			}
			if(existedeja == true){
				j--;
			}
			else{
				listeintervalle[j] = a;
			}
		}
		
		triBulleCroissant(listeintervalle); // on trie par ordre croissant le tableau
		
		for(int l = 0; l != nbnoeud; l++){
			ABRI abri = new ABRI();
			int min;
			int max;
			
			min = listeintervalle[2*l];
			max = listeintervalle[(2*l)+1]; // min et max du premier intervalle
			nbvalpossible = max - min;  //nb val possibles
			while (nbn == 0 && nbvalpossible != 1){
				nbn = (int) (Math.random()*nbvalpossible);  // nb noeud dans ABRI
			}
			if(nbvalpossible == 1){
				nbn = nbvalpossible;
			}
			intArray2 = new int[nbn];  //cr�ation d'un tableau d'�l�ment
			for(int j = 0; j != nbn; j++){
				element = (int) (min + Math.random() * (max - min + 1));
				intArray2[j] = element;
			}
			abri.CreerABRI(min, max, intArray2);
			racine = inserer(abri, racine);
			nbn = 0;
		}
		return this;		
	}
	
	public ABRI inserer(ABRI valeur, ABRI racine) {		// insertion d'un ABRI dans l'AABRI
		if (racine == null) return valeur;
		if (valeur.getMax() < racine.getMin()) racine.setSag(inserer(valeur, racine.getSag()));
		else if (valeur.getMin() > racine.getMax()) racine.setSad(inserer(valeur, racine.getSad()));
		return racine;
	}
	
	public void insererUnElement(Scanner saisie){
		int element;
		element = 0;
		System.out.println("Element a ajouter : ");
		element = saisie.nextInt();
		if(this.racine != null)
		{
			racine.insererElement(element);
		}
		else
		{
			System.out.println("L'abre est vide");
		}
	}
	
	public void supprimerUnElement(Scanner saisie){
		int element;
		element = 0;
		System.out.println("Element a supprimer : ");
		element = saisie.nextInt();
		if(this.racine != null)
		{
			racine.supprimerElement(element);
		}
		else
		{
			System.out.println("L'abre est vide");
		}
	}
	
	public void afficherAABRI(){
		String chaine = "";
		if(this.racine != null){
			chaine = racine.afficherToutArbre();
			System.out.println(chaine);
		}		
		else{
			System.out.println("L'abre est vide");
		}
	}
	
	public static void triBulleCroissant(int tableau[]) {
		int longueur = tableau.length;
		int tampon = 0;
		boolean permut; 
		do {
			permut = false;
			for (int i = 0; i < longueur - 1; i++) {
				if (tableau[i] > tableau[i + 1]) {
					tampon = tableau[i];
					tableau[i] = tableau[i + 1];
					tableau[i + 1] = tampon;
					permut = true;
				}
			}
		} while (permut);
	}

	public ABRI getValeur() {
		return racine;
	}

	public void setValeur(ABRI valeur) {
		this.racine = valeur;
	}

	public void ABRversAABRI(Scanner saisie, ABR abr2) {
		int min = abr2.getRacine().getMinValue().getValeur();
		int max = abr2.getRacine().getMaxValue().getValeur();
		int nbABRI;
		int tailleIntervalle;
		ArrayList<Noeud> al = new ArrayList<Noeud>();
		ArrayList<Integer> listVal = new ArrayList();
		System.out.println("Nombre d'ABRI ?");
		nbABRI = saisie.nextInt();
		tailleIntervalle = (int)(max-min)/nbABRI;
		for (int i = 0; i<nbABRI; i++){
			ABRI abri = new ABRI();
			//!\---------------------------------------------------
			abri.setMin(min);
			abri.setMax(min + tailleIntervalle);
			al.clear();
			abr2.getRacine().getListNoeud(al);
			for (Noeud n : al){
				if (n.getValeur() <= abri.getMax() && n.getValeur() >= abri.getMin()){
					listVal.add(n.getValeur());
				}
			}
			abri = abri.CreerABRI(min, min + tailleIntervalle, listVal);
			listVal.clear();
			//!\ Probl�me, arbre filiforme
			racine = this.inserer(abri, this.racine);
			min = min + tailleIntervalle + 1;
		}
	}
	
	public void verification(){
		System.out.println("Disjoint : " + racine.verificationDisjoint());
		System.out.println("ABRI : " + racine.verificationABRI(true));
		System.out.println("Borne : " + racine.verificationBorne(true));
	}
}
