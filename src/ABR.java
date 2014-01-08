import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class ABR {
	
	private Noeud racine;
	
	public Noeud getRacine() {
		return racine;
	}
	
	public void setRacine(Noeud racine) {
		this.racine = racine;
	}
	
	public ABR() {
	}
	
	public Noeud inserer(int x, Noeud a) {
		if (a == null){
			Noeud n = new Noeud(x);
			return n;
		}
		if (x < a.getValeur()) a.setSag(inserer(x, a.getSag()));
		else if (x > a.getValeur()) a.setSad(inserer(x, a.getSad())); 
		return a;
	}
	
	public void AABRIversABR(AABRI abri){
		if(abri.racine != null){
			this.ConstruireABR(abri.racine);
			System.out.println(this.afficherABR());
		}		
		else{
			System.out.println("L'abre est vide");
		}
	}
	
	public void ConstruireABR(ABRI abri){
		this.AjouterDansABR(abri);
		if(abri.getSag() != null)
		{
			ConstruireABR(abri.getSag());
		}
		if(abri.getSad() != null)
		{
			ConstruireABR(abri.getSad());
		}		
	}
	
	public void AjouterDansABR(ABRI abri){
		if(abri.getRacine() != null)
		{
			racine = this.inserer(abri.getRacine().getValeur(), racine);
			if(abri.getRacine().getSag() != null)
			{
				this.ecrireNoeudDansABR(abri.getRacine().getSag());
			}
		    if(abri.getRacine().getSad() != null)
		    {
		    	this.ecrireNoeudDansABR(abri.getRacine().getSad());
		    }
		}
	}
	
	public String afficherABR(){
		return this.ecrireParcoursPrefixeFichier();
	}
	
	public String ecrireParcoursPrefixeFichier()
	{
		String chaine = "";
		if(this.getRacine() != null)
		{
			chaine += racine.getValeur() + ":";
			if(racine.getSag() != null)
			{
				chaine += racine.getSag().ecrireParcoursPrefixeFichier();
			}
		    if(racine.getSad() != null)
		    {
		    	chaine += racine.getSad().ecrireParcoursPrefixeFichier();
		    }
		}
		chaine = chaine.substring(0,chaine.length()-1);
		chaine += "\n";
		return chaine;
	}
	
	public void ecrireNoeudDansABR(Noeud noeud){
		racine = this.inserer(noeud.getValeur(), racine);
		if(noeud.getSag() != null)
		{
			racine = this.inserer(noeud.getSag().getValeur(), racine);
		}
	    if(noeud.getSad() != null)
	    {
	    	racine = this.inserer(noeud.getSad().getValeur(), racine);
	    }
	}

	public ABR genererAbrAleatoire() {
		int nbnoeud = 0;
		int bornemax = 0;
		Set<Integer> hs = new HashSet<Integer>();
		Scanner saisie = new Scanner(System.in);
		System.out.println("Nombre de noeuds ? ");
		nbnoeud = saisie.nextInt();
		System.out.println("Borne max ? ");
		bornemax = saisie.nextInt();
		for(int j = 0; j < nbnoeud; j++){   //Tire notre liste de valeur
			if (!hs.add((int) (Math.random()*bornemax))){
				j--;
			}
		}
		for (int value : hs){
			racine = this.inserer(value, racine);
		}
		return this;
	}
}
