/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import src.Erro;
import src.modelo.Aluno;
import src.modelo.AssuntoModel;
import src.modelo.Indicador;

/**
 *
 * @author root
 */
public class DaoIndicador {
    
    private ConnectDataBase connectDataBase = null;
    private Statement statement;

    public DaoIndicador() {
        connectDataBase = new ConnectDataBase();
    }

    public ArrayList<AssuntoModel> listarAssunto(String aluno) {
        String selectTableSQL = "SELECT assunto, nome FROM indicador"
                + " where codigo_aluno = '"+aluno+"' group by assunto, nome;";
        ArrayList<AssuntoModel> retorno = new ArrayList<>();
        try {
            if (connectDataBase.openConection() == null) {
                return null;//criar uma variavel no usauario pra condicao do banco
            }
            statement = connectDataBase.openConection().createStatement();
            //statement = dbConnection.createStatement();       
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                AssuntoModel temp = new AssuntoModel();
                temp.setId(rs.getInt("assunto"));
                temp.setName(rs.getString("nome"));
                retorno.add(temp);
            }
            return retorno;
        } catch (Exception e) {
            Erro erroLog = new Erro();
            erroLog.Gravar(e.getMessage(), "DaoIndicador", "listarAssunto");
            return null;
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public ArrayList<Indicador> listarIndicadorGeral(AssuntoModel assunto, String aluno) {
        String selectTableSQL = "select count(*) AS total, "
                + "sum(case when resposta = '1' then 1 else 0 end) as acertou, "
                + "sum(case when resposta = '2' then 1 else 0 end) AS errou, "
                + "sum(case when resposta = '3' then 1 else 0 end) AS duvidou "
                + "from indicador "
                + "where assunto = '"+assunto.getId()+"' "
                + "and codigo_aluno='"+aluno+"';";
        ArrayList<Indicador> retorno = new ArrayList<>();
        try {
            if (connectDataBase.openConection() == null) {
                return null;//criar uma variavel no usauario pra condicao do banco
            }
            statement = connectDataBase.openConection().createStatement();
            //statement = dbConnection.createStatement();       
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                Indicador temp = new Indicador();
                temp.setDisciplina(assunto.getName());
                temp.setAcerto(rs.getInt("acertou"));
                temp.setErro(rs.getInt("errou"));
                temp.setDuvida(rs.getInt("duvidou"));
                retorno.add(temp);
            }
            return retorno;
        } catch (Exception e) {
            Erro erroLog = new Erro();
            erroLog.Gravar(e.getMessage(), "DaoIndicador", "listarIndicador");
            return null;
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public ArrayList<AssuntoModel> listarCodigoSimuladoASC(String aluno) {
        String selectTableSQL = "SELECT assunto, nome, codigo_simulado FROM indicador "
                + "where codigo_aluno = '"+aluno+"' "
                + "group by assunto, nome, codigo_simulado order by codigo_simulado ASC;";
        ArrayList<AssuntoModel> retorno = new ArrayList<>();
        try {
            if (connectDataBase.openConection() == null) {
                return null;//criar uma variavel no usauario pra condicao do banco
            }
            statement = connectDataBase.openConection().createStatement();
            //statement = dbConnection.createStatement();       
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                AssuntoModel temp = new AssuntoModel();
                temp.setId(rs.getInt("assunto"));
                temp.setName(rs.getString("nome"));
                temp.setCodigoSimulado(rs.getString("codigo_simulado"));
                retorno.add(temp);
            }
            return retorno;
        } catch (Exception e) {
            Erro erroLog = new Erro();
            erroLog.Gravar(e.getMessage(), "DaoIndicador", "listarAssunto");
            return null;
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public ArrayList<AssuntoModel> listarCodigoSimuladoDESC(String aluno) {
        String selectTableSQL = "SELECT assunto, nome, codigo_simulado FROM indicador "
                + "where codigo_aluno = '"+aluno+"' "
                + "group by assunto, nome, codigo_simulado order by codigo_simulado DESC;";
        ArrayList<AssuntoModel> retorno = new ArrayList<>();
        try {
            if (connectDataBase.openConection() == null) {
                return null;//criar uma variavel no usauario pra condicao do banco
            }
            statement = connectDataBase.openConection().createStatement();
            //statement = dbConnection.createStatement();       
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                AssuntoModel temp = new AssuntoModel();
                temp.setId(rs.getInt("assunto"));
                temp.setName(rs.getString("nome"));
                temp.setCodigoSimulado(rs.getString("codigo_simulado"));
                retorno.add(temp);
            }
            return retorno;
        } catch (Exception e) {
            Erro erroLog = new Erro();
            erroLog.Gravar(e.getMessage(), "DaoIndicador", "listarAssunto");
            return null;
        } finally {
            connectDataBase.closeConnection();
        }
    }

    public ArrayList<Indicador>listarIndicador(AssuntoModel assunto, String aluno) {
        String selectTableSQL = "select count(*) AS total, "
                + "sum(case when resposta = '1' then 1 else 0 end) as acertou, "
                + "sum(case when resposta = '2' then 1 else 0 end) AS errou, "
                + "sum(case when resposta = '3' then 1 else 0 end) AS duvidou "
                + "from indicador "
                + "where codigo_simulado = '"+assunto.getCodigoSimulado()+"' "
                + "and assunto='"+assunto.getId()+"'  "
                + "and codigo_aluno='"+aluno+"';";
        ArrayList<Indicador> retorno = new ArrayList<>();
        try {
            if (connectDataBase.openConection() == null) {
                return null;//criar uma variavel no usauario pra condicao do banco
            }
            statement = connectDataBase.openConection().createStatement();
            //statement = dbConnection.createStatement();       
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                Indicador temp = new Indicador();
                temp.setDisciplina(assunto.getName());
                temp.setAcerto(rs.getInt("acertou"));
                temp.setErro(rs.getInt("errou"));
                temp.setDuvida(rs.getInt("duvidou"));
                retorno.add(temp);
            }
            return retorno;
        } catch (Exception e) {
            Erro erroLog = new Erro();
            erroLog.Gravar(e.getMessage(), "DaoIndicador", "listarIndicador");
            return null;
        } finally {
            connectDataBase.closeConnection();
        }
    }
}
