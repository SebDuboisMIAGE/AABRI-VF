import java.util.Scanner;


public class Application {

	//public static String path = "C:/Users/Sébastien/Documents/Eclipse/AABRI-V2/src/listeABRI.txt";
	//public static String savePath = "C:/Users/Sébastien/Documents/Eclipse/AABRI-V2/src/saveListeABRI.txt";
	public static String path = "D:/Logiciel/eclipse-standard-kepler-SR1-win32-x86_64/workspace/AABRI/src/listeABRI.txt";
	public static String savePath = "D:/Logiciel/eclipse-standard-kepler-SR1-win32-x86_64/workspace/AABRI/src/saveListeABRI.txt";
	// test ok
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MenuCreer();
	}
	
	public static void MenuCreer(){
		int choix;
		AABRI aabri;
		choix = 0;
		Scanner saisie = new Scanner(System.in);
		System.out.println("\nMenu de Creation\n");
		while(choix != 3){
			System.out.println("1 : Créer depuis un fichier");
			System.out.println("2 : Générer un arbre aléatoirement");
			System.out.println("3 : Quitter");
			choix = saisie.nextInt();
			switch(choix){
			case 1 :
				aabri = new AABRI(path);
				Menu(aabri, saisie);
				break;
			case 2 :
				aabri = new AABRI();
				aabri.genererAabriAleatoire(saisie);
				Menu(aabri, saisie);
				break;
			}
		}
	}
	
	public static void Menu(AABRI abri, Scanner saisie){
		int choix;
		choix = 0;
		while(choix != 9){
			System.out.println("\nMenu principal\n");
			System.out.println("1 : Inserer un element");
			System.out.println("2 : Supprimer un element");
			System.out.println("3 : Afficher l'AABRI");
			System.out.println("4 : Ecrire AABRI dans fichier");
			System.out.println("6 : AABRI => ABR");
			System.out.println("7 : ABR => AABRI");
			System.out.println("8 : Vérifier AABRI");
			System.out.println("9 : Retour");
			choix = saisie.nextInt();
			switch(choix){
			case 1 :
				abri.insererUnElement(saisie);
				break;
			case 2 :
				abri.supprimerUnElement(saisie);
				break;
			case 3 :
				abri.afficherAABRI();
				break;
			case 4 :
				abri.ecrireAABRIFichier(savePath);
				break;
			case 6 :
				ABR abr = new ABR();
				abr.AABRIversABR(abri);
				break;
			case 7 :
				ABR abr2 = new ABR();
				abr2.genererAbrAleatoire();
				System.out.println(abr2.afficherABR());
				AABRI aabri = new AABRI();
				aabri.ABRversAABRI(saisie, abr2);
				aabri.afficherAABRI();
				break;
			case 8 :
				abri.verification();
			}
		}		
	}
}
