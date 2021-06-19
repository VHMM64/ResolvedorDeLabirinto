package banco;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LabirintoDAO {

	public Labirinto buscarLabirinto(String nome) throws Exception {       
        if (nome == null || nome == "")
            throw new Exception("O nome não pode ser vazio ou nulo");

        Connection con = ConexaoBD.getConexaoBD();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        stmt = con.prepareStatement("SELECT  cliente_ip, nome, create_date, modified_date, labirinto FROM labirinto WHERE nome = ?");
        
        stmt.setString(1, nome);

        Labirinto Labirinto = null;

        try {
            rs = stmt.executeQuery();

            while (rs.next()) {
                Labirinto = new Labirinto();

                Labirinto.setNome(rs.getString("nome"));
                Labirinto.setCliente_ip(rs.getString("cliente_ip"));
                Labirinto.setCreate_date(rs.getObject("create_date", LocalDateTime.class));
                Labirinto.setModified_date(rs.getObject("modified_date", LocalDateTime.class));

                Labirinto.setLabirinto(rs.getString("labirinto"));
            }
        }
        catch (SQLException e) {
            throw e;
        }
        finally {
            stmt.close();
            ConexaoBD.FecharConexao();
        }

        return Labirinto;
    }
	


    public void salvarLabirinto(Labirinto Labirinto) throws Exception {
        if (Labirinto == null)
            throw new Exception("O Labirinto não pode ser nulo");

        Connection con = ConexaoBD.getConexaoBD();
        PreparedStatement stmt = null;

        Labirinto LabirintoExistente = buscarLabirinto(Labirinto.getNome());

        try {
            if (LabirintoExistente != null) {
                stmt = con.prepareStatement("UPDATE labirinto SET modified_date = ?, labirinto = ? WHERE nome = ?");

                stmt.setObject(1, Labirinto.getModified_date());
                stmt.setString(2, Labirinto.getLabirinto());
                stmt.setString(3, Labirinto.getNome());

                stmt.executeUpdate();
            } else {
                stmt = con.prepareStatement("INSERT INTO Labirinto (cliente_ip, nome, create_date, modified_date ,labirinto) VALUES (?,?,?,?,?)");

                stmt.setString(1, Labirinto.getCliente_ip());
                stmt.setString(2, Labirinto.getNome());
                stmt.setObject(3, Labirinto.getCreate_date());
                stmt.setObject(4, Labirinto.getModified_date());
                stmt.setString(5, Labirinto.getLabirinto());
    
                stmt.executeUpdate();
            }
        }
        catch (SQLException e) {
            throw e;
        }
        finally {
            stmt.close();
            ConexaoBD.FecharConexao();
        }
    }

	
}
