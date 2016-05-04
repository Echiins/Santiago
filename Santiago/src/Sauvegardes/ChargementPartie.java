package Sauvegardes;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import org.w3c.dom.Document;
import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.SAXException;

import Interface.Partie;
import Interface.PartieInterface;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import Classes.*;

public class ChargementPartie {

	/* 
	 * Les clients qui ont voulus accéder à cette partie sont ajoutés à l'arraylist d'interface dans la partie
	 * Dans le même ordre que la liste de joueurs
	 *Sur l'interface ->
	 *Charger partie
	 *On attend le joueur 1 .......
	 *dans qu'on l'a : on attend le joueur 2...
	 *jusqu'à la taille de liste de joueurs
	 *
	 *
	 */
	
	
	public static Partie loadPartie(int id_partie){	
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
		    final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document document= builder.parse(new File("sauvegarde_partie_"+id_partie+".xml"));
			final Element racine = document.getDocumentElement();
			int id =Integer.parseInt(racine.getElementsByTagName("idPartie").item(0).getTextContent());
			int tour =Integer.parseInt(racine.getElementsByTagName("tour").item(0).getTextContent());
			int phase =Integer.parseInt(racine.getElementsByTagName("phase").item(0).getTextContent());
			int score =Integer.parseInt(racine.getElementsByTagName("score").item(0).getTextContent());
			int maxTour =Integer.parseInt(racine.getElementsByTagName("maxTour").item(0).getTextContent());
			boolean start=Boolean.parseBoolean(racine.getElementsByTagName("start").item(0).getTextContent());
				
			//plateau
			final Element plateau=(Element)(racine.getElementsByTagName("plateau").item(0));
			int id_plateau=Integer.parseInt(plateau.getElementsByTagName("id_plateau").item(0).getTextContent());
			int plateau_sourceX=Integer.parseInt(plateau.getElementsByTagName("sourceX").item(0).getTextContent());
			int plateau_sourceY=Integer.parseInt(plateau.getElementsByTagName("sourceY").item(0).getTextContent());
			final Element parcelles = (Element) (plateau.getElementsByTagName("parcelles").item(0));
			final Element fosses=(Element)(plateau.getElementsByTagName("fosses").item(0));
			
			ArrayList<Parcelle> liste_parcelles=new ArrayList();
			ArrayList<Fosse> liste_fosses=new ArrayList();
			for(int i=0;i<parcelles.getChildNodes().getLength();i++){
				Element parcelle = (Element) parcelles.getElementsByTagName("parcelle").item(i);
				int id_parcelle=Integer.parseInt(parcelle.getElementsByTagName("idParcelle").item(0).getTextContent());
				int coorX_parcelle=Integer.parseInt(parcelle.getElementsByTagName("coorX").item(0).getTextContent());
				int coorY_parcelle=Integer.parseInt(parcelle.getElementsByTagName("coorY").item(0).getTextContent());
				boolean palmier= Boolean.parseBoolean(parcelle.getElementsByTagName("palmier").item(0).getTextContent());
				boolean occupee=Boolean.parseBoolean(parcelle.getElementsByTagName("occupee").item(0).getTextContent());
				liste_parcelles.add(new Parcelle(id_parcelle,coorX_parcelle, coorY_parcelle,palmier,occupee));
			}
			for(int i=0;i<fosses.getElementsByTagName("fosse").getLength();i++){
				Element fosse=(Element) fosses.getElementsByTagName("fosse").item(i);
				int id_fosse=Integer.parseInt(fosse.getElementsByTagName("idFosse").item(0).getTextContent());
				int coorX_fosse=Integer.parseInt(fosse.getElementsByTagName("coorX").item(0).getTextContent());
				int coorY_fosse=Integer.parseInt(fosse.getElementsByTagName("coorY").item(0).getTextContent());
				boolean irrigue= Boolean.parseBoolean(fosse.getElementsByTagName("irrigue").item(0).getTextContent());
				String sens=fosse.getElementsByTagName("sens").item(0).getTextContent();
				liste_fosses.add(new Fosse(id_fosse,coorX_fosse, coorY_fosse,sens,irrigue));
			}
			Plateau plateau_de_jeu=new Plateau(id_plateau,plateau_sourceX,plateau_sourceY,liste_parcelles,liste_fosses);

			//banque
			ArrayList<BilletBanque> billets_banque=new ArrayList();
			final Element banque_elt=(Element)racine.getElementsByTagName("banque").item(0);
			int id_banque=Integer.parseInt(banque_elt.getElementsByTagName("id_banque").item(0).getTextContent());
			for (int i=0;i<banque_elt.getElementsByTagName("billet").getLength();i++){
				Element billet= (Element) banque_elt.getElementsByTagName("billet").item(i);
				String couleur=billet.getElementsByTagName("couleur").item(0).getTextContent();
				int montant= Integer.parseInt(billet.getElementsByTagName("montant").item(0).getTextContent());
				int nb_billets= Integer.parseInt(billet.getElementsByTagName("nb_billets").item(0).getTextContent());
				billets_banque.add(new BilletBanque(couleur,montant,nb_billets));
			}
			
			Banque banque=new Banque(id_banque,billets_banque);
			//joueurs
			ArrayList<Joueur>liste_joueurs=new ArrayList();
			final Element joueurs=(Element)racine.getElementsByTagName("joueurs").item(0);
			for (int i=0;i<joueurs.getElementsByTagName("joueur").getLength();i++){
				ArrayList<TuilePlantation> liste_tuiles=new ArrayList();
				//un joueur
				Element joueur=(Element) joueurs.getElementsByTagName("joueur").item(i);
				//id
				int id_joueur=Integer.parseInt(joueur.getAttribute("id"));
				//attributs
				String nom_joueur=joueur.getElementsByTagName("nom_joueur").item(0).getTextContent();
				String mdp=joueur.getElementsByTagName("mdp").item(0).getTextContent();
				int cagnotte=Integer.parseInt(joueur.getElementsByTagName("cagnotte").item(0).getTextContent());
				int canalPerso=Integer.parseInt(joueur.getElementsByTagName("canalPerso").item(0).getTextContent());
				boolean canalBleu=Boolean.valueOf(joueur.getElementsByTagName("canalBleu").item(0).getTextContent());
				int nbTAG=Integer.parseInt(joueur.getElementsByTagName("nbTAG").item(0).getTextContent());
				boolean constructeur=Boolean.parseBoolean(joueur.getElementsByTagName("estConstructeurdecanal").item(0).getTextContent());
				String couleur=joueur.getElementsByTagName("couleur").item(0).getTextContent();
				int rang=Integer.parseInt(joueur.getElementsByTagName("rang").item(0).getTextContent());
				boolean montour=Boolean.parseBoolean(joueur.getElementsByTagName("montour").item(0).getTextContent());

				//tuiles Joueurs
				ArrayList<TuilePlantation> liste_tuiles_joueur=new ArrayList();
				Element tuiles=(Element) joueur.getElementsByTagName("tuiles_joueur").item(0);
				for (int j=0;j<tuiles.getElementsByTagName("tuile").getLength();j++){
					Element tuile=(Element) tuiles.getElementsByTagName("tuile").item(j);
					int id_tuile=Integer.parseInt(tuile.getElementsByTagName("id").item(0).getTextContent());
					String plante=tuile.getElementsByTagName("plante").item(0).getTextContent();
					int tag_necessaires=Integer.parseInt(tuile.getElementsByTagName("tag_necessaires").item(0).getTextContent());
					liste_tuiles_joueur.add(new TuilePlantation(id_tuile,plante,tag_necessaires));
				}
				Joueur j=new Joueur(id_joueur, nom_joueur,couleur,mdp,rang,canalPerso,canalBleu,cagnotte,nbTAG,constructeur,montour,liste_tuiles_joueur);
				liste_joueurs.add(j);

				
			}

			//encheres
			ArrayList<ProposerMise> encheres_courantes=new ArrayList();
			final Element encheres=(Element) racine.getElementsByTagName("encheresCourantes").item(0);
			for (int i=0; i< encheres.getElementsByTagName("enchere").getLength();i++){
				Element enchere=(Element)encheres.getElementsByTagName("enchere").item(i);
				int id_mise=Integer.parseInt(enchere.getElementsByTagName("id_mise").item(0).getTextContent());
				int id_mise_joueur=Integer.parseInt(enchere.getElementsByTagName("id_joueur").item(0).getTextContent());
				int mise=Integer.parseInt(enchere.getElementsByTagName("montant_enchere").item(0).getTextContent());
				encheres_courantes.add(new ProposerMise(id_mise,mise,id_mise_joueur,liste_joueurs));
			}
			
			
			
			
			//piles
			ArrayList<PileTuile> liste_piles=new ArrayList();
			final Element piles=(Element)racine.getElementsByTagName("piles").item(0);
			for (int i=0; i<piles.getElementsByTagName("pile").getLength();i++){
				Element pile=(Element) racine.getElementsByTagName("pile").item(i);
				int id_pile=Integer.parseInt(pile.getElementsByTagName("id_pile").item(0).getTextContent());
				ArrayList<TuilePlantation> liste_pile_tuiles=new ArrayList();
				Element pile_tuiles=(Element) pile.getElementsByTagName("tuiles").item(0);
				for(int j=0;j<pile_tuiles.getElementsByTagName("tuile").getLength(); j++){
					Element tuile=(Element) pile_tuiles.getElementsByTagName("tuile").item(j);
					int id_tuile=Integer.parseInt(tuile.getElementsByTagName("id").item(0).getTextContent());
					String plante=tuile.getElementsByTagName("plante").item(0).getTextContent();
					int tag_necessaires=Integer.parseInt(tuile.getElementsByTagName("tag_necessaires").item(0).getTextContent());
					liste_pile_tuiles.add(new TuilePlantation(id_tuile,plante,tag_necessaires));
				}
				liste_piles.add(new PileTuile(id_pile,liste_pile_tuiles));
			}
			
			
			
			Partie partie=new Partie(id,tour,phase,plateau_de_jeu,banque,liste_joueurs,start,maxTour,liste_piles,encheres_courantes,score);
			partie.getBanque().setPartie(partie);
			return partie;
			
		}
		catch (final ParserConfigurationException e) {
		    e.printStackTrace();
		}
		catch (final SAXException e) {
		    e.printStackTrace();
		}
		catch (final IOException e) {
		    e.printStackTrace();
		}

		return null;

	}
	

}



