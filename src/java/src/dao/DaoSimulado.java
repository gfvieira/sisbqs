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
import src.modelo.DisciplinaModel;
import src.modelo.DisciplinaSimulado;
import src.modelo.Questao;

/**
 *
 * @author root
 */
public class DaoSimulado {

    private ConnectDataBase connectDataBase = null;
    private Statement statement;

    public DaoSimulado() {
        connectDataBase = new ConnectDataBase();
    }

    public ArrayList<DisciplinaSimulado> buscarAssunto() {
        String selectTableSQL = "select a.id_disciplina ,b.nome as disciplina, a.id_assunto, a.nome as assunto "
                + "from assunto a, disciplina b "
                + "where a.id_disciplina = b.id_disciplina;";
        ArrayList<DisciplinaSimulado> list = new ArrayList<>();
        try {
            if (connectDataBase.openConection() == null) {
                return null;//criar uma variavel no usauario pra condicao do banco
            }
            statement = connectDataBase.openConection().createStatement();
            //statement = dbConnection.createStatement();       
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                DisciplinaSimulado disciplinaRetorno = new DisciplinaSimulado();
                disciplinaRetorno.setId_disciplina(rs.getInt("id_disciplina"));
                disciplinaRetorno.setId_assunto(rs.getInt("id_assunto"));
                disciplinaRetorno.setDisciplina(rs.getString("disciplina"));
                disciplinaRetorno.setAssunto(rs.getString("assunto"));
                list.add(disciplinaRetorno);
            }
            return list;
        } catch (Exception e) {
            Erro erroLog = new Erro();
            erroLog.Gravar(e.getMessage(), "DaoSimulado", "buscarAssunto");
            return null;
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public ArrayList<DisciplinaModel> listarAssunto(String dis) {
        String selectTableSQL = "SELECT * from assunto where id_disciplina='" + dis + "';";
        ArrayList<DisciplinaModel> list = new ArrayList<>();
        try {
            if (connectDataBase.openConection() == null) {
                return null;//criar uma variavel no usauario pra condicao do banco
            }
            statement = connectDataBase.openConection().createStatement();
            //statement = dbConnection.createStatement();       
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                DisciplinaModel alunoRetorno = new DisciplinaModel();
                alunoRetorno.setId(rs.getInt("id_assunto"));
                alunoRetorno.setName(rs.getString("nome"));
                list.add(alunoRetorno);
            }
            return list;
        } catch (Exception e) {
            Erro erroLog = new Erro();
            erroLog.Gravar(e.getMessage(), "DaoSimulado", "listarDisciplina");
            return null;
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public Questao buscaQuestao(String codigo) {
        String selectTableSQL = "SELECT a.codigo, b.nome as disciplina, c.nome as assunto, "
                + "a.origem, a.pergunta, a.a, a.b, a.c, a.d, a.e,"
                + "a.perguntaimg, a.aimg, a.bimg, a.cimg, a.dimg, a.eimg, a.resposta1, a.resposta2 "
                + "from questao a, disciplina b, assunto c where "
                + "a.disciplina = b.id_disciplina and "
                + "a.assunto = c.id_assunto and "
                + "a.codigo = '" + codigo + "';";
        Questao questao = null;
        try {
            if (connectDataBase.openConection() == null) {
                return null;//criar uma variavel no usauario pra condicao do banco
            }
            statement = connectDataBase.openConection().createStatement();
            //statement = dbConnection.createStatement();       
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                questao = new Questao();
                questao.setCodigo(rs.getString("codigo"));
                questao.setNomeDisciplina(rs.getString("disciplina"));
                questao.setNomeAssunto(rs.getString("assunto"));
                questao.setOrigem(rs.getString("origem"));
                questao.setPergunta(rs.getString("pergunta"));
                questao.setA(rs.getString("a"));
                questao.setB(rs.getString("b"));
                questao.setC(rs.getString("c"));
                questao.setD(rs.getString("d"));
                questao.setE(rs.getString("e"));
                questao.setPerguntaImg(rs.getString("perguntaimg"));
                questao.setAImg(rs.getString("aimg"));
                questao.setBImg(rs.getString("bimg"));
                questao.setCImg(rs.getString("cimg"));
                questao.setDImg(rs.getString("dimg"));
                questao.setEImg(rs.getString("eimg"));
                questao.setResposta1(rs.getString("resposta1"));
                questao.setResposta2(rs.getString("resposta2"));
            }
            return questao;
        } catch (Exception e) {
            Erro erroLog = new Erro();
            erroLog.Gravar(e.getMessage(), "DaoSimulado", "buscaQuestao");
            return null;
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public ArrayList<Questao> listarQuestao(int assunto) {
        String selectTableSQL = "SELECT a.codigo, b.nome as disciplina, c.nome as assunto, "
                + "a.origem, a.pergunta, a.a, a.b, a.c, a.d, a.e,"
                + "a.perguntaimg, a.aimg, a.bimg, a.cimg, a.dimg, a.eimg, a.resposta1, a.resposta2 "
                + "from questao a, disciplina b, assunto c where "
                + "a.assunto = '" + assunto + "' and "
                + "a.disciplina = b.id_disciplina and "
                + "a.assunto = c.id_assunto and "
                + "atv = '1'";
        ArrayList<Questao> list = new ArrayList<>();
        try {
            if (connectDataBase.openConection() == null) {
                return null;//criar uma variavel no usauario pra condicao do banco
            }
            statement = connectDataBase.openConection().createStatement();
            //statement = dbConnection.createStatement();       
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                Questao questao = new Questao();
                questao.setCodigo(rs.getString("codigo"));
                questao.setNomeDisciplina(rs.getString("disciplina"));
                questao.setNomeAssunto(rs.getString("assunto"));
                questao.setOrigem(rs.getString("origem"));
                if (!rs.getString("pergunta").equals("")) {
                    questao.setPergunta(rs.getString("pergunta"));
                }
                questao.setA(rs.getString("a"));
                questao.setB(rs.getString("b"));
                questao.setC(rs.getString("c"));
                questao.setD(rs.getString("d"));
                questao.setE(rs.getString("e"));
                questao.setPerguntaImg(rs.getString("perguntaimg"));
                questao.setAImg(rs.getString("aimg"));
                questao.setBImg(rs.getString("bimg"));
                questao.setCImg(rs.getString("cimg"));
                questao.setDImg(rs.getString("dimg"));
                questao.setEImg(rs.getString("eimg"));
                questao.setResposta1(rs.getString("resposta1"));
                questao.setResposta2(rs.getString("resposta2"));
                list.add(questao);
            }
            return list;
        } catch (Exception e) {
            Erro erroLog = new Erro();
            erroLog.Gravar(e.getMessage(), "DaoSimulado", "listarQuestao");
            return null;
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public boolean insereSimulado(String numeroSimulado, int numero, String questao, String user, String ip) {
        Timestamp tm = new Timestamp(System.currentTimeMillis());
        String t = new SimpleDateFormat("HH:mm:ss").format(tm);
        Date date = new Date();
        LocalTime thisSec = LocalTime.parse(t);
        String insereTableSQL = "INSERT INTO simulado(codigo_simulado, numero, pergunta, "
                + "usercad, datacad, horacad, ipcad)VALUES ('" + numeroSimulado + "', '"+numero+"', "
                + "'" + questao + "', '" + user + "', '" + date + "', '" + thisSec + "', "
                + "'" + ip + "');";

        try {
            if (connectDataBase.openConection() == null) {
                return false;
            }
            System.out.println(insereTableSQL);
            statement = connectDataBase.openConection().createStatement();
            statement.executeUpdate(insereTableSQL);
            return true;
        } catch (SQLException e) {
            Erro erroLog = new Erro();
            erroLog.Gravar(e.getMessage(), "DaoSimulado", "insereSimulado");
            return false;
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public boolean insereResultado(String simulado, int numero, String aluno, String questao, String user, String ip) {
        Timestamp tm = new Timestamp(System.currentTimeMillis());
        String t = new SimpleDateFormat("HH:mm:ss").format(tm);
        Date date = new Date();
        LocalTime thisSec = LocalTime.parse(t);
        String insereTableSQL = "INSERT INTO resultado_simulado(codigo_simulado, codigo_aluno, numero, "
                + "resposta, usercad, datacad, horacad, ipcad)"
                + "VALUES ('" + simulado + "', '" + aluno + "', '"+numero+"', "
                + "'" + questao + "', '" + user + "', '" + date + "', '" + thisSec + "', "
                + "'" + ip + "');";

        try {
            if (connectDataBase.openConection() == null) {
                return false;
            }
            System.out.println(insereTableSQL);
            statement = connectDataBase.openConection().createStatement();
            statement.executeUpdate(insereTableSQL);
            return true;
        } catch (SQLException e) {
            Erro erroLog = new Erro();
            erroLog.Gravar(e.getMessage(), "DaoSimulado", "insereResultado");
            return false;
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public ArrayList<String> buscaSimulado(String aluno) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<String> buscaResposta(String simulado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Questao> buscaPerguntas(String simulado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
