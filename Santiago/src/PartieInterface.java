
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface PartieInterface extends java.rmi.Remote{

	public void setClient(PartieInterface client) throws RemoteException;
	public ArrayList<PartieInterface> getClient() throws RemoteException;
}
