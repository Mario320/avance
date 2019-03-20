/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class operaciones extends conexion{
    
    public List obtenerventas(){
		/* metodo para listar los datos
		 * */
		List listar_venta = new ArrayList();
			try {
				//La constulta
					String sql = "SELECT * FROM ventas";
				
				 ResultSet rs = null;
				
                                 PreparedStatement pste= getConnection().prepareStatement(sql);
					//PreparedStatement ps = conn.prepareStatement(sql);
				//ejecuto la consulta
					 rs = pste.executeQuery();
				//la recorro
					while(rs.next()){
				//nueva instancia
						beans listar = new beans();
						listar.setId_venta(rs.getInt("id_venta"));
						listar.setId_linea(rs.getInt("id_linea"));
						listar.setFecha_venta(rs.getString("fecha_venta"));
						listar.setDescripcion(rs.getString("descripcion"));
						
						/* on met cette instance dans la liste */
						listar_venta.add(listar);
					}
					/* g�rer les excepions */
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return listar_venta;
	}
    
    
    
    
    public boolean registrar(Integer id_linea,String fecha_venta,String descripcion){
	        
	        
	      /*  System.out.println(nombre1 + "**" + nombre2 + "**" + apellido1 + "**" + apellido2 + "**"+dui+"**"+nit+"**"+departamento+"**"+rol);*/
	        
	        try { 
	            //String sql="INSERT INTO trabajadores(1Nombre, 2Nombre, 1Apellido, 2Apellido, dui, nit, Departamento, rol) VALUES ("+"'"+nombre1+"'"+","+"'"+nombre2+"'"+","+"'"+apellido1+"'"+","+"'"+apellido2+"','"+dui+"','"+nit+"',"+departamento+","+rol+")";
	            String insert="INSERT INTO ventas(id_linea,fecha_venta,descripcion) VALUES (?,?,?)";
	            PreparedStatement pste= getConnection().prepareStatement(insert);
	            pste.setInt(1, id_linea);
	            pste.setString(2,fecha_venta);
                    pste.setString(3, descripcion);
	            
	            

//	       if(pste.executeUpdate()==1){
//	       
//	       return true;
//	       }
	        pste.execute();
	        return true;
	        } catch (Exception e) {
	            
	             System.out.println("" + e.toString());
	        }
	        
	        
	        
	       return  false;  
	        
	    }
    
}
