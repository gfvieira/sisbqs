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
import src.modelo.Questao;

/**
 *
 * @author root
 */
public class DaoQuestao {

    private ConnectDataBase connectDataBase = null;
    private Statement statement;

    public DaoQuestao() {
        connectDataBase = new ConnectDataBase();
    }

    public ArrayList<DisciplinaModel> listarDisciplina() {
        String selectTableSQL = "SELECT * from disciplina;";
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
                alunoRetorno.setId(rs.getInt("id_disciplina"));
                alunoRetorno.setName(rs.getString("nome"));
                list.add(alunoRetorno);
            }
            return list;
        } catch (Exception e) {
            Erro erroLog = new Erro();
            erroLog.Gravar(e.getMessage(), "Daoquestao", "listarDisciplina");
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
            erroLog.Gravar(e.getMessage(), "Daoquestao", "listarDisciplina");
            return null;
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public void insereDisciplina(String dis) {
        String insereTableSQL = "INSERT INTO disciplina("
                + "nome) "
                + "VALUES ('" + dis + "');";
        try {
            if (connectDataBase.openConection() == null) {
            }
            statement = connectDataBase.openConection().createStatement();
            statement.executeUpdate(insereTableSQL);
        } catch (SQLException e) {
            Erro erroLog = new Erro();
            erroLog.Gravar(e.getMessage(), "DaoQuestao", "insereDisciplina");
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public void insereAssunto(String dis, String assun) {
        String insereTableSQL = "INSERT INTO assunto("
                + "id_disciplina, nome) "
                + "VALUES ('" + dis + "', '" + assun + "');";
        try {
            if (connectDataBase.openConection() == null) {
            }
            statement = connectDataBase.openConection().createStatement();
            statement.executeUpdate(insereTableSQL);
        } catch (SQLException e) {
            Erro erroLog = new Erro();
            erroLog.Gravar(e.getMessage(), "DaoQuestao", "insereAssunto");
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
            erroLog.Gravar(e.getMessage(), "Daoquestao", "buscaQuestao");
            return null;
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public boolean insereQuestao(Questao questao) {
        String insereTableSQL = "INSERT INTO questao("
                + "atv, codigo, disciplina, assunto, origem, "
                + "pergunta, A, B, C, D, E, "
                + "perguntaimg, Aimg, Bimg, Cimg, Dimg, Eimg, "
                + "resposta1, resposta2) "
                + "VALUES ('1', '" + questao.getCodigo() + "', '" + questao.getDisciplina() + "', "
                + "'" + questao.getAssunto() + "', '" + questao.getOrigem() + "', "
                + "'" + questao.getPergunta() + "', '" + questao.getA() + "', '" + questao.getB() + "', "
                + "'" + questao.getC() + "', '" + questao.getD() + "', '" + questao.getE() + "', "
                + "'" + questao.getPerguntaImg() + "', '" + questao.getAImg() + "', '" + questao.getBImg() + "', "
                + "'" + questao.getCImg() + "', '" + questao.getDImg() + "', '" + questao.getEImg() + "', "
                + "'" + questao.getResposta1() + "', '" + questao.getResposta2() + "');";
        try {
            if (connectDataBase.openConection() == null) {
                return false;
            }
            statement = connectDataBase.openConection().createStatement();
            System.out.println(insereTableSQL);
            statement.executeUpdate(insereTableSQL);
            return true;
        } catch (SQLException e) {
            Erro erroLog = new Erro();
            erroLog.Gravar(e.getMessage(), "DaoQuestao", "insereQuestao");
            return false;
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public boolean insereAuditoriaQuestao(Questao questao, String auditoria) {
        Timestamp tm = new Timestamp(System.currentTimeMillis());
        String t = new SimpleDateFormat("HH:mm:ss").format(tm);
        Date date = new Date();
        LocalTime thisSec = LocalTime.parse(t);
        String insereTableSQL = "INSERT INTO questao_auditoria("
                + "codigo, desc_auditoria, useralt, dataalt, horaalt, ipalt) "
                + "VALUES ('" + questao.getCodigo() + "', '" + auditoria + "', "
                + "'" + questao.getUser() + "', '" + date + "', '" + thisSec + "', "
                + "'" + questao.getIp() + "');";
        try {
            if (connectDataBase.openConection() == null) {
                return false;
            }
            statement = connectDataBase.openConection().createStatement();
            statement.executeUpdate(insereTableSQL);
            return true;
        } catch (SQLException e) {
            Erro erroLog = new Erro();
            erroLog.Gravar(e.getMessage(), "DaoQuestao", "insereAuditoriaQuestao");
            return false;
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public ArrayList<Questao> listarQuestao() {
        String selectTableSQL = "SELECT a.codigo, b.nome as disciplina, c.nome as assunto, "
                + "a.origem, a.pergunta, a.a, a.b, a.c, a.d, a.e,"
                + "a.perguntaimg, a.aimg, a.bimg, a.cimg, a.dimg, a.eimg, a.resposta1, a.resposta2 "
                + "from questao a, disciplina b, assunto c where "
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
            erroLog.Gravar(e.getMessage(), "DaoQuestao", "listarQuestao");
            return null;
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public int buscaIdDisciplina(String string) {
        String selectTableSQL = "SELECT id_disciplina from disciplina where nome = '" + string + "';";
        int retorno = 0;
        try {
            if (connectDataBase.openConection() == null) {
                return 0;//criar uma variavel no usauario pra condicao do banco
            }
            statement = connectDataBase.openConection().createStatement();
            //statement = dbConnection.createStatement();       
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                retorno = rs.getInt("id_disciplina");
            }
            return retorno;
        } catch (Exception e) {
            Erro erroLog = new Erro();
            erroLog.Gravar(e.getMessage(), "Daoquestao", "listarDisciplina");
            return 0;
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public int buscaIdAssunto(String string) {
        String selectTableSQL = "SELECT id_assunto from assunto where nome = '" + string + "';";
        int retorno = 0;
        try {
            if (connectDataBase.openConection() == null) {
                return 0;//criar uma variavel no usauario pra condicao do banco
            }
            statement = connectDataBase.openConection().createStatement();
            //statement = dbConnection.createStatement();       
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                retorno = rs.getInt("id_assunto");
            }
            return retorno;
        } catch (Exception e) {
            Erro erroLog = new Erro();
            erroLog.Gravar(e.getMessage(), "Daoquestao", "listarDisciplina");
            return 0;
        } finally {
            connectDataBase.closeConnection();
        }
    }

}
