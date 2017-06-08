/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.bqs.questao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import src.dao.DaoQuestao;
import src.modelo.Questao;

/**
 *
 * @author root
 */
public class GeraQuestao extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            DaoQuestao daoquestao = new DaoQuestao();
            daoquestao.insereDisciplina("DISCIPLINA1");
            daoquestao.insereDisciplina("DISCIPLINA2");
            daoquestao.insereDisciplina("DISCIPLINA3");
            daoquestao.insereDisciplina("DISCIPLINA4");
            daoquestao.insereDisciplina("DISCIPLINA5");
            Random gerador = new Random();
            int pergunta = 0;
            for (int i = 1; i < 6; i++) {
                int disciplina = daoquestao.buscaIdDisciplina("DISCIPLINA" + i);
                for (int k = 1; k < 6; k++) {
                    daoquestao.insereAssunto("" + disciplina, "ASSUNTO" + i + k);
                    int assunto = daoquestao.buscaIdAssunto("ASSUNTO" + i + k);
                    for (int j = 1; j < 11; j++) {
                        Questao questao = new Questao();
                        questao.setDisciplina(disciplina);
                        questao.setAssunto(assunto);
                        questao.setOrigem("QT2016");
                        questao.setPergunta("PERGUNTA " + ++pergunta);
                        questao.setA("RESPOSTA a");
                        questao.setB("RESPOSTA b");
                        questao.setC("RESPOSTA c");
                        questao.setD("RESPOSTA d");
                        questao.setE("RESPOSTA e");
                        questao.setResposta1("RESPOSTA a");
                        questao.setUser("TESTE");
                        questao.setCodigo("" + gerador.nextInt(2016111328));
                        boolean check = daoquestao.insereQuestao(questao);
                    }
                }

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
