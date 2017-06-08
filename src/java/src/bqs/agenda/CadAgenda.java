package src.bqs.agenda;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import src.dao.DaoAgenda;
import src.modelo.Agenda;

public class CadAgenda extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Agenda agenda = new Agenda();
            Map mapRequest = request.getParameterMap();
            Map.Entry entryRequest;
            Iterator iteratorRequest = mapRequest.entrySet().iterator();
            String key;
            while (iteratorRequest.hasNext()) {
                entryRequest = (Map.Entry) iteratorRequest.next();
                key = (String) entryRequest.getKey();
                if (key.equals("assunto")) {
                    agenda.setTitle(request.getParameterValues(key)[0].toUpperCase());
                }
                if (key.equals("dataini")) {
                    agenda.setDataini(request.getParameterValues(key)[0]);
                }
                if (key.equals("datater")) {
                    agenda.setDatater(request.getParameterValues(key)[0]);
                }
                if (key.equals("horaini")) {
                    agenda.setHoraini(request.getParameterValues(key)[0]);
                }
                if (key.equals("horater")) {
                    agenda.setHorater(request.getParameterValues(key)[0]);
                }
                if (key.equals("cor")) {
                    agenda.setBackgroundColor(request.getParameterValues(key)[0]);
                }
                if (key.equals("user")) {
                    agenda.setUser(request.getParameterValues(key)[0]);
                }
            }
            DaoAgenda daoagenda = new DaoAgenda();
            Timestamp tm = new Timestamp(System.currentTimeMillis());
            String da = new SimpleDateFormat("dd").format(tm);
            String t = (da + new SimpleDateFormat("HHmm").format(tm));
            agenda.setId(Integer.parseInt(t));
            agenda.setIp(request.getRemoteAddr());
            boolean check = daoagenda.inserAgenda(agenda);
            if (check == false) {
                request.setAttribute("mensagem", "NO");
            } else {
                check = daoagenda.inserAuditoriaagenda(agenda, "CADASTRO CONCLUIDO COM SUCESSO");
                if (check == false) {
                    request.setAttribute("mensagem", "NO");
                } else {
                    request.setAttribute("mensagem", "OK");
                }
            }
            
            RequestDispatcher rd = request.getRequestDispatcher("cadastroAgenda.jsp");
            rd.forward(request, response);
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
