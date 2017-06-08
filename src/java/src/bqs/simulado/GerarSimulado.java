package src.bqs.simulado;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import src.dao.DaoSimulado;
import src.modelo.Questao;

public class GerarSimulado extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Map mapRequest = request.getParameterMap();
            Map.Entry entryRequest;
            Iterator iteratorRequest = mapRequest.entrySet().iterator();
            int qtd = Integer.parseInt(request.getParameter("total"));
            String key;
            ArrayList<Integer> listAssunto = new ArrayList<>();
            while (iteratorRequest.hasNext()) {
                entryRequest = (Map.Entry) iteratorRequest.next();
                key = (String) entryRequest.getKey();
                for (int i = 0; i < qtd; i++) {
                    if (key.equals("assunto" + i)) {
                        listAssunto.add(Integer.parseInt(request.getParameterValues(key)[0]));
                    }
                }
            }
            DaoSimulado daosimulado = new DaoSimulado();
            ArrayList<Questao> simulado = new ArrayList<>();
            int questoes = 0;
            for (int assunto : listAssunto) {
                ArrayList<Questao> listQuestao = daosimulado.listarQuestao(assunto);
                Random gerador = new Random();
                questoes += listQuestao.size();
                int r = 50 / listAssunto.size();
                if (r <= listQuestao.size()) {
                    ArrayList<Integer> ran = new ArrayList<>();
                    for (int i = 0; i < r; i++) {
                        int random = gerador.nextInt(listQuestao.size());
                        if (!ran.contains(random)) {
                            simulado.add(listQuestao.get(random));
                            ran.add(random);
                        } else {
                            i--;
                        }
                    }
                } else {
                    for (int i = 0; i < listQuestao.size(); i++) {
                        simulado.add(listQuestao.get(gerador.nextInt(listQuestao.size())));
                    }
                }
            }
            if (simulado.size() != 50 && questoes >= 50) {
                ArrayList<Questao> listQuestao = new ArrayList<>();
                for (int assunto : listAssunto) {
                    listQuestao.addAll(daosimulado.listarQuestao(assunto));
                }
                Random gerador = new Random();
                int r = (50 - simulado.size());
                ArrayList<Integer> ran = new ArrayList<>();
                    for (int i = 0; i < r; i++) {
                        int random = gerador.nextInt(listQuestao.size());
                        if (!ran.contains(random)) {
                            simulado.add(listQuestao.get(random));
                            ran.add(random);
                        } else {
                            i--;
                        }
                    }
                request.setAttribute("mensagem", "OK");
            } else if (simulado.size() == 50) {
                request.setAttribute("mensagem", "OK");
            } else {
                request.setAttribute("mensagem", "O Banco de questão nao possui questoes suficiente!");
            }
            request.setAttribute("simulado", simulado);
            RequestDispatcher rd = request.getRequestDispatcher("exibeSimulado.jsp");
            rd.forward(request, response);
            // criar o inserir no banco com os codigos das questoes criadas depois gera o pdf e o html
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
