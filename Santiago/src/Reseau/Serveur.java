package Reseau;

import static java.net.InetAddress.getLocalHost;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Enumeration;
import Classes.*;
import Interface.Partie;

public class Serveur {
	
	private Partie server;
	
	public Serveur(){
		try {
			this.server=new Partie();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	
	public Partie getServer() {
		return server;
	}

	public void setServer(Partie server) {
		this.server = server;
	}

	public static void initServeur()  throws Exception{
		
		String host = "localhost";
        System.out.println("[SERVEUR : "+host+"]");
        LocateRegistry.createRegistry(5755);
        System.setSecurityManager(new SecurityManager());

        System.setProperty("java.rmi.server.hostname",host);
        
        Partie server=new Partie();
        Naming.rebind("rmi://"+host+":5755/jeu",server);

        System.out.println("La partie est automatiquement lancée si 5 joueurs sont connectés. Sinon, vous pouvez jouer à partir de trois joueurs en utilisant la commande GO");

        int i=0;
        while(true){ // ON COMMENCE MAINTENANT LA PARTIE
        if(server.getStart()==false){
        	i++; 
        	if(i==1000000000){System.out.println("En attente de joueurs...");}
        }
        if(server.getStart()){
        	if(server.getMax_tour()==0){
        		if(server.getClient().size()==5){
        			server.setMax_tour(11);
        		}
        		else{
        			server.setMax_tour(9);
        		}
        	}
			//La on est censé commencer la partie avec les 'maxTour' tours, chacun avec les 7 phases
			server.jouerPhase(); //Cette méthode comprendra tout le lancement du jeu
        	server.tourSuivant();
        	if(server.getTour()>server.getMax_tour()){ //le tour 12 correspond au tour de fin de la partie
			//Après les x tours (11 si 5 joueurs sinon 9 tours) : fin de la partie ->
        	
	        	//Ultime secheresse
	        	
	        	//décompte
	        	
	        	//resultats
	        	
	        	//fin partie
        	server.quitterPartie(); // prend en compte la sauvegarde
        	}
        }
        }
	}
}