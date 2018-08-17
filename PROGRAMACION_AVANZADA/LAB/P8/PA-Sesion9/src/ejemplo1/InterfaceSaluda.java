package ejemplo1;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceSaluda extends Remote
{
    String saludar(String nombre) throws RemoteException;
}
