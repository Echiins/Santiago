
import static java.net.InetAddress.getLocalHost;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Enumeration;
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
	
	public Joueur(int idJoueur, String nomJoueur,int canalperso,boolean canalbleu,int cagnotte,int nbTAG, int rang, boolean estConstructeurdecanal){
		this.idJoueur=idJoueur;
		this.nomJoueur=nomJoueur;
		this.canalperso=canalperso;
		this.canalbleu=canalbleu;
		this.cagnotte=cagnotte;
		this.nbTAG=nbTAG;
		this.rang=rang;
		this.estConstructeurdecanal=estConstructeurdecanal;
	}
	
	public Joueur(){}
	

	
	
	
}

