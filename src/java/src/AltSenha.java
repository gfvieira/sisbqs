package src;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import src.dao.DaoLogin;
import src.modelo.Usuario;

public class AltSenha extends HttpServlet {

    String senha1 = "";
    String senha2 = "";
    Usuario usuario = null;
    DaoLogin daologin = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            usuario = new Usuario();
            Map mapRequest = request.getParameterMap();
            Map.Entry entryRequest;
            Iterator iteratorRequest = mapRequest.entrySet().iterator();
            String key;
            while (iteratorRequest.hasNext()) {
                entryRequest = (Map.Entry) iteratorRequest.next();
                key = (String) entryRequest.getKey();
                if (key.equals("senha1")) {
                    senha1 = request.getParameterValues(key)[0];
                }
                if (key.equals("senha2")) {
                    senha2 = request.getParameterValues(key)[0];
                }
                if (key.equals("nip")) {
                    usuario.setNip(request.getParameterValues(key)[0]);
                }
            }
            if (senha1.equals(senha2)) {
                usuario.setSenha(senha1);
                daologin = new DaoLogin();
                boolean check = daologin.attSenhaUsuario(usuario);
                if (check == true) {
                    usuario.setSenha(senha1);
                    usuario = daologin.loginUsuario(usuario);
                    if (usuario == null) {
                        request.setAttribute("mensagem", "Usuario ou Senha inválida.");
                        RequestDispatcher rd = request.getRequestDispatcher("loginSigbase.jsp");
                        rd.forward(request, response);
                    } else if (usuario.getAtivo() == 0) {
                        request.setAttribute("mensagem", "Este usuario não possui acesso ao sistema.");
                        RequestDispatcher rd = request.getRequestDispatcher("loginSigbase.jsp");
                        rd.forward(request, response);
                    } else {
                        HttpSession session = request.getSession(true);
                        session.setAttribute("usuario", usuario);
                        session.setMaxInactiveInterval(30 * 60);
                        usuario.setIp_access(request.getRemoteAddr());
                        usuario.setId_session(session.getId());
                        daologin.lastAccess(usuario);
                        if (usuario.getPassword() == 1) {
                            response.sendRedirect("redefinir_senha.jsp");
                        } else if (usuario.getPri().equals("ADMIN")) {
                            response.sendRedirect("admin/admin_home.jsp");
                        } else if (usuario.getPri().equals("BH30")) {
                            response.sendRedirect("bhmn/bh30/bh30_home.jsp");
                        } else if (usuario.getPri().equals("CHAPA")) {
                            response.sendRedirect("bhmn/bh30/chapa/bh30_home.jsp");
                        } else if (usuario.getPri().equals("BH31")) {
                            response.sendRedirect("bhmn/bh30/bh31/BH31.jsp?ind=HOME&access=" + usuario.getTypeAccess());
                        } else if (usuario.getPri().equals("BH34")) {
                            response.sendRedirect("bhmn/bh30/bh34/BH34.jsp?ind=HOME&access=" + usuario.getTypeAccess());
                        } else if (usuario.getPri().equals("BH10")) {
                            response.sendRedirect("bhmn/bh10/BH10.jsp?ind=HOME&access=" + usuario.getTypeAccess());
                        } else if (usuario.getPri().equals("BH50")) {
                            response.sendRedirect("bhmn/bh50/BH50.jsp?ind=HOME&access=" + usuario.getTypeAccess());
                        } else if (usuario.getPri().equals("TOTAL")) {
                            response.sendRedirect("bhmn/cmd_home.jsp");
                        } else if (usuario.getPri().equals("BH25")) {
                            response.sendRedirect("bhmn/bh25/Contador25.jsp");
                        } else if (usuario.getPri().equals("IDENTIFICADOR")) {
                            response.sendRedirect("bhmn/identificador/Contador.jsp");
                        } else if (usuario.getPri().equals("BH04")) {
                            response.sendRedirect("bhmn/secom/secom_home.jsp");
                        } else if (usuario.getPri().equals("BH08")) {
                            response.sendRedirect("bhmn/bh08/bh08_home.jsp");
                        } else {
                            /*HttpSession session = request.getSession(true);
                        session.setAttribute("usuario", usuario);
                        session.setMaxInactiveInterval(30*60);
                        response.sendRedirect("home.jsp");
                        HttpSession session = request.getSession(true);
                        session.setAttribute("usuario", usuario);
                        //setting session to expiry in 30 mins
                        session.setMaxInactiveInterval(30 * 60);
                        response.sendRedirect("home.jsp");*/
                        }

                    }
                } else {
                    RequestDispatcher rd = request.getRequestDispatcher("503.html");
                    rd.forward(request, response);
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
