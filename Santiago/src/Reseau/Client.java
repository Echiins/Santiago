package Reseau;


import static java.net.InetAddress.getLocalHost;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.rmi.Naming;
import java.util.Enumeration;

import Classes.*;
import Interface.Partie;
import Interface.PartieInterface;



public class Client {
	private String host;
	private String pseudo;
	private String password;
	private Joueur joueur;
	
	
	public Client(String host, String pseudo, String password){
		this.host=host;
		this.pseudo=pseudo;
		this.password=password;
	}
	
	//************************************GETTER************************************
	public String getHost() {
		return host;
	}
	public String getPseudo() {
		return pseudo;
	}
	public Joueur getJoueur() {
		return joueur;
	}
	public String getPassword() {
		return password;
	}
	//************************************SETTER************************************
	public void setHost(String host) {
		this.host = host;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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
	
	public  void creerClient() throws Exception{
		
		String myHost= this.host;
		
		System.setProperty("java.rmi.server.hostname",myHost);
		
		System.out.println("[JOUEUR]");

	    System.setSecurityManager(new SecurityManager());
	    PartieInterface client=new Partie();
	    PartieInterface server=(PartieInterface)Naming.lookup("rmi://"+this.host+":5755/jeu");
	    System.out.println("Connection au serveur effectuée");
	    server.setClient(client);

	    
	    //Création joueur + ajout
	    int nbJoueurs=server.getClient().size();
	    if(nbJoueurs<=5){ //il faudrait des verrous !!!!
	    	//System.out.println("Bienvenue dans cette partie veuillez indiquer quelques informations:\nPseudo:");
			System.out.println("Votre couleur sera le "+server.getCouleur(nbJoueurs-1));
			this.joueur=new Joueur(nbJoueurs,this.pseudo,server.getCouleur(nbJoueurs-1),nbJoueurs,this.password);
			server.addJoueur(this.joueur);
			
			/*On demande au client s'il veut lancer la partie
			System.out.println("Tapez GO pour commencer la partie");
			while(server.getStart()==false){
				Scanner scGo=new Scanner(System.in);
				String go=scGo.nextLine();
				if(go.equals("GO")){
					System.out.println("........");
					server.lancerLaPartie();
				}
			}*/

			
			
	    }
	    
	    

	}
	
}

