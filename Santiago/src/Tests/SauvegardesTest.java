package Tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.Test;

import Classes.Banque;
import Classes.BilletBanque;
import Classes.Fosse;
import Classes.Joueur;
import Classes.Parcelle;
import Classes.PileTuile;
import Classes.Plateau;
import Classes.ProposerMise;
import Classes.TuilePlantation;
import Interface.Partie;
import Sauvegardes.ChargementPartie;
import Sauvegardes.Sauvegarde;

public class SauvegardesTest {

	@Test
	public void test_save_and_load() throws IOException {
		ArrayList<BilletBanque> liste_billets= new ArrayList();
		liste_billets.add(new BilletBanque("bleu",20,15));
		liste_billets.add(new BilletBanque("rose",10,30));
		liste_billets.add(new BilletBanque("gris",5,50));
		ArrayList<Joueur> liste_joueurs=new ArrayList();
		ArrayList<TuilePlantation> liste_tuiles_j1=new ArrayList<TuilePlantation>();
		liste_tuiles_j1.add(new TuilePlantation(1,"banane",2));
		liste_tuiles_j1.add(new TuilePlantation(2,"piment",1));
		Joueur j1=new Joueur(1,"Sarah","rouge","mdp1",1,3,false,35,12,false,false,liste_tuiles_j1);
		Joueur j2=new Joueur(2,"Mouna","vert","mdp2",2,5,false,55,1,false,true,new ArrayList<TuilePlantation>());
		liste_joueurs.add(j1);
		liste_joueurs.add(j2);
		liste_joueurs.add(new Joueur(3,"Julien","bleu","mdp3",3,0,false,35,12,true,true,new ArrayList<TuilePlantation>()));
		
		ArrayList<PileTuile> liste_piles=new ArrayList();
		PileTuile pile1=new PileTuile(1,liste_tuiles_j1);
		PileTuile pile2=new PileTuile(2);
		liste_piles.add(pile1);
		liste_piles.add(pile2);
		
		
		ArrayList<ProposerMise> encheres_courantes=new ArrayList();
		ProposerMise enchere1=new ProposerMise(2,j1);
		ProposerMise enchere2=new ProposerMise(1,50,2,liste_joueurs);
		encheres_courantes.add(enchere1);
		encheres_courantes.add(enchere2);
		ArrayList<Parcelle> liste_parcelles=new ArrayList();
		liste_parcelles.add(new Parcelle(1,2,3,true,false));
		ArrayList<Fosse> liste_fosses=new ArrayList();
		liste_fosses.add(new Fosse(1,2,3,"V",false));
		liste_fosses.add(new Fosse(3,5,9,"H",true));
		Plateau plateau=new Plateau(0,1,2,liste_parcelles,liste_fosses);
		
		Partie p1=new Partie(5,1,2,plateau,new Banque(3,liste_billets),liste_joueurs,true,11,liste_piles,encheres_courantes,0);
		Sauvegarde.savePartie(p1);
		Partie p2=ChargementPartie.loadPartie(5);
		assertEquals(3,p2.getListe_joueurs().size());
		assertEquals(5,p2.getIdPartie());
		assertEquals("mdp1",p2.getListe_joueurs().get(0).getMdp());
		assertEquals(false,p2.getPlateau().getListe_parcelles().get(0).getOccupee());
		assertEquals("banane",p2.getListe_joueurs().get(0).getTuilesjoueur().get(0).getPlante());
		
		
	}

}
