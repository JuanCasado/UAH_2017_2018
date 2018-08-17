package postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sergio
 */
public class PostgreSQL {
 
    private final String url = "jdbc:postgresql://localhost:5432/test";
    private final String user = "postgres";
    private final String password = "sergio";
    private final Logger logger = Logger.getLogger(PostgreSQL.class.getName());
    private Connection conn = null;
 
    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     */
    public Connection connect() { 
        try {
            Class.forName("org.postgresql.Driver").newInstance(); 
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado!!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            System.out.println("Driver no detectado");
        }
 
        return conn;
    }
    
    public void disconnect(){
        try {
            conn.close();
            System.out.println("Desconectado!!");
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se ha podido cerrar la conexión",ex);
        } catch (Exception ex){
            logger.log(Level.WARNING, "Excepción capturada",ex);
        }
    }
    
    public void getAllClientes(){
        try {
            String query = "SELECT * FROM \"Cliente\" ORDER BY nombre;";
            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(query);
            
            System.out.println("Listado de clientes");
            System.out.println("============================");
            while(rs.next()){
                System.out.println("["+rs.getString(1).trim()+"]: "+rs.getString("nombre").trim()+".");
            }
            System.out.println("============================");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            logger.log(Level.WARNING, "SQL Exception", ex);
        }
    }
    
    
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PostgreSQL app = new PostgreSQL();
        app.connect();
        app.getAllClientes();
        app.disconnect();
    }
    
}
