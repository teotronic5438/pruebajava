package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Contacto;

// Anotación que define este archivo como un Servlet y establece la URL a la que responderá.
@WebServlet(name = "AgendaServlet", urlPatterns = {"/AgendaServlet"})
public class AgendaServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recoge los datos del formulario
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        
        // Crea un nuevo contacto con los datos recogidos
        Contacto nuevoContacto = new Contacto(nombre, apellido, email);
        
        // Obtiene la lista de contactos del contexto de la aplicación
        List<Contacto> contactos = (List<Contacto>) getServletContext().getAttribute("contactos");
        if (contactos == null) {
            contactos = new ArrayList<>();
        }
        
        // Añade el nuevo contacto a la lista
        contactos.add(nuevoContacto);
        
        // Guarda la lista actualizada en el contexto de la aplicación
        getServletContext().setAttribute("contactos", contactos);
        
        // Redirige de nuevo al JSP
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}

