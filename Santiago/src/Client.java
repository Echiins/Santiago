
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
	    server.setClient(client);

	    System.out.println("Connection effectuée");

	}
	
}

