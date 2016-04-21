package Santiago.Tests;

import java.net.UnknownHostException;
import java.rmi.RemoteException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Santiago.Tests.Classes.Joueur;
import Santiago.Tests.Interface.Partie;
import Santiago.Tests.Interface.PartieInterface;
import static org.junit.Assert.*;

public class SantiagoTest {

	public SantiagoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    
    /**
     * Test de la méthode SetClient Pour:
     * -pas assez de joueur
     * -assez de joueur
     * -trop de joueuer
     * @throws UnknownHostException 
     * @throws RemoteException 
     */
    @Test
     public void testSetClient() throws RemoteException, UnknownHostException {
    	/*Partie partie=new Partie();
    	Joueur joueur1=new Joueur(1,"joueur1","Noire",0);
    	Joueur joueur2=new Joueur(1,"joueur2","Gris",1);
    	Joueur joueur3=new Joueur(1,"joueur3","Violet",2);
    	Joueur joueur4=new Joueur(1,"joueur4","Beige",1);
    	Joueur joueur5=new Joueur(1,"joueur5","Rouge",2);
    	PartieInterface partie1=new Partie();
    	PartieInterface partie2=new Partie();
    	PartieInterface partie3=new Partie();
    	PartieInterface partie4=new Partie();
    	PartieInterface partie5=new Partie();
    	partie.setClient(partie1);
    	partie.setClient(partie2);
    	partie.setClient(partie3);
    	partie.setClient(partie4);
    	partie.setClient(partie5);
    	assertEquals(true, partie.getStart()); 
     */}
    
    /**
     * Test de la méthode Lancerlapartie Pour:
     * le nombre de joueurs adequats
     * @throws UnknownHostException 
     * @throws RemoteException 
     */
    @Test
    public void testLancerlapartie() throws RemoteException, UnknownHostException {
   	/*Partie partie=new Partie();
   	Partie partie01=new Partie();
   	Joueur joueur1=new Joueur(1,"joueur1","Noire",0);
   	Joueur joueur2=new Joueur(1,"joueur2","Gris",1);
   	Joueur joueur3=new Joueur(1,"joueur3","Violet",2);
   	PartieInterface partie1=new Partie();
   	PartieInterface partie2=new Partie();
   	PartieInterface partie3=new Partie();
   	partie.setClient(partie1);
   	partie.setClient(partie2);
   	partie.setClient(partie3);
   	partie.lancerLaPartie();
   	
	partie01.setClient(partie1);
   	partie01.setClient(partie2);
   	assertEquals(true, partie.getStart()); 
   	assertEquals(false, partie01.getStart()); 
    */}
    
    /**
     * Test de la méthode phase0()
     * -test pour savoir si il y a bien un distibuteur de canal
     * -test pour savoir si la nombre de tuile total est le meme que la somme des piles: pour 3 et 5 Joueurs
     * -teste pour savoir si la nombre de tuile est correct
     * -test palmier
     */
    @Test
    public void testPhase0() throws RemoteException, UnknownHostException {
   	/*Partie partie=new Partie();
   	Partie partie01=new Partie();
   	Joueur joueur1=new Joueur(1,"joueur1","Noire",0);
   	Joueur joueur2=new Joueur(2,"joueur2","Gris",1);
   	Joueur joueur3=new Joueur(1,"joueur3","Violet",2);

	Joueur joueur4=new Joueur(1,"joueur4","Beige",1);
	Joueur joueur5=new Joueur(1,"joueur5","Rouge",2);
   	
   	partie.addJoueur(joueur1);	
   	partie.addJoueur(joueur2);
   	partie.addJoueur(joueur3);
   	partie.addJoueur(joueur4);
   	partie.addJoueur(joueur5);
	partie01.addJoueur(joueur1);	
   	partie01.addJoueur(joueur2);
   	partie01.addJoueur(joueur3);
   	PartieInterface partie1=new Partie();
   	PartieInterface partie2=new Partie();
   	PartieInterface partie3=new Partie();
   	partie.setClient(partie1);
   	partie.setClient(partie2);
   	partie.setClient(partie3);
   	partie.lancerLaPartie();
   	partie.jouerPhase();
   	partie01.lancerLaPartie();
   	partie01.jouerPhase();
   	assertEquals(true, partie.getStart());
   	assertEquals(44, (partie01.getListe_piles().get(0).getTuiles().size()
				+ partie01.getListe_piles().get(1).getTuiles().size()
				+ partie01.getListe_piles().get(2).getTuiles().size()
				+ partie01.getListe_piles().get(3).getTuiles().size()));

   	assertEquals(45, (partie.getListe_piles().get(0).getTuiles().size()
   					+partie.getListe_piles().get(1).getTuiles().size()
   					+partie.getListe_piles().get(2).getTuiles().size()
   					+partie.getListe_piles().get(3).getTuiles().size()
   					+partie.getListe_piles().get(4).getTuiles().size()));
    */}
    
    /**
     * Test de la méthode phase0()
     * -test pour savoir si il y a bien un distibuteur de canal
     * -test pour savoir si la nombre de tuile total est le meme que la somme des piles: pour 3 et 5 Joueurs
     * -teste pour savoir si la nombre de tuile est correct
     * -test palmier
     */
    @Test
    public void testresetOrdre() throws RemoteException, UnknownHostException {
   /*	Partie partie=new Partie();
   	
   	Joueur joueur1=new Joueur(1,"joueur1","Noire",1);
   	Joueur joueur2=new Joueur(2,"joueur2","Gris",2);
   	Joueur joueur3=new Joueur(3,"joueur3","Violet",3);
	Joueur joueur4=new Joueur(4,"joueur4","Beige",4);
	Joueur joueur5=new Joueur(5,"joueur5","Rouge",5);
   	
   	partie.addJoueur(joueur1);	
   	partie.addJoueur(joueur2);
   	partie.addJoueur(joueur3);
   	partie.addJoueur(joueur4);
   	partie.addJoueur(joueur5);
	
   	partie.getJoueurs().get(1).devenirConstructeur();
   	partie.resetOrdre(partie.getJoueurs().get(2).getId_joueur());
   	assertEquals(5, partie.getJoueurs().get(2).getRang());
   */
    }
   
    @Test
    public void testPhase1() throws RemoteException, UnknownHostException{
    	Partie p=new Partie();
		Joueur j1=new Joueur(1, "J1", "rouge", 1);
		Joueur j2=new Joueur(2,"J2","brun",2);
		Joueur j3=new Joueur(3,"J3","vert",3);
		p.addJoueur(j1);
		p.addJoueur(j2);
		p.addJoueur(j3);
		p.getJoueurs().get(1).Est_constructeurdecanal();
	   	p.phase0();
	   	assertEquals(3, p.getConstructeur().getId_joueur());
	  
    }
    /*
    public void testPhase2() throws RemoteException, UnknownHostException{
    	Partie p=new Partie();
		Joueur j1=new Joueur(1, "J1", "rouge", 1);
		Joueur j2=new Joueur(2,"J2","brun",2);
		Joueur j3=new Joueur(3,"J3","vert",3);
		p.addJoueur(j1);
		p.addJoueur(j2);
		p.addJoueur(j3);
		p.phase0();
		p.phase1();
		
		
    }*/
    
    
}
