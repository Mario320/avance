

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>   
        
        
        <title>Inicio</title>
        
        
    </head>
    <body>
        <div class="row">
  <div class="col-md-4"></div>
  <div class="col-md-4">
      
      <form action="insertar" id="login" method="post">
            <input type="hidden" name="op" value="ingresar" >
      <div class="form-group has-feedback">
           <SELECT name="sel" class="form-control" >
<OPTION VALUE="1">Linea Blanca </OPTION>
<OPTION VALUE="2">Electronica</OPTION>
<OPTION VALUE="3">Ferreteria</OPTION>
<OPTION VALUE="4">Hogar</OPTION>
</SELECT>
          
          
      </div>
      <div class="form-group has-feedback">
          <input type="text" id="2nombre"  name="fecha" class="form-control" placeholder="fecha">
        <span class="glyphicon glyphicon-user form-control-feedback"></span>
      </div>
       <div class="form-group has-feedback">
          <input type="text" id="1apellido" name="descrpcion"  class="form-control" placeholder="Descripcion">
        <span class="glyphicon glyphicon-user form-control-feedback"></span>
      </div>
    
         
        
     
     
      
                      
      <div class="row">
        <div class="col-xs-8">
          <div class="checkbox icheck">
          
                <%
                    
                       
                   /* 
                  
                    if(request.getParameter("error")!= null){
                        out.println("<div class='alert alert-danger'>");
                        out.print(request.getParameter("error"));
                         out.println("</div>");
                              /*  "<div class='alert alert-danger'>"+"error"+"</div>"));*/
                                
                        /*        
                               
                    
                    }else{
                    out.print("");
                    }
                */
          
                %>
  
          </div>
        </div>
        <!-- /.col -->
        <div class="col-xs-4">
           <!--   <input type="submit" id="agregar" name="agregar">-->
              <button type="submit" id="ir" class="btn btn-primary btn-block btn-flat">Iniciar</button>
            
        </div>
        <!-- /.col -->
      </div>
    </form>
      
      
      
  </div>
  <div class="col-md-4"></div>
</div>
    </body>
</html>
