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

public class SantiagoTestsPlateau {

	public SantiagoTestsPlateau() {
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
     * Test sur la taille du nombre de parcelles adjacentes
     * @throws RemoteException
     * @throws UnknownHostException
     */
    @Test
    public void testGetParcelle() throws RemoteException, UnknownHostException{
    	Plateau p=new Plateau(1,0,0);
    	p.initliste_parcelles();
    	//p.initfosses();
    	assertEquals(2, p.get(0,1).getIdParcelle());
 
    }
    
    /**
     * Test pour les fosses horizontaux les parcelles adjacentes
     * @throws RemoteException
     * @throws UnknownHostException
     */
    @Test
    public void testGetParcellesadH() throws RemoteException, UnknownHostException{
    	Plateau p=new Plateau(1,0,0);
    	p.initliste_parcelles();
    	//p.initfosses();
    	Fosse fosse=new Fosse(6,1,1,"H",false);
    	Fosse fosse1=new Fosse(1,0,0,"H",false);
    	Fosse fosse2=new Fosse(12,3,0,"H",false);
    	Fosse fosse3=new Fosse(11,2,2,"H",false);
    	assertEquals(2, p.get(0,1).getIdParcelle());
    	assertEquals(4,p.getparcellesAdjacentes(fosse).size());
    	assertEquals(2,p.getparcellesAdjacentes(fosse2).size());
    	assertEquals(11,p.getparcellesAdjacentes(fosse).get(0).getIdParcelle());
    	assertEquals(1,p.getparcellesAdjacentes(fosse1).get(0).getIdParcelle());
    	assertEquals(41,p.getparcellesAdjacentes(fosse2).get(0).getIdParcelle());
    	assertEquals(29,p.getparcellesAdjacentes(fosse3).get(0).getIdParcelle());
    }
    
    /**
     * Test pour les fosses verticaux les parcelles adjacentes
     * @throws RemoteException
     * @throws UnknownHostException
     */
    @Test
    public void testGetParcelleV() throws RemoteException, UnknownHostException{
    	Plateau p=new Plateau(1,0,0);
    	p.initliste_parcelles();
    	//p.initfosses();
    	Fosse fosse=new Fosse(5,0,4,"V",false);
    	Fosse fosse1=new Fosse(12,2,1,"V",false);
    	Fosse fosse2=new Fosse(15,2,4,"V",false);
    	assertEquals(2,p.getparcellesAdjacentes(fosse).size());
    	assertEquals(8,p.getparcellesAdjacentes(fosse).get(0).getIdParcelle());
    	assertEquals(34,p.getparcellesAdjacentes(fosse1).get(0).getIdParcelle());
    	assertEquals(40,p.getparcellesAdjacentes(fosse2).get(0).getIdParcelle());
    	assertEquals(25, p.get(3,0).getIdParcelle());
    }
    
    @Test
    public void testGetFosses() throws RemoteException, UnknownHostException{
    	Plateau p=new Plateau(1,0,0);
    	p.initliste_parcelles();
    	p.initfosses();
    	Parcelle p0=new Parcelle(39,4,6,false);
    	Parcelle p1=new Parcelle(8,2,1,false);
    	//Parcelle p2=new Parcelle(30,3,5,false);
    	assertEquals(12, p.getFosse(2,3,"H").getIdFosse());
    	//assertEquals(2,p.getFossesAdjacents(p0).size());
    	assertEquals(12,p.getFossesAdjacents(p0).get(0).getIdFosse());
    	assertEquals(14,p.getFossesAdjacents(p0).get(1).getIdFosse());
    	assertEquals(25, p.get(3,0).getIdParcelle());
    }
}
