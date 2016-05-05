package Santiago.Tests;

import java.net.UnknownHostException;
import java.rmi.RemoteException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Santiago.Tests.Classes.Fosse;
import Santiago.Tests.Classes.Joueur;
import Santiago.Tests.Classes.Parcelle;
import Santiago.Tests.Classes.Plateau;
import Santiago.Tests.Interface.Partie;
import Santiago.Tests.Interface.PartieInterface;
import static org.junit.Assert.*;

public class SantiagoTestsPhase5 {

	public SantiagoTestsPhase5() {
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
    
    @Test
    public void testphase5() throws RemoteException, UnknownHostException{
    	Partie p=new Partie();
		Joueur j1=new Joueur(1, "J1", "rouge", 1);
		Joueur j2=new Joueur(2,"J2","brun",2);
		Joueur j3=new Joueur(3,"J3","vert",3);
		p.addJoueur(j1);
		p.addJoueur(j2);
		p.addJoueur(j3);
		Plateau p=new Plateau(1,0,0);
    	p.initliste_parcelles();
    	p.initfosses();
    	p.getFosse(0, 2, "H").setIrrigue(true);;
		p.phase5();
		//Faire un test avec un fosse adjacent, un fosse irrugé
		
		assertEquals(true,p.getFosse(x, y, sens) );
		assertEquals(false,p.getFosse(x, y, sens) );
		assertEquals(true,p.getFosse(x, y, sens).getIrrigue() );
		assertEquas(false, j1.getCanal_bleu());
		
		
		
    	
    
    }
    
    @Test
    public void testFossesIrrigue() throws RemoteException, UnknownHostException{
    	Plateau p=new Plateau(1,0,0);
    	p.initliste_parcelles();
    	p.initfosses();
    	p.getFosse(0, 2, "H").setIrrigue(true);;
    	Fosse f = p.getFosse(1, 3, "V");
    	assertEquals(true, p.getFossesIrrigueAdjacents(f));
    	
    
    }
}
