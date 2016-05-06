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
import java.util.ArrayList;
import java.util.Enumeration;

import Classes.*;
import Interface.Partie;

public class Serveur {
	String host;
	
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
		
		String host = getAddress().getHostAddress();
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
	        	if(server.getPhase()==1){
	        		System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-\nTour \n=-=-=-=-=-=-=-=-=-="+server.getTour());
	        		System.out.println("===============\nPhase 1: Mise aux enchï¿½res des tuiles de plantation \n===============");
	        		//RETOURNER PILE
	        		for(int p=0; p<server.getListe_piles().size();p++){
	        			server.getListe_piles().get(p).getTuiles().get(0).setVisible(true);
	        		}
	        			
	        		
	        		int j=0;
	        		while(true){
	        				j++;
	        				if(j==1000000000){System.out.println("En attente d'enchere...");
	        			if(server.getEncheres_courantes().size()==server.getListe_joueurs().size()){
	        				System.out.println("toutes les encheres recue");
	        				server.phaseSuivante();
	        				break;
	        				}
	        			}
	        	}
        	}
	        	if(server.getPhase()==2){
	        		server.jouerPhase();
	        		server.phaseSuivante();
	        		break;
	        	}
	        	
	        	if(server.getPhase()==4){
	        		//controller, changer les constructeur et ejt canaux
	        		server.jouerPhase();
	        		break;
	        	}
	        	
	        	if(server.getPhase()==5){
	        		server.jouerPhase();
	        		break;
	        	}
	        	
        }
        }
	}
}