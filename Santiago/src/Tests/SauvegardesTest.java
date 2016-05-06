package Tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import Classes.Fosse;
import Classes.Joueur;
import Classes.Parcelle;
import Classes.PileTuile;
import Classes.Plateau;
import Classes.ProposerMise;
import Classes.PropositionSoudoiement;
import Classes.SoutienSoudoiement;
import Classes.TuilePlantation;
import Interface.Partie;
import Sauvegardes.ChargementPartie;
import Sauvegardes.Sauvegarde;

public class SauvegardesTest {
	
	@Test
	public void test_save_and_load() throws IOException {
		ArrayList<Joueur> liste_joueurs=new ArrayList();
		ArrayList<TuilePlantation> liste_tuiles_j1=new ArrayList<TuilePlantation>();
		liste_tuiles_j1.add(new TuilePlantation(1,"banane",2));
		liste_tuiles_j1.add(new TuilePlantation(2,"piment",1));
		Joueur j1=new Joueur(1,"Sarah","rouge","mdp1",1,3,false,35,12,false,false,liste_tuiles_j1,0);
		Joueur j2=new Joueur(2,"Mouna","vert","mdp2",2,5,false,55,1,false,true,new ArrayList<TuilePlantation>(),0);
		Joueur j3=new Joueur(3,"Julien","bleu","mdp3",3,0,false,35,12,true,true,new ArrayList<TuilePlantation>(),0);
		liste_joueurs.add(j1);
		liste_joueurs.add(j2);
		liste_joueurs.add(j3);
		
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
		Fosse fosse1= new Fosse(1,2,3,"V",false);
		Fosse fosse2=new Fosse(3,5,9,"H",true);
		liste_fosses.add(fosse1);
		liste_fosses.add(fosse2);
		Plateau plateau=new Plateau(0,1,2,liste_parcelles,liste_fosses);
		
		ArrayList<SoutienSoudoiement> liste_soutiens1=new ArrayList<SoutienSoudoiement>();
		SoutienSoudoiement soutien1=new SoutienSoudoiement(3,j2,10, fosse1);
		SoutienSoudoiement soutien2=new SoutienSoudoiement(4,j3,5, fosse1);
		liste_soutiens1.add(soutien1);
		liste_soutiens1.add(soutien2);
		PropositionSoudoiement propo1= new PropositionSoudoiement(1, 20, j1,fosse1,liste_soutiens1,true);
		PropositionSoudoiement propo2= new PropositionSoudoiement(2, 40, j2,fosse2,new ArrayList<SoutienSoudoiement>(),false);

		ArrayList<PropositionSoudoiement> liste_soudoi=new ArrayList();
		liste_soudoi.add(propo1);
		liste_soudoi.add(propo2);
		
		Partie p1=new Partie(5,1,2,plateau,liste_joueurs,true,11,liste_piles,encheres_courantes,liste_soudoi);
		Sauvegarde.savePartie(p1);
		Partie p2=ChargementPartie.loadPartie(5);
		assertEquals(3,p2.getListe_joueurs().size());
		assertEquals(5,p2.getIdPartie());
		assertEquals("mdp1",p2.getListe_joueurs().get(0).getPassword());
		assertEquals(false,p2.getPlateau().getListe_parcelles().get(0).getOccupee());
		assertEquals("banane",p2.getListe_joueurs().get(0).getTuilesjoueur().get(0).getPlante());
		
		//+ vérification à l'oeil du fichier
	}
	
	/*
	@Test
	public void score() throws IOException, ParserConfigurationException, SAXException, TransformerException{

		Joueur j1=new Joueur(1,"Sarah","rouge","mdp1",1,3,false,35,12,false,false,new ArrayList<TuilePlantation>(),213);
		Joueur j2=new Joueur(2,"Mouna","vert","mdp2",2,5,false,55,1,false,true,new ArrayList<TuilePlantation>(),100);
		Joueur j3=new Joueur(3,"Julien","bleu","mdp3",3,0,false,35,12,true,true,new ArrayList<TuilePlantation>(),15);
		ArrayList<Joueur> liste_joueurs=new ArrayList();
		liste_joueurs.add(j1);
		liste_joueurs.add(j2);
		liste_joueurs.add(j3);
		Partie p1=new Partie(-1,1,2,null,liste_joueurs,true,11,null,null,null);

		Sauvegarde.nouveau_score(p1);
		
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		final DocumentBuilder builder = factory.newDocumentBuilder();
		final Document document= builder.parse(new File("SCORES.xml"));
		final Element racine = document.getDocumentElement();
		int score1=-1;
		int score2=-1;
		int score3=-1;
		
		for(int i=0; i<racine.getElementsByTagName("score").getLength();i++){
			System.out.println(racine.getElementsByTagName("score").getLength());
			Element e=((Element) racine.getElementsByTagName("score").item(i));
			System.out.println(e.getAttribute("id_partie"));
			if (e.getAttribute("id_partie").equals("-1")){

				score1=Integer.parseInt(e.getElementsByTagName("joueur").item(0).getTextContent());
				score2=Integer.parseInt(e.getElementsByTagName("joueur").item(1).getTextContent());
				score3=Integer.parseInt(e.getElementsByTagName("joueur").item(2).getTextContent());
			}
		assertEquals(j1.getScore(),score1);
		assertEquals(j2.getScore(),score2);
		assertEquals(j3.getScore(),score3);
	
		
		}
	}
	*/
}
