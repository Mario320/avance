
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="modelo.operaciones" %>
 <%@ page import="modelo.beans" %>
 <%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <title>JSP Page</title>
    </head>
    <body>
<%
	operaciones op = new operaciones();
	List<beans> ventaList = op.obtenerventas();
	Iterator<beans> itr = ventaList.iterator();
        beans ver = null;
%>


<div class="row">
  <div class="col-md-4"></div>
  <div class="col-md-4">

<table class="table table-hover">
<tr>
		<th>id_venta</th>
		<th>id_linea</th>
		<th>fecha_venta</th>
		<th>descripcion</th>
		<th>Modificar</th>
		<th>Elminar</th>
		
	</tr>
	
	
	<tr>
	
	<% 
	
		while(itr.hasNext()) {
                    ver = itr.next();
		%>
		
		<td><%= ver.getId_venta()  %></td>
		<td><%= ver.getId_linea()  %></td>
			<td><%= ver.getFecha_venta()  %></td>
			<td><%= ver.getDescripcion()  %></td>
			
			<td>
                            <form action="insertar" id="login" method="post">
                                <button class="edit"></button>
                                <input type="hidden" name="op" value="editar" >
                                <input type="hidden" name="ID" value="<%= ver.getId_venta() %>" >
                            </form>
                            
                        </td>
                        
                        <td>
                            <form method="POST" action="UserHandler">
                                <button class="effacer" onclick="if(!confirm('Êtes-vous sur de bien vouloir supprimer ce utilisateur ?')) return false;"></button>
                                <input type="hidden" name="action" value="delete" >
                                <input type="hidden" name="ID" value="<%= ver.getId_venta() %>" >
                            </form>
                            
                        </td>
                        
                        
			
	</tr>
	
	<%
			}
		%>	
</table>




</div>
  <div class="col-md-4"></div>
</div>




</body>
</html>
