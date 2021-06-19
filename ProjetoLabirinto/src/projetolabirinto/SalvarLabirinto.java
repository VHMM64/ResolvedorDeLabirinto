package projetolabirinto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SalvarLabirinto extends ResolvedorLabirintoActionListener {
	
	public SalvarLabirinto(ResolvedorDeLabirintoUI resolvedorDeLabirintoUI) {
        super(resolvedorDeLabirintoUI);        
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		System.out.println("Abrir Janela Salvar");
		JFileChooser fc = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt", "text");
        fc.setFileFilter(filter);
        
        try {
        
        	int returnVal = fc.showSaveDialog(null);
        	
        	if (returnVal == JFileChooser.APPROVE_OPTION) {
        		String labirinto = this.resolvedorDeLabirintoUI.getTxtLabirintoResolvido().getText();
                File file = fc.getSelectedFile();
                
                System.out.println(labirinto);
                
                int input = 0;
                if (file.exists()) 
                    input = JOptionPane.showConfirmDialog(null, "Tem certeza que quer sobreescrever este arquivo?");
                
                if (input == 0) {
                	System.out.println("Labirinto Salvo");
                	
                    BufferedWriter escritor = new BufferedWriter(new FileWriter(file));
                    escritor.write(labirinto);
                    escritor.close();
                    
                    }    
                }
        	
        }
                   
        catch(Exception ex) {
        	
            JOptionPane.showMessageDialog(null, "Erro na abertura do arquivo:", "",
                    JOptionPane.WARNING_MESSAGE);
        }
	
	}
	
}