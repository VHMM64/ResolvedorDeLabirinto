package projetolabirinto;

import java.awt.event.ActionEvent;
import java.net.InetAddress;

import javax.swing.JOptionPane;

import banco.ConexaoBD;
import banco.LabirintoDAO;
import comunicacao.PedidoSalvamento;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class SalvarLabirintoBanco extends ResolvedorLabirintoActionListener {
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	public SalvarLabirintoBanco(ResolvedorDeLabirintoUI resolvedorDeLabirintoUI) {
		super(resolvedorDeLabirintoUI);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		ConexaoBD conexao = new ConexaoBD();
		try {
			java.sql.Connection conect = conexao.getConexaoBD();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} 
		
		LabirintoDAO labirintoDao = new LabirintoDAO();
		banco.Labirinto labirintoSalva = new banco.Labirinto();
		
		
		
		try {
			
			ResolvedorDeLabirintoUI LabirintoTela = new ResolvedorDeLabirintoUI();
			LabirintoTela.mostraLabirintoBanco(labirintoSalva.getLabirinto());
			
			InetAddress ip = InetAddress.getLocalHost();
			System.out.println(ip.getHostAddress());
			
			labirintoSalva.setCliente_ip(ip.getHostAddress());
			labirintoSalva.setNome("LabirintoDoMaligno");
			labirintoSalva.setCreate_date(LocalDateTime.now());
			labirintoSalva.setModified_date(LocalDateTime.now());
			labirintoSalva.setLabirinto(LabirintoTela.toString());
			
			System.out.println(LabirintoTela.toString());
			
			labirintoDao.salvarLabirinto(labirintoSalva);

			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}
