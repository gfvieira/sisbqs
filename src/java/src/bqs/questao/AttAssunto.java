package src.bqs.questao;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import src.dao.DaoAgenda;
import src.dao.DaoQuestao;
import src.modelo.Agenda;
import src.modelo.DisciplinaModel;

/**
 *
 * @author gustavo
 */
public class AttAssunto extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String dis = request.getParameter("dis").toUpperCase();
        String assun = request.getParameter("assun").toUpperCase();

        DaoQuestao daoquestao = new DaoQuestao();
        daoquestao.insereAssunto(dis, assun);
        ArrayList<DisciplinaModel> l = daoquestao.listarAssunto(dis);
//        ArrayList<DisciplinaModel> l = new ArrayList<>();
//
//        DisciplinaModel c = new DisciplinaModel();
//        c.setId(1);
//        c.setName("teste1");
//        
//        DisciplinaModel d = new DisciplinaModel();
//        d.setId(1);
//        d.setName("teste1");
//
//        l.add(c);
//        l.add(d);
        
        // convert "apps" to "events"		
        String json = new Gson().toJson(l);

        // Write JSON string.
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }

}
