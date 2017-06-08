package src.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import src.Erro;
import src.modelo.Consumo_model;

public class DaoEngenharia {

    private ConnectDataBase connectDataBase = null;
    private Erro log = null;
    private Statement statement;

    public DaoEngenharia() {
        connectDataBase = new ConnectDataBase();
    }

    public boolean insereConsumo(int con, int ano, int mes, int type, String user, String ip) {
        Timestamp tm = new Timestamp(System.currentTimeMillis());
        String t = new SimpleDateFormat("HH:mm:ss").format(tm);
        Date date = new Date();
        LocalTime thisSec = LocalTime.parse(t);
        String temp = "";
        if (type == 1) {
            temp = "consumo_eletrico";
        } else {
            temp = "consumo_agua";
        }
        String insereTableSQL = "INSERT INTO " + temp + " (valor, ano, mes, user_cad, data_cad, hora_cad, ip_cad) "
                + "VALUES ('" + con + "', '" + ano + "', '" + mes + "', '" + user + "',"
                + "'" + date + "', '" + thisSec + "', '" + ip + "');";

        try {
            if (connectDataBase.openConection() == null) {
                return false;
            }
            statement = connectDataBase.openConection().createStatement();
            statement.executeUpdate(insereTableSQL);
            //statement.close();
            //connectDataBase.closeConnection();
            return true;
        } catch (SQLException e) {
            log = new Erro();
            log.Gravar(e.getMessage(), "DaoEngenharia_insereConsumo");
            return false;
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public ArrayList<Consumo_model> exibeConsumo(int type) {
        String temp = "";
        ArrayList<Consumo_model> lista = new ArrayList<>();
        if (type == 1) {
            temp = "consumo_eletrico";
        } else {
            temp = "consumo_agua";
        }
        String selectTableSQL = "select * from " + temp + " ORDER BY ano ASC;";
        try {
            if (connectDataBase.openConection() == null) {
                return null;//criar uma variavel no usauario pra condicao do banco
            }
            statement = connectDataBase.openConection().createStatement();
            //statement = dbConnection.createStatement();       
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                Consumo_model consumo = new Consumo_model();
                consumo.setId(rs.getInt("os_bh34"));
                lista.add(consumo);
            }
            return lista;
        } catch (Exception e) {
            log = new Erro();
            log.Gravar(e.getMessage(), "DaoEngenharia_exibeConsumo");
            return null;
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public ArrayList<Consumo_model> buscaAno(int year, int type) {
        ArrayList<Consumo_model> lista = new ArrayList<>();
        String temp = "";
        if (type == 1) {
            temp = "consumo_eletrico";
        } else {
            temp = "consumo_agua";
        }
        String selectTableSQL = "select * from " + temp + " where ano = " + year + " ORDER BY mes ASC;";
        try {
            if (connectDataBase.openConection() == null) {
                return null;//criar uma variavel no usauario pra condicao do banco
            }
            statement = connectDataBase.openConection().createStatement();
            //statement = dbConnection.createStatement();       
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                Consumo_model consumo = new Consumo_model();
                consumo.setMes(rs.getString("mes"));
                consumo.setConsumo(rs.getInt("valor"));
                lista.add(consumo);
            }
            return lista;
        } catch (Exception e) {
            log = new Erro();
            log.Gravar(e.getMessage(), "DaoEngenharia_buscaAno");
            return null;
        } finally {
            connectDataBase.closeConnection();
        }
    }
}
