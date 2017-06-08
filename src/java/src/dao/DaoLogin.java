package src.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import src.Erro;
import src.modelo.Usuario;

public class DaoLogin {
    private ConnectDataBase connectDataBase = null;
    private Statement statement;
    private Erro erroLog = null;

    
    public DaoLogin() {
        connectDataBase = new ConnectDataBase();  
    }    
    
    public Usuario loginUsuario(Usuario usuario){
        String selectTableSQL = "SELECT * from usuario WHERE login='"+usuario.getNip()+"' and senha='"+usuario.getSenha()+"';";
        Usuario usuarioRetorno = null;
        try{
            if(connectDataBase.openConection() == null)
                return null;//criar uma variavel no usauario pra condicao do banco
            statement = connectDataBase.openConection().createStatement(); 
            //statement = dbConnection.createStatement();       
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {   
                //atv, pass, nip, senha, nome, posto_grad, guerra, setor, ramal, pri, data_cad, last_access, access
                usuarioRetorno = new Usuario();
                usuarioRetorno.setNome(rs.getString("nome"));
                usuarioRetorno.setAtivo(rs.getInt("atv"));
                usuarioRetorno.setPassword(rs.getInt("pass"));
            }
            return usuarioRetorno;
        }
        catch(SQLException e){
            erroLog = new Erro();
            erroLog.Gravar(e.getMessage(), "DaoLogin", "loginUsuario");
            return null;
        }
        finally {
            connectDataBase.closeConnection();
        }
    }

    public Boolean attSenhaUsuario(Usuario usuario) {
        String insereTableSQL = "UPDATE usuario SET senha = '"+usuario.getSenha()+"', pass = '0' WHERE nip ='"+usuario.getNip()+"';";
        try{
            if(connectDataBase.openConection() == null)
                return false;
            statement = connectDataBase.openConection().createStatement();   
            statement.executeUpdate(insereTableSQL);
            statement.close();
            connectDataBase.closeConnection();
            return true;
        }catch (SQLException e){
            erroLog = new Erro();
            erroLog.Gravar(e.getMessage(), "DaoLogin_attSenhaUsuario");
            return false;
        }
        finally {
            connectDataBase.closeConnection();
        }
    }    
    
    public Boolean lastAccess(Usuario usuario) {
        Timestamp tm = new Timestamp(System.currentTimeMillis());
        String t = new SimpleDateFormat("HH:mm:ss").format(tm);
        Date date = new Date();
        LocalTime thisSec = LocalTime.parse(t);
        String insereTableSQL = "UPDATE usuario SET last_access = '"+date+"' WHERE nip ='"+usuario.getNip()+"';";
        String insereTableSQL1 = "INSERT INTO control_logon (id_session, nip, data_access, hora_access, ip_logon) "
                + "VALUES ('"+usuario.getId_session()+"','"+usuario.getNip()+"', "
                + "'"+date+"', '"+thisSec+"', '"+usuario.getIp_access()+"');";
        try{
            if(connectDataBase.openConection() == null)
                return false;
            statement = connectDataBase.openConection().createStatement();   
            statement.executeUpdate(insereTableSQL);
            statement.executeUpdate(insereTableSQL1);
            statement.close();
            connectDataBase.closeConnection();
            return true;
        }catch (SQLException e){
            erroLog = new Erro();
            erroLog.Gravar(e.getMessage(), "DaoLogin_lastAccess");
            return false;
        }
        finally {
            connectDataBase.closeConnection();
        }
    }  
}

