package src.bqs.indicador;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import src.dao.DaoAluno;
import src.dao.DaoIndicador;
import src.modelo.Aluno;
import src.modelo.AssuntoModel;
import src.modelo.Indicador;

/**
 *
 * @author gustavo
 */
public class IndicadorDisciplina extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String aluno = request.getParameter("aluno");
        int intervalo = Integer.parseInt(request.getParameter("inter"));
        DaoIndicador daoindicador = new DaoIndicador();
        ArrayList<Indicador> temp = new ArrayList<>();
        ArrayList<Indicador> l = new ArrayList<>();
        ArrayList<AssuntoModel> assuntoList = null;
        String codigo = "";
        int count = 0;
        switch (intervalo) {
            case 99:
                assuntoList = daoindicador.listarAssunto(aluno);
                for (AssuntoModel assunto : assuntoList) {
                    l.addAll(daoindicador.listarIndicadorGeral(assunto, aluno));
                }
                break;
            case 12:
                assuntoList = daoindicador.listarCodigoSimuladoASC(aluno);
                codigo = "";
                count = 0;
                for (AssuntoModel assunto : assuntoList) {
                    if (!codigo.equals(assunto.getCodigoSimulado()) & count < 2) {
                        codigo = assunto.getCodigoSimulado();
                        temp.addAll(daoindicador.listarIndicador(assunto, aluno));
                        count++;
                    } else if (codigo.equals(assunto.getCodigoSimulado())) {
                        temp.addAll(daoindicador.listarIndicador(assunto, aluno));
                    }
                }
                break;
            case 13:
                assuntoList = daoindicador.listarCodigoSimuladoASC(aluno);
                codigo = "";
                count = 0;
                for (AssuntoModel assunto : assuntoList) {
                    if (!codigo.equals(assunto.getCodigoSimulado()) & count < 3) {
                        codigo = assunto.getCodigoSimulado();
                        temp.addAll(daoindicador.listarIndicador(assunto, aluno));
                        count++;
                    } else if (codigo.equals(assunto.getCodigoSimulado())) {
                        temp.addAll(daoindicador.listarIndicador(assunto, aluno));
                    }
                }
                break;
            case 14:
                assuntoList = daoindicador.listarCodigoSimuladoASC(aluno);
                codigo = "";
                count = 0;
                for (AssuntoModel assunto : assuntoList) {
                    if (!codigo.equals(assunto.getCodigoSimulado()) & count < 4) {
                        codigo = assunto.getCodigoSimulado();
                        temp.addAll(daoindicador.listarIndicador(assunto, aluno));
                        count++;
                    } else if (codigo.equals(assunto.getCodigoSimulado())) {
                        temp.addAll(daoindicador.listarIndicador(assunto, aluno));
                    }
                }
                break;

            case 21:
                assuntoList = daoindicador.listarCodigoSimuladoDESC(aluno);
                codigo = "";
                count = 0;
                for (AssuntoModel assunto : assuntoList) {
                    if (!codigo.equals(assunto.getCodigoSimulado()) & count < 2) {
                        codigo = assunto.getCodigoSimulado();
                        temp.addAll(daoindicador.listarIndicador(assunto, aluno));
                        count++;
                    } else if (codigo.equals(assunto.getCodigoSimulado())) {
                        temp.addAll(daoindicador.listarIndicador(assunto, aluno));
                    }
                }
                break;
            case 31:
                assuntoList = daoindicador.listarCodigoSimuladoDESC(aluno);
                codigo = "";
                count = 0;
                for (AssuntoModel assunto : assuntoList) {
                    if (!codigo.equals(assunto.getCodigoSimulado()) & count < 3) {
                        codigo = assunto.getCodigoSimulado();
                        temp.addAll(daoindicador.listarIndicador(assunto, aluno));
                        count++;
                    } else if (codigo.equals(assunto.getCodigoSimulado())) {
                        temp.addAll(daoindicador.listarIndicador(assunto, aluno));
                    }
                }
                break;
            case 41:
                assuntoList = daoindicador.listarCodigoSimuladoDESC(aluno);
                codigo = "";
                count = 0;
                for (AssuntoModel assunto : assuntoList) {
                    if (!codigo.equals(assunto.getCodigoSimulado()) & count < 4) {
                        codigo = assunto.getCodigoSimulado();
                        temp.addAll(daoindicador.listarIndicador(assunto, aluno));
                        count++;
                    } else if (codigo.equals(assunto.getCodigoSimulado())) {
                        temp.addAll(daoindicador.listarIndicador(assunto, aluno));
                    }
                }
                break;
            case 1:
                assuntoList = daoindicador.listarCodigoSimuladoDESC(aluno);
                codigo = "";
                count = 0;
                for (AssuntoModel assunto : assuntoList) {
                    if (!codigo.equals(assunto.getCodigoSimulado()) & count < 1) {
                        codigo = assunto.getCodigoSimulado();
                        temp.addAll(daoindicador.listarIndicador(assunto, aluno));
                        count++;
                    } else if (codigo.equals(assunto.getCodigoSimulado())) {
                        temp.addAll(daoindicador.listarIndicador(assunto, aluno));
                    }
                }
                break;
        }
        if (intervalo != 99) {
            ArrayList<String> disciplina = new ArrayList<>();
            for (Indicador indicador : temp) {
                Indicador indicadorResult = new Indicador();
                for (Indicador indicador2 : temp) {
                    if (indicador.getDisciplina().equals(indicador2.getDisciplina())) {
                        indicadorResult.setDisciplina(indicador.getDisciplina());
                        indicadorResult.setAcerto(indicadorResult.getAcerto() + indicador2.getAcerto());
                        indicadorResult.setErro(indicadorResult.getErro() + indicador2.getErro());
                        indicadorResult.setDuvida(indicadorResult.getDuvida() + indicador2.getDuvida());
                        System.out.println(indicador.getDisciplina() + "/" + indicador2.getDisciplina());
                    }
                }
                if (!disciplina.contains(indicador.getDisciplina())) {
                    l.add(indicadorResult);
                    disciplina.add(indicadorResult.getDisciplina());
                }
            }
        }
        String json = new Gson().toJson(l);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }

}
