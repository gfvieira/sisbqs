package src.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import src.dao.DaoAdmin;
import src.modelo.Usuario;

public class ListarUsuariosAdmin extends HttpServlet {
    
    ArrayList<Usuario> lista = null;
    DaoAdmin daoadmin = null;

    protected void processRequest(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8"); 
        try (PrintWriter out = response.getWriter()) {
            
            daoadmin = new DaoAdmin();
            lista = new ArrayList<>();
            lista = daoadmin.listaUsuario();
            int cont = lista.size();
            req.setAttribute("lista", lista);  
            req.setAttribute("cont", cont);  
            RequestDispatcher rd = req.getRequestDispatcher("admin_listarusuario.jsp");
            rd.forward(req, response);
 
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
