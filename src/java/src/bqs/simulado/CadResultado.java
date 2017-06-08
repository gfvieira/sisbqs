package src.bqs.simulado;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import src.dao.DaoSimulado;

public class CadResultado extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Map mapRequest = request.getParameterMap();
            Map.Entry entryRequest;
            Iterator iteratorRequest = mapRequest.entrySet().iterator();
            String key;
            String user = "";
            String aluno = "";
            String simulado = "";
            ArrayList<String> listQuestao = new ArrayList<>();
            while (iteratorRequest.hasNext()) {
                entryRequest = (Map.Entry) iteratorRequest.next();
                key = (String) entryRequest.getKey();
                for (int i = 1; i <= 50; i++) {
                    if (key.equals("questao" + i)) {
                        listQuestao.add(request.getParameterValues(key)[0]);
                    }
                }
                if (key.equals("user")) {
                    user = request.getParameterValues(key)[0];
                }
                if (key.equals("simulado")) {
                    simulado = request.getParameterValues(key)[0];
                }
                if (key.equals("aluno")) {
                    aluno = request.getParameterValues(key)[0];
                }
            }
            String ip = request.getRemoteAddr();
            DaoSimulado daosimulado = new DaoSimulado();
            boolean check = false;
            int count = 0;
            for(String questao:listQuestao){
                check = daosimulado.insereResultado(simulado, ++count, aluno, questao, user, ip);
            }
            if (check == false) {
                request.setAttribute("mensagem", "NO");
            } else {
                request.setAttribute("mensagem", "OK");
            }
            RequestDispatcher rd = request.getRequestDispatcher("resultadoSimulado.jsp");
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
