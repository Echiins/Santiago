package Classes;

import java.util.ArrayList;

import Reseau.Client;
import Reseau.Serveur;

public final class Santiago {
	private static Santiago INSTANCE = new Santiago();
	private Serveur serveur;
	private  Client client;
	
	public Santiago(){
		this.serveur=new Serveur();
		this.client=null;
	}
	
	public static Santiago getSantiago() {
		return INSTANCE;
	}
	
	public void demarrerServeur(){
		try {
			this.serveur.initServeur();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Santiago getINSTANCE() {
		return INSTANCE;
	}

	public static void setINSTANCE(Santiago iNSTANCE) {
		INSTANCE = iNSTANCE;
	}

	public Serveur getServeur() {
		return serveur;
	}

	public void setServeur(Serveur serveur) {
		this.serveur = serveur;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client2) {
		this.client=client2;
		
	}

	

}
