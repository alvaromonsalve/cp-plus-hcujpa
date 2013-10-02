/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oldConnection;

import java.sql.ResultSet;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author Alvaro Monsalve
 */
public class FuncionesBD {
    
    public static long verCambiosListPacientesTriege(Properties props){
        Database bd = new Database(props);
        ResultSet rs = null; 
        long max = 0;
        try {
            bd.ConectarBasedeDatos();
            bd.sentencia = bd.conexion.prepareStatement("SELECT MAX(listadopacientetriage) AS id from config_actualizacion_listas");               
            rs = bd.sentencia.executeQuery();
            if(rs != null){
                while(rs.next()){
                   max=rs.getLong("id");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Error verificando cambios en listado pacientes triage "+ex.getMessage());
        }finally{
            bd.cerrarConexionYStatement(bd.conexion, bd.sentencia);
            bd.cerrarResultSet(rs);
        }   
        
        return max;
        
    }
    
    public static String CaculaEdad(String fecha,Properties props){
        Database bd = new Database(props);
        ResultSet rs = null; 
        String max = "";
        try {
            bd.ConectarBasedeDatos();
            bd.sentencia = bd.conexion.prepareStatement("SELECT calc_edad(?) AS edad");   
            bd.sentencia.setString(1, fecha);
            rs = bd.sentencia.executeQuery();
            if(rs != null){
                while(rs.next()){
                   max=rs.getString("edad");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Error Calculando edad "+ex.getMessage());
        }finally{
            bd.cerrarConexionYStatement(bd.conexion, bd.sentencia);
            bd.cerrarResultSet(rs);
        }   
        return max;
    }
    
    
}
