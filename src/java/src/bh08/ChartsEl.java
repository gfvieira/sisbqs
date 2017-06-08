package src.bh08;

import com.google.gson.Gson;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import src.dao.DaoEngenharia;
import src.modelo.Consumo_model;
import src.modelo.Consumo_model_charts;

public class ChartsEl extends HttpServlet {

    DaoEngenharia daoengenharia = null;
    ArrayList<Consumo_model> consumo1 = null;
    ArrayList<Consumo_model> consumo2 = null;
    ArrayList<Consumo_model> consumo3 = null;
    ArrayList<Consumo_model> consumo4 = null;

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<Consumo_model_charts> l = new ArrayList();
        daoengenharia = new DaoEngenharia();
        Timestamp tm = new Timestamp(System.currentTimeMillis());
        int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(tm));
        consumo1 = daoengenharia.buscaAno(year--, 1);
        consumo2 = daoengenharia.buscaAno(year--, 1);
        consumo3 = daoengenharia.buscaAno(year--, 1);
        consumo4 = daoengenharia.buscaAno(year--, 1);

        for (int i = 0; i < 12; i++) {
            Consumo_model_charts a = new Consumo_model_charts();
            String mes = "";
            switch (i) {
                case 0:
                    mes = "JAN";
                    break;
                case 1:
                    mes = "FEV";
                    break;
                case 2:
                    mes = "MAR";
                    break;
                case 3:
                    mes = "ABR";
                    break;
                case 4:
                    mes = "MAI";
                    break;
                case 5:
                    mes = "JUN";
                    break;
                case 6:
                    mes = "JUL";
                    break;
                case 7:
                    mes = "AGO";
                    break;
                case 8:
                    mes = "SET";
                    break;
                case 9:
                    mes = "OUT";
                    break;
                case 10:
                    mes = "NOV";
                    break;
                case 11:
                    mes = "DEZ";
                    break;
            }
            a.setPeriodo(mes);
            a.setItem1(consumo4.get(i).getConsumo());
            a.setItem2(consumo3.get(i).getConsumo());
            a.setItem3(consumo2.get(i).getConsumo());
            if (consumo1.size() > i) {
                a.setItem4(consumo1.get(i).getConsumo());
            } else {
                a.setItem4();
            }
            l.add(a);
        }
        // convert "apps" to "events"		
        String json = new Gson().toJson(l);

        // Write JSON string.
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }

}
