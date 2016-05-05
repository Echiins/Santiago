package Tests;

import java.net.UnknownHostException;
import java.rmi.RemoteException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Classes.Joueur;
import Interface.*;
import static org.junit.Assert.*;

public class SantiagoTestPhase1 {

	public SantiagoTestPhase1() {
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
    * Test si il y a bien autant d'enchere que de joueurs
    * @throws RemoteException
    * @throws UnknownHostException
    */
    @Test
    public void testPhase1() throws RemoteException, UnknownHostException{
    	Partie p=new Partie();
		Joueur j1=new Joueur(1, "J1", "rouge", 1);
		Joueur j2=new Joueur(2,"J2","brun",2);
		Joueur j3=new Joueur(3,"J3","vert",3);
		p.addJoueur(j1);
		p.addJoueur(j2);
		p.addJoueur(j3);
		p.getJoueurs().get(1).setEst_constructeurdecanal(true);
	   	p.phase0();
	   	assertEquals(3,p.getEncheresCourantes().size() );
	  
    }
    
}
