package controlador;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Contacto;

// Anotación que define este archivo como un Servlet y establece la URL a la que responderá.

@WebServlet(name = "OperacionServlet", urlPatterns = {"/OperacionServlet"})
public class OperacionServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener la acción (eliminar, actualizar, editar) desde el formulario.
        String accion = request.getParameter("accion");
        // Lista donde se almacenan todos los contactos.
        List<Contacto> contactos = (List<Contacto>) getServletContext().getAttribute("contactos");
        //1.delclara una lista de contactos 
        //2.getServletContext(): Este método se invoca sobre el servlet actual y devuelve un objeto ServletContext.
        //El ServletContext es un objeto que existe durante toda la vida útil de la aplicación web y es compartido por todos los servlets
        //y sesiones. Sirve como un contenedor para datos que son globales a toda la aplicación.
        //3.getAttribute("contactos"): Este método se llama sobre el objeto ServletContext y trata de obtener un atributo llamado "contactos"
        //4.(List<Contacto>): Esta parte del código es un cast. Convierte el tipo de objeto que se recupera del ServletContext (que es genéricamente un Object) al tipo específico List<Contacto>.
        //Este casting es necesario porque en Java, los datos obtenidos del ServletContext son de tipo Object por defecto y deben ser convertidos explícitamente al tipo de dato deseado
        //resumen: esta línea recupera una lista de contactos que ha sido previamente almacenada en el contexto de la aplicación web
        
        // Usamos un switch para manejar las diferentes acciones.
        switch (accion) {
            case "eliminar":
                // Si la acción es "eliminar", obtenemos el índice del contacto y lo eliminamos de la lista.
                int indiceEliminar = Integer.parseInt(request.getParameter("indice"));
                contactos.remove(indiceEliminar);
                // Redirigimos al usuario de vuelta a la página principal.
                response.sendRedirect("index.jsp");
                break;
            case "actualizar":
                // Si la acción es "actualizar", obtenemos los datos actualizados del contacto y los modificamos en la lista.
                int indiceActualizar = Integer.parseInt(request.getParameter("indice"));
                String nombre = request.getParameter("nombre");
                String apellido = request.getParameter("apellido");
                String email = request.getParameter("email");

                // Creamos un nuevo objeto Contacto con la información actualizada.
                Contacto contactoActualizado = new Contacto(nombre, apellido, email);
                // Actualizamos el contacto en la lista.
                contactos.set(indiceActualizar, contactoActualizado);

                // Redirigimos al usuario de vuelta a la página principal.
                response.sendRedirect("index.jsp");
                break;
            case "editar":
                // Si la acción es "editar", obtenemos el contacto actual de la lista y preparamos la información para enviarla a la página de edición.
                int indiceEditar = Integer.parseInt(request.getParameter("indice"));
                Contacto contactoAEditar = contactos.get(indiceEditar);
                // Establecemos los atributos que se enviarán a la página de edición.
                request.setAttribute("contacto", contactoAEditar);
                request.setAttribute("indice", indiceEditar);
                // Enviamos al usuario a la página de edición con la información del contacto.
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
        }
    }
}