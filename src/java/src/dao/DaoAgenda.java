/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import src.modelo.Agenda;
import src.modelo.Aluno;

/**
 *
 * @author root
 */
public class DaoAgenda {

    private ConnectDataBase connectDataBase = null;
    private Statement statement;

    public DaoAgenda() {
        connectDataBase = new ConnectDataBase();
    }

    public Agenda buscaAgenda(String matricula) {
//        String selectTableSQL = "SELECT * from evento WHERE atv = '" + matricula + "';";
//        Aluno alunoRetorno = null;
//        try {
//            if (connectDataBase.openConection() == null) {
//                return null;//criar uma variavel no usauario pra condicao do banco
//            }
//            statement = connectDataBase.openConection().createStatement();
//            //statement = dbConnection.createStatement();       
//            ResultSet rs = statement.executeQuery(selectTableSQL);
//            while (rs.next()) {
//                alunoRetorno = new Aluno();
//                alunoRetorno.setId(rs.getInt("id_aluno"));
//                alunoRetorno.setMatricula(rs.getString("matricula"));
//                alunoRetorno.setNome(rs.getString("nome"));
//                alunoRetorno.setCpf(rs.getString("cpf"));
//                alunoRetorno.setRg(rs.getString("rg"));
//                alunoRetorno.setMail(rs.getString("mail"));
//                alunoRetorno.setCel(rs.getString("cel"));
//                alunoRetorno.setTel(rs.getString("tel"));
//            }
//            return alunoRetorno;
//        } catch (Exception e) {
//            Erro erroLog = new Erro();
//            erroLog.Gravar(e.getMessage(), "DaoAluno", "buscaAluno");
//            return null;
//        } finally {
//            connectDataBase.closeConnection();
//        }
        return null;
    }

    public boolean inserAgenda(Agenda agenda) {
        String insereTableSQL = "INSERT INTO evento("
                + "id_evento, atv, assunto, dataini, horaini, datater, horater, cor) "
                + "VALUES ('"+agenda.getId()+"', '1', '" + agenda.getTitle() + "', "
                + "'" + agenda.getDataini() + "', '" + agenda.getHoraini() + "', "
                + "'" + agenda.getDatater() + "', '" + agenda.getHorater() + "', "
                + "'" + agenda.getBackgroundColor() + "');";
        try {
            if (connectDataBase.openConection() == null) {
                return false;
            }
            statement = connectDataBase.openConection().createStatement();
            statement.executeUpdate(insereTableSQL);
            return true;
        } catch (SQLException e) {
            Erro erroLog = new Erro();
            erroLog.Gravar(e.getMessage(), "DaoAgenda", "inserAgenda");
            return false;
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public boolean inserAuditoriaagenda(Agenda agenda, String auditoria) {
        Timestamp tm = new Timestamp(System.currentTimeMillis());
        String t = new SimpleDateFormat("HH:mm:ss").format(tm);
        Date date = new Date();
        LocalTime thisSec = LocalTime.parse(t);
        String insereTableSQL2 = "INSERT INTO evento_auditoria("
                + "id_evento, desc_auditoria, useralt, dataalt, horaalt, ipalt) "
                + "VALUES ('" + agenda.getId() + "', '" + auditoria + "', "
                + "'" + agenda.getUser() + "', '" + date + "', '" + thisSec + "', "
                + "'" + agenda.getIp() + "');";
        try {
            if (connectDataBase.openConection() == null) {
                return false;
            }
            statement = connectDataBase.openConection().createStatement();
            statement.executeUpdate(insereTableSQL2);
            return true;
        } catch (SQLException e) {
            Erro erroLog = new Erro();
            erroLog.Gravar(e.getMessage(), "DaoAgenda", "inserAuditoriagenda");
            return false;
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public ArrayList<Agenda> listarAgenda() {
        String selectTableSQL = "SELECT * from evento WHERE atv = '1';";
        ArrayList<Agenda> list = new ArrayList<>();
        try {
            if (connectDataBase.openConection() == null) {
                return null;//criar uma variavel no usauario pra condicao do banco
            }
            statement = connectDataBase.openConection().createStatement();
            //statement = dbConnection.createStatement();       
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                Agenda agendaRetorno = new Agenda();
                agendaRetorno.setId(rs.getInt("id_evento"));
                agendaRetorno.setDataini(rs.getString("dataini"));
                agendaRetorno.setHoraini(rs.getString("horaini"));
                agendaRetorno.setDatater(rs.getString("datater"));
                agendaRetorno.setHorater(rs.getString("horater"));
                agendaRetorno.setTitle(rs.getString("assunto"));
                agendaRetorno.setBackgroundColor(rs.getString("cor"));
                list.add(agendaRetorno);
            }
            return list;
        } catch (Exception e) {
            Erro erroLog = new Erro();
            erroLog.Gravar(e.getMessage(), "DaoAgenda", "listarAgenda");
            return null;
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public ArrayList<Agenda> listarCalendario() {
        String selectTableSQL = "SELECT * from evento WHERE atv = '1';";
        ArrayList<Agenda> list = new ArrayList<>();
        try {
            if (connectDataBase.openConection() == null) {
                return null;//criar uma variavel no usauario pra condicao do banco
            }
            statement = connectDataBase.openConection().createStatement();
            //statement = dbConnection.createStatement();       
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                Agenda agendaRetorno = new Agenda();
                agendaRetorno.setId(rs.getInt("id_evento"));
                agendaRetorno.setDataini(rs.getString("dataini"));
                agendaRetorno.setHoraini(rs.getString("horaini"));
                agendaRetorno.setDatater(rs.getString("datater"));
                agendaRetorno.setHorater(rs.getString("horater"));
                agendaRetorno.setStart(agendaRetorno.getDataini()+" "+agendaRetorno.getHoraini());
                agendaRetorno.setEnd(agendaRetorno.getDatater()+" "+agendaRetorno.getHorater());
                agendaRetorno.setTitle(rs.getString("assunto"));
                agendaRetorno.setBackgroundColor(rs.getString("cor"));
                list.add(agendaRetorno);
            }
            return list;
        } catch (Exception e) {
            Erro erroLog = new Erro();
            erroLog.Gravar(e.getMessage(), "DaoAgenda", "listarAgenda");
            return null;
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public void removeAgenda(int id) {
       String insereTableSQL = "UPDATE evento SET atv='0'"
                + " where id_evento = '" + id+ "';";
        try {
            if (connectDataBase.openConection() == null) {
            }
            statement = connectDataBase.openConection().createStatement();
            statement.executeUpdate(insereTableSQL);
        } catch (SQLException e) {
            Erro erroLog = new Erro();
            erroLog.Gravar(e.getMessage(),  "DaoAgenda", "removeAgenda");
        } finally {
            connectDataBase.closeConnection();
        }
    }

}
