package Sauvegardes;


import java.io.BufferedReader;
import java.util.Properties;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.text.AsyncBoxView.ChildState;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

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
	
	
	public static void nouveau_score(Partie p) throws IOException, ParserConfigurationException, SAXException, TransformerException{

		try{
			//Si le fichier existe
			System.out.println("jjjjj");
			FileReader f= new FileReader("SCORES.xml");
			System.out.println("LOL");
			
		}
		catch(FileNotFoundException e){
			//S'il n'existe pas on crée un nouveau fichier xml "vide"
			System.out.println("NEW");
			File f= new File("SCORES.xml");
			FileWriter fw=new FileWriter(f);
			fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?><scores></scores>");
			fw.close();
		}
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		final DocumentBuilder builder = factory.newDocumentBuilder();
		final Document document= builder.parse(new File("SCORES.xml"));
		final Element racine = document.getDocumentElement();
		Element score = document.createElement("score");
		score.setTextContent(String.valueOf(p.getScore()));
		score.setAttribute("id",String.valueOf(p.getIdPartie()));
		racine.appendChild(score);
		
		File f= new File("SCORES.xml");
		FileWriter fw=new FileWriter(f);
		
		
				
		DOMSource domSource = new DOMSource(document); 
		StringWriter writer = new StringWriter(); 
		StreamResult result = new StreamResult(writer); 
		TransformerFactory tf = TransformerFactory.newInstance(); 
		Transformer transformer = tf.newTransformer(); 
		transformer.transform(domSource, result); 
		String stringResult = writer.toString();
 
		fw.write(stringResult);
		
		fw.close();
		
	}
	

	
}
