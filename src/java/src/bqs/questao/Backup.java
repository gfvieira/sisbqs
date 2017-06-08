package src.bqs.questao;

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
import src.dao.DaoQuestao;
import src.modelo.Questao;

public class Backup extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Questao questao = new Questao();
            Map mapRequest = request.getParameterMap();
            Map.Entry entryRequest;
            Iterator iteratorRequest = mapRequest.entrySet().iterator();
            String key;
            while (iteratorRequest.hasNext()) {
                entryRequest = (Map.Entry) iteratorRequest.next();
                key = (String) entryRequest.getKey();
                if (key.equals("disciplina")) {
                    questao.setDisciplina(Integer.parseInt(request.getParameterValues(key)[0]));
                }
                if (key.equals("assunto")) {
                    questao.setAssunto(Integer.parseInt(request.getParameterValues(key)[0]));
                }
                if (key.equals("origem")) {
                    questao.setOrigem(request.getParameterValues(key)[0]);
                }
                if (key.equals("TxtQuestao")) {
                    questao.setPergunta(request.getParameterValues(key)[0]);
                }
                if (key.equals("A")) {
                    questao.setA(request.getParameterValues(key)[0]);
                }
                if (key.equals("B")) {
                    questao.setB(request.getParameterValues(key)[0]);
                }
                if (key.equals("C")) {
                    questao.setC(request.getParameterValues(key)[0]);
                }
                if (key.equals("D")) {
                    questao.setD(request.getParameterValues(key)[0]);
                }
                if (key.equals("E")) {
                    questao.setE(request.getParameterValues(key)[0]);
                }
                if (key.equals("resposta1")) {
                    questao.setResposta1(request.getParameterValues(key)[0]);
                }
                if (key.equals("resposta2")) {
                    questao.setResposta2(request.getParameterValues(key)[0]);
                }
                if (key.equals("user")) {
                    questao.setUser(request.getParameterValues(key)[0]);
                }
            }
            DaoQuestao daoquestao = new DaoQuestao();
            questao.setIp(request.getRemoteAddr());
            Timestamp tm = new Timestamp(System.currentTimeMillis());
            String da = new SimpleDateFormat("yyyydd").format(tm);
            String t = (da + new SimpleDateFormat("HHmmss").format(tm));
            questao.setCodigo(t);
            boolean check = false;
            Questao questaoTemp = daoquestao.buscaQuestao(questao.getCodigo());
            if (questaoTemp == null) {
                check = daoquestao.insereQuestao(questao);
                if (check == false) {
                    request.setAttribute("mensagem", "NO");
                } else {
                    check = daoquestao.insereAuditoriaQuestao(questao, "CADASTRO CONCLUIDO COM SUCESSO");
                    if (check == false) {
                        request.setAttribute("mensagem", "NO");
                    } else {
                        request.setAttribute("mensagem", "OK");
                    }
                }
            } else {
                request.setAttribute("mensagem", "NO");
            }
            RequestDispatcher rd = request.getRequestDispatcher("cadastroQuestao.jsp");
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
