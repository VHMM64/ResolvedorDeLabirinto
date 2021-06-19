package projetolabirinto;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import banco.ConexaoBD;
import banco.LabirintoDAO;

public class AbrirLabirintoBanco extends ResolvedorLabirintoActionListener {
	
	public AbrirLabirintoBanco(ResolvedorDeLabirintoUI resolvedorDeLabirintoUI) {
		super(resolvedorDeLabirintoUI);
		// TODO Auto-generated constructor stub
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
		banco.Labirinto labirinto = new banco.Labirinto();
		
		try {
			labirinto = labirintoDao.buscarLabirinto("LabirintoDoMaligno");
			System.out.println(labirinto.getLabirinto());
			
			ResolvedorDeLabirintoUI LabirintoBanco = new ResolvedorDeLabirintoUI();
			LabirintoBanco.mostraLabirintoBanco(labirinto.getLabirinto());
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
    }
	
	
}
