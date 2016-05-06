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
import java.util.ArrayList;
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


		
		//Joueurs
		String joueurs_xml="";
		for(Joueur j : p.getJoueurs()){
			joueurs_xml+="<joueur id= '"+j.getId_joueur()+"'>"
							+"<nom_joueur>"+j.getNom_joueur()+"</nom_joueur>"
							+"<mdp>"+j.getPassword()+"</mdp>"
							+"<cagnotte>"+j.getCagnotte()+"</cagnotte>"
							+"<canalPerso>"+j.getCanal_perso()+"</canalPerso>"
							+"<canalBleu>"+j.getCanal_bleu()+"</canalBleu>"
							+"<nbTAG>"+j.getNb_tag()+"</nbTAG>"
							+"<estConstructeurdecanal>"+j.getEst_constructeurdecanal()+"</estConstructeurdecanal>"
							+"<couleur>"+j.getCouleur()+"</couleur>"
							+"<rang>"+j.getRang()+"</rang>"
							+"<montour>"+j.isMontour()+"</montour>"
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
		
		//soudoiements
		String soudoi_xml="<propositions_soudoiement>";
		for (PropositionSoudoiement proposition : p.getSoudoiments()){
			soudoi_xml+="<proposition><idPS>"+proposition.getIdPS()+"</idPS>"+"<montant_prop>"+proposition.getMontant()+"</montant_prop><premier>"+proposition.getPremier().getId_joueur()+"</premier>";
			soudoi_xml+=xml_fosse(proposition.getF());
			soudoi_xml+="<supporters>";
			for(SoutienSoudoiement soutient: proposition.getSupporters()){
				soudoi_xml+="<soutien><idSS>"+soutient.getIdSS()+"</idSS><montant_soudoi>"+soutient.getMontant()+"</montant_soudoi>";
				soudoi_xml+=xml_fosse(soutient.getF());
				soudoi_xml+="<id_supporter>"+soutient.getSupporter().getId_joueur()+"</id_supporter>";
				soudoi_xml+="</soutien>";
			}
			soudoi_xml+="</supporters>";
			soudoi_xml+="<etat>"+proposition.isEtat()+"</etat></proposition>";

		}
		soudoi_xml+="</propositions_soudoiement>";
		
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
			plateau_xml+=xml_fosse(fosse);
		}
			plateau_xml+="</fosses>";
		
		//final
		String partie_xml="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>"
				+ "<Partie>"
					+ "<idPartie>"+p.getIdPartie()+"</idPartie>"
					+"<tour>"+p.getTour()+"</tour>"
					+"<phase>"+p.getPhase()+"</phase>"
					+"<plateau><id_plateau>"+plateau.getIdPlateau()+"</id_plateau>"
						+plateau_xml
					+"</plateau>"
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
					+soudoi_xml
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
	public static String xml_fosse(Fosse fosse){
		return "<fosse>"
				+ "<idFosse>"+fosse.getIdFosse()+"</idFosse>"
				+ "<coorX>"+fosse.getCoorX()+"</coorX>"
				+ "<coorY>"+fosse.getCoorY()+"</coorY>"
				+ "<irrigue>"+fosse.getIrrigue()+"</irrigue>"
				+ "<sens>"+fosse.getSens()+"</sens>"
			+ "</fosse>";
	}
	
	public static void nouveau_score(Partie p) throws IOException, ParserConfigurationException, SAXException, TransformerException{

		try{
			//Si le fichier existe
			FileReader f= new FileReader("SCORES.xml");
			
		}
		catch(FileNotFoundException e){
			//S'il n'existe pas on crée un nouveau fichier xml "vide"
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
		score.setAttribute("id_partie",String.valueOf(p.getIdPartie()));
		
		for (Joueur j : p.getListe_joueurs()){
			Element joueur=document.createElement("joueur");
			joueur.setAttribute("id", String.valueOf(j.getId_joueur()));
			joueur.setTextContent(String.valueOf(j.getScore()));
			score.appendChild(joueur);
		}

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
