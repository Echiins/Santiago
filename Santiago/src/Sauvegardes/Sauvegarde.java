package Sauvegardes;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import Interface.Partie;
import Classes.*;
public class Sauvegarde {

	String file_name;
	

	public Sauvegarde(String file_name) {
		this.file_name = file_name;
	}
	

	
	public static void savePartie(Partie p) throws IOException{

		//banque
		Banque banque=p.getBanque();
		String billets_xml="";
		for(BilletBanque billet : banque.getBilletsbanques()){
			billets_xml+="<billet><couleur>"+billet.getCouleur()+"</couleur>"
					+"<montant>"+billet.getMontant()+"</montant>"
					+"<nb_billets>"+billet.getNbbillets()+"</nb_billets></billet>";
		}

		
		//Joueurs
		String joueurs_xml="";
		for(Joueur j : p.getJoueurs()){
			joueurs_xml+="<joueur id= '"+j.getId_joueur()+"'>"
							+"<nom_joueur>"+j.getNom_joueur()+"</nom_joueur>"
							+"<mdp>"+j.getMdp()+"</mdp>"
							+"<cagnotte>"+j.getCagnotte()+"</cagnotte>"
							+"<canalPerso>"+j.getCanal_perso()+"</canalPerso>"
							+"<canalBleu>"+j.getCanal_bleu()+"</canalBleu>"
							+"<nbTAG>"+j.getNb_tag()+"</nbTAG>"
							+"<estConstructeurdecanal>"+j.getEst_constructeurdecanal()+"</estConstructeurdecanal>"
							+"<couleur>"+j.getCouleur()+"</couleur>"
							+"<rang>"+j.getRang()+"</rang>"
							+"<montour>"+j.getMontour()+"</montour>"
						+"<tuiles_joueur>";
			for (TuilePlantation tuile : j.getTuilesjoueur()){
				joueurs_xml+=xml_tuile(tuile);
			}
			
			joueurs_xml+="</tuiles_joueur>"
					+"</joueur>";
		}
		
		//piles
		String piles_xml="";
		for (PileTuile pile:p.getListe_piles()){
			piles_xml+="<pile><id_pile>"+pile.getIdPiletuile()+"</id_pile><tuiles>";
			for(TuilePlantation tuile : pile.getTuiles()){
				piles_xml+=xml_tuile(tuile);
			}
			piles_xml+="</tuiles></pile>";
		}
		
		
		//encheres
		String encheres_xml="";
		for( ProposerMise enchere : p.getEncheres_courantes()){
			encheres_xml+="<enchere>"
								+"<id_mise>"+enchere.getIdMise()+"</id_mise>"
								+"<id_joueur>"+enchere.getJoueur().getId_joueur()+"</id_joueur>"
								+"<montant_enchere>"+enchere.getMontant()+"</montant_enchere>"
						+"</enchere>";
		}
		
		

		
		Plateau plateau=p.getPlateau();
		String plateau_xml="<sourceX>"+plateau.getSourceX()+"</sourceX>"
				+ "<sourceY>"+plateau.getSourceY()+"</sourceY>"
				+"<parcelles>";
		for(Parcelle parcelle : plateau.getListe_parcelles()){
			plateau_xml+="<parcelle>"
						+ "<idParcelle>"+parcelle.getIdParcelle()+"</idParcelle>"
						+ "<coorX>"+parcelle.getCoorX()+"</coorX>"
						+ "<coorY>"+parcelle.getCoorY()+"</coorY>"
						+ "<palmier>"+parcelle.getPalmier()+"</palmier>"
						+ "<occupee>"+parcelle.getOccupee()+"</occupee>"
					+ "</parcelle>";
		}
		plateau_xml+="</parcelles><fosses>";
		for(Fosse fosse: plateau.getListe_fosses()){
			plateau_xml+="<fosse>"
					+ "<idFosse>"+fosse.getIdFosse()+"</idFosse>"
					+ "<coorX>"+fosse.getCoorX()+"</coorX>"
					+ "<coorY>"+fosse.getCoorY()+"</coorY>"
					+ "<irrigue>"+fosse.getIrrigue()+"</irrigue>"
					+ "<sens>"+fosse.getSens()+"</sens>"
				+ "</fosse>";
		}
			plateau_xml+="</fosses>";
		
		//final
		String partie_xml="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>"
				+ "<Partie>"
					+ "<idPartie>"+p.getIdPartie()+"</idPartie>"
					+"<tour>"+p.getTour()+"</tour>"
					+"<phase>"+p.getPhase()+"</phase>"
					+"<score>"+p.getScore()+"</score>"
					+"<plateau><id_plateau>"+plateau.getIdPlateau()+"</id_plateau>"
						+plateau_xml
					+"</plateau>"
					+"<banque><id_banque>"+banque.getIdBanque()+"</id_banque>"
						+"<billets>"
							+billets_xml
						+"</billets>"
					+"</banque>"
					+"<joueurs>"
						+joueurs_xml
					+"</joueurs>"
					+"<start>"+p.getStart()+"</start>"
					+"<maxTour>"+p.getMaxTour()+"</maxTour>"
					+"<piles>"
						+piles_xml
					+"</piles>"
					+"<encheresCourantes>"
						+encheres_xml
					+"</encheresCourantes>"
					+"</Partie>";
		
		//on enregistre le fichier
		
		File f= new File("sauvegarde_partie_"+p.getIdPartie()+".xml");
		FileWriter fw=new FileWriter(f);
		fw.write(partie_xml);
		fw.close();

	}
	
	public static String xml_tuile(TuilePlantation tuile){
		return "<tuile>"
				+"<id>"+tuile.getIdPlantation()+"</id>"
				+"<plante>"+tuile.getPlante()+"</plante>"
				+"<tag_necessaires>"+tuile.getTag_necessaires()+"</tag_necessaires>" 
				+"</tuile>";
	}

	
}
