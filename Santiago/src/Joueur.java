
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;



public class Joueur {
	private int idJoueur;
	private String nomJoueur;
	//INUTIL, il aura toujours son canal
	private int canalperso;
	private boolean canalbleu;
	private int cagnotte;
	private int nbTAG;
	private int rang;
	private boolean estConstructeurdecanal;
	private Partie partie;

	public Joueur(int idJoueur, String nomJoueur,int canalperso,boolean canalbleu,int cagnotte,int nbTAG, int rang, boolean estConstructeurdecanal,Partie partie){
	this.idJoueur=idJoueur;
	this.nomJoueur=nomJoueur;
	this.canalperso=canalperso;
	this.canalbleu=canalbleu;
	this.cagnotte=cagnotte;
	this.nbTAG=nbTAG;
	this.rang=rang;
	this.estConstructeurdecanal=estConstructeurdecanal;
	this.partie=partie;}
	
	public static void main (String[]args) throws RemoteException, MalformedURLException, NotBoundException, UnknownHostException{
		System.out.println("[JOUEUR]");
		

		Scanner sc=new Scanner(System.in);
		System.out.println("Veuillez indiquez l'adresse du serveur");
		String host = sc.nextLine();

		
	    System.setSecurityManager(new SecurityManager());
	    PartieInterface client=new Partie(1,0,0,new Plateau(1,4,5),new Banque(1));
	    PartieInterface server=(PartieInterface)Naming.lookup("rmi://"+host+":5755/jeu");
	    server.setClient(client);

	    System.out.println("Connection effectuée");

	}
	
}

