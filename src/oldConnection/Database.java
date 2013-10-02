/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oldConnection;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Alvaro Monsalve
 */
public class Database {
    private static final Logger logger = Logger.getLogger(Database.class.getName());
    public Connection conexion;
    public PreparedStatement sentencia;
    public ResultSet resultado;
    private String url,pass,user,driver;
    
    public Database(Properties props){
        driver = props.getProperty("javax.persistence.jdbc.driver");
        url = props.getProperty("javax.persistence.jdbc.url"); 
        user = props.getProperty("javax.persistence.jdbc.user"); 
        pass = props.getProperty("javax.persistence.jdbc.password"); 
    }
    
    public void Conectar(){
        try {
            final String Controlador = driver;
            Class.forName( Controlador );
            final String url_bd = url;	
            conexion = (Connection) DriverManager.getConnection(url_bd,user,pass);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "10067:\n"+ex.getMessage(), Database.class.getName(), JOptionPane.INFORMATION_MESSAGE);
        } catch (ClassNotFoundException ex) {     
            JOptionPane.showMessageDialog(null, "10068:\n"+ex.getMessage(), Database.class.getName(), JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void cerrarConexionYStatement(Connection conexion,PreparedStatement... statements) {
        try {
        conexion.close();
        } catch (java.sql.SQLException ex) {
            JOptionPane.showMessageDialog(null, "10016:\n"+ex.getMessage(), Database.class.getName(), JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "10017:\n"+ex.getMessage(), Database.class.getName(), JOptionPane.INFORMATION_MESSAGE);
        }finally{
            for(Statement statement : statements){
                if(statement != null){
                    try {
                        statement.close();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null,"10018:\n "+ex.getMessage(), Database.class.getName(), JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "10019:\n "+ex.getMessage(), Database.class.getName(), JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
    }
    
    public void cerrarResultSet(ResultSet... results){
        for(ResultSet rs: results){
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "10020:\n "+ex.getMessage(), Database.class.getName(), JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "10021:\n "+ex.getMessage(), Database.class.getName(), JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }
    
     public void ConectarBasedeDatos(){
         try {
             final String Controlador = driver;
             Class.forName( Controlador );
             final String url_bd = url;
             conexion = (Connection) DriverManager.getConnection(url_bd,user,pass);
         } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "10010:\n "+ex.getMessage(), Database.class.getName(), JOptionPane.INFORMATION_MESSAGE);
         } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "10009:\n "+ex.getMessage(), Database.class.getName(), JOptionPane.INFORMATION_MESSAGE);
         } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "10011:\n "+ex.getMessage(), Database.class.getName(), JOptionPane.INFORMATION_MESSAGE);
         }
     }
     
     public void DesconectarBasedeDatos() {
        try {
            if (conexion != null ) {
                if(sentencia != null) {
                    sentencia.close();
                }
                conexion.close();
            }
        }
        catch (Exception ex) {
             logger.log(Level.SEVERE,"Error cerrando la sentencia y la conexion de la base de datos", ex);
        }
    }
    
}
