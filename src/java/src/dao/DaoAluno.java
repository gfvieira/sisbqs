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
import src.modelo.Aluno;
import src.modelo.AlunoAuditoria;

/**
 *
 * @author root
 */
public class DaoAluno {

    private ConnectDataBase connectDataBase = null;
    private Statement statement;

    public DaoAluno() {
        connectDataBase = new ConnectDataBase();
    }

    public Aluno buscaAlunoCPF(String cpf) {
        String selectTableSQL = "SELECT * from aluno WHERE cpf = '" + cpf + "';";
        Aluno alunoRetorno = null;
        try {
            if (connectDataBase.openConection() == null) {
                return null;//criar uma variavel no usauario pra condicao do banco
            }
            statement = connectDataBase.openConection().createStatement();
            //statement = dbConnection.createStatement();       
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                alunoRetorno = new Aluno();
                alunoRetorno.setId(rs.getInt("id_aluno"));
                alunoRetorno.setMatricula(rs.getString("matricula"));
                alunoRetorno.setNome(rs.getString("nome"));
                alunoRetorno.setCpf(rs.getString("cpf"));
                alunoRetorno.setRg(rs.getString("rg"));
                alunoRetorno.setMail(rs.getString("mail"));
                alunoRetorno.setCel(rs.getString("cel"));
                alunoRetorno.setTel(rs.getString("tel"));
            }
            return alunoRetorno;
        } catch (Exception e) {
            Erro erroLog = new Erro();
            erroLog.Gravar(e.getMessage(), "DaoAluno", "buscaAluno");
            return null;
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public Aluno buscaAlunoMATRICULA(String matricula) {
        String selectTableSQL = "SELECT * from aluno WHERE matricula = '" + matricula + "';";
        Aluno alunoRetorno = null;
        try {
            if (connectDataBase.openConection() == null) {
                return null;//criar uma variavel no usauario pra condicao do banco
            }
            statement = connectDataBase.openConection().createStatement();
            //statement = dbConnection.createStatement();       
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                alunoRetorno = new Aluno();
                alunoRetorno.setId(rs.getInt("id_aluno"));
                alunoRetorno.setMatricula(rs.getString("matricula"));
                alunoRetorno.setNome(rs.getString("nome"));
                alunoRetorno.setCpf(rs.getString("cpf"));
                alunoRetorno.setRg(rs.getString("rg"));
                alunoRetorno.setMail(rs.getString("mail"));
                alunoRetorno.setCel(rs.getString("cel"));
                alunoRetorno.setTel(rs.getString("tel"));
            }
            return alunoRetorno;
        } catch (Exception e) {
            Erro erroLog = new Erro();
            erroLog.Gravar(e.getMessage(), "DaoAluno", "buscaAluno");
            return null;
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public boolean inserAluno(Aluno aluno) {
        
        String insereTableSQL = "INSERT INTO aluno("
                + "atv, matricula, nome, cpf, rg, mail, cel, tel) "
                + "VALUES ('1', '" + aluno.getMatricula() + "', "
                + "'" + aluno.getNome() + "', '" + aluno.getCpf() + "', "
                + "'" + aluno.getRg() + "', '" + aluno.getMail() + "', "
                + "'" + aluno.getCel() + "', '" + aluno.getTel() + "');";
        try {
            if (connectDataBase.openConection() == null) {
                return false;
            }
            statement = connectDataBase.openConection().createStatement();
            statement.executeUpdate(insereTableSQL);
            return true;
        } catch (SQLException e) {
            Erro erroLog = new Erro();
            erroLog.Gravar(e.getMessage(), "DaoAluno", "insereAluno");
            return false;
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public boolean inserAuditorialuno(Aluno aluno, String auditoria) {
        Timestamp tm = new Timestamp(System.currentTimeMillis());
        String t = new SimpleDateFormat("HH:mm:ss").format(tm);
        Date date = new Date();
        LocalTime thisSec = LocalTime.parse(t);
        String insereTableSQL2 = "INSERT INTO aluno_auditoria("
                + "matricula, desc_auditoria, useralt, dataalt, horaalt, ipalt) "
                + "VALUES ('" + aluno.getMatricula() + "', '" + auditoria + "', "
                + "'" + aluno.getUsercad() + "', '" + date + "', '" + thisSec + "', "
                + "'" + aluno.getIpcad() + "');";
        try {
            if (connectDataBase.openConection() == null) {
                return false;
            }
            statement = connectDataBase.openConection().createStatement();
            statement.executeUpdate(insereTableSQL2);
            return true;
        } catch (SQLException e) {
            Erro erroLog = new Erro();
            erroLog.Gravar(e.getMessage(), "DaoAluno", "inserAuditorialuno");
            return false;
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public ArrayList<Aluno> listarAluno() {
        String selectTableSQL = "SELECT * from aluno WHERE atv = '1';";
        ArrayList<Aluno> list = new ArrayList<>();
        try {
            if (connectDataBase.openConection() == null) {
                return null;//criar uma variavel no usauario pra condicao do banco
            }
            statement = connectDataBase.openConection().createStatement();
            //statement = dbConnection.createStatement();       
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                Aluno alunoRetorno = new Aluno();
                alunoRetorno.setId(rs.getInt("id_aluno"));
                alunoRetorno.setMatricula(rs.getString("matricula"));
                alunoRetorno.setNome(rs.getString("nome"));
                alunoRetorno.setCpf(rs.getString("cpf"));
                alunoRetorno.setRg(rs.getString("rg"));
                alunoRetorno.setMail(rs.getString("mail"));
                alunoRetorno.setCel(rs.getString("cel"));
                alunoRetorno.setTel(rs.getString("tel"));
                list.add(alunoRetorno);
            }
            return list;
        } catch (Exception e) {
            Erro erroLog = new Erro();
            erroLog.Gravar(e.getMessage(), "DaoAluno", "listarAluno");
            return null;
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public ArrayList<AlunoAuditoria> buscaAuditoria(String matricula) {
        String selectTableSQL = "SELECT * from aluno_auditoria WHERE matricula = '" + matricula + "';";
        ArrayList<AlunoAuditoria> list = new ArrayList<>();
        try {
            if (connectDataBase.openConection() == null) {
                return null;//criar uma variavel no usauario pra condicao do banco
            }
            statement = connectDataBase.openConection().createStatement();
            //statement = dbConnection.createStatement();       
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                AlunoAuditoria alunoRetorno = new AlunoAuditoria();
                alunoRetorno.setId(rs.getInt("id_aluno_aud"));
                alunoRetorno.setMatricula(rs.getString("matricula"));
                alunoRetorno.setDescAuditoria(rs.getString("desc_auditoria"));
                alunoRetorno.setUseralt(rs.getString("useralt"));
                alunoRetorno.setDataalt(rs.getDate("dataalt"));
                alunoRetorno.setHoraalt(LocalTime.parse(rs.getString("horaalt")));
                alunoRetorno.setIpalt(rs.getString("ipalt"));
                list.add(alunoRetorno);
            }
            return list;
        } catch (Exception e) {
            Erro erroLog = new Erro();
            erroLog.Gravar(e.getMessage(), "DaoAluno", "buscaAuditoria");
            return null;
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public boolean attAluno(Aluno aluno) {
        String insereTableSQL = "UPDATE aluno SET nome='" + aluno.getNome() + "',"
                + " cpf='" + aluno.getCpf() + "', rg='" + aluno.getRg() + "',"
                + " mail='" + aluno.getMail() + "', cel='" + aluno.getCel() + "',"
                + " tel='" + aluno.getTel() + "'"
                + " where matricula = '" + aluno.getMatricula() + "';";
        try {
            if (connectDataBase.openConection() == null) {
                return false;
            }
            statement = connectDataBase.openConection().createStatement();
            statement.executeUpdate(insereTableSQL);
            return true;
        } catch (SQLException e) {
            Erro erroLog = new Erro();
            erroLog.Gravar(e.getMessage(),  "DaoAluno", "attAluno");
            return false;
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public void removeAluno(String matricula) {
        String insereTableSQL = "UPDATE aluno SET atv='0'"
                + " where matricula = '" + matricula+ "';";
        try {
            if (connectDataBase.openConection() == null) {
            }
            statement = connectDataBase.openConection().createStatement();
            statement.executeUpdate(insereTableSQL);
        } catch (SQLException e) {
            Erro erroLog = new Erro();
            erroLog.Gravar(e.getMessage(),  "DaoAluno", "removeAluno");
        } finally {
            connectDataBase.closeConnection();
        }
    }

}
