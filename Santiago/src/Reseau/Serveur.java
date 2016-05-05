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
        	if(server.getMaxTour()==0){
        		if(server.getClient().size()==5){
        			server.setMaxTour(11);
        		}
        		else{
        			server.setMaxTour(9);
        		}
        	}
        	while(true){
	        	if(server.getPhase()==1){
	        		System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-\nTour \n=-=-=-=-=-=-=-=-=-="+server.getTour());
	        		System.out.println("===============\nPhase 1: Mise aux enchï¿½res des tuiles de plantation \n===============");
	        		//retourner les tuiles
	        		int j=0;
	        		while(true){
	        			if(server.getEncheres_courantes().size()==server.getListe_joueurs().size()){
	        				break;
	        			}
	        			else{
	        				j++;
	        				if(j==j+1000000000){System.out.println("En attente d'enchere...");
	        				}
	        			}
	        		}
	        		
	        		
	        	}
        	}
        	
        }
        }
	}
}