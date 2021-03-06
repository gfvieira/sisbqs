package src.bh08;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import src.dao.DaoEngenharia;

public class CadConsumo extends HttpServlet {

    int con;
    int ano;
    int mes;
    int type;
    String user;
    String ip;
    DaoEngenharia daoengenharia = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Map mapRequest = request.getParameterMap();
            Map.Entry entryRequest;
            Iterator iteratorRequest = mapRequest.entrySet().iterator();
            String key;
            while (iteratorRequest.hasNext()) {
                entryRequest = (Map.Entry) iteratorRequest.next();
                key = (String) entryRequest.getKey();
                if (key.equals("con")) {
                    con = Integer.parseInt(request.getParameterValues(key)[0]);
                }
                if (key.equals("ano")) {
                    ano = Integer.parseInt(request.getParameterValues(key)[0]);
                }
                if (key.equals("mes")) {
                    mes = Integer.parseInt(request.getParameterValues(key)[0]);
                }
                if (key.equals("type")) {
                    type = Integer.parseInt(request.getParameterValues(key)[0]);
                }if (key.equals("user")) {
                    user = request.getParameterValues(key)[0];
                }
            }
            ip = request.getRemoteAddr();
            daoengenharia = new DaoEngenharia();
            boolean check = daoengenharia.insereConsumo(con, ano, mes, type, user, ip);
            if (check == true) {
                request.setAttribute("mensagem", "OK");
                RequestDispatcher rd = request.getRequestDispatcher("bh08_inserir.jsp");
                rd.forward(request, response);
            } else {
                request.setAttribute("mensagem", "NO");
                RequestDispatcher rd = request.getRequestDispatcher("bh08_inserir.jsp");
                rd.forward(request, response);
            }

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
