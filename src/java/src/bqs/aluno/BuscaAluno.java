package src.bqs.aluno;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import src.dao.DaoAluno;
import src.modelo.Aluno;

/**
 *
 * @author gustavo
 */
public class BuscaAluno extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        DaoAluno daoaluno = new DaoAluno();
        ArrayList<Aluno> l = daoaluno.listarAluno();
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
