
public class NomArbreAssociation {

	String nom;
	ABR abr = null;
	ABRI abri = null;
	AABRI aabri = null;
	
	public NomArbreAssociation(ABR arbre, String nom){
		this.abr = arbre;
		this.nom = nom;
	}
	public NomArbreAssociation(ABRI arbre, String nom){
		this.abri = arbre;
		this.nom = nom;
	}
	public NomArbreAssociation(AABRI arbre, String nom){
		this.aabri = arbre;
		this.nom = nom;
	}
	
	public String getNom() {
		return nom;
	}
	
	public Object getArbre() {
		if (abr != null) return abr;
		else if (abri != null) return abri;
		else if (aabri != null) return aabri;
		return null;
	}
}
