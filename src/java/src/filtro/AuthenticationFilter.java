package src.filtro;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import src.modelo.Usuario;

@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {

    private ServletContext context;
    private Usuario usuario;

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        if (session != null) {
            usuario = (Usuario) session.getAttribute("usuario"); ///verificar excessao 
        }

        String uri = req.getRequestURI();
        this.context.log("Requested Resource:: " + uri);

        String url = "/sisbqs/";

        boolean check;
        if (uri.equals(url + "index.jsp") //pagina
                || uri.equals(url)//servlet
                || uri.equals(url + "Login.jsp")//servlet
                ) {
            check = true;
        } else if (usuario != null) {
            if ((uri.equals(url + "admin/admin_home.jsp")
                    || uri.equals(url + "admin/admin_cadastro.jsp")
                    || uri.equals(url + "admin/ListarUsuariosAdmin.jsp")
                    || uri.equals(url + "admin/LogOff.jsp")
                    || uri.equals(url + "admin/ExibirUsuarioAdmin.jsp")
                    || uri.equals(url + "admin/AttUsuarioAdmin.jsp")
                    || uri.equals(url + "admin/CadastroAdmin.jsp"))
                    && usuario.getPri().equals("ADMIN")) {
                check = true;
            } else if ((uri.equals(url + "bqs/Calendario.jsp")
                    || uri.equals(url + "bqs/home.jsp")
                    || uri.equals(url + "bqs/aluno/cadastroAluno.jsp")
                    || uri.equals(url + "bqs/aluno/CadAluno.jsp")
                    || uri.equals(url + "bqs/aluno/ListAluno.jsp")
                    || uri.equals(url + "bqs/aluno/ExibeAluno.jsp")
                    || uri.equals(url + "bqs/aluno/AttAluno.jsp")
                    || uri.equals(url + "bqs/aluno/DelAluno.jsp")
                    || uri.equals(url + "bqs/aluno/BuscaAluno.jsp")
                    || uri.equals(url + "bqs/agenda/cadastroAgenda.jsp")
                    || uri.equals(url + "bqs/agenda/CadAgenda.jsp")
                    || uri.equals(url + "bqs/agenda/ListAgenda.jsp")
                    || uri.equals(url + "bqs/agenda/DelAgenda.jsp")
                    || uri.equals(url + "bqs/questao/cadastroQuestao.jsp")
                    || uri.equals(url + "bqs/questao/Disciplina.jsp")
                    || uri.equals(url + "bqs/questao/Assunto.jsp")
                    || uri.equals(url + "bqs/questao/AttDisciplina.jsp")
                    || uri.equals(url + "bqs/questao/AttAssunto.jsp")
                    || uri.equals(url + "bqs/questao/CadQuestao.jsp")
                    || uri.equals(url + "bqs/questao/ListQuestao.jsp")
                    || uri.equals(url + "bqs/simulado/Simulado.jsp")
                    || uri.equals(url + "bqs/simulado/GerarSimulado.jsp")
                    || uri.equals(url + "bqs/simulado/CadSimulado.jsp")
                    || uri.equals(url + "bqs/simulado/CadResultado.jsp")
                    || uri.equals(url + "bqs/simulado/resultadoSimulado.jsp")
                    || uri.equals(url + "bqs/indicador/indicadorDisciplina.jsp")
                    || uri.equals(url + "bqs/indicador/IndicadorDisciplina.jsp")
                    || uri.equals(url + "bqs/GeraQuestao.jsp")
                    )) {
                check = true;
            } else if (uri.equals(url + "redefinir_senha.jsp")
                    || uri.equals(url + "AltSenha.jsp")) {
                check = true;
            } else if (uri.equals(url + "LogOff.jsp")) {
                session.invalidate();
                check = false;
            } else {
                check = false;
            }
        } else {
            check = false;
        }
        if (check == false) {// implementar log de recusa de acesso
            Timestamp tm = new Timestamp(System.currentTimeMillis());
            String d = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss").format(tm);
            this.context.log("====== ACESSO NAO AUTORIZADO PARA IP: " + request.getRemoteAddr() + " "
                    + "AS " + d);
            res.sendRedirect(url + "index.jsp");
        } else {
            // pass the request along the filter chain
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {
        //close any resources here
    }

}
