package Santiago.Tests;

import static org.junit.Assert.assertEquals;

import java.net.UnknownHostException;
import java.rmi.RemoteException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Santiago.Tests.Classes.Joueur;
import Santiago.Tests.Interface.Partie;

public class SantiagoTestPhase3 {
	
	public SantiagoTestPhase3() {
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
    public void testPhase3() throws RemoteException, UnknownHostException{
    	Partie p=new Partie();
		Joueur j1=new Joueur(1, "J1", "rouge", 1);
		Joueur j2=new Joueur(2,"J2","brun",2);
		Joueur j3=new Joueur(3,"J3","vert",3);
		p.addJoueur(j1);
		p.addJoueur(j2);
		p.addJoueur(j3);
		p.jouerPhase();
		//assertEquals(3,p.getListe_joueurs().get(1).getCagnotte() );
		//assertEquals(3,p.getListe_joueurs().get(2).getCagnotte() );
		//assertEquals(3,p.getListe_joueurs().get(0).getCagnotte() );
		assertEquals(2,p.getListe_joueurs().get(0).getTuilesjoueur().size());
		assertEquals(4,p.getListe_piles().size());
		assertEquals(40,p.getListe_piles().get(0).getTuiles().size()+p.getListe_piles().get(1).getTuiles().size()+p.getListe_piles().get(2).getTuiles().size()+p.getListe_piles().get(3).getTuiles().size());
		assertEquals(3,p.getEncheres_courantes().size());
		
    }

}
