/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.bqs.agenda;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import src.dao.DaoAgenda;
import src.modelo.Agenda;

/**
 *
 * @author gustavo
 */
public class Calendario extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        DaoAgenda daoagenda = new DaoAgenda();
        ArrayList<Agenda> l = daoagenda.listarCalendario();

//        Agenda c = new Agenda();
//        c.setId(1);
//        c.setStart("2016-11-02");
//        c.setEnd("2016-11-07");
//        c.setTitle("Reunião");
//
//        Agenda d = new Agenda();
//        d.setId(2);
//        d.setStart("2016-11-26 10:00");
//        d.setEnd("2016-11-28 10:00");
//        d.setTitle("IAM");
//        d.setBackgroundColor("#378006");
//
//        Agenda f = new Agenda();
//        f.setId(2);
//        f.setStart("2016-11-15 10:00");
//        f.setEnd("2016-11-15 10:30");
//        f.setTitle("Licença");
//        f.setBackgroundColor("#378006");
//
//        Agenda e = new Agenda();
//        e.setId(2);
//        e.setStart("2016-11-15 10:30");
//        e.setTitle("Licença");
//        e.setBackgroundColor("#378006");
//
//        l.add(c);
//        l.add(e);
//        l.add(f);
//        l.add(d);

        // convert "apps" to "events"		
        String json = new Gson().toJson(l);

        // Write JSON string.
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }

}
