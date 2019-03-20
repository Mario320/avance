/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.sql.*;

public class conexion {
    
    
    
    
    
    
     private String user = "root";
	    private String pass = "";
	    private String hostname = "localhost";
	    private String port = "3306";
	    private String database ="inventario";
	    private String classname ="com.mysql.jdbc.Driver";
	    private String url= "jdbc:mysql://"+hostname+":"+port+"/"+database;
	    private Connection con;
	    
	    public conexion(){ /* conturctor*/
	        try {
	            Class.forName(classname);
	            con = DriverManager.getConnection(url,user,pass);
	            
	        } catch (ClassNotFoundException e) {
	            System.out.println(e.getMessage());
	        }catch(SQLException e){
	            System.out.println(e.getMessage());
	            
	        
	        }
	    
	    }
	    
	    
	    
	    
	    public  Connection getConnection(){
         /* metodo */
return  con; /* retorna objeto*/
}
    
}
