
import static java.net.InetAddress.getLocalHost;

import java.io.Serializable;
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





public class Joueur implements Serializable{
	
	private int idJoueur;
	private String nomJoueur;
	//INUTIL, il aura toujours son canal
	private int canalperso;
	private boolean canalbleu;
	private int cagnotte;
	private int nbTAG;
	private int rang;
	private boolean estConstructeurdecanal;
	private String couleur;
	
	public Joueur(int idJoueur, String nomJoueur, String couleur, int rang){
		this.idJoueur=idJoueur;
		this.nomJoueur=nomJoueur;
		this.canalperso=1;
		this.canalbleu=true;
		this.cagnotte=10;
		this.nbTAG=22;
		this.rang=rang;
		this.estConstructeurdecanal=false;
		this.couleur=couleur;
	}
	
	public Joueur(){}
	

	public boolean getConstructeur(){
		return this.estConstructeurdecanal;
	}
	public void devenirConstructeur(){
		this.estConstructeurdecanal=true;
	}
	public void nEstPlusConstructeur(){
		this.estConstructeurdecanal=false;
	}
	
	
}

