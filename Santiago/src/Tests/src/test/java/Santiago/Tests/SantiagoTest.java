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
    	Partie partie=new Partie();
    	Joueur joueur1=new Joueur(1,"joueur1","Noire",0);
    	Joueur joueur2=new Joueur(1,"joueur2","Gris",1);
    	Joueur joueur3=new Joueur(1,"joueur3","Violet",2);
    	//Partie.setClient(joueur1);
    	assertEquals(true, partie.getStart());
    	 
     }
    
    /**
     * Test de la méthode phase0()
     * -test pour savoir si il y a bien un distibuteur de canal
     * -test pour savoir si la nombre de tuile total est le meme que la somme des piles: pour 3 et 5 Joueurs
     * -teste pour savoir si la nombre de tuile est correct
     * -test palmier
     */
    
    /**
     * Test de la méthode phase1()
     */
    
    
}
