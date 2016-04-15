package Reseau;


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

import Classes.*;
import Interface.Partie;
import Interface.PartieInterface;



public class Client {
	
	Joueur joueur;
	
	public static  InetAddress getAddress() throws Exception {
		 Enumeration<NetworkInterface> networkInterfaces = NetworkInterface
                      .getNetworkInterfaces();
              while (networkInterfaces.hasMoreElements()) {
                  NetworkInterface ni = (NetworkInterface) networkInterfaces
                          .nextElement();
                  Enumeration<InetAddress> nias = ni.getInetAddresses();
                  while(nias.hasMoreElements()) {
                      InetAddress ia= (InetAddress) nias.nextElement();
                      if (!ia.isLinkLocalAddress() 
                       && !ia.isLoopbackAddress()
                       && ia instanceof Inet4Address) {
                          return ia;
                      }
                  }
              }
		return null;
	}
	
	public static void main (String[]args) throws Exception{
		
		String myHost= getAddress().getHostAddress();
		
		System.setProperty("java.rmi.server.hostname",myHost);
		
		System.out.println("[JOUEUR]");
		

		Scanner sc=new Scanner(System.in);
		System.out.println("Veuillez indiquez l'adresse du serveur");
		String host = sc.nextLine();

		
	    System.setSecurityManager(new SecurityManager());
	    PartieInterface client=new Partie();
	    PartieInterface server=(PartieInterface)Naming.lookup("rmi://"+host+":5755/jeu");
	    System.out.println("Connection au serveur effectuée");
	    server.setClient(client);

	    
	    //Création joueur + ajout
	    int nbJoueurs=server.getClient().size();
	    if(nbJoueurs<=5){ //il faudrait des verrous !!!!
	    	System.out.println("Bienvenue dans cette partie veuillez indiquer quelques informations:\nPseudo:");
	    	Scanner scNom=new Scanner(System.in);
			String nom = scNom.nextLine();
			System.out.println("Votre couleur sera le "+server.getCouleur(nbJoueurs-1));
			Joueur j=new Joueur(nbJoueurs,nom,server.getCouleur(nbJoueurs-1),nbJoueurs);
			server.addJoueur(j);
			
			//On demande au client s'il veut lancer la partie
			System.out.println("Tapez GO pour commencer la partie");
			while(server.getStart()==false){
				Scanner scGo=new Scanner(System.in);
				String go=scGo.nextLine();
				if(go.equals("GO")){
					System.out.println("........");
					server.lancerLaPartie();
				}
			}

			
			
	    }
	    
	    

	}
	
}

