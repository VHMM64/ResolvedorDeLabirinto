package banco;

import java.sql.*;

public class ConexaoBD {

	private static String driverName = "com.mysql.jdbc.Driver";                        
    private static String serverName = "localhost:3306";   
    private static String url = "jdbc:mysql://" + serverName + "/ResolvedorLabirinto?useSSL=false";
    private static String username = "root";            
    private static String password = "password";     
 
    public static java.sql.Connection getConexaoBD() throws ClassNotFoundException {   
        try {
        	
            Class.forName(driverName);
            
            java.sql.Connection conexao;
            
            conexao = DriverManager.getConnection(url, username, password);
            System.out.println(conexao);
            
            return conexao;
            
        } 
        catch (SQLException e) {

        	e.printStackTrace();
            System.out.println("Nao foi possivel conectar ao Banco de Dados.");
            return null;
        }
    }
 
    public static boolean FecharConexao() throws ClassNotFoundException {
        try {
        	
            ConexaoBD.getConexaoBD().close();
            return true;
 
        } catch (SQLException e) {
            return false;
        }
    }
 
    public static java.sql.Connection ReiniciarConexao() throws ClassNotFoundException {
        FecharConexao();
        return ConexaoBD.getConexaoBD();
    }
}
