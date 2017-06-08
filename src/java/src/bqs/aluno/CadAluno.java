package src.bqs.aluno;

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
import src.dao.DaoAluno;
import src.modelo.Aluno;

public class CadAluno extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Aluno aluno = new Aluno();
            Map mapRequest = request.getParameterMap();
            Map.Entry entryRequest;
            Iterator iteratorRequest = mapRequest.entrySet().iterator();
            String key;
            while (iteratorRequest.hasNext()) {
                entryRequest = (Map.Entry) iteratorRequest.next();
                key = (String) entryRequest.getKey();
                if (key.equals("nome")) {
                    aluno.setNome(request.getParameterValues(key)[0].toUpperCase());
                }
                if (key.equals("cpf")) {
                    aluno.setCpf(request.getParameterValues(key)[0]);
                }
                if (key.equals("rg")) {
                    aluno.setRg(request.getParameterValues(key)[0]);
                }
                if (key.equals("mail")) {
                    aluno.setMail(request.getParameterValues(key)[0]);
                }
                if (key.equals("cel")) {
                    aluno.setCel(request.getParameterValues(key)[0]);
                }
                if (key.equals("tel")) {
                    aluno.setTel(request.getParameterValues(key)[0]);
                }
                if (key.equals("user")) {
                    aluno.setUsercad(request.getParameterValues(key)[0]);
                }
            }
            DaoAluno daoaluno = new DaoAluno();
            aluno.setIpcad(request.getRemoteAddr());
            Timestamp tm = new Timestamp(System.currentTimeMillis());
            String da = new SimpleDateFormat("yyyyMMdd").format(tm);
            String t = (da + new SimpleDateFormat("HHmm").format(tm));
            aluno.setMatricula(t);
            boolean check = false;
            Aluno alunoTemp = daoaluno.buscaAlunoCPF(aluno.getCpf());
            if (alunoTemp == null) {
                check = daoaluno.inserAluno(aluno);
                if (check == false) {
                    request.setAttribute("mensagem", "NO");
                } else {
                    check = daoaluno.inserAuditorialuno(aluno, "CADASTRO CONCLUIDO COM SUCESSO");
                    if (check == false) {
                        request.setAttribute("mensagem", "NO");
                    } else {
                        request.setAttribute("mensagem", "OK");
                    }
                }
            } else {
                request.setAttribute("mensagem", "NO");
            }
            RequestDispatcher rd = request.getRequestDispatcher("cadastroAluno.jsp");
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
