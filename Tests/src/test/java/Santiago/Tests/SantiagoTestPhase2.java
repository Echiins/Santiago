package Santiago.Tests;

import java.net.UnknownHostException;
import java.rmi.RemoteException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Santiago.Tests.Classes.Joueur;
import Santiago.Tests.Classes.ProposerMise;
import Santiago.Tests.Interface.Partie;
import Santiago.Tests.Interface.PartieInterface;
import static org.junit.Assert.*;

public class SantiagoTestPhase2 {

	public SantiagoTestPhase2() {
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
    * Test si le nouveau constructeur de can est bien redeffinit
    * @throws RemoteException
    * @throws UnknownHostException
    */
    @Test
    public void testPhase2() throws RemoteException, UnknownHostException{
    	Partie p=new Partie();
		Joueur j1=new Joueur(1, "J1", "rouge", 1);
		Joueur j2=new Joueur(2,"J2","brun",2);
		Joueur j3=new Joueur(3,"J3","vert",3);
		p.addJoueur(j1);
		p.addJoueur(j2);
		p.addJoueur(j3);
		p.getJoueurs().get(2).setEst_constructeurdecanal(true);
		p.jouerPhase();
	   	assertEquals(3, p.getConstructeur().getId_joueur());
	  
    }
    
}
