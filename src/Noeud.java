import java.util.ArrayList;


public class Noeud {
	
	private int valeur;
	private Noeud sag;
	private Noeud sad;
	private String chaine = "";
	
	public Noeud(int valeur) {
		this.valeur = valeur;
		this.sag = null;
		this.sad = null;
	}
	public void setChaine(String chaine) {
		this.chaine = chaine;
	}
	public String getChaine() {
		return chaine;
	}
	public int getValeur() {
		return valeur;
	}
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
	public Noeud getSag() {
		return sag;
	}
	public void setSag(Noeud sag) {
		this.sag = sag;
	}
	public void setSag(int valeur) {
		this.sag = new Noeud(valeur);
	}
	public Noeud getSad() {
		return sad;
	}
	public void setSad(Noeud sad) {
		this.sad = sad;
	}
	public void setSad(int valeur) {
		this.sad = new Noeud(valeur);
	}
	
	public Noeud getMinValue(){
		if(this.getSag() == null){
			return this;
		} else {
			return this.getSag().getMinValue();
		}
	}
	
	public Noeud getMaxValue(){
		if(this.getSad() == null){
			return this;
		} else {
			return this.getSad().getMaxValue();
		}
	}
	
	public void parcoursPrefixe() {
	    System.out.println(this.getValeur());
	    this.chaine += this.getValeur() + ":";
	    if(this.getSag() != null){
	      this.getSag().parcoursPrefixe();
	      this.chaine += this.getSag().getChaine();
	    }
	    if(this.getSad() != null){
	      this.getSad().parcoursPrefixe();
	      this.chaine += this.getSad().getChaine();
	    }
	}
	
	public String ecrireParcoursPrefixeFichier()
	{
		String chaine = "";
		chaine += this.getValeur() + ":";
		if(this.getSag() != null){
		      chaine += this.getSag().ecrireParcoursPrefixeFichier();
		}
	    if(this.getSad() != null){
	    	chaine += this.getSad().ecrireParcoursPrefixeFichier();
	    }
	    return chaine;
	}
	
	public void insererElementABR(ABR abr)
	{
		abr.inserer(getValeur(), abr.getRacine());
		if(this.getSag() != null){
		      this.getSag().insererElementABR(abr);
		}
	    if(this.getSad() != null){
	    	this.getSad().insererElementABR(abr);
	    }
	}

	public ArrayList getListNoeud(ArrayList ar) {
		ar.add(this);
		if (getSag() != null) sag.getListNoeud(ar);
		if (getSad() != null) sad.getListNoeud(ar);
		return ar;
	}

	@Override
	public String toString() {
		return "Noeud [valeur=" + valeur + "]";
	}
	public boolean verificationABRI(Boolean bool) {
		if (!bool) return false;
		if (sag != null) sag.verificationABRI(bool);
		if (sad != null) sad.verificationABRI(bool);
		if (sag != null && valeur > sag.valeur) return false;
		if (sad != null && valeur < sad.valeur) return false;
		return true;
	}
	public boolean verificationBorne(boolean bool, int min, int max) {
		if (!bool) return false;
		if (sag != null) sag.verificationBorne(bool, min, max);
		if (sad != null) sad.verificationBorne(bool, min, max);
		if (min > valeur && valeur > max) return false;
		return true;
	}
}
