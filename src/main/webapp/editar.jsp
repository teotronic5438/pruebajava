<%-- 
    Document   : editar
    Created on : 07/12/2023, 21:23:39
    Author     : elias
--%>



<%@page import="modelo.Contacto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Editar Contacto</title>
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-4">
            <h2>Editar Contacto</h2>
            <!-- Formulario con clases de Bootstrap -->
            <form action="OperacionServlet" method="post" class="mt-3">
                <input type="hidden" name="accion" value="actualizar">
                <input type="hidden" name="indice" value="<%= request.getAttribute("indice")%>">

                <div class="form-group">
                    <label>Nombre:</label>
                    <input type="text" class="form-control" name="nombre" value="<%= ((Contacto) request.getAttribute("contacto")).getNombre()%>">
                </div>

                <div class="form-group">
                    <label>Apellido:</label>
                    <input type="text" class="form-control" name="apellido" value="<%= ((Contacto) request.getAttribute("contacto")).getApellido()%>">
                </div>

                <div class="form-group">
                    <label>Email:</label>
                    <input type="email" class="form-control" name="email" value="<%= ((Contacto) request.getAttribute("contacto")).getEmail()%>">
                </div>

                <button type="submit" class="btn btn-primary">Actualizar Contacto</button>
            </form>
        </div>
    </body>
</html>

